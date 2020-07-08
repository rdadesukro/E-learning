package com.example.e_learning;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.e_learning.app.AppConfig;
import com.example.e_learning.app.AppController;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class menu_quiz_1 extends AppCompatActivity {

    private mapel_1 db;
    private TextView txtnama, txtno, txttanggal, txtwaktu, txtsoal,txtnis;
    private ImageView img;
    private RadioGroup rg;
    private RadioButton rdA, rdB, rdC,rdD;
    private List<Soal> listSoal;
    private CounterClass mCountDownTimer;
    private int detik = 300000; // --> 10 menit
    private Button btnPrev, btnNext, btnSelesai;
    int jawabanYgDiPilih[] = null;
    int jawabanYgBenar[] = null;
    boolean cekPertanyaan = false;
    int urutanPertanyaan = 0;
    String noSalah = "";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    private String KEY_ID = "id_profil";
    TextView nilai;
    private String KEY_ID_USER = "id";
    private String KEY_NO = "no_hp";
    private static final String TAG = menu_quiz_1.class.getSimpleName();
    private String KEY_NIS = "nis";
    private String KEY_ID_QUIZ = "id_quiz";
    private String KEY_NILAI = "nilai";
    private String KEY_GURU = "id_guru";
    private String KEY_STATUS = "status";
    TextView mapel;
    private String KEY_MAPEL = "mapel";
    int total;
    int success;
    String tag_json_obj = "json_obj_req";
    EditText inputUser,inputnis;
    TextView sts;
    SharedPreferences sharedpreferences;
    public final static String TAG_userneme = "username";
    public final static String TAG_ID = "nis";
    ImageView hapus;
    public final static String TAG_level = "level";
    public static final String my_shared_preferences = "my_shared_preferences";
    Boolean session = false;
    public static final String session_status = "session_status";
    TextView id_guru;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz);
        db = new mapel_1(this);
        txtnama = (TextView) findViewById(R.id.textViewNama);
        txtnis = (TextView) findViewById(R.id.txt_nis);
        txtno = (TextView) findViewById(R.id.textViewHalaman);
        txttanggal = (TextView) findViewById(R.id.textViewTanggal);
        id_guru = (TextView) findViewById(R.id.txt_id_guru);
        sts = (TextView) findViewById(R.id.txt_sts);
        nilai = (TextView) findViewById(R.id.txt_nilai);
        mapel = (TextView) findViewById(R.id.txt_mapel);
        txtwaktu = (TextView) findViewById(R.id.textViewWaktu);
        txtsoal = (TextView) findViewById(R.id.textViewSoal);
        img = (ImageView) findViewById(R.id.gambarKuis);
        sharedpreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
        session = sharedpreferences.getBoolean(session_status, false);
        id = sharedpreferences.getString(TAG_ID, null);

        id_guru.setText(id);
        rg = (RadioGroup) findViewById(R.id.radioGroup1);
        rdA = (RadioButton) findViewById(R.id.radio0);
        rdB = (RadioButton) findViewById(R.id.radio1);
        rdC = (RadioButton) findViewById(R.id.radio2);
        rdD = (RadioButton) findViewById(R.id.radio3);
        btnPrev = (Button) findViewById(R.id.buttonPrev);
        btnNext = (Button) findViewById(R.id.buttonNext);
        btnSelesai = (Button) findViewById(R.id.buttonSelesai);
        final Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);
        txttanggal.setText(Integer.toString(day)+"-"+Integer.toString(month+1)+"-"+Integer.toString(year));

        listSoal = new ArrayList<Soal>();
        listSoal = db.getSoal();

        btnSelesai.setOnClickListener(klikSelesai);
        btnPrev.setOnClickListener(klikSebelum);
        btnNext.setOnClickListener(klikBerikut);
        //new GetSoal().execute();
        jawabanYgDiPilih = new int[listSoal.size()];
        java.util.Arrays.fill(jawabanYgDiPilih, -2);
        jawabanYgBenar = new int[listSoal.size()];
        java.util.Arrays.fill(jawabanYgBenar, -1);
        showInputUser();
    }

    private void showInputUser() {
        LayoutInflater mInflater = LayoutInflater.from(this);
        View v = mInflater.inflate(R.layout.nama_anda, null);

        final AlertDialog dialog = new AlertDialog.Builder(this).create();

        dialog.setView(v);
       // dialog.setTitle("Ketikkan Nama Anda");
        dialog.setCancelable(false);

        final Button btnOk = (Button) v.findViewById(R.id.buttonOK);
        final Button btn_batal = (Button) v.findViewById(R.id.btn_btl);

        inputUser = (EditText) v.findViewById(R.id.editTextNama);
        inputnis = (EditText) v.findViewById(R.id.editTextnis);
        Bundle b = getIntent().getExtras();
        inputUser.setText(b.getCharSequence("nama"));
        inputnis.setText(b.getCharSequence("id"));
        btnOk.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                if(inputUser.getText().toString().equals("")){
                    Toast.makeText(getBaseContext(), "Isi dulu", Toast.LENGTH_LONG).show();
                }else{
                    txtnama.setText(inputUser.getText().toString());
                    txtnis.setText(inputnis.getText().toString());
                    mulaiKuis();
                    dialog.dismiss();
                }

            }
        });

        dialog.show();
        btn_batal.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getApplication(),menu_utama.class));

            }
        });

    }

    private void mulaiKuis() {
        cler();
        setUpWaktu();
        setUpSoal();
    }

    private void setUpSoal() {
        Collections.shuffle(listSoal);
        this.tunjukanPertanyaan(0, cekPertanyaan);
    }

    private void cler() {
        mCountDownTimer = new CounterClass(detik, 1000);
        mCountDownTimer.cancel();
    }
    private void setUpWaktu() {
        mCountDownTimer = new CounterClass(detik, 1000);
        mCountDownTimer.start();
    }
    private void tunjukanPertanyaan(int urutan_soal_soal, boolean cekPertanyaan) {
        btnSelesai.setEnabled(false);
        try {
            rg.clearCheck();
            Soal soal = new Soal();

            soal = listSoal.get(urutan_soal_soal);
            String pertanyaan = soal.getSoal();
            if (jawabanYgBenar[urutan_soal_soal] == -1) {
                jawabanYgBenar[urutan_soal_soal] = soal.getJwban();
            }

            int gambar = soal.getGambar();
            txtsoal.setText(pertanyaan.toCharArray(), 0, pertanyaan.length());
            img.setImageResource(gambar);
            rg.check(-1);
            String jwb_a = soal.getPil_a();
            rdA.setText(jwb_a.toCharArray(), 0,
                    jwb_a.length());
            String jwb_b = soal.getPil_b();
            rdB.setText(jwb_b.toCharArray(), 0,
                    jwb_b.length());
            String jwb_c = soal.getPil_c();
            rdC.setText(jwb_c.toCharArray(), 0,
                    jwb_c.length());
//            String jwb_d = soal.getPil_d();
//            rdD.setText(jwb_d.toCharArray(), 0,
//                    jwb_d.length());


            Log.d("", jawabanYgDiPilih[urutan_soal_soal] + "");
            if (jawabanYgDiPilih[urutan_soal_soal] == 0)
                rg.check(R.id.radio0);
            if (jawabanYgDiPilih[urutan_soal_soal] == 1)
                rg.check(R.id.radio1);
            if (jawabanYgDiPilih[urutan_soal_soal] == 2)
                rg.check(R.id.radio2);

//            if (jawabanYgDiPilih[urutan_soal_soal] == 3)
//                rg.check(R.id.radio3);

            pasangLabelDanNomorUrut();

            if (urutan_soal_soal == (listSoal.size() - 1)){
                btnNext.setEnabled(false);
                btnSelesai.setEnabled(true);
            }

            if (urutan_soal_soal == 0)
                btnPrev.setEnabled(false);

            if (urutan_soal_soal > 0)
                btnPrev.setEnabled(true);

            if (urutan_soal_soal < (listSoal.size() - 1))
                btnNext.setEnabled(true);

        } catch (Exception e) {
            Log.e(this.getClass().toString(), e.getMessage(), e.getCause());
        }
    }
    public class CounterClass extends CountDownTimer {
        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            aturJawaban_nya();
            // hitung berapa yg benar
            int jumlahJawabanYgBenar = 0;
            int jawaban=0;

            int i;
            for ( i = 0; i < jawabanYgBenar.length; i++) {
                if ((jawabanYgBenar[i] != -1) && (jawabanYgBenar[i] == jawabanYgDiPilih[i]))
                    jumlahJawabanYgBenar++;
                jawaban=jawabanYgDiPilih[i];
                if(jawabanYgBenar[i] != jawabanYgDiPilih[i])
                    noSalah = noSalah+" " + Integer.toString(i+1);
            }
            if(noSalah == ""){
                noSalah = "Benar semua";
            }
            else{
                noSalah = "No yang salah"+noSalah;
            }
            total = jumlahJawabanYgBenar*10;
            AlertDialog tampilKotakAlert;
            tampilKotakAlert = new AlertDialog.Builder(menu_quiz_1.this).create();
            tampilKotakAlert.setTitle("Nilai");
            tampilKotakAlert.setMessage("Besdasdasdnar " +jumlahJawabanYgBenar + " dari "
                    + (listSoal.size() +" soal. "+noSalah+ " Jawaban yang benar " +jawabanYgBenar[i]+"Total nialai anda = "+total));
          // nilai.setText(total);
            tampilKotakAlert.setButton(AlertDialog.BUTTON_NEUTRAL, "Lagi",
                    new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            cekPertanyaan = false;
                            urutanPertanyaan = 0;
                            noSalah="";
                            java.util.Arrays.fill(jawabanYgDiPilih, -2);
                            menu_quiz_1.this.tunjukanPertanyaan(0,
                                    cekPertanyaan);
                            nilai.setText(total);
                        }
                    });

            tampilKotakAlert.setButton(AlertDialog.BUTTON_NEGATIVE, "Keluar",
                    new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            cekPertanyaan = false;
                            finish();
                        }
                    });

            tampilKotakAlert.show();
        }

        @SuppressLint("NewApi")
        @TargetApi(Build.VERSION_CODES.GINGERBREAD)

        public void onTick(long millisUntilFinished) {
            long millis = millisUntilFinished;
            String hms = String.format(
                    "%02d:%02d:%02d",
                    TimeUnit.MILLISECONDS.toHours(millis),
                    TimeUnit.MILLISECONDS.toMinutes(millis)
                            - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS
                            .toHours(millis)),
                    TimeUnit.MILLISECONDS.toSeconds(millis)
                            - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS
                            .toMinutes(millis)));
            txtwaktu.setText(hms);
        }
    }

    private View.OnClickListener klikSelesai = new View.OnClickListener() {
        public void onClick(View v) {
            aturJawaban_nya();
            // hitung berapa yg benar
            int jumlahJawabanYgBenar = 0;
            int jawaban=0;
           // int total= 0;
            int i;
            for (i = 0; i < jawabanYgBenar.length; i++) {
                if ((jawabanYgBenar[i] != -1) && (jawabanYgBenar[i] == jawabanYgDiPilih[i]))
                    jumlahJawabanYgBenar++;
                jawaban=jawabanYgDiPilih[i];
                if(jawabanYgBenar[i] != jawabanYgDiPilih[i])
                    noSalah = noSalah+" " + Integer.toString(i+1);
            }
            if(noSalah == ""){
                noSalah = "Benar semua";
            }
            else{
                noSalah = "No yang salah"+noSalah;
            }
             total = jumlahJawabanYgBenar*10;
            AlertDialog tampilKotakAlert;
            tampilKotakAlert = new AlertDialog.Builder(menu_quiz_1.this).create();
            tampilKotakAlert.setTitle("Nilai");
            tampilKotakAlert.setMessage("Benar " +jumlahJawabanYgBenar + " dari "
                    + (listSoal.size() +" soal. "+noSalah+" Jawaban yang benar " +jawabanYgBenar+ " Total nialai anda = "+total));
            nilai.setText(""+total);
            tampilKotakAlert.setButton(AlertDialog.BUTTON_NEUTRAL, "Lagi",
                    new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            cekPertanyaan = false;
                            urutanPertanyaan = 0;
                            noSalah="";
                            java.util.Arrays.fill(jawabanYgDiPilih, -2);
                            menu_quiz_1.this.tunjukanPertanyaan(0,
                                    cekPertanyaan);
                            txtwaktu.setText("dsdasd");

                           // nilai.setText(total);
                        }
                    });

            tampilKotakAlert.setButton(AlertDialog.BUTTON_NEGATIVE, "Simpan",
                    new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            simapan_quiz();
                            cekPertanyaan = false;
                            finish();
                        }
                    });

            tampilKotakAlert.show();

        }
    };

    private void aturJawaban_nya() {
        if (rdA.isChecked())
            jawabanYgDiPilih[urutanPertanyaan] = 0;
        if (rdB.isChecked())
            jawabanYgDiPilih[urutanPertanyaan] = 1;
        if (rdC.isChecked())
            jawabanYgDiPilih[urutanPertanyaan] = 2;

        Log.d("", Arrays.toString(jawabanYgDiPilih));
        Log.d("", Arrays.toString(jawabanYgBenar));

    }

    private View.OnClickListener klikBerikut = new View.OnClickListener() {
        public void onClick(View v) {
            aturJawaban_nya();
            urutanPertanyaan++;
            if (urutanPertanyaan >= listSoal.size())
                urutanPertanyaan = listSoal.size() - 1;

            tunjukanPertanyaan(urutanPertanyaan, cekPertanyaan);
        }
    };

    private View.OnClickListener klikSebelum = new View.OnClickListener() {
        public void onClick(View v) {
            aturJawaban_nya();
            urutanPertanyaan--;
            if (urutanPertanyaan < 0)
                urutanPertanyaan = 0;
            tunjukanPertanyaan(urutanPertanyaan, cekPertanyaan);
        }
    };

    private void pasangLabelDanNomorUrut() {
        txtno.setText("Soal ke-" + (urutanPertanyaan + 1) + " dari "
                + listSoal.size());
    }


    private void simapan_quiz() {
        //menampilkan progress dialog
        final ProgressDialog loading = ProgressDialog.show(this, "Uploading...", "Please wait...", false, false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppConfig.quiz,
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
                                Toast.makeText(menu_quiz_1.this,"SUKSES SIMPAN NILAI",Toast.LENGTH_LONG).show();
                                // Intent i = new Intent(menu_lapor_bencana.this, data_terkirim.class);
                                //  startActivity(i);
                                //tes();

                            } else {
                                Toast.makeText(menu_quiz_1.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
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
                        Toast.makeText(menu_quiz_1.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
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
                params.put(KEY_NIS, txtnis.getText().toString().trim());
                params.put(KEY_NILAI, nilai.getText().toString().trim());
                params.put(KEY_MAPEL, mapel.getText().toString().trim());
                params.put(KEY_STATUS, sts.getText().toString().trim());
                params.put(KEY_GURU, id_guru.getText().toString().trim());

                //kembali ke parameters
                Log.e(TAG, "" + params);
                return params;
            }
        };

        AppController.getInstance().addToRequestQueue(stringRequest, tag_json_obj);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            Intent intent = new Intent(getApplicationContext(), menu_quiz.class);
            startActivity(intent);

            return true;
        } else {


            return super.onKeyDown(keyCode, event);
        }
    }

}
