package Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
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
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.payroll_detail_single_view, viewGroup, false);
        return new Payslip_TemplateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Payslip_TemplateViewHolder payslip_templateViewholder, int i) {
        payslip_templateViewholder.tvPayslipName.setText(payslipTemplateList.get(i).getName());
        payslip_templateViewholder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                payslip_templateViewholder.chkSelected.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return payslipTemplateList.size();
    }

    public static class Payslip_TemplateViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private CardView cv;
        private TextView tvPayslipName;
        private ItemClickListener itemClickListener;
        public CheckBox chkSelected;
        public Payslip_TemplateViewHolder(@NonNull View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cvPayroll);
            tvPayslipName = itemView.findViewById(R.id.tvPayrollName);
            chkSelected = itemView.findViewById(R.id.chkSelected);
            chkSelected.setVisibility(View.INVISIBLE);
            itemView.setOnLongClickListener(this);
        }
        public void setItemClickListener(ItemClickListener itemClickListener){
            this.itemClickListener = itemClickListener;
        }
        @Override
        public void onClick(View v) {

        }

        @Override
        public boolean onLongClick(View v) {
            itemClickListener.onClick(v,getAdapterPosition(), true);
            return true;
        }
    }
}
