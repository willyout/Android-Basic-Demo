package com.willyout.BasicDemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by will on 2014/9/18.
 */
public class FrameActivity extends Activity {

    TextView view1;
    int a = 0;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 16){
                if(a%2 == 0)
                    view1.setBackgroundColor(getResources().getColor(R.color.color1));
                else
                    view1.setBackgroundColor(getResources().getColor(R.color.color2));
                a++;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame);

        view1 = (TextView)findViewById(R.id.view1);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(16);
            }
        }, 0, 200);
    }
}
