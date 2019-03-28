package com.example.phongdc.peaapp.SalaryRule;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phongdc.peaapp.AsyncHttpClient.HttpUtils;
import com.example.phongdc.peaapp.Home.HomeActivity;
import com.example.phongdc.peaapp.R;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Adapter.SalaryRuleGroupAdapter;
import Model.SalaryRuleGroup;
import cz.msebera.android.httpclient.Header;

public class SalaryRuleGroupActivity extends AppCompatActivity {
    private TextView tvTitle;
    private RecyclerView rv_SalaryRuleGroup;
    private List<SalaryRuleGroup> salaryRuleGroupList;
    private PopupWindow popupWindow;
    private EditText groupName;
    private SalaryRuleGroupAdapter adapter;
    SwipeRefreshLayout pullToRefresh;
    private String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salary_rule_group);
        findViewById();
        token = HomeActivity.getToken();
        rv_SalaryRuleGroup.setLayoutManager(new LinearLayoutManager(this));
        salaryRuleGroupList = new ArrayList<>();
        adapter = new SalaryRuleGroupAdapter(SalaryRuleGroupActivity.this,salaryRuleGroupList);
        getSalaryGroup();
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getSalaryGroup();
                pullToRefresh.setRefreshing(false);
            }
        });
    }
    private void findViewById(){
        tvTitle = findViewById(R.id.tvTitle);
        tvTitle.setText("Các nhóm lương quy định");
        rv_SalaryRuleGroup = findViewById(R.id.rv_SalaryRuleGroup);
        pullToRefresh = findViewById(R.id.pullToRefresh);
    }
    private void getSalaryGroup() {
        salaryRuleGroupList.clear();

        com.example.phongdc.peaapp.AsyncHttpClient.HttpUtils.getAuth("salary_rule_group",token, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        SalaryRuleGroup salaryRuleGroup = new SalaryRuleGroup();
                        JSONObject object = response.getJSONObject(i);
                        salaryRuleGroup.setId(object.getInt("id"));
                        salaryRuleGroup.setName(object.getString("name"));
                        salaryRuleGroupList.add(salaryRuleGroup);
                    }
                    rv_SalaryRuleGroup.setAdapter(adapter);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
         private void callPopup() {

             LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);

             View popupView = layoutInflater.inflate(R.layout.popup, null);

             popupWindow = new PopupWindow(popupView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);

             popupWindow.setTouchable(true);
             popupWindow.setFocusable(true);

             popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);
             groupName = (EditText) popupView.findViewById(R.id.edtGroupName);
                tvTitle = popupView.findViewById(R.id.tvTitle);
                tvTitle.setText("Tạo nhóm lương");
             ((Button) popupView.findViewById(R.id.btSave)).setOnClickListener(new View.OnClickListener() {

                 @TargetApi(Build.VERSION_CODES.GINGERBREAD)
                 public void onClick(View arg0) {
                     RequestParams params = new RequestParams();
                     params.put("name", groupName.getText().toString());
                     params.setUseJsonStreamer(true);
                  HttpUtils.postAuth("salary_rule_group",token, params, new AsyncHttpResponseHandler() {

                         @Override
                         public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                             Toast.makeText(SalaryRuleGroupActivity.this, "Tạo thành công", Toast.LENGTH_SHORT).show();
                             popupWindow.dismiss();
                             adapter.notifyDataSetChanged();
                         }

                         @Override
                         public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                             Toast.makeText(SalaryRuleGroupActivity.this, "Vui lòng thử lại sau", Toast.LENGTH_SHORT).show();
                         }
                     });


                 }

             });

             ((Button) popupView.findViewById(R.id.btCancel)).setOnClickListener(new View.OnClickListener() {

                 public void onClick(View arg0) {

                     popupWindow.dismiss();
                 }
             });
         }

    @Override
    protected void onResume() {
        super.onResume();
       // getSalaryGroup();
    }



    public void clickToCreateSalaryGroup(View view) {
             callPopup();
         }
     }