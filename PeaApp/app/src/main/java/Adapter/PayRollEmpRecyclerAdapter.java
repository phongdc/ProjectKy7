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

import Model.PayRollEmp;
import Model.TimeFrame;



public class PayRollEmpRecyclerAdapter extends RecyclerView.Adapter<PayRollEmpRecyclerAdapter.PayRollEmpViewHolder> {

    public static class PayRollEmpViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

        CardView cv;
        TextView tvPayRollEmoName;
        TextView tvPayRollEmpFromDate;
        TextView tvPayRollEmpToDate;
        
        private ItemClickListener itemClickListener;
        public PayRollEmpViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cvPayrollEmp);
            tvPayRollEmoName = (TextView)itemView.findViewById(R.id.tvPayrollEmpName);
            tvPayRollEmpFromDate = (TextView)itemView.findViewById(R.id.tvPayrollEmpFromDate);
            tvPayRollEmpToDate = (TextView)itemView.findViewById(R.id.tvPayrollEmpToDate);

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

    private List<PayRollEmp> payRollEmps;
    private Context context;
    public PayRollEmpRecyclerAdapter(List<PayRollEmp> payRollEmps, Context context){
        this.payRollEmps = payRollEmps;
        this.context = context;

    }

    @NonNull
    @Override
    public PayRollEmpRecyclerAdapter.PayRollEmpViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.payroll_period_emp_view, viewGroup, false);
        return new PayRollEmpRecyclerAdapter.PayRollEmpViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PayRollEmpRecyclerAdapter.PayRollEmpViewHolder PayRollEmpViewHolder, final int i) {
        PayRollEmpViewHolder.tvPayRollEmoName.setText(payRollEmps.get(i).getName().toString());
        PayRollEmpViewHolder.tvPayRollEmpFromDate.setText(payRollEmps.get(i).getFromDate().toString());
        PayRollEmpViewHolder.tvPayRollEmpToDate.setText(payRollEmps.get(i).getToDate().toString());

        PayRollEmpViewHolder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
//                Context context = view.getContext();
//                Intent intent = new Intent(context, PayPeriodDetails.class);
//
//                int a = timeFrame.get(i).getId();
//                String b = timeFrame.get(i).getName();
//                Bundle bundle = new Bundle();
//                bundle.putInt("ID", a);
//                bundle.putString("NAME", b);
//                intent.putExtras(bundle);
//                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return payRollEmps.size();
    }
}
