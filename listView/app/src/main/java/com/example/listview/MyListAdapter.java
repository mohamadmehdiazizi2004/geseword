package com.example.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class MyListAdapter extends ArrayAdapter<ItemModel> {

    private LayoutInflater inflater;

    public MyListAdapter(Context context, List<ItemModel> items) {
        super(context, 0, items);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_row, parent, false);
            holder = new ViewHolder();
            holder.txtTitle = convertView.findViewById(R.id.txtTitle);
            holder.txtDescription = convertView.findViewById(R.id.txtDescription);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ItemModel item = getItem(position);
        if (item != null) {
            holder.txtTitle.setText(item.getTitle());
            holder.txtDescription.setText(item.getDescription());
        }

        return convertView;
    }

    static class ViewHolder {
        TextView txtTitle;
        TextView txtDescription;
    }
}
