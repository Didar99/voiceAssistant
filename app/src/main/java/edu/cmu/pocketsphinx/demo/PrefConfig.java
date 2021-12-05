package edu.cmu.pocketsphinx.demo;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefConfig {
    private static final String MY_PREFERENCE_NAME = "com.iot.smart_home";
    private static final String PREF_HTTP_KEY = "pref_http_key";
    private static final String PREF_IP_KEY = "pref_ip_key";
    private static final String PREF_PORT_KEY = "pref_port_key";
    private static final String PREF_ZERO_PARAM_KEY = "pref_zero_param_key";
    private static final String PREF_FIRST_PARAM_KEY = "pref_first_param_key";
    private static final String PREF_SECOND_PARAM_KEY = "pref_second_param_key";


    // for HTTP or HTTPS
    public static void saveHttpPref(Context context, String http) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(PREF_HTTP_KEY, http);
        editor.apply();
    }
    public static String loadHttpPref(Context context) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return pref.getString(PREF_HTTP_KEY, "http");
    }

    // for IP address
    public static void saveIpPref(Context context, String ip) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(PREF_IP_KEY, ip);
        editor.apply();
    }
    public static String loadIpPref(Context context) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return pref.getString(PREF_IP_KEY, "95.85.112.74");
    }

    // for PORT
    public static void savePORTPref(Context context, String port) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(PREF_PORT_KEY, port);
        editor.apply();
    }
    public static String loadPORTPref(Context context) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return pref.getString(PREF_PORT_KEY, "35123");
    }

    // for Zero Parameter (null)
    public static void saveZeroParam(Context context, String zeroParam) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(PREF_ZERO_PARAM_KEY, zeroParam);
        editor.apply();
    }
    public static String loadZeroParam(Context context) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return pref.getString(PREF_ZERO_PARAM_KEY, "");
    }

    // for First Parameter (esp)
    public static void saveFirstParam(Context context, String firstParam) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(PREF_FIRST_PARAM_KEY, firstParam);
        editor.apply();
    }
    public static String loadFirstParam(Context context) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return pref.getString(PREF_FIRST_PARAM_KEY, "esp");
    }

    // for Second Parameter (JsonToArg)
    public static void saveSecondParam(Context context, String secondParam) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(PREF_SECOND_PARAM_KEY, secondParam);
        editor.apply();
    }
    public static String loadSecondParam(Context context) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return pref.getString(PREF_SECOND_PARAM_KEY, "JsonToArg");
    }


}