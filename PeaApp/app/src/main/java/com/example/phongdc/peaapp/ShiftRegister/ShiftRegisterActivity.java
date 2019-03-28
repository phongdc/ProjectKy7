package com.example.phongdc.peaapp.ShiftRegister;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phongdc.peaapp.AsyncHttpClient.HttpUtils;
import com.example.phongdc.peaapp.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import Model.Employee;
import Model.PayslipTemplate;
import Model.TimeFrame;
import cz.msebera.android.httpclient.Header;

public class ShiftRegisterActivity  extends AppCompatActivity implements View.OnClickListener {
    private EditText edtShiftFromDate;
    private EditText edtShiftTodate;
    private DatePickerDialog shiftFromDatePickerDialog;
    private DatePickerDialog shiftToDatePickerDialog;
    private SimpleDateFormat dateFormat;
    private TextView tvTitle;
    private List<Employee> empList;
    private List<String> empName;
    private List<TimeFrame> timeFrameList;
    private List<String> timeFrameName;
    private int empID;
    private int timeFrameID;
    Spinner spnEmployee;
    Spinner spnTimeFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shift_register);
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        findViewsById();

        setDateTimeField();
        getEmployee();
        getTimeFrame();

        spnEmployee.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String template = spnEmployee.getItemAtPosition(spnEmployee.getSelectedItemPosition()).toString();

                for (int a = 0; a < empList.size(); a++){
                    if (empList.get(i).getEmployee_name().matches(template)){
                        empID = (empList.get(i).getId());
//                        Toast.makeText(getApplicationContext(),empID,Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        spnTimeFrame.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String template = spnTimeFrame.getItemAtPosition(spnTimeFrame.getSelectedItemPosition()).toString();

                for (int a = 0; a < timeFrameList.size(); a++){
                    if (timeFrameList.get(i).getName().matches(template)){
                        timeFrameID = (timeFrameList.get(i).getId());
//                        Toast.makeText(getApplicationContext(),timeFrameID,Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    private void findViewsById() {
        edtShiftFromDate = (EditText) findViewById(R.id.edtShiftFromdate);
        edtShiftFromDate.setInputType(InputType.TYPE_NULL);
        edtShiftFromDate.requestFocus();

        edtShiftTodate = (EditText) findViewById(R.id.edtShiftTodate);
        edtShiftTodate.setInputType(InputType.TYPE_NULL);

        tvTitle = findViewById(R.id.tvTitle);
        tvTitle.setText("Đăng Kí Giờ Làm");

        empList = new ArrayList<Employee>();
        empName = new ArrayList<>();
        timeFrameList = new ArrayList<TimeFrame>();
        timeFrameName = new ArrayList<>();

        spnEmployee = (Spinner)findViewById(R.id.spnEmployee);
        spnTimeFrame = (Spinner)findViewById(R.id.spnTimeFrame);
    }

    private void setDateTimeField(){
        edtShiftFromDate.setOnClickListener(this);
        edtShiftTodate.setOnClickListener(this);

        Calendar calendar = Calendar.getInstance();
        shiftFromDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, month, dayOfMonth);
                edtShiftFromDate.setText(dateFormat.format(newDate.getTime()));

            }
        },calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        shiftToDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, month, dayOfMonth);
                edtShiftTodate.setText(dateFormat.format(newDate.getTime()));

            }
        },calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    public void onClick(View v) {
        if(v == edtShiftFromDate) {
            shiftFromDatePickerDialog.show();
        } else if(v == edtShiftTodate) {
            shiftToDatePickerDialog.show();
        }
    }
    public void clickToAddShift(View view) {
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        final RequestParams params = new RequestParams();
        JSONObject object = new JSONObject();

        String from = edtShiftFromDate.getText().toString();
//        String to = edtShiftTodate.getText().toString();
//        String from = "2010-07-03";
//        String to = "2012-08-04";

        params.put("employee_id", 2);
        params.put("start_time",from);
        params.put("end_time", from);
        params.put("time_frame_id", timeFrameID);

        params.setUseJsonStreamer(true);

        asyncHttpClient.post("http://payroll.unicode.edu.vn/api/shift_register", params, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
//                Intent intent = new Intent(ShiftRegisterActivity.this, GetPeriodActivity.class);
//                startActivity(intent);
                Toast.makeText(ShiftRegisterActivity.this,"Thêm Thành Công",Toast.LENGTH_SHORT ).show();

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(ShiftRegisterActivity.this,"Thêm that bai",Toast.LENGTH_SHORT ).show();
//                Toast.makeText(ShiftRegisterActivity.this,params.toString(),Toast.LENGTH_SHORT ).show();
            }


            @Override
            public void onRetry(int retryNo) {
                super.onRetry(retryNo);
            }
        });
    }

    public void getTimeFrame(){

        HttpUtils.getByUrl("http://payroll.unicode.edu.vn/api/time_frame", null, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                try {
                    JSONObject json = new JSONObject(response.toString());
                    JSONArray jArray = json.getJSONArray("data");

                    for (int i = 0; i < jArray.length(); i++) {
                        TimeFrame timeFrame = new TimeFrame();
                        JSONObject object = jArray.getJSONObject(i);
                        timeFrame.setId(object.getInt("id"));
                        timeFrame.setName(object.getString("name"));
                        timeFrameList.add(timeFrame);
                        timeFrameName.add(object.getString("name"));
                    }
                    spnTimeFrame.setAdapter(new ArrayAdapter<>(ShiftRegisterActivity.this, android.R.layout.simple_spinner_dropdown_item, timeFrameName));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void getEmployee(){

        HttpUtils.getByUrl("http://payroll.unicode.edu.vn/api/employee", null, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                try {
                    JSONObject json = new JSONObject(response.toString());
                    JSONArray jArray = json.getJSONArray("data");

                    for (int i = 0; i < jArray.length(); i++) {
                        Employee employee = new Employee();
                        JSONObject object = jArray.getJSONObject(i);
                        employee.setId(object.getInt("id"));
                        employee.setEmployee_name(object.getString("employee_name"));
                        empList.add(employee);
                        empName.add(object.getString("employee_name"));
                    }
                    spnEmployee.setAdapter(new ArrayAdapter<>(ShiftRegisterActivity.this, android.R.layout.simple_spinner_dropdown_item, empName));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }



}
