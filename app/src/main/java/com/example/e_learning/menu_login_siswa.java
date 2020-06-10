package com.example.e_learning;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.e_learning.app.AppConfig;
import com.example.e_learning.app.AppController;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class menu_login_siswa extends AppCompatActivity {


    ProgressDialog pDialog;
    EditText txt_nis, txt_password;
    TextView btn_register;
    Button btn_login,btn_lupa;
    Intent intent;

    int success;
    ConnectivityManager conMgr;
    private static final String TAG = menu_login_siswa.class.getSimpleName();
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    public final static String TAG_username = "username";
    public final static String TAG_level = "level";

    public final static String TAG_ID = "nis";

    Boolean session = false;
    String id,level,nis;
    String tag_json_obj = "json_obj_req";

    public static final String session_status = "session_status";
    SharedPreferences sharedpreferences;
    public static final String my_shared_preferences = "my_shared_preferences";
    //initially it is false
    private boolean loggedIn = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_login_siswa);
        //  sesi();
        conMgr =(ConnectivityManager) getSystemService( Context.CONNECTIVITY_SERVICE);
        {
            if (conMgr.getActiveNetworkInfo() != null
                    && conMgr.getActiveNetworkInfo().isAvailable()
                    && conMgr.getActiveNetworkInfo().isConnected()) {
            } else {
                Toast.makeText(getApplicationContext(), "No Internet Connection",
                        Toast.LENGTH_LONG).show();
            }
        }

        btn_login = (Button) findViewById(R.id.btn_login_adm);
        btn_register = (TextView) findViewById(R.id.btn_register);
        btn_lupa = (Button) findViewById(R.id.btn_lupa_pass);
        txt_nis = (EditText) findViewById(R.id.txt_username);
        txt_password = (EditText) findViewById(R.id.txt_pass);

        sharedpreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
        session = sharedpreferences.getBoolean(session_status, false);
        id = sharedpreferences.getString(TAG_ID, null);
        nis = sharedpreferences.getString(TAG_username, null);
        level = sharedpreferences.getString(TAG_level, null);

        if (session) {
            Intent intent = new Intent(menu_login_siswa.this, menu_utama.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            intent.putExtra(TAG_ID, id);
            intent.putExtra(TAG_username, nis);
            intent.putExtra(TAG_level, level);

//            intent.putExtra(TAG_NAME, nama);
            finish();
            startActivity(intent);
        }

        btn_login.setOnClickListener(new View.OnClickListener()
        {

            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View v) {
                String nis = txt_nis.getText().toString();
                String password = txt_password.getText().toString();

                // mengecek kolom yang kosong
                if (nis.trim().length() > 0 && password.trim().length() > 0) {
                    if (conMgr.getActiveNetworkInfo() != null
                            && conMgr.getActiveNetworkInfo().isAvailable()
                            && conMgr.getActiveNetworkInfo().isConnected()) {
                        checkLogin(nis, password);
                    } else {
                        Toast.makeText(getApplicationContext() ,"No Internet Connection", Toast.LENGTH_LONG).show();
                    }
                } else {
                    // Prompt user to enter credentials
                    Toast.makeText(getApplicationContext() ,"Kolom tidak boleh kosong", Toast.LENGTH_LONG).show();
                }
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                intent = new Intent(menu_login_siswa.this, menu_register.class);
                finish();
                startActivity(intent);
            }
        });
        btn_lupa.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                 intent = new Intent(menu_login_siswa.this, menu_lupa_password.class);
                finish();
                startActivity(intent);
            }
        });
        // db = new SQLiteHandler(getApplicationContext());


    }

    private void checkLogin(final String nis, final String password) {
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Logging in ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.URL_LOGIN_USER, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "Login Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);

                    // Check for error node in json
                    if (success == 1) {
                        String nis = jObj.getString(TAG_username);
                        String id = jObj.getString(TAG_ID);

                        String level= jObj.getString(TAG_level);
                        Log.e("Successfully Login!", jObj.toString());

                        Toast.makeText(getApplicationContext(), jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                        // menyimpan login ke session_dtr
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putBoolean(session_status, true);
                        editor.putString(TAG_ID, id);
                        editor.putString(TAG_username, nis);
                        editor.putString(TAG_level, level);
                        editor.commit();

                        // Memanggil main activity
                        Intent intent = new Intent(menu_login_siswa.this, menu_utama.class);
                        intent.putExtra(TAG_ID, id);
                        intent.putExtra(TAG_level, level);
                        intent.putExtra(TAG_username, nis);
                        finish();
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(),
                                jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();

                hideDialog();

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", nis);
                params.put("password", password);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

//
//    protected void sesi()
//    {
//        super.onResume();
//        //In onresume fetching value from sharedpreference
//        SharedPreferences sharedPreferences = getSharedPreferences(AppConfig.SHARED_PREF_NAME,Context.MODE_PRIVATE);
//
//        //Fetching the boolean value form sharedpreferences
//        loggedIn = sharedPreferences.getBoolean(AppConfig.LOGGEDIN_SHARED_PREF, false);
//        sharedpreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
//        id = sharedpreferences.getString(TAG_ID, null);
//        nama = sharedpreferences.getString(TAG_username, null);
//        //If we will get true
//        if(loggedIn){
//            //We will start the Profile Activity
//            intent.putExtra(TAG_username, nama);
//            intent.putExtra(TAG_ID, id);
//            Intent intent = new Intent(activity_login.this, Menu_utama_Sekali.class);
//            startActivity(intent);
//        }
//    }
//    @Override
//    public void onBackPressed() {
//        //do nothing.
//    }
//private void sendToken1() {
//   // progressDialog = new ProgressDialog(this);
//    //progressDialog.setMessage("Registering Device...");
//    //progressDialog.show();
//
//
//    //final String token = SharedPreference.getInstance( this ).getDeviceToken();
//   // final String token = FirebaseInstanceId.getInstance().getToken();
//     ///final String nis = txt_nis.getText().toString();
////Log.i( "TOKEN",token );
//    if (token == null) {
//      //  progressDialog.dismiss();
//        Toast.makeText(this, "Token not generated", Toast.LENGTH_LONG).show();
//        return;
//    }
//
//    StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGISTER_DEVICE,
//            new Response.Listener<String>() {
//                @Override
//                public void onResponse(String response) {
//                    //progressDialog.dismiss();
//                    try {
//                        JSONObject obj = new JSONObject(response);
//                        Toast.makeText(activity_login.this, obj.getString("message"), Toast.LENGTH_LONG).show();
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//            },
//            new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    //progressDialog.dismiss();
//                    Toast.makeText(activity_login.this, error.getMessage(), Toast.LENGTH_LONG).show();
//                }
//            }) {
//
//        @Override
//        protected Map<String, String> getParams() throws AuthFailureError {
//            Map<String, String> params = new HashMap<>();
//            params.put("nis", txt_nis);
//            params.put("token", "ASDFSDFSDF");
//            return params;
//        }
//    };
//    FcmVolley.getInstance(this).addToRequestQueue(stringRequest);
//}
}
