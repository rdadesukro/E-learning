package com.example.e_learning;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.example.e_learning.app.AppConfig;
import com.example.e_learning.app.AppController;
import com.example.e_learning.objekdata.BaseResponse;
import com.example.e_learning.server.MyInterface;
import com.example.e_learning.server.Retroserver_server;
import com.squareup.picasso.Picasso;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

public class menu_materi_A extends AppCompatActivity {
    private static final String TAG = menu_materi_A.class.getSimpleName();
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    int success;
    public final static String TAG_userneme = "username";
    public final static String TAG_ID = "nis";
    ImageView hapus,edit;
    GridView myGridView;
    Uri tempUri;
    File file;
    String id_mapel;
    private static final int BUFFER_SIZE = 1024 * 2;
    private static final String IMAGE_DIRECTORY = "/demonuts_upload_gallery";
    EditText bab,data;
    Button siman,seleck;

    ProgressBar myProgressBar;
    public final static String TAG_level = "level";
    public static final String my_shared_preferences = "my_shared_preferences";
    Boolean session = false;
    FloatingActionButton add;
    String tag_json_obj = "json_obj_req";
    String id,level,username;
    TextView id_apel,siswa;

    public static final String session_status = "session_status";
    TextView txt_username, txt_nama;
    static SwitchCompat switcher;
    SharedPreferences sharedpreferences;
    public class PDFDoc {
        int id;
        String name,category,pdfURL,pdfIconURL;
        public int getId() {return id;}
        public void setId(int id) {this.id = id;}
        public String getName() {return name;}
        public void setName(String name) {this.name = name;}
        public String getAuthor() {return category;}
        public void setCategory(String category) {this.category = category;}
        public String getPdfURL() {return pdfURL;}
        public void setPdfURL(String pdfURL) {this.pdfURL = pdfURL;}
        public String getPdfIconURL() {return pdfIconURL;}
        public void setPdfIconURL(String pdfIconURL) {this.pdfIconURL = pdfIconURL;}
    }
    /*
    Our custom adapter class
    */
    public class GridViewAdapter extends BaseAdapter {
        Context c;
        ArrayList<PDFDoc> pdfDocuments;
        public GridViewAdapter(Context c, ArrayList<PDFDoc> pdfDocuments) {
            this.c = c;
            this.pdfDocuments = pdfDocuments;
        }
        @Override
        public int getCount() {return pdfDocuments.size();}
        @Override
        public Object getItem(int pos) {return pdfDocuments.get(pos);}
        @Override
        public long getItemId(int pos) {return pos;}
        @Override
        public View getView(int pos, View view, ViewGroup viewGroup) {
            if(view==null)
            {
                view= LayoutInflater.from(c).inflate(R.layout.row_model,viewGroup,false);
            }
            TextView txtName = view.findViewById(R.id.pdfNameTxt);
            TextView txtAuthor = view.findViewById(R.id.authorTxt);
            hapus = view.findViewById(R.id.btn_hapus);
            edit = view.findViewById(R.id.btn_edit);
            ImageView pdfIcon = view.findViewById(R.id.imageView);
            final     PDFDoc pdf;
             pdf= (PDFDoc) this.getItem(pos);
            txtName.setText(pdf.getName());
            txtAuthor.setText(pdf.getAuthor());
            if(pdf.getPdfURL() != null && pdf.getPdfURL().length()>0)
            {
               // Picasso.get().load(pdf.getPdfIconURL()).placeholder(R.drawable.default_scroll_handle_bottom).into(pdfIcon);
            }else {
                Toast.makeText(c, "Empty Image URL", Toast.LENGTH_LONG).show();
               // Picasso.get().load(R.drawable.default_scroll_handle_bottom).into(pdfIcon);
            }
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(c, pdf.getName(), Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(c,PDFActivity.class);
                    i.putExtra("PATH",pdf.getPdfURL());
                    c.startActivity(i);
                }
            });



if (siswa.getText().equals("siswa")){
    Toast.makeText(c, "Anda Siswa", Toast.LENGTH_LONG).show();
    hapus.setVisibility(View.GONE);
    edit.setVisibility(View.GONE);
}else {
    hapus.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Toast.makeText(c, "Empty Image URL"+pdf.getId(), Toast.LENGTH_LONG).show();
            AlertDialog.Builder alertexit = new AlertDialog.Builder(menu_materi_A.this);
            alertexit.setIcon(R.drawable.ic_delete_forever_black_24dp);
            alertexit.setTitle("HAPUS");
            alertexit
                    .setMessage("Apakah Yakin Mau di Hapus")
                    .setCancelable(false)
                    .setPositiveButton("Iya",
                            new AlertDialog.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    StringRequest stringRequest = new StringRequest(Request.Method.GET, AppConfig.HAPUS+pdf.getId(),
                                            new Response.Listener<String>() {
                                                @Override
                                                public void onResponse(String response) {
                                                    Log.e(TAG, "Response: " + response.toString());

                                                    try {
                                                        JSONObject jObj = new JSONObject(response);
                                                        success = jObj.getInt(TAG_SUCCESS);

                                                        if (success == 1) {
                                                            Log.e("v Add", jObj.toString());
                                                           // new JSONDownloader(menu_materi_A.this).retrieve(myGridView,myProgressBar);
                                                            //    Toast.makeText(menu_detail_laporan.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
                                                            Toast.makeText(menu_materi_A.this,"SUKSES HAPUS DATA",Toast.LENGTH_LONG).show();
                                                            //  Intent i = new Intent(menu_detail_laporan.this, menu_laporan_masuk.class);
                                                            //  startActivity(i);

                                                        } else {
                                                            //Toast.makeText(menu_detail_laporan.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
                                                        }
                                                    } catch (JSONException e) {
                                                        e.printStackTrace();
                                                    }

                                                    //menghilangkan progress dialog
                                                    //  loading.dismiss();
                                                }
                                            },
                                            new Response.ErrorListener() {
                                                @Override
                                                public void onErrorResponse(VolleyError error) {
                                                    //menghilangkan progress dialog
                                                    //  loading.dismiss();

                                                    //menampilkan toast
                                                    Toast.makeText(menu_materi_A.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
                                                    Log.e(TAG, error.getMessage().toString());
                                                }
                                            }) {
                                    };

                                    AppController.getInstance().addToRequestQueue(stringRequest, tag_json_obj);


                                    // Toast.makeText(menu_materi_A.this,"SUKSES HAPUS DATA",Toast.LENGTH_LONG).show();
                                    //  Intent i = new Intent(menu_materi_A.this, menu_materi_A.class);
                                    //  startActivity(i);
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

         //   return false;

        }
    });

    edit.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            LayoutInflater layoutInflater = LayoutInflater.from(menu_materi_A.this);
            View promptView = layoutInflater.inflate(R.layout.input_profil, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(menu_materi_A.this);
        alertDialogBuilder.setView(promptView);


        bab = (EditText) promptView.findViewById(R.id.edit_bab);
        data = (EditText) promptView.findViewById(R.id.edit_file);


        bab.setText(pdf.getAuthor());
        data.setText(pdf.getPdfURL());




        siman = (Button) promptView.findViewById(R.id.btn_simpan);
        seleck = (Button) promptView.findViewById(R.id.btn_select);



            seleck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    tempUri=null;
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    intent.setType("application/pdf");
                    startActivityForResult(intent,1);
                    // Toast.makeText(this, "ade ade", Toast.LENGTH_SHORT).show();

                }catch (Exception e){
                    Log.i("cek_errror", "btn_data: "+e);

                }

            }
        });

            siman.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final AlertDialog pd_new = new AlertDialog.Builder(menu_materi_A.this).create();

                    pd_new.setTitle("Edit Data");
                    pd_new.setMessage("Mohon tunggu sedang memproses...");
                    pd_new.show();
                    pd_new.setCancelable(false);

                    MyInterface api = Retroserver_server.getClient().create(MyInterface.class);
                    final RequestBody bab1 = createPartFromString(""+bab.getText().toString());
                    RequestBody id_data = createPartFromString(""+pdf.getId());

                    RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);

                    MultipartBody.Part body =
                            MultipartBody.Part.createFormData("materi", file.getName(), requestFile);
                    Log.i("nama_file", "simpan_permohonan: "+body);

                    Call<BaseResponse> sendbio = api.edit_file(
                            bab1,
                            id_data,
                            body);


                    // Toast.makeText(this, "image" + (i + 1), Toast.LENGTH_SHORT);
                    sendbio.enqueue(new Callback<BaseResponse>() {
                        @Override
                        public void onResponse(Call<BaseResponse> call, retrofit2.Response<BaseResponse> response) {

                            try {
                                if (response.isSuccessful()) {
                                    String kode = response.body().getvalue();
                                    //   String value = response.body().getvalue();
                                    //  progress.dismiss();
                                    if (kode.equals("1")) {

                                        tempUri=null;
                                        pd_new.dismiss();
                                        Toast.makeText(menu_materi_A.this, "Berhasil", Toast.LENGTH_SHORT).show();

                                    }

                                    if (kode.equals("0")) {
                                        pd_new.dismiss();
                                        Toast.makeText(menu_materi_A.this, "Gagal", Toast.LENGTH_SHORT);
                                    }
                                }else {
                                    switch (response.code()) {
                                        case 404:
                                            // pd_new.dismiss();
                                            //  gagal("Gagal");
                                            Toast.makeText(menu_materi_A.this, "not found", Toast.LENGTH_SHORT).show();
                                            break;
                                        case 500:
                                            //  pd_new.dismiss();
                                            //  gagal("server broken");
                                            Toast.makeText(menu_materi_A.this, "server broken", Toast.LENGTH_SHORT).show();
                                            break;
                                        default:
                                            //pd_new.dismiss();
                                            // gagal("unknown error");
                                            Toast.makeText(menu_materi_A.this, "unknown error", Toast.LENGTH_SHORT).show();
                                            break;
                                    }
                                }

                            }catch (Exception e){
                                Log.i("error2", "There is an error"+e);
                                e.printStackTrace();

                            }


                        }

                        @Override
                        public void onFailure(Call<BaseResponse> call, Throwable t) {

                            Log.i("error1", "onFailure: "+t);
                            t.printStackTrace();
                            if (t instanceof IOException) {
                                // pd_new.dismiss();
                                //  gagal("Jaringan Anda Bermasalah");
                                Log.i("error3", "onFailure: "+t);
                                Toast.makeText(menu_materi_A.this, "Jaringan Anda Bermasalah", Toast.LENGTH_SHORT).show();

                            }
                            else {
                                // pd_new.dismiss();
                                //  gagal("Server Donwn");
                                Toast.makeText(menu_materi_A.this, "Server Donwn", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });






                }
            });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();

        }
    });



}

            return view;

        }


    }
    /*
    Our HTTP Client
    */
    public class JSONDownloader {
       private static final String PDF_SITE_URL1="http://192.168.56.1/e-learning/?id_mapel=";
        private static final String PDF_SITE_URL2="http://192.168.56.1/e-learning/pdf/";

       String s= (String) id_apel.getText();
        private final String B = AppConfig.PDF_SITE_URL+s;
        private final Context c;
        private GridViewAdapter adapter ;
        public JSONDownloader(Context c) {
            this.c = c;
        }
        /*
        DOWNLOAD PDFS FROM MYSQL
        */
        public void retrieve(final GridView gv, final ProgressBar myProgressBar)
        {
            final ArrayList<PDFDoc> pdfDocuments = new ArrayList<>();
            myProgressBar.setIndeterminate(true);
            myProgressBar.setVisibility(View.VISIBLE);
            AndroidNetworking.get(PDF_SITE_URL1+id_apel.getText())
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONArray(new JSONArrayRequestListener() {
                        @Override
                        public void onResponse(JSONArray response) {
                            JSONObject jo;
                            PDFDoc p;
                            try
                            {
                                for(int i=0;i<response.length();i++)
                                {
                                    jo=response.getJSONObject(i);
                                    int id=jo.getInt("id");
                                    String name=jo.getString("nama");
                                    String category=jo.getString("bab");
                                   // String description=jo.getString("des");
                                    String pdfURL=jo.getString("pdf_url");
                                    String pdfIconURL=jo.getString("pdf_icon");
                                    p=new PDFDoc();
                                    p.setId(id);
                                    p.setName(name);
                                    p.setCategory(category);
                                   // p.setCategory(description);
                                    p.setPdfURL(PDF_SITE_URL2+pdfURL);
                                   p.setPdfIconURL(PDF_SITE_URL2+pdfIconURL);
                                    pdfDocuments.add(p);
                                }
                                adapter =new GridViewAdapter(c,pdfDocuments);
                                gv.setAdapter(adapter);
                                myProgressBar.setVisibility(View.GONE);
                            }catch (JSONException e)
                            {
                                myProgressBar.setVisibility(View.GONE);
                                Toast.makeText(c, "GOOD RESPONSE BUT JAVA CAN'T PARSE JSON IT RECEIEVED. "+e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                        //ERROR
                        @Override
                        public void onError(ANError error) {
                            error.printStackTrace();
                            myProgressBar.setVisibility(View.GONE);
                         //   Toast.makeText(c, "UNSUCCESSFUL :  ERROR IS : "+error.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
        }
    }


    @SuppressLint("RestrictedApi")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_materi1);
        final GridView myGridView= findViewById(R.id.myGridView);
        id_apel = (TextView) findViewById(R.id.txt_nama);
        siswa = (TextView) findViewById(R.id.txt_siswa);
       hapus = (ImageView) findViewById(R.id.btn_hapus);
        sharedpreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
        session = sharedpreferences.getBoolean(session_status, false);
        id = sharedpreferences.getString(TAG_ID, null);
        username = sharedpreferences.getString(TAG_userneme, null);
        level = sharedpreferences.getString(TAG_level, null);
        siswa.setText(level);
     //   Toast.makeText(this, "UNSUCCESSFUL :  ERROR IS : "+level, Toast.LENGTH_LONG).show();




        add=   (FloatingActionButton) findViewById(R.id.btn_Add);
        if (level.equals("siswa")){
            add.setVisibility(View.GONE);
        }
        //lvl.setText(level);
        //id_user.setText(id);
        FloatingActionButton btnRetrieve = (FloatingActionButton) findViewById(R.id.downloadBtn);
        final ProgressBar myProgressBar= findViewById(R.id.myProgressBar);
        Intent i = this.getIntent();
        String idD = i.getExtras().getString("id");
        id_apel.setText(idD);
        new JSONDownloader(menu_materi_A.this).retrieve(myGridView,myProgressBar);
        btnRetrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new JSONDownloader(menu_materi_A.this).retrieve(myGridView,myProgressBar);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), menu_add_data.class);
                Bundle b = new Bundle();


                b.putString("id_mapel", id_apel.getText().toString());
                intent.putExtras(b);
                startActivity(intent);

            }
        });


    }
    private void tes() {

    }
    private void uploadImage() {
        //menampilkan progress dialog
        final ProgressDialog loading = ProgressDialog.show(this, "Uploading...", "Please wait...", false, false);

    }


    public  void  onResume() {

        super.onResume();
         myGridView= findViewById(R.id.myGridView);
        id_apel = (TextView) findViewById(R.id.txt_nama);
        FloatingActionButton btnRetrieve = (FloatingActionButton) findViewById(R.id.downloadBtn);
        final ProgressBar myProgressBar= findViewById(R.id.myProgressBar);
        siswa = (TextView) findViewById(R.id.txt_siswa);
        new JSONDownloader(menu_materi_A.this).retrieve(myGridView,myProgressBar);

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data1) {
        if (resultCode == RESULT_OK) {
            // Get the Uri of the selected file
            Uri uri = data1.getData();

            String path = getFilePathFromURI(menu_materi_A.this,uri);
            Log.d("ioooo",path);
            File file = new File(path);
//        displayFromFile(file);
//        edit_file.setText(""+file);
            int file_size = Integer.parseInt(String.valueOf(file.length()/1024));



            String val = ""+file;
            String extension = val.substring(val.lastIndexOf(".") + 1);
            //   tempUri = Uri.fromFile(new File(file.getAbsolutePath()));
            displayFromFile(file);
            data.setText(""+file);
            Log.i("ukuran_file", "onActivityResult: "+file_size+tempUri);
            // data1.setText(""+file);
//            if (extension.equals("pdf")||extension.equals("PDF")){
//
//                if (file_size>5024){
//                  //  new GlideToast.makeToast((Activity) this,"File Terlau Besar",GlideToast.LENGTHTOOLONG,GlideToast.WARNINGTOAST,GlideToast.CENTER).show();
//
//                }else{
//
//                    displayFromFile(file);
//                    data1.setText(""+file);
//                }
////
//            }else {
//               //new GlideToast.makeToast((Activity) this,"File Harus Format Pdf",GlideToast.LENGTHTOOLONG,GlideToast.WARNINGTOAST,GlideToast.CENTER).show();
//
//            }


            //  uploadPDFfile(path);

        }

        super.onActivityResult(requestCode, resultCode, data1);

    }
    public static String getFileName(Uri uri) {
        if (uri == null) return null;
        String fileName = null;
        String path = uri.getPath();
        int cut = path.lastIndexOf('/');
        if (cut != -1) {
            fileName = path.substring(cut + 1);
        }
        return fileName;
    }

    public static String getFilePathFromURI(Context context, Uri contentUri) {
        //copy file and send new file path
        String fileName = getFileName(contentUri);
        File wallpaperDirectory = new File(
                Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
        // have the object build the directory structure, if needed.
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
        }
        if (!TextUtils.isEmpty(fileName)) {
            File copyFile = new File(wallpaperDirectory + File.separator + fileName);
            // create folder if not exists

            copy(context, contentUri, copyFile);
            return copyFile.getAbsolutePath();
        }
        return null;
    }
    public static void copy(Context context, Uri srcUri, File dstFile) {
        try {
            InputStream inputStream = context.getContentResolver().openInputStream(srcUri);
            if (inputStream == null) return;
            OutputStream outputStream = new FileOutputStream(dstFile);
            copystream(inputStream, outputStream);
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int copystream(InputStream input, OutputStream output) throws Exception, IOException {
        byte[] buffer = new byte[BUFFER_SIZE];

        BufferedInputStream in = new BufferedInputStream(input, BUFFER_SIZE);
        BufferedOutputStream out = new BufferedOutputStream(output, BUFFER_SIZE);
        int count = 0, n = 0;
        try {
            while ((n = in.read(buffer, 0, BUFFER_SIZE)) != -1) {
                out.write(buffer, 0, n);
                count += n;
            }
            out.flush();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                Log.e(e.getMessage(), String.valueOf(e));
            }
            try {
                in.close();
            } catch (IOException e) {
                Log.e(e.getMessage(), String.valueOf(e));
            }
        }
        return count;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void displayFromFile(File file1) {


        tempUri = Uri.fromFile(new File(file1.getAbsolutePath()));
        file =  FileUtils.getFile(this, tempUri);
        file.getName();
        Log.i("nama_file", "displayFromFile: "+file.getName());



    }
    @NonNull
    private RequestBody createPartFromString(String descriptionString) {
        return RequestBody.create(MediaType.parse(FileUtils.MIME_TYPE_TEXT), descriptionString);
    }

}