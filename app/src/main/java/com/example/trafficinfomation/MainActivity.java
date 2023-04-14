package com.example.trafficinfomation;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //recyclerview Adapter 생성
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter();
        recyclerViewAdapter.setContext(this);

        //파일 읽기 후 recyclerViewAdapter에 데이터 전달
        InputStream inputStream = getResources().openRawResource(R.raw.cctv_list);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        bufferedReader.lines().forEach(s -> {
            StringTokenizer st = new StringTokenizer(s, " ");
            recyclerViewAdapter.addCctvList(new Cctv(st.nextToken(), st.nextToken()));
        });

        //recyclerView 생성
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);

    }
}