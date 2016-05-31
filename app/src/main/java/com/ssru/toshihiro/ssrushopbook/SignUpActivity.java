package com.ssru.toshihiro.ssrushopbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class SignUpActivity extends AppCompatActivity {

    //Explicit
    private EditText nameEditText, surnameEditText,
            userEditText , passEditText;

    private String nameString, surnameString , userString , passString;

    private static final String urlUpload ="http://swiftcodingthai.com/ssru/add_user_master.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Bind Widget
        nameEditText = (EditText) findViewById(R.id.editText);
        surnameEditText = (EditText) findViewById(R.id.editText2);
        userEditText = (EditText) findViewById(R.id.editText3);
        passEditText = (EditText) findViewById(R.id.editText4);


    }   //  Main  Method

    public void clickSignUpSign(View view) {

        nameString = nameEditText.getText().toString().trim();
        surnameString = surnameEditText.getText().toString().trim();
        userString = userEditText.getText().toString().trim();
        passString = passEditText.getText().toString().trim();

        //Check Space

        if (nameString.equals("") || surnameString.equals("") || userString.equals("") || passString.equals("")) {

            //Have Space
            Myalert myAlert = new Myalert();
            myAlert.myDialog(this,"มีช่องว่าง" , "กรุณากดทุกช่อง เว้ยเห้ยยย");
        } else {
            urlUpload();

            //No Space

        }


    }   //  Click  Sign

    private void urlUpload() {
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormEncodingBuilder().add("isAdd", "true")
                .add("Name", nameString).add("Surname", surnameString).add("User", userString).add("Password", passString).build();
        Request.Builder builder = new Request.Builder();
        Request request = builder.url(urlUpload).post(requestBody).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                finish();
            }
        });



    }   //  UploadNewUser

}   //  Main Class
