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


        CelebrityDataBaseHelper mDB = new CelebrityDataBaseHelper(this);

        List<Celebrity> celebrities= new ArrayList<>();

        /*Celebrity cb1 = new Celebrity("Heiner","Sport","July 14","Venezuela","Heiner is an Amazing person");
        mDB.insertCelebrity(cb1);

        Celebrity cb2 = new Celebrity("Maria","Movie","July 14","Venezuela","Heiner is an Amazing person");
        mDB.insertCelebrity(cb2);


        */

        //mDB.updateCelebrityByID(new Celebrity("7","Heiner new","Sport","May 16","Venezuela","This is update"));



        celebrities = mDB.getAllCelebrity();

        /*for (Celebrity celebrity : celebrities)
        {
            Log.d("Heiner","Id "+ celebrity.getId());
            Log.d("Heiner","Name "+ celebrity.getFirstName());
            Log.d("Heiner","Category "+ celebrity.getCategory());
            Log.d("Heiner","Birth Date "+ celebrity.getBirthDate());
            Log.d("Heiner","Born Country "+ celebrity.getBornCountry());
            Log.d("Heiner","Bio "+ celebrity.getBio());
        }*/

       // mDB.updateCelebrityCategory("Movie","Suuuper Movies");

        /*HashSet<String> c;
        c = mDB.getAllCategory();
        for (String category:c)
        {
            Log.d("Heiner","Category list --> "+category);
        }*/

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
}
