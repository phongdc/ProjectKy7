package Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.phongdc.peaapp.R;

import java.util.List;

import Model.ListItem;
import Model.PaySlip;
public class PaySlipAdapter  extends RecyclerView.Adapter<PaySlipAdapter.ViewHolder>{
    private Context context;
    private List<ListItem> data;

    public PaySlipAdapter(Context context, List<ListItem> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_pay_slip_adapter, viewGroup, false);
        return new PaySlipAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.mtxtName.setText(data.get(position).getName());
        viewHolder.mTxtValue.setText(data.get(position).getValue()+"");
    }

    @Override
    public int getItemCount() {
        int count = (data != null) ? data.size() : 0;
        return count;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private  TextView mtxtName,mTxtValue;
        private LinearLayout mLinearLayout;
        public ViewHolder(View itemView) {
            super(itemView);
           mtxtName = itemView.findViewById(R.id.text_name_pay_slip);
           mTxtValue = itemView.findViewById(R.id.text_value_pay_slip);
        }
    }
}
