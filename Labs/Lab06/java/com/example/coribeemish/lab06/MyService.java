package com.example.coribeemish.lab06;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;
import android.support.annotation.Nullable;

import java.util.Random;

/**
 * Cori Beemish
 * Lab 06
 * September 30th, 2018
 */

public class MyService extends Service {
    String TAG = "MyService: ";

    private boolean isRunning;


    private final Random mGenerator = new Random();
    String mRandomAlphabet;

    public class LocalBinder extends Binder{
        MyService getService(){
            return MyService.this;
        }
    }

    private final IBinder iBinder = new LocalBinder();

    @Override
    public void onCreate() {
        super.onCreate();

        Toast.makeText(this, "Service Created", Toast.LENGTH_SHORT).show();
        Log.i(TAG, "Service Created");

        isRunning = true;

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //Toast.makeText(this, "Service Started", Toast.LENGTH_SHORT).show();
        Log.i(TAG, "Service Started with thread ID " + Thread.currentThread().getId());
        isRunning = true;

        new Thread(new Runnable() {
            @Override
            public void run() {
                randomGenerate();

            }
        }
        ).start();
        return START_STICKY;
    }

    public void randomGenerate( ){
        while (isRunning){
            try {
                Thread.sleep(1000);
                if(isRunning){
                    Random generator = new Random();
                    int index = generator.nextInt(26);
                    String [] alphabets = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L","M", "N","O","P","Q", "R", "S", "T", "U", "V","W", "X","Y","Z"};
                    mRandomAlphabet = alphabets[index];
                    //Toast.makeText(this, "Random Number " + mRandomAlphabet, Toast.LENGTH_SHORT).show();
                    Log.i("Random Char is ", mRandomAlphabet);
                }

            } catch (Exception e) {
                Thread.currentThread().interrupt();
            }


        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_SHORT).show();
        isRunning = false;

        Log.i(TAG, "Service Destroyed with thread ID " + Thread.currentThread().getId());
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "Service Bound");
        return iBinder;
    }

    public String getRandomChar()
    {
        return mRandomAlphabet;
    }
}
