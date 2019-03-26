package com.example.phongdc.peaapp.Login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.phongdc.peaapp.Home.HomeActivity;
import com.example.phongdc.peaapp.Home.HomeEmployee;
import com.example.phongdc.peaapp.MainActivity;
import com.example.phongdc.peaapp.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.squareup.moshi.Json;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class LoginActivity extends AppCompatActivity {

    EditText username;
    EditText password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText)findViewById(R.id.edtUsername);
        password = (EditText)findViewById(R.id.edtPassword);

    }



    public void clickToLogin(View view) {
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        String n = username.getText().toString();
        String p = password.getText().toString();

        final ProgressDialog loading = new ProgressDialog(LoginActivity.this);
        loading.setMessage("Login....");
        loading.show();

        params.put("email", n);
        params.put("password",p);

        params.setUseJsonStreamer(true);

        asyncHttpClient.post("http://payroll.unicode.edu.vn/api/user/login", params, new JsonHttpResponseHandler() {
            @Override
            public void onStart() {
            }

//            @Override
//            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
//
//            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                loading.dismiss();
                try {
                    if ((response.getBoolean("success")) == true) {
                        String data = response.getString("role");
                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            startActivity(intent);
                        }
                        else {
                        Toast.makeText(LoginActivity.this, "Wrong username or password", Toast.LENGTH_SHORT).show();
                        password.setText("");
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                loading.dismiss();

            }

//            @Override
//            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
//
//            }


            @Override
            public void onRetry(int retryNo) {
                super.onRetry(retryNo);
            }
        });
    }
}
