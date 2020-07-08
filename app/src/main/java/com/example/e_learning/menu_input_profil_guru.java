package com.example.e_learning;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.e_learning.adavter.PicassoClient;
import com.example.e_learning.app.AppConfig;
import com.example.e_learning.app.AppController;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class menu_input_profil_guru extends AppCompatActivity {


    static final int REQUEST_LOCATION = 1;
    LocationManager locationManager;
    FloatingActionButton buttonChoose;
    Button buttonUpload,buttonUpload2;
    Toolbar toolbar;
    Double latti,longi;
    TextView id_txt,status;
    TextView v_lat,v_lng,v_jenis;
    Spinner v_nama_ibul,v_rhsu,v_jns;
    ImageView imageView;
    EditText v_berat,v_panajang;
    EditText v_nama,v_alamat,tgl,tgl1,v_agama,v_kls;
    Bitmap bitmap, decoded;
    int success;
    int PICK_IMAGE_REQUEST = 1;
    int bitmap_size = 60; // range 1 - 100
    private DatePicker datePicker;
    private Calendar calendar;
    private TextView dateView;
    private int year, month, day;
    private static final String TAG = menu_input_profil_guru.class.getSimpleName();
    TextView tgl2;
    /* 10.0.2.2 adalah IP Address localhost Emulator Android Studio. Ganti IP Address tetgl_lahirebut dengan
    IP Address Laptop jika di RUN di HP/Genymotion. HP/Genymotion dan Laptop harus 1 jaringan! */
    private String UPLOAD_URL = "http://192.168.56.1/TASRIF_anak/upload_tes.php";

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    private String KEY_IMAGE = "image";
    private String KEY_NAME = "nama";
    private String KEY_TTL = "ttl";
    private String KEY_AGAMA = "agama";
    private String KEY_MAPEL = "kelas";
    private String KEY_ALAMAT = "alamat";
    private  String KEY_JENIS= "jenkel";
    private String KEY_ID = "nis";
    String tag_json_obj = "json_obj_req";
    TextView foto;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_input_profil_guru);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        Toolbar toolbar = (Toolbar) findViewById( R.id.toolbar );
//        setSupportActionBar(toolbar);
//        if (getSupportActionBar() != null)
//            getSupportActionBar().setDisplayHomeAsUpEnabled( true );
        // buttonChoose = (FloatingActionButton) findViewById(R.id.buttonChoose);
        buttonUpload = (Button) findViewById(R.id.btn_up);
        v_nama = (EditText) findViewById(R.id.edit_nama);
        v_agama = (EditText) findViewById(R.id.txt_agama);
        v_kls = (EditText) findViewById(R.id.txt_mapel);
        v_jns = (Spinner) findViewById(R.id.spi_jns_kel);
        v_jenis= (TextView) findViewById(R.id.txt_jns);
        v_alamat = (EditText) findViewById(R.id.txt_alamat);
        foto = (TextView) findViewById(R.id.txt_foto);
        v_berat = (EditText) findViewById(R.id.txt_berat);
        buttonUpload2 = (Button) findViewById(R.id.btn_up2);
        v_panajang = (EditText) findViewById(R.id.txt_pjg);
        v_berat = (EditText) findViewById(R.id.txt_berat);
        imageView = (ImageView) findViewById(R.id.img_profil);
        id_txt= (TextView) findViewById(R.id.txt_nis);
        tgl = (EditText) findViewById(R.id.txt_tgl);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        // showDate(year, month + 1, day);
        // showDate1(year, month + 1, day);
        // showDate2(year, month + 1, day);
        // Initializing a String Array
        String[] jns = new String[]{
                "Select Jensis Kelamin...",
                "LK",
                "PR",
                "LAINYA"
        };
        final List<String> jenis = new ArrayList<>( Arrays.asList(jns));

        // Initializing an ArrayAdapter
        final ArrayAdapter<String> spinnerArrayAdapter2 = new ArrayAdapter<String>(
                this,R.layout.spinner_item,jenis){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    // Disable the fitgl_lahirt item from Spinner
                    // Fitgl_lahirt item will be use for hint
                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor( Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        spinnerArrayAdapter2.setDropDownViewResource(R.layout.spinner_item);
        v_jns.setAdapter(spinnerArrayAdapter2);

        v_jns.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);
                // If user change the default selection
                // Fitgl_lahirt item is disable and it is used for hint
                if(position > 0){
                    // Notify the selected item text
                    Toast.makeText
                            (getApplicationContext(), "Selected : " + selectedItemText, Toast.LENGTH_SHORT)
                            .show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        Bundle b = getIntent().getExtras();
        id_txt.setText(b.getCharSequence("nis"));
        v_nama.setText(b.getCharSequence("nama"));
        v_agama.setText(b.getCharSequence("agama"));
        v_alamat.setText(b.getCharSequence("alamat"));
        foto.setText(b.getCharSequence("foto"));
        tgl.setText(b.getCharSequence("ttl"));
        v_kls.setText(b.getCharSequence("mapel"));
        v_jenis.setText(b.getCharSequence("jl"));
        PicassoClient.downloadImage( this,"http://192.168.56.1/e-learning/foto/"+b.getCharSequence("foto"),imageView );
        //  imageView.setImageDrawable(Drawable.createFromPath("foto"));
        if (v_jenis.getText().equals("LK")){
            v_jns.setSelection(1);
        } else if (v_jenis.getText().equals("PR")){
            v_jns.setSelection(2);
        }  else {
            v_jns.setSelection(3);
        }




        // PicassoClient.downloadImage( this,"http://192.168.56.1/TASRIF_anak/"+imageurl,imageView );
        // PicassoClient.downloadImage(this, imageurl,foto);
        //  v_jenis.setText(jenkel);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);



        locationManager = (LocationManager)getSystemService( Context.LOCATION_SERVICE);
        //getLocation();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooser();
            }
        });

        buttonUpload.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (imageView.getResources()==null)
                    if (decoded==null){
                        ce();
                    }else {
                        uploadImage();
                    }
            }
        });
    }

    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, bitmap_size, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    private void uploadImage() {
        //menampilkan progress dialog
        final ProgressDialog loading = ProgressDialog.show(this, "Uploading...", "Please wait...", false, false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppConfig.Up_profil_guru,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "Response: " + response.toString());

                        try {
                            JSONObject jObj = new JSONObject(response);
                            success = jObj.getInt(TAG_SUCCESS);

                            if (success == 1) {
                                Log.e("v Add", jObj.toString());

                                Toast.makeText(menu_input_profil_guru.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
                                Toast.makeText(menu_input_profil_guru.this,"SUKSES UPLOUD DATA",Toast.LENGTH_LONG).show();


                            } else {
                                Toast.makeText(menu_input_profil_guru.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
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
                        Toast.makeText(menu_input_profil_guru.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
                        Log.e(TAG, error.getMessage().toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                //membuat parametetgl_lahir
                Map<String, String> params = new HashMap<String, String>();

                //menambah parameter yang di kirim ke web servis
                params.put(KEY_IMAGE, getStringImage(decoded));
                params.put(KEY_NAME, v_nama.getText().toString().trim());
                params.put(KEY_MAPEL, v_kls.getText().toString().trim());
                params.put(KEY_ID, id_txt.getText().toString().trim());
                params.put(KEY_AGAMA, v_agama.getText().toString().trim());
                //params.put(KEY_BERAT, v_berat.getText().toString().trim());
                // params.put(KEY_PANJANG, v_panajang.getText().toString().trim());
                params.put(KEY_JENIS, v_jns.getSelectedItem().toString().trim());
                params.put(KEY_TTL, tgl.getText().toString().trim());
                params.put(KEY_ALAMAT, v_alamat.getText().toString().trim());
                //kembali ke parametetgl_lahir
                Log.e(TAG, "" + params);
                return params;
            }
        };

        AppController.getInstance().addToRequestQueue(stringRequest, tag_json_obj);
    }

    private void ce() {
        //menampilkan progress dialog
        final ProgressDialog loading = ProgressDialog.show(this, "Uploading...", "Please wait...", false, false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppConfig.Up_profil_guru,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "Response: " + response.toString());

                        try {
                            JSONObject jObj = new JSONObject(response);
                            success = jObj.getInt(TAG_SUCCESS);

                            if (success == 1) {
                                Log.e("v Add", jObj.toString());

                                Toast.makeText(menu_input_profil_guru.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
                                Toast.makeText(menu_input_profil_guru.this,"SUKSES UPLOUD DATA",Toast.LENGTH_LONG).show();


                            } else {
                                Toast.makeText(menu_input_profil_guru.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
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
                        Toast.makeText(menu_input_profil_guru.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
                        Log.e(TAG, error.getMessage().toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                //membuat parametetgl_lahir
                Map<String, String> params = new HashMap<String, String>();

                //menambah parameter yang di kirim ke web servis
                //   params.put(KEY_IMAGE, getStringImage(decoded));
                params.put(KEY_NAME, v_nama.getText().toString().trim());
                params.put(KEY_MAPEL, v_kls.getText().toString().trim());
                params.put(KEY_ID, id_txt.getText().toString().trim());
                params.put(KEY_AGAMA, v_agama.getText().toString().trim());
                //params.put(KEY_BERAT, v_berat.getText().toString().trim());
                // params.put(KEY_PANJANG, v_panajang.getText().toString().trim());
                params.put(KEY_JENIS, v_jns.getSelectedItem().toString().trim());
                params.put(KEY_TTL, tgl.getText().toString().trim());
                params.put(KEY_ALAMAT, v_alamat.getText().toString().trim());
                //kembali ke parametetgl_lahir
                Log.e(TAG, "" + params);
                return params;
            }
        };

        AppController.getInstance().addToRequestQueue(stringRequest, tag_json_obj);
    }


    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //mengambil fambar dari Gallery
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                // 512 adalah resolusi tertinggi setelah image di resize, bisa di ganti.
                setToImageView(getResizedBitmap(bitmap, 512));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void kosong() {
        imageView.setImageResource( R.drawable.placeholder);
        v_nama.setText(null);
        id_txt.setText(null);
        v_alamat.setText(null);

        v_nama_ibul.setSelection(0);
        v_rhsu.setSelection(0);
        v_berat.setText(null);
    }

    private void setToImageView(Bitmap bmp) {
        //compress image
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, bitmap_size, bytes);
        decoded = BitmapFactory.decodeStream(new ByteArrayInputStream(bytes.toByteArray()));

        //menampilkan gambar yang dipilih dari camera/gallery ke ImageView
        imageView.setImageBitmap(decoded);
    }

    // fungsi resize image
    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }
//    void getLocation() {
//        if( ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
//                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COAtgl_lahirE_LOCATION)
//                != PackageManager.PERMISSION_GRANTED) {
//
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
//
//        } else {
//            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
//
//            if (location != null){
//                latti = location.getLatitude();
//                longi = location.getLongitude();
//
//                (v_lat).setText(""+ latti);
//                (v_lng).setText("" + longi);
//            } else {
//                ((TextView)findViewById(R.id.etLocationLat)).setText("Unable to find correct location.");
//                ((TextView)findViewById(R.id.etLocationLong)).setText("Unable to find correct location. ");
//            }
//        }
//
//    }


    //    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//
//        switch (requestCode) {
//            case REQUEST_LOCATION:
//                getLocation();
//                break;
//        }
//    }
    private void back() {
        startActivity(new Intent(menu_input_profil_guru.this, menu_utama.class));
        finish();
    }
    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
        //akan menampilkan teks ketika kalendar muncul setelah menekan tombol
        Toast.makeText(getApplicationContext(), "Pilih Tangal", Toast.LENGTH_SHORT)
                .show();
    }
    public void setDate1(View view) {
        showDialog(888);
        //akan menampilkan teks ketika kalendar muncul setelah menekan tombol
        Toast.makeText(getApplicationContext(), "Pilih Tangal", Toast.LENGTH_SHORT)
                .show();
    }
    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }if (id==888){
            return new DatePickerDialog(this, myDateListener1, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
            // arg1 = year
            // arg2 = month
            // arg3 = day
            showDate(arg1, arg2+1, arg3);
            //  showDate1(arg1, arg2+1, arg3);
            //showDate2(arg1, arg2+1+3, arg3);
        }
    };
    private DatePickerDialog.OnDateSetListener myDateListener1 = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
            // arg1 = year
            // arg2 = month
            // arg3 = day
            //  showDate(arg1, arg2+1, arg3);
            showDate1(arg1, arg2+1, arg3);
            showDate2(arg1, arg2+1+3, arg3);
        }
    };

    private void showDate(int year, int month, int day) {
        tgl.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }
    private void showDate1(int year, int month, int day) {
        tgl1.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));

    }
    private void showDate2(int year, int month, int day) {
        int a =0;
        int b = 0;
        if (month>12){
            year=year+1;
            //  month=month+3;
        }

        if (month==13){
            month=13-11;
        }else if(month==14){
            month=14-12;
        }else if (month==15){
            month=15-12;
        }
        tgl2.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));

    }


    protected void onResume() {
        super.onResume();
//
//        if (imageView.getDrawable()==null){
//            buttonUpload.setVisibility(View.INVISIBLE);
//        }
//
//
//        if (imageView.getDrawable()!=null){
//            buttonUpload.setVisibility(View.VISIBLE);
//            buttonUpload2.setVisibility(View.INVISIBLE);
//        }

    }



}
