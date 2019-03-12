package com.example.phongdc.peaapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class CreatePaySlipGroupActivity extends AppCompatActivity{

    private String nameApi = "PaySlip";


    private ListView lvAddGroup;
    private Button btnUpdate;
    private Button btnAdd;
    private Button btnRemove;
    private EditText editText;
    private ArrayList<String> arrayCourse;
    private int vitri = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_list);
        lvAddGroup = findViewById(R.id.lvAddGroup);
        btnAdd = findViewById(R.id.btnAdd);
        editText = findViewById(R.id.editText);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnRemove = findViewById(R.id.btnRemove);



        arrayCourse = new ArrayList<>();
        final ArrayAdapter adapter = new ArrayAdapter(CreatePaySlipGroupActivity.this, android.R.layout.simple_list_item_1, arrayCourse);
        lvAddGroup.setAdapter(adapter);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emp = editText.getText().toString();
                arrayCourse.add(emp);
                adapter.notifyDataSetChanged();
            }
        });
        lvAddGroup.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                editText.setText(arrayCourse.get(i));
                vitri = i;
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayCourse.set(vitri, editText.getText().toString());
                adapter.notifyDataSetChanged();
            }
        });
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayCourse.remove(vitri);
                adapter.notifyDataSetChanged();
            }
        });
    }


}


