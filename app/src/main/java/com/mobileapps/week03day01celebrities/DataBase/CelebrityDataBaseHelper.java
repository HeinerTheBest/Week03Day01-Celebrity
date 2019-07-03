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

import static com.mobileapps.week03day01celebrities.DataBase.CelebrityDataBaseContract.CELEBRITY_TABLE_CREATE;
import static com.mobileapps.week03day01celebrities.DataBase.CelebrityDataBaseContract.CELEBRITY_TABLE_NAME;
import static com.mobileapps.week03day01celebrities.DataBase.CelebrityDataBaseContract.DATABASE_NAME;
import static com.mobileapps.week03day01celebrities.DataBase.CelebrityDataBaseContract.DATABASE_VERSION;
import static com.mobileapps.week03day01celebrities.DataBase.CelebrityDataBaseContract.KEY_ID;
import static com.mobileapps.week03day01celebrities.DataBase.CelebrityDataBaseContract.KEY_FIRST_NAME;
import static com.mobileapps.week03day01celebrities.DataBase.CelebrityDataBaseContract.KEY_IS_ALIVE;
import static com.mobileapps.week03day01celebrities.DataBase.CelebrityDataBaseContract.KEY_IS_FAVORITE;
import static com.mobileapps.week03day01celebrities.DataBase.CelebrityDataBaseContract.KEY_MOST_POPULAR_MOVIE;
import static com.mobileapps.week03day01celebrities.DataBase.CelebrityDataBaseContract.KEY_LAST_NAME;
import static com.mobileapps.week03day01celebrities.DataBase.CelebrityDataBaseContract.KEY_LAST_SCANDAL;
import static com.mobileapps.week03day01celebrities.DataBase.CelebrityDataBaseContract.KEY_PICTURE;


public class CelebrityDataBaseHelper extends SQLiteOpenHelper
{
    final static String TAG = CelebrityDataBaseHelper.class.getSimpleName();



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
        contentValues.put(KEY_FIRST_NAME,         celebrity.getFirstName());
        contentValues.put(KEY_LAST_NAME,          celebrity.getLastName());
        contentValues.put(KEY_MOST_POPULAR_MOVIE, celebrity.getMostPopularMovie());
        contentValues.put(KEY_IS_ALIVE,           celebrity.isAlive());
        contentValues.put(KEY_LAST_SCANDAL,       celebrity.getMostRecentScandal());
        contentValues.put(KEY_IS_FAVORITE,        celebrity.isFavorite());
        contentValues.put(KEY_PICTURE,            celebrity.getPicture());

        final long id = database.insert(CELEBRITY_TABLE_NAME, null, contentValues);
        database.close();
        Log.d("Heiner ","We inserted "+celebrity.getFirstName()+"with id = "+id);
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

    /*public int deleteByCategory(String category)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        final int itemsDeleted = database.delete(CELEBRITY_TABLE_NAME,  KEY_CATEGORY + " =? ", new String[]{String.valueOf(category)});
        database.close();
        return itemsDeleted;
    }*/

    //Updates
    public int updateCelebrityByID(Celebrity celebrity) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(KEY_FIRST_NAME,         celebrity.getFirstName());
        contentValues.put(KEY_LAST_NAME,          celebrity.getLastName());
        contentValues.put(KEY_MOST_POPULAR_MOVIE, celebrity.getMostPopularMovie());
        contentValues.put(KEY_IS_ALIVE,           celebrity.isAlive());
        contentValues.put(KEY_LAST_SCANDAL,       celebrity.getMostRecentScandal());
        contentValues.put(KEY_IS_FAVORITE,        celebrity.isFavorite());
        contentValues.put(KEY_PICTURE,            celebrity.getPicture());


        final int numberOfRowsUpdated = database.update(CELEBRITY_TABLE_NAME,
                contentValues, // new values to insert
                KEY_ID + " = ?",
                new String[]{String.valueOf(celebrity.getId())});

        database.close();

        return numberOfRowsUpdated;
    }

   /* public int updateCelebrityCategory(String oldCategory, String newCategory) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(KEY_CATEGORY, newCategory);

        final int numberOfRowsUpdated = database.update(CELEBRITY_TABLE_NAME,
                contentValues, // new values to insert
                KEY_CATEGORY + " = ?",
                new String[]{String.valueOf(oldCategory)});

        database.close();

        return numberOfRowsUpdated;
    }*/


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
                String  id                =                    cursor.getString(cursor.getColumnIndex(KEY_ID))                ;
                String  firstName         =                    cursor.getString(cursor.getColumnIndex(KEY_FIRST_NAME))        ;
                String  lastName          =                    cursor.getString(cursor.getColumnIndex(KEY_LAST_NAME))         ;
                String  mostPopularMovie  =                    cursor.getString(cursor.getColumnIndex(KEY_MOST_POPULAR_MOVIE));
                boolean isAlive           = Boolean.getBoolean(cursor.getString(cursor.getColumnIndex(KEY_IS_ALIVE)))         ;
                String  lastScandal       =                    cursor.getString(cursor.getColumnIndex(KEY_LAST_SCANDAL))      ;
                boolean isFavorite        = Boolean.getBoolean(cursor.getString(cursor.getColumnIndex(KEY_IS_FAVORITE)))      ;
                byte[]  picture           =                    cursor.getBlob  (cursor.getColumnIndex(KEY_PICTURE))           ;

                returnList.add(new Celebrity(id, firstName,lastName,mostPopularMovie,isAlive,lastScandal,isFavorite,picture));
                Log.d("Heiner","Adding to the returnList in Helper to "+firstName+" with id "+id);
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return returnList;
    }

    public ArrayList<Celebrity> getAllFavoriteCelebrity() {
        SQLiteDatabase database = this.getReadableDatabase();
        ArrayList<Celebrity> returnList = new ArrayList<>();

        Cursor cursor = database.rawQuery(CelebrityDataBaseContract.getIfIsFavorite(),null);

        if (cursor.moveToFirst()) {
            do {
                String  id                =                    cursor.getString(cursor.getColumnIndex(KEY_ID))                ;
                String  firstName         =                    cursor.getString(cursor.getColumnIndex(KEY_FIRST_NAME))        ;
                String  lastName          =                    cursor.getString(cursor.getColumnIndex(KEY_LAST_NAME))         ;
                String  mostPopularMovie  =                    cursor.getString(cursor.getColumnIndex(KEY_MOST_POPULAR_MOVIE));
                boolean isAlive           = Boolean.getBoolean(cursor.getString(cursor.getColumnIndex(KEY_IS_ALIVE)))         ;
                String  lastScandal       =                    cursor.getString(cursor.getColumnIndex(KEY_LAST_SCANDAL))      ;
                boolean isFavorite        = Boolean.getBoolean(cursor.getString(cursor.getColumnIndex(KEY_IS_FAVORITE)))      ;
                byte[]  picture           =                    cursor.getBlob  (cursor.getColumnIndex(KEY_PICTURE))           ;

                returnList.add(new Celebrity(id, firstName,lastName,mostPopularMovie,isAlive,lastScandal,isFavorite,picture));
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return returnList;
    }

   /* public HashSet<String> getAllCategory() {
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
    }*/




    public Celebrity getCelebrity(String idToFInd) {

        SQLiteDatabase database = this.getReadableDatabase();
        Celebrity returnCelebrity = new Celebrity();

        Cursor cursor = database.rawQuery(CelebrityDataBaseContract.getById(idToFInd),null);

        if (cursor.moveToFirst()) {

            String  id                =                    cursor.getString(cursor.getColumnIndex(KEY_ID))                ;
            String  firstName         =                    cursor.getString(cursor.getColumnIndex(KEY_FIRST_NAME))        ;
            String  lastName          =                    cursor.getString(cursor.getColumnIndex(KEY_LAST_NAME))         ;
            String  mostPopularMovie  =                    cursor.getString(cursor.getColumnIndex(KEY_MOST_POPULAR_MOVIE));
            boolean isAlive           = Boolean.getBoolean(cursor.getString(cursor.getColumnIndex(KEY_IS_ALIVE)))         ;
            String  lastScandal       =                    cursor.getString(cursor.getColumnIndex(KEY_LAST_SCANDAL))      ;
            boolean isFavorite        = Boolean.getBoolean(cursor.getString(cursor.getColumnIndex(KEY_IS_FAVORITE)))      ;
            byte[]  picture           =                    cursor.getBlob  (cursor.getColumnIndex(KEY_PICTURE))           ;

            returnCelebrity = new Celebrity(id, firstName,lastName,mostPopularMovie,isAlive,lastScandal,isFavorite,picture);
        }
        cursor.close();
        database.close();
        return returnCelebrity;
    }






}
