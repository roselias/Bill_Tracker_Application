package com.example.billtracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.view.View;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

public class ListViewAdapter extends ArrayAdapter<Bills> {

    public ListViewAdapter(Context context, ArrayList<Bills> bills) {
        super(context, 0, bills);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Bills bill = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.single_bill, parent, false);
        }

        TextView item_date = (TextView) convertView.findViewById(R.id.item_date);
        TextView item_name = (TextView) convertView.findViewById(R.id.item_name);

        item_date.setText(bill.getDueDate());
        item_name.setText(bill.getBillName());

        return convertView;

    }
}
