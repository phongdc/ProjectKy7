package com.example.phongdc.peaapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Adapter.GetShiftRegisterRecyclerAdapter;
import Model.ShiftRegister;
import cz.msebera.android.httpclient.Header;

public class GetShiftRegisterActivity extends AppCompatActivity {

    private TextView tvTitle;
    private RecyclerView rv_ShiftRegister;
    private List<ShiftRegister> shiftRegisterList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_shift_register);
        findViewById();
        tvTitle.setText("Shift Register");
        shiftRegisterList = new ArrayList<>();
        rv_ShiftRegister.setLayoutManager(new LinearLayoutManager(this));
        getTimeFrame();


    }
    private void findViewById(){
        tvTitle = findViewById(R.id.tvTitle);
        rv_ShiftRegister = findViewById(R.id.rv_ShiftRegister);

    }
    public void getTimeFrame(){
        HttpUtils.getByUrl("http://payroll.unicode.edu.vn/api/shift_register", null, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONObject json = new JSONObject(response.toString());
                    JSONArray jArray = json.getJSONArray("data");
                    for (int i = 0; i < jArray.length(); i++) {
                        JSONObject object = jArray.getJSONObject(i);
                        if(!object.getString("status").matches("Đã duyệt")){
                            ShiftRegister shiftRegister = new ShiftRegister();
                            shiftRegister.setShiftId(object.getInt("id"));
                            shiftRegister.setTimeFrameId(object.getInt("time_frame_id"));
                            shiftRegister.setEmpName(object.getString("name"));
                            shiftRegister.setStartDate(object.getString("start_date"));
                            shiftRegister.setEndDate(object.getString("end_date"));
                            shiftRegister.setStartTime(object.getString("start_time"));
                            shiftRegister.setEndTime(object.getString("end_time"));
                            shiftRegister.setStatus(object.getString("status"));
                            shiftRegisterList.add(shiftRegister);
                        }

                    }
                    rv_ShiftRegister.setAdapter(new GetShiftRegisterRecyclerAdapter(shiftRegisterList, GetShiftRegisterActivity.this));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }


    public void clickToGoToShiftAccept(View view) {
        Intent intent = new Intent(GetShiftRegisterActivity.this, AcceptShiftRegister.class);
        startActivity(intent);
    }
}
