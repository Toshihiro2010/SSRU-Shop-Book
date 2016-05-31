package com.ssru.toshihiro.ssrushopbook;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    //Ecplicit
    private Mymanage mymanage;
    private static final String urlJSON = "http://swiftcodingthai.com/ssru/get_user_toshihiro.php";

    private EditText userEditText , passwordEditText;
    private String userString , passwordString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Bind Widget
        userEditText = (EditText) findViewById(R.id.editText5);
        passwordEditText = (EditText) findViewById(R.id.editText6);



        mymanage = new Mymanage(MainActivity.this);

        //Test Add Value to SQLite
        //mymanage.addNewUser("name", "sur", "user", "pass","money");

        //Delete All userTABLE
        deleteAlluserTABLE();

        synJSONtoSQLite();


    }   //  Main Method

    public void clickSignIn(View view) {

        userString = userEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();

        //check space
        if (userString.equals("") || passwordString.equals("")) {

            Myalert myalert = new Myalert();
            myalert.myDialog(this, "มีช่องว่างเห้ย มั่ววะ" , "กรอกทุกช่องสิฟะ");


        }

    }// clickSigIn



    private void synJSONtoSQLite() {
        ConnectUserTABLE connectUserTABLE = new ConnectUserTABLE();
        connectUserTABLE.execute();

    }   //  SynJson

    public class ConnectUserTABLE extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {

            try {
                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                Request request = builder.url(urlJSON).build();
                Response response = okHttpClient.newCall(request).execute();

                return response.body().string();

            } catch (Exception e) {
                Log.d("31May", "my Error ==> " + e.toString());
                return null;
            }


        }   //  doInBlack


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);


            try {
                Log.d("31May", "JSON ==> " + s);
                JSONArray jsonArray = new JSONArray(s);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String strName = jsonObject.getString(Mymanage.column_name);
                    String strSurname = jsonObject.getString(Mymanage.column_surname);
                    String strUser = jsonObject.getString(Mymanage.column_user);
                    String strPassword = jsonObject.getString(Mymanage.column_password);
                    String strMoney = jsonObject.getString(Mymanage.column_money);

                    mymanage.addNewUser(strName, strSurname, strUser, strPassword, strMoney);


                }//for

            } catch (Exception e) {
                e.printStackTrace();

            }


        }   // On Post


    }   //  Connect Class


    private void deleteAlluserTABLE() {
        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name, MODE_PRIVATE, null);

        sqLiteDatabase.delete(Mymanage.user_table, null, null);

    }

    public void clickSignUpMain(View view) {
        startActivity(new Intent(MainActivity.this, SignUpActivity.class));
    }
}   // Main Class
