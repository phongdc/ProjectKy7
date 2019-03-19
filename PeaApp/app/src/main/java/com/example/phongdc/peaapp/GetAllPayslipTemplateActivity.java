package com.example.phongdc.peaapp;

import android.content.ClipData;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Adapter.PayrollRecyclerAdapter;
import Adapter.Payslip_TemplateAdapter;
import Model.Payroll;
import Model.PayslipTemplate;
import cz.msebera.android.httpclient.Header;

public class GetAllPayslipTemplateActivity extends AppCompatActivity {
    private TextView tvTitle;
    private RecyclerView rv_Payslip_template;
    private List<PayslipTemplate> payslipTemplateList;
    private Toolbar mToolbar;
    private List<Integer> array;
    private RecyclerView.Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_all_payslip_template);
        findViewById();
        setSupportActionBar(mToolbar);
        tvTitle.setText("Mẫu phiếu lương");
        payslipTemplateList = new ArrayList<>();
        rv_Payslip_template.setLayoutManager(new LinearLayoutManager(this));
        getAllPayslipTemplate();
    }

    private void findViewById() {
        tvTitle = findViewById(R.id.tvTitle);
        rv_Payslip_template = findViewById(R.id.rv_Payslip_template);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

    }

    public void getAllPayslipTemplate() {
        HttpUtils.get("payslip_template", null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                try {
                    payslipTemplateList.clear();
                    JSONArray data =(JSONArray)  response.get("data");
                    for (int i = 0; i < data.length(); i++) {
                        PayslipTemplate payslipTemplate = new PayslipTemplate();
                        JSONObject jsonObject = data.getJSONObject(i);
                        payslipTemplate.setId(jsonObject.getInt("id"));
                        payslipTemplate.setName(jsonObject.getString("name"));

                        payslipTemplateList.add(payslipTemplate);
                    }
                    adapter = new  Payslip_TemplateAdapter(payslipTemplateList, GetAllPayslipTemplateActivity.this);
                    rv_Payslip_template.setAdapter(adapter);
                    //rv_Payslip_template.setAdapter(new Payslip_TemplateAdapter(payslipTemplateList, GetAllPayslipTemplateActivity.this));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_items, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        array = new ArrayList<Integer>();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_Delete) {
            for (int i = 0; i < Payslip_TemplateAdapter.payslipTemplateList.size(); i++) {
                try{
                    if(Payslip_TemplateAdapter.payslipTemplateList.get(i).isSelected()){
                        array.add(Payslip_TemplateAdapter.payslipTemplateList.get(i).getId());
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            RequestParams params = new RequestParams();
            JSONArray jsonArray = new JSONArray(array);
            params.put("list_id", jsonArray);
            params.setUseJsonStreamer(true);
            HttpUtils.delete("payslip_template", params, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    super.onSuccess(statusCode, headers, response);
                }
                //                @Override
//                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
//                    Toast.makeText(GetAllPayslipTemplateActivity.this,"Đã xóa",Toast.LENGTH_SHORT ).show();
//                    adapter.notifyDataSetChanged();
//                }
//
//                @Override
//                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
//                    Toast.makeText(GetAllPayslipTemplateActivity.this,"Xóa thất bại",Toast.LENGTH_SHORT ).show();
//                }
            });
        }
        return super.onOptionsItemSelected(item);
    }

    public void clickToCreateTemplate(View view) {
        startActivity(new Intent(this, PaySlipTemplateActivity.class));
        onStop();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        getAllPayslipTemplate();
    }
}
