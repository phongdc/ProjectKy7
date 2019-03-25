package com.example.phongdc.peaapp;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.Calendar;

import cz.msebera.android.httpclient.Header;

public class CreateTimeFrame extends AppCompatActivity implements View.OnClickListener{
    private EditText edtStartTime;
    private EditText edtEndTime;
    private EditText timeFrameName;
    private TimePickerDialog startTimePickerDialog;
    private TimePickerDialog endTimePickerDialog;
    private TextView tvTitle;
    private int brandID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_time_frame);
        findViewsById();
        tvTitle.setText("Tạo Time Frame");
        setDateTimeField();

    }
    private void findViewsById() {
        edtStartTime = (EditText) findViewById(R.id.edtStartTime);
        edtStartTime.setInputType(InputType.TYPE_NULL);
        edtStartTime.requestFocus();

        edtEndTime = (EditText) findViewById(R.id.edtEndTime);
        edtEndTime.setInputType(InputType.TYPE_NULL);

        timeFrameName = findViewById(R.id.edtTimeFrameName);
        tvTitle = findViewById(R.id.tvTitle);
    }

    private void setDateTimeField(){
        edtStartTime.setOnClickListener(this);
        edtEndTime.setOnClickListener(this);

        Calendar calendar = Calendar.getInstance();
        final int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        startTimePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                edtStartTime.setText(hourOfDay + ":" + minute + ":00");
            }
        }, hour, minute, true);

        endTimePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                edtEndTime.setText(hourOfDay + ":" + minute + ":00");
            }
        }, hour, minute, true);


    }
    @Override
    public void onClick(View v) {
        if(v == edtStartTime) {
            startTimePickerDialog.show();
        } else if(v == edtEndTime) {
            endTimePickerDialog.show();
        }
    }

    public void clickToAddTimeFrame(View view) {
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        String nameTimeFrame = timeFrameName.getText().toString();
        String start = edtStartTime.getText().toString();
        String end = edtEndTime.getText().toString();


        params.put("name", nameTimeFrame);
        params.put("start_time",start);
        params.put("end_time", end);
        params.put("brand_id", brandID);


        params.setUseJsonStreamer(true);

        asyncHttpClient.post("http://payroll.unicode.edu.vn/api/time_frame", params, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
//                TimeFrameActivity timeFrameActivity = new TimeFrameActivity();
//                timeFrameActivity.getTimeFrame();
                Intent intent = new Intent(CreateTimeFrame.this, TimeFrameActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(CreateTimeFrame.this,"Thêm that bai",Toast.LENGTH_SHORT ).show();
            }


            @Override
            public void onRetry(int retryNo) {
                super.onRetry(retryNo);
            }
        });
    }

}
