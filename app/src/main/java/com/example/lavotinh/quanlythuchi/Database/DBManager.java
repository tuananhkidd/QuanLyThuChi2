package com.example.lavotinh.quanlythuchi.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by tuaan on 9/12/2017.
 */

public class DBManager extends SQLiteOpenHelper{
    public static final String DBNAME = "MANAGERMONEY_DATABASE";
    public static final int VERSION = 1;

    public static final String WORKTABLE = "WORK";
    public static final String ID = "id";
    public static final String CONTENT = "content";
    public static final String SUMMONEY = "money";
    public static final String DATETIME = "datetime";

    public String sqlCreate = "CREAT " + WORKTABLE + " ( "
                        + ID + " integer primary key , "
                        + CONTENT + " TEXT , "
                        + SUMMONEY + " integer , "
                        + DATETIME  + " TEXT )";

    public DBManager(Context context) {
        super(context, DBNAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(sqlCreate);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
