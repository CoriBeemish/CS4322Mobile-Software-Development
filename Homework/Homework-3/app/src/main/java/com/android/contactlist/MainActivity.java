package com.android.contactlist;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Variables
    DbAdapter db;
    SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Calling DbAdapter
        db = new DbAdapter(this);
        db.open();

        //initially insert some data
        db.insert("Cori", "(770) 425-4379", "cbeemish@kennesaw.edu", "1675 Franklin Road", "Lindenhurst");

        //display data
        ListView lv = (ListView) findViewById(R.id.listView1);

        int layoutstyle=R.layout.liststyle;

        int[] xml_id = new int[] {R.id.txtname, R.id.txtnumber};

        String[] column = new String[] {"name", "number"};

        Cursor row = db.fetchAllData();
        adapter = new SimpleCursorAdapter(this, layoutstyle,row,column, xml_id, 0);
        lv.setAdapter(adapter);

        //onClick function
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterview, View view, int position, long id) {
                Cursor row = (Cursor) adapterview.getItemAtPosition(position);
                String _id = row.getString(row.getColumnIndexOrThrow("_id"));
                String name = row.getString(row.getColumnIndexOrThrow("name"));
                String number = row.getString(row.getColumnIndexOrThrow("number"));
                String email = row.getString(row.getColumnIndexOrThrow("email"));
                String addressStreet = row.getString(row.getColumnIndexOrThrow("addressStreet"));
                String addressCity = row.getString(row.getColumnIndexOrThrow("addressCity"));

                // Go to detailsContact page
                Intent todetais = new Intent(MainActivity.this, DetailsContact.class);
                todetais.putExtra("ID",_id);
                todetais.putExtra("NAME", name);
                todetais.putExtra("NUMBER",number);
                todetais.putExtra("EMAIL",email);
                todetais.putExtra("ADDRESS_STREET",addressStreet);
                todetais.putExtra("ADDRESS_CITY",addressCity);
                startActivity(todetais);
            }
        });

        // Filter
        EditText et = (EditText) findViewById(R.id.myFilter);
        et.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s.toString());
            }
        });
        adapter.setFilterQueryProvider(new FilterQueryProvider() {
            public Cursor runQuery(CharSequence constraint) {
                return db.fetchdatabyfilter(constraint.toString(),"name");
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.add_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.actionAdd:
                addContact();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void addContact() {
        Intent addNewContact = new Intent(MainActivity.this, addNewContact.class);
        startActivity(addNewContact);
    }

    public void addContact(View v){
        Intent addNewContact = new Intent(MainActivity.this, addNewContact.class);
        startActivity(addNewContact);
    }

}