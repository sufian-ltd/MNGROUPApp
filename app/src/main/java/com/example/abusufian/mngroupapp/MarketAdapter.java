package com.example.abusufian.mngroupapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class MarketAdapter extends ArrayAdapter<Product> {
    Context context;
    List<Product> list;
    public MarketAdapter(Context context, List<Product> list) {
        super(context, R.layout.list_item, list);
        this.context = context;
        this.list = list;
    }

    class ViewHolder {
        TextView t1, t2, t3,t4;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item, parent, false);
            holder = new ViewHolder();
            holder.t1 = convertView.findViewById(R.id.tvDate);
            holder.t2 = convertView
                    .findViewById(R.id.tvDes);
            holder.t3 =  convertView.findViewById(R.id.tvDebit);
            holder.t4 =  convertView.findViewById(R.id.tvCredit);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
            holder.t1.setText(list.get(position).getDate());
            holder.t2.setText(list.get(position).getDescription());
            holder.t3.setText(list.get(position).getPaid() + "");
            holder.t4.setText(list.get(position).getDue() + "");
        return convertView;
    }
}
