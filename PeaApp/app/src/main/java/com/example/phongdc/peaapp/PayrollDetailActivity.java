package com.example.phongdc.peaapp;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import Adapter.PayrollRecyclerAdapter;
import Model.Payroll;
import Model.SalaryRuleGroup;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

public class PayrollDetailActivity extends AppCompatActivity {

    private TextView tvTitle;
    private RecyclerView rv_payroll;
    private List<Payroll> payrolls;
    PopupWindow popupWindow;
    private String nameApi = "payroll_detail";
    private List<SalaryRuleGroup> salaryGroupList;
    private Spinner spnSalaryRuleGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payroll_detail);
        findViewById();
        tvTitle.setText("Bảng lương");
        payrolls = new ArrayList<>();
        rv_payroll.setLayoutManager(new LinearLayoutManager(this));
        getPayroll();
    }
    private void findViewById(){
        tvTitle = findViewById(R.id.tvTitle);
        rv_payroll = findViewById(R.id.rv_Payrolls);
    }
    private void getPayroll(){
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
                    rv_payroll.setAdapter(new PayrollRecyclerAdapter(payrolls, PayrollDetailActivity.this));
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });
    }
    private void getSalaryRuleGroup(){
        salaryGroupList = new ArrayList<>();
        spnSalaryRuleGroup = findViewById(R.id.spnSalaryRuleGroup);
        final ArrayAdapter<SalaryRuleGroup> adapter = new ArrayAdapter<SalaryRuleGroup>(this, android.R.layout.simple_spinner_item,salaryGroupList);
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
                    spnSalaryRuleGroup.setAdapter(adapter);

                }}catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
    private void createPayroll(){

    }
    private void callPopup() {

        LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);

        View popupView = layoutInflater.inflate(R.layout.create_payrolldetail_popup, null);

        popupWindow = new PopupWindow(popupView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);

        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);

        popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);

        ((Button) popupView.findViewById(R.id.btCreatePayrollDetail)).setOnClickListener(new View.OnClickListener() {

            @TargetApi(Build.VERSION_CODES.GINGERBREAD)
            public void onClick(View arg0) {


                popupWindow.dismiss();

            }

        });

        ((Button) popupView.findViewById(R.id.btCancel)).setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {

                popupWindow.dismiss();
            }
        });
    }

    public void clickToCreatePayrollDetail(View view) {
        callPopup();
        getSalaryRuleGroup();
    }
}
