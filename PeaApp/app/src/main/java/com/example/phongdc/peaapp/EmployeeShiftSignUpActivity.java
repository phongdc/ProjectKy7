package com.example.phongdc.peaapp;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import Model.Employee;
import Model.TimeFrame;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

public class EmployeeShiftSignUpActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView tvTitle;

    private EditText datePick;

    private List<Employee> empList;
    private List<String> empListName;
    Spinner spnEmpList;
    int empListID;

    private List<TimeFrame> timeFrameList;

    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormat;

    LinearLayout linearLayoutDateList;
    LinearLayout linearLayoutTimeFrame;
    LinearLayout linearLayoutMinTime;
    LinearLayout linearLayoutMaxtime;
    LinearLayout.LayoutParams checkParams;
    List<CheckBox> allCheckBoxDate;
    List<CheckBox> allCheckBoxTimeFrame;
    List<CheckBox> allCheckBoxMinTime;
    List<CheckBox> allCheckBoxMaxTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_shift_sign_up);

        findViewsById();
        setDateTimeField();
        tvTitle.setText("Shift Register For Employee");

        checkParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        checkParams.setMargins(10, 10, 10, 10);
        checkParams.gravity = Gravity.LEFT;

        dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        getEmployee();
        getTimeFrame();
        spnEmpListener();


    }
    private void findViewsById() {
        tvTitle = findViewById(R.id.tvTitle);
        linearLayoutDateList = (LinearLayout) findViewById(R.id.dateList);
        linearLayoutTimeFrame = (LinearLayout) findViewById(R.id.timeFrameList);
        linearLayoutMinTime = (LinearLayout) findViewById(R.id.timeMinList);
        linearLayoutMaxtime = (LinearLayout) findViewById(R.id.timeMaxList);

        datePick = (EditText) findViewById(R.id.edtDate);
        datePick.setInputType(InputType.TYPE_NULL);
        datePick.requestFocus();

        empList = new ArrayList<Employee>();
        timeFrameList = new ArrayList<TimeFrame>();
        empListName = new ArrayList<>();
        allCheckBoxDate = new ArrayList<CheckBox>();
        allCheckBoxTimeFrame = new ArrayList<CheckBox>();
        allCheckBoxMinTime = new ArrayList<CheckBox>();
        allCheckBoxMaxTime = new ArrayList<CheckBox>();
        spnEmpList = (Spinner)findViewById(R.id.spnEmployeeShiftSignUp);

    }
    private void setDateTimeField(){
        datePick.setOnClickListener(this);

        Calendar calendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, month, dayOfMonth);
                datePick.setText(dateFormat.format(newDate.getTime()));
                for (int i = 0; i < 5; i++){
                    dayOfMonth++;
                    newDate.set(year, month, dayOfMonth);
                    CheckBox cb = new CheckBox(EmployeeShiftSignUpActivity.this);
                    cb.setText(dateFormat.format(newDate.getTime()));
                    cb.setTextSize(16);
                    linearLayoutDateList.addView(cb, checkParams);
                    allCheckBoxDate.add(cb);
                }
            }
        },calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

    }

    public void spnEmpListener(){
        spnEmpList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String empLists = spnEmpList.getItemAtPosition(spnEmpList.getSelectedItemPosition()).toString();

                for (int a = 0; a < empList.size(); a++){
                    if (empList.get(i).getEmployee_name().matches(empLists)){
                        empListID = (empList.get(i).getId());
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
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
                        timeFrame.setStart_time(object.getString("start_time"));
                        timeFrame.setEnd_time(object.getString("end_time"));
                        timeFrame.setDuration(object.getString("duration"));
                        timeFrame.setBreak_time(object.getString("break_time"));

                        CheckBox cb = new CheckBox(EmployeeShiftSignUpActivity.this);
                        cb.setText(object.getString("name"));
                        cb.setTextSize(18);
                        linearLayoutTimeFrame.addView(cb, checkParams);
                        allCheckBoxTimeFrame.add(cb);

                        timeFrameList.add(timeFrame);
                    }

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
                        Employee emp = new Employee();
                        JSONObject object = jArray.getJSONObject(i);
                        emp.setId(object.getInt("id"));
                        emp.setEmployee_name(object.getString("employee_name"));
                        emp.setCode(object.getString("code"));

                        empList.add(emp);
                        empListName.add(object.getString("name"));
                    }
                    spnEmpList.setAdapter(new ArrayAdapter<>(EmployeeShiftSignUpActivity.this, android.R.layout.simple_spinner_dropdown_item, empListName));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void clickToLoadTimeFrameTime(View view) {

        linearLayoutMinTime.removeAllViews();
        linearLayoutMaxtime.removeAllViews();

        for (int i = 0; i < allCheckBoxTimeFrame.size(); i++){
            if (allCheckBoxTimeFrame.get(i).isChecked() == true){
                CheckBox cbmin = new CheckBox(EmployeeShiftSignUpActivity.this);
                cbmin.setText(timeFrameList.get(i).getStart_time().toString());
                cbmin.setTextSize(18);
                linearLayoutMinTime.addView(cbmin, checkParams);
                allCheckBoxMinTime.add(cbmin);

                CheckBox cbmax = new CheckBox(EmployeeShiftSignUpActivity.this);
                cbmax.setText(timeFrameList.get(i).getEnd_time().toString());
                cbmax.setTextSize(18);
                linearLayoutMaxtime.addView(cbmax, checkParams);
                allCheckBoxMaxTime.add(cbmax);

            }
        }



    }

    public void clickToSignUpShiftForEmployee(View view) throws UnsupportedEncodingException {
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        final RequestParams params = new RequestParams();
        final RequestParams params2 = new RequestParams();

        JSONArray jsonArray = new JSONArray();
        JSONArray jsonArrayDate = new JSONArray();
        JSONArray jsonArrayTimeFrame = new JSONArray();
        JSONArray jsonArrayMinTime = new JSONArray();
        JSONArray jsonArrayMaxTime = new JSONArray();

        for (int i = 0; i < allCheckBoxDate.size(); i++){
            if (allCheckBoxDate.get(i).isChecked() == true){
                jsonArrayDate.put(allCheckBoxDate.get(i).getText().toString());
            }
        }

        for (int i = 0; i < allCheckBoxTimeFrame.size(); i++){
            if (allCheckBoxTimeFrame.get(i).isChecked() == true){
                jsonArrayTimeFrame.put(timeFrameList.get(i).getId());
            }
        }

        for (int i = 0; i < allCheckBoxMinTime.size(); i++){
            if (allCheckBoxMinTime.get(i).isChecked() == true){
                jsonArrayMinTime.put(allCheckBoxMinTime.get(i).getText().toString());

            }
        }

        for (int i = 0; i < allCheckBoxMaxTime.size(); i++){
            if (allCheckBoxMaxTime.get(i).isChecked() == true){
                jsonArrayMaxTime.put(allCheckBoxMaxTime.get(i).getText().toString());

            }
        }


//        params.put("employee_id", empListID);
        params.put("employee_id", 1);
        params.put("list_date", jsonArrayDate.toString());
        params.put("list_time_frame_id", jsonArrayTimeFrame.toString());
        params.put("list_shift_min", jsonArrayMinTime.toString());
        params.put("list_shift_max", jsonArrayMaxTime.toString());
        params.put("store_id", 1);
        params.put("brand_id", 1);

        jsonArray.put(params);
        params2.add("",jsonArray.toString());
        params2.setUseJsonStreamer(true);

        asyncHttpClient.post("http://payroll.unicode.edu.vn/api/attendance", params2, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Toast.makeText(EmployeeShiftSignUpActivity.this,"Thêm Thành Công",Toast.LENGTH_SHORT ).show();
//                Toast.makeText(PayPeriodAddGroupEmployee.this, "a " + rdEmp.getId(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(EmployeeShiftSignUpActivity.this,"Thêm that bai",Toast.LENGTH_SHORT ).show();
                Toast.makeText(EmployeeShiftSignUpActivity.this,params2.toString(),Toast.LENGTH_SHORT ).show();
            }


            @Override
            public void onRetry(int retryNo) {
                super.onRetry(retryNo);
            }
        });
    }

    @Override
    public void onClick(View view) {
        if(view == datePick) {
            datePickerDialog.show();
        }
    }
}
