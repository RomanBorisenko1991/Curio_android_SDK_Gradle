/*
 * Copyright (C) 2014 Turkcell
 * 
 * Created by Can Ciloglu on 16 Haz 2014
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
package com.turkcell.curio.utils;

import com.turkcell.curio.CurioRequestProcessor;
import com.turkcell.curio.INetworkConnectivityChangeListener;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Utility class for data network connection changes and connection types.
 *  
 * @author Can Ciloglu
 *
 */
public class NetworkUtil {

	private static NetworkUtil instance;
	private boolean isConnected;
	private boolean previousConnectionState;
	private String connectionType;
	private INetworkConnectivityChangeListener listener;

	/**
	 * Should be called first to create instance.
	 */
	public static synchronized NetworkUtil createInstance(Context context, INetworkConnectivityChangeListener listener){
		if(instance == null){
			instance = new NetworkUtil(context, listener);
		}
		return instance;
	}

	/**
	 * Be sure that createInstance is called first.
	 * @return
	 */
	public static synchronized NetworkUtil getInstance(){
		if(instance == null){
			throw new IllegalStateException("NetworkUtil is not created. You should call createInstance method first.");
		}
		return instance;
	}
	
	/**
	 * Private constructor.
	 * 
	 * @param context
	 * @param listener 
	 */
	private NetworkUtil(Context context, INetworkConnectivityChangeListener listener) {
		this.listener = listener;
		
		setConnectivityState(context);
		
		context.getApplicationContext().registerReceiver(new BroadcastReceiver() {
			@Override
			public void onReceive(Context context, Intent intent) {
				setConnectivityState(context);
				
				if(previousConnectionState != isConnected()){
					previousConnectionState = isConnected();
					NetworkUtil.this.listener.networkConnectivityChanged(isConnected());
				}
			}
		}, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
	}

	/**
	 * Sets connectivity state.
	 * 
	 * @param context
	 */
	protected void setConnectivityState(Context context) {
		// Check if there is an active Internet connection.
		ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetwork = connMgr.getActiveNetworkInfo();
		setConnected(activeNetwork != null && activeNetwork.isConnectedOrConnecting());

		// Get connection type
		if (isConnected) {
			if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
				connectionType = Constants.CONNECTION_TYPE_STR_WIFI;
			} else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
				connectionType = Constants.CONNECTION_TYPE_STR_MOBILE;
			} else {
				connectionType = Constants.CONNECTION_TYPE_STR_OTHER;
			}
		}else{
			connectionType = "";
		}
	}

	private void setConnected(boolean isConnected) {
		this.isConnected = isConnected;

		if(isConnected){
			try {
				CurioRequestProcessor.lock.lock();
				CurioRequestProcessor.queueEmptyCondition.signal();
			} finally {
				CurioRequestProcessor.lock.unlock();
			}
		}
	}
	
	public boolean isConnected() {
		return isConnected;
	}
	
	public String getConnectionType() {
		return connectionType;
	}
}
