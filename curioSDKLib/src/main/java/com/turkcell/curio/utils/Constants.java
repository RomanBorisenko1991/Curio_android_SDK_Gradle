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
package com.turkcell.curio.utils;


/**
 * Holder class for constants.
 * 
 * @author Can Ciloglu
 *
 */
public class Constants {
	public static final String JSON_NODE_AUTH_TOKEN = "authToken";
	public static final String JSON_NODE_HIT_CODE = "hitCode";
	public static final String JSON_NODE_DATA = "data";
	public static final String JSON_NODE_SESSION_CODE = "sessionCode";
	public static final String JSON_NODE_TYPE = "type";
	public static final String JSON_NODE_EVENT_CODE = "eventCode";

	public static final String JSON_NODE_TIMESTAMP = "timestamp";
	public static final String HTTP_PARAM_HIT_CODE = "hitCode";
	public static final String HTTP_PARAM_EVENT_CODE = "eventCode";
	public static final String HTTP_PARAM_TRACKING_CODE = "trackingCode";
	public static final String HTTP_PARAM_VISITOR_CODE = "visitorCode";
	public static final String HTTP_PARAM_PAGE_TITLE = "pageTitle";
	public static final String HTTP_PARAM_PATH = "path";
	public static final String HTTP_PARAM_SCREEN_WIDTH = "screenWidth";
	public static final String HTTP_PARAM_SCREEN_HEIGHT = "screenHeight";
	public static final String HTTP_PARAM_ACTIVITY_WIDTH = "activityWidth";
	public static final String HTTP_PARAM_ACTIVITY_HEIGHT = "activityHeight";
	public static final String HTTP_PARAM_OS_TYPE = "osType";
	public static final String HTTP_PARAM_OS_VERSION = "osVer";
	public static final String HTTP_PARAM_CURIO_SDK_VERSION = "curioSdkVer";
	public static final String HTTP_PARAM_APP_VERSION = "appVer";
	public static final String HTTP_PARAM_BRAND = "brand";
	public static final String HTTP_PARAM_MODEL = "model";
	public static final String HTTP_PARAM_SESSION_CODE = "sessionCode";
	public static final String HTTP_PARAM_EVENT_KEY = "eventKey";
	public static final String HTTP_PARAM_EVENT_VALUE = "eventValue";
	public static final String HTTP_PARAM_SIM_OPERATOR = "simOperator";
	public static final String HTTP_PARAM_SIM_COUNTRY_ISO = "simOpCountry";
	public static final String HTTP_PARAM_NETWORK_OPERATOR_NAME = "networkOpName";
	public static final String HTTP_PARAM_INTERNET_CONN_TYPE = "connType";
	public static final String HTTP_PARAM_LANG = "lang";
	public static final String HTTP_PARAM_API_KEY = "apiKey";
	public static final String HTTP_PARAM_SESSION_TIMEOUT = "sessionTimeout";
	public static final String HTTP_PARAM_JSON_DATA = "data";
	public static final String HTTP_PARAM_PUSH_TOKEN = "pushToken";
	public static final String HTTP_PARAM_PUSH_ID = "pushId";
	public static final String HTTP_PARAM_CUSTOM_ID = "customId";
	public static final String HTTP_PARAM_BT_STATE = "bluetooth";
	public static final String HTTP_PARAM_EVENT_DURATION = "eventDuration";
	public static final String HTTP_PARAM_USER_TAGS = "userTags";
	public static final String HTTP_PARAM_AVAILABLE_STORAGE = "storage";
	public static final String HTTP_PARAM_INSTALLED_APPS = "installedApps";
	public static final String HTTP_PARAM_BATTERY_LEVEL = "batteryLevel";

	public static final String CONFIG_PARAM_SESSION_TIMEOUT = "session_timeout";
	public static final String CONFIG_PARAM_API_KEY = "api_key";
	public static final String CONFIG_PARAM_GCM_SENDER_ID = "gcm_senderId";
	public static final String CONFIG_PARAM_PERIODIC_DISPATCH = "periodic_dispatch_enabled";
	public static final String CONFIG_PARAM_TRACKING_CODE = "tracking_code";

	public static final String CONFIG_PARAM_SERVER_URL = "server_url";
	public static final String CONFIG_PARAM_DISPATCH_PERIOD = "dispatch_period";
	public static final String CONFIG_PARAM_MAX_CACHED_ACTIVITY_COUNT = "max_cached_activity_count";

	public static final String CONFIG_PARAM_LOGGING_ENABLED = "logging_enabled";

	public static final String CONFIG_PARAM_AUTO_PUSH_REGISTRATION = "auto_push_registration";
	public static final int CONFIG_PARAM_DEFAULT_VALUE_SESSION_TIMEOUT_IN_MINUTES = 30;
	public static final int CONFIG_PARAM_DEFAULT_VALUE_DISPATCH_PERIOD_IN_MINUTES = 5;

	public static final int CONFIG_PARAM_DEFAULT_VALUE_MAX_CACHED_ACTIVITY_COUNT = 1000;
	public static final int CONFIG_PARAM_MAX_VALUE_MAX_CACHED_ACTIVITY_COUNT = 4000;
	public static final String ERROR = "error";
	public static final String ERROR_CODE = "errorCode";
	public static final String ERROR_MESSAGE = "errorMessage";
	public static final String URL = "url";
	public static final String PAIRS = "pairs";
	public static final String CALLBACK = "callback";

	public static final String PRIORITY = "priority";
	public static final String HTTP = "http://";

	public static final String HTTPS = "https://";
	public static final int ERROR_CODE_NO_NETWORK = -100;
	public static final int ERROR_CODE_AUTO_PUSH_REGISTERATION_NOT_ENABLED = -101;
	public static final String BACKSLASH = "/";
	public static final String API = "api";
	public static final String SERVER_URL_SUFFIX_SESSION_START = "/visit/create";
	public static final String SERVER_URL_SUFFIX_SESSION_END = "/visit/end";

	public static final String SERVER_URL_SUFFIX_SCREEN_START = "/hit/create";
	public static final String SERVER_URL_SUFFIX_SCREEN_END = "/hit/end";
	public static final String SERVER_URL_SUFFIX_SEND_EVENT = "/event/create";
	public static final String SERVER_URL_SUFFIX_EVENT_END = "/event/end";
	public static final String SERVER_URL_SUFFIX_PERIODIC_BATCH = "/batch/create";
	public static final String SERVER_URL_SUFFIX_OFFLINE_CACHE = "/offline/create";

	public static final String SERVER_URL_SUFFIX_PUSH_DATA = "/visitor/setPushData";
	public static final String SERVER_URL_SUFFIX_UNREGISTER = "/visitor/unregister";
	public static final String SERVER_URL_SUFFIX_SET_USER_TAG = "/visitor/setUserTag";
	public static final String SERVER_URL_SUFFIX_GET_USER_TAGS = "/visitor/getVisitorProfileTags";

	public static final String UTF8_ENCODING = "utf-8";

	public static final int NOT_IN_PROCESS = 0;
	public static final String NOT_IN_PROCESS_STR = "0";
	public static final int IN_PROCESS = 1;

	public static final String IN_PROCESS_STR = "1";
	public static final String OS_NAME_STR = "Android";
	public static final String CONNECTION_TYPE_STR_WIFI = "wifi";
	public static final String CONNECTION_TYPE_STR_MOBILE = "mobile";

	public static final String CONNECTION_TYPE_STR_OTHER = "other";
	public static final String THREAD_NAME_CURIO_REQ_PROC = "Curio Request Processor";
	public static final String THREAD_NAME_DB_REQ_PROC = "DB Request Processor";
	public static final int REQUEST_QUEUE_CAPACITY = 200;
	public static final int GINGERBREAD_2_3_3_SDK_INT = 10;

	public static final int HONEYCOMB_SDK_INT = 11;
	public static final int HONEYCOMB_3_2_SDK_INT = 13;

	public static final int JELLYBEAN_4_2_SDK_INT = 17;
	public static final int JELLY_BEAN_MR2_4_3_SDK_INT = 18;
	//Important, update this value on every version update!!!
	public static final String CURIO_SDK_VER = "1.1.6";
	public static final String SHARED_PREF_NAME_GCM = "gcm";
	public static final String SHARED_PREF_NAME_CURIO = "curio";
	public static final String SHARED_PREF_KEY_GCM_REGID = "gcm_registration_id";
	public static final String SHARED_PREF_KEY_FIRST_TIME_USE = "first_time";
	public static final String SHARED_PREF_KEY_APP_VERSION = "app_version";
	public static final String PAYLOAD_KEY_PUSH_TOKEN = "pId";
	public static final String INTENT_PARAM_BATTERY_LEVEL = "level";
	public static final String BT_STATUS_ON = "on";
	public static final String BT_STATUS_OFF = "off";
	public static final String BT_STATUS_NO_PERMISSON = "no permission";

	public static final int MAX_UNAUTH_TRY_COUNT = 5;
	public static final long PARAM_LOAD_WAIT_START_SESSION = 100;
	public static final long PARAM_LOAD_WAIT_START_SCREEN = 250;
	public static final long PARAM_LOAD_WAIT_END_SCREEN = 500;
	public static final long PARAM_LOAD_WAIT_SEND_EVENT = 500;
	public static final long PARAM_LOAD_WAIT_END_EVENT = 500;

	public static final int DB_QUEUE_TYPE_OFFLINE = 1;
	public static final int DB_QUEUE_TYPE_PERIODIC = 2;

	public static final int REQUEST_TYPE_START_SESSION = 0;
	public static final int REQUEST_TYPE_END_SESSION = 1;
	public static final int REQUEST_TYPE_SCREEN_START = 2;
	public static final int REQUEST_TYPE_SCREEN_END = 3;
	public static final int REQUEST_TYPE_SEND_EVENT = 4;
	public static final int REQUEST_TYPE_END_EVENT = 7;
}
