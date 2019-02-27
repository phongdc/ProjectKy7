package com.example.phongdc.peaapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import Model.Employee;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Callback;
import okhttp3.Response;

public class ListEmp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_emp);

        //tạo recyclerView
        final RecyclerView rvEmps = (RecyclerView)findViewById(R.id.rv_Emp);
        final TextView tvTotal = (TextView) findViewById(R.id.tvTotal);
        rvEmps.setLayoutManager(new LinearLayoutManager(this));
        // Khởi tạo OkHttpClient để lấy dữ liệu.
        OkHttpClient client = new OkHttpClient();

        // Khởi tạo Moshi adapter để biến đổi json sang model java (ở đây là User)
        Moshi moshi = new Moshi.Builder().build();
        Type empType = Types.newParameterizedType(List.class, Employee.class);
        final JsonAdapter<List<Employee>> jsonAdapter = moshi.adapter(empType);
        // Tạo request lên server.
        Request request = new Request.Builder()
                .url("http://payroll.unicode.edu.vn/api/Employee")
                .build();

        // Thực thi request.
        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // Lấy thông tin JSON trả về. Bạn có thể log lại biến json này để xem nó như thế nào.
                String json = response.body().string();
                final List<Employee> employees = jsonAdapter.fromJson(json);
                final int total = employees.size();


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        rvEmps.setAdapter(new EmpRecycleAdapter(employees, ListEmp.this));
                        tvTotal.setText("Total: " + total);

                    }
                });
            }

            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("Error", "Network Error");
            }

        });
}

    public void clickToDetails(View view) {
        
        Toast.makeText(this,"abc",Toast.LENGTH_SHORT).show();
    }
}
