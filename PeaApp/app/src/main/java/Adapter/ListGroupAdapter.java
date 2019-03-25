package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.phongdc.peaapp.R;

import java.util.List;

import Model.EmpGroup;
public class ListGroupAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<EmpGroup> empGroups;

    public ListGroupAdapter(Context context, int layout, List<EmpGroup> empGroups) {
        this.context = context;
        this.layout = layout;
        this.empGroups = empGroups;
    }

    @Override
    public int getCount() {
        return empGroups.size();
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
        TextView txtTenGroup, txtNgayTao;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder;
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder = new ViewHolder();
            holder.txtTenGroup =  view.findViewById(R.id.txtTenGroup );
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        EmpGroup listGroup = empGroups.get(i);
        holder.txtTenGroup.setText(listGroup.getName());
        return view;
    }
}
