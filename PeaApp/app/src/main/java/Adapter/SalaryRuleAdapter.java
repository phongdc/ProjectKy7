package Adapter;

import android.content.Context;
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

import Model.SalaryRule;

public class SalaryRuleAdapter extends RecyclerView.Adapter<SalaryRuleAdapter.SalaryRuleViewHolder> {
    private Context context;
    private List<SalaryRule> salaryRuleList;
    public SalaryRuleAdapter(Context context, List<SalaryRule> salaryRuleList)  {
        this.context = context;
        this.salaryRuleList = salaryRuleList;
    }

    @NonNull
    @Override
    public SalaryRuleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.period_single_view, viewGroup, false);

        return new SalaryRuleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SalaryRuleViewHolder salaryRuleViewHolder, int i) {
        salaryRuleViewHolder.tvSalaryRuleName.setText(salaryRuleList.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return salaryRuleList.size();
    }

    public static class SalaryRuleViewHolder extends RecyclerView.ViewHolder {
        private CardView cv;
        private TextView tvSalaryRuleName;
        private ItemClickListener itemClickListener;

        public SalaryRuleViewHolder(@NonNull View itemView) {

            super(itemView);
            cv = itemView.findViewById(R.id.cvPayPeriod);
            tvSalaryRuleName = itemView.findViewById(R.id.tvPeriodName);

        }
    }
}
