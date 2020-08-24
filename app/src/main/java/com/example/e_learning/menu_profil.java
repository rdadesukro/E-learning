package com.example.e_learning;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.example.e_learning.app.AppConfig;
import com.example.e_learning.app.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class menu_profil extends AppCompatActivity {
    TextView nama,nis,agama,alamat,ttl,jl,kls;
    ImageView foto;
    private static final String TAG = menu_utama.class.getSimpleName();


    CardView btn_klr,btn_sklh,btn_quiz,btn_nilai,btn_mtri,btn_profil;

    public final static String TAG_userneme = "username";
    public final static String TAG_ID = "nis";
    public final static String TAG_level = "level";
    public static final String my_shared_preferences = "my_shared_preferences";
    Boolean session = false;
    String id,level,username;
    String foto1;
    String tag_json_obj = "json_obj_req";

    public static final String session_status = "session_status";
    TextView txt_username, txt_nama;
    static SwitchCompat switcher;
    SharedPreferences sharedpreferences;

    LocationManager locationManager;
    ImageLoader imageLoader;
    TextView lat, lng, txt_token, lvl;
    private String jsonResponse;
    int success;
    ConnectivityManager conMgr;
    Intent mapIntent;
    Intent intent;


    public static final String TAG_NAME = "name";
    ViewPager viewPager;
    ImageView prof;
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
   TextView t_foto,textView9;
    String url = null;
    TextView ganti;
    NetworkImageView previewImage;
    FloatingActionButton add;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_profil);
        nama = (TextView) findViewById(R.id.nama);
        nis = (TextView) findViewById(R.id.txt_nis);
        agama = (TextView) findViewById(R.id.txt_agama);
        alamat = (TextView) findViewById(R.id.txt_almt);
        jl = (TextView) findViewById(R.id.txt_jl);
        lvl=(TextView) findViewById(R.id.txt_level);
        ttl = (TextView) findViewById(R.id.txt_ttl);
        ganti = (TextView) findViewById(R.id.txt_ganti);
        textView9 = (TextView) findViewById(R.id.textView9);
        foto = (ImageView) findViewById(R.id.profile_image);

        t_foto = (TextView) findViewById(R.id.txt_foto);
        kls = (TextView) findViewById(R.id.txt_nilai);
        add=   (FloatingActionButton) findViewById(R.id.btn_Add);

        sharedpreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
        session = sharedpreferences.getBoolean(session_status, false);
        id = sharedpreferences.getString(TAG_ID, null);
        username = sharedpreferences.getString(TAG_userneme, null);
        level = sharedpreferences.getString(TAG_level, null);


        lvl.setText(level);
        Bundle b = getIntent().getExtras();
        nis.setText(b.getCharSequence("id"));
        if (lvl.getText().equals("guru")){
            textView9.setText("NIP");
            data_guru();
            ganti.setText("Mapel");
          //  add.setVisibility(View.GONE);

        }else {
            data();
        }

        add.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (lvl.getText().equals("guru")){

                    Intent intent = new Intent(getApplicationContext(), menu_input_profil_guru.class);
                    Bundle b = new Bundle();

                    //Menyisipkan tipe data String ke dalam obyek bundle
                    b.putString("nis", nis.getText().toString());
                    b.putString("nama", nama.getText().toString());
                    b.putString("jl", jl.getText().toString());
                    b.putString("ttl", ttl.getText().toString());
                    b.putString("alamat", alamat.getText().toString());
                    b.putString("agama", agama.getText().toString());
                    //  b.putString("kls", kls.getText().toString());
                    b.putString("kls", kls.getText().toString());
                    b.putString("foto", t_foto.getText().toString());


                    // b.putString("kls", txt_kls.getText().toString());
                    // b.putString("vidio", txtvidio.getText().toString());
                    intent.putExtras(b);
                    startActivity(intent);

                }else {
                    Intent intent = new Intent(getApplicationContext(), menu_input_profil.class);
                    Bundle b = new Bundle();

                    //Menyisipkan tipe data String ke dalam obyek bundle
                    b.putString("nis", nis.getText().toString());
                    b.putString("nama", nama.getText().toString());
                    b.putString("jl", jl.getText().toString());
                    b.putString("ttl", ttl.getText().toString());
                    b.putString("alamat", alamat.getText().toString());
                    b.putString("agama", agama.getText().toString());
                    //  b.putString("kls", kls.getText().toString());
                    b.putString("kls", kls.getText().toString());
                    b.putString("foto", t_foto.getText().toString());


                    // b.putString("kls", txt_kls.getText().toString());
                    // b.putString("vidio", txtvidio.getText().toString());
                    intent.putExtras(b);
                    startActivity(intent);

                }





            }
        });
    }



    public class DownloadImagesTask extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... urls) {
            return download_Image(urls[0]);
        }

        @Override
        protected void onPostExecute(Bitmap result) {foto.setImageBitmap(result);
        }

        private Bitmap download_Image(String url) {
            Bitmap bm = null;
            try {
                java.net.URL aURL = new URL(url);
                URLConnection conn = aURL.openConnection();
                conn.connect();
                InputStream is = conn.getInputStream();
                BufferedInputStream bis = new BufferedInputStream(is);
                bm = BitmapFactory.decodeStream(bis);
                bis.close();
                is.close();
            } catch (IOException e) {
                Log.e("Hub", "Error getting the image from server : " + e.getMessage().toString());
            }
            return bm;
        }
    }
    private void data() {


        JsonArrayRequest req = new JsonArrayRequest(AppConfig.PROFIL+nis.getText(),



                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());

                        try {


                            jsonResponse = "";



                            for (int i = 0; i < response.length(); i++) {

                                JSONObject person = (JSONObject) response
                                        .get(i);

                                String nm = person.getString("nama");
                                String kl = person.getString("kelas");
                                String ttl1 = person.getString("ttl");
                                String agm = person.getString("agama");
                                String jk = person.getString("jenkel");
                                String alm = person.getString("alamat");
                                foto1 = person.getString("foto");



                                t_foto.setText(foto1);
                                nama.setText(nm);
                                agama.setText(agm);
                                alamat.setText(alm);
                                jl.setText(jk);
                                ttl.setText(ttl1);

                                kls.setText(kl);

                                new DownloadImagesTask().execute("http://192.168.56.1/e-learning/foto/"+person.getString("foto"));





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


    private void data_guru() {


        JsonArrayRequest req = new JsonArrayRequest(AppConfig.PROFIL_GURU+nis.getText(),



                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());

                        try {


                            jsonResponse = "";



                            for (int i = 0; i < response.length(); i++) {

                                JSONObject person = (JSONObject) response
                                        .get(i);

                                String nm = person.getString("nama");
                                String kl = person.getString("nama_mapel");
                                String ttl1 = person.getString("ttl");
                                String agm = person.getString("agama");
                                String jk = person.getString("jk");
                                String alm = person.getString("alamat");
                                foto1 = person.getString("foto");



                                t_foto.setText(foto1);
                                nama.setText(nm);
                                agama.setText(agm);
                                alamat.setText(alm);
                                jl.setText(jk);
                                ttl.setText(ttl1);

                                kls.setText(kl);

                                new DownloadImagesTask().execute("http://192.168.56.1/e-learning/foto/"+person.getString("foto"));





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



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            Intent intent = new Intent(getApplicationContext(), menu_utama.class);
            startActivity(intent);

            return true;
        } else {


            return super.onKeyDown(keyCode, event);
        }
    }
}
