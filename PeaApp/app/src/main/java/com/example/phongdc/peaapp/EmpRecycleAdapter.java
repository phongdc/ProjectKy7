package com.example.phongdc.peaapp;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import Model.Employee;

public class EmpRecycleAdapter extends RecyclerView.Adapter<EmpRecycleAdapter.EmpViewHolder> {
  public static class EmpViewHolder extends RecyclerView.ViewHolder{
      CardView cv;
      TextView empName;
      TextView empCode;
      public EmpViewHolder(View itemView){
          super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            empName = (TextView)itemView.findViewById(R.id.tvEmpName);
            empCode = (TextView)itemView.findViewById(R.id.tvEmpCode);
      }
  }
    private List<Employee> employees;
    private Context context;
    public EmpRecycleAdapter(List<Employee> employees, Context context ){
        this.employees = employees;
        this.context = context;
    }
    @Override
    public EmpViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.emp_view, parent, false);
        return new EmpViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EmpViewHolder holder, int position) {
        holder.empName.setText(employees.get(position).employee_name);
        holder.empCode.setText(employees.get(position).code);

    }


    @Override
    public int getItemCount() {
        return employees.size();
    }
}
