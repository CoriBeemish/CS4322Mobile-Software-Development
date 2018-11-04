package com.android.contactlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class addNewContact extends AppCompatActivity {
    // Calling variables
    DbAdapter db;
    EditText etname, etnumber, etemail, etaddress1, etaddress2;
    String name, number, email, addressStreet, addressCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_contact);
        // Get data from text feld
        etname =(EditText)findViewById(R.id.name);
        etnumber =(EditText)findViewById(R.id.number);
        etemail =(EditText)findViewById(R.id.email);
        etaddress1 = (EditText)findViewById(R.id.addressStreet);
        etaddress2 = (EditText)findViewById(R.id.addressCity);

        // Calling DbAdapter
        db = new DbAdapter(this);
        db.open();
    }
    public void Save(View v){
        if(db.isExist(number)){
            Toast.makeText(getApplicationContext(),"Contact already exists", Toast.LENGTH_SHORT).show();
        }else{
            name=etname.getText().toString();
            number=etnumber.getText().toString();
            email=etemail.getText().toString();
            addressStreet=etaddress1.getText().toString();
            addressCity=etaddress2.getText().toString();
        }

        //checking the data fields
        boolean isNameEmpy=etname.getText().toString().isEmpty();
        boolean isPhoneEmpty=etnumber.getText().toString().isEmpty();

        if (isNameEmpy){
            etname.setError("Name required!");
        }
        else if(isPhoneEmpty){
            etnumber.setError("Phone number required!");
        }
        else{
            db.insert(name,number,email,addressStreet,addressCity);
            Toast.makeText(getApplicationContext(),"Contact added", Toast.LENGTH_SHORT).show();
            finish();
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}