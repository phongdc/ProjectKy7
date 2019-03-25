package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.phongdc.peaapp.ItemClickListener;
import com.example.phongdc.peaapp.R;

import java.util.List;

import Model.DayMode;

public class DayModeAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<DayMode> dayModes;

    public DayModeAdapter(Context context, int layout, List<DayMode> dayModes) {
        this.context = context;
        this.layout = layout;
        this.dayModes = dayModes;
    }


    @Override
    public int getCount() {
        return dayModes.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }



    private class ViewHolder implements View.OnClickListener{
        TextView txtNameMode, txtPriority, tvDayMode ;
        CheckBox checkBoxDayMode;
        private ItemClickListener itemClickListener;

        @Override
        public void onClick(View v) {

        }
        public void setItemClickListener(ItemClickListener itemClickListener){
            this.itemClickListener = itemClickListener;
        }
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        final DayModeAdapter.ViewHolder holder;
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder = new DayModeAdapter.ViewHolder();
            holder.txtNameMode =  view.findViewById(R.id.txtNameMode);
            holder.txtPriority =  view.findViewById(R.id.txtPriority);
            view.setTag(holder);
        }else{
            holder = (DayModeAdapter.ViewHolder) view.getTag();
        }
        final DayMode dayMode = dayModes.get(i);
        holder.txtNameMode.setText(dayMode.getName());
        holder.txtPriority.setText("Priority: " + dayMode.getPriority());

        return view;
    }

}
