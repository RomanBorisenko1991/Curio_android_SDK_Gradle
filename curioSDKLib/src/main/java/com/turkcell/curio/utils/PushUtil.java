/*
 * Copyright (C) 2014 Turkcell
 *
 * Created by Can Ciloglu on 11 Oca 2015
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

import java.io.IOException;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.turkcell.curio.CurioClient;

/**
 * 
 * @author Can Ciloglu
 */
public class PushUtil {

	private static String TAG = PushUtil.class.getSimpleName();

	/**
	 * Check the device to make sure it has the Google Play Services APK.
	 * 
	 */
	private static boolean checkPlayServices(Context context) {
		int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(context);
		if (resultCode != ConnectionResult.SUCCESS) {
			CurioLogger.e(TAG, "This device does not support Google Play Services.");
			return false;
		}
		return true;
	}

	public static void checkForGCMRegistration(final Context context) {
		if (checkPlayServices(context)) {
			String gcmRegistrationId = getStoredRegistrationId(context);
			if (gcmRegistrationId == null) {
				Thread gcmRegisterThread = new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							String registrationId = GoogleCloudMessaging.getInstance(context).register(CurioClient.getInstance().getStaticFeatureSet().getGcmSenderId());
							CurioLogger.d(TAG, "GCM Registration Id acquired: " + registrationId);
							storeRegistrationId(context, registrationId);
							CurioClient.getInstance().sendRegistrationId(context, registrationId);
						} catch (IOException e) {
							CurioLogger.i(TAG, "An error occured while registering/storing GCM registration id.", e);
						}
					}
				});
				gcmRegisterThread.start();
			}else {
				CurioClient.getInstance().sendRegistrationId(context, gcmRegistrationId);
			}
		}
	}

	/**
	 * Saves GCM registration id to shared prefs.
	 * 
	 * @param context
	 */
	private static void storeRegistrationId(Context context, String gcmRegistrationId) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.SHARED_PREF_NAME_GCM, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putString(Constants.SHARED_PREF_KEY_GCM_REGID, gcmRegistrationId);
		editor.putInt(Constants.SHARED_PREF_KEY_APP_VERSION, getAppVersion(context));
		editor.commit();
	}

	/**
	 * Gets stored GCM registration id from shared prefs.
	 * 
	 * @param context
	 */
	public static String getStoredRegistrationId(Context context) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.SHARED_PREF_NAME_GCM, Context.MODE_PRIVATE);
		String registrationId = sharedPreferences.getString(Constants.SHARED_PREF_KEY_GCM_REGID, null);
		int currentAppVersion = sharedPreferences.getInt(Constants.SHARED_PREF_KEY_APP_VERSION, 0);
		if (getAppVersion(context) != currentAppVersion){
			return null;
		}
		return registrationId;
	}
	

	/**
	 * Deletes stored GCM registration id from shared prefs.
	 * 
	 * @param context
	 */
	public static void deleteRegistrationId(Context context) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.SHARED_PREF_NAME_GCM, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.remove(Constants.SHARED_PREF_KEY_GCM_REGID);
		editor.remove(Constants.SHARED_PREF_KEY_APP_VERSION);
		editor.commit();
		CurioLogger.d(TAG, "Registration Id removed from shared prefs.");
	}

	private static int getAppVersion(Context context) {
		try {
			PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
			return packageInfo.versionCode;
		} catch (NameNotFoundException e) {
			// should never happen
			throw new RuntimeException("Could not get package name: " + e);
		}
	}
}
