package com.example.e_learning;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.example.e_learning.app.AppConfig;
import com.example.e_learning.app.AppController;
import com.example.e_learning.app.volleyrequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class menu_utama extends AppCompatActivity {

    CardView btn_klr,btn_sklh,btn_quiz,btn_nilai,btn_mtri,btn_profil;

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

    String goolgeMap = "com.google.android.apps.maps";
    Uri gmmIntentUri;
    ImageView iv;
    Animation anim;
    LinearLayout sliderDotspanel;
    private int dotscount;
    private ImageView[] dots;
    RequestQueue rq;

    TextView id_user;
    TextView sts,mapel1,mapel2,t_mapel1,t_mapel2;

    public static final String TAG_NAME = "name";
    ViewPager viewPager;
    private static final String TAG = menu_utama.class.getSimpleName();
    ImageView prof;
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    ImageView foto;
    String url = null;
    NetworkImageView previewImage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_utama);

        conMgr =(ConnectivityManager) getSystemService( Context.CONNECTIVITY_SERVICE);

        txt_username=   (TextView) findViewById(R.id.txt_nama);
        id_user = (TextView) findViewById(R.id.txt_id);
        lvl = (TextView) findViewById(R.id.txt_level);
        sts = (TextView) findViewById(R.id.txt_sts);
        mapel1 = (TextView) findViewById(R.id.txt_1);
        mapel2 = (TextView) findViewById(R.id.txt_2);
        t_mapel1 = (TextView) findViewById(R.id.txt_mapel1);
        t_mapel2 = (TextView) findViewById(R.id.txt_mapel2);
        previewImage = (NetworkImageView) findViewById( R.id.img );

        prof = (ImageView) findViewById(R.id.profile_image);
       
        btn_klr= (CardView) findViewById(R.id.card_klr);
        btn_sklh= (CardView) findViewById(R.id.card_sklh);
        btn_profil= (CardView) findViewById(R.id.card_siswa);
        btn_quiz= (CardView) findViewById(R.id.card_quiz);
        btn_nilai= (CardView) findViewById(R.id.card_nilai);
        btn_mtri= (CardView) findViewById(R.id.card_mtri);
        sharedpreferences = getSharedPreferences(menu_login_siswa.my_shared_preferences, Context.MODE_PRIVATE);
        id = getIntent().getStringExtra(TAG_ID);
        level = getIntent().getStringExtra(TAG_level);
        username = getIntent().getStringExtra(TAG_userneme);
       // Typeface aaa = Typeface.createFromAsset(getAssets(), "font/aaa.ttf");
      //  txt_username.setTypeface(aaa);
        txt_username.setText(username);
        lvl.setText(level);
        id_user.setText(id);
        Log.i("id_user", "onCreate: "+id);

        if (lvl.getText().equals("siswa")){
            data();
            sts();
            sts2();
           // simapan_sts();
           // simapan_sts2();

            btn_nilai.setVisibility(View.GONE);
        }
        if (lvl.getText().equals("guru")){
            data_guru();
            //prof.setVisibility(View.GONE);
            btn_quiz.setVisibility(View.GONE);
        }


        imageLoader = volleyrequest.getInstance(getApplicationContext())
                .getImageLoader();



        foto();


        String username = txt_username.getText().toString();
      
        btn_klr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertexit = new AlertDialog.Builder(menu_utama.this);
                alertexit
                        .setMessage("Apakah anda yakin ingin keluar ?")
                        .setCancelable(false)
                        .setPositiveButton("Iya",
                                new AlertDialog.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface arg0,
                                                        int arg1) {
                                        SharedPreferences.Editor editor = sharedpreferences.edit();
                                        editor.putBoolean(menu_login_siswa.session_status, false);
                                        editor.putString(TAG_ID, null);
                                        editor.putString(TAG_userneme, null);
                                        editor.commit();

                                        Intent intent = new Intent(menu_utama.this, menu_login_siswa.class);
                                        finish();
                                        startActivity(intent);
                                    }
                                })
                        .setNegativeButton("Tidak",
                                new AlertDialog.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        dialog.cancel();
                                    }
                                });
                AlertDialog a = alertexit.create();
                a.show();

            }
        });



        btn_quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent intent = new Intent(getApplicationContext(), menu_quiz.class);
                    Bundle b = new Bundle();

                    //Menyisipkan tipe data String ke dalam obyek bundle
                    b.putString("id", id_user.getText().toString());
                    b.putString("nama", txt_username.getText().toString());
                    b.putString("sts", sts.getText().toString());
                    b.putString("sts2", t_mapel2.getText().toString());
                    // b.putString("kls", txt_kls.getText().toString());
                    // b.putString("vidio", txtvidio.getText().toString());
                    intent.putExtras(b);
                    startActivity(intent);


            }
        });
        btn_sklh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), menu_sekolah.class);
                Bundle b = new Bundle();

                //Menyisipkan tipe data String ke dalam obyek bundle
                b.putString("id", id_user.getText().toString());
                intent.putExtras(b);
                startActivity(intent);
            }
        });
        prof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), menu_profil.class);
                Bundle b = new Bundle();

                //Menyisipkan tipe data String ke dalam obyek bundle
                b.putString("id", id_user.getText().toString());
                intent.putExtras(b);
                startActivity(intent);
            }
        });
        //Toast.makeText(this, "", Toast.LENGTH_SHORT).show();

        btn_profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), menu_siswa.class);
                startActivity(intent);
            }
        });

        btn_nilai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), menu_nilai.class);
                Bundle b = new Bundle();

                startActivity(intent);
            }
        });

        btn_mtri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), menu_mapel.class);
                Bundle b = new Bundle();

                //Menyisipkan tipe data String ke dalam obyek bundle
                b.putString("level", lvl.getText().toString());
                // b.putString("vidio", txtvidio.getText().toString());
                intent.putExtras(b);
                startActivity(intent);
            }
        });





    }
    private void checkRegister1(final String username1, final String token1) {


        StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.URL_MATIKAN_NOTIF, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "Register Response: " + response.toString());
                //  hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);

                    // Check for error node in json
                    if (success == 1) {
                        //    Toast.makeText(menu_utama.this, "Successfully Register", Toast.LENGTH_LONG).show();
                        //  Log.e("Successfully Register!", jObj.toString());

                        //   Toast.makeText(getApplicationContext(),
                        //      jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();


                    } else {
                        //  Toast.makeText(getApplicationContext(),
                        //      jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                //  Log.e(TAG, "Login Error: " + error.getMessage());
                // Toast.makeText(getApplicationContext(),
                //       error.getMessage(), Toast.LENGTH_LONG).show();

                //    hideDialog();

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                // params.put("name", name);
                params.put("username", username1);
                params.put("token", token1);


                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }


    public  void  onResume() {

        super.onResume();

        sharedpreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
        session = sharedpreferences.getBoolean(session_status, false);
        id = sharedpreferences.getString(TAG_ID, null);
        username = sharedpreferences.getString(TAG_userneme, null);
        level = sharedpreferences.getString(TAG_level, null);

        id_user.setText(id);
        txt_username.setText(username);
        lvl.setText(level);
        if (lvl.getText().equals("siswa")){
            data();
          //  simapan_sts();
            sts();
            btn_nilai.setVisibility(View.GONE);
        }
        if (lvl.getText().equals("guru")){
            data_guru();
            //prof.setVisibility(View.GONE);
            btn_quiz.setVisibility(View.GONE);
        }

       // data();
        foto();
    }





    public class DownloadImagesTask extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... urls) {
            return download_Image(urls[0]);
        }

        @Override
        protected void onPostExecute(Bitmap result) {prof.setImageBitmap(result);
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


        JsonArrayRequest req = new JsonArrayRequest(AppConfig.PROFIL+id_user.getText(),



                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());

                        try {


                            jsonResponse = "";



                            for (int i = 0; i < response.length(); i++) {

                                JSONObject person = (JSONObject) response
                                        .get(i);



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


        JsonArrayRequest req = new JsonArrayRequest(AppConfig.PROFIL_GURU+id_user.getText(),



                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());

                        try {


                            jsonResponse = "";



                            for (int i = 0; i < response.length(); i++) {

                                JSONObject person = (JSONObject) response
                                        .get(i);



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
    public void foto() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST,AppConfig.FOTO,

                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        // Log.e(TAG, "Login Response: " + response.toString());
                        // hideDialog();

                        try {
                            JSONObject res = new JSONObject(response);
                            JSONArray thread = res.getJSONArray("image");
                            for (int i = 0; i < thread.length(); i++) {
                                JSONObject obj = thread.getJSONObject(i);
                                url  = obj.getString("foto");


                            }

                            imageLoader.get("http://192.168.56.1/e-info-siswa/foto/"+url, ImageLoader.getImageListener(previewImage
                                    ,0,android.R.drawable
                                            .ic_dialog_alert));
                            previewImage.setImageUrl("http://192.168.56.1/e-info-siswa/foto/"+url, imageLoader);


                            // ImageLoader class instance
//                    ImageLoader imgLoader = new ImageLoader(getApplicationContext());
//                    imgLoader.DisplayImage(image_url, loader, image);

                        } catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse (VolleyError error) {
                // Log.e(TAG, "Login Error: " + error.getMessage());
//                Toast.makeText(getApplicationContext(),
//                        error.getMessage(), Toast.LENGTH_LONG).show();

                // hideDialog();
                previewImage.setImageResource( R.drawable.placeholder );
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("username",txt_username.getText().toString());
                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(stringRequest, tag_json_obj);
    }

    private void sts() {


        JsonArrayRequest req = new JsonArrayRequest(AppConfig.CEK+id_user.getText() + "&mapel=" +mapel1.getText(),



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
                                String ma = person.getString("mapel");



                                sts.setText(nm);
                                t_mapel1.setText(ma);





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
    private void sts2() {


        JsonArrayRequest req = new JsonArrayRequest(AppConfig.CEK+id_user.getText() + "&mapel=" +mapel2.getText(),



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
                                String ma = person.getString("mapel");



                                t_mapel2.setText(nm);
                               // t_mapel2.setText(ma);





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

    private void simapan_sts() {
        //menampilkan progress dialog
        final ProgressDialog loading = ProgressDialog.show(this, "Uploading...", "Please wait...", false, false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppConfig.sts,
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
                                Toast.makeText(menu_utama.this,"SUKSES SIMPAN NILAI",Toast.LENGTH_LONG).show();
                                // Intent i = new Intent(menu_lapor_bencana.this, data_terkirim.class);
                                //  startActivity(i);
                                //tes();

                            } else {
                                Toast.makeText(menu_utama.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
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
                        Toast.makeText(menu_utama.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
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
                params.put(KEY_NIS, id_user.getText().toString().trim());
                params.put(KEY_MAPEL, mapel1.getText().toString().trim());


                //kembali ke parameters
                Log.e(TAG, "" + params);
                return params;
            }
        };

        AppController.getInstance().addToRequestQueue(stringRequest, tag_json_obj);
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
                                Toast.makeText(menu_utama.this,"SUKSES SIMPAN NILAI",Toast.LENGTH_LONG).show();
                                // Intent i = new Intent(menu_lapor_bencana.this, data_terkirim.class);
                                //  startActivity(i);
                                //tes();

                            } else {
                                Toast.makeText(menu_utama.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
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
                        Toast.makeText(menu_utama.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
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
                params.put(KEY_NIS, id_user.getText().toString().trim());
                 params.put(KEY_MAPEL1, mapel2.getText().toString().trim());


                //kembali ke parameters
                Log.e(TAG, "" + params);
                return params;
            }
        };

        AppController.getInstance().addToRequestQueue(stringRequest, tag_json_obj);
    }



}
