package com.example.phongdc.peaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Adapter.PayrollRecyclerAdapter;
import Model.Payroll;
import Model.PayslipTemplate;
import cz.msebera.android.httpclient.Header;


public class PaySlipTemplateActivity extends AppCompatActivity {
    private RecyclerView rv_Payroll;
    private TextView tvTitle;
    private List<Payroll> payrolls;
    private String nameApi = "PayrollDetail";
    private String nameApi1 = "PaySlipTemplate";
    //private Integer[] itemId;
    private List<Integer> array;
    private EditText edtTemplateName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_slip_template);
        findViewById();
        tvTitle.setText("Tạo mẫu phiếu lương");
        payrolls = new ArrayList<>();
        rv_Payroll.setLayoutManager(new LinearLayoutManager(this));
        getPayrolls();
    }
    private void findViewById(){
        rv_Payroll = findViewById(R.id.rv_PayrollDetails);
        tvTitle = findViewById(R.id.tvTitle);
        edtTemplateName = findViewById(R.id.edtTemplateName);

    }
    public void getPayrolls(){
       HttpUtils.get(nameApi, null, new JsonHttpResponseHandler() {
           @Override
           public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
               try {
                   for (int i = 0; i < response.length(); i++) {
                       Payroll payroll = new Payroll();
                       JSONObject object = response.getJSONObject(i);
                       payroll.setId(object.getInt("id"));
                       //payroll.setPayroll_detail_category_id(object.getInt("payroll_detail_category_id"));
                       //payroll.setSalary_rule_group_id(object.getInt("salary_rule_group_id"));
                       payroll.setName(object.getString("name"));
                       //payroll.setValue(object.getInt("value"));
                       //payroll.setDescription(object.getString("description"));
                       //payroll.setDefault_formula_id(object.getInt("default_formula_id"));
                       //payroll.setFrequency(object.getString("frequency"));
                       //payroll.setVariability(object.getString("variability"));
                      // payroll.setDestination(object.getString("destination"));
                       payrolls.add(payroll);
                   }
                   rv_Payroll.setAdapter(new PayrollRecyclerAdapter(payrolls, PaySlipTemplateActivity.this));
               }catch (Exception ex){
                   ex.printStackTrace();
               }
           }
       });

    }

    public void clickToCreate(View view) {
        array = new ArrayList<Integer>();

        for (int i = 0; i < PayrollRecyclerAdapter.payrolls.size(); i++) {

            try{
            if(PayrollRecyclerAdapter.payrolls.get(i).isSelected()){
                array.add( PayrollRecyclerAdapter.payrolls.get(i).getId());

            }}catch (Exception e){
                e.printStackTrace();
            }

        }
        //itemId = array.toArray(new Integer[array.size()]);
        JSONArray jsonArray = new JSONArray(array);
        RequestParams params = new RequestParams();
        params.put("name", edtTemplateName.getText().toString());
        params.put("list_parroll_detail",jsonArray);
        params.setUseJsonStreamer(true);
        HttpUtils.post(nameApi1, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Toast.makeText(PaySlipTemplateActivity.this,"Thêm thành công",Toast.LENGTH_SHORT ).show();
                finish();
                //startActivity(new Intent(PaySlipTemplateActivity.this, GetAllPayslipTemplateActivity.class));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(PaySlipTemplateActivity.this,"Thêm thất bại",Toast.LENGTH_SHORT ).show();
            }


        });
    }


}
