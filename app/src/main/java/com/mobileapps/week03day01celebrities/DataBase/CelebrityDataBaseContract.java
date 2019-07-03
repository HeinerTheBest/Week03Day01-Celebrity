package com.mobileapps.week03day01celebrities.DataBase;

public class CelebrityDataBaseContract
{
    // Column names...
    public static final String KEY_ID                 = "_id";
    public static final String KEY_FIRST_NAME         = "first_name";
    public static final String KEY_LAST_NAME          = "last_name";
    public static final String KEY_MOST_POPULAR_MOVIE = "most_popular_movie";
    public static final String KEY_IS_ALIVE           = "is_alive";
    public static final String KEY_LAST_SCANDAL       = "last_scandal";
    public static final String KEY_IS_FAVORITE        = "IS_favorite";
    public static final String KEY_PICTURE            = "picture";




    final static String DATABASE_NAME = "celebrity_table";
    final static int DATABASE_VERSION = 1;

    public static final String CELEBRITY_TABLE_NAME = "celebrity_entries";
    public static final String SELECT_ALL_QUERY = String.format("SELECT * FROM %s", CELEBRITY_TABLE_NAME);




    // String array of columns.
    public static final String[] COLUMNS = {KEY_ID, KEY_FIRST_NAME, KEY_LAST_NAME, KEY_MOST_POPULAR_MOVIE, KEY_IS_ALIVE, KEY_LAST_SCANDAL, KEY_IS_FAVORITE, KEY_PICTURE};

    //query for creating database
    public static final String CELEBRITY_TABLE_CREATE =
            "CREATE TABLE " +
                    CELEBRITY_TABLE_NAME + " (" +
                    KEY_ID            + " INTEGER PRIMARY KEY, " + // will auto-increment if no value passed
                    KEY_FIRST_NAME    + " TEXT, " +
                    KEY_LAST_NAME     + " TEXT, " +
                    KEY_MOST_POPULAR_MOVIE + " TEXT, " +
                    KEY_IS_ALIVE      + " TEXT, " +
                    KEY_LAST_SCANDAL  + " TEXT, " +
                    KEY_IS_FAVORITE   + " TEXT, " +
                    KEY_PICTURE       + " BLOB " +
                    ");";





















    public static  String getIfIsFavorite()
    {
        return String.format("%s WHERE %S = \"%s\"",
                SELECT_ALL_QUERY,KEY_IS_FAVORITE,"true");
    }

    public static  String getById(String id)
    {
        return String.format("%s WHERE %S = \"%s\"",
                SELECT_ALL_QUERY,KEY_ID,id);
    }


    public static  String getAllCategory()
    {
        return SELECT_ALL_QUERY;
    }
}
