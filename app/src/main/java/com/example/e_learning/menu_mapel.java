package com.example.e_learning;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e_learning.app.AppConfig;
import com.example.e_learning.data_parser.Downloader_mapel;
import com.example.e_learning.data_parser.Downloader_siswa;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class menu_mapel extends AppCompatActivity {

    TextView nis,lvl,pdf,bab,id_mapel;
    Button btn_pdf,btn_foto;
    ImageView imageView;
    Spinner spi_mapel;
    SearchView sv;
    private int PICK_PDF_REQUEST = 1;
    private Uri filePath;
    //storage permission code
    private static final int STORAGE_PERMISSION_CODE = 123;

    String PdfNameHolder, PdfPathHolder, PdfID,mapel;
    //Uri to store the image uri

    // Creating EditText.
    String PdfNameEditText ;

    // Creating URI .
    Uri uri;

    // Server URL.
    public static final String PDF_UPLOAD_HTTP_URL = "https://android-examples.000webhostapp.com/server_upload_pdf.php";

    // Pdf upload request code.
    public int PDF_REQ_CODE = 1;
String mapel_new;

    Intent intent;
    Uri fileUri;
    Button btn_choose_image,simpan;
    Bitmap bitmap, decoded;
    public final int REQUEST_CAMERA = 0;
    public final int SELECT_FILE = 1;
    EditText v_lat,v_lng,v_jenis,v_des;
    int bitmap_size = 40; // image quality 1 - 100;
    int max_resolution_image = 800;
    FloatingActionButton add;
    String level;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_mapel);

        nis=   (TextView) findViewById(R.id.txt_nama);
        lvl=   (TextView) findViewById(R.id.txt_level);
        add=   (FloatingActionButton) findViewById(R.id.btn_Add);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar()!=null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle b = getIntent().getExtras();
        lvl.setText(b.getCharSequence("level"));
        level= String.valueOf(b.getCharSequence("level"));
      nis.setText(b.getCharSequence("id_mapel"));

//        if (lvl.getText().equals("siswa")){
//            add.setVisibility(View.GONE);
//        }
        RequestRunTimePermission();
        final RecyclerView rv = (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setItemAnimator(new DefaultItemAnimator());
        sv= (SearchView) findViewById(R.id.sv);
        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipelayout);

        if (level.equals("siswa")){
            new Downloader_mapel( menu_mapel.this, AppConfig.mapel_siswa , rv, swipeRefreshLayout ).execute();
        }else {

            new Downloader_mapel( menu_mapel.this, AppConfig.mapel+nis.getText() , rv, swipeRefreshLayout ).execute();
        }


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (level.equals("siswa")){
                    new Downloader_mapel( menu_mapel.this, AppConfig.mapel_siswa , rv, swipeRefreshLayout ).execute();
                }else {

                    new Downloader_mapel( menu_mapel.this, AppConfig.mapel+nis.getText() , rv, swipeRefreshLayout ).execute();
                }
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInputDialog();
//                Intent intent = new Intent(getApplicationContext(), tes_up.class);
//                startActivity(intent);

            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                if (level.equals("siswa")){
                    new Downloader_mapel( menu_mapel.this, AppConfig.mapel_siswa , rv, swipeRefreshLayout ).execute();
                }else {

                    new Downloader_mapel( menu_mapel.this, AppConfig.mapel+nis.getText() , rv, swipeRefreshLayout ).execute();
                }
            }
        });

    }

    protected void showInputDialog() {

        // get prompts.xml view
//        LayoutInflater layoutInflater = LayoutInflater.from(menu_mapel.this);
//        View promptView = layoutInflater.inflate(R.layout.input_profil, null);
//
//        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(menu_mapel.this);
//        alertDialogBuilder.setTitle("TAMBAH DATA");
//       // alertDialogBuilder.setIcon(R.drawable.bpbd);
//        alertDialogBuilder.setView(promptView);
//
//
//        bab = (EditText) promptView.findViewById(R.id.ipt_bab);
//     //   pdf = (EditText) promptView.findViewById(R.id.ipt_pdf);
//        spi_mapel = (Spinner) promptView.findViewById(R.id.spi_mapel);
//
//        id_mapel = (TextView) promptView.findViewById(R.id.txt_id);
//
//        btn_pdf = (Button) promptView.findViewById(R.id.btn_pdf);
//       // btn_foto = (Button) promptView.findViewById(R.id.btn_foto);
//        imageView = (ImageView) promptView.findViewById(R.id.imagetoolbar);
//
//        btn_pdf.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                // PDF selection code start from here .
//                // Creating intent object.
//                Intent intent = new Intent();
//
//                // Setting up default file pickup time as PDF.
//                intent.setType("application/pdf");
//
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//
//                startActivityForResult(Intent.createChooser(intent, "Select Pdf"), PDF_REQ_CODE);
//            }
//        });
//
//        String[] bencana = new String[]{
//                "Pilih Mata Pelajaran...",
//                "A",
//                "B"
//
//        };
//        final List<String> bencanaList = new ArrayList<>( Arrays.asList(bencana));
//
//        // Initializing an ArrayAdapter
//        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
//                this,R.layout.spinner_item,bencanaList){
//            @Override
//            public boolean isEnabled(int position){
//                if(position == 0)
//                {
//                    // Disable the first item from Spinner
//                    // First item will be use for hint
//                    return false;
//                }
//                else
//                {
//                    return true;
//                }
//            }
//            @Override
//            public View getDropDownView(int position, View convertView,
//                                        ViewGroup parent) {
//                View view = super.getDropDownView(position, convertView, parent);
//                TextView tv = (TextView) view;
//                if(position == 0){
//                    // Set the hint text color gray
//                    tv.setTextColor( Color.parseColor("#673AB7"));
//                }
//                else {
//                    tv.setTextColor(Color.BLACK);
//                }
//                return view;
//            }
//        };
//        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
//        spi_mapel.setAdapter(spinnerArrayAdapter);
//
//        spi_mapel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String selectedItemText = (String) parent.getItemAtPosition(position);
//                // If user change the default selection
//                // First item is disable and it is used for hint
//                if(position > 0){
//                    // Notify the selected item text
//                    Toast.makeText
//                            (getApplicationContext(), "Selected : " + selectedItemText, Toast.LENGTH_SHORT)
//                            .show();
//                    if (selectedItemText.equals("A")){
//                        id_mapel.setText("1");
//                    }else {
//                        id_mapel.setText("2");
//                    }
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//
//
//
//        alertDialogBuilder.setCancelable(false)
//                .setPositiveButton("SIMPAN", new DialogInterface.OnClickListener() {
//                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//                    public void onClick(DialogInterface dialog, int id) {
//
//                        PdfUploadFunction();
//                    }
//                })
//                .setNegativeButton("Cancel",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//                                dialog.cancel();
//                            }
//                        });
//
//        // create an alert dialog
//        AlertDialog alert = alertDialogBuilder.create();
//        alert.show();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PDF_REQ_CODE && resultCode == RESULT_OK && data != null && data.getData() != null) {

            uri = data.getData();

            // After selecting the PDF set PDF is Selected text inside Button.
            btn_pdf.setText("PDF is Selected");
        }
    }

    // PDF upload function starts from here.
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void PdfUploadFunction() {

        // Getting pdf name from EditText.
        PdfNameHolder = bab.getText().toString().trim();
        mapel= id_mapel.getText().toString().trim();

        // Getting file path using Filepath class.
        PdfPathHolder = FilePath.getPath(this, uri);

        // If file path object is null then showing toast message to move file into internal storage.
        if (PdfPathHolder == null) {

            Toast.makeText(this, "Please move your PDF file to internal storage & try again.", Toast.LENGTH_LONG).show();

        }
        // If file path is not null then PDF uploading file process will starts.
        else {

            try {

                PdfID = UUID.randomUUID().toString();

                new MultipartUploadRequest(this, PdfID, AppConfig.UP)
                        .addFileToUpload(PdfPathHolder, "pdf")
                        .addParameter("name", PdfNameHolder)
                        .addParameter("mapel", mapel)
                        .addParameter("id_guru", mapel)
                        .setNotificationConfig(new UploadNotificationConfig())
                        .setMaxRetries(5)
                        .startUpload();

                Toast.makeText(menu_mapel.this,"SUKSES TAMBAH MATERI", Toast.LENGTH_LONG).show();


            } catch (Exception exception) {

                Toast.makeText(this, exception.getMessage(), Toast.LENGTH_SHORT).show();
                exception.printStackTrace();
            }
        }
    }


    // Requesting run time permission method starts from here.
    public void RequestRunTimePermission(){

        if (ActivityCompat.shouldShowRequestPermissionRationale(menu_mapel.this, Manifest.permission.READ_EXTERNAL_STORAGE))
        {

            Toast.makeText(menu_mapel.this,"READ_EXTERNAL_STORAGE permission Access Dialog", Toast.LENGTH_LONG).show();

        } else {

            ActivityCompat.requestPermissions(menu_mapel.this,new String[]{ Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

        }
    }

    @Override
    public void onRequestPermissionsResult(int RC, String per[], int[] Result) {

        switch (RC) {

            case 1:

                if (Result.length > 0 && Result[0] == PackageManager.PERMISSION_GRANTED) {

                  //  Toast.makeText(menu_mapel.this,"Permission Granted", Toast.LENGTH_LONG).show();

                } else {

                    Toast.makeText(menu_mapel.this,"Permission Canceled", Toast.LENGTH_LONG).show();

                }
                break;
        }
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
