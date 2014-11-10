package com.willyout.BasicDemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

/**
 * Created by will on 2014/9/18.
 */
public class DrawViewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
        ** 可以使用下边一行代码代替
        */
        //setContentView(R.layout.draw);

        setContentView(R.layout.main);

        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.root);
        DrawView drawView = new DrawView(this);

        drawView.setMinimumHeight(500);
        drawView.setMinimumWidth(500);

        linearLayout.addView(drawView);
    }
}
