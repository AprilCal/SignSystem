package com.example.aprilcal.signsystem.Network;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.aprilcal.signsystem.Activity.MainActivity;
import com.example.aprilcal.signsystem.Activity.SignUpActivity;
import com.example.aprilcal.signsystem.vo.Student;
import com.example.aprilcal.signsystem.vo.Teacher;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by AprilCal on 2018/4/28.
 */

public class NetworkHelper {

    //TODO modify, configuration file;
    private static String loginUrl = "http://192.168.1.108:8080/SignSystemServer/LoginServlet";
    private static String studentSignUpUrl = "http://192.168.1.108:8080/SignSystemServer/StudentSignUpServlet";
    private static String teacherSignUpUrl = "http://192.168.1.108:8080/SignSystemServer/TeacherSignUpServlet";
    private static String teacherSynchronizeUrl = "http://192.168.1.108:8080/SignSystemServer/TeacherSignUpServlet";

    public static void TeacherSignUpActivity(final Context context, final Handler handler, final Teacher teacher){
        //TODO modify address;
        String tag = "TeacherSignUp";
        RequestQueue requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        requestQueue.cancelAll(tag);
        final StringRequest request = new StringRequest(Request.Method.POST, teacherSignUpUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = (JSONObject) new JSONObject(response).get("params");
                            String result = jsonObject.getString("result");
                            // result = {"success"|"failed"}
                            // sign up success
                            if (result.equals("success")) {
                                Message message = Message.obtain();
                                message.what = SignUpActivity.SIGN_UP_SUCCESS;
                                handler.sendMessage(message);
                            }
                            // sign up failed;
                            else {
                                Message message = Message.obtain();
                                message.what = SignUpActivity.SIGN_UP_FAILED;
                                handler.sendMessage(message);
                            }
                        } catch (JSONException e) {
                            //做自己的请求异常操作，如Toast提示（“无网络连接”等）
                            Log.e("TAG", e.getMessage(), e);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", "error occured");
                error.printStackTrace();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("school", teacher.getSchool());
                params.put("tel", teacher.getTel());
                params.put("mail", teacher.getMail());
                params.put("studentName", teacher.getTeacherName());
                params.put("password", teacher.getPassword());
                return params;
            }
        };

        //设置Tag标签
        request.setTag(tag);

        //将请求添加到队列中
        requestQueue.add(request);
    }

    public static void StudentSignUpActivity(final Context context, final Handler handler, final Student student){
        //TODO modify address;
        String tag = "SignUp";
        RequestQueue requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        requestQueue.cancelAll(tag);
        final StringRequest request = new StringRequest(Request.Method.POST, studentSignUpUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = (JSONObject) new JSONObject(response).get("params");
                            String result = jsonObject.getString("result");
                            // result = {"success"|"failed"}
                            // sign up success
                            if (result.equals("success")) {
                                Message message = Message.obtain();
                                message.what = SignUpActivity.SIGN_UP_SUCCESS;
                                handler.sendMessage(message);
                            }
                            // sign up failed;
                            else {
                                Message message = Message.obtain();
                                message.what = SignUpActivity.SIGN_UP_FAILED;
                                handler.sendMessage(message);
                            }
                        } catch (JSONException e) {
                            Log.e("TAG", e.getMessage(), e);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Message message = Message.obtain();
                message.what = MainActivity.NETWORK_UNREACHABLE;
                handler.sendMessage(message);
                error.printStackTrace();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("schoolID", student.getSchoolID());
                params.put("school", student.getSchool());
                params.put("tel", student.getTel());
                params.put("mail", student.getMail());
                params.put("studentName", student.getStudentName());
                params.put("password", student.getPassword());
                params.put("mac",student.getMac());
                return params;
            }
        };
        request.setTag(tag);
        requestQueue.add(request);
    }

    public static void StudentLoginRequest(final Context context, final Handler handler, final String accountNumber, final String password, final String mac){
        String tag = "StudentLogin";
        RequestQueue requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        requestQueue.cancelAll(tag);
        final StringRequest request = new StringRequest(Request.Method.POST, loginUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = (JSONObject) new JSONObject(response).get("params");
                            // result = {"success"|"failed"}
                            String result = jsonObject.getString("result");
                            if (result.equals("success")) {
                                Message message = Message.obtain();
                                message.what = MainActivity.STUDENT_LOGIN_SUCCESS;
                                Bundle bundle = new Bundle();
                                bundle.putInt("studentID", Integer.valueOf(jsonObject.getString("studentID")));
                                bundle.putString("schoolID", jsonObject.getString("schoolID"));
                                bundle.putString("studentName", jsonObject.getString("studentName"));
                                bundle.putString("tel", jsonObject.getString("tel"));
                                bundle.putString("mail", jsonObject.getString("mail"));
                                bundle.putString("school", jsonObject.getString("school"));
                                bundle.putString("token", jsonObject.getString("token"));
                                message.setData(bundle);
                                handler.sendMessage(message);
                            }
                            else if(result.equals("mac error")){
                                Message message = Message.obtain();
                                message.what = MainActivity.MAC_ERROR;
                                handler.sendMessage(message);
                            }
                            else {
                                Message message = Message.obtain();
                                message.what = MainActivity.LOGIN_FAILED;
                                Bundle bundle = new Bundle();
                                //bundle.putInt("studentID", Integer.valueOf(jsonObject.getString("studentID")));
                                message.setData(bundle);
                                handler.sendMessage(message);
                            }
                        } catch (JSONException e) {
                            Log.e("TAG", e.getMessage(), e);
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                //Log.e("cause", error.getCause().toString());
                if(error.getCause() == null){
                    Message message = Message.obtain();
                    message.what = MainActivity.CONNECTION_REFUSED;
                    handler.sendMessage(message);
                    return;
                }
                if(error.getCause().toString().equals("java.net.ConnectException: Connection refused")){
                    Message message = Message.obtain();
                    message.what = MainActivity.CONNECTION_REFUSED;
                    handler.sendMessage(message);
                }
                else if(error.getCause().toString().equals("java.net.ConnectException: Network is unreachable")){
                    Message message = Message.obtain();
                    message.what = MainActivity.NETWORK_UNREACHABLE;
                    handler.sendMessage(message);
                }
                else{
                    Message message = Message.obtain();
                    message.what = MainActivity.UNKONWN_ERROR;
                    handler.sendMessage(message);
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("accountNumber", accountNumber);
                params.put("password", password);
                params.put("mac",mac);
                params.put("identity","student");
                return params;
            }
        };
        request.setTag(tag);
        requestQueue.add(request);
    }

    public static void synchronizeCourse(final Context context, final Handler handler, final String token ){

    }

    public static void TeacherLoginRequest(final Context context, final Handler handler, final String accountNumber, final String password){
        String tag = "Login";
        RequestQueue requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        requestQueue.cancelAll(tag);
        final StringRequest request = new StringRequest(Request.Method.POST, loginUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = (JSONObject) new JSONObject(response).get("params");
                            // result = {"success"|"failed"}
                            String result = jsonObject.getString("result");
                            if (result.equals("success")) {
                                Message message = Message.obtain();
                                message.what = MainActivity.TEACHER_LOGIN_SUCCESS;
                                Bundle bundle = new Bundle();
                                bundle.putInt("teacherID", Integer.valueOf(jsonObject.getString("teacherID")));
                                bundle.putString("teacherName", jsonObject.getString("teacherName"));
                                bundle.putString("tel", jsonObject.getString("tel"));
                                bundle.putString("mail", jsonObject.getString("mail"));
                                bundle.putString("school", jsonObject.getString("school"));
                                bundle.putString("token", jsonObject.getString("token"));
                                message.setData(bundle);
                                handler.sendMessage(message);
                            }
                            else {
                                Message message = Message.obtain();
                                message.what = MainActivity.LOGIN_FAILED;
                                Bundle bundle = new Bundle();
                                bundle.putInt("studentID", Integer.valueOf(jsonObject.getString("studentID")));
                                message.setData(bundle);
                                handler.sendMessage(message);
                            }
                        } catch (JSONException e) {
                            Log.e("TAG", e.getMessage(), e);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Log.e("cause", error.getCause().toString());
                if(error.getCause() == null){
                    Message message = Message.obtain();
                    message.what = MainActivity.CONNECTION_REFUSED;
                    handler.sendMessage(message);
                    return;
                }
                if(error.getCause().toString().equals("java.net.ConnectException: Connection refused")){
                    Message message = Message.obtain();
                    message.what = MainActivity.CONNECTION_REFUSED;
                    handler.sendMessage(message);
                }
                else if(error.getCause().toString().equals("java.net.ConnectException: Network is unreachable")){
                    Message message = Message.obtain();
                    message.what = MainActivity.NETWORK_UNREACHABLE;
                    handler.sendMessage(message);
                }
                else{
                    Message message = Message.obtain();
                    message.what = MainActivity.UNKONWN_ERROR;
                    handler.sendMessage(message);
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("accountNumber", accountNumber);
                params.put("password", password);
                params.put("identity","teacher");
                return params;
            }
        };
        request.setTag(tag);
        requestQueue.add(request);
    }
}
