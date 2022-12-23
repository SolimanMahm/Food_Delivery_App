package com.solimanmahmoud.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private final static String DATABASE_NAME = "CART";
    public final static int DATABASE_VERSION = 6;

    public final static String TABLE01_NAME = "Food";
    public final static String TABLE01_COL01 = "_id";
    public final static String TABLE01_COL02 = "name";
    public final static String TABLE01_COL03 = "qty";
    public final static String TABLE01_COL04 = "price";

    public final static String TABLE02_NAME = "Total";
    public final static String TABLE02_COL01 = "_id";
    public final static String TABLE02_COL02 = "subtotal";
    public final static String TABLE02_COL03 = "delivery";
    public final static String TABLE02_COL04 = "time";
    public final static String TABLE02_COL05 = "total1";

    private static final String CREATE_TABLE01_STATEMENT = "CREATE TABLE " + TABLE01_NAME + "(" + TABLE01_COL01 + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            "" + TABLE01_COL02 + " TEXT NOT NULL, " + TABLE01_COL03 + " INTEGER NOT NULL, " + TABLE01_COL04 + " REAL NOT NULL);";

    private static final String CREATE_TABLE02_STATEMENT = "CREATE TABLE " + TABLE02_NAME + "(" + TABLE02_COL01 + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            "" + TABLE02_COL02 + " REAL NOT NULL, " + TABLE02_COL03 + " REAL NOT NULL, " + TABLE02_COL04 + " REAL NOT NULL, " + TABLE02_COL05 + " REAL NOT NULL);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE01_STATEMENT);
        sqLiteDatabase.execSQL(CREATE_TABLE02_STATEMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE01_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE02_NAME);
        onCreate(sqLiteDatabase);
    }
}
