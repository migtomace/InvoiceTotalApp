package com.example.miguelacevedo.invoicetotalapp;

import android.app.Activity;
import java.text.NumberFormat;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends Activity implements TextView.OnEditorActionListener {

    //declare variables for widgets
    private EditText subtotalEditText;
    private TextView percentTextView;
    private TextView discountTextView;
    private TextView totalTextView;

    private String subtotalString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create references to widgets
        subtotalEditText = findViewById(R.id.input_editText);
        percentTextView = findViewById(R.id.percent_textView);
        discountTextView = findViewById(R.id.discount_textView);
        totalTextView = findViewById(R.id.total_textView);

        subtotalEditText.setOnEditorActionListener(this);
    }


    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent){
        calculateAndDisplay();
        return false;
    }

    private void calculateAndDisplay(){
        //get subtotal
        subtotalString = subtotalEditText.getText().toString();
        float subtotal;
        if(subtotalString.equals("")){
            subtotal = 0;
        }else {
            subtotal = Float.parseFloat(subtotalString);
        }

        //get the discount percent

        float discountPercent;

        if(subtotal >= 200){
            discountPercent = .2f;
        } else if (subtotal >= 100){
            discountPercent = .1f;
        } else {
            discountPercent = 0;
        }

        //calculate
        float discountAmount = subtotal * discountPercent;
        float total = subtotal - discountAmount;

        //display the data on the layout
        NumberFormat percent = NumberFormat.getPercentInstance();
        percentTextView.setText(percent.format(discountPercent));

        //display discount amount and total
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        totalTextView.setText(currency.format(total));
        discountTextView.setText(currency.format(discountAmount));
    }


}
