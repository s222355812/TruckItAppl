package com.example.truckitappl;
import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "trucksharing.db";
    private static final int DATABASE_VERSION = 2;
    public static final String TABLE_ORDERS = "orders";
    public static final String COLUMN_ORDER_TITLE = "order_title";
    public static final String COLUMN_ORDER_DETAILS = "order_details";
    public static final String COLUMN_ORDER_IMAGE = "order_image";
    public static final String TABLE_USERS = "users";
    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_USER_NAME = "user_name";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_PROFILE_IMAGE = "profile_image";

    public static final String CREATE_TABLE_USERS = "CREATE TABLE " + TABLE_USERS + " ("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_USER_NAME + " TEXT, "
            + COLUMN_EMAIL + " TEXT, "
            + COLUMN_PASSWORD + " TEXT, "
            + COLUMN_PROFILE_IMAGE + " BLOB);";
    public static final String CREATE_TABLE_ORDERS = "CREATE TABLE " + TABLE_ORDERS + " ("
            + COLUMN_ORDER_TITLE + " TEXT, "
            + COLUMN_ORDER_DETAILS + " TEXT, "
            + COLUMN_ORDER_IMAGE + " INTEGER, "
            + COLUMN_USER_ID + " INTEGER);";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
        db.execSQL(CREATE_TABLE_ORDERS);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }
    public List<Truck> getMyOrders(int userId) {
        List<Truck> orders = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = {COLUMN_ORDER_TITLE, COLUMN_ORDER_DETAILS, COLUMN_ORDER_IMAGE};
        String selection = COLUMN_USER_ID + " = ?";
        String[] selectionArgs = {String.valueOf(userId)};

        Cursor cursor = db.query(TABLE_ORDERS, columns, selection, selectionArgs, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String orderTitle = cursor.getString(cursor.getColumnIndex(COLUMN_ORDER_TITLE));
                @SuppressLint("Range") String orderDetails = cursor.getString(cursor.getColumnIndex(COLUMN_ORDER_DETAILS));
                @SuppressLint("Range") int orderImage = cursor.getInt(cursor.getColumnIndex(COLUMN_ORDER_IMAGE));

                orders.add(new Truck(orderTitle, orderDetails, orderImage));
            } while (cursor.moveToNext());

            cursor.close();
        }

        db.close();

        return orders;
    }
}
