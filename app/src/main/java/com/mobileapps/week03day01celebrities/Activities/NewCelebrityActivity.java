package com.mobileapps.week03day01celebrities.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mobileapps.week03day01celebrities.DataBase.CelebrityDataBaseHelper;
import com.mobileapps.week03day01celebrities.Models.Celebrity;
import com.mobileapps.week03day01celebrities.R;

public class NewCelebrityActivity extends AppCompatActivity {

    Button btnAdd,btnEdit,btnSave,btnCancel;
    Celebrity celebrity;
    EditText etName, etCategory,etBirth,etBorn,etBio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_celebrity);


        Intent intent = getIntent();

        String id = intent.getStringExtra("celebrity_id");


        etName = findViewById(R.id.etName);
        etCategory = findViewById(R.id.etCategory);
        etBirth = findViewById(R.id.etBirth);
        etBorn = findViewById(R.id.etBornCountry);
        etBio = findViewById(R.id.etBio);
        btnAdd = findViewById(R.id.btnAdd);
        btnEdit = findViewById(R.id.btnEdit);
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

    private void populateData(String id)
    {
        celebrity = new CelebrityDataBaseHelper(this).getCelebrity(id);

        etName.setText(celebrity.getName());
        etName.setEnabled(false);
        etCategory.setText(celebrity.getCategory());
        etCategory.setEnabled(false);
        etBirth.setText(celebrity.getBirthDate());
        etBirth.setEnabled(false);
        etBorn.setText(celebrity.getBornCountry());
        etBorn.setEnabled(false);
        etBio.setText(celebrity.getBio());
        etBio.setEnabled(false);
    }

    public void addNewCelebrity(View view)
    {
        CelebrityDataBaseHelper dataBaseHelper = new CelebrityDataBaseHelper(this);
        String name = etName.getText().toString();
        String category = etCategory.getText().toString();
        String birth = etBirth.getText().toString();
        String born =etBorn.getText().toString();
        String bio = etBio.getText().toString();

        dataBaseHelper.insertCelebrity(new Celebrity(name,category,birth,born,bio));
        clean();
    }

    private void clean()
    {
        etName.setText("");
        etCategory.setText("");
        etBirth.setText("");
        etBorn.setText("");
        etBio.setText("");
    }

    public void btnEdit(View view)
    {
        btnEdit.setVisibility(View.GONE);
        btnCancel.setVisibility(View.VISIBLE);
        btnSave.setVisibility(View.VISIBLE);

        etName.setEnabled(true);
        etCategory.setEnabled(true);
        etBirth.setEnabled(true);
        etBorn.setEnabled(true);
        etBio.setEnabled(true);
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
        String name = etName.getText().toString();
        String category = etCategory.getText().toString();
        String birth = etBirth.getText().toString();
        String born =etBorn.getText().toString();
        String bio = etBio.getText().toString();
        dataBaseHelper.updateCelebrityByID(new Celebrity(id,name,category,birth,born,bio));

        btnEdit.setVisibility(View.VISIBLE);
        btnCancel.setVisibility(View.GONE);
        btnSave.setVisibility(View.GONE);

        etName.setEnabled(false);
        etCategory.setEnabled(false);
        etBirth.setEnabled(false);
        etBorn.setEnabled(false);
        etBio.setEnabled(false);
    }
}
