package com.example.phongdc.peaapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Model.ShiftRegister;
import cz.msebera.android.httpclient.Header;

public class AcceptShiftRegister extends AppCompatActivity {

    private int storeID = 1;
    private String empName;
    private List<ShiftRegister> shiftRegistersList;
    LinearLayout linearLayout;
    LinearLayout.LayoutParams checkParams;
    List<CheckBox> allCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accept_shift_register);

        checkParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        checkParams.setMargins(10, 10, 10, 10);
        checkParams.gravity = Gravity.LEFT;

        allCheckBox = new ArrayList<CheckBox>();
        linearLayout = (LinearLayout) findViewById(R.id.shiftRegisterlist);
        shiftRegistersList = new ArrayList<ShiftRegister>();
        getShift();

    }

    public void getShift(){

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
                            shiftRegister.setEmpName(object.getString("name"));
                            shiftRegistersList.add(shiftRegister);
                            CheckBox cb = new CheckBox(AcceptShiftRegister.this);
                            cb.setText(object.getString("name"));
                            cb.setTextSize(20);
                            cb.setId(i);
                            linearLayout.addView(cb, checkParams);
                            allCheckBox.add(cb);
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void clickToAccept(View view) {
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        final RequestParams params = new RequestParams();
        JSONArray jsonArray = new JSONArray();

        for (int i = 0; i < allCheckBox.size(); i++){
            if (allCheckBox.get(i).isChecked() == true){
                jsonArray.put(shiftRegistersList.get(i).getShiftId());
            }
        }

        params.put("list_shift_id", jsonArray);
        params.put("store_id", storeID);

        params.setUseJsonStreamer(true);

        asyncHttpClient.post("http://payroll.unicode.edu.vn/api/attendance/accept", params, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Toast.makeText(AcceptShiftRegister.this,"Duyệt Thành Công",Toast.LENGTH_SHORT ).show();
                Intent intent = new Intent(AcceptShiftRegister.this, GetShiftRegisterActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(AcceptShiftRegister.this,"Thêm that bai",Toast.LENGTH_SHORT ).show();
            }


            @Override
            public void onRetry(int retryNo) {
                super.onRetry(retryNo);
            }
        });
    }

}
