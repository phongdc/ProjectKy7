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
import com.example.phongdc.peaapp.PayPeriodDetails;
import com.example.phongdc.peaapp.R;

import java.util.List;

import Model.TimeFrame;

public class TimeFrameRecyclerAdapter extends RecyclerView.Adapter<TimeFrameRecyclerAdapter.TimeFrameViewHolder> {

public static class TimeFrameViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

    CardView cv;
    TextView tvTimeFrameName;
    private ItemClickListener itemClickListener;
    public TimeFrameViewHolder(View itemView) {
        super(itemView);
        cv = (CardView)itemView.findViewById(R.id.cvTimeFrame);
        tvTimeFrameName = (TextView)itemView.findViewById(R.id.tvTimeFrameName);

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

    private List<TimeFrame> timeFrame;
    private Context context;
    public TimeFrameRecyclerAdapter(List<TimeFrame> timeFrame, Context context){
        this.timeFrame = timeFrame;
        this.context = context;

    }

    @NonNull
    @Override
    public TimeFrameRecyclerAdapter.TimeFrameViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.time_frame_view, viewGroup, false);
        return new TimeFrameRecyclerAdapter.TimeFrameViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TimeFrameRecyclerAdapter.TimeFrameViewHolder TimeFrameViewHolder, final int i) {
        TimeFrameViewHolder.tvTimeFrameName.setText(timeFrame.get(i).getName().toString());

//        TimeFrameViewHolder.setItemClickListener(new ItemClickListener() {
//            @Override
//            public void onClick(View view, int position, boolean isLongClick) {
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
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return timeFrame.size();
    }
}
