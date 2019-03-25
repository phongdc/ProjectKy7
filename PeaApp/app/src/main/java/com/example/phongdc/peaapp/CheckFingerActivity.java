package com.example.phongdc.peaapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

public class CheckFingerActivity extends AppCompatActivity {
    TextView tvTitle;
    ImageView imgFingerPrint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_finger);
        tvTitle = findViewById(R.id.tvTitle);
        tvTitle.setText("Điểm danh");
        imgFingerPrint = findViewById(R.id.imgFingerPrint);
        imgFingerPrint.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                checkAttendent();
                return true;
            }
        });
    }
    private void checkAttendent(){
        RequestParams params = new RequestParams();
        params.put("store_id", 1);
        params.put("code", "0001");
        params.setUseJsonStreamer(true);
        HttpUtils.post("CheckFinger", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Toast.makeText(CheckFingerActivity.this, "Xin cảm ơn", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

}
