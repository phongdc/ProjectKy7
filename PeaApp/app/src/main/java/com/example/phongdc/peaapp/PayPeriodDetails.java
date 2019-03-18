package com.example.phongdc.peaapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Adapter.PayPeriodRecyclerAdapter;
import Model.Employee;
import Model.PayrollPeriod;
import cz.msebera.android.httpclient.Header;

public class PayPeriodDetails extends AppCompatActivity {
    private TextView txtPeriodName;

    private int id;
    private String name;
    private List<Employee> empList;
    LinearLayout linearLayout;
    LinearLayout.LayoutParams checkParams;
    List<CheckBox > allCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_period_details);
        Bundle extras = this.getIntent().getExtras();
        id = extras.getInt("ID");
        name = extras.getString("NAME");
        txtPeriodName = findViewById(R.id.payperiodname);
        checkParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        checkParams.setMargins(10, 10, 10, 10);
        checkParams.gravity = Gravity.LEFT;
        allCheckBox = new ArrayList<CheckBox>();
        linearLayout = (LinearLayout) findViewById(R.id.EmpList);
        empList = new ArrayList<Employee>();
        txtPeriodName.setText(name);



//        getPayPeriodDetails();
        getEmployee();
    }

//    private void getPayPeriodDetails() {
//        AsyncHttpClient client = new AsyncHttpClient();
//        client.get("http://payroll.unicode.edu.vn/api/PayrollPeriod?empId=" + id , null, new JsonHttpResponseHandler(){
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                try {
//                    JSONObject jsonObject = new JSONObject(response.toString());
//                    String name = jsonObject.getString("name");
//                    txtPeriodName.setText(name);
//                }catch (Exception ex){
//                    ex.printStackTrace();
//                }
//            }
//
//        });
//    }



    public void getEmployee(){

        HttpUtils.getByUrl("http://payroll.unicode.edu.vn/api/employee", null, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                try {
                    JSONObject json = new JSONObject(response.toString());
                    JSONArray jArray = json.getJSONArray("data");

                    for (int i = 0; i < jArray.length(); i++) {
                        Employee emp = new Employee();
                        JSONObject object = jArray.getJSONObject(i);
                        emp.setId(object.getInt("id"));
                        emp.setEmployee_name(object.getString("employee_name"));
                        emp.setCode(object.getString("code"));

                        CheckBox cb = new CheckBox(PayPeriodDetails.this);
                        cb.setText(object.getString("employee_name"));
                        cb.setId(i);
                        linearLayout.addView(cb, checkParams);
                        allCheckBox.add(cb);

                        empList.add(emp);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void clickToAddEmp(View view) {
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        JSONObject object = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        for (int i = 0; i < allCheckBox.size(); i++){
            if (allCheckBox.get(i).isChecked() == true){
                jsonArray.put(empList.get(i).getId());
            }
        }

        params.put("period_apply_id", 0);
        params.put("list_emp", jsonArray);
        params.put("group_emp", 0);
        params.setUseJsonStreamer(true);

        asyncHttpClient.post("http://payroll.unicode.edu.vn/api/payroll_period/apply", params, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
//                GetPeriodActivity getPeriodActivity = new GetPeriodActivity();
//                getPeriodActivity.getPayPeriod();
                Toast.makeText(PayPeriodDetails.this,"Thêm thanh cong",Toast.LENGTH_SHORT ).show();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(PayPeriodDetails.this,"Thêm that bai",Toast.LENGTH_SHORT ).show();
            }


            @Override
            public void onRetry(int retryNo) {
                super.onRetry(retryNo);
            }
        });
    }



}