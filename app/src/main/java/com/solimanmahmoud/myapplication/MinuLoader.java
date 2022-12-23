package com.solimanmahmoud.myapplication;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class MinuLoader extends AsyncTaskLoader<ArrayList<Minu>> {

    String url = null;
    ArrayList<Minu> Fawaz = new ArrayList<>();
    ArrayList<Minu> Hawa_Al_Sham = new ArrayList<>();
    ArrayList<Minu> Al_Sultan = new ArrayList<>();
    ArrayList<Minu> Abo_Elkheir = new ArrayList<>();
    ArrayList<Minu> El_Tayb = new ArrayList<>();
    ArrayList<Minu> Abo_Samra = new ArrayList<>();
    ArrayList<Minu> El_Hoot = new ArrayList<>();
    ArrayList<Minu> Mias = new ArrayList<>();
    ArrayList<Minu> El_Younany = new ArrayList<>();
    ArrayList<Minu> Zack = new ArrayList<>();
    ArrayList<Minu> Jamie = new ArrayList<>();
    ArrayList<Minu> Gunnerz = new ArrayList<>();
    String state;

    public MinuLoader(@NonNull Context context, String url, String state) {
        super(context);
        this.url = url;
        this.state = state;
    }

    @Nullable
    @Override
    public ArrayList<Minu> loadInBackground() {
        JSONObject jsonRoot = null;
        Fawaz.clear();
        Hawa_Al_Sham.clear();
        Al_Sultan.clear();
        Abo_Elkheir.clear();
        El_Tayb.clear();
        Abo_Samra.clear();
        El_Hoot.clear();
        Mias.clear();
        El_Younany.clear();
        Zack.clear();
        Jamie.clear();
        Gunnerz.clear();
        try {
            jsonRoot = new JSONObject(getHttpRequest(new URL(url)));
            JSONArray jsonArray = jsonRoot.getJSONArray("food_categories");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject foodCategories = jsonArray.getJSONObject(i);
                JSONArray SyrianFood = foodCategories.getJSONArray("Syrian_Food");
                JSONArray BBq = foodCategories.getJSONArray("BBQ");
                JSONArray Girll_Fish = foodCategories.getJSONArray("Girll_Fish");
                JSONArray Fried_Chicken = foodCategories.getJSONArray("Fried_Chicken");
                JSONArray Fawz[] = new JSONArray[SyrianFood.length()];
                JSONArray bbq[] = new JSONArray[BBq.length()];
                JSONArray GirllFish[] = new JSONArray[Girll_Fish.length()];
                JSONArray FriedChicken[] = new JSONArray[Fried_Chicken.length()];
                for (int j = 0; j < SyrianFood.length(); j++) {
                    JSONObject resturnt = SyrianFood.getJSONObject(j);
                    Fawz[j] = resturnt.getJSONArray("Minu");
                    if (j == 0) {
                        for (int x = 0; x < Fawz[j].length(); x++) {
                            JSONObject fawz = Fawz[j].getJSONObject(x);
                            int id = fawz.getInt("id");
                            String E_name = fawz.getString("E-Name");
                            String A_name = fawz.getString("A-Name");
                            double pound = fawz.getDouble("Pound");
                            double time = fawz.getDouble("Time");
                            String img = fawz.getString("Photo");
                            Minu minu = new Minu(id, pound, time, E_name, A_name, img);
                            Fawaz.add(minu);
                        }
                    } else if (j == 1) {
                        for (int x = 0; x < Fawz[j].length(); x++) {
                            JSONObject fawz = Fawz[j].getJSONObject(x);
                            int id = fawz.getInt("id");
                            String E_name = fawz.getString("E-Name");
                            String A_name = fawz.getString("A-Name");
                            double pound = fawz.getDouble("Pound");
                            double time = fawz.getDouble("Time");
                            String img = fawz.getString("Photo");
                            Minu minu = new Minu(id, pound, time, E_name, A_name, img);
                            Hawa_Al_Sham.add(minu);
                        }
                    } else if (j == 2) {
                        for (int x = 0; x < Fawz[j].length(); x++) {
                            JSONObject fawz = Fawz[j].getJSONObject(x);
                            int id = fawz.getInt("id");
                            String E_name = fawz.getString("E-Name");
                            String A_name = fawz.getString("A-Name");
                            double pound = fawz.getDouble("Pound");
                            double time = fawz.getDouble("Time");
                            String img = fawz.getString("Photo");
                            Minu minu = new Minu(id, pound, time, E_name, A_name, img);
                            Al_Sultan.add(minu);
                        }
                    }
                }
                for (int j = 0; j < BBq.length(); j++) {
                    JSONObject resturnt = BBq.getJSONObject(j);
                    bbq[j] = resturnt.getJSONArray("Minu");
                    if (j == 0) {
                        for (int x = 0; x < bbq[j].length(); x++) {
                            JSONObject Bbq = bbq[j].getJSONObject(x);
                            int id = Bbq.getInt("id");
                            String E_name = Bbq.getString("E-Name");
                            String A_name = Bbq.getString("A-Name");
                            double pound = Bbq.getDouble("Pound");
                            double time = Bbq.getDouble("Time");
                            String img = Bbq.getString("Photo");
                            Minu minu = new Minu(id, pound, time, E_name, A_name, img);
                            Abo_Elkheir.add(minu);
                        }
                    } else if (j == 1) {
                        for (int x = 0; x < bbq[j].length(); x++) {
                            JSONObject Bbq = bbq[j].getJSONObject(x);
                            int id = Bbq.getInt("id");
                            String E_name = Bbq.getString("E-Name");
                            String A_name = Bbq.getString("A-Name");
                            double pound = Bbq.getDouble("Pound");
                            double time = Bbq.getDouble("Time");
                            String img = Bbq.getString("Photo");
                            Minu minu = new Minu(id, pound, time, E_name, A_name, img);
                            El_Tayb.add(minu);
                        }
                    } else if (j == 2) {
                        for (int x = 0; x < bbq[j].length(); x++) {
                            JSONObject Bbq = bbq[j].getJSONObject(x);
                            int id = Bbq.getInt("id");
                            String E_name = Bbq.getString("E-Name");
                            String A_name = Bbq.getString("A-Name");
                            double pound = Bbq.getDouble("Pound");
                            double time = Bbq.getDouble("Time");
                            String img = Bbq.getString("Photo");
                            Minu minu = new Minu(id, pound, time, E_name, A_name, img);
                            Abo_Samra.add(minu);
                        }
                    }
                }
                for (int j = 0; j < Girll_Fish.length(); j++) {
                    JSONObject resturant = Girll_Fish.getJSONObject(j);
                    GirllFish[j] = resturant.getJSONArray("Minu");
                    if (j == 0) {
                        for (int x = 0; x < GirllFish[j].length(); x++) {
                            JSONObject girllfish = GirllFish[j].getJSONObject(x);
                            int id = girllfish.getInt("id");
                            String E_name = girllfish.getString("E-Name");
                            String A_name = girllfish.getString("A-Name");
                            double pound = girllfish.getDouble("Pound");
                            double time = girllfish.getDouble("Time");
                            String img = girllfish.getString("Photo");
                            Minu minu = new Minu(id, pound, time, E_name, A_name, img);
                            El_Hoot.add(minu);
                        }
                    } else if (j == 1) {
                        for (int x = 0; x < GirllFish[j].length(); x++) {
                            JSONObject girllfish = GirllFish[j].getJSONObject(x);
                            int id = girllfish.getInt("id");
                            String E_name = girllfish.getString("E-Name");
                            String A_name = girllfish.getString("A-Name");
                            double pound = girllfish.getDouble("Pound");
                            double time = girllfish.getDouble("Time");
                            String img = girllfish.getString("Photo");
                            Minu minu = new Minu(id, pound, time, E_name, A_name, img);
                            Mias.add(minu);
                        }
                    } else if (j == 2) {
                        for (int x = 0; x < GirllFish[j].length(); x++) {
                            JSONObject girllfish = GirllFish[j].getJSONObject(x);
                            int id = girllfish.getInt("id");
                            String E_name = girllfish.getString("E-Name");
                            String A_name = girllfish.getString("A-Name");
                            double pound = girllfish.getDouble("Pound");
                            double time = girllfish.getDouble("Time");
                            String img = girllfish.getString("Photo");
                            Minu minu = new Minu(id, pound, time, E_name, A_name, img);
                            El_Younany.add(minu);
                        }
                    }
                }
                for (int j = 0; j < Fried_Chicken.length(); j++) {
                    JSONObject resturant = Fried_Chicken.getJSONObject(j);
                    FriedChicken[j] = resturant.getJSONArray("Minu");
                    if (j == 0) {
                        for (int x = 0; x < FriedChicken[j].length(); x++) {
                            JSONObject friedchicken = FriedChicken[j].getJSONObject(x);
                            int id = friedchicken.getInt("id");
                            String E_name = friedchicken.getString("E-Name");
                            String A_name = friedchicken.getString("A-Name");
                            double pound = friedchicken.getDouble("Pound");
                            double time = friedchicken.getDouble("Time");
                            String img = friedchicken.getString("Photo");
                            Minu minu = new Minu(id, pound, time, E_name, A_name, img);
                            Zack.add(minu);
                        }
                    } else if (j == 1) {
                        for (int x = 0; x < FriedChicken[j].length(); x++) {
                            JSONObject friedchicken = FriedChicken[j].getJSONObject(x);
                            int id = friedchicken.getInt("id");
                            String E_name = friedchicken.getString("E-Name");
                            String A_name = friedchicken.getString("A-Name");
                            double pound = friedchicken.getDouble("Pound");
                            double time = friedchicken.getDouble("Time");
                            String img = friedchicken.getString("Photo");
                            Minu minu = new Minu(id, pound, time, E_name, A_name, img);
                            Jamie.add(minu);
                        }
                    } else if (j == 2) {
                        for (int x = 0; x < FriedChicken[j].length(); x++) {
                            JSONObject friedchicken = FriedChicken[j].getJSONObject(x);
                            int id = friedchicken.getInt("id");
                            String E_name = friedchicken.getString("E-Name");
                            String A_name = friedchicken.getString("A-Name");
                            double pound = friedchicken.getDouble("Pound");
                            double time = friedchicken.getDouble("Time");
                            String img = friedchicken.getString("Photo");
                            Minu minu = new Minu(id, pound, time, E_name, A_name, img);
                            Gunnerz.add(minu);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (state.equals("fawaz")) return Fawaz;
        else if (state.equals("Hawa Al Sham")) return Hawa_Al_Sham;
        else if (state.equals("Al Sultan")) return Al_Sultan;
        else if (state.equals("Abo Elkheir")) return Abo_Elkheir;
        else if (state.equals("El Tayb")) return El_Tayb;
        else if (state.equals("Abo Samra")) return Abo_Samra;
        else if (state.equals("El Hoot")) return El_Hoot;
        else if (state.equals("Mias")) return Mias;
        else if (state.equals("El Younany")) return El_Younany;
        else if (state.equals("Zack's")) return Zack;
        else if (state.equals("Jamie's")) return Jamie;
        else if (state.equals("Gunnerz")) return Gunnerz;
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