package com.example.phongdc.peaapp.PayslipTemplate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import Adapter.Payslip_TemplateAdapter;
import Model.PayslipTemplate;
import cz.msebera.android.httpclient.Header;

public class GetAllPayslipTemplateActivity extends AppCompatActivity {
    private TextView tvTitle;
    private RecyclerView rv_Payslip_template;
    private List<PayslipTemplate> payslipTemplateList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_all_payslip_template);
        findViewById();
        tvTitle.setText("Mẫu phiếu lương");
        payslipTemplateList = new ArrayList<>();
        rv_Payslip_template.setLayoutManager(new LinearLayoutManager(this));
        getAllPayslipTemplate();
    }

    private void findViewById() {
        tvTitle = findViewById(R.id.tvTitle);
        rv_Payslip_template = findViewById(R.id.rv_Payslip_template);

    }

    public void getAllPayslipTemplate() {
        HttpUtils.get("payslip_template", null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                try {
                    payslipTemplateList.clear();
                    JSONArray data =(JSONArray)  response.get("data");
                    for (int i = 0; i < data.length(); i++) {
                        PayslipTemplate payslipTemplate = new PayslipTemplate();
                        JSONObject jsonObject = data.getJSONObject(i);
                        payslipTemplate.setId(jsonObject.getInt("id"));
                        payslipTemplate.setName(jsonObject.getString("name"));

                        payslipTemplateList.add(payslipTemplate);
                    }
                    rv_Payslip_template.setAdapter(new Payslip_TemplateAdapter(payslipTemplateList, GetAllPayslipTemplateActivity.this));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public void clickToCreateTemplate(View view) {
        startActivity(new Intent(this, PaySlipTemplateActivity.class));
        onStop();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        getAllPayslipTemplate();
    }
}
