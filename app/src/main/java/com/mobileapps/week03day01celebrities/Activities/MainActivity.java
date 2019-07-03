package com.mobileapps.week03day01celebrities.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.mobileapps.week03day01celebrities.DataBase.CelebrityDataBaseHelper;
import com.mobileapps.week03day01celebrities.Models.Celebrity;
import com.mobileapps.week03day01celebrities.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void addCelebrity(View view)
    {
        Intent intent = new Intent(this, NewCelebrityActivity.class);
        startActivity(intent);
    }

    public void allTheCelebrities(View view)
    {
        Intent intent = new Intent(this,CelebrityListActivity.class);
        startActivity(intent);
    }

    public void allTheFavoriteCelebrities(View view)
    {
        Intent intent = new Intent(this,CelebrityListActivity.class);
        intent.putExtra("favorite",true);
        startActivity(intent);
    }

    public void openFile(View view)
    {
        Intent intent = new Intent(this,FileStorageActivity.class);
        startActivity(intent);
    }

}
