package com.example.phongdc.peaapp;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import Adapter.DayTypeAdapter;
import Model.DayType;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

public class DayTypeActivity extends AppCompatActivity {
    private EditText edtDayName;
    private EditText edtDotw;
    private EditText edtFromdateDayType;
    private EditText edtTodateDayType;
    private EditText edtPriority;
    private TextView tvTitle;
    private ListView lvDayType;
    private List<DayType> dayTypes;
    PopupWindow popupDayType;
    DayTypeAdapter adapter;
    private int vitri = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_day_type);
        tvTitle = findViewById(R.id.tvTitle);
        tvTitle.setText("Loại ngày");
        lvDayType = findViewById(R.id.lvDayType);

        dayTypes = new ArrayList<>();
        adapter = new DayTypeAdapter(DayTypeActivity.this, R.layout.day_type, dayTypes);
        lvDayType.setAdapter(adapter);
        getDayType();
        lvDayType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                dayTypes.get(i);
                startActivity(new Intent(DayTypeActivity.this, GetInformationDayTypeActivity.class));
            }
        });

    }

    private void getDayType(){
        HttpUtils.get("DayType", null, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                try {
                    for(int i = 0; i < response.length(); i++){
                        DayType dayType = new DayType();
                        JSONObject object = response.getJSONObject(i);
                        dayType.setId(object.getInt("id"));
                        dayType.setName(object.getString("name"));
                        dayType.setPriority(object.getString("priority"));
                        dayTypes.add(dayType);
                    }
                    adapter.notifyDataSetChanged();
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        });
    }
    private void postDayType(){
        HttpUtils httpUtils = new HttpUtils();
        RequestParams params = new RequestParams();

        params.put("name", edtDayName);
        params.put("days_of_the_week", edtDotw);
        params.put("from_date", edtFromdateDayType);
        params.put("to_date", edtTodateDayType);
        params.put("priority", edtPriority);
        httpUtils.post("http://payroll.unicode.edu.vn/api/DayType", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Toast.makeText(DayTypeActivity.this,"Thêm thành công",Toast.LENGTH_SHORT ).show();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(DayTypeActivity.this,"Thêm that bai",Toast.LENGTH_SHORT ).show();
            }
        });
    }

    public void clickToAddDayType(View view) {
        //callPopupDayType();
    }
//    private void callPopupDayType() {
//        tvTitle.setText("Tạo loại ngày mới");
//        LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
//
//        View popupView = layoutInflater.inflate(R.layout.popup_day_type, null);
//
//        popupDayType = new PopupWindow(popupView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
//
//        popupDayType.setTouchable(true);
//        popupDayType.setFocusable(true);
//
//        popupDayType.showAtLocation(popupView, Gravity.CENTER, 0, 0);
//        edtDayName =  popupView.findViewById(R.id.edtDayName);
//        edtDotw = popupView.findViewById(R.id.edtDotw);
//        edtFromdateDayType = popupView.findViewById(R.id.edtFromdateDayType);
//        edtFromdateDayType.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                chonNgay();
//            }
//        });
//        edtTodateDayType = popupView.findViewById(R.id.edtTodateDayType);
//        edtTodateDayType.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                chonNgay2();
//            }
//        });
//        edtPriority = popupView.findViewById(R.id.edtPriority);
//
//
//        ( popupView.findViewById(R.id.btSaveDayType)).setOnClickListener(new View.OnClickListener() {
//
//            @TargetApi(Build.VERSION_CODES.GINGERBREAD)
//            public void onClick(View arg0) {
//                HttpUtils httpUtils = new HttpUtils();
//                AsyncHttpClient client = new AsyncHttpClient();
//                JSONObject object = new JSONObject();
////                client.post()
//                try {
//                    StringEntity entity = new StringEntity(object.toString());
//                } catch (UnsupportedEncodingException e) {
//                    e.printStackTrace();
//                }
//                popupDayType.dismiss();
//                postDayType();
//
//                RequestParams params = new RequestParams();
//
//                params.put("name", findViewById(R.id.edtDayName));
//
//                httpUtils.post("http://payroll.unicode.edu.vn/api/DayType", params, new AsyncHttpResponseHandler() {
//
//                    @Override
//                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
//                        if(statusCode == 200){
//
//                        }
//                        if(statusCode == 500){
//
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
//
//                    }
//                });
//
//            }
//
//        });
//
//        ((Button) popupView.findViewById(R.id.btCancelDayType)).setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View arg0) {
//
//                popupDayType.dismiss();
//            }
//        });
//    }
    private void chonNgay(){
        final Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int i, int i1, int i2) {
                // i: năm - i1: tháng - i2: ngày
                calendar.set(i, i1, i2);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                edtFromdateDayType.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, nam, thang, ngay);
        datePickerDialog.show();
    }
    private void chonNgay2(){
        final Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int i, int i1, int i2) {
                // i: năm - i1: tháng - i2: ngày
                calendar.set(i, i1, i2);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                edtTodateDayType.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, nam, thang, ngay);
        datePickerDialog.show();
    }


}
