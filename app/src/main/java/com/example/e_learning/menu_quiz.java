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
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.e_learning.app.AppConfig;
import com.example.e_learning.app.AppController;
import com.example.e_learning.objekdata.model_cek_quiz.Response_cek;
import com.example.e_learning.objekdata.model_cek_quiz.ResultItem_cek;
import com.example.e_learning.server.MyInterface;
import com.example.e_learning.server.Retroserver_server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    private List<ResultItem_cek> data = new ArrayList<>();
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
                cek("1");

            }
        });

        quiz2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             cek("2");
            }
        });
    }

    void cek(final String quiz_tampung){
        MyInterface api = Retroserver_server.getClient().create(MyInterface.class);
        Call<Response_cek> call = api.cek_quiz(nis.getText().toString(),quiz_tampung);
        call.enqueue(new Callback<Response_cek>() {
            @Override
            public void onResponse(Call<Response_cek> call, Response<Response_cek> response) {


                //  final List<Response_cek> masalah_list = response.body().getResult();
                data = response.body().getResult();



                if (data.size()==0) {
                    if (quiz_tampung.equals("1")){
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

                }else {
                    Toast.makeText(menu_quiz.this, "Sudah Quiz", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<Response_cek> call, Throwable t) {
                t.printStackTrace();
                if (t instanceof IOException) {


                }
                else {


                }
            }
        });

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
