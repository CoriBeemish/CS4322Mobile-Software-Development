// Cori Beemish
// Homework 1
// September 10th, 2018

package com.example.coribeemish.homework1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    //currency and percent formatters
    private static final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
    private static final NumberFormat percentFormat = NumberFormat.getPercentInstance();

    private double billAmount = 0.0; // Bill Amount entered by the user
    private double customPercent = 0.18; // initial custom tip percentage
    private TextView amountDisplayTextView; // shows formatted bill amount
    private TextView tip10TextView; // shows 10% tip
    private TextView tip15TextView; // shows 15% tip
    private TextView tip20TextView; // shows 20% tip
    private TextView total10TextView; // shows total with 10% tip
    private TextView total15TextView; // shows total with 15% tip
    private TextView total20TextView; // shows total with 20% tip
    private TextView percentCustomTextView; // shows custom tip percentage
    private TextView tipCustomTextView; // shows custom tip amount
    private TextView totalCustomTextView; // shows total with custom tip

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get references to the TextViewLabels and settext
        TextView appLabel = (TextView) findViewById(R.id.appLabel);
        appLabel.setText("Tip Calculator");
        TextView billLabel = (TextView) findViewById(R.id.billLabel);
        billLabel.setText("Enter Bill Amount: ");
        TextView tenTipLabel = (TextView) findViewById(R.id.tenTipLabel);
        tenTipLabel.setText(" 10% ");
        TextView fifteenTipLabel = (TextView) findViewById(R.id.fifteenTipLabel);
        fifteenTipLabel.setText(" 15% ");
        TextView twentyTipLabel = (TextView) findViewById(R.id.twentyTipLabel);
        twentyTipLabel.setText(" 20% ");
        TextView tipLabel = (TextView) findViewById(R.id.tipLabel);
        tipLabel.setText(" Tip ");
        TextView totalLabel = (TextView) findViewById(R.id.totalLabel);
        totalLabel.setText(" Total ");
        TextView customTipTextView = (TextView) findViewById(R.id.customTipTextView);
        customTipTextView.setText(" Custom Tip ");
        TextView customTipLabel = (TextView) findViewById(R.id.customTipLabel);
        customTipLabel.setText(" Tip ");
        TextView customTipTotalLabel = (TextView) findViewById(R.id.customTipTotalLabel);
        customTipTotalLabel.setText(" Total ");

        // get references to the TextViews
        amountDisplayTextView = (TextView) findViewById(R.id.amountDisplayTextView);
        tip10TextView = (TextView) findViewById(R.id.tip10TextView);
        tip15TextView = (TextView) findViewById(R.id.tip15TextView);
        tip20TextView = (TextView) findViewById(R.id.tip20TextView);
        total10TextView = (TextView) findViewById(R.id.total10TextView);
        total15TextView = (TextView) findViewById(R.id.total15TextView);
        total20TextView = (TextView) findViewById(R.id.total20TextView);
        percentCustomTextView = (TextView) findViewById(R.id.percentCustomTextView);
        tipCustomTextView = (TextView) findViewById(R.id.tipCustomTextView);
        totalCustomTextView = (TextView) findViewById(R.id.totalCustomTextView);

        // update GUI based on billAmount and customPercent
        amountDisplayTextView.setText(currencyFormat.format(billAmount));
        update10(); // update the 10% tip TextViews
        update15(); // update the 15% tip TextViews
        update20(); // update the 20% tip TextViews
        updateCustom(); // update the custom tip TextViews

        // set amountEditText's TextWatcher
        EditText amountEditText = (EditText) findViewById(R.id.amountEditText);
        amountEditText.addTextChangedListener(amountEditTextWatcher);

        // set customTipSeekBar's OnSeekBarChangeListener
        SeekBar customTipSeekBar = (SeekBar) findViewById(R.id.customTipSeekBar);
        customTipSeekBar.setOnSeekBarChangeListener(customSeekBarListener);

    } // end method OnCreate

    // updates 10% tip TextViews
    private void update10()
    {
        // calculate 10% tip and total
        double tenPercentTip = billAmount * 0.10;
        double tenPercentTotal = billAmount + tenPercentTip;

        // display 20% tip and total formatted as currency
        tip10TextView.setText(currencyFormat.format(tenPercentTip));
        total10TextView.setText(currencyFormat.format(tenPercentTotal));
    } // end method update20

    // updates 15% tip TextViews
    private void update15()
    {
        // calculate 15% tip and total
        double fifteenPercentTip = billAmount * 0.15;
        double fifteenPercentTotal = billAmount + fifteenPercentTip;

        // display 15% tip and total formatted as currency
        tip15TextView.setText(currencyFormat.format(fifteenPercentTip));
        total15TextView.setText(currencyFormat.format(fifteenPercentTotal));
    } // end method update15

    // updates 20% tip TextViews
    private void update20()
    {
        // calculate 20% tip and total
        double twentyPercentTip = billAmount * 0.20;
        double twentyPercentTotal = billAmount + twentyPercentTip;

        // display 20% tip and total formatted as currency
        tip20TextView.setText(currencyFormat.format(twentyPercentTip));
        total20TextView.setText(currencyFormat.format(twentyPercentTotal));
    } // end method update20

    // updates the custom tip and total TextViews
    private void updateCustom()
    {
        // show customPercent in percentCustomTextView formatted as %
        percentCustomTextView.setText(percentFormat.format(customPercent));

        // calculate the custom tip and total
        double customTip = billAmount * customPercent;
        double customTotal = billAmount + customTip;

        // display custom tip and total formatted as currency
        tipCustomTextView.setText(currencyFormat.format(customTip));
        totalCustomTextView.setText(currencyFormat.format(customTotal));
    } // end method updateCustom

    // called when the user changes the position of SeekBar
    private SeekBar.OnSeekBarChangeListener customSeekBarListener = new SeekBar.OnSeekBarChangeListener() {
        // update customPercent, then call updateCustom
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
        {
            // sets customPercent to position of the SeekBar's thumb
            customPercent = progress / 100.0;
            updateCustom(); // update the custom tip TextViews
        } // end method onProgressChanged

        @Override
        public void onStartTrackingTouch(SeekBar seekBar)
        {
        } // end method onStartTrackingTouch

        @Override
        public void onStopTrackingTouch(SeekBar seekBar)
        {
        } // end method onStopTrackingTouch
    }; // end OnSeekBarChangeListener

    // event-handling object that responds to amountEditText's events
    private TextWatcher amountEditTextWatcher = new TextWatcher() {
        // called when the user enters a number
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // convert amountEditText's text to a double
            try
            {
                billAmount = Double.parseDouble(s.toString());
            } // end try
            catch (NumberFormatException e)
            {
                billAmount = 0.0; // default if an exception occurs
            } // end catch

            // display currency formatted bill amount
            amountDisplayTextView.setText(currencyFormat.format(billAmount));
            update10(); // update the 10% tip TextViews
            update15(); // update the 15% tip TextViews
            update20(); // update the 20% tip TextViews
            updateCustom(); // update the custom tip TextViews
        } // end method onTextChanged

        @Override
        public void afterTextChanged(Editable s)
        {

        } // end method afterTextChanged

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after)
        {

        } // end method beforeTextChanged
    }; // end amountEditTextWatcher
}
