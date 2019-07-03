package com.mobileapps.week03day01celebrities.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.mobileapps.week03day01celebrities.Adapters.CelebritiesAdapter;
import com.mobileapps.week03day01celebrities.DataBase.CelebrityDataBaseHelper;
import com.mobileapps.week03day01celebrities.Models.Celebrity;
import com.mobileapps.week03day01celebrities.R;

import java.util.ArrayList;

public class CelebrityListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Celebrity> celebrities;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_celebrity_list);

        recyclerView = findViewById(R.id.rvCelebrities);

        getAllCelebrity();


    }

    private void getAllCelebrity()
    {
        Intent intent = getIntent();

        if(intent.getBooleanExtra("favorite",false))
        {
            celebrities = new CelebrityDataBaseHelper(this).getAllFavoriteCelebrity();
        }
        else
        {
            celebrities = new CelebrityDataBaseHelper(this).getAllCelebrity();
        }
        CelebritiesAdapter celebritiesAdapter = new CelebritiesAdapter(celebrities,this);
        recyclerView.setAdapter(celebritiesAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        getAllCelebrity();
    }
}
