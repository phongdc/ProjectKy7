package Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
import com.example.phongdc.peaapp.ShiftRegisterDetailsActivity;

import java.util.List;

import Model.ShiftRegister;
import Model.ShiftRegisterDetails;

public class ShiftRegisterDetailsRecyclerAdapter extends RecyclerView.Adapter<ShiftRegisterDetailsRecyclerAdapter.ShiftRegisterDetailsViewHolder> {

    public static class ShiftRegisterDetailsViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

        CardView cv;
        TextView tvShiftRegisterDetailsName;
        TextView tvShiftRegisterDetailsStartTime;
        TextView tvShiftRegisterDetailsEndTime;
        TextView tvShiftRegisterDetailsStartDate;
        TextView tvShiftRegisterDetailsEndDate;
        TextView tvShiftRegisterDetailsTimeFrame;
        TextView tvShiftRegisterDetailsStatus;


        private ItemClickListener itemClickListener;
        public ShiftRegisterDetailsViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cvShiftRegisterDetails);
            tvShiftRegisterDetailsName = (TextView)itemView.findViewById(R.id.tvShiftRegisterDetailsName);
            tvShiftRegisterDetailsStartTime = (TextView)itemView.findViewById(R.id.tvShiftRegisterDetailsStartTime);
            tvShiftRegisterDetailsEndTime = (TextView)itemView.findViewById(R.id.tvShiftRegisterDetailsEndTime);
            tvShiftRegisterDetailsStartDate = (TextView)itemView.findViewById(R.id.tvShiftRegisterDetailsStartDate);
            tvShiftRegisterDetailsEndDate = (TextView)itemView.findViewById(R.id.tvShiftRegisterDetailsEndDate);
            tvShiftRegisterDetailsTimeFrame = (TextView)itemView.findViewById(R.id.tvShiftRegisterDetailsTimeFrameName);
            tvShiftRegisterDetailsStatus = (TextView)itemView.findViewById(R.id.tvShiftRegisterDetailsStatus);

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

    private List<ShiftRegisterDetails> shiftRegistersDetails;
    private Context context;
    public ShiftRegisterDetailsRecyclerAdapter(List<ShiftRegisterDetails> shiftRegistersDetails, Context context){
        this.shiftRegistersDetails = shiftRegistersDetails;
        this.context = context;

    }

    @NonNull
    @Override
    public ShiftRegisterDetailsRecyclerAdapter.ShiftRegisterDetailsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.shift_register_details_view, viewGroup, false);
        return new ShiftRegisterDetailsRecyclerAdapter.ShiftRegisterDetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ShiftRegisterDetailsRecyclerAdapter.ShiftRegisterDetailsViewHolder ShiftRegisterViewHolder, final int i) {
        ShiftRegisterViewHolder.tvShiftRegisterDetailsName.setText(shiftRegistersDetails.get(i).getEmpName().toString());
        ShiftRegisterViewHolder.tvShiftRegisterDetailsStartTime.setText(shiftRegistersDetails.get(i).getStartTime().toString());
        ShiftRegisterViewHolder.tvShiftRegisterDetailsEndTime.setText(shiftRegistersDetails.get(i).getEndTime().toString());
        ShiftRegisterViewHolder.tvShiftRegisterDetailsStartDate.setText(shiftRegistersDetails.get(i).getStartDate().toString());
        ShiftRegisterViewHolder.tvShiftRegisterDetailsEndDate.setText(shiftRegistersDetails.get(i).getEndDate().toString());
        ShiftRegisterViewHolder.tvShiftRegisterDetailsTimeFrame.setText(shiftRegistersDetails.get(i).getTimeFrameName().toString());
        if(shiftRegistersDetails.get(i).getStatus().toString().equals("Đã duyệt")){
            ShiftRegisterViewHolder.tvShiftRegisterDetailsStatus.setText(shiftRegistersDetails.get(i).getStatus().toString());
            ShiftRegisterViewHolder.tvShiftRegisterDetailsStatus.setTextColor(Color.GREEN);
        } else {
            ShiftRegisterViewHolder.tvShiftRegisterDetailsStatus.setText(shiftRegistersDetails.get(i).getStatus().toString());
            ShiftRegisterViewHolder.tvShiftRegisterDetailsStatus.setTextColor(Color.RED);
        }


        ShiftRegisterViewHolder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
//                Context context = view.getContext();
//                Intent intent = new Intent(context, ShiftRegisterDetailsActivity.class);
////
////                int shiftId = shiftRegisters.get(i).getShiftId();
////                int timeFrameId = shiftRegisters.get(i).getTimeFrameId();
////                String status = shiftRegisters.get(i).getStatus();
//                String empName = shiftRegisters.get(i).getEmpName();
////                String startDate = shiftRegisters.get(i).getStartDate();
////                String endDate = shiftRegisters.get(i).getEndDate();
////                String startTime = shiftRegisters.get(i).getStartTime();
////                String endTime = shiftRegisters.get(i).getEndTime();
////
//                Bundle bundle = new Bundle();
////                bundle.putInt("ShiftID", shiftId);
////                bundle.putInt("TimeFrameID", timeFrameId);
////                bundle.putString("Status", status);
//                bundle.putString("EmpNAME", empName);
////                bundle.putString("StartDATE", startDate);
////                bundle.putString("EndDATE", endDate);
////                bundle.putString("StartTIME", startTime);
////                bundle.putString("EndTIME", endTime);
//                intent.putExtras(bundle);
//                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return shiftRegistersDetails.size();
    }
}
