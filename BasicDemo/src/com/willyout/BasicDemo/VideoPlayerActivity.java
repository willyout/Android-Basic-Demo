package com.willyout.BasicDemo;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import java.io.File;

/**
 * Created by will on 2014/9/17.
 */
public class VideoPlayerActivity extends Activity {
    VideoView video;
    MediaController controller;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.video);

        video = (VideoView)findViewById(R.id.video);
        controller = new MediaController(this);
        File file = new File("/storage/sdcard0/DCIM/Camera/nu.mp4");
        if(file.exists()){
            video.setVideoPath(file.getAbsolutePath());
            video.setMediaController(controller);
            controller.setMediaPlayer(video);
            video.requestFocus();
        }
    }
}
