package com.example.e_learning;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class menu_register extends AppCompatActivity {

    ProgressDialog pDialog;
    Button btn_register, btn_login;
    EditText txt_name, txt_password, txt_confirm_password, txt_nama, txt_kunci, txt_token,txt_nis,txt_no,txt_user;
    Intent intent;
    int success;
    ConnectivityManager conMgr;


    private static final String TAG = menu_register.class.getSimpleName();

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    String tag_json_obj = "json_obj_req";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_register);

        conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        {
            if (conMgr.getActiveNetworkInfo() != null
                    && conMgr.getActiveNetworkInfo().isAvailable()
                    && conMgr.getActiveNetworkInfo().isConnected()) {
            } else {
                Toast.makeText(getApplicationContext(), "No Internet Connection",
                        Toast.LENGTH_LONG).show();
            }
        }

        btn_login = (Button) findViewById(R.id.btn_finis);
        btn_register = (Button) findViewById(R.id.btn_Register);
        txt_nis = (EditText) findViewById(R.id.txt_nama);


        txt_token = (EditText) findViewById(R.id.token);
        //txt_name = (EditText) findViewById(R.id.txt_nama);
        txt_password = (EditText) findViewById(R.id.txt_pass);
        txt_user = (EditText) findViewById(R.id.txt_user);
        txt_kunci = (EditText) findViewById(R.id.kunci);
        txt_confirm_password = (EditText) findViewById(R.id.txt_conf);
        btn_login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                intent = new Intent(menu_register.this, menu_login_siswa.class);
                finish();
                startActivity(intent);
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                // String name = txt_name.getText().toString();
                String nis = txt_nis.getText().toString();
                String ktakunci = txt_kunci.getText().toString();
                String user = txt_user.getText().toString();
                String level = txt_token.getText().toString();
                String password = txt_password.getText().toString();
                String confirm_password = txt_confirm_password.getText().toString();
                if (password.equals(null) && (password.equals( null ))){
                    Toast.makeText(getApplicationContext(), "PASSWORD DAN KOFIRAMSI PASSWORD TIDAK BOLEH KOSONG", Toast.LENGTH_SHORT).show();
                }

                if (conMgr.getActiveNetworkInfo() != null
                        && conMgr.getActiveNetworkInfo().isAvailable()
                        && conMgr.getActiveNetworkInfo().isConnected()) {
                    checkRegister( nis,user,ktakunci,password, confirm_password,level);

                } else {
                    Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                }

            }

        });




    }

    private void checkRegister(final String nis, final String user, final String ktakunci, final String password, final String confirm_password, final String level) {
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Register ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.URL_REGISTER_USER, new Response.Listener<String>() {

            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(String response) {
                Log.e(TAG, "Register Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);

                    // Check for error node in json
                    if (success == 1) {
                        Toast.makeText(menu_register.this, "Successfully Register!, Silahkan Login", Toast.LENGTH_LONG).show();
                        Log.e("Successfully Register!, Silahkan Login", jObj.toString());

                        // Toast.makeText(getApplicationContext(),
                        //  jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
                        Toast.makeText(getApplicationContext(), "REGISTER BERHASIL, SILAHKAN LOGIN", Toast.LENGTH_LONG).show();

                       txt_user.setText("");
                        txt_password.setText("");
                        txt_kunci.setText("");
                        txt_confirm_password.setText("");
                        txt_nis.setText("");


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
                // params.put("name", name);
                params.put("nis", nis);
                params.put("username", user);
                params.put("kunci", ktakunci);
                params.put("password", password);
                params.put("confirm_password", confirm_password);
                params.put("level", level);

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

    @Override
    public void onBackPressed() {
        // intent = new Intent(menu_register.this, activity_login.class);
        // finish();
        startActivity(intent);
    }

    private void back() {

    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            startActivity(new Intent(menu_register.this, menu_login.class));
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }


    public static boolean isValidnis(String nis) {
        boolean validate;
        String nisPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String nisPattern2 = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+\\.+[a-z]+";

        if (nis.matches(nisPattern)) {
            validate = true;
        } else if (nis.matches(nisPattern2)) {
            validate = true;
        } else {
            validate = false;
        }

        return validate;
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        unbinder.unbind();
//    }
}
