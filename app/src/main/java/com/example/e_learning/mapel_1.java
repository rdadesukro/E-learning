package com.example.e_learning;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class mapel_1 extends SQLiteOpenHelper {
    final static String DB_NAME = "db_bio";

    public mapel_1(Context context) {
        super(context, DB_NAME, null, 1);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS tbl_soal1(id INTEGER PRIMARY KEY AUTOINCREMENT, soal TEXT, pil_a TEXT, pil_b TEXT, pil_c TEXT, jwban INTEGER, img BLOB)";
        db.execSQL(sql);

        ContentValues values = new ContentValues();
        values.put("soal", "Nama komponen AC (air conditioners) yang berputar menekan gas refrigerant sehingga temperaturnya meningkat adalah… ?");
        values.put("pil_a", "Kondensor");
        values.put("pil_b", "kompresor");
        values.put("pil_c", "Refrigerant");
        values.put("jwban", "1");
        //  values.put("img", R.drawable.sapi);
        db.insert("tbl_soal1", "soal", values);

        values.put("soal", "Komponen AC yang berbentuk tabung dan ada kaca pengintai pada bagian atasnya adalah…?");
        values.put("pil_a", "Dryer");
        values.put("pil_b", "kondensor");
        values.put("pil_c", "Evaporator");
        values.put("jwban", "1");
        //  values.put("img", R.drawable.hidup);
        db.insert("tbl_soal1", "soal", values);

        values.put("soal", "Nama komponen AC yang berfungsi menyerap panas refrigerant dan mengubah gas refrigerant menjadi cairan adalah…?");
        values.put("pil_a", "Kondensor");
        values.put("pil_b", "kompresor");
        values.put("pil_c", "Refrigeran");
        values.put("jwban", "0");
        //  values.put("img", R.drawable.iakan);
        db.insert("tbl_soal1", "soal", values);

        values.put("soal", "Zat cair yang terdapat di dalam system AC adalah…?");
        values.put("pil_a", "Refrigeran");
        values.put("pil_b", "Dryer");
        values.put("pil_c", "kompresor");
        values.put("jwban", "0");
        //  values.put("img", R.drawable.ramb);
        db.insert("tbl_soal1", "soal", values);

        values.put("soal", "Alat yang berfungsi menyerap panas udara sehingga membuat udara menjadi digin adalah…?");
        values.put("pil_a", "Refrigeran");
        values.put("pil_b", "Dryer");
        values.put("pil_c", "Evaporator");
        values.put("jwban", "2");
        // values.put("img", R.drawable.ayam);
        db.insert("tbl_soal1", "soal", values);

        values.put("soal", "Urutan sirkulasi sistem AC yang benar adalah….?");
        values.put("pil_a", "kompresor, kondensor, receiver, expansion valve, evaporator");
        values.put("pil_b", "kompresor, receiver, kondensor, evaporator, expansion valve");
        values.put("pil_c", "kompresor, evaporator ,expansion valve, receiver,kondensor");
        values.put("jwban", "0");
        //  values.put("img", R.drawable.tebu);
        db.insert("tbl_soal1", "soal", values);

        values.put("soal", "Refriger yang berada antara evaporator dan kompresor adalah…?");
        values.put("pil_a", "Cair");
        values.put("pil_b", "Antara cair dan gas");
        values.put("pil_c", "Gas");
        values.put("jwban", "1");
        //  values.put("img", R.drawable.kelapa);
        db.insert("tbl_soal1", "soal", values);

        values.put("soal", "Keadaan tekanan dan temperatur refriger pada kondensor adalah….??");
        values.put("pil_a", "Sedang");
        values.put("pil_b", "Renda");
        values.put("pil_c", "Tinggi");
        values.put("jwban", "2");
        //  values.put("img", R.drawable.tikus);
        db.insert("tbl_soal1", "soal", values);


        values.put("soal",  "Proses pendinginan refrigerant pada kondensor melalui…..?");
        values.put("pil_a", "Gabungan antara air dan udara ");
        values.put("pil_b", "pendinginan air");
        values.put("pil_c", "Pendinginan udara");
        values.put("jwban", "0");
        // values.put("img", R.drawable.padi);
        db.insert("tbl_soal1", "soal", values);

        values.put("soal", "Kaca pengintai pada receiver berfungsi untuk….?");
        values.put("pil_a", "Melihat refrigeran");
        values.put("pil_b", "Melihat jumlah refrigerant");
        values.put("pil_c", "Melihat gelembung udarah yang ada pada refrigerant");
        values.put("jwban", "0");
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