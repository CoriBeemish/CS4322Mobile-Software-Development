package com.example.coribeemish.lab4;

// Cori Beemish
// Lab 04
// September 12th, 2018

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginFail extends AppCompatActivity {

    TextView loginFailMessage;
    Button backButtonFail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_fail);

        loginFailMessage = (TextView) findViewById(R.id.loginFailMessage);
        loginFailMessage.setText("Login Fail! Please go back and try again!");

        backButtonFail = (Button) findViewById(R.id.backButtonFail);
        backButtonFail.setText(" Back ");

        backButtonFail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), Login.class);
                startActivity(intent);
            }
        });

    }
}
