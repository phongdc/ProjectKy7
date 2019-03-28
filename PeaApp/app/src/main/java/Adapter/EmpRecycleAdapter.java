package Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.phongdc.peaapp.Employees.EmpDetailsActivity;
import com.example.phongdc.peaapp.ItemClickListener;
import com.example.phongdc.peaapp.R;

import java.util.List;

import Model.Employee;

public class EmpRecycleAdapter extends RecyclerView.Adapter<EmpRecycleAdapter.EmpViewHolder> {
    public static class EmpViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        CardView cv;
        TextView empName;
        TextView empCode;
        private ItemClickListener itemClickListener;
        public EmpViewHolder(View itemView){
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            empName = (TextView)itemView.findViewById(R.id.tvEmpName);
            empCode = (TextView)itemView.findViewById(R.id.tvEmpCode);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }
        public void setItemClickListener(ItemClickListener itemClickListener){
            this.itemClickListener = itemClickListener;
        }
        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v, getAdapterPosition(),false);
        }

        @Override
        public boolean onLongClick(View v) {
            itemClickListener.onClick(v,getAdapterPosition(), true);
            return true;
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
    public void onBindViewHolder(final EmpViewHolder holder, int position) {
        holder.empName.setText(employees.get(position).employee_name);
        holder.empCode.setText(employees.get(position).code);

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                Context context = view.getContext();
                Intent intent = new Intent(context,EmpDetailsActivity.class);
                int s = employees.get(position).getId();
                Bundle bundle = new Bundle();
                bundle.putInt("ID", s);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return employees.size();
    }
}
