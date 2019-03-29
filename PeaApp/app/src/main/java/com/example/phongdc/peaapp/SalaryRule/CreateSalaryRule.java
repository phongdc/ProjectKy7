package com.example.phongdc.peaapp.SalaryRule;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phongdc.peaapp.AsyncHttpClient.HttpUtils;
import com.example.phongdc.peaapp.Home.HomeActivity;
import com.example.phongdc.peaapp.R;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import Model.TimeMode;
import cz.msebera.android.httpclient.Header;

public class CreateSalaryRule extends AppCompatActivity {
    private TextView tvTitle;
    private EditText edtSalaryRuleName;
    private EditText edtSalaryValue;
    private EditText edtDurationMin;
    private EditText edtDurationMax;
    private EditText edtRateValue;
    private Spinner spnTimeMode;
    private String token;
    private List<TimeMode> timeModeList;
    private List<String> timeModeNameList;
    private int timemodeId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_salary_rule);
        findViewById();
        token = HomeActivity.getToken();
        getTimeMode();
        spnTimeMode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String timemode = spnTimeMode.getItemAtPosition(spnTimeMode.getSelectedItemPosition()).toString();
                for (int j = 0; j < timeModeList.size(); j++) {
                    if(timeModeList.get(position).getTime_mode_name().matches(timemode)){
                        timemodeId = timeModeList.get(position).getId();
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void findViewById(){
        tvTitle = findViewById(R.id.tvTitle);
        tvTitle.setText("Tạo quy định lương");
        edtSalaryRuleName = findViewById(R.id.edtSalaryRuleName);
        edtSalaryValue = findViewById(R.id.edtSalaryValue);
        edtDurationMin = findViewById(R.id.edtDurationMin);
        edtDurationMax = findViewById(R.id.edtDurationMax);
        edtRateValue = findViewById(R.id.edtRateValue);
        spnTimeMode = findViewById(R.id.spnTimeMode);
    }
    private void getTimeMode(){
        timeModeList = new ArrayList<>();
        timeModeNameList = new ArrayList<>();
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,timeModeNameList);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        HttpUtils.getAuth("time_mode", token,null, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject object= response.getJSONObject(i);
                        TimeMode timeMode = new TimeMode();
                        timeMode.setId(object.getInt("id"));
                        timeMode.setTime_mode_name(object.getString("time_mode_name"));
                        timeModeList.add(timeMode);
                        timeModeNameList.add(object.getString("time_mode_name"));
                    }
                    spnTimeMode.setAdapter(adapter);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
    public void clickToCreateSalary(View view) {
        RequestParams params = new RequestParams();
        params.put("name", edtSalaryRuleName.getText().toString());
        params.put("timemode_id", timemodeId);
        params.put("duration_min", edtDurationMin.getText().toString());
        params.put("duration_max", edtDurationMax.getText().toString());
        params.put("fix_value", edtSalaryValue.getText().toString());
        params.put("rate_value", edtRateValue.getText().toString());
        params.put("brand_id", 5);
        params.setUseJsonStreamer(true);
        HttpUtils.postAuth("salary_rule",token, null, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Toast.makeText(CreateSalaryRule.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
}
