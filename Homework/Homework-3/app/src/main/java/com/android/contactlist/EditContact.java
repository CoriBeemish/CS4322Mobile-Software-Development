package com.android.contactlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditContact extends AppCompatActivity {

    DbAdapter db;
    String id, name, number, email, addressStreet, addressCity;
    EditText etname, etnumber, etemail, etaddress1, etaddress2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_contact);

        Intent intent = getIntent();
        id = intent.getStringExtra("ID");
        name = intent.getStringExtra("NAME");
        number = intent.getStringExtra("NUMBER");
        email = intent.getStringExtra("EMAIL");
        addressStreet = intent.getStringExtra("ADDRESS_STREET");
        addressCity = intent.getStringExtra("ADDRESS_CITY");

        ((EditText) findViewById(R.id.name)).setText(name);
        ((EditText) findViewById(R.id.number)).setText(number);
        ((EditText) findViewById(R.id.email)).setText(email);
        ((EditText) findViewById(R.id.addressStreet)).setText(addressStreet);
        ((EditText) findViewById(R.id.addressCity)).setText(addressCity);

        //calling DbAdapter
        db = new DbAdapter(this);
        db.open();

        //get data from text field
        etname =(EditText)findViewById(R.id.name);
        etnumber =(EditText)findViewById(R.id.number);
        etemail =(EditText)findViewById(R.id.email);
        etaddress1 = (EditText)findViewById(R.id.addressStreet);
        etaddress2 = (EditText)findViewById(R.id.addressCity);
    }

    public void Save(View v){
        name=etname.getText().toString();
        number=etnumber.getText().toString();
        email=etemail.getText().toString();
        addressStreet=etaddress1.getText().toString();
        addressCity=etaddress2.getText().toString();

        db.update(Integer.parseInt(id), name, number, email, addressStreet, addressCity);

        Toast.makeText(getApplicationContext(),"Contact updated!", Toast.LENGTH_SHORT).show();

        finish();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}