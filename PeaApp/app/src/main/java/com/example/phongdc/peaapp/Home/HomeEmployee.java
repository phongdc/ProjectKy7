package com.example.phongdc.peaapp.Home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.Time;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.phongdc.peaapp.CheckFinger.CheckFingerActivity;
import com.example.phongdc.peaapp.LeftMenu.FragmentDrawer;
import com.example.phongdc.peaapp.LoginActivity;
import com.example.phongdc.peaapp.MyDetailActivity;
import com.example.phongdc.peaapp.PayRollEmpActivity;
import com.example.phongdc.peaapp.PayrollDetail.PayrollDetailActivity;
import com.example.phongdc.peaapp.PayrollPeriod.GetPeriodActivity;
import com.example.phongdc.peaapp.R;
import com.example.phongdc.peaapp.ShiftRegister.ShiftRegisterActivity;

public class HomeEmployee extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener{
    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;
    private TextView tvTitle;
    private TextView tvCurrentDay;
    public static int ID;
    public static String Code;
    private TextView tvUsername;
    private TextView tvUserCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_employee);

        mToolbar = findViewById(R.id.toolbar);
        tvTitle = findViewById(R.id.tvTitle);
        tvCurrentDay = findViewById(R.id.tvCurrentDay);
        tvUsername = findViewById(R.id.tvUserName);
        tvUserCode = findViewById(R.id.tvCode);
        Time today = new Time();
        today.setToNow();
        int month = today.month +1;
        tvCurrentDay.setText("Ngày "+today.monthDay +" tháng " +month +" năm "+today.year);
        tvTitle.setText("HOME EMPLOYEE");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        drawerFragment = (FragmentDrawer) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawers);
        drawerFragment.setUp(R.id.fragment_navigation_drawers, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);
        Bundle extras = this.getIntent().getExtras();
        ID = extras.getInt("UserID");
        Code = extras.getString("Code");
        String name = extras.getString("UserName");
        tvUsername.setText(name);
        tvUserCode.setText(Code);

    }
    public static String getUserCode(){
        return Code;
    }
    public static int getUserID(){
        return ID;
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
    private void displayView(int position){
        switch (position){
            case 0:
                break;
            case 1:
                startActivity(new Intent(HomeEmployee.this, LoginActivity.class));
                finish();
                break;
            case 3:
                break;
        }

    }



    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }
    public void clickToPayPeriodEmp(View view) {
        startActivity(new Intent(HomeEmployee.this, PayRollEmpActivity.class));
    }

    public void clickToOff(View view) {
        startActivity(new Intent(HomeEmployee.this, PayrollDetailActivity.class));

    }

    public void clickToPayrollDetailEmp(View view) {
        startActivity(new Intent(HomeEmployee.this, PayrollDetailActivity.class));

    }
    public void clickToAttendanceEmp(View view) {
        startActivity(new Intent(HomeEmployee.this, PayrollDetailActivity.class));
    }

    public void clickToCheckAttendanceEmp(View view) {
        startActivity(new Intent(HomeEmployee.this, CheckFingerActivity.class));

    }

    public void clickToAcceptShiftRegisterEmp(View view) {
        startActivity(new Intent(HomeEmployee.this, ShiftRegisterActivity.class));

    }

    public void clickToMyDetail(View view) {
        startActivity(new Intent(HomeEmployee.this, MyDetailActivity.class));
    }
}
