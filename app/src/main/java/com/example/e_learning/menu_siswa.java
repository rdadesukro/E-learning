package com.example.e_learning;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.e_learning.app.AppConfig;
import com.example.e_learning.data_parser.Downloader_mapel;
import com.example.e_learning.data_parser.Downloader_siswa;

public class menu_siswa extends AppCompatActivity {
    TextView nis;
    SearchView sv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_siswa);


        nis=   (TextView) findViewById(R.id.txt_nama);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar()!=null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle b = getIntent().getExtras();
//        nis.setText(b.getCharSequence("id"));

        final RecyclerView rv = (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setItemAnimator(new DefaultItemAnimator());
        sv= (SearchView) findViewById(R.id.sv);
        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipelayout);

        new Downloader_siswa( menu_siswa.this, AppConfig.siswa , rv, swipeRefreshLayout ).execute();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new Downloader_siswa( menu_siswa.this, AppConfig.siswa , rv, swipeRefreshLayout ).execute();
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new Downloader_siswa( menu_siswa.this, AppConfig.siswa , rv, swipeRefreshLayout ).execute();
            }
        });

    }
}
