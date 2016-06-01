package com.ssru.toshihiro.ssrushopbook;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

public class ProductListViewActivity extends AppCompatActivity {


    //Explicit
    private TextView nameTextView, surnameTextView, moneyTextView;
    private ListView listView;

    private String[] loginString, nameStrings, priceStrings, coverStrings, eBookStrings;

    private String urlJSON = "http://swiftcodingthai.com/ssru/get_product.php";


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

        SynChronizeProduct synChronizeProduct = new SynChronizeProduct(this, urlJSON);
        synChronizeProduct.execute();


    }//Main Method

    private class SynChronizeProduct extends AsyncTask<Void, Void, String> {

        private Context context;
        private String urlString;
        private ProgressDialog progressDialog;

        public SynChronizeProduct(Context context, String urlString) {
            this.context = context;
            this.urlString = urlString;


        }//Constructor

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(context, "Load Product", "Load Product Process ...");


        }   //   onPre

        @Override
        protected String doInBackground(Void... params) {

            try {
                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                Request request = builder.url(urlString).build();
                Response response = okHttpClient.newCall(request).execute();

                return response.body().string();


            } catch (Exception e) {
                Log.d("1JuneV1", "doIn e ==> " + e.toString());
                return null;
            }


        }// doInback

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if (s.length() != 0) {

                progressDialog.dismiss();
                Log.d("1JuneV1", "s ==> " + s);


            }

            try {

                JSONArray jsonArray = new JSONArray(s);

                nameStrings = new String[jsonArray.length()];
                priceStrings = new String[jsonArray.length()];
                coverStrings = new String[jsonArray.length()];
                eBookStrings = new String[jsonArray.length()];

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    nameStrings[i] = jsonObject.getString("Name");
                    priceStrings[i] = jsonObject.getString("Price");
                    coverStrings[i] = jsonObject.getString("Cover");
                    eBookStrings[i] = jsonObject.getString("Ebook");


                }

                MyAdapter myAdapter = new MyAdapter(context, nameStrings, priceStrings, coverStrings);

                listView.setAdapter(myAdapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        if (checkMoney(priceStrings[position])) {

                            confirmDialog(nameStrings[position], priceStrings[position]);

                        } else {
                            Myalert myalert = new Myalert();
                            myalert.myDialog(context, "เงินไม่พอ", "ไปเติมเงินเพิ่ม หรือ ซื้อเล่มอื่นสิวะ");


                        }

                    }
                });


            } catch (Exception e) {
                Log.d("1JuneV2", "onPlost e ==>" + e.toString());
            }


        }// OnPost

        private boolean checkMoney(String priceString) {
            int myMoney = Integer.parseInt(loginString[5]);
            int intPrice = Integer.parseInt(priceString);

            if (myMoney >= intPrice) {
                return true;
            } else {
                return false;
            }

        }// Check Money


    }//SynChronize

    private void confirmDialog(String nameString, String priceString) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.icon_myaccount);
        builder.setCancelable(false);
        builder.setTitle("Confirm Order");
        builder.setMessage(nameString + " ราคา " + priceString + " THB." + "\n" + " คุณต้องการหนังสือเล่มนี้ใช่ หรือ ไม่");
        builder.setNegativeButton("ไม่", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.setPositiveButton("รับ Order", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        });
        builder.show();


    }// Confirm


}// Main Class
