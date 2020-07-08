package com.example.e_learning;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
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
import com.squareup.picasso.Picasso;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class menu_materi_A extends AppCompatActivity {
    private static final String TAG = menu_materi_A.class.getSimpleName();
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    int success;
    public final static String TAG_userneme = "username";
    public final static String TAG_ID = "nis";
    ImageView hapus;
    GridView myGridView;

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
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            Intent intent = new Intent(getApplicationContext(), menu_mapel.class);
            startActivity(intent);

            return true;
        } else {


            return super.onKeyDown(keyCode, event);
        }
    }

}