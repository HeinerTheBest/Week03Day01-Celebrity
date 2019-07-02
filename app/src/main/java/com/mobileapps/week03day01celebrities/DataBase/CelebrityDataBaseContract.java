package com.mobileapps.week03day01celebrities.DataBase;

public class CelebrityDataBaseContract
{
    // Column names...
    public static final String KEY_ID           = "_id";
    public static final String KEY_NAME         = "name";
    public static final String KEY_CATEGORY     = "category";
    public static final String KEY_BIRTH_DATE   = "birth_date";
    public static final String KEY_BORN_COUNTRY = "born_country";
    public static final String KEY_BIO          = "bio";

    public static final String CELEBRITY_TABLE_NAME = "celebrity_entries";
    public static final String SELECT_ALL_QUERY = String.format("SELECT * FROM %s", CELEBRITY_TABLE_NAME);

    public static final String SELECT_CATEGORY_QUERY = String.format("SELECT %s FROM %s",KEY_CATEGORY, CELEBRITY_TABLE_NAME);





    public static  String getByCategory(String category)
    {
        return String.format("%s WHERE %S = \"%s\"",
                SELECT_ALL_QUERY,KEY_CATEGORY,category);
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
