package com.example.phongdc.peaapp.Login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.phongdc.peaapp.HomeActivity;
import com.example.phongdc.peaapp.AsyncHttpClient.HttpUtils;
import com.example.phongdc.peaapp.R;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class LoginActivity extends AppCompatActivity {
private EditText edtUserName;
private EditText edtPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtUserName = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
    }

    public void clickToLogin(View view) {
        //startActivity(new Intent(LoginActivity.this, HomeActivity.class));

        RequestParams params = new RequestParams();
        params.put("email",edtUserName.getText().toString());
        params.put("password", edtPassword.getText().toString());
        params.setUseJsonStreamer(true);
        HttpUtils.post("user/login", params, new JsonHttpResponseHandler() {
            String status = "";
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    status = response.getString("message");
                    Toast.makeText(LoginActivity.this, status, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                try {
                  status = errorResponse.getString("message");
                    Toast.makeText(LoginActivity.this, status, Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
