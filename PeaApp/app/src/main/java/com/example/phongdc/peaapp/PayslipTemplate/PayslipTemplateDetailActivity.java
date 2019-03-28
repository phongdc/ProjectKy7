package com.example.phongdc.peaapp.PayslipTemplate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.phongdc.peaapp.AsyncHttpClient.HttpUtils;
import com.example.phongdc.peaapp.Home.HomeActivity;
import com.example.phongdc.peaapp.R;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Adapter.PayrollRecyclerAdapter;
import Model.Payroll;
import cz.msebera.android.httpclient.Header;

public class PayslipTemplateDetailActivity extends AppCompatActivity {
    private RecyclerView rv_Payroll;
    private int payslipID;
    private TextView tvTitle;
    private TextView tvPayslipName;
    private List<Payroll> payrollList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payslip_template_detail);
        Bundle extras = this.getIntent().getExtras();
        tvTitle = findViewById(R.id.tvTitle);
        tvTitle.setText("Thông tin mẫu phiếu lương");
        tvPayslipName = findViewById(R.id.tvPayslipTemplateName);
        payslipID = extras.getInt("ID");
        rv_Payroll = findViewById(R.id.rv_PayrollDetails);
        rv_Payroll.setLayoutManager(new LinearLayoutManager(this));
        getDetail();
    }
    private void getDetail(){
        payrollList = new ArrayList<>();
        String token = HomeActivity.getToken();
        HttpUtils.getAuth("payslip_template?id="+payslipID,token,null, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONObject data = (JSONObject) response.get("data");
                        tvPayslipName.setText(data.getString("name"));
                        JSONArray listPayroll = data.getJSONArray("list_payroll_detail");
                        for (int j = 0; j < listPayroll.length(); j++) {
                            JSONObject obj = listPayroll.getJSONObject(j);
                            Payroll payroll = new Payroll();
                            payroll.setId(obj.getInt("id"));
                            payroll.setName(obj.getString("name"));
                            payroll.setSelected(obj.getBoolean("check"));
                            payrollList.add(payroll);
                        }
                        rv_Payroll.setAdapter(new PayrollRecyclerAdapter(payrollList, PayslipTemplateDetailActivity.this));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}
