package com.example.phongdc.peaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import Adapter.PayPeriodRecyclerAdapter;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Model.PayrollPeriod;
import cz.msebera.android.httpclient.Header;

public class GetPeriodActivity extends AppCompatActivity {

    private TextView tvTitle;
    private RecyclerView rv_PayPeriod;
    private String nameApi = "PayrollPeriod";
    private List<PayrollPeriod> periodList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_preiod);
        findViewById();
        tvTitle.setText("Kỳ Lương");
        periodList = new ArrayList<>();
        rv_PayPeriod.setLayoutManager(new LinearLayoutManager(this));
        getPayPeriod();


    }
    private void findViewById(){
        tvTitle = findViewById(R.id.tvTitle);
        rv_PayPeriod = findViewById(R.id.rv_Period);

    }
    public void getPayPeriod(){
        HttpUtils.getByUrl("http://payroll.unicode.edu.vn/api/payroll_period", null, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                Toast.makeText(GetPeriodActivity.this,"abc",Toast.LENGTH_LONG ).show();
                try {
                    JSONObject json = new JSONObject(response.toString());
                    JSONArray jArray = json.getJSONArray("data");
                    for (int i = 0; i < jArray.length(); i++) {
                        PayrollPeriod period = new PayrollPeriod();
                        JSONObject object = jArray.getJSONObject(i);
                        period.setId(object.getInt("id"));
                        period.setName(object.getString("name"));
                        period.setFrom_date(object.getString("from_date"));
                        period.setTo_date(object.getString("to_date"));
                        periodList.add(period);

                    }
                    rv_PayPeriod.setAdapter(new PayPeriodRecyclerAdapter(periodList, GetPeriodActivity.this));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        }


    public void clickToAddPeriod(View view) {
        Intent intent = new Intent(GetPeriodActivity.this, CreatePayPeriod.class);
        startActivity(intent);
    }
}
