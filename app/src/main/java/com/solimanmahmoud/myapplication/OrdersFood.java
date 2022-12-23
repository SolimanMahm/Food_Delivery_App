package com.solimanmahmoud.myapplication;

public class OrdersFood {
    private int _id, qty;
    private String name;
    private double price;

    public OrdersFood() {
    }

    public OrdersFood(int _id, String name, int qty, double price) {
        this._id = _id;
        this.qty = qty;
        this.name = name;
        this.price = price;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
