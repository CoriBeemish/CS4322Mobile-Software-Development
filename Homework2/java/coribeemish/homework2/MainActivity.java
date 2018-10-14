package coribeemish.homework2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import static android.R.attr.onClick;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences savedInfo;
    private EditText nameET, tagET;
    private TableLayout myTL;
    private static final String SAVED_INFO_NAME = "StudentsInfo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize the shared preferences from 'disk'
        savedInfo = getSharedPreferences(SAVED_INFO_NAME, MODE_PRIVATE);

        myTL = (TableLayout)findViewById(R.id.tableLayout);
        nameET = (EditText)findViewById(R.id.editText);
        tagET = (EditText)findViewById(R.id.editText3);

        refreshButton("",true);

        Button delBTN = (Button) findViewById(R.id.newDeleteBTN);

//        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
//        filterRadioButton = (RadioButton) findViewById(R.id.filterRadioButton);
//        videoRadioButton = (RadioButton) findViewById(R.id.videoRadioButton);
//        imageRadioButton = (RadioButton) findViewById(R.id.imageRadioButton);
//        linkRadioButton = (RadioButton) findViewById(R.id.linkRadioButton);
    }

    public void onClick(View view)
    {
        switch (view.getId()){
            case R.id.button:
                if(nameET.getText().length() > 0 && tagET.getText().length()>0) {
                    String name = nameET.getText().toString();
                    String tag = tagET.getText().toString();
                    boolean tagAlreadySaved = savedInfo.contains(tag);
                    SharedPreferences.Editor myEditor = savedInfo.edit();
                    myEditor.putString(tag, name);
//                    if (videoRadioButton.isChecked()){
//                        myEditor.putBoolean("Video", videoRadioButton.isChecked());
//                        //myEditor.apply();
//                    }
//                    else if (imageRadioButton.isChecked()) {
//                        myEditor.putBoolean("Image", imageRadioButton.isChecked());
//                        //myEditor.apply();
//                    }
//                    else if(linkRadioButton.isChecked()){
//                        myEditor.putBoolean("Link", linkRadioButton.isChecked());
//                        //myEditor.apply();
//                    }
//                    else{
//                        myEditor.putBoolean("Blank", filterRadioButton.isChecked());
//                        //myEditor.apply();
//                    }

//                        myEditor.putBoolean("Video", videoRadioButton.isChecked());
//                        myEditor.putBoolean("Image", imageRadioButton.isChecked());
//                        myEditor.putBoolean("Link", linkRadioButton.isChecked());
                    myEditor.apply();
                    if (!tagAlreadySaved) {
                        refreshButton(tag, false);
                    }
                    nameET.setText("");
                    tagET.setText("");
                }
                else{
                    AlertDialog.Builder bld = new AlertDialog.Builder(MainActivity.this);
                    bld.setTitle("Missing Text");
                    bld.setMessage("Please enter a search query and tag it.");
                    bld.setPositiveButton("OK", null);
                    AlertDialog missingDialog = bld.create();
                    missingDialog.show();
                }
                break;
            case R.id.button3:
                AlertDialog.Builder adb = new AlertDialog.Builder(MainActivity.this);
                // set title and message using string resources
                // The  set methods look up the actual values of the resources
                adb.setTitle("Are you sure?");
                adb.setMessage("This will delete all saved searches.");
                adb.setCancelable(true);
                // negative button does nothing other than dismiss the dialog
                adb.setNegativeButton("Cancel", null);
                // positive button will carry out the deletion
                adb.setPositiveButton("Erase", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // get rid of the rows in the GUI
                        myTL.removeAllViews();
                        // clear the stored queries
                        SharedPreferences.Editor myEditor = savedInfo.edit();
                        myEditor.clear();
                        myEditor.apply();
                    }
                });
                // create the dialog
                AlertDialog confirmDialog = adb.create();
                // show the dialog
                // if the user selects to erase, the queries will be erased before the show
                //  method returns
                confirmDialog.show();
                break;
            case R.id.newTagBTN:
                String tag1 = ((Button)view).getText().toString();
                String query1 = savedInfo.getString(tag1, "");
                String url= "https://twitter.com/search?q="+query1;

//                Boolean filterBTN = savedInfo.getBoolean("Blank", false);
//                Boolean videoBTN = savedInfo.getBoolean("Video", false);
//                Boolean imageBTN = savedInfo.getBoolean("Image", false);
//                Boolean linkBTN = savedInfo.getBoolean("Link", false);

                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(myIntent);
                break;
            case R.id.newEditBTN:
                TableRow row = (TableRow) view.getParent();
                // the text on the button is the tag for this query
                Button tagBTN = (Button) row.findViewById(R.id.newTagBTN);
                String tag = tagBTN.getText().toString();
                // get the query from the saved searches
                String query = savedInfo.getString(tag,"");
                tagET.setText(tag);
                nameET.setText(query);
                break;
            case R.id.newDeleteBTN:
//                    AlertDialog.Builder delAlert = new AlertDialog.Builder(MainActivity.this);
//                    // set title and message using string resources
//                    // The  set methods look up the actual values of the resources
//                    delAlert.setTitle("Are you sure?");
//                    delAlert.setMessage("This will delete this saved searches.");
//                    delAlert.setCancelable(true);
//                    // negative button does nothing other than dismiss the dialog
//                    delAlert.setNegativeButton("Cancel", null);
//                    // positive button will carry out the deletion
//                    delAlert.setPositiveButton("Erase", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            TableRow tableRow = (TableRow) view.getParent();
//                            myTL.removeView(tableRow);
//                            myTL.removeView((View) view.getParent());
//                            // clear the stored queries
//                            SharedPreferences.Editor myEditor = savedInfo.edit();
//                            myEditor.clear();
//                            myEditor.apply();
//                        }
//                    });
//                    // create the dialog
//                    AlertDialog confirm = delAlert.create();
//                    // show the dialog
//                    // if the user selects to erase, the queries will be erased before the show
//                    //  method returns
//                    confirm.show();
                break;

        }

    }

    private void refreshButton(String tag, boolean applytoAll) {
        // get the map of tags to queries
        Map<String,?> queryMap = savedInfo.getAll();
        // get the keys from the map
        Set<String> tagSet = queryMap.keySet();
        // convert the set to an array
        String[] tags = tagSet.toArray(new String[0]);
        // sort the tags
        Arrays.sort(tags,String.CASE_INSENSITIVE_ORDER);
        // determine where the new tag should go
        int index = Arrays.binarySearch(tags, tag);
        if(applytoAll){
            for(int i = 0; i < tags.length; i++ ) {
                makeTagGUI(tags[i], i);
            }
        }
        else
            makeTagGUI(tag, index);
    }

    private void makeTagGUI(String tag, int index) {
        // create a new row by inflating the layout file
        LayoutInflater li =
                (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = li.inflate(R.layout.new_tag_view, null);

        // get the tag button
        Button tagBTN = (Button)row.findViewById(R.id.newTagBTN);
        //tagBTN.setText(tag + " " + index);
        tagBTN.setText(tag);


        // set the edit listener on the edit button
        Button editBTN = (Button)row.findViewById(R.id.newEditBTN);
        // set the delete listener on the edit button
        //Button deleteBTN = (Button) findViewById(R.id.newDeleteBTN);
        myTL.addView(row,index);
    }
}
