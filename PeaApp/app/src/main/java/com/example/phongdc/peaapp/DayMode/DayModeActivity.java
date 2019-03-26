package com.example.phongdc.peaapp.DayMode;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.phongdc.peaapp.AsyncHttpClient.HttpUtils;
import com.example.phongdc.peaapp.R;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Adapter.DayModeAdapter;
import Model.DayMode;
import cz.msebera.android.httpclient.Header;

public class DayModeActivity extends AppCompatActivity {
    private EditText edtDayName;
    private EditText edtDotw;
    private EditText edtFromdateDayType;
    private EditText edtTodateDayType;
    private EditText edtPriority;
    private TextView tvTitle;
    private ListView lvDayType;
    private List<DayMode> dayModes;
    DayModeAdapter adapter;
    private int vitri = -1;
    private String day;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_day_mode);
        tvTitle = findViewById(R.id.tvTitle);
        tvTitle.setText("Lịch ngày làm");
        lvDayType = findViewById(R.id.lvDayType);

        dayModes = new ArrayList<>();
        adapter = new DayModeAdapter(DayModeActivity.this, R.layout.day_mode, dayModes);
        lvDayType.setAdapter(adapter);
        getDayMode();
        lvDayType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            Intent intent = new Intent(DayModeActivity.this, GetInformationDayTypeActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("dayModeID",dayModes.get(position).getId());
            intent.putExtras(bundle);
            startActivity(intent);

        }
    });
    }


    private void getDayMode(){
        HttpUtils.get("day_mode", null, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {

                    JSONArray data = (JSONArray) response.get("data");
                    for(int i = 0; i < data.length(); i++){
                        DayMode dayMode = new DayMode();
                        JSONObject object = data.getJSONObject(i);
                        dayMode.setId(object.getInt("id"));
                        dayMode.setName(object.getString("name"));
                        dayMode.setPriority(object.getString("priority"));
//                           JSONArray dataDayOfWeek = (JSONArray) object.get("day_of_week");
//                           dayMode.setDayOfWeek(dataDayOfWeek);
//                        for (int j = 0; j < dataDayOfWeek.length(); j++) {
//                            JSONObject obj = dataDayOfWeek.getJSONObject(j);
//                            if(obj.getBoolean("active") == true){
//
//                            }
//
//                        }
//                        dayMode.setDay_filter(day);
//                        dayMode.setIs_special_day(object.getString("is_special_day"));

                        dayModes.add(dayMode);
                    }
                    adapter.notifyDataSetChanged();
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        });
    }
//    private void postDayType(){
//        HttpUtils httpUtils = new HttpUtils();
//        RequestParams params = new RequestParams();
//
//        params.put("name", edtDayName);
//        params.put("days_of_the_week", edtDotw);
//        params.put("from_date", edtFromdateDayType);
//        params.put("to_date", edtTodateDayType);
//        params.put("priority", edtPriority);
//        httpUtils.post("http://payroll.unicode.edu.vn/api/DayMode", params, new AsyncHttpResponseHandler() {
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
//                Toast.makeText(DayModeActivity.this,"Thêm thành công",Toast.LENGTH_SHORT ).show();
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
//                Toast.makeText(DayModeActivity.this,"Thêm that bai",Toast.LENGTH_SHORT ).show();
//            }
//        });
//    }

    public void clickToAddDayType(View view) {
//        callPopupDayType();
        Intent intent = new Intent(this, GetInformationDayTypeActivity.class);
        startActivity(intent);
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
//                httpUtils.post("http://payroll.unicode.edu.vn/api/DayMode", params, new AsyncHttpResponseHandler() {
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
//    private void chonNgay(){
//        final Calendar calendar = Calendar.getInstance();
//        int ngay = calendar.get(Calendar.DATE);
//        int thang = calendar.get(Calendar.MONTH);
//        int nam = calendar.get(Calendar.YEAR);
//        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker view, int i, int i1, int i2) {
//                // i: năm - i1: tháng - i2: ngày
//                calendar.set(i, i1, i2);
//                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
//                edtFromdateDayType.setText(simpleDateFormat.format(calendar.getTime()));
//            }
//        }, nam, thang, ngay);
//        datePickerDialog.show();
//    }
//    private void chonNgay2(){
//        final Calendar calendar = Calendar.getInstance();
//        int ngay = calendar.get(Calendar.DATE);
//        int thang = calendar.get(Calendar.MONTH);
//        int nam = calendar.get(Calendar.YEAR);
//        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker view, int i, int i1, int i2) {
//                // i: năm - i1: tháng - i2: ngày
//                calendar.set(i, i1, i2);
//                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
//                edtTodateDayType.setText(simpleDateFormat.format(calendar.getTime()));
//            }
//        }, nam, thang, ngay);
//        datePickerDialog.show();
//    }


}
