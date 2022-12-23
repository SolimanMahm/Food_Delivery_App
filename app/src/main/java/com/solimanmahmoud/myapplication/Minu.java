package com.solimanmahmoud.myapplication;

public class Minu {
    private int id;
    private double pound, time;
    private String E_name,A_name,img;

    public Minu(int id, double pound, double time, String E_name, String A_name, String img) {
        this.id = id;
        this.pound = pound;
        this.time = time;
        this.E_name = E_name;
        this.A_name = A_name;
        this.img=img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPound() {
        return pound;
    }

    public void setPound(double pound) {
        this.pound = pound;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public String getE_name() {
        return E_name;
    }

    public void setE_name(String name) {
        this.E_name = E_name;
    }

    public String getA_name() {
        return A_name;
    }

    public void setA_name(String a_name) {
        A_name = a_name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}

