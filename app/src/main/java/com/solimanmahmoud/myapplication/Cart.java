package com.solimanmahmoud.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Cart extends AppCompatActivity {

    ListView lv_cart;
    TextView check, subt, delivery_txt, time_txt, total_txt;
    ConstraintLayout layout;
    ArrayList<OrdersFood> foods = new ArrayList<>();
    ArrayList<OrdersTotal> totals = new ArrayList<>();
    DatabaseController dpController;
    String name[];
    int num[];
    double price[];
    double subtotal = 0, delivery = 0, time = 0, total = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        subtotal = 0;
        delivery = 0;
        time = 0;
        total = 0;

        dpController = new DatabaseController(getApplicationContext());

        lv_cart = findViewById(R.id.list_cart);

        dpController.open();

        Bundle bundle = getIntent().getExtras();

        layout = findViewById(R.id.checkout);
        check = findViewById(R.id.textView7);
        subt = findViewById(R.id.subtotal);
        delivery_txt = findViewById(R.id.delivery);
        time_txt = findViewById(R.id.time);
        total_txt = findViewById(R.id.total);

        if (bundle != null) {
            ArrayList<String> arrayList = bundle.getStringArrayList("name");
            num = bundle.getIntArray("quntaty");
            price = bundle.getDoubleArray("price");
            name = new String[arrayList.size()];
            for (int i = 0; i < arrayList.size(); i++) name[i] = arrayList.get(i);

            CustomeBaseAdapter_cart customeBaseAdapter_cart = new CustomeBaseAdapter_cart(getApplicationContext(), name, num, price);
            lv_cart.setAdapter(customeBaseAdapter_cart);
            for (int i = 0; i < price.length; i++) {
                subtotal += price[i];
            }
            delivery = ThirdAvtivity.dis[ThirdAvtivity.index] * 5.0;
            time = FourthActivity.time_cart + ThirdAvtivity.dis[ThirdAvtivity.index];
            total = subtotal + delivery;
            subt.setText(subtotal + "");
            delivery_txt.setText(delivery + "");
            time_txt.setText(time + "");
            total_txt.setText(total + "");
        } else {
            getOrders();
            getTotals();
            if (totals != null && foods != null) {
                check.setText(R.string.checked);
                name = new String[foods.size()];
                num = new int[foods.size()];
                price = new double[foods.size()];
                for (int i = 0; i < foods.size(); i++) {
                    name[i] = foods.get(i).getName();
                    num[i] = foods.get(i).getQty();
                    price[i] = foods.get(i).getPrice();
                }
                subt.setText(totals.get(0).getSubtotal() + "");
                delivery_txt.setText(totals.get(0).getDelivery() + "");
                time_txt.setText(totals.get(0).getTime() + "");
                total_txt.setText(totals.get(0).getTotal() + "");
                CustomeBaseAdapter_cart customeBaseAdapter_cart = new CustomeBaseAdapter_cart(getApplicationContext(), name, num, price);
                lv_cart.setAdapter(customeBaseAdapter_cart);
            }
        }

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bundle != null) {
                    dpController.deleteAll_Order();
                    dpController.deleteAll_Total();
                    dpController.close();
                    dpController.open();
                    check.setText(R.string.checked);
                    for (int i = 0; i < name.length; i++) {
                        dpController.insertOrder(name[i], num[i], price[i]);
                        getOrders();
                    }
                    dpController.insertTotal(subtotal, delivery, time, total);
                    getTotals();
                }
            }
        });
    }

    private void getTotals() {
        totals.clear();
        for (OrdersTotal ordersTotal : dpController.selectTotals()) {
            totals.add(ordersTotal);
        }
    }

    private void getOrders() {
        foods.clear();
        for (OrdersFood ordersFood : dpController.selectOrders()) {
            foods.add(ordersFood);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dpController.close();
    }
}