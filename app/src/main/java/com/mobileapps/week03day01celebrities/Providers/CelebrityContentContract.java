package com.mobileapps.week03day01celebrities.Providers;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

public class CelebrityContentContract implements BaseColumns
{
    public static final String CONTENT_AUTHORITY =
            "com.mobileapps.week03day01celebrities.Providers";
    public static final Uri CONTENT_URI =
            Uri.parse(String.format("content://%s", CONTENT_AUTHORITY));
    public static final String PATH_CELEBRITY = "zoo_animals";


    public static class CelebrityEntry implements BaseColumns {
        public static final Uri Celebrity_CONTENT_URI =
                CONTENT_URI.buildUpon().appendPath(PATH_CELEBRITY).build();
        public static final String CONTENT_TYPE =
                "vnd.android.cursor.dir" + Celebrity_CONTENT_URI + "/" + PATH_CELEBRITY;
        public static final String CONTENT_ITEM_TYPE =
                "vnd.android.cursor.item" + Celebrity_CONTENT_URI + "/" + PATH_CELEBRITY;
        public static final Uri buildZooAnimalUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

}
