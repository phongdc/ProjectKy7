package com.example.phongdc.peaapp;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.format.Time;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.phongdc.peaapp.DayMode.DayModeActivity;
import com.example.phongdc.peaapp.EmployeeGroups.GetEmpGroupActivity;
import com.example.phongdc.peaapp.Employees.ListEmp;
import com.example.phongdc.peaapp.LeftMenu.FragmentDrawer;
import com.example.phongdc.peaapp.PayrollDetail.PayrollDetailActivity;
import com.example.phongdc.peaapp.PayrollPeriod.GetPeriodActivity;
import com.example.phongdc.peaapp.PayslipTemplate.GetAllPayslipTemplateActivity;
import com.example.phongdc.peaapp.User.MyDetailActivity;

public class HomeActivity extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener{
    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;
    private TextView tvTitle;
    private TextView tvCurrentDay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mToolbar = findViewById(R.id.toolbar);
        tvTitle = findViewById(R.id.tvTitle);
        tvCurrentDay = findViewById(R.id.tvCurrentDay);
        Time today = new Time();
        today.setToNow();
        int month = today.month +1;
        tvCurrentDay.setText("Ngày "+today.monthDay +" tháng " +month +" năm "+today.year);
        tvTitle.setText("HOME");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        drawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

   public void clickToEmployeeList(View view) {
        startActivity(new Intent(HomeActivity.this, ListEmp.class));
    }

    public void clickToMyDetail(View view) {
        startActivity(new Intent(HomeActivity.this, MyDetailActivity.class));
    }

    public void clickToGroupEmp(View view) {
        startActivity(new Intent(HomeActivity.this, GetEmpGroupActivity.class));
    }

    public void clickToPayPeriod(View view) {
        startActivity(new Intent(HomeActivity.this, GetPeriodActivity.class));

    }

    public void clickToPaySlipTemplate(View view) {
        startActivity(new Intent(HomeActivity.this, GetAllPayslipTemplateActivity.class));
    }

    public void clickToPayrollDetail(View view) {
        startActivity(new Intent(HomeActivity.this, PayrollDetailActivity.class));
    }
    public void clickToDayMode(View view) {
        startActivity(new Intent(HomeActivity.this, DayModeActivity.class));
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {

    }


    public void clickToTimeFrame(View view) {
        startActivity(new Intent(HomeActivity.this, TimeFrameActivity.class));
    }


    public void clickToAcceptShiftRegister(View view) {
        startActivity(new Intent(HomeActivity.this, AcceptShiftRegister.class));
    }

    public void clickToSalaryRule(View view) {
    }
}
