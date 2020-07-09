package com.example.e_learning;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.e_learning.objekdata.BaseResponse;
import com.example.e_learning.server.MyInterface;
import com.example.e_learning.server.Retroserver_server;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class menu_add_data extends AppCompatActivity {

    EditText bab,data1;
    Button simpan,select;
    Uri tempUri;
    File file;
    String id_mapel;
    private static final int BUFFER_SIZE = 1024 * 2;
    private static final String IMAGE_DIRECTORY = "/demonuts_upload_gallery";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_add_data);
        Bundle b = getIntent().getExtras();
      id_mapel = (String) b.getCharSequence("id_mapel");
        Toast.makeText(this, ""+b.getCharSequence("id_mapel"), Toast.LENGTH_SHORT).show();
        bab=   (EditText) findViewById(R.id.edit_bab);
        data1=   (EditText) findViewById(R.id.edit_file);
        simpan=   (Button) findViewById(R.id.btn_simpan);
        select=   (Button) findViewById(R.id.btn_select);

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
//            new MaterialFilePicker()
//                    .withActivity(menu_uploud_berkas.this)
//                    .withRequestCode(FILE_PICKER_REQUEST_CODE)
//                    .withHiddenFiles(true)
//                    .withFilter(Pattern.compile(".*\\.pdf$"))
//                    .withTitle("Select PDF file")
//                    .start();
                    data1.setText("");
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

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (tempUri==null) {
                    Toast.makeText(menu_add_data.this, "Anda belum mengambil Foto", Toast.LENGTH_SHORT);


                    AlertDialog alertDialog = new AlertDialog.Builder(menu_add_data.this).create();
                    alertDialog.setTitle("Alert");
                    alertDialog.setMessage("Anda Belum Mengambil File!");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();


                }else if (bab.getText().toString().equals("")) {
                    Toast.makeText(menu_add_data.this, "Input BAB Mata Pelajaran", Toast.LENGTH_SHORT);


                    AlertDialog alertDialog = new AlertDialog.Builder(menu_add_data.this).create();
                    alertDialog.setTitle("Alert");
                    alertDialog.setMessage("Input BAB Mata Pelajaran!");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }
                else {
                    final AlertDialog pd_new = new AlertDialog.Builder(menu_add_data.this).create();

                    pd_new.setTitle("Simpan Data");
                    pd_new.setMessage("Mohon tunggu sedang memproses...");
                    pd_new.show();
                    pd_new.setCancelable(false);

                    //Toast.makeText(menu_uploud_berkas.this, "data", Toast.LENGTH_SHORT);
                    // image = getStringImage(decoded);

                    //  Log.i("data_aku", "onClick: "+file);
                    MyInterface api = Retroserver_server.getClient().create(MyInterface.class);
                    final RequestBody bab1 = createPartFromString(""+bab.getText().toString());
                    RequestBody id_data = createPartFromString(""+id_mapel);


                    RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);


//                                    RequestBody requestFile =
//                                            RequestBody.create(
//                                                    MediaType.parse(getContentResolver().getType(tempUri)),
//                                                    file
//                                            );

                    // MultipartBody.Part is used to send also the actual file name


                    MultipartBody.Part body =
                            MultipartBody.Part.createFormData("materi", file.getName(), requestFile);
                    Log.i("nama_file", "simpan_permohonan: "+body);

                    Call<BaseResponse> sendbio = api.simpan_berkas(
                            bab1,
                            id_data,
                            body);


                    // Toast.makeText(this, "image" + (i + 1), Toast.LENGTH_SHORT);
                    sendbio.enqueue(new Callback<BaseResponse>() {
                        @Override
                        public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {

                            try {
                                if (response.isSuccessful()) {
                                    String kode = response.body().getvalue();
                                    //   String value = response.body().getvalue();
                                    //  progress.dismiss();
                                    if (kode.equals("1")) {
                                        tempUri=null;
                                        pd_new.dismiss();
                                      //  pd_new.dismiss();
                                        file.delete();

                                        Toast.makeText(menu_add_data.this, "Berhasil", Toast.LENGTH_SHORT).show();
                                        data1.setText("");
                                        bab.setText("");



                                    }

                                    if (kode.equals("0")) {
                                       // pd_new.dismiss();
                                        //gagal("Gagal");
                                        pd_new.dismiss();
                                        Toast.makeText(menu_add_data.this, "Gagal", Toast.LENGTH_SHORT);
                                    }
                                }else {
                                    switch (response.code()) {
                                        case 404:
                                           // pd_new.dismiss();
                                          //  gagal("Gagal");
                                            Toast.makeText(menu_add_data.this, "not found", Toast.LENGTH_SHORT).show();
                                            break;
                                        case 500:
                                          //  pd_new.dismiss();
                                          //  gagal("server broken");
                                            Toast.makeText(menu_add_data.this, "server broken", Toast.LENGTH_SHORT).show();
                                            break;
                                        default:
                                            //pd_new.dismiss();
                                           // gagal("unknown error");
                                            Toast.makeText(menu_add_data.this, "unknown error", Toast.LENGTH_SHORT).show();
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
                                Toast.makeText(menu_add_data.this, "Jaringan Anda Bermasalah", Toast.LENGTH_SHORT).show();

                            }
                            else {
                               // pd_new.dismiss();
                              //  gagal("Server Donwn");
                                Toast.makeText(menu_add_data.this, "Server Donwn", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });





                }



            }
        });


    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            // Get the Uri of the selected file
            Uri uri = data.getData();

            String path = getFilePathFromURI(menu_add_data.this,uri);
            Log.d("ioooo",path);
            File file = new File(path);
//        displayFromFile(file);
//        edit_file.setText(""+file);
            int file_size = Integer.parseInt(String.valueOf(file.length()/1024));



            String val = ""+file;
            String extension = val.substring(val.lastIndexOf(".") + 1);
         //   tempUri = Uri.fromFile(new File(file.getAbsolutePath()));
            displayFromFile(file);
            data1.setText(""+file);
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

        super.onActivityResult(requestCode, resultCode, data);

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