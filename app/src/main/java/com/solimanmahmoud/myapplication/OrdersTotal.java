package com.solimanmahmoud.myapplication;

public class OrdersTotal {

    private int _id;
    private double subtotal, delivery, time, total;


    public OrdersTotal() {
    }

    public OrdersTotal(int _id, double subtotal, double delivery, double time, double total) {
        this._id = _id;
        this.subtotal = subtotal;
        this.delivery = delivery;
        this.time = time;
        this.total = total;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getDelivery() {
        return delivery;
    }

    public void setDelivery(double delivery) {
        this.delivery = delivery;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

}

