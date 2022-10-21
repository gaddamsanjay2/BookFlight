package com.example.book_flight;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity3 extends AppCompatActivity {
    RecyclerView recyclerView;
    ModelClass modelClass;
    AdapterClass adapterClass;
    List<ModelClass> modelClassList=new ArrayList<>();

    ArrayList<ModelClass> modelClassArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        recyclerView=findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Cursor cursor=new HelperClass(this).readallData();
        modelClassArrayList=new ArrayList<>();

        while (cursor.moveToNext()){
            ModelClass model=new ModelClass(cursor.getString(1),cursor.getString(2));
            modelClassArrayList.add(model);
        }

        adapterClass=new AdapterClass(getApplicationContext(),modelClassArrayList);


        recyclerView.setAdapter(adapterClass);
//        loadHorizontalMethod();
    }
//    private void loadHorizontalMethod() {
//        modelClass=new ModelClass("sanjay","sai");
//        modelClassList.add(modelClass);
//        modelClass=new ModelClass("Raju","rk");
//        modelClassList.add(modelClass);
//        modelClass=new ModelClass("sanjay","rk");
//        modelClassList.add(modelClass);
//        modelClass=new ModelClass("Raju","rk");
//        modelClassList.add(modelClass);
//
//
//
//        adapterClass.notifyDataSetChanged();
//    }
}