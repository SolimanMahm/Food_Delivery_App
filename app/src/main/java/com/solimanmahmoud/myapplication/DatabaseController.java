package com.solimanmahmoud.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DatabaseController {

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    public DatabaseController(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public void open() {
        database = databaseHelper.getWritableDatabase();
    }

    // Orders Food

    public long insertOrder(String name, int qty, double price) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.TABLE01_COL02, name);
        values.put(DatabaseHelper.TABLE01_COL03, qty);
        values.put(DatabaseHelper.TABLE01_COL04, price);
        return database.insert(DatabaseHelper.TABLE01_NAME, null, values);
    }

    public int deleteOrder(int id) {
        return database.delete(DatabaseHelper.TABLE01_NAME, DatabaseHelper.TABLE01_COL01 + "=" + id, null);
    }

    public void deleteAll_Order(){
        database.delete(DatabaseHelper.TABLE01_NAME, null, null);
    }

    public ArrayList<OrdersFood> selectOrders() {
        ArrayList<OrdersFood> ordersFoods = new ArrayList<>();
        Cursor cursor = database.query(DatabaseHelper.TABLE01_NAME, new String[]{DatabaseHelper.TABLE01_COL01, DatabaseHelper.TABLE01_COL02, DatabaseHelper.TABLE01_COL03, DatabaseHelper.TABLE01_COL04}, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            ordersFoods.add(cursorToOrder(cursor));
            cursor.moveToNext();
        }
        return ordersFoods;
    }

    private OrdersFood cursorToOrder(Cursor cursor) {
        return new OrdersFood(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getDouble(3));
    }

    // Orders Total

    public long insertTotal(double subtotal, double delivery, double time, double total) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.TABLE02_COL02, subtotal);
        values.put(DatabaseHelper.TABLE02_COL03, delivery);
        values.put(DatabaseHelper.TABLE02_COL04, time);
        values.put(DatabaseHelper.TABLE02_COL05, total);
        return database.insert(DatabaseHelper.TABLE02_NAME, null, values);
    }

    public void deleteAll_Total(){
        database.delete(DatabaseHelper.TABLE02_NAME, null, null);
    }

    public ArrayList<OrdersTotal> selectTotals() {

        ArrayList<OrdersTotal> ordersTotals = new ArrayList<>();

        Cursor cursor = database.query(DatabaseHelper.TABLE02_NAME, new String[]{DatabaseHelper.TABLE02_COL01, DatabaseHelper.TABLE02_COL02, DatabaseHelper.TABLE02_COL03, DatabaseHelper.TABLE02_COL04, DatabaseHelper.TABLE02_COL05}, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            ordersTotals.add(cursorToTotal(cursor));
            cursor.moveToNext();
        }
        return ordersTotals;
    }

    private OrdersTotal cursorToTotal(Cursor cursor) {
        return new OrdersTotal(cursor.getInt(0), cursor.getDouble(1), cursor.getDouble(2), cursor.getDouble(3), cursor.getDouble(4));
    }

    public void close() {
        if (databaseHelper != null) {
            databaseHelper.close();
        }
    }

}