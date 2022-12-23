package com.solimanmahmoud.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class CustomeBaseAdapter extends BaseAdapter {

    Context context;
    String food_name[], food_img[];
    double pound[], time[];
    LayoutInflater inflater;
    View convertView;

    public CustomeBaseAdapter(Context context, String[] food_name, double[] pound, String[] food_img, double[] time) {
        this.context = context;
        this.food_name = food_name;
        this.pound = pound;
        this.food_img = food_img;
        this.time = time;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return food_name.length;
    }

    @Override
    public Object getItem(int i) {
        return food_name[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        int cnt[] = new int[food_name.length];
        for (int x = 0; x < cnt.length; x++) {
            cnt[x] = 0;
        }
        convertView = inflater.inflate(R.layout.activity_custom_list_view, null);
        TextView name = (TextView) convertView.findViewById(R.id.textview_minu);
        ImageView photo = (ImageView) convertView.findViewById(R.id.imageicon);
        Button add = (Button) convertView.findViewById(R.id.add);
        Button sub = (Button) convertView.findViewById(R.id.sub);
        TextView Pound = (TextView) convertView.findViewById(R.id.pound);
        TextView counter = (TextView) convertView.findViewById(R.id.counter);
        name.setText(food_name[i]);
        Pound.setText(pound[i] + "");
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cnt[i]++;
                counter.setText(cnt[i] + "");
                FourthActivity.num[i] = cnt[i];
            }
        });
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cnt[i] - 1 >= 0) {
                    cnt[i]--;
                    counter.setText(cnt[i] + "");
                    FourthActivity.num[i] = cnt[i];
                }
            }
        });
        Picasso.get().load(food_img[i]).placeholder(R.drawable.ic_baseline_autorenew_24).resize(240, 270).into(photo);
        return convertView;
    }

}
