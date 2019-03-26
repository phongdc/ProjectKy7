package com.example.phongdc.peaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.phongdc.peaapp.Home.HomeActivity;
import com.example.phongdc.peaapp.Login.LoginActivity;
import com.example.phongdc.peaapp.User.MyDetailActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

    }


}
