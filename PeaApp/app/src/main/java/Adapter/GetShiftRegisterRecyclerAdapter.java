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

import Model.ShiftRegister;

public class GetShiftRegisterRecyclerAdapter extends RecyclerView.Adapter<GetShiftRegisterRecyclerAdapter.ShiftRegisterViewHolder> {

public static class ShiftRegisterViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

    CardView cv;
    TextView tvShiftRegisterName;
    private ItemClickListener itemClickListener;
    public ShiftRegisterViewHolder(View itemView) {
        super(itemView);
        cv = (CardView)itemView.findViewById(R.id.cvShiftRegister);
        tvShiftRegisterName = (TextView)itemView.findViewById(R.id.tvShiftRegisterName);

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

    private List<ShiftRegister> shiftRegisters;
    private Context context;
    public GetShiftRegisterRecyclerAdapter(List<ShiftRegister> shiftRegisters, Context context){
        this.shiftRegisters = shiftRegisters;
        this.context = context;

    }

    @NonNull
    @Override
    public GetShiftRegisterRecyclerAdapter.ShiftRegisterViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.shift_register_view, viewGroup, false);
        return new GetShiftRegisterRecyclerAdapter.ShiftRegisterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final GetShiftRegisterRecyclerAdapter.ShiftRegisterViewHolder ShiftRegisterViewHolder, final int i) {
        ShiftRegisterViewHolder.tvShiftRegisterName.setText(shiftRegisters.get(i).getEmpName().toString());

//        ShiftRegisterViewHolder.setItemClickListener(new ItemClickListener() {
//            @Override
//            public void onClick(View view, int position, boolean isLongClick) {
//                Context context = view.getContext();
//                Intent intent = new Intent(context, ShiftRegisterDetails.class);
//
//                int shiftId = shiftRegisters.get(i).getShiftId();
//                int timeFrameId = shiftRegisters.get(i).getTimeFrameId();
//                String status = shiftRegisters.get(i).getStatus();
//                String empName = shiftRegisters.get(i).getEmpName();
//                String startDate = shiftRegisters.get(i).getStartDate();
//                String endDate = shiftRegisters.get(i).getEndDate();
//                String startTime = shiftRegisters.get(i).getStartTime();
//                String endTime = shiftRegisters.get(i).getEndTime();
//
//                Bundle bundle = new Bundle();
//                bundle.putInt("ShiftID", shiftId);
//                bundle.putInt("TimeFrameID", timeFrameId);
//                bundle.putString("Status", status);
//                bundle.putString("EmpNAME", empName);
//                bundle.putString("StartDATE", startDate);
//                bundle.putString("EndDATE", endDate);
//                bundle.putString("StartTIME", startTime);
//                bundle.putString("EndTIME", endTime);
//                intent.putExtras(bundle);
//                context.startActivity(intent);
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return shiftRegisters.size();
    }
}
