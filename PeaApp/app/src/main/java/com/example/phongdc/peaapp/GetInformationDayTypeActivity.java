package com.example.phongdc.peaapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Adapter.GetInfomationDayTypeAdapter;
import Model.DayModeInf;
import cz.msebera.android.httpclient.Header;

public class GetInformationDayTypeActivity extends AppCompatActivity {

    private String nameApi = "day_mode";
    private TextView tvTitle;
    private RecyclerView rvDayOfWeek;
    private List<DayModeInf> dayModeInfs;
    private TextView txtDayModeName;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = this.getIntent().getExtras();
        id = extras.getInt("dayModeID");
        setContentView(R.layout.activity_get_information_day_type);
        txtDayModeName = findViewById(R.id.txtDayName);
        tvTitle = findViewById(R.id.tvTitle);
        tvTitle.setText("Thông tin lịch làm");
        rvDayOfWeek = findViewById(R.id.rvDayOfWeek);
        dayModeInfs = new ArrayList<>();
        rvDayOfWeek.setLayoutManager(new LinearLayoutManager(this));
        getDetail();



    }
    public  void  getDetail() {
        HttpUtils.get(nameApi, null, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray data = (JSONArray) response.get("data");
                    for (int i = 0; i < data.length(); i++) {
                        JSONObject object = data.getJSONObject(i);
                        if(id == object.getInt("id")){
                            String name =  object.getString("name");
                            txtDayModeName.setText(name);
                            JSONArray dataDayOfWeek = (JSONArray) object.get("day_of_week");
                            for (int j = 0; j < dataDayOfWeek.length(); j++) {
                                JSONObject obj = dataDayOfWeek.getJSONObject(j);
                                DayModeInf dayModeInf = new DayModeInf();
                                dayModeInf.setNameDay(obj.getString("name"));
                                dayModeInf.setId(obj.getInt("id"));
                                dayModeInf.setActive(obj.getBoolean("active"));
                                dayModeInfs.add(dayModeInf);
                            }//end for
                            rvDayOfWeek.setAdapter(new GetInfomationDayTypeAdapter(dayModeInfs, GetInformationDayTypeActivity.this));
                        }//end if
                    }//end for
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
    }

}
