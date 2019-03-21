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

import Model.DayModeInf;

public class GetInfomationDayTypeAdapter extends RecyclerView.Adapter<GetInfomationDayTypeAdapter.PayrollViewHolder>{
    public static List<DayModeInf> dayModeInfs;
    private Context context;


    public GetInfomationDayTypeAdapter(List<DayModeInf> dayModeInfs, Context context) {
        this.context = context;
        this.dayModeInfs = dayModeInfs;
    }


    @NonNull
    @Override
    public PayrollViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_day_mode_day_of_week, viewGroup, false);
        return new PayrollViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PayrollViewHolder payrollViewHolder, final int i) {
        payrollViewHolder.tvDayMode.setText(dayModeInfs.get(i).getNameDay());
        payrollViewHolder.checkBoxDayMode.setChecked(dayModeInfs.get(i).isActive());
        payrollViewHolder.checkBoxDayMode.setTag(i);
        payrollViewHolder.checkBoxDayMode.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Integer pos = (Integer) payrollViewHolder.checkBoxDayMode.getTag();
                if(dayModeInfs.get(pos).isActive()){
                    dayModeInfs.get(pos).setActive(false);
                }else {
                    dayModeInfs.get(pos).setActive(true);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return dayModeInfs.size();
    }

    public static class PayrollViewHolder extends  RecyclerView.ViewHolder implements OnClickListener{
        private CardView cvDayModeInf;
        private TextView tvDayMode;
        private ItemClickListener itemClickListener;
        public CheckBox checkBoxDayMode;
        public PayrollViewHolder(@NonNull View itemView) {
            super(itemView);
            cvDayModeInf = itemView.findViewById(R.id.cvDayModeInf);
            tvDayMode = itemView.findViewById(R.id.tvDayMode);
            checkBoxDayMode = itemView.findViewById(R.id.checkBoxDayMode);
            //itemView.setOnClickListener(this);
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
