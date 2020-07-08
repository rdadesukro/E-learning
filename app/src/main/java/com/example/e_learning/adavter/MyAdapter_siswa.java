package com.example.e_learning.adavter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.e_learning.R;
import com.example.e_learning.menu_detail_siswa;
import com.example.e_learning.menu_materi_A;
import com.example.e_learning.objekdata.data_mapel;

import java.util.ArrayList;

public class MyAdapter_siswa extends RecyclerView.Adapter<MyHolder_data> {

    Context c;
    ArrayList<data_mapel> objekDatas;

    public MyAdapter_siswa(Context c, ArrayList<data_mapel> objekDatas) {
        this.c = c;

        this.objekDatas = objekDatas;
    }




    @Override
    public MyHolder_data onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.model_siswa_cth,parent,false);
        MyHolder_data holder=new MyHolder_data(v);
        return holder;

    }

    @Override
    public void onBindViewHolder(MyHolder_data holder, int position) {


        final data_mapel s= objekDatas.get(position);
         holder.nama.setText(s.getNama());
        holder.nis.setText(s.getNis());
        holder.kls_new.setText(s.getKls());
        PicassoClient.downloadImage( c,"http://192.168.56.1/e-learning/foto/" + s.getFot_Sis(), holder.foto );





        //Menyisipkan tipe data_stok String ke dalam obyek bundle


   //   YoYo.with(Techniques.FadeInLeft).duration(700).playOn(holder.cardView);
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick() {
               openDetailActivity(s.getNis());
            }
        });



    }


    @Override
    public int getItemCount() {

        return objekDatas.size();
    }
    private void openDetailActivity(String id)
    {
        Intent i=new Intent(c, menu_detail_siswa.class);

        //PACK DATA
        i.putExtra("id",id);
        c.startActivity(i);
    }


}