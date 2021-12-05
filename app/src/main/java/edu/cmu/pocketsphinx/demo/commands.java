package edu.cmu.pocketsphinx.demo;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class commands {
    static String ip_address, strPort, strZeroParam, strFirstParam, strSecondParam, strHttp;

    static void light_on(Context context) {
        // IP-Address load from pref_config
        ip_address = PrefConfig.loadIpPref(context);
        // load data from PrefConfig
        strPort = PrefConfig.loadPORTPref(context);
        strZeroParam = PrefConfig.loadZeroParam(context);
        strFirstParam = PrefConfig.loadFirstParam(context);
        strSecondParam = PrefConfig.loadSecondParam(context);
        strHttp = PrefConfig.loadHttpPref(context);
        String str = strHttp + "://" + ip_address + strPort + strZeroParam + strFirstParam + strSecondParam;
        System.out.println(str);
        Log.e("tv_power onResponse => ", str);

        Thread thread = new Thread(()
                -> {
            try {
                URL url = new URL(strHttp + "://" + ip_address + strPort + strZeroParam + strFirstParam + strSecondParam);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                conn.setRequestProperty("Accept", "application/json");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                JSONObject jsonParam = new JSONObject();
                JSONObject jsonParam2 = new JSONObject();
                JSONObject jsonParam3 = new JSONObject();
                JSONObject json = new JSONObject();
                JSONArray pin_array = new JSONArray();
                jsonParam.put("command", "pin2");
                jsonParam.put("action", "1");
                jsonParam2.put("command", "pin3");
                jsonParam2.put("action", "1");
                jsonParam3.put("command", "pin1");
                jsonParam3.put("action", "0");
                pin_array.put(jsonParam);
                pin_array.put(jsonParam2);
                pin_array.put(jsonParam3);
                json.put("command", "three_mode_switch");
                json.put("pins", pin_array);
                Log.i("JSON", json.toString());
                DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                os.writeBytes(json.toString());
                os.flush();
                os.close();
                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    conn.disconnect();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();
        Log.e("RESPONSE => ", "LIGHT ON");

    }    // Send json data to ESP 8266 module  -> in Browser you can type like [ip_address/control/pin1=1 or pin2=1 or pin3=1 <-> pin1=0 or pin2=0 or pin3=0]
    static void light_off(Context context) {
        ip_address = PrefConfig.loadIpPref(context);
        strPort = PrefConfig.loadPORTPref(context);
        strZeroParam = PrefConfig.loadZeroParam(context);
        strFirstParam = PrefConfig.loadFirstParam(context);
        strSecondParam = PrefConfig.loadSecondParam(context);
        strHttp = PrefConfig.loadHttpPref(context);
        Thread thread = new Thread(() -> {
            try {
                URL url = new URL(strHttp + "://" + ip_address + strPort + strZeroParam + strFirstParam + strSecondParam);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                conn.setRequestProperty("Accept", "application/json");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                JSONObject jsonParam = new JSONObject();
                JSONObject jsonParam2 = new JSONObject();
                JSONObject jsonParam3 = new JSONObject();
                JSONObject json = new JSONObject();
                JSONArray pin_array = new JSONArray();
                jsonParam.put("command", "pin2");
                jsonParam.put("action", "0");
                jsonParam2.put("command", "pin3");
                jsonParam2.put("action", "0");
                jsonParam3.put("command", "pin1");
                jsonParam3.put("action", "0");
                pin_array.put(jsonParam);
                pin_array.put(jsonParam2);
                pin_array.put(jsonParam3);
                json.put("command", "three_mode_switch");
                json.put("pins", pin_array);
                Log.i("JSON", json.toString());
                DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                os.writeBytes(json.toString());
                os.flush();
                os.close();
                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    conn.disconnect();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();
        Log.e("RESPONSE => ", "LIGHT OFF");
    }
    static void curtain_up(Context context) {
        ip_address = PrefConfig.loadIpPref(context);
        strPort = PrefConfig.loadPORTPref(context);
        strZeroParam = PrefConfig.loadZeroParam(context);
        strFirstParam = PrefConfig.loadFirstParam(context);
        strSecondParam = PrefConfig.loadSecondParam(context);
        strHttp = PrefConfig.loadHttpPref(context);
        Thread thread = new Thread(() -> {
            try {
                URL url = new URL(strHttp + "://" + ip_address + strPort + strZeroParam + strFirstParam + strSecondParam);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                conn.setRequestProperty("Accept","application/json");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                JSONObject jsonParam = new JSONObject();
                jsonParam.put("command", "curtain");
                jsonParam.put("state", "1");
                jsonParam.put("action", "");
                Log.i("JSON", jsonParam.toString());
                DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                os.writeBytes(jsonParam.toString());
                os.flush();
                os.close();
                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    conn.disconnect();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();
        Log.e("RESPONSE => ", "CURTAIN UP");
    }
    static void curtain_down(Context context) {
        ip_address = PrefConfig.loadIpPref(context);
        strPort = PrefConfig.loadPORTPref(context);
        strZeroParam = PrefConfig.loadZeroParam(context);
        strFirstParam = PrefConfig.loadFirstParam(context);
        strSecondParam = PrefConfig.loadSecondParam(context);
        strHttp = PrefConfig.loadHttpPref(context);
        Thread thread = new Thread(() -> {
            try {
                URL url = new URL(strHttp + "://" + ip_address + strPort + strZeroParam + strFirstParam + strSecondParam);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                conn.setRequestProperty("Accept","application/json");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                JSONObject jsonParam = new JSONObject();
                jsonParam.put("command", "curtain");
                jsonParam.put("state", "2");
                jsonParam.put("action", "");
                Log.i("JSON", jsonParam.toString());
                DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                os.writeBytes(jsonParam.toString());
                os.flush();
                os.close();
                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    conn.disconnect();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();
        Log.e("RESPONSE => ", "CURTAIN DOWN");
    }
//    static void curtain_stop(Context context) {
//        ip_address = PrefConfig.loadIpPref(context);
//        strPort = PrefConfig.loadPORTPref(context);
//        strZeroParam = PrefConfig.loadZeroParam(context);
//        strFirstParam = PrefConfig.loadFirstParam(context);
//        strSecondParam = PrefConfig.loadSecondParam(context);
//        strHttp = PrefConfig.loadHttpPref(context);
//        Thread thread = new Thread(() -> {
//            try {
//                URL url = new URL(strHttp + "://" + ip_address + strPort + strZeroParam + strFirstParam + strSecondParam);
//                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//                conn.setRequestMethod("POST");
//                conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
//                conn.setRequestProperty("Accept","application/json");
//                conn.setDoOutput(true);
//                conn.setDoInput(true);
//                JSONObject jsonParam = new JSONObject();
//                jsonParam.put("command", "curtain");
//                jsonParam.put("state", "2");
//                jsonParam.put("action", "");
//                Log.i("JSON", jsonParam.toString());
//                DataOutputStream os = new DataOutputStream(conn.getOutputStream());
//                os.writeBytes(jsonParam.toString());
//                os.flush();
//                os.close();
//                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
//                    conn.disconnect();
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
//        thread.start();
//        Log.e("RESPONSE => ", "CURTAIN STOP");
//    }

    static void air_power_on(Context context) {
        ip_address = PrefConfig.loadIpPref(context);
        strPort = PrefConfig.loadPORTPref(context);
        strZeroParam = PrefConfig.loadZeroParam(context);
        strFirstParam = PrefConfig.loadFirstParam(context);
        strSecondParam = PrefConfig.loadSecondParam(context);
        strHttp = PrefConfig.loadHttpPref(context);
        Thread thread = new Thread(() -> {
            try {
                URL url = new URL(strHttp + "://" + ip_address + strPort + strZeroParam + strFirstParam + strSecondParam);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                conn.setRequestProperty("Accept", "application/json");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                JSONObject jsonParam = new JSONObject();
                JSONObject json = new JSONObject();
                JSONArray pin_array = new JSONArray();
                jsonParam.put("command", "command");
                jsonParam.put("action", "poweron");
                pin_array.put(jsonParam);
                json.put("command", "conditioner");
                json.put("pins", pin_array);
                Log.i("JSON", json.toString());
                DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                os.writeBytes(json.toString());
                os.flush();
                os.close();
                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    conn.disconnect();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();
        Log.e("RESPONSE => ", "AIR ON");

    }
    static void air_power_off(Context context) {
        ip_address = PrefConfig.loadIpPref(context);
        strPort = PrefConfig.loadPORTPref(context);
        strZeroParam = PrefConfig.loadZeroParam(context);
        strFirstParam = PrefConfig.loadFirstParam(context);
        strSecondParam = PrefConfig.loadSecondParam(context);
        strHttp = PrefConfig.loadHttpPref(context);
        Thread thread = new Thread(() -> {
            try {
                URL url = new URL(strHttp + "://" + ip_address + strPort + strZeroParam + strFirstParam + strSecondParam);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                conn.setRequestProperty("Accept", "application/json");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                JSONObject jsonParam = new JSONObject();
                JSONObject json = new JSONObject();
                JSONArray pin_array = new JSONArray();
                jsonParam.put("command", "command");
                jsonParam.put("action", "poweroff");
                pin_array.put(jsonParam);
                json.put("command", "conditioner");
                json.put("pins", pin_array);
                Log.i("JSON", json.toString());
                DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                os.writeBytes(json.toString());
                os.flush();
                os.close();
                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    conn.disconnect();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();
        Log.e("RESPONSE => ", "AIR OFF");
    }
    static void fingerPrintAfterLock(Context context) {
        ip_address = PrefConfig.loadIpPref(context);
        strPort = PrefConfig.loadPORTPref(context);
        strZeroParam = PrefConfig.loadZeroParam(context);
        strFirstParam = PrefConfig.loadFirstParam(context);
        strSecondParam = PrefConfig.loadSecondParam(context);
        strHttp = PrefConfig.loadHttpPref(context);
        Thread thread = new Thread(() -> {
            try {
                URL url = new URL(strHttp + "://" + ip_address + strPort + strZeroParam + strFirstParam + strSecondParam);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                conn.setRequestProperty("Accept", "application/json");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                JSONObject jsonParam = new JSONObject();
                jsonParam.put("command", "unlock_door");
                jsonParam.put("state", "2");
                jsonParam.put("action", "");
                Log.i("JSON", jsonParam.toString());
                DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                os.writeBytes(jsonParam.toString());
                os.flush();
                os.close();
                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    conn.disconnect();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();
        Log.e("RESPONSE => ", "DOOR OPEN");
    }

    static void MainLampOn(Context context) {
        // IP-Address load from pref_config
        ip_address = PrefConfig.loadIpPref(context);
        // load data from PrefConfig
        strPort = PrefConfig.loadPORTPref(context);
        strZeroParam = PrefConfig.loadZeroParam(context);
        strFirstParam = PrefConfig.loadFirstParam(context);
        strSecondParam = PrefConfig.loadSecondParam(context);
        strHttp = PrefConfig.loadHttpPref(context);
        Thread thread = new Thread(() -> {
            try {
                URL url = new URL(strHttp + "://" + ip_address + strPort + strZeroParam + strFirstParam + strSecondParam);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                conn.setRequestProperty("Accept","application/json");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                JSONObject jsonParam = new JSONObject();
                jsonParam.put("command", "kitchen_light123");
                jsonParam.put("state", "1");
                jsonParam.put("action", "");
                Log.i("JSON", jsonParam.toString());
                DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                os.writeBytes(jsonParam.toString());
                os.flush();
                os.close();
                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    conn.disconnect();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }

    static void MainLampOff(Context context) {
        // IP-Address load from pref_config
        ip_address = PrefConfig.loadIpPref(context);
        // load data from PrefConfig
        strPort = PrefConfig.loadPORTPref(context);
        strZeroParam = PrefConfig.loadZeroParam(context);
        strFirstParam = PrefConfig.loadFirstParam(context);
        strSecondParam = PrefConfig.loadSecondParam(context);
        strHttp = PrefConfig.loadHttpPref(context);
        Thread thread = new Thread(() -> {
            try {
                URL url = new URL(strHttp + "://" + ip_address + strPort + strZeroParam + strFirstParam + strSecondParam);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                conn.setRequestProperty("Accept","application/json");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                JSONObject jsonParam = new JSONObject();
                jsonParam.put("command", "kitchen_light123");
                jsonParam.put("state", "0");
                jsonParam.put("action", "");
                Log.i("JSON", jsonParam.toString());
                DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                os.writeBytes(jsonParam.toString());
                os.flush();
                os.close();
                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    conn.disconnect();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }

    public static void acOn(Context context) {
        ip_address = PrefConfig.loadIpPref(context);

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "http://192.168.1.177/control/?command=on";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    // Display the first 500 characters of the response string.
                    Log.e("RESPONSE => ", "ENGINE START => ");
                }, error -> Log.e("RESPONSE => ", "ENGINE START ERROR"));
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
    public static void acOff(Context context) {
        ip_address = PrefConfig.loadIpPref(context);
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "http://192.168.1.177/control/?command=off";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> Log.e("RESPONSE => ", "ENGINE START => "), error -> Log.e("RESPONSE => ", "ENGINE START ERROR"));
        queue.add(stringRequest);
    }
}
