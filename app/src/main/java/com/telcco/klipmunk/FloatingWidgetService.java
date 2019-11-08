package com.telcco.klipmunk;

import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;

import androidx.annotation.Nullable;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.andremion.counterfab.CounterFab;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by PHD on 2/4/2019.
 */

public class FloatingWidgetService extends Service {
    private WindowManager mWindowManager;
    private View mOverlayView;
    int mWidth;
    CounterFab counterFab;
    boolean activity_background;
    WindowManager.LayoutParams params;
    int resultCode;
    Intent data;
    String tempPath;
    File image;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
       // data = LogIn.dataG;
        data=NewsFragment.dataG;
        if (intent != null) {
            activity_background = intent.getBooleanExtra("activity_background", false);
            resultCode = intent.getIntExtra("resultCode", 0);


        }

        if (mOverlayView == null) {

            mOverlayView = LayoutInflater.from(this).inflate(R.layout.overlay_layout, null);

            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
                params = new WindowManager.LayoutParams(
                        WindowManager.LayoutParams.WRAP_CONTENT,
                        WindowManager.LayoutParams.WRAP_CONTENT,
                        WindowManager.LayoutParams.TYPE_PHONE,
                        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                                | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                                | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH
                                | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                        PixelFormat.TRANSLUCENT);


//Specify the view position
                params.gravity = Gravity.TOP | Gravity.LEFT;        //Initially view will be added to top-left corner
                params.x = 0;
                params.y = 100;


                mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
                mWindowManager.addView(mOverlayView, params);
            } else {
                params = new WindowManager.LayoutParams(
                        WindowManager.LayoutParams.WRAP_CONTENT,
                        WindowManager.LayoutParams.WRAP_CONTENT,
                        WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                                | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                                | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH
                                | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                        PixelFormat.TRANSLUCENT);


                params.gravity = Gravity.CENTER_HORIZONTAL | Gravity.TOP;
                params.x = 0;
                params.y = 100;
                mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
                mWindowManager.addView(mOverlayView, params);
            }


            Display display = mWindowManager.getDefaultDisplay();
            final Point size = new Point();
            display.getSize(size);

            counterFab = (CounterFab) mOverlayView.findViewById(R.id.fabHead);
//            counterFab.setCount(1);


            final RelativeLayout layout = (RelativeLayout) mOverlayView.findViewById(R.id.layout);
            ViewTreeObserver vto = layout.getViewTreeObserver();
            vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    layout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    int width = layout.getMeasuredWidth();

                    //To get the accurate middle of the screen we subtract the width of the floating widget.
                    mWidth = size.x - width;

                }
            });

            counterFab.setOnTouchListener(new View.OnTouchListener() {
                private int initialX;
                private int initialY;
                private float initialTouchX;
                private float initialTouchY;

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:

                            //remember the initial position.
                            initialX = params.x;
                            initialY = params.y;


                            //get the touch location
                            initialTouchX = event.getRawX();
                            initialTouchY = event.getRawY();


                            return true;
                        case MotionEvent.ACTION_UP:

                            //Only start the activity if the application is in background. Pass the current badge_count to the activity
                            if (activity_background) {

                                float xDiff = event.getRawX() - initialTouchX;
                                float yDiff = event.getRawY() - initialTouchY;

                                if ((Math.abs(xDiff) < 5) && (Math.abs(yDiff) < 5)) {
                    /*Intent intent = new Intent(FloatingWidgetService.this, LogIn.class);
                    intent.putExtra("badge_count", counterFab.getCount());
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);*/

                                    Screenshotter.getInstance()
                                            .setSize(720, 1280)
                                            .takeScreenshot(getApplicationContext(), resultCode, data, new ScreenshotCallback() {
                                                @Override
                                                public void onScreenshot(Bitmap bitmap) {
                                    Log.d("ScreenshotterExample", "onScreenshot called");
                                    ByteArrayOutputStream bStream = new ByteArrayOutputStream();
                                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, bStream);
                                   // byte[] byteArray = bStream.toByteArray();

                                   /* Intent in = new Intent(FloatingWidgetService.this,CounterScreenShot.class);
                                    in.putExtra("bitmap",byteArray);
                                    in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(in);*/

//                                                    Date now = new Date();
//                                                    android.text.format.DateFormat.format("yyyyMMdd_HHmmss", now);

                                                    try {
                                                        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                                                        String imageFileName = "JPEG_" + timeStamp + "_";
                                                        File storageDir = new File(Environment.getExternalStorageDirectory()+File.separator, "klipmunk/imageclips/");
                                                        storageDir.mkdirs(); // make sure you call mkdirs() and not mkdir()
                                                       // storageDir = new File(storageDir.getAbsolutePath());
                                                        Log.i("storageDir",storageDir.toString());
                                                        image = File.createTempFile(
                                                                imageFileName,  // prefix
                                                                ".jpg",         // suffix
                                                                storageDir      // directory
                                                        );

                                                        // image naming and path  to include sd card  appending name you choose for file
//                                                        String mPath = Environment.getExternalStorageDirectory().toString() + "/" + now + ".jpg";
//                                                        String replacempath =mPath.replaceAll("%20","");
//                                                        Log.i("ReplacePAth",replacempath);
//
//                                                        File imageFile = new File(replacempath);

                                                        FileOutputStream outputStream = new FileOutputStream(image);
                                                        //outputStream.write(bStream.toByteArray());
                                                        int quality = 100;
                                                        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
                                                        outputStream.flush();
                                                        outputStream.close();



                                                        MediaScannerConnection.scanFile(getApplicationContext(), new String[]{image.toString()}, null,
                                                                new MediaScannerConnection.OnScanCompletedListener() {
                                                                    public void onScanCompleted(String path, Uri uri) {
                                                                        Log.i("ExternalStorage", "Scanned " + path + ":");
                                                                        Log.i("ExternalStorage", "-> uri=" + uri);

                                                                        openScreenshot(image,path);

                                                                    }
                                                                });


                                                        //openScreenshotPath(replacempath);
                                                    } catch (Throwable e) {
                                                        // Several error may come out with file handling or DOM
                                                        Log.i("imageStore",e.getMessage());
                                                    }



                                                }
                                            });


                                    //close the service and remove the fab view
                                    stopSelf();
                                }

                            }
                            //Logic to auto-position the widget based on where it is positioned currently w.r.t middle of the screen.
                            int middle = mWidth / 2;
                            float nearestXWall = params.x >= middle ? mWidth : 0;
                            params.x = (int) nearestXWall;


                            mWindowManager.updateViewLayout(mOverlayView, params);


                            return true;
                        case MotionEvent.ACTION_MOVE:


                            int xDiff = Math.round(event.getRawX() - initialTouchX);
                            int yDiff = Math.round(event.getRawY() - initialTouchY);


                            //Calculate the X and Y coordinates of the view.
                            params.x = initialX + xDiff;
                            params.y = initialY + yDiff;

                            //Update the layout with new X & Y coordinates
                            mWindowManager.updateViewLayout(mOverlayView, params);


                            return true;
                    }
                    return false;
                }
            });
        } else {

            counterFab.increase();

        }


        return super.onStartCommand(intent, flags, startId);


    }



    @Override
    public void onCreate() {
        super.onCreate();

        setTheme(R.style.AppTheme);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mOverlayView != null)
            mWindowManager.removeView(mOverlayView);
    }

    private void openScreenshot(File imageFile,String MediaPath) {
/* Intent intent = new Intent();
intent.setAction(Intent.ACTION_VIEW);
Uri uri = Uri.fromFile(imageFile);
intent.setDataAndType(uri, "image/*");
intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
startActivity(intent);*/

        Uri uri = Uri.fromFile(imageFile);
        System.out.println("floatingServiceUri" + "---" + uri);

        Intent in = new Intent(FloatingWidgetService.this, CounterScreenShot.class);
        in.putExtra("bitmap", imageFile);
        in.putExtra("MediaPath",MediaPath);
        in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(in);
    }

    private void openScreenshotPath(String replacempath) {
        Uri uri = Uri.parse(replacempath);
        File file = new File(uri.getPath());
        Intent in = new Intent(FloatingWidgetService.this, CounterScreenShot.class);
        in.putExtra("bitmap", file);
        in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(in);
    }


}
