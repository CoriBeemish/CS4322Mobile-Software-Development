package com.example.coribeemish.lab06;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;

/**
 * Cori Beemish
 * Lab 06
 * September 30th, 2018
 */

public class MainActivity extends AppCompatActivity {

    Button btnStart, btnStop;
    String tag = "MainActivity: ";
    TextView charTextView;
    MyService myService;
    boolean isBound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = (Button) findViewById(R.id.btnStart);
        btnStop = (Button) findViewById(R.id.btnStop);
        charTextView = (TextView) findViewById(R.id.charTextView);


        Log.i(tag, "Thread ID is: "+ Thread.currentThread().getId());

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MyService.class);
                bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
                //setRandomChar();
                MainActivity.this.startService(intent);

                if(isBound){
                    setRandomChar();
                }

            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, MyService.class);
                MainActivity.this.stopService(intent);
            }
        });
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyService.LocalBinder binder = (MyService.LocalBinder) service;
            myService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;

        }
    };

    private void setRandomChar()
    {
        if(isBound)
            charTextView.setText(myService.getRandomChar());
        else
            charTextView.setText("Service is not bound.");
    }
}
