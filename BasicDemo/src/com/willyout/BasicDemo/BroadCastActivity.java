package com.willyout.BasicDemo;

import android.app.Activity;
import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.willyout.BasicDemo.data.Words;


public class BroadCastActivity extends Activity {
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.broadcast);
        button = (Button) findViewById(R.id.broadcast);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testDB();
            }
        });
    }

    private void testBC() {
        Intent intent = new Intent(getString(R.string.broadcastTag));
        sendBroadcast(intent);
    }

    private void testDB() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Words.Word.WORD, "hello");
        contentValues.put(Words.Word.DETAIL, "hello fuck");
        getContentResolver().insert(Words.Word.WORD_CONTENT_URI, contentValues);

        Cursor cursor = getContentResolver().query(Words.Word.DICT_CONTENT_URI, null, "word like ? or detail like ?", new String[]{"%hello%", "%fuck%"}, null);
        while (cursor.moveToNext()){
            Toast.makeText(this, cursor.getString(cursor.getColumnIndex(Words.Word.WORD)), Toast.LENGTH_LONG).show();
        }
    }
}
