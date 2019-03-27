package com.example.phongdc.peaapp.SalaryRule;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.phongdc.peaapp.R;

public class CreateSalaryRule extends AppCompatActivity {
    private TextView tvTitle;
    private EditText edtSalaryRuleName;
    private EditText edtSalaryValue;
    private EditText edtDurationMin;
    private EditText edtDurationMax;
    private EditText edtRateValue;
    private Spinner spnTimeMode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_salary_rule);
        findViewById();
    }
    private void findViewById(){
        tvTitle = findViewById(R.id.tvTitle);
        tvTitle.setText("");
        edtSalaryRuleName = findViewById(R.id.edtSalaryRuleName);
        edtRateValue = findViewById(R.id.edtSalaryValue);
        edtDurationMin = findViewById(R.id.edtDurationMin);
        edtDurationMax = findViewById(R.id.edtDurationMax);
        edtRateValue = findViewById(R.id.edtRateValue);
        spnTimeMode = findViewById(R.id.spnTimeMode);
    }
    public void clickToCreateSalary(View view) {


    }
}
