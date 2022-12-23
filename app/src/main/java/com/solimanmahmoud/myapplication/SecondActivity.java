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
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Locale;

public class SecondActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Items>> {

    private LinearLayout linear1, linear2, linear3, linear4, homebtn, profilebtn;
    private FloatingActionButton cart;
    String state, currentLang;
    Intent intent;
    ProgressBar progressBar;
    private ArrayList<String> arrayList = new ArrayList<>();
    private static String STUDENTS_URL = "https://jsonware.com/api/v1/json/ab90e527-58de-4e03-a5fa-5673bb06c01d?dynamic=true";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Resources.getSystem().getConfiguration().locale.getLanguage();
        currentLang = Locale.getDefault().getLanguage();

        linear1 = findViewById(R.id.linear1);
        linear2 = findViewById(R.id.linear2);
        linear3 = findViewById(R.id.linear3);
        linear4 = findViewById(R.id.linear4);
        homebtn = findViewById(R.id.homeBtn);
        profilebtn = findViewById(R.id.profileBtn);
        cart = findViewById(R.id.cart);
        progressBar = findViewById(R.id.bp_progress);

    }

    public void Select(View view) {
        if (view == linear1) {
            state = "bbq";
            LoaderManager loaderManager = getSupportLoaderManager();
            loaderManager.initLoader(0, null, SecondActivity.this).forceLoad();
        } else if (view == linear2) {
            state = "girllfish";
            LoaderManager loaderManager = getSupportLoaderManager();
            loaderManager.initLoader(1, null, SecondActivity.this).forceLoad();
        } else if (view == linear3) {
            state = "friedchicken";
            LoaderManager loaderManager = getSupportLoaderManager();
            loaderManager.initLoader(2, null, SecondActivity.this).forceLoad();
        } else if (view == linear4) {
            state = "syrianfood";
            LoaderManager loaderManager = getSupportLoaderManager();
            loaderManager.initLoader(3, null, SecondActivity.this).forceLoad();
        } else if (view == homebtn) {
            startActivity(new Intent(this, SecondActivity.class));
        } else if (view == profilebtn) {
            startActivity(new Intent(this,Profile.class));
        } else if (view == cart) {
            startActivity(new Intent(this,Cart.class));
        }
    }

    @NonNull
    @Override
    public Loader<ArrayList<Items>> onCreateLoader(int id, @Nullable Bundle args) {
        progressBar.setVisibility(View.VISIBLE);
        return new ResturantLoader(SecondActivity.this, STUDENTS_URL, state);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<Items>> loader, ArrayList<Items> data) {
        progressBar.setVisibility(View.GONE);
        if (data.size() == 0)
            Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
        arrayList.clear();
        double lat[] = new double[data.size()];
        double lng[] = new double[data.size()];
        String photo[] = new String[data.size()];
        if (currentLang.equals("ar")) {
            for (int i = 0; i < data.size(); i++) {
                arrayList.add(data.get(i).getA_Name());
                lat[i] = data.get(i).getLatitude();
                lng[i] = data.get(i).getLongitude();
                photo[i] = data.get(i).getPhoto();
            }
        } else if (currentLang.equals("en")) {
            for (int i = 0; i < data.size(); i++) {
                arrayList.add(data.get(i).getE_Name());
                lat[i] = data.get(i).getLatitude();
                lng[i] = data.get(i).getLongitude();
                photo[i] = data.get(i).getPhoto();
            }
        }
        intent = new Intent(SecondActivity.this, ThirdAvtivity.class);
        intent.putExtra("syrianfood0", arrayList);
        intent.putExtra("syrianfood1", lat);
        intent.putExtra("syrianfood2", lng);
        intent.putExtra("syrianfood3", photo);
        intent.putExtra("syrianfood4", state);
        startActivity(intent);
        state = "";
    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<Items>> loader) {

    }

}