package Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.phongdc.peaapp.EmpDetailsActivity;
import com.example.phongdc.peaapp.ItemClickListener;
import com.example.phongdc.peaapp.PayPeriodDetails;
import com.example.phongdc.peaapp.R;

import java.util.List;

import Model.PayrollPeriod;

public class PayPeriodRecyclerAdapter extends RecyclerView.Adapter<PayPeriodRecyclerAdapter.PayPeriodViewHolder> {
    public static class PayPeriodViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

        CardView cv;
        TextView tvPeriodName;
        private ItemClickListener itemClickListener;
        public PayPeriodViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cvPayPeriod);
            tvPeriodName = (TextView)itemView.findViewById(R.id.tvPeriodName);

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
    private List<PayrollPeriod> payrollPeriods;
    private Context context;
    public PayPeriodRecyclerAdapter(List<PayrollPeriod> payrollPeriods, Context context){
        this.payrollPeriods = payrollPeriods;
        this.context = context;

    }

    @NonNull
    @Override
    public PayPeriodViewHolder onCreateViewHolder( ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.period_single_view, viewGroup, false);
        return new PayPeriodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PayPeriodViewHolder payPeriodViewHolder, final int i) {
        payPeriodViewHolder.tvPeriodName.setText(payrollPeriods.get(i).getName());

        payPeriodViewHolder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                Context context = view.getContext();
                Intent intent = new Intent(context, PayPeriodDetails.class);

                int a = payrollPeriods.get(i).getId();
                String b = payrollPeriods.get(i).getName();
                Bundle bundle = new Bundle();
                bundle.putInt("ID", a);
                bundle.putString("NAME", b);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return payrollPeriods.size();
    }
}
