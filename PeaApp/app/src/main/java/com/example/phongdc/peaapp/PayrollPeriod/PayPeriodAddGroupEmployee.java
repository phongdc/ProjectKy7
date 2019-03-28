package com.example.phongdc.peaapp.PayrollPeriod;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.phongdc.peaapp.AsyncHttpClient.HttpUtils;
import com.example.phongdc.peaapp.Home.HomeActivity;
import com.example.phongdc.peaapp.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Model.EmpGroup;
import Model.Employee;
import Model.PayslipTemplate;
import cz.msebera.android.httpclient.Header;

public class PayPeriodAddGroupEmployee extends AppCompatActivity {

    private int id;
    private String name;
    private List<EmpGroup> empGroups;
    private List<String> empGroupsName;
    LinearLayout.LayoutParams checkParams;
    RadioGroup rdGroupEmp;
    RadioButton rdEmp;
    int selectedId;
    private String token;
//    Spinner spnEmpGroup;
//    private int empGroupID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_period_add_group_emp);
        token = HomeActivity.getToken();
        Bundle extras = this.getIntent().getExtras();
        id = extras.getInt("ID");
        name = extras.getString("NAME");

//        spnEmpGroup = (Spinner)findViewById(R.id.spnEmpGroup);
        empGroupsName = new ArrayList<>();
        empGroups = new ArrayList<EmpGroup>();

        checkParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        checkParams.setMargins(10, 10, 10, 10);
        checkParams.gravity = Gravity.LEFT;

        rdGroupEmp = (RadioGroup)findViewById(R.id.radioGroupEmp);

        getGroupEmployee();

        rdGroupEmp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                selectedId = rdGroupEmp.getCheckedRadioButtonId();
            }
        });

//        spnEmpGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                String empGroup = spnEmpGroup.getItemAtPosition(spnEmpGroup.getSelectedItemPosition()).toString();
//
//                for (int a = 0; a < empGroups.size(); a++){
//                    if (empGroups.get(i).getName().matches(empGroup)){
//                        empGroupID = (empGroups.get(i).getId());
//                    }
//                }
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//                // DO Nothing here
//            }
//        });

    }

    public void getGroupEmployee(){

        HttpUtils.getByUrlAuth("http://payroll.unicode.edu.vn/api/employee_group",token, null, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                try {
                    JSONObject json = new JSONObject(response.toString());
                    JSONArray jArray = json.getJSONArray("data");

                    for (int i = 0; i < jArray.length(); i++) {
                        EmpGroup empGroup = new EmpGroup();
                        JSONObject object = jArray.getJSONObject(i);
                        empGroup.setId(object.getInt("id"));
                        empGroup.setName(object.getString("name"));

                        RadioButton rd = new RadioButton(PayPeriodAddGroupEmployee.this);
                        rd.setId(object.getInt("id"));
                        rd.setText(object.getString("name"));
                        rd.setTextSize(20);
                        rdGroupEmp.addView(rd, checkParams);

                        empGroups.add(empGroup);
//                        empGroupsName.add(object.getString("name"));

                    }
//                    spnEmpGroup.setAdapter(new ArrayAdapter<>(PayPeriodAddGroupEmployee.this, android.R.layout.simple_spinner_dropdown_item, empGroupsName));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }



    public void clickToAddGroupEmp(View view) {
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        final RequestParams params = new RequestParams();

        rdEmp = (RadioButton) findViewById(selectedId);

        params.put("period_apply_id", id);
        params.put("list_emp", "");
        params.put("group_emp", rdEmp.getId());

        params.setUseJsonStreamer(true);

        HttpUtils.postByUrlAuth("http://payroll.unicode.edu.vn/api/payroll_period/apply",token, params, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Toast.makeText(PayPeriodAddGroupEmployee.this,"Thêm Thành Công",Toast.LENGTH_SHORT ).show();

//                Toast.makeText(PayPeriodAddGroupEmployee.this, "a " + rdEmp.getId(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(PayPeriodAddGroupEmployee.this,"Thêm that bai",Toast.LENGTH_SHORT ).show();
            }


            @Override
            public void onRetry(int retryNo) {
                super.onRetry(retryNo);
            }
        });
    }



}
