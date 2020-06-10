package com.example.e_learning.adavter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.e_learning.menu_materi_A;
import com.example.e_learning.objekdata.data_mapel;
import com.example.e_learning.R;

import java.util.ArrayList;

public class MyAdapter_mapel extends RecyclerView.Adapter<MyHolder_data> {

    Context c;
    ArrayList<data_mapel> objekDatas;

    public MyAdapter_mapel(Context c, ArrayList<data_mapel> objekDatas) {
        this.c = c;

        this.objekDatas = objekDatas;
    }




    @Override
    public MyHolder_data onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.model_mapel,parent,false);
        MyHolder_data holder=new MyHolder_data(v);
        return holder;

    }

    @Override
    public void onBindViewHolder(MyHolder_data holder, int position) {


        final data_mapel s= objekDatas.get(position);
         holder.mapel.setText(s.getMapel());
        PicassoClient.downloadImage( c,"http://192.168.43.48/e-learning/foto/" + s.getFoto(), holder.foto );





        //Menyisipkan tipe data_stok String ke dalam obyek bundle


   //   YoYo.with(Techniques.FadeInLeft).duration(700).playOn(holder.cardView);
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick() {
               openDetailActivity(s.getId(),s.getMapel(),s.getFoto());
            }
        });
        holder.setOnItemLongClickListener(new ItemClickListener() {
            @Override
            public void onItemClick() {
                openDetailActivity(s.getId(),s.getMapel(),s.getFoto());
            }
        });


    }


    @Override
    public int getItemCount() {

        return objekDatas.size();
    }
    private void openDetailActivity(String id, String mapel,String foto)
    {
        Intent i=new Intent(c, menu_materi_A.class);

        //PACK DATA
        i.putExtra("id",id);
        i.putExtra("mapel",mapel);

        i.putExtra("foto",foto);

        c.startActivity(i);
    }


}