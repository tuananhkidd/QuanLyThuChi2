package com.example.lavotinh.quanlythuchi.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.lavotinh.quanlythuchi.model.work;

import java.util.ArrayList;

/**
 * Created by tuaan on 9/12/2017.
 */

public class DBManager extends SQLiteOpenHelper{
    public static final String DBNAME = "MANAGERMONEY_DATABASE";
    public static final int VERSION = 1;

    public static final String WORKTABLE = "WORK";
    public static String ID = "id";
    public static final String CONTENT = "content";
    public static final String SUMMONEY = "money";
    public static final String DATE = "date";
    public static final String TIME = "time";


    public String sqlCreate = "CREATE TABLE " + WORKTABLE + " ( "
                        + ID + " integer primary key , "
                        + CONTENT + " TEXT , "
                        + SUMMONEY + " integer , "
                        + DATE  + " TEXT ,"
                        + TIME + " TEXT )";

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

    public void addWork(work w){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put(ID,w.getID());
        content.put(CONTENT,w.getName());
        content.put(SUMMONEY,w.getSumOfMoney());
        content.put(DATE,w.getDate());
        content.put(TIME,w.getTime());

        db.insert(WORKTABLE,null,content);
        db.close();
    }

    public ArrayList<work> loadlistWork(){
        ArrayList<work> list = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();

        String sql = "SELECT * FROM " + WORKTABLE;

        Cursor cursor = db.rawQuery(sql,null);

        if(cursor.moveToFirst()){
            do{
                list.add(new work(cursor.getInt(0),cursor.getString(1),cursor.getInt(2),cursor.getString(3),cursor.getString(4)));
            }while (cursor.moveToNext());
        }

        db.close();

        return list;
    }

    public int update(work w){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put(ID,w.getID());
        content.put(CONTENT,w.getName());
        content.put(SUMMONEY,w.getSumOfMoney());
        content.put(DATE,w.getDate());
        content.put(TIME,w.getTime());

        return db.update(WORKTABLE,content,ID = " ? ",new String[]{w.getID()+""});
    }

    public int delWork(work w){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(WORKTABLE,ID = " ? ",new String[]{w.getID()+""});
    }

}
