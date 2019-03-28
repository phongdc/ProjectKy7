package com.example.phongdc.peaapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.phongdc.peaapp.presenters.GetAttendancePresenter;
import com.example.phongdc.peaapp.views.AttendanceView;


import java.util.List;

import Model.Attendance;


public class CalendarEmployeeDetailActivity extends AppCompatActivity implements AttendanceView {
private String time;
private int image;
private AttendanceView attendanceView;
private GetAttendancePresenter mGetAttendancePresenter;
private ImageView  imageView;
private TextView mTimeChoose,mShiftMin,mshiftMax,mCheckMin,mCheckMax,mTotal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_employee_detail);
        getDataIntent();
        initialView();
        initialData();
    }
    private void initialView(){
            imageView= findViewById(R.id.status_checked);
            mTimeChoose = findViewById(R.id.time_choose);
            mShiftMin = findViewById(R.id.shift_min);
            mshiftMax = findViewById(R.id.shift_max);
            mCheckMin = findViewById(R.id.check_min);
            mCheckMax = findViewById(R.id.check_max);
            mTotal = findViewById(R.id.total_time);
    }
    private void initialData(){
            imageView.setImageResource(image);
            mTimeChoose.setText("Ngày "+ time+"");
            mGetAttendancePresenter = new GetAttendancePresenter(CalendarEmployeeDetailActivity.this, CalendarEmployeeDetailActivity.this,this);
            mGetAttendancePresenter.getAttendace(time,time,2);
    }
    private  void getDataIntent(){
        Bundle bundle = getIntent().getExtras();
        time = bundle.getString("time");
        image = bundle.getInt("image");
    }


    @Override
    public void getAttendance(List<Attendance> attendanceList) {
        if(attendanceList!=null){
            mShiftMin.setText(attendanceList.get(0).getShiftMin()+"");
            mshiftMax.setText(attendanceList.get(0).getShiftMax()+"");
            if(attendanceList.get(0).getTotalHour() != null){
                mTotal.setText(attendanceList.get(0).getTotalHour() + "");
            }
            else {
                mTotal.setText("Đang cập nhật");
            }

            if(attendanceList.get(0).getCheckMax()!=null ){
                mCheckMax.setText(attendanceList.get(0).getCheckMax());
            }
            else {
                mCheckMax.setText("Chưa điểm danh");
            }
            if(attendanceList.get(0).getCheckMin()!=null ){
                mCheckMin.setText(attendanceList.get(0).getCheckMin());
            }
            else {
                mCheckMin.setText("Chưa điểm danh");
            }
        }
    }

    @Override
    public void getFail(String message) {

    }
}
