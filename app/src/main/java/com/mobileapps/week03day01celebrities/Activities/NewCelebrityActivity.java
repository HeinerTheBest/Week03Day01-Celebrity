package com.mobileapps.week03day01celebrities.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.mobileapps.week03day01celebrities.DataBase.CelebrityDataBaseHelper;
import com.mobileapps.week03day01celebrities.Models.Celebrity;
import com.mobileapps.week03day01celebrities.R;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class NewCelebrityActivity extends AppCompatActivity {

    Button btnAdd,btnEdit,btnSave,btnCancel;
    Celebrity celebrity;
    EditText etFirstName, etLastName,etMostPopularMovie,etMostRecentScandal;
    ImageView isFavorite,btnRight,btnLeft,imgDemo;
    RadioButton rbDied, rbLive;


    int position = 0;

    Boolean favorite = false;
    ArrayList<String> pictures = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_celebrity);

        addPictures();

        Intent intent = getIntent();

        String id = intent.getStringExtra("celebrity_id");

        isFavorite = findViewById(R.id.btnFavorite);
        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etMostPopularMovie = findViewById(R.id.etMostPopularMovie);
        etMostRecentScandal = findViewById(R.id.etMostRecentScandal);
        imgDemo = findViewById(R.id.imgPictureDemo);
        btnRight = findViewById(R.id.btnRight);
        btnLeft = findViewById(R.id.btnLeft);

        rbLive = findViewById(R.id.rbLive);
        rbDied = findViewById(R.id.rbDead);

        btnAdd = findViewById(R.id.btnAdd);
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);


        if(id!=null)
        {
            populateData(id);
            btnEdit.setVisibility(View.VISIBLE);

        }
        else
        {
            btnAdd.setVisibility(View.VISIBLE);

        }

    }



    private void addPictures()
    {
        pictures.add("bass_guitar");
        pictures.add("girl");
        pictures.add("iron_man");
        pictures.add("spiderman");
        pictures.add("super_girl");
        pictures.add("super_man");
        pictures.add("wolverine");

    }

    public void favorite(View view)
    {
        if(favorite) {
            favorite = false;
            isFavorite.setImageResource(R.drawable.favorite_off);
        }
        else {
            favorite = true;
            isFavorite.setImageResource(R.drawable.favorite_on);
        }
    }

    private void populateData(String id)
    {
        celebrity = new CelebrityDataBaseHelper(this).getCelebrity(id);

        etFirstName.setText(celebrity.getFirstName());
        etLastName.setText(celebrity.getLastName());
        etMostPopularMovie.setText(celebrity.getMostPopularMovie());
        etMostRecentScandal.setText(celebrity.getMostRecentScandal());
    }

    public void addNewCelebrity(View view)
    {
        CelebrityDataBaseHelper dataBaseHelper = new CelebrityDataBaseHelper(this);

        String  firstName     = etFirstName.getText().toString();
        String  lastName      = etLastName.getText().toString();
        String  popularMovie  = etMostPopularMovie.getText().toString();
        boolean isAlive       = rbLive.isChecked();
        String  recentScandal = etMostRecentScandal.getText().toString();
        boolean fav           = favorite;
        byte[]  picture       = getThePictureInByte();

        dataBaseHelper.insertCelebrity(new Celebrity(firstName,lastName,popularMovie,isAlive,recentScandal,fav,picture));
        clean();
    }

    private void clean()
    {
        etFirstName.setText("");
        etLastName.setText("");
        etMostPopularMovie.setText("");
        etMostRecentScandal.setText("");
    }

    public void btnEdit(View view)
    {
        btnEdit.setVisibility(View.GONE);
        btnCancel.setVisibility(View.VISIBLE);
        btnSave.setVisibility(View.VISIBLE);

        setViewsEnabled(true);
    }

    public void setViewsEnabled(boolean enabled)
    {
        etFirstName.setEnabled(enabled);
        etLastName.setEnabled(enabled);
        etMostPopularMovie.setEnabled(enabled);
        etMostRecentScandal.setEnabled(enabled);
    }

    public void btnCancel(View view)
    {
        btnEdit.setVisibility(View.VISIBLE);
        btnCancel.setVisibility(View.GONE);
        btnSave.setVisibility(View.GONE);
    }

    public void btnUpdate(View view)
    {
        CelebrityDataBaseHelper dataBaseHelper = new CelebrityDataBaseHelper(this);
        String id = String.valueOf(celebrity.getId());
        String firstName = etFirstName.getText().toString();
        String lastName = etLastName.getText().toString();
        String movie = etMostPopularMovie.getText().toString();
        String scandal =etMostRecentScandal.getText().toString();


       // dataBaseHelper.updateCelebrityByID(new Celebrity(id,name,category,birth,born,bio));

        btnEdit.setVisibility(View.VISIBLE);
        btnCancel.setVisibility(View.GONE);
        btnSave.setVisibility(View.GONE);

        setViewsEnabled(false);
    }

    public void changeToTheLeft(View view)
    {
        if(position==0)
        {
            position = pictures.size()-1;
        }
        else
        {
            position--;
        }

        int id = getResources().getIdentifier(pictures.get(position), "drawable",getPackageName());
        imgDemo.setImageResource(id);
    }

    public void changeToTheRight(View view)
    {
        if(position==pictures.size()-1)
        {
            position = 0;
        }
        else
        {
            position++;
        }

        int id = getResources().getIdentifier(pictures.get(position), "drawable",getPackageName());
        imgDemo.setImageResource(id);
    }

    public byte[] getThePictureInByte()
    {
        Drawable d = imgDemo.getDrawable(); // the drawable (Captain Obvious, to the rescue!!!)
        Bitmap bitmap = ((BitmapDrawable)d).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        return stream.toByteArray();
    }

}
