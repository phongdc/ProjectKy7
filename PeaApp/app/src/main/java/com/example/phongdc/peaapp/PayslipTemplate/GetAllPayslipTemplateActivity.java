package com.example.phongdc.peaapp.PayslipTemplate;

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

import Adapter.Payslip_TemplateAdapter;
import Model.PayslipTemplate;
import cz.msebera.android.httpclient.Header;

public class GetAllPayslipTemplateActivity extends AppCompatActivity {
    private TextView tvTitle;
    private RecyclerView rv_Payslip_template;
    private List<PayslipTemplate> payslipTemplateList;
    private List<Integer> itemList;
    private Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_all_payslip_template);
        findViewById();
        tvTitle.setText("Mẫu phiếu lương");
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        payslipTemplateList = new ArrayList<>();
        rv_Payslip_template.setLayoutManager(new LinearLayoutManager(this));
        getAllPayslipTemplate();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            itemList = new ArrayList<Integer>();
            for (int i = 0; i < Payslip_TemplateAdapter.payslipTemplateList.size(); i++) {
                try{
                    if(Payslip_TemplateAdapter.payslipTemplateList.get(i).isSelected()){
                        itemList.add(Payslip_TemplateAdapter.payslipTemplateList.get(i).getId());
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            JSONArray jsonArray = new JSONArray(itemList);
            RequestParams params =new RequestParams();
            params.put("list_id", jsonArray);
            //params.setUseJsonStreamer(true);
            try{
            HttpUtils.delete("payslip_template", params, new JsonHttpResponseHandler() {

                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    try {
                        if(response.getBoolean("success") == true){
                            Toast.makeText(GetAllPayslipTemplateActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                    super.onFailure(statusCode, headers, throwable, errorResponse);
                    Toast.makeText(GetAllPayslipTemplateActivity.this, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                }
            });
            return true;
        }catch (Exception e){
            e.printStackTrace();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void findViewById() {
        tvTitle = findViewById(R.id.tvTitle);
        rv_Payslip_template = findViewById(R.id.rv_Payslip_template);

    }

    public void getAllPayslipTemplate() {
        HttpUtils.getAuth("payslip_template",HomeActivity.getToken(), null,new JsonHttpResponseHandler() {
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
                    rv_Payslip_template.setAdapter(new Payslip_TemplateAdapter(payslipTemplateList, GetAllPayslipTemplateActivity.this));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
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
