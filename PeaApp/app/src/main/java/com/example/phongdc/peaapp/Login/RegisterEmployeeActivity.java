package com.example.phongdc.peaapp.Login;

import android.content.Intent;
import android.service.autofill.RegexValidator;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.phongdc.peaapp.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import customfonts.MyEditText;
import cz.msebera.android.httpclient.Header;

public class RegisterEmployeeActivity extends AppCompatActivity {
    private MyEditText tvNameRegister, tvMailRegister, tvPasswordRegister, tvPhoneRegister, tvAddressRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_employee);

        tvAddressRegister = findViewById(R.id.tvAddressRegister);
        tvMailRegister = findViewById(R.id.tvMailRegister);
        tvPasswordRegister = findViewById(R.id.tvPasswordRegister);
        tvPhoneRegister = findViewById(R.id.tvPhoneRegister);
        tvNameRegister = findViewById(R.id.tvNameRegister);

    }
    private boolean valid(String data){
        if(data == "" || data.isEmpty()){
            return false;
        }
        return true;
    }
    private boolean passWordValid(String data){
        if(!data.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")){
            Toast.makeText(RegisterEmployeeActivity.this, "Xin điền đúng mật khẩu !!!", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    public void clickToCreateAccountEmployee(View view) {
        String mEmail = tvMailRegister.getText().toString();
        String mPassword = tvPasswordRegister.getText().toString();
        String mName = tvNameRegister.getText().toString();
        String mAddress = tvAddressRegister.getText().toString();
        String mPhone = tvPhoneRegister.getText().toString();
        if (valid(mEmail)  && valid(mPassword) && valid(mName) && valid(mAddress) && valid(mPhone) && passWordValid(mPassword)) {
            AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
            final RequestParams params = new RequestParams();

            params.put("email", mEmail);
            params.put("password", mPassword);
            params.put("store_id", 1);
            params.put("name", mName);
            params.put("address", mAddress);
            params.put("phone", mPhone);
            params.setUseJsonStreamer(true);

            asyncHttpClient.post("http://payroll.unicode.edu.vn/api/user/register", params, new JsonHttpResponseHandler() {
                @Override
                public void onStart() {
                }



                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    try {
                        if (response.getBoolean("success") == false) {
                            Toast.makeText(RegisterEmployeeActivity.this, "Tạo thất bại", Toast.LENGTH_SHORT).show();
                        }else {
                        Toast.makeText(RegisterEmployeeActivity.this, "Tạo thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegisterEmployeeActivity.this, LoginActivity.class);
                        startActivity(intent);}
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    super.onFailure(statusCode, headers, throwable, errorResponse);
                }

                @Override
                public void onRetry(int retryNo) {
                    super.onRetry(retryNo);
                }
            });
        }else {
            Toast.makeText(RegisterEmployeeActivity.this, "Xin điền đầy đủ !!", Toast.LENGTH_SHORT).show();
        }

    }
}
