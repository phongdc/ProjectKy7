package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.example.phongdc.peaapp.R;

import java.util.List;

import Model.DayType;

public class DayTypeAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<DayType> dayTypes;

    public DayTypeAdapter(Context context, int layout, List<DayType> dayTypes) {
        this.context = context;
        this.layout = layout;
        this.dayTypes = dayTypes;
    }

    @Override
    public int getCount() {
        return dayTypes.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder{
        TextView txtNameType, txtPriority;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        DayTypeAdapter.ViewHolder holder;
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder = new DayTypeAdapter.ViewHolder();
            holder.txtNameType =  view.findViewById(R.id.txtNameType );

            holder.txtPriority =  view.findViewById(R.id.txtPriority);
            view.setTag(holder);
        }else{
            holder = (DayTypeAdapter.ViewHolder) view.getTag();
        }
        DayType dayType = dayTypes.get(i);
        holder.txtNameType.setText("Tên ngày: " + dayType.getName());
        holder.txtPriority.setText("Priority: " + dayType.getPriority());

        return view;
    }

}
