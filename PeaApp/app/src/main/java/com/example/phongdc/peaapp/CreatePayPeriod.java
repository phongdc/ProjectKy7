package com.example.phongdc.peaapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import Model.Employee;
import Model.PayslipTemplate;
import cz.msebera.android.httpclient.Header;

public class CreatePayPeriod extends AppCompatActivity implements View.OnClickListener {
    private EditText edtFromDate;
    private EditText edtTodate;
    private String nameApi = "PayrollPeriod";
    private EditText periodName;
    private DatePickerDialog fromDatePickerDialog;
    private DatePickerDialog toDatePickerDialog;
    private SimpleDateFormat dateFormat;
    private TextView tvTitle;
    private List<PayslipTemplate> paySlipList;
    private List<String> paySlipListName;
    private int templateID;
    Spinner spnTemplate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_pay_period);
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        findViewsById();
        tvTitle.setText("Tạo Kỳ Lương");
        paySlipList = new ArrayList<PayslipTemplate>();
        paySlipListName = new ArrayList<>();
        spnTemplate = (Spinner)findViewById(R.id.spnTemplate);
        setDateTimeField();
        getPaySlipTemplate();
        spnTemplate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String template = spnTemplate.getItemAtPosition(spnTemplate.getSelectedItemPosition()).toString();

                for (int a = 0; a < paySlipList.size(); a++){
                    if (paySlipList.get(i).getName().matches(template)){
                        templateID = (paySlipList.get(i).getId());
//                        Toast.makeText(getApplicationContext(),templateID,Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
            }
        });


    }
    private void findViewsById() {
        edtFromDate = (EditText) findViewById(R.id.edtFromdate);
        edtFromDate.setInputType(InputType.TYPE_NULL);
        edtFromDate.requestFocus();

        edtTodate = (EditText) findViewById(R.id.edtTodate);
        edtTodate.setInputType(InputType.TYPE_NULL);
        periodName = findViewById(R.id.edtPeriodName);
        tvTitle = findViewById(R.id.tvTitle);
    }
    private void setDateTimeField(){
        edtFromDate.setOnClickListener(this);
        edtTodate.setOnClickListener(this);

        Calendar calendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, month, dayOfMonth);
                edtFromDate.setText(dateFormat.format(newDate.getTime()));

            }
        },calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        toDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, month, dayOfMonth);
                edtTodate.setText(dateFormat.format(newDate.getTime()));

            }
        },calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
    }


    @Override
    public void onClick(View v) {
        if(v == edtFromDate) {
            fromDatePickerDialog.show();
        } else if(v == edtTodate) {
            toDatePickerDialog.show();
        }
    }


    public void clickToAddPeriod(View view) {
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        JSONObject object = new JSONObject();

//        try {
//            fromDate = dateFormat.parse(edtFromDate.getText().toString());
//            toDate = dateFormat.parse(edtTodate.getText().toString());
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
        String namePeriod = periodName.getText().toString();
        String from = "2010-07-03";
        String to = "2012-08-04";
        int i = 1;

        params.put("payslip_template_id", templateID);

        params.put("name", namePeriod);
        params.put("from_date",from);
        params.put("to_date", to);



        params.setUseJsonStreamer(true);

          asyncHttpClient.post("http://payroll.unicode.edu.vn/api/payroll_period", params, new AsyncHttpResponseHandler() {
              @Override
              public void onStart() {
              }

              @Override
              public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
//                  GetPeriodActivity getPeriodActivity = new GetPeriodActivity();
//                  getPeriodActivity.getPayPeriod();
                  Intent intent = new Intent(CreatePayPeriod.this, GetPeriodActivity.class);
                  startActivity(intent);
              }

              @Override
              public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                  Toast.makeText(CreatePayPeriod.this,"Thêm that bai",Toast.LENGTH_SHORT ).show();
              }


              @Override
              public void onRetry(int retryNo) {
                  super.onRetry(retryNo);
              }
          });
    }

    public void getPaySlipTemplate(){

        HttpUtils.getByUrl("http://payroll.unicode.edu.vn/api/payslip_template", null, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                try {
                    JSONObject json = new JSONObject(response.toString());
                    JSONArray jArray = json.getJSONArray("data");

                    for (int i = 0; i < jArray.length(); i++) {
                        PayslipTemplate payslip = new PayslipTemplate();
                        JSONObject object = jArray.getJSONObject(i);
                        payslip.setId(object.getInt("id"));
                        payslip.setName(object.getString("name"));
                        paySlipList.add(payslip);
                        paySlipListName.add(object.getString("name"));
                    }
                    spnTemplate.setAdapter(new ArrayAdapter<>(CreatePayPeriod.this, android.R.layout.simple_spinner_dropdown_item, paySlipListName));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }


}
