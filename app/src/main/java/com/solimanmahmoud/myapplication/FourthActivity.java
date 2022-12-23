package com.solimanmahmoud.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class FourthActivity extends AppCompatActivity {

    ListView lv_showFood;
    public static int num[];
    public static double time_cart=0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        lv_showFood = findViewById(R.id.lv_showFood);

        Bundle bundle = getIntent().getExtras();
        String name[] = bundle.getStringArray("fawaz0");
        double[] pound = bundle.getDoubleArray("fawaz1");
        String img[] = bundle.getStringArray("fawaz2");
        double time[] = bundle.getDoubleArray("fawaz3");

        num = new int[name.length];

        CustomeBaseAdapter customeBaseAdapter = new CustomeBaseAdapter(getApplicationContext(), name, pound, img, time);
        lv_showFood.setAdapter(customeBaseAdapter);

        findViewById(R.id.addtocart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> name_cart = new ArrayList<>();
                for(int i = 0;i<num.length;i++){
                    if(num[i]!=0)name_cart.add(name[i]);
                }
                int qunt[] = new int[name_cart.size()];
                double price[] = new double[name_cart.size()];
                int j = 0;
                for(int i = 0;i<num.length;i++){
                    if(num[i]!=0) {
                        qunt[j] = num[i];
                        price[j] = pound[i]*num[i];
                        j++;
                        time_cart=Math.max(time_cart,time[i]);
                    }
                }
                Intent intent = new Intent(FourthActivity.this,Cart.class);
                intent.putExtra("name",name_cart);
                intent.putExtra("quntaty",qunt);
                intent.putExtra("price",price);
                startActivity(intent);
                finish();
            }
        });

    }
}