package com.example.phongdc.peaapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Model.SalaryRuleGroup;
import cz.msebera.android.httpclient.Header;

public class CreatePayrollDetailActivity extends AppCompatActivity {
    private TextView tvTitle;
    private EditText edtPayrollName;
    private Spinner spnPayrollCategory;
    private EditText edtValue;
    private Spinner spnSalaryRuleGroup;
    private EditText edtDescription;
    private int salaryRuleID;
    private List<SalaryRuleGroup> salaryGroupList;
    private List<String>salaryGroupName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_payrolldetail_popup);
        findViewById();
        salaryGroupName = new ArrayList<>();
        getSalaryRuleGroup();
        spnSalaryRuleGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String salaryRuleGroup = spnSalaryRuleGroup.getItemAtPosition(spnSalaryRuleGroup.getSelectedItemPosition()).toString();
                for (int a = 0; a < salaryGroupList.size(); a++) {
                    if(salaryGroupList.get(position).getName().matches(salaryRuleGroup)){
                        salaryRuleID = salaryGroupList.get(position).getId();
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
    }
    private void findViewById(){
        edtPayrollName = findViewById(R.id.edtPayrollName);
        spnPayrollCategory = findViewById(R.id.spnPayrollCategory);
        edtValue = findViewById(R.id.edtValue);
        spnSalaryRuleGroup = findViewById(R.id.spnSalaryRuleGroup);
        edtDescription = findViewById(R.id.edtDescription);
        tvTitle = findViewById(R.id.tvTitle);
        tvTitle.setText("Tạo bảng lương");

    }
    private void getSalaryRuleGroup(){
        salaryGroupList = new ArrayList<>();
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,salaryGroupName);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        HttpUtils.get("salary_rule_group", null,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                try{
                    for (int i = 0; i < response.length(); i++) {
                        SalaryRuleGroup salaryRuleGroup = new SalaryRuleGroup();
                        JSONObject object = response.getJSONObject(i);
                        salaryRuleGroup.setId(object.getInt("id"));
                        salaryRuleGroup.setName(object.getString("name"));
                        salaryGroupList.add(salaryRuleGroup);
                        salaryGroupName.add(object.getString("name"));

                    } spnSalaryRuleGroup.setAdapter(adapter);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });


    }

    public void clickToCancel(View view) {
        finish();
    }

    public void clickToCreate(View view) {
        int mValue = Integer.parseInt(edtValue.getText().toString());
        String mName = edtPayrollName.getText().toString();
        String mDescriptiop = edtDescription.getText().toString();
        RequestParams params = new RequestParams();

        params.put("name",mName );
        params.put("payroll_detail_category_id", 7);
        params.put("value", mValue);
        params.put("salary_rule_group_id", salaryRuleID);
        params.put("description", mDescriptiop);
        params.setUseJsonStreamer(true);
        HttpUtils.post("payroll_detail", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Toast.makeText(CreatePayrollDetailActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
}
