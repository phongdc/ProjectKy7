package com.example.phongdc.peaapp.Login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phongdc.peaapp.EmployeeGroups.GetEmpGroupActivity;
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

import customfonts.MyEditText;
import customfonts.MyTextView;
import cz.msebera.android.httpclient.Header;

public class LoginActivity extends AppCompatActivity {

    MyEditText username;
    MyEditText password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.txtUsername);
        password = findViewById(R.id.txtPassword);

    }



    public void clickToLogin(View view) {
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        String n = username.getText().toString().trim();
        String p = password.getText().toString().trim();

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
                        JSONObject jsonObject =  (JSONObject) response.get("data");
                            JSONArray role = jsonObject.getJSONArray("role");
                            String token = jsonObject.getString("token");
                        Bundle bundle = new Bundle();

                        bundle.putString("token", token);
                        for (int i = 0; i < role.length() ; i++) {
                            String mRole = role.getString(i);

                            if (mRole.matches("Administrator")) {
                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                intent.putExtras(bundle);
                                startActivity(intent);
                                finish();

                            }}
                            JSONObject EmpObject =  (JSONObject) jsonObject.get("employee");
                                String code = EmpObject.getString("code");
                                String userName = EmpObject.getString("employee_name");
                                int userId = EmpObject.getInt("id");
                                bundle.putInt("UserID", userId);
                                bundle.putString("Code", code);
                                bundle.putString("UserName", userName);
                                Intent intent = new Intent(LoginActivity.this, HomeEmployee.class);
                                intent.putExtras(bundle);
                                startActivity(intent);

                            }

                        else {
                        Toast.makeText(LoginActivity.this, "Sai tài khoản hoặc mật khẩu !", Toast.LENGTH_SHORT).show();
                        password.setText("");
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Toast.makeText(LoginActivity.this, "Vui lòng thử lại", Toast.LENGTH_SHORT).show();

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

    public void clickToCreateAccount(View view) {
        startActivity(new Intent(LoginActivity.this, RegisterEmployeeActivity.class));
    }
}
