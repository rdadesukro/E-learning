package com.example.e_learning;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class mapel_2 extends SQLiteOpenHelper {
    final static String DB_NAME = "db_2";

    public mapel_2(Context context) {
        super(context, DB_NAME, null, 1);
        // TODO Auto-generated constructor stub
        //ade
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS tbl_soal1(id INTEGER PRIMARY KEY AUTOINCREMENT, soal TEXT, pil_a TEXT, pil_b TEXT, pil_c TEXT, jwban INTEGER, img BLOB)";
        db.execSQL(sql);


        ContentValues values = new ContentValues();
        values.put("soal", ".Tune up engine adalah ");
        values.put("pil_a", "Mengganti komponen engine, dari komponen imitasi diganti komponen orisinil agar tenaga engine lebih kuat");
        values.put("pil_b", "Mengembalikan kinerja engine secara maksimal dengan memeriksa, menyetel, membersihkan dan mengganti komponen");
        values.put("pil_c", "Mengembalikan dan meningkatkan kinerja engine dengan mengganti semua komponen engine");
        values.put("jwban", "1");
        //  values.put("img", R.drawable.sapi);
        db.insert("tbl_soal1", "soal", values);

        values.put("soal", "Fungsi camshaft pada engine adalah ");
        values.put("pil_a", "Menggerakkan katup – katup yang terdapat pada kepala silinder");
        values.put("pil_b", "Menggerakan piston pada silinder");
        values.put("pil_c", "Penerus putaran ke flywheel");
        values.put("jwban", "0");
        //  values.put("img", R.drawable.hidup);
        db.insert("tbl_soal1", "soal", values);


        values.put("soal", "Di bawah ini adalah termasuk 5 sistem pada engine");
        values.put("pil_a", "Sistem penerangan dan wiring");
        values.put("pil_b", "Sistem pengapian");
        values.put("pil_c", "Sistem pendingin");
        values.put("jwban", "0");
        //  values.put("img", R.drawable.iakan);
        db.insert("tbl_soal1", "soal", values);

        values.put("soal", "Di bawah ini adalah fungsi piston, kecuali");
        values.put("pil_a", "Menghisap dan mengkompresi udara pada motor diesel");
        values.put("pil_b", "Menghisap dan mengkompresi udara pada motor bensin");
        values.put("pil_c", "Sebagai pembentuk ruang bak");
        values.put("jwban", "1");
        //  values.put("img", R.drawable.ramb);
        db.insert("tbl_soal1", "soal", values);

        values.put("soal", "Sumber arus listrik utama pada kendaraan adalah");
        values.put("pil_a", "Accu");
        values.put("pil_b", "Coil");
        values.put("pil_c", "Rotor");
        values.put("jwban", "0");
        // values.put("img", R.drawable.ayam);
        db.insert("tbl_soal1", "soal", values);

        values.put("soal", "Fungsi kumparan primer pada coil mobil adalah");
        values.put("pil_a", "Membuat magnet permanen pada coil");
        values.put("pil_b", "Membagi arus tegangan tinggi");
        values.put("pil_c", "Mengatur lamanya platina menutup");
        values.put("jwban", "1");
        //  values.put("img", R.drawable.tebu);
        db.insert("tbl_soal1", "soal", values);

        values.put("soal", "RFungsi centrifugal governor advancer adalah");
        values.put("pil_a", "Memajukan saat pengapian sesuai dengan putaran mesin");
        values.put("pil_b", "Memajukan saat pengapian sesuai dengan beban mesin");
        values.put("pil_c", "Membuat sudut dwell tepat");
        values.put("jwban", "0");
        //  values.put("img", R.drawable.kelapa);
        db.insert("tbl_soal1", "soal", values);

        values.put("soal", "Setelah melewati kumparan primer arus pengapian mengalir ke");
        values.put("pil_a", "Platina");
        values.put("pil_b", "Kondeksor");
        values.put("pil_c", "Busi");
        values.put("jwban", "0");
        //  values.put("img", R.drawable.tikus);
        db.insert("tbl_soal1", "soal", values);


        values.put("soal",  "Yang terjadi pada saat platina menutup adalah");
        values.put("pil_a", "Arus didistribusikan ke busi ");
        values.put("pil_b", "Terjadi ground");
        values.put("pil_c", "PArus masuk primer");
        values.put("jwban", "2");
        // values.put("img", R.drawable.padi);
        db.insert("tbl_soal1", "soal", values);

        values.put("soal", "Kapan terjadi induksi arus pengapian");
        values.put("pil_a", "Saat arus di fuse");
        values.put("pil_b", "Saat platina membuka");
        values.put("pil_c", "Saat arus sampai busi");
        values.put("jwban", "2");
        //  values.put("img", R.drawable.burung);
        db.insert("tbl_soal1", "soal", values);

        String sql2 = "CREATE TABLE IF NOT EXISTS tbl_gambar(id INTEGER PRIMARY KEY AUTOINCREMENT, nama TEXT, img BLOB)";
        db.execSQL(sql2);

        ContentValues v = new ContentValues();
        v.put("nama", "Manfaat hewan");
        // values.put("img", R.drawable.sapi);
        db.insert("tbl_gambar1", "nama", v);

        v.put("nama", "Manfaat hewan");
        //   values.put("img", R.drawable.burung);
        db.insert("tbl_gambar1", "nama", v);

    }

    public List<Soal> getSoal() {
        List<Soal> listSoal = new ArrayList<Soal>();
        String query = "select * from tbl_soal1";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Soal s = new Soal();
                s.setSoal(cursor.getString(1));
                s.setPil_a(cursor.getString(2));
                s.setPil_b(cursor.getString(3));
                s.setPil_c(cursor.getString(4));
                s.setJwban(cursor.getInt(5));
                s.setGambar(cursor.getInt(6));
                listSoal.add(s);
            } while (cursor.moveToNext());
        }
        return listSoal;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS tbl_soal1");
        db.execSQL("DROP TABLE IF EXISTS tbl_gambar1");
    }
}