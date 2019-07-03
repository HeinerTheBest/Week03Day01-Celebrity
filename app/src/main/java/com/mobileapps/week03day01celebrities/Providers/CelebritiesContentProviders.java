package com.mobileapps.week03day01celebrities.Providers;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.mobileapps.week03day01celebrities.DataBase.CelebrityDataBaseContract;
import com.mobileapps.week03day01celebrities.DataBase.CelebrityDataBaseHelper;

import static com.mobileapps.week03day01celebrities.DataBase.CelebrityDataBaseContract.CELEBRITY_TABLE_NAME;

public class CelebritiesContentProviders extends ContentProvider
{
    public static final int CELEBRITY = 69;
    public static final int CELEBRITY_ITEM = 313;
    CelebrityDataBaseHelper celebrityDataBaseHelper;
    UriMatcher uriMatcher = buildUriMatcher();



    @Override
    public boolean onCreate() {
        celebrityDataBaseHelper = new CelebrityDataBaseHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection,String selection,String[] selectionArgs, String sortOrder) {
        final SQLiteDatabase db = celebrityDataBaseHelper.getWritableDatabase();
        Cursor retCursor = null;
        switch(uriMatcher.match(uri)) {
            case CELEBRITY:
                retCursor = db.query(
                        CELEBRITY_TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            case CELEBRITY_ITEM:
                long _id = ContentUris.parseId(uri);
                retCursor = db.query(
                        CELEBRITY_TABLE_NAME,
                        projection,
                        CelebrityDataBaseContract.KEY_ID + " = ?",
                        new String[]{String.valueOf(_id)},
                        null,
                        null,
                        sortOrder
                );
                break;
        }
        return retCursor;
    }

    @Override
    public String getType(Uri uri) {
        switch(uriMatcher.match(uri)){
            case CELEBRITY:
                return CelebrityContentContract.CelebrityEntry.CONTENT_TYPE;
            case CELEBRITY_ITEM:
                return CelebrityContentContract.CelebrityEntry.CONTENT_ITEM_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri,ContentValues values)
    {
        SQLiteDatabase database = celebrityDataBaseHelper.getWritableDatabase();
        Uri returnUri;
        long id = database.insert(CELEBRITY_TABLE_NAME, null, values);
        if(id > 0) {
            returnUri = CelebrityContentContract.CelebrityEntry.buildZooAnimalUri(id);
        } else {
            throw new UnsupportedOperationException("Row Not Inserted!!!!!");
        }
        return returnUri;
    }

    @Override
    public int delete(Uri uri, String selection,String[] selectionArgs) {
        //zooDatabaseHelper.deleteSpeciesFromDatabase(selectionArgs[0]);
        SQLiteDatabase database = celebrityDataBaseHelper.getWritableDatabase();
        final int itemsDelete = database.delete(CELEBRITY_TABLE_NAME, selection, selectionArgs);
        database.close();
        return itemsDelete;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase database = celebrityDataBaseHelper.getWritableDatabase();
        final int itemsUpdated =
                database.update(CELEBRITY_TABLE_NAME, values, selection, selectionArgs);
        database.close();
        return itemsUpdated;
    }

    public static UriMatcher buildUriMatcher(){
        String content = CelebrityContentContract.CONTENT_AUTHORITY;
        // All paths to the UriMatcher have a corresponding code to return
        // when a match is found (the ints above).
        UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI(content, CelebrityContentContract.PATH_CELEBRITY, CELEBRITY);
        matcher.addURI(content, CelebrityContentContract.PATH_CELEBRITY + "/#", CELEBRITY_ITEM);
        return matcher;
    }

}
