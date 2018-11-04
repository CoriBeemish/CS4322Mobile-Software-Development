package com.android.contactlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class DetailsContact extends AppCompatActivity {
    DbAdapter db;
    String id,name,number,email,addressStreet,addressCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_contact);

        Intent intent = getIntent();
        id = intent.getStringExtra("ID");
        name = intent.getStringExtra("NAME");
        number = intent.getStringExtra("NUMBER");
        email = intent.getStringExtra("EMAIL");
        addressStreet = intent.getStringExtra("ADDRESS_STREET");
        addressCity = intent.getStringExtra("ADDRESS_CITY");

        ((TextView) findViewById(R.id.name)).setText(name);
        ((TextView) findViewById(R.id.number)).setText(number);
        ((TextView) findViewById(R.id.email)).setText(email);
        ((TextView) findViewById(R.id.addressStreet)).setText(addressStreet);
        ((TextView) findViewById(R.id.addressCity)).setText(addressCity);

        //calling DbAdapter
        db = new DbAdapter(this);
        db.open();
    }

    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.actionEdit:
                Edit();
                break;
            case R.id.actionDelete:
                Delete();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void Edit() {
        //go to EditContact page
        Intent edit = new Intent(DetailsContact.this, EditContact.class);
        edit.putExtra("ID", id);
        edit.putExtra("NAME", name);
        edit.putExtra("NUMBER", number);
        edit.putExtra("EMAIL", email);
        edit.putExtra("ADDRESS_STREET",addressStreet);
        edit.putExtra("ADDRESS_City",addressCity);
        startActivity(edit);
    }

    private void Delete() {
        db.delete(Integer.parseInt(id));
        Toast.makeText(getApplicationContext(),"Contact deleted!", Toast.LENGTH_SHORT).show();
        finish();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void Edit(View v){
        //go to EditContact page
        Intent edit = new Intent(DetailsContact.this, EditContact.class);
        edit.putExtra("ID", id);
        edit.putExtra("NAME", name);
        edit.putExtra("NUMBER", number);
        edit.putExtra("EMAIL", email);
        edit.putExtra("ADDRESS_STREET",addressStreet);
        edit.putExtra("ADDRESS_CITY",addressCity);
        startActivity(edit);
    }

    public void Delete(View v){
        db.delete(Integer.parseInt(id));
        Toast.makeText(getApplicationContext(),"Contact deleted!", Toast.LENGTH_SHORT).show();
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