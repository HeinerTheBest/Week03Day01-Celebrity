package com.mobileapps.week03day01celebrities.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.mobileapps.week03day01celebrities.Models.Celebrity;

import java.util.ArrayList;
import java.util.HashSet;

import static com.mobileapps.week03day01celebrities.DataBase.CelebrityDataBaseContract.CELEBRITY_TABLE_NAME;
import static com.mobileapps.week03day01celebrities.DataBase.CelebrityDataBaseContract.KEY_BIO;
import static com.mobileapps.week03day01celebrities.DataBase.CelebrityDataBaseContract.KEY_BIRTH_DATE;
import static com.mobileapps.week03day01celebrities.DataBase.CelebrityDataBaseContract.KEY_BORN_COUNTRY;
import static com.mobileapps.week03day01celebrities.DataBase.CelebrityDataBaseContract.KEY_CATEGORY;
import static com.mobileapps.week03day01celebrities.DataBase.CelebrityDataBaseContract.KEY_ID;
import static com.mobileapps.week03day01celebrities.DataBase.CelebrityDataBaseContract.KEY_NAME;


public class CelebrityDataBaseHelper extends SQLiteOpenHelper
{
    final static String TAG = CelebrityDataBaseHelper.class.getSimpleName();
    final static String DATABASE_NAME = "celebrity_table";
    final static int DATABASE_VERSION = 1;





    // String array of columns.
    private static final String[] COLUMNS = {KEY_ID, KEY_NAME, KEY_CATEGORY, KEY_BIRTH_DATE, KEY_BORN_COUNTRY, KEY_BIO};

    //query for creating database
    private static final String CELEBRITY_TABLE_CREATE =
            "CREATE TABLE " +
                    CELEBRITY_TABLE_NAME + " (" +
                                                KEY_ID            + " INTEGER PRIMARY KEY, " + // will auto-increment if no value passed
                                                KEY_NAME          + " TEXT, " +
                                                KEY_CATEGORY      + " TEXT, " +
                                                KEY_BIRTH_DATE    + " TEXT, " +
                                                KEY_BORN_COUNTRY  + " TEXT, " +
                                                KEY_BIO           + " TEXT " +
                                            ");";




    public CelebrityDataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d(TAG, "Construct CelebrityDataBaseHelper");
    }


    @Override
    public void onCreate(SQLiteDatabase db)
    {
        Log.d(TAG,"Creating the table ");
        db.execSQL(CELEBRITY_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        Log.d(TAG,"Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
        if (oldVersion < newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + CELEBRITY_TABLE_NAME);
            onCreate(db);
        }
    }

    public long insertCelebrity(Celebrity celebrity)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME, celebrity.getName());
        contentValues.put(KEY_CATEGORY, celebrity.getCategory());
        contentValues.put(KEY_BIRTH_DATE, celebrity.getBirthDate());
        contentValues.put(KEY_BORN_COUNTRY, celebrity.getBornCountry());
        contentValues.put(KEY_BIO, celebrity.getBio());

        final long id = database.insert(CELEBRITY_TABLE_NAME, null, contentValues);
        database.close();
        return id;
    }



    //Deletes
    public int deleteByID(String id)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        final int itemsDeleted = database.delete(CELEBRITY_TABLE_NAME,  KEY_ID + " =? ", new String[]{String.valueOf(id)});
        database.close();
        return itemsDeleted;
    }

    public int deleteByCategory(String category)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        final int itemsDeleted = database.delete(CELEBRITY_TABLE_NAME,  KEY_CATEGORY + " =? ", new String[]{String.valueOf(category)});
        database.close();
        return itemsDeleted;
    }

    //Updates
    public int updateCelebrityByID(Celebrity celebrity) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(KEY_NAME, celebrity.getName());
        contentValues.put(KEY_CATEGORY, celebrity.getCategory());
        contentValues.put(KEY_BIRTH_DATE, celebrity.getBirthDate());
        contentValues.put(KEY_BORN_COUNTRY, celebrity.getBornCountry());
        contentValues.put(KEY_BIO, celebrity.getBio());


        final int numberOfRowsUpdated = database.update(CELEBRITY_TABLE_NAME,
                contentValues, // new values to insert
                KEY_ID + " = ?",
                new String[]{String.valueOf(celebrity.getId())});

        database.close();

        return numberOfRowsUpdated;
    }

    public int updateCelebrityCategory(String oldCategory, String newCategory) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(KEY_CATEGORY, newCategory);

        final int numberOfRowsUpdated = database.update(CELEBRITY_TABLE_NAME,
                contentValues, // new values to insert
                KEY_CATEGORY + " = ?",
                new String[]{String.valueOf(oldCategory)});

        database.close();

        return numberOfRowsUpdated;
    }


    //Count
    public long count()
    {
        SQLiteDatabase database = this.getReadableDatabase();
        final long count = DatabaseUtils.queryNumEntries(database, CELEBRITY_TABLE_NAME);
        database.close();
        return count;
    }


    //Get()
    public ArrayList<Celebrity> getAllCelebrity() {
        SQLiteDatabase database = this.getReadableDatabase();
        ArrayList<Celebrity> returnList = new ArrayList<>();

        Cursor cursor = database.rawQuery("SELECT * FROM "+ CELEBRITY_TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                String id = cursor.getString(cursor.getColumnIndex(KEY_ID));
                String name = cursor.getString(cursor.getColumnIndex(KEY_NAME));
                String category = cursor.getString(cursor.getColumnIndex(KEY_CATEGORY));
                String birth = cursor.getString(cursor.getColumnIndex(KEY_BIRTH_DATE));
                String born = cursor.getString(cursor.getColumnIndex(KEY_BORN_COUNTRY));
                String bio = cursor.getString(cursor.getColumnIndex(KEY_BIO));
                returnList.add(new Celebrity(id, name, category, birth, born, bio));
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return returnList;
    }

    public ArrayList<Celebrity> getAllCelebrityByCategory(String categoryToFind) {
        SQLiteDatabase database = this.getReadableDatabase();
        ArrayList<Celebrity> returnList = new ArrayList<>();

        Cursor cursor = database.rawQuery(CelebrityDataBaseContract.getByCategory(categoryToFind),null);

        if (cursor.moveToFirst()) {
            do {
                String id = cursor.getString(cursor.getColumnIndex(KEY_ID));
                String name = cursor.getString(cursor.getColumnIndex(KEY_NAME));
                String category = cursor.getString(cursor.getColumnIndex(KEY_CATEGORY));
                String birth = cursor.getString(cursor.getColumnIndex(KEY_BIRTH_DATE));
                String born = cursor.getString(cursor.getColumnIndex(KEY_BORN_COUNTRY));
                String bio = cursor.getString(cursor.getColumnIndex(KEY_BIO));
                returnList.add(new Celebrity(id, name, category, birth, born, bio));
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return returnList;
    }

    public HashSet<String> getAllCategory() {
        SQLiteDatabase database = this.getReadableDatabase();
        HashSet<String> returnList = new HashSet<>();

        Cursor cursor = database.rawQuery(CelebrityDataBaseContract.getAllCategory(),null);

        if (cursor.moveToFirst()) {
            do {

                returnList.add(cursor.getString(cursor.getColumnIndex(KEY_CATEGORY)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return returnList;
    }









}
