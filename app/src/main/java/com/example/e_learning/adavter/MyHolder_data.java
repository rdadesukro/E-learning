package com.example.e_learning.adavter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.e_learning.R;

public class MyHolder_data extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView mapel;
    public TextView nama;
    public TextView nis;
    public TextView kls;



    public TextView nilai,kls_new;
    public ImageView foto;

    ItemClickListener itemClickListener;
    CardView cardView;

    ItemLongClickListener itemLongClickListener;

    public MyHolder_data(View itemView) {
        super(itemView);
        mapel= (TextView) itemView.findViewById(R.id.txt_mapel);
        nama= (TextView) itemView.findViewById(R.id.txt_nama);
        nis= (TextView) itemView.findViewById(R.id.txt_nis);
        nilai= (TextView) itemView.findViewById(R.id.txt_nilai);
        kls= (TextView) itemView.findViewById(R.id.txt_kls);
        kls_new= (TextView) itemView.findViewById(R.id.txt_nilai);
        foto = (ImageView) itemView.findViewById(R.id.img_bg);
        itemView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        this.itemClickListener.onItemClick();
    }

    public void setItemClickListener(ItemClickListener itemClickListener)
    {
        this.itemClickListener=itemClickListener;
    }


    public void setOnItemLongClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener=itemClickListener;
    }
}