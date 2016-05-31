package com.ssru.toshihiro.ssrushopbook;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class MainActivity extends AppCompatActivity {

    //Ecplicit
    private Mymanage mymanage;
    private static final String urlJSON = "http://swiftcodingthai.com/ssru/get_user_toshihiro.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mymanage = new Mymanage(MainActivity.this);

        //Test Add Value to SQLite
        //mymanage.addNewUser("name", "sur", "user", "pass","money");

        //Delete All userTABLE
        deleteAlluserTABLE();

        synJSONtoSQLite();




    }   //  Main Method

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

            Log.d("31May", "JSON ==> " + s);

            try {

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
