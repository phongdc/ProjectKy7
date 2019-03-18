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

import Model.Payroll;
import Model.PayslipTemplate;

public class Payslip_TemplateAdapter extends RecyclerView.Adapter<Payslip_TemplateAdapter.Payslip_TemplateViewHolder>{
    public static List<PayslipTemplate> payslipTemplateList;
    private Context context;


    public Payslip_TemplateAdapter(List<PayslipTemplate> payslipTemplateList, Context context) {
        this.context = context;
        this.payslipTemplateList = payslipTemplateList;
    }

    @NonNull
    @Override
    public Payslip_TemplateViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.period_single_view, viewGroup, false);
        return new Payslip_TemplateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Payslip_TemplateViewHolder payslip_templateViewholder, int i) {
        payslip_templateViewholder.tvPayslipName.setText(payslipTemplateList.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return payslipTemplateList.size();
    }

    public static class Payslip_TemplateViewHolder extends RecyclerView.ViewHolder{
        private CardView cv;
        private TextView tvPayslipName;
        private ItemClickListener itemClickListener;
        public Payslip_TemplateViewHolder(@NonNull View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cvPayPeriod);
            tvPayslipName = itemView.findViewById(R.id.tvPeriodName);
        }
    }
}
