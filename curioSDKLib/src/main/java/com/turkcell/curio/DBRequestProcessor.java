/*
 * Copyright (C) 2014 Turkcell
 * 
 * Created by Can Ciloglu on 10 Haz 2014
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.turkcell.curio;

import android.util.Log;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.turkcell.curio.model.OfflineRequest;
import com.turkcell.curio.utils.Constants;
import com.turkcell.curio.utils.CurioDBHelper;
import com.turkcell.curio.utils.CurioLogger;

/**
 * Processor thread for all requests (periodic/offline/online).
 * Requests pushed to queues and then polled and processed from those queues.
 *
 * @author Can Ciloglu
 */
public class DBRequestProcessor implements Runnable {
    private static final String TAG = DBRequestProcessor.class.getSimpleName();

    private static final Queue<OfflineRequest> offlineQueue = new ConcurrentLinkedQueue<>();
    private static final Queue<OfflineRequest> periodicDispatchQueue = new ConcurrentLinkedQueue<>();

    public static final Lock lock = new ReentrantLock(false);
    public static final Condition queueEmptyCondition = lock.newCondition();

    private static AtomicInteger queueSize = new AtomicInteger(0);

    public static boolean isFinished = true;

    /**
     * Pushes request to offline cache DB queue.
     *
     * @param offlineRequest
     */
    public static void pushToOfflineDBQueue(OfflineRequest offlineRequest) {
        pushToOfflineQueue(Constants.DB_QUEUE_TYPE_OFFLINE, offlineRequest);
    }

    /**
     * Pushes request to periodic dispatch DB  queue.
     *
     * @param offlineRequest
     */
    public static void pushToPeriodicDispatchDBQueue(OfflineRequest offlineRequest) {
        pushToOfflineQueue(Constants.DB_QUEUE_TYPE_PERIODIC, offlineRequest);
    }

    private static void pushToOfflineQueue(int queueType, OfflineRequest offlineRequest) {
        if (queueSize.get() > Constants.REQUEST_QUEUE_CAPACITY) {
            CurioLogger.e(TAG, "Curio DB request queue is full. Will not add this and upcoming requests until queue size is below capacity.");
        }

        switch (queueType) {
            case Constants.DB_QUEUE_TYPE_OFFLINE:
                offlineQueue.add(offlineRequest);
                break;
            case Constants.DB_QUEUE_TYPE_PERIODIC:
                periodicDispatchQueue.add(offlineRequest);
                break;
        }

        int size = queueSize.incrementAndGet();
        CurioLogger.d(TAG, "DB request queue size is: " + size);

        try {
            lock.lock();
            queueEmptyCondition.signal();
        } finally {
            lock.unlock();
        }
    }

    public void run() {
        Log.d(TAG, "Starting DB request thread...");

        while (!isFinished) {
            processOfflineQueue();
            processPeriodicDispatchQueue();

            CurioLogger.d(TAG, "DB request queue size is: " + queueSize.get());

            if (queueSize.get() == 0 && !isFinished) {
                try {
                    lock.lock();
                    CurioLogger.d(TAG, "Queue is empty thread will sleep...");
                    queueEmptyCondition.await();
                    CurioLogger.d(TAG, "Queue is NOT empty thread awakens...");
                } catch (InterruptedException e) {
                    CurioLogger.e(TAG, e.getMessage());
                } finally {
                    lock.unlock();
                }
            }
        }

        CurioLogger.d(TAG, "Stopping DBRequestProcessor thread.");
    }

    /**
     * Stores offline request at DB.
     *
     * @param offlineRequest
     */
    private void storeOfflineRequest(OfflineRequest offlineRequest) {
        /**
         * Before storing any offline requests, move all periodic dispatch requests to offline request table
         * to guarantee ordered dispatch of all requests.
         */
        CurioDBHelper.getInstance().moveAllExistingPeriodicDispatchDataToOfflineTable();

        if (!CurioDBHelper.getInstance().persistOfflineRequestForCaching(offlineRequest)) {
            CurioLogger.e(TAG, "Could not persist offline request.");
        }
    }

    /**
     * Stores periodic dispatch request at DB.
     *
     * @param offlineRequest
     */
    private void storePeriodicDispatchRequest(OfflineRequest offlineRequest) {
        if (!CurioDBHelper.getInstance().persistOfflineRequestForPeriodicDispatch(offlineRequest)) {
            CurioLogger.e(TAG, "Could not persist periodic dispatch request.");
        }
    }

    /**
     * Processes offline queue.
     */
    private void processOfflineQueue() {
        OfflineRequest request = offlineQueue.poll();

        if (request != null) {
            storeOfflineRequest(request);
            queueSize.decrementAndGet();
        }
    }

    /**
     * Processes offline queue.
     */
    private void processPeriodicDispatchQueue() {
        OfflineRequest request = periodicDispatchQueue.poll();

        if (request != null) {
            storePeriodicDispatchRequest(request);
            queueSize.decrementAndGet();
        }
    }
}
