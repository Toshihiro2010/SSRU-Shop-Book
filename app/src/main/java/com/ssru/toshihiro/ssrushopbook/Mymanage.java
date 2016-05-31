package com.ssru.toshihiro.ssrushopbook;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Admin on 31/5/2559.
 */
public class Mymanage {

    private MyOpenHelper myOpenHelper;
    private SQLiteDatabase sqLiteDatabase;


    public Mymanage(Context context) {

        myOpenHelper = new MyOpenHelper(context);
        sqLiteDatabase = myOpenHelper.getWritableDatabase();


    }   //Constructor
}   //  Main    Class
