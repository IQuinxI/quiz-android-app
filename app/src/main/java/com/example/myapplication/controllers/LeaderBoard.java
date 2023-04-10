package com.example.myapplication.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.myapplication.R;

import java.util.ArrayList;

public class LeaderBoard extends AppCompatActivity {

//    private ListView listViewScores;
//
//    ArrayList<String> data = new ArrayList<String>();
//    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);

//        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
//        setListAdapter(adapter);
//        addItem();
    }

//    public void addItem() {
//        data.add("Hello");
//        adapter.notifyDataSetChanged();
//    }
}