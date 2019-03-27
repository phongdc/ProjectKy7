package com.example.phongdc.peaapp.Employees;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.phongdc.peaapp.AsyncHttpClient.HttpUtils;
import com.example.phongdc.peaapp.Home.HomeActivity;
import com.example.phongdc.peaapp.R;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Adapter.EmpRecycleAdapter;
import Model.Employee;
import cz.msebera.android.httpclient.Header;

public class ListEmp extends AppCompatActivity {
    private List<Employee> employeeList;
    private TextView tvTotal;
    private RecyclerView rvEmps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_emp);

        //tạo recyclerView
        employeeList = new ArrayList<>();
          rvEmps = (RecyclerView)findViewById(R.id.rv_Emp);
          tvTotal = (TextView) findViewById(R.id.tvTotal);
        rvEmps.setLayoutManager(new LinearLayoutManager(this));
        // Khởi tạo OkHttpClient để lấy dữ liệu.
        getEmpList();
}

  private void getEmpList(){
        String token = HomeActivity.getToken();
        HttpUtils.getAuth("employee", token ,null, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray dataArray = (JSONArray) response.get("data");
                    for (int i = 0; i < dataArray.length(); i++) {
                        Employee employee = new Employee();
                        JSONObject object = dataArray.getJSONObject(i);
                        employee.setId(object.getInt("id"));
                        employee.setEmployee_name(object.getString("employee_name"));
                        employee.setCode(object.getString("code"));
                        employeeList.add(employee);
                        int total = employeeList.size();
                        tvTotal.setText("Total: "+total);
                    }
                    rvEmps.setAdapter(new EmpRecycleAdapter(employeeList, ListEmp.this));
                }
               catch (Exception e){
                    e.printStackTrace();
               }
            }
        });
  }
}
