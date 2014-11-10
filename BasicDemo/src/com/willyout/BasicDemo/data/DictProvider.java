package com.willyout.BasicDemo.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class DictProvider extends ContentProvider{
    private static UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
    private static final int WORDS = 1;
    private static final int WORD = 2;

    private MyDatabaseHelper myDatabaseHelper;

    static {
        matcher.addURI(Words.AUTHORITY, "word", WORDS);
        matcher.addURI(Words.AUTHORITY, "word/#", WORD);
    }

    @Override
    public boolean onCreate() {
        myDatabaseHelper = new MyDatabaseHelper(this.getContext(), "mydict.db3", 1);
        return true;
    }

    @Override
    public String getType(Uri uri) {
        switch(matcher.match(uri)){
            case WORDS:
                return "vnd.android.cursor.dir/org.willyout.BasicDemo.data";
            case WORD:
                return "vnd.android.cursor.item/org.willyout.BasicDemo.data";
            default:
                throw new IllegalArgumentException("erroe uri");
        }
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase sqLiteDatabase = myDatabaseHelper.getReadableDatabase();
        switch(matcher.match(uri)){
            case WORDS:
                return sqLiteDatabase.query("dict", projection, selection, selectionArgs, null, null, sortOrder);
            case WORD:
                long id = ContentUris.parseId(uri);
                String where = Words.Word._ID + "=" + id;
                if(selection != null && !"".equals(selection)){
                    where += " and " + selection;
                }
                return sqLiteDatabase.query("dict", projection, where, selectionArgs, null, null, sortOrder);
            default:
                throw new IllegalArgumentException("erroe uri");
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase sqLiteDatabase = myDatabaseHelper.getWritableDatabase();
        switch (matcher.match(uri)){
            case WORDS:
                long rowID = sqLiteDatabase.insert("dict", Words.Word._ID, values);
                if(rowID > 0){
                    Uri wordUri = ContentUris.withAppendedId(uri, rowID);
                    getContext().getContentResolver().notifyChange(wordUri, null);
                    return wordUri;
                }
                break;
            default:
                throw new IllegalArgumentException("error uri");
            }
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase sqLiteDatabase = myDatabaseHelper.getWritableDatabase();
        int num;
        switch (matcher.match(uri)){
            case WORDS:
                num = sqLiteDatabase.delete("dict", selection, selectionArgs);
                break;
            case WORD:
                long id = ContentUris.parseId(uri);
                String where = Words.Word._ID + "=" + id;
                if(selection != null && !"".equals(selection)){
                    where += " and " + selection;
                }
                num = sqLiteDatabase.delete("dict", where, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("error uri");
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return num;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase sqLiteDatabase = myDatabaseHelper.getWritableDatabase();
        int num;
        switch (matcher.match(uri)){
            case WORDS:
                num = sqLiteDatabase.update("dict", values, selection, selectionArgs);
                break;
            case WORD:
                long id = ContentUris.parseId(uri);
                String where = Words.Word._ID + "=" + id;
                if(selection != null && !"".equals(selection)){
                    where += " and " + selection;
                }
                num = sqLiteDatabase.update("dict", values, where, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("error uri");
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return num;
    }
}
