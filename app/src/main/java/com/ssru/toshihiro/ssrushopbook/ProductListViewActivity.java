package com.ssru.toshihiro.ssrushopbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class ProductListViewActivity extends AppCompatActivity {


    //Explicit
    private TextView nameTextView , surnameTextView , moneyTextView;
    private ListView listView;

    private String[] loginString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list_view);


        //Bind Widget
        nameTextView = (TextView) findViewById(R.id.textView7);
        surnameTextView = (TextView) findViewById(R.id.textView8);
        moneyTextView = (TextView) findViewById(R.id.textView9);
        listView = (ListView) findViewById(R.id.listView);

        //Receive Value From Intent
        loginString = getIntent().getStringArrayExtra("Login ");

        //Show String
        nameTextView.setText(loginString[1]);
        surnameTextView.setText(loginString[2]);
        moneyTextView.setText(loginString[5] + " THB.");


    }//Main Method


}// Main Class
