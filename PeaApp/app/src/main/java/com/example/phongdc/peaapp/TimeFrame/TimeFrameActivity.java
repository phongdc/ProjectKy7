package com.example.phongdc.peaapp.TimeFrame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.phongdc.peaapp.AsyncHttpClient.HttpUtils;
import com.example.phongdc.peaapp.R;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Adapter.TimeFrameRecyclerAdapter;
import Model.TimeFrame;
import cz.msebera.android.httpclient.Header;

public class TimeFrameActivity extends AppCompatActivity {

    private TextView tvTitle;
    private RecyclerView rv_TimeFrame;
    private List<TimeFrame> timeFrameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_frame);
        findViewById();
        tvTitle.setText("TimeFrame");
        timeFrameList = new ArrayList<>();
        rv_TimeFrame.setLayoutManager(new LinearLayoutManager(this));
        getTimeFrame();


    }
    private void findViewById(){
        tvTitle = findViewById(R.id.tvTitle);
        rv_TimeFrame = findViewById(R.id.rv_TimeFrame);

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
                        timeFrame.setBrand_id(object.getInt("brand_id"));
                        timeFrame.setBreak_time(object.getString("break_time"));
                        timeFrameList.add(timeFrame);
                    }
                    rv_TimeFrame.setAdapter(new TimeFrameRecyclerAdapter(timeFrameList, TimeFrameActivity.this));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }


    public void clickToGoToAddTimeFrame(View view) {
        Intent intent = new Intent(TimeFrameActivity.this, CreateTimeFrame.class);
        startActivity(intent);
    }
}
