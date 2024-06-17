package com.example.project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "user.db";

    public static final String TABLE_USERS = "users";
    public static final String KEY_ID = "id";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_TABLE_USERS =
                "CREATE TABLE " + TABLE_USERS + "(" +
                KEY_ID       + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                KEY_USERNAME + " TEXT NOT NULL," +
                KEY_EMAIL    + " TEXT NOT NULL UNIQUE," +
                KEY_PASSWORD + " TEXT NOT NULL)";

        db.execSQL(CREATE_TABLE_USERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }


    public long signUp(String username, String email, String password) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, username);
        values.put(KEY_EMAIL, email);
        values.put(KEY_PASSWORD, password);
        long result = db.insert(TABLE_USERS, null, values);
        db.close();
        return result;
    }


    public boolean login(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = KEY_EMAIL + " = ? AND " + KEY_PASSWORD + " = ?";
        String[] selectionArgs = {email, password};
        Cursor cursor = db.query(TABLE_USERS, null, query, selectionArgs, null, null, null);
        boolean success = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return success;
    }
}