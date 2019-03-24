package com.example.phongdc.peaapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class LoginActivity extends AppCompatActivity {

    EditText username;
    EditText password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText)findViewById(R.id.edtUserName);
        password = (EditText)findViewById(R.id.edtPassWord);

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

        asyncHttpClient.post("http://payroll.unicode.edu.vn/api/user/login", params, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                loading.dismiss();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                loading.dismiss();
                Toast.makeText(LoginActivity.this,"Wrong username or password",Toast.LENGTH_SHORT ).show();
                password.setText("");
            }


            @Override
            public void onRetry(int retryNo) {
                super.onRetry(retryNo);
            }
        });
    }
}
