package com.solimanmahmoud.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CustomeBaseAdapter_cart extends BaseAdapter {

    Context context;
    View convertView;
    LayoutInflater inflater;
    String name[];
    int quantity[];
    double price[];

    public CustomeBaseAdapter_cart(Context context, String[] name, int[] quantity, double[] price) {
        this.context = context;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return name.length;
    }

    @Override
    public Object getItem(int i) {
        return name.length;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        convertView = inflater.inflate(R.layout.activity_list_cart, null);
        TextView meal_name = convertView.findViewById(R.id.txt_cart);
        TextView quant = convertView.findViewById(R.id.quantity);
        TextView pri = convertView.findViewById(R.id.price);
        meal_name.setText(name[i]);
        quant.setText(quantity[i] + "");
        pri.setText(price[i] + "");
        return convertView;
    }
}
