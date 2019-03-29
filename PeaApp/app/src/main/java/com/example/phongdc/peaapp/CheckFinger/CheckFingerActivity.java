package com.example.phongdc.peaapp.CheckFinger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phongdc.peaapp.AsyncHttpClient.HttpUtils;
import com.example.phongdc.peaapp.Home.HomeEmployee;
import com.example.phongdc.peaapp.R;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

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
        String token = HomeEmployee.getToken();
        String code = HomeEmployee.getUserCode();
        RequestParams params = new RequestParams();
        params.put("store_id", 1);
        params.put("code", code);
        params.setUseJsonStreamer(true);
        HttpUtils.postAuth("check_finger",token, params, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    if (response.getInt("result-code") == 13) {
                        Toast.makeText(CheckFingerActivity.this, "Vui lòng đăng nhập wifi công ty!", Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(CheckFingerActivity.this, "Xin cảm ơn", Toast.LENGTH_SHORT).show();

                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

        });
    }

}
