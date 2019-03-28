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

import com.example.phongdc.peaapp.ItemClickListener;
import com.example.phongdc.peaapp.R;

import java.util.List;

import Model.SalaryRuleGroup;

public class SalaryRuleGroupAdapter extends RecyclerView.Adapter<SalaryRuleGroupAdapter.SalaryRuleGroupViewHolder> {
    private Context context;
    private List<SalaryRuleGroup> salaryGroupList;

    public SalaryRuleGroupAdapter(Context context, List<SalaryRuleGroup> salaryGroupList) {
        this.context = context;
        this.salaryGroupList = salaryGroupList;
    }

    @NonNull
    @Override
    public SalaryRuleGroupViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.period_single_view, viewGroup, false);

        return new SalaryRuleGroupViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SalaryRuleGroupViewHolder salaryRuleGroupViewHolder, int i) {
        salaryRuleGroupViewHolder.tvSalaryGroupName.setText(salaryGroupList.get(i).getName());
        salaryRuleGroupViewHolder.setItemClickListener(new ItemClickListener(){

            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                Context context = view.getContext();
                Intent intent = new Intent(context, com.example.phongdc.peaapp.SalaryRule.SalaryRule.class);

                int s = salaryGroupList.get(position).getId();
                Bundle bundle = new Bundle();
                bundle.putInt("salaryGroupID", s);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return salaryGroupList.size();
    }

    public static class SalaryRuleGroupViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private CardView cv;
        private TextView tvSalaryGroupName;
        private ItemClickListener itemClickListener;
        public SalaryRuleGroupViewHolder(@NonNull View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cvPayPeriod);
            tvSalaryGroupName = itemView.findViewById(R.id.tvPeriodName);
            itemView.setOnClickListener(this);
        }
        public void setItemClickListener(ItemClickListener itemClickListener){
            this.itemClickListener = itemClickListener;
        }
        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v, getAdapterPosition(),false);
        }
    }

}
