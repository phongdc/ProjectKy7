package com.example.phongdc.peaapp.ShiftRegister;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.phongdc.peaapp.AsyncHttpClient.HttpUtils;
import com.example.phongdc.peaapp.Home.HomeActivity;
import com.example.phongdc.peaapp.R;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Adapter.ShiftRegisterDetailsRecyclerAdapter;
import Model.ShiftRegisterDetails;
import cz.msebera.android.httpclient.Header;

public class ShiftRegisterDetailsActivity extends AppCompatActivity {

    private String name;

    private TextView tvTitle;
    private RecyclerView rv_ShiftRegisterDetails;
    private List<ShiftRegisterDetails> shiftRegisterList;
    private String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shift_register_details);

        Bundle extras = this.getIntent().getExtras();
        name = extras.getString("EmpNAME");
        token = HomeActivity.getToken();
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
       HttpUtils.getByUrlAuth("http://payroll.unicode.edu.vn/api/shift_register",token, null, new JsonHttpResponseHandler(){
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
                            if (object.getString("name").matches(name)){
                                shiftRegister.setShiftId(object.getInt("id"));
                                shiftRegister.setTimeFrameName(object.getString("time_frame_name"));
                                shiftRegister.setEmpName(object.getString("name"));
                                shiftRegister.setStartDate(object.getString("start_date"));
                                shiftRegister.setEndDate(object.getString("end_date"));
                                shiftRegister.setStartTime(object.getString("start_time"));
                                shiftRegister.setEndTime(object.getString("end_time"));
                                shiftRegister.setStatus(object.getString("status"));
                                shiftRegisterList.add(shiftRegister);
                        }

                        }
                    }

                    rv_ShiftRegisterDetails.setAdapter(new ShiftRegisterDetailsRecyclerAdapter(shiftRegisterList, ShiftRegisterDetailsActivity.this));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void clickToGoToShiftAccept(View view) {
        Intent intent = new Intent(ShiftRegisterDetailsActivity.this, AcceptShiftRegister.class);

        Bundle bundle = new Bundle();
        bundle.putString("EmployeeName", name);
        intent.putExtras(bundle);

        startActivity(intent);
    }

}
