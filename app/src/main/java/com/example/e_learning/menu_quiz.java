package com.example.e_learning;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.e_learning.app.AppConfig;
import com.example.e_learning.app.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class menu_quiz extends AppCompatActivity {
     CardView quiz1,quiz2;
    private static final String TAG = menu_quiz.class.getSimpleName();
     TextView nis,nama,sts,mapel;
    private String jsonResponse;
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    public final static String TAG_userneme = "username";
    public final static String TAG_ID = "nis";
    public final static String TAG_level = "level";
    public static final String my_shared_preferences = "my_shared_preferences";
    Boolean session = false;
    String id,level,username;
    String tag_json_obj = "json_obj_req";
    private String KEY_NIS = "nis";
    private String KEY_MAPEL = "mapel";
    private String KEY_MAPEL1 = "mapel1";
    private String KEY_ID_QUIZ = "id_quiz";
    private String KEY_STATUS = "status";
    public static final String session_status = "session_status";
    TextView sts2, txt_nama;
    static SwitchCompat switcher;
    SharedPreferences sharedpreferences;

    LocationManager locationManager;
    ImageLoader imageLoader;
    TextView lat, lng, txt_token, lvl;

    int success;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_quiz);
        quiz1= (CardView) findViewById(R.id.card_q1);
        nis= (TextView) findViewById(R.id.txt_id);
        nama= (TextView) findViewById(R.id.txt_nama);
        mapel= (TextView) findViewById(R.id.txt_mapel);
        sts= (TextView) findViewById(R.id.txt_sts);
        sts2= (TextView) findViewById(R.id.txt_sts2);
        quiz2= (CardView) findViewById(R.id.card_q2);
        Bundle b = getIntent().getExtras();
        nis.setText(b.getCharSequence("id"));
        nama.setText(b.getCharSequence("nama"));
        sts.setText(b.getCharSequence("sts"));
        sts2.setText(b.getCharSequence("sts2"));
      //  simapan_sts2();
        quiz1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // mapel.setText("1");
                if (sts.getText().equals("1")){
                    Toast.makeText(getApplicationContext() ,"Anda Sudah Quiz", Toast.LENGTH_LONG).show();
                }else {
                    Intent intent = new Intent(getApplicationContext(), menu_quiz_1.class);
                    Bundle b = new Bundle();
                   // Toast.makeText(getApplicationContext() ,"Anda Sudah Quiz", Toast.LENGTH_LONG).show();
                    //Menyisipkan tipe data String ke dalam obyek bundle
                    b.putString("id", nis.getText().toString());
                    b.putString("nama", nama.getText().toString());
                    // b.putString("kls", txt_kls.getText().toString());
                    // b.putString("vidio", txtvidio.getText().toString());
                    intent.putExtras(b);
                    startActivity(intent);
                }
            }
        });

        quiz2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mapel.setText("2");
                if (sts2.getText().equals("1")){
                    Toast.makeText(getApplicationContext() ,"Anda Sudah Quiz", Toast.LENGTH_LONG).show();
                }else {
                    Intent intent = new Intent(getApplicationContext(), menu_quiz_2.class);
                    Bundle b = new Bundle();
                    // Toast.makeText(getApplicationContext() ,"Anda Sudah Quiz", Toast.LENGTH_LONG).show();
                    //Menyisipkan tipe data String ke dalam obyek bundle
                    b.putString("id", nis.getText().toString());
                    b.putString("nama", nama.getText().toString());
                    // b.putString("kls", txt_kls.getText().toString());
                    // b.putString("vidio", txtvidio.getText().toString());
                    intent.putExtras(b);
                    startActivity(intent);
                }
            }
        });
    }

    private void sts() {


        JsonArrayRequest req = new JsonArrayRequest(AppConfig.CEK+nis.getText()+ "&mapel=" +mapel.getText(),



                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());

                        try {


                            jsonResponse = "";



                            for (int i = 0; i < response.length(); i++) {

                                JSONObject person = (JSONObject) response
                                        .get(i);

                                String nm = person.getString("status");



                                sts.setText(nm);





                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),
                                    "Error: " + e.getMessage(),

                                    Toast.LENGTH_LONG).show();
                            Toast.makeText(getApplicationContext() ,"No Internet TESSS", Toast.LENGTH_LONG).show();


                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());

                //  Toast.makeText(getApplicationContext() ,"DATA TIDAK ADA", Toast.LENGTH_LONG).show();


            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(req);
    }
    private void simapan_sts2() {
        //menampilkan progress dialog
        final ProgressDialog loading = ProgressDialog.show(this, "Uploading...", "Please wait...", false, false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppConfig.sts2,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "Response: " + response.toString());

                        try {
                            JSONObject jObj = new JSONObject(response);
                            success = jObj.getInt(TAG_SUCCESS);

                            if (success == 1) {
                                Log.e("v Add", jObj.toString());

                                //  Toast.makeText(menu_profil.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
                                Toast.makeText(menu_quiz.this,"SUKSES SIMPAN NILAI",Toast.LENGTH_LONG).show();
                                // Intent i = new Intent(menu_lapor_bencana.this, data_terkirim.class);
                                //  startActivity(i);
                                //tes();

                            } else {
                                Toast.makeText(menu_quiz.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        //menghilangkan progress dialog
                        loading.dismiss();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //menghilangkan progress dialog
                        loading.dismiss();

                        //menampilkan toast
                        Toast.makeText(menu_quiz.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
                        Log.e(TAG, error.getMessage().toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                //membuat parameters
                Map<String, String> params = new HashMap<String, String>();

                //menambah parameter yang di kirim ke web servis
                // params.put(KEY_IMAGE, getStringImage(decoded));
                // params.put(KEY_ID, id1.getText().toString().trim());
                params.put(KEY_NIS, nis.getText().toString().trim());
                params.put(KEY_MAPEL1, mapel.getText().toString().trim());


                //kembali ke parameters
                Log.e(TAG, "" + params);
                return params;
            }
        };

        AppController.getInstance().addToRequestQueue(stringRequest, tag_json_obj);
    }
}
