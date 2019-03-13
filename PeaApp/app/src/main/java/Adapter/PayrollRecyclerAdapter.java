package Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import com.example.phongdc.peaapp.R;

import com.example.phongdc.peaapp.ItemClickListener;

import java.util.List;

import Model.Payroll;

public class PayrollRecyclerAdapter extends RecyclerView.Adapter<PayrollRecyclerAdapter.PayrollViewHolder>{
    private List<Payroll> payrolls;
    private Context context;

    public PayrollRecyclerAdapter(List<Payroll> payrolls, Context context) {
        this.context = context;
        this.payrolls = payrolls;
    }


    @NonNull
    @Override
    public PayrollViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.payroll_detail_single_view, viewGroup, false);
        return new PayrollViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PayrollViewHolder payrollViewHolder, int i) {
        payrollViewHolder.tvPayrollName.setText(payrolls.get(i).getName());

    }

    @Override
    public int getItemCount() {
        return payrolls.size();
    }

    public static class PayrollViewHolder extends  RecyclerView.ViewHolder implements OnClickListener{
        private CardView cv;
        private TextView tvPayrollName;
        private ItemClickListener itemClickListener;
        private CheckBox chkSelected;
        public PayrollViewHolder(@NonNull View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cvPayroll);
            tvPayrollName = itemView.findViewById(R.id.tvPayrollName);
            chkSelected = itemView.findViewById(R.id.chkSelected);

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
