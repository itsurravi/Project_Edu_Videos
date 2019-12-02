package com.codrox.myapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB_Handler extends SQLiteOpenHelper {


    public static final String DB_NAME = "UserDB";
    public static final String TABLE_NAME = "Users";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";


    public DB_Handler(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME
                + "(" + ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME + " VARCHAR, "
                + EMAIL + " VARCHAR, "
                + PASSWORD + " VARCHAR);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean registerUser(String name, String uname, String pswd) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(NAME, name);
        cv.put(EMAIL, uname);
        cv.put(PASSWORD, pswd);

        db.insert(TABLE_NAME, null, cv);
        db.close();

        return true;
    }

    public boolean updateUserInfo(String id, String name, String uname, String pswd) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(NAME, name);
        cv.put(EMAIL, uname);
        cv.put(PASSWORD, pswd);

        int res = db.update(TABLE_NAME, cv, "id=?", new String[]{id});
        if (res > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Cursor showUser(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM "+TABLE_NAME+" WHERE " + EMAIL + "='" + email + "' AND " + PASSWORD + "='" + password + "';";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }
}
