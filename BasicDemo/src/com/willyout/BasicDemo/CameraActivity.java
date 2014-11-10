package com.willyout.BasicDemo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.text.format.Time;
import android.view.*;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

/**
 * Created by will on 2014/9/17.
 */
public class CameraActivity extends Activity {
    SurfaceView view;
    SurfaceHolder holder;
    int width, height;
    Camera camera;
    boolean isPreview = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.camera);
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        width = display.getWidth();
        height = display.getHeight();

        view = (SurfaceView) findViewById(R.id.sview);
        holder = view.getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                initCamera();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                destoryCamera();
            }
        });
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    private void destoryCamera() {
        if (camera != null) {
            if (isPreview) {
                camera.stopPreview();
            }
            camera.release();
            camera = null;
        }
    }

    private void initCamera() {
        if (!isPreview) {
            camera = Camera.open();
        }
        if (camera != null && !isPreview) {
            try {
                Camera.Parameters parameters = camera.getParameters();
                List<Camera.Size> sizes = parameters.getSupportedPreviewSizes();
                Camera.Size cs = sizes.get(0);
                parameters.setPreviewSize(cs.width, cs.height);
//                //parameters.setPreviewFpsRange(4, 5);
//                parameters.setPictureFormat(PixelFormat.JPEG);
//                //parameters.set("jpeg-quality", 100);
//                parameters.setPictureSize(width, height);
                camera.setParameters(parameters);
                camera.setPreviewDisplay(holder);
                camera.startPreview();
                camera.autoFocus(null);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_CENTER:
            case KeyEvent.KEYCODE_CAMERA:
                if (camera != null && event.getRepeatCount() == 0) {
                    camera.takePicture(null, null, jpegCallBack);
                    return true;
                }
                break;
        }
        return super.onKeyDown(keyCode, event);
    }

    Camera.PictureCallback jpegCallBack = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            final Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0 ,data.length);
            File file = new File(Environment.getExternalStorageDirectory(), "ewew" + ".jpg");
            FileOutputStream stream = null;
            try{
                stream = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                stream.close();
            }catch(Exception ex){
                ex.printStackTrace();
            }
            camera.stopPreview();
            camera.startPreview();
            isPreview = true;
        }
    };
}

