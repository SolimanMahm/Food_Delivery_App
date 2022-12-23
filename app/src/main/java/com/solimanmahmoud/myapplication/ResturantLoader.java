package com.solimanmahmoud.myapplication;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class ResturantLoader extends AsyncTaskLoader<ArrayList<Items>> {

    String url = null;
    ArrayList<Items> Syrian_Food = new ArrayList<>();
    ArrayList<Items> BBQ = new ArrayList<>();
    ArrayList<Items> Girll_Fish = new ArrayList<>();
    ArrayList<Items> Fried_Chicken = new ArrayList<>();
    String state;

    public ResturantLoader(@NonNull Context context, String url, String state) {
        super(context);
        this.url = url;
        this.state = state;
    }

    @Nullable
    @Override
    public ArrayList<Items> loadInBackground() {
        JSONObject jsonRoot = null;
        Syrian_Food.clear();
        BBQ.clear();
        Girll_Fish.clear();
        Fried_Chicken.clear();
        try {
            jsonRoot = new JSONObject(getHttpRequest(new URL(url)));
            JSONArray jsonArray = jsonRoot.getJSONArray("food_categories");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject foodCategories = jsonArray.getJSONObject(i);
                JSONArray SyrianFood = foodCategories.getJSONArray("Syrian_Food");
                JSONArray BBq = foodCategories.getJSONArray("BBQ");
                JSONArray GirllFish = foodCategories.getJSONArray("Girll_Fish");
                JSONArray FriedChicken = foodCategories.getJSONArray("Fried_Chicken");
                for (int j = 0; j < SyrianFood.length(); j++) {
                    JSONObject resturnt = SyrianFood.getJSONObject(j);
                    Items syrianfood = new Items(resturnt.getInt("id"), resturnt.getString("E-Name"), resturnt.getString("A-Name"), resturnt.getDouble("latitude"), resturnt.getDouble("longitude"), resturnt.getString("Photo"));
                    Syrian_Food.add(syrianfood);
                }
                for (int j = 0; j < BBq.length(); j++) {
                    JSONObject resturnt1 = BBq.getJSONObject(j);
                    Items bbq = new Items(resturnt1.getInt("id"), resturnt1.getString("E-Name"), resturnt1.getString("A-Name"), resturnt1.getDouble("latitude"), resturnt1.getDouble("longitude"), resturnt1.getString("Photo"));
                    BBQ.add(bbq);
                }
                for (int j = 0; j < GirllFish.length(); j++) {
                    JSONObject resturnt = GirllFish.getJSONObject(j);
                    Items girllfish = new Items(resturnt.getInt("id"), resturnt.getString("E-Name"), resturnt.getString("A-Name"), resturnt.getDouble("latitude"), resturnt.getDouble("longitude"), resturnt.getString("Photo"));
                    Girll_Fish.add(girllfish);
                }
                for (int j = 0; j < FriedChicken.length(); j++) {
                    JSONObject resturnt = FriedChicken.getJSONObject(j);
                    Items friedchicken = new Items(resturnt.getInt("id"), resturnt.getString("E-Name"), resturnt.getString("A-Name"), resturnt.getDouble("latitude"), resturnt.getDouble("longitude"), resturnt.getString("Photo"));
                    Fried_Chicken.add(friedchicken);
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (state.equals("syrianfood")) return Syrian_Food;
        else if (state.equals("bbq")) return BBQ;
        else if (state.equals("girllfish")) return Girll_Fish;
        else if (state.equals("friedchicken")) return Fried_Chicken;
        else return null;
    }

    private String getHttpRequest(URL url) throws IOException {
        String jsonResponse = "";
        HttpsURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(20000);
            urlConnection.connect();
            inputStream = urlConnection.getInputStream();
            jsonResponse = readFromStream(inputStream);
        } catch (Exception e) {
            return "No Internet Connection";
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder result = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                result.append(line);
                line = reader.readLine();
            }
        }
        return result.toString();
    }
}
