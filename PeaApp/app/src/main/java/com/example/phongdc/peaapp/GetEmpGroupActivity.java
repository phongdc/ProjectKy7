package com.example.phongdc.peaapp;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import Adapter.ListGroupAdapter;
import Model.EmpGroup;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

public class GetEmpGroupActivity extends AppCompatActivity {
    private ListView lvPaySlipGroup;
    private TextView tvTitle;
    private List<EmpGroup> empGroups;
    PopupWindow popupWindow;
    private EditText groupName;
    ListGroupAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_emp_group);
        lvPaySlipGroup = findViewById(R.id.lvPaySlipGroup);
        tvTitle = findViewById(R.id.tvTitle);
        tvTitle.setText("Nhóm nhân viên");
        empGroups = new ArrayList<>();
        adapter = new ListGroupAdapter(GetEmpGroupActivity.this, R.layout.group_emp,empGroups);
        lvPaySlipGroup.setAdapter(adapter);
        getPaySlipGroup();
//        lvPaySlipGroup.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
//
//            }
//        });
    }

    private void getPaySlipGroup() {
        HttpUtils.get("EmployeeGroup", null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        EmpGroup empGroup = new EmpGroup();
                        JSONObject object = response.getJSONObject(i);
                        empGroup.setId(object.getInt("id"));
                        empGroup.setName(object.getString("name"));
                        empGroup.setCreate_date(object.getString("create_date"));
                        empGroups.add(empGroup);
                    }
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void clickToAddPaySlip(View view) {

        callPopup();
//        object.put("groupName",  );
//        client.post();

    }

    private void callPopup() {

        LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);

        View popupView = layoutInflater.inflate(R.layout.popup, null);

        popupWindow = new PopupWindow(popupView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);

        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);

        popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);
        groupName = (EditText) popupView.findViewById(R.id.edtGroupName);

        ((Button) popupView.findViewById(R.id.btSave)).setOnClickListener(new View.OnClickListener() {

            @TargetApi(Build.VERSION_CODES.GINGERBREAD)
            public void onClick(View arg0) {

                AsyncHttpClient client = new AsyncHttpClient();
                JSONObject object = new JSONObject();
//                client.post()
                try {
                    StringEntity entity = new StringEntity(object.toString());
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                popupWindow.dismiss();

            }

        });

        ((Button) popupView.findViewById(R.id.btCancel)).setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {

                popupWindow.dismiss();
            }
        });
    }
}
