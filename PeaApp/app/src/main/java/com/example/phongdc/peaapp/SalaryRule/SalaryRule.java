package com.example.phongdc.peaapp.SalaryRule;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.phongdc.peaapp.AsyncHttpClient.HttpUtils;
import com.example.phongdc.peaapp.R;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Adapter.SalaryRuleAdapter;
import cz.msebera.android.httpclient.Header;

public class SalaryRule extends AppCompatActivity {
    private List<Model.SalaryRule> salaryRuleList;
    private TextView tvTitle;
    private RecyclerView rv_SalaryRule;
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salary_rule);
        findViewById();
        rv_SalaryRule.setLayoutManager(new LinearLayoutManager(this));
        Bundle extras = this.getIntent().getExtras();
        id = extras.getInt("salaryGroupID");
    }
    private void findViewById(){
        salaryRuleList = new ArrayList<>();
        tvTitle = findViewById(R.id.tvTitle);
        tvTitle.setText("Quy định lương");
        rv_SalaryRule = findViewById(R.id.rv_SalaryRule);
        getSalaryRule();
    }
    private void getSalaryRule(){
        HttpUtils.get("salary_rule", null, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                       Model.SalaryRule salary = new Model.SalaryRule();
                        JSONObject object = response.getJSONObject(i);
                        salary.setId(object.getInt("id"));
                        salary.setName(object.getString("name"));
                        salary.setDuration_min(object.getInt("duration_min"));
                        salary.setDuration_max(object.getInt("duration_max"));
                        salary.setValue(object.getInt("value"));
                        salary.setRate_value(object.getDouble("rate_value"));
                        salaryRuleList.add(salary);
                    }
                    rv_SalaryRule.setAdapter(new SalaryRuleAdapter(SalaryRule.this, salaryRuleList));
                }catch (Exception e){
                e.printStackTrace();}
            }
        });
    }

    public void clickToCreateSalaryRule(View view) {
        startActivity(new Intent(SalaryRule.this, CreateSalaryRule.class));
    }

}
