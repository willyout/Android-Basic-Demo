package com.willyout.BasicDemo.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    public String CREATE_TABLE_SQL = "create table dict (" +
            Words.Word._ID + " integer primary key autoincrement, " +
            Words.Word.WORD + ", " + Words.Word.DETAIL + ");";

    public MyDatabaseHelper(Context context, String name, int version){
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.e("myapp", "update");
    }
}
