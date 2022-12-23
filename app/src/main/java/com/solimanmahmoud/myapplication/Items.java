package com.solimanmahmoud.myapplication;

public class Items {
    private int id;
    private String E_name, A_name, photo;
    private double latitude, longitude;

    public Items(int id, String E_name, String A_name, double latitude, double longitude, String photo) {
        this.id = id;
        this.E_name = E_name;
        this.A_name = A_name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getE_Name() {
        return E_name;
    }

    public String getA_Name() {
        return A_name;
    }

    public void setE_name(String e_name) {
        E_name = e_name;
    }

    public void setA_name(String a_name) {
        A_name = a_name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}

