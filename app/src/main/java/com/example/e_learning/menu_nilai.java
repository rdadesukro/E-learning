package com.example.e_learning;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.e_learning.app.AppConfig;
import com.example.e_learning.data_parser.Downloader_mapel;
import com.example.e_learning.data_parser.Downloader_nilai;

public class menu_nilai extends AppCompatActivity {
    TextView nis,lvl,pdf,bab,id_mapel;
    Button btn_pdf,btn_foto;
    ImageView imageView;
    Spinner spi_mapel;
    SearchView sv;
    private int PICK_PDF_REQUEST = 1;
    private Uri filePath;
    //storage permission code
    private static final int STORAGE_PERMISSION_CODE = 123;


    //Uri to store the image uri
    public final static String TAG_userneme = "username";
    public final static String TAG_ID = "nis";
    public final static String TAG_level = "level";
    public static final String my_shared_preferences = "my_shared_preferences";
    Boolean session = false;
    String id,level,username;
    String tag_json_obj = "json_obj_req";
    private String KEY_NIS = "nis";
    private String KEY_ID_QUIZ = "id_quiz";
    private String KEY_STATUS = "status";
    public static final String session_status = "session_status";
    TextView txt_username, txt_nama;
    static SwitchCompat switcher;
    SharedPreferences sharedpreferences;
    Intent intent;
    TextView id_guru;
    Uri fileUri;
    Button btn_choose_image,simpan;
    Bitmap bitmap, decoded;
    public final int REQUEST_CAMERA = 0;
    public final int SELECT_FILE = 1;
    EditText v_lat,v_lng,v_jenis,v_des;
    int bitmap_size = 40; // image quality 1 - 100;
    int max_resolution_image = 800;
    FloatingActionButton add;
    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_nilai);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar()!=null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        sharedpreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
        session = sharedpreferences.getBoolean(session_status, false);
        id = sharedpreferences.getString(TAG_ID, null);
        username = sharedpreferences.getString(TAG_userneme, null);
        level = sharedpreferences.getString(TAG_level, null);
        id_guru = (TextView)  findViewById(R.id.txt_id_guru);
        nis=   (TextView) findViewById(R.id.txt_nama);
        Bundle b = getIntent().getExtras();
        nis.setText(b.getCharSequence("id_mapel"));
       // id_user.setText(id);
        id_guru.setText(id);
//        lvl.setText(level);
        final RecyclerView rv = (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setItemAnimator(new DefaultItemAnimator());
        sv= (SearchView) findViewById(R.id.sv);
        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipelayout);

        new Downloader_nilai( menu_nilai.this, AppConfig.nilai+nis.getText() , rv, swipeRefreshLayout ).execute();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new Downloader_nilai( menu_nilai.this, AppConfig.nilai+nis.getText() , rv, swipeRefreshLayout ).execute();
            }
        });

     

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new Downloader_nilai( menu_nilai.this, AppConfig.nilai+nis.getText() , rv, swipeRefreshLayout ).execute();
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
