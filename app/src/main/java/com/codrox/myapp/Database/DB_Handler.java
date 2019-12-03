package com.codrox.myapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DB_Handler extends SQLiteOpenHelper {


    public static final String DB_NAME = "UserDB";
    public static final String TABLE_USER = "Users";
    public static final String USER_ID = "id";
    public static final String USER_NAME = "name";
    public static final String USER_EMAIL = "email";
    public static final String USER_PASSWORD = "password";

    public static final String TABLE_COMMENTS = "Comments";
    public static final String COMMENT_ID = "id";
    public static final String COMMENT_USER_ID = "user_id";
    public static final String COMMENT = "comment";

    public DB_Handler(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_USER
                + "(" + USER_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + USER_NAME + " VARCHAR, "
                + USER_EMAIL + " VARCHAR, "
                + USER_PASSWORD + " VARCHAR);";
        db.execSQL(sql);

        String sql2 = "CREATE TABLE " + TABLE_COMMENTS
                + "(" + COMMENT_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COMMENT_USER_ID + " VARCHAR, "
                + COMMENT + " VARCHAR);";

        db.execSQL(sql2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    /*-------------------------------USER TABLE---------------------------------------*/
    public boolean registerUser(String name, String uname, String pswd) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(USER_NAME, name);
        cv.put(USER_EMAIL, uname);
        cv.put(USER_PASSWORD, pswd);

        db.insert(TABLE_USER, null, cv);
        db.close();

        return true;
    }

    public boolean updateUserName(String id, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(USER_NAME, name);

        int res = db.update(TABLE_USER, cv, "id=?", new String[]{id});
        db.close();
        if (res > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean updateUserEmail(String id, String uname) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(USER_EMAIL, uname);

        int res = db.update(TABLE_USER, cv, "id=?", new String[]{id});
        db.close();
        if (res > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean updateUserPassword(String id, String pswd) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(USER_PASSWORD, pswd);

        int res = db.update(TABLE_USER, cv, "id=?", new String[]{id});
        db.close();
        if (res > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Cursor signInUserWithEmailAndPassword(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_USER + " WHERE " + USER_EMAIL + "='" + email + "' AND " + USER_PASSWORD + "='" + password + "';";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }

    public Cursor getUserData(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_USER + " WHERE " + USER_ID + "=" + id + ";";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }

    public int getUserId(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_USER + " WHERE " + USER_EMAIL + "='" + email + "';";
        Cursor c = db.rawQuery(sql, null);
        c.moveToFirst();
        String id = c.getString(c.getColumnIndex(DB_Handler.USER_ID));

        return Integer.parseInt(id);
    }


    /*------------------------------USER COMMENT--------------------------------------*/
    public void saveComment(String id, String comment) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COMMENT_USER_ID, id);
        cv.put(COMMENT, comment);

        db.insert(TABLE_COMMENTS, null, cv);
        db.close();
    }

    public List<String> getComments(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_COMMENTS + " WHERE " + COMMENT_USER_ID + "='" + id + "';";
        Cursor c = db.rawQuery(sql, null);
        List<String> l = new ArrayList<>();
        try {
            if (c != null) {
                c.moveToFirst();
                do {
                    l.add(c.getString(c.getColumnIndex(COMMENT)));
                }
                while (c.moveToNext());
                c.close();
            }
            return l;
        } catch (Exception e) {
            return null;
        }
    }
}
