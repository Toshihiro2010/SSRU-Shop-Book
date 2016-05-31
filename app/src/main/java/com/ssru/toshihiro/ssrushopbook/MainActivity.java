package com.ssru.toshihiro.ssrushopbook;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    //Ecplicit
    private Mymanage mymanage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mymanage = new Mymanage(MainActivity.this);

        //Test Add Value to SQLite
        //mymanage.addNewUser("name", "sur", "user", "pass","money");

        //Delete All userTABLE
        deleteAlluserTABLE();


    }   //  Main Method

    private void deleteAlluserTABLE() {
        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name, MODE_PRIVATE, null);

        sqLiteDatabase.delete(Mymanage.user_table, null, null);

    }

    public void clickSignUpMain(View view) {
        startActivity(new Intent(MainActivity.this, SignUpActivity.class));
    }
}   // Main Class
