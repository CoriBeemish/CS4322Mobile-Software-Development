package com.example.coribeemish.lab4;

// Cori Beemish
// Lab 04
// September 12th, 2018

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class User extends AppCompatActivity {

    private TextView loginMessage;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        //get references to the TextView Labels
        loginMessage = (TextView) findViewById(R.id.loginMessage);
        loginMessage.setText(getIntent().getStringExtra("username") + ", welcome to my webpage!" );

        backButton = (Button) findViewById(R.id.backButton);
        backButton.setText(" Back ");

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Sends the user back to the login page
                Intent intent = new Intent(getBaseContext(), Login.class);
                startActivity(intent);
            }
        });

    }
}
