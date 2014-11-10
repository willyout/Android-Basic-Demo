package com.willyout.BasicDemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by will on 2014/9/17.
 */
public class BCReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "test", Toast.LENGTH_LONG).show();
    }
}
