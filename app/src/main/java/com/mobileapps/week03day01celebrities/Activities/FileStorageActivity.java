package com.mobileapps.week03day01celebrities.Activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.mobileapps.week03day01celebrities.R;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FileStorageActivity extends AppCompatActivity {

    private final String FILE_NAME = "celebrity_file.txt";
    EditText etTextForFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_storage);

        etTextForFile = findViewById(R.id.etFileTest);

        try {
            readFromFile();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this,"Problem to open the file",Toast.LENGTH_SHORT);
        }

    }


    public void writeToFIle(String text) throws Exception
    {
        FileOutputStream fileOutputStream = openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
        outputStreamWriter.write(text);
        outputStreamWriter.close();
        fileOutputStream.close();
    }

    public void save(View view)
    {
        try {
            writeToFIle(etTextForFile.getText().toString());
            finish();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this,"Problem for save the file",Toast.LENGTH_SHORT);
        }
    }

    public void cancel(View view)
    {
        finish();
    }

    public void readFromFile() throws Exception
    {
        String returnString = "";
        FileInputStream fileInputStream = openFileInput(FILE_NAME);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);

        char[] inputBuffer = new char[100];
        int charRead;
        while ((charRead=inputStreamReader.read(inputBuffer))>0)
        {
            String readString = String.copyValueOf(inputBuffer,0,charRead);
            returnString = String.format("%s%s",returnString,readString);
        }
        etTextForFile.setText(returnString);
    }

}
