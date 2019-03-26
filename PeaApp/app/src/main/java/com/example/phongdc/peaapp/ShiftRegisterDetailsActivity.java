package com.example.phongdc.peaapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Adapter.GetShiftRegisterRecyclerAdapter;
import Adapter.ShiftRegisterDetailsRecyclerAdapter;
import Model.Employee;
import Model.ShiftRegister;
import Model.ShiftRegisterDetails;
import cz.msebera.android.httpclient.Header;

public class ShiftRegisterDetailsActivity extends AppCompatActivity {

    private String name;

    private TextView tvTitle;
    private RecyclerView rv_ShiftRegisterDetails;
    private List<ShiftRegisterDetails> shiftRegisterList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shift_register_details);

        Bundle extras = this.getIntent().getExtras();
        name = extras.getString("EmpNAME");

        findViewById();
        tvTitle.setText("Shift Register Details");
        shiftRegisterList = new ArrayList<>();
        rv_ShiftRegisterDetails.setLayoutManager(new LinearLayoutManager(this));
        getDetails();

    }
    private void findViewById(){
        tvTitle = findViewById(R.id.tvTitle);
        rv_ShiftRegisterDetails = findViewById(R.id.rv_ShiftRegisterDetails);

    }

    public void getDetails(){
        HttpUtils.getByUrl("http://payroll.unicode.edu.vn/api/shift_register", null, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONObject json = new JSONObject(response.toString());
                    JSONArray jArray = json.getJSONArray("data");
                    for (int i = 0; i < jArray.length(); i++) {
                        JSONObject json2 = jArray.getJSONObject(i);
                        JSONArray jArray2 = json2.getJSONArray("shift");


                        for (int y = 0; y < jArray2.length(); y++) {
                            JSONObject object = jArray2.getJSONObject(y);
                            ShiftRegisterDetails shiftRegister = new ShiftRegisterDetails();
//                            if (object.getString("name").matches(name)){
                                shiftRegister.setShiftId(object.getInt("id"));
                                shiftRegister.setTimeFrameName(object.getString("time_frame_name"));
                                shiftRegister.setEmpName(object.getString("name"));
                                shiftRegister.setStartDate(object.getString("start_date"));
                                shiftRegister.setEndDate(object.getString("end_date"));
                                shiftRegister.setStartTime(object.getString("start_time"));
                                shiftRegister.setEndTime(object.getString("end_time"));
                                shiftRegister.setStatus(object.getString("status"));
                                shiftRegisterList.add(shiftRegister);
//                        }

                        }
                    }

                    rv_ShiftRegisterDetails.setAdapter(new ShiftRegisterDetailsRecyclerAdapter(shiftRegisterList, ShiftRegisterDetailsActivity.this));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

}
