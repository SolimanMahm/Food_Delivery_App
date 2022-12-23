package com.solimanmahmoud.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Locale;

public class ThirdAvtivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Minu>> {

    private LinearLayout fawaz, linear2, linear3, homebtn, profilebtn;
    private FloatingActionButton cart;
    private TextView res1, res2, res3, text1, text2, text3, name1, name2;
    public static double dis[] = new double[3];
    public static int index;
    private ImageView img1, img2, img3;
    String state, currentLang, state_category;
    Intent intent;
    ProgressBar progressBar;
    private ArrayList<String> arrayList = new ArrayList<>();
    private static String STUDENTS_URL = "https://jsonware.com/api/v1/json/ab90e527-58de-4e03-a5fa-5673bb06c01d?dynamic=true";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_avtivity);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Resources.getSystem().getConfiguration().locale.getLanguage();
        currentLang = Locale.getDefault().getLanguage();

        fawaz = findViewById(R.id.fawaz);
        linear2 = findViewById(R.id.linear2);
        linear3 = findViewById(R.id.linear5);
        homebtn = findViewById(R.id.homeBtn);
        profilebtn = findViewById(R.id.profileBtn);
        cart = findViewById(R.id.cart);
        progressBar = findViewById(R.id.bp_progress1);
        res1 = findViewById(R.id.res1);
        res2 = findViewById(R.id.res2);
        res3 = findViewById(R.id.res3);
        text1 = findViewById(R.id.txt1);
        text2 = findViewById(R.id.txt2);
        text3 = findViewById(R.id.txt3);
        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        img3 = findViewById(R.id.img3);
        name1 = findViewById(R.id.txt_name1);
        name2 = findViewById(R.id.txt_name2);

        Bundle bundle = getIntent().getExtras();
        ArrayList<String> arrayList = bundle.getStringArrayList("syrianfood0");
        double[] lat = bundle.getDoubleArray("syrianfood1");
        double[] lng = bundle.getDoubleArray("syrianfood2");
        String[] photo = bundle.getStringArray("syrianfood3");
        state_category = bundle.getString("syrianfood4");

        if (state_category.equals("bbq")) {
            name1.setText(R.string.BBQ);
            name2.setText(R.string.resturants);
        } else if (state_category.equals("girllfish")) {
            name1.setText(R.string.grill_Fish);
            name2.setText(R.string.resturants);
        } else if (state_category.equals("friedchicken")) {
            name1.setText(R.string.fried_chicken);
            name2.setText(R.string.resturants);
        } else if (state_category.equals("syrianfood")) {
            name1.setText(R.string.syrian);
            name2.setText(R.string.resturants);
        }

        res1.setText(arrayList.get(0));
        res2.setText(arrayList.get(1));
        res3.setText(arrayList.get(2));

        Picasso.get().load(photo[0]).resize(250, 270).placeholder(R.drawable.ic_baseline_autorenew_24).into(img1);
        Picasso.get().load(photo[1]).resize(250, 270).placeholder(R.drawable.ic_baseline_autorenew_24).into(img2);
        Picasso.get().load(photo[2]).resize(250, 270).placeholder(R.drawable.ic_baseline_autorenew_24).into(img3);

        dis[0] = distance(MainActivity.latiude, MainActivity.longitude, lat[0], lng[0]);
        dis[1] = distance(MainActivity.latiude, MainActivity.longitude, lat[1], lng[1]);
        dis[2] = distance(MainActivity.latiude, MainActivity.longitude, lat[2], lng[2]);

        text1.setText(dis[0] + " " + text1.getText());
        text2.setText(dis[1] + " " + text2.getText());
        text3.setText(dis[2] + " " + text3.getText());

    }

    public void Select(View view) {
        if (view == fawaz) {
            index = 0;
            if (state_category.equals("syrianfood")) state = "fawaz";
            else if (state_category.equals("bbq")) state = "Abo Elkheir";
            else if(state_category.equals("girllfish")) state = "El Hoot";
            else if(state_category.equals("friedchicken")) state = "Zack's";
            LoaderManager loaderManager = getSupportLoaderManager();
            loaderManager.initLoader(0, null, ThirdAvtivity.this).forceLoad();
        } else if (view == linear2) {
            index = 1;
            if (state_category.equals("syrianfood")) state = "Hawa Al Sham";
            else if (state_category.equals("bbq")) state = "El Tayb";
            else if(state_category.equals("girllfish")) state = "Mias";
            else if(state_category.equals("friedchicken")) state = "Jamie's";
            LoaderManager loaderManager = getSupportLoaderManager();
            loaderManager.initLoader(1, null, ThirdAvtivity.this).forceLoad();
        } else if (view == linear3) {
            index = 2;
            if (state_category.equals("syrianfood")) state = "Al Sultan";
            else if (state_category.equals("bbq")) state = "Abo Samra";
            else if(state_category.equals("girllfish"))state = "El Younany";
            else if(state_category.equals("friedchicken")) state = "Gunnerz";
            LoaderManager loaderManager = getSupportLoaderManager();
            loaderManager.initLoader(2, null, ThirdAvtivity.this).forceLoad();
        } else if (view == homebtn) {
            startActivity(new Intent(this, SecondActivity.class));
            finish();
        } else if (view == profilebtn) {
            startActivity(new Intent(this,Profile.class));
            finish();
        } else if (view == cart) {
            startActivity(new Intent(this, Cart.class));
            finish();
        }
    }

    private double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        dist = dist * 1.609344;
        return Math.round(dist * 10) / 10.0;
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

    @NonNull
    @Override
    public Loader<ArrayList<Minu>> onCreateLoader(int id, @Nullable Bundle args) {
        progressBar.setVisibility(View.VISIBLE);
        return new MinuLoader(ThirdAvtivity.this, STUDENTS_URL, state);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<Minu>> loader, ArrayList<Minu> data) {
        progressBar.setVisibility(View.GONE);
        if (data.size() == 0)
            Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
        arrayList.clear();
        String name[] = new String[data.size()];
        double pound[] = new double[data.size()];
        String img[] = new String[data.size()];
        double time[] = new double[data.size()];
        if (currentLang.equals("ar")) {
            for (int i = 0; i < data.size(); i++) {
                name[i] = data.get(i).getA_name();
                pound[i] = data.get(i).getPound();
                img[i] = data.get(i).getImg();
                time[i] = data.get(i).getTime();
            }
        } else if (currentLang.equals("en")) {
            for (int i = 0; i < data.size(); i++) {
                name[i] = data.get(i).getE_name();
                pound[i] = data.get(i).getPound();
                img[i] = data.get(i).getImg();
                time[i] = data.get(i).getTime();
            }
        }
        intent = new Intent(ThirdAvtivity.this, FourthActivity.class);
        intent.putExtra("fawaz0", name);
        intent.putExtra("fawaz1", pound);
        intent.putExtra("fawaz2", img);
        intent.putExtra("fawaz3", time);
        startActivity(intent);
        state = "";
    }


    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<Minu>> loader) {

    }
}