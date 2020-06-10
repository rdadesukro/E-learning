package com.example.e_learning.data_parser;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.e_learning.adavter.MyAdapter_mapel;
import com.example.e_learning.adavter.MyAdapter_nilai;
import com.example.e_learning.objekdata.data_mapel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.android.volley.VolleyLog.TAG;

public class DataParser_nilai extends AsyncTask<Void,Void,Boolean> {

    Context c;
    String jsonData;
    RecyclerView rv;
    SwipeRefreshLayout swipeRefreshLayout;
    // ProgressDialog pd;
    ArrayList<data_mapel> objekDatas =new ArrayList<>();

    public DataParser_nilai(Context c, String jsonData, RecyclerView rv, SwipeRefreshLayout swipeRefreshLayout) {
        this.c = c;
        this.jsonData = jsonData;
        this.rv = rv;
        this.swipeRefreshLayout =swipeRefreshLayout;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        // pd=new ProgressDialog(c);
        //pd.setTitle("Mohon Tunggu");
        //pd.setMessage("Sedang Mengambil Data...");
        //pd.show();
    }

    @Override
    protected Boolean doInBackground(Void... params) {

        return this.parseData();
    }

    @Override
    protected void onPostExecute(Boolean parsed) {
        super.onPostExecute(parsed);

            swipeRefreshLayout.setRefreshing(false);

        // pd.dismiss();

        if(parsed)
        {
            //BIND
            Log.d(TAG, parsed.toString());
            MyAdapter_nilai adapter = new MyAdapter_nilai(c, objekDatas);
            rv.setAdapter(adapter);


        }else {
            Toast.makeText(c,"OPSSS....DATA GAGAL DI AMBIL", Toast.LENGTH_SHORT).show();

        }
    }

    private Boolean parseData()
    {
        try
        {
            JSONArray ja=new JSONArray(jsonData);
            JSONObject jo;

            objekDatas.clear();
            data_mapel objekData;

            for (int i=0;i<ja.length();i++)
            {
                jo=ja.getJSONObject(i);

                String id=jo.getString("id_quiz");
                String nis=jo.getString("nis");
                String ft=jo.getString("foto");
                String nm=jo.getString("nama");
                String mpl=jo.getString("nama_mapel");
                String nilai=jo.getString("nilai");
                String kls=jo.getString("kelas");


                objekData =new data_mapel();

                objekData.setId(id);
                objekData.setNis(nis);
                objekData.setNama(nm);
                objekData.setNilai(nilai);
                objekData.setMapel(mpl);
                objekData.setFoto(ft);
                objekData.setKls(kls);
                objekDatas.add(objekData);

            }

            return true;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}