package com.example.coribeemish.lab4;

// Cori Beemish
// Lab 04
// September 12th, 2018

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    private TextView usernameTextView;
    private TextView passwordTextView;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //get references to the TextView Labels
        usernameTextView = (TextView) findViewById(R.id.usernameTextView);
        usernameTextView.setText("Username: ");
        passwordTextView = (TextView) findViewById(R.id.passwordTextView);
        passwordTextView.setText("Password: ");
        loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setText(" Login ");

        //get references to the EditText
        usernameEditText = (EditText) findViewById(R.id.usernameEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);

        login();
    } //end method OnCreate

    public void login(){
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // if the username and the password is correct
                if (usernameEditText.getText().toString().equals("cs4322") && passwordEditText.getText().toString().equals("123456")){
                    Toast.makeText(getApplicationContext(),"Redirecting...",Toast.LENGTH_SHORT).show();
                    //Redirects the user to the correct username & password page
                    Intent intent = new Intent(getBaseContext(), User.class);
                    intent.putExtra("username",usernameEditText.getText().toString());
                    startActivity(intent);
                }
                else { 
                    //if the username and/or password is wrong
                    Toast.makeText(getApplicationContext(), "Wrong Credentials",Toast.LENGTH_SHORT).show();
                    //Redirects the user to the error page
                    Intent intent = new Intent(getBaseContext(), LoginFail.class);
                    startActivity(intent);
                }
            }
        });
    } //end method login
}
