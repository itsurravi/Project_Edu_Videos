package com.codrox.myapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.codrox.myapp.Models.CartItems;
import com.codrox.myapp.Models.TopicsInfo;

import java.util.ArrayList;
import java.util.List;

public class DB_Handler extends SQLiteOpenHelper {

    public final String TAG = "SQL_Query";

    public static final String DB_NAME = "UserDB";

    /*---------------User Table------------------*/
    public static final String TABLE_USER = "Users";
    public static final String USER_ID = "id";
    public static final String USER_NAME = "name";
    public static final String USER_EMAIL = "email";
    public static final String USER_PASSWORD = "password";


    /*---------------User Comments Table-------------*/
    public static final String TABLE_COMMENTS = "Comments";
    public static final String COMMENT_ID = "id";
    public static final String COMMENT_USER_ID = "user_id";
    public static final String COMMENT = "comment";
    public static final String COMMENT_TOPIC_ID = "topic_id";

    /*--------------Chapter Topics Table--------------*/
    public static final String TABLE_TOPIC = "Topic";
    public static final String TOPIC_ID = "topic_id";
    public static final String TOPIC_CLASS = "class";
    public static final String TOPIC_SUBJECT = "subject";
    public static final String TOPIC_CHAPTER = "chapter";
    public static final String TOPIC_NAME = "topic_name";
    public static final String TOPIC_PRICE = "topic_price";
    public static final String TOPIC_VIDEO_URL = "topic_video_url";

    /*----------------Cart Table-----------------*/
    public static final String TABLE_CART = "Cart";
    public static final String CART_ID = "cart_id";
    public static final String CART_TOPIC_ID = "topic_id";

    /*----------------VideoLib Table-------------*/
    public static final String TABLE_VIDEO_LIB = "Video_Library";
    public static final String VIDEO_ID = "video_id";
    public static final String VIDEO_TOPIC_ID = "topic_id";

    public DB_Handler(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String userTable = "CREATE TABLE " + TABLE_USER
                + "(" + USER_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + USER_NAME + " VARCHAR, "
                + USER_EMAIL + " VARCHAR, "
                + USER_PASSWORD + " VARCHAR);";
        db.execSQL(userTable);

        String userComments = "CREATE TABLE " + TABLE_COMMENTS
                + "(" + COMMENT_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COMMENT_USER_ID + " VARCHAR, "
                + COMMENT_TOPIC_ID + " VARCHAR, "
                + COMMENT + " VARCHAR);";

        db.execSQL(userComments);

        String topicsTable = "CREATE TABLE " + TABLE_TOPIC
                + "(" + TOPIC_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TOPIC_CLASS + " VARCHAR, "
                + TOPIC_SUBJECT + " VARCHAR, "
                + TOPIC_CHAPTER + " VARCHAR, "
                + TOPIC_NAME + " VARCHAR, "
                + TOPIC_PRICE + " VARCHAR, "
                + TOPIC_VIDEO_URL + " VARCHAR);";

        db.execSQL(topicsTable);

        String cartTable = "CREATE TABLE " + TABLE_CART
                + "(" + CART_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CART_TOPIC_ID + " VARCHAR);";
        db.execSQL(cartTable);

        String videoTable = "CREATE TABLE " + TABLE_VIDEO_LIB
                + "(" + VIDEO_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + VIDEO_TOPIC_ID + " VARCHAR);";
        db.execSQL(videoTable);
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
    public void saveComment(String id, String comment/*,String topic_id*/  /*need to add topic id here*/) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COMMENT_USER_ID, id);
        cv.put(COMMENT, comment);
//        cv.put(COMMENT_TOPIC_ID, topic_id);

        db.insert(TABLE_COMMENTS, null, cv);
        db.close();
    }

    public List<String> getComments(String id/*, String topic_id*/  /*need to add topic id here*/) {
        SQLiteDatabase db = this.getReadableDatabase();
//        String sql = "SELECT * FROM " + TABLE_COMMENTS + " WHERE " + COMMENT_USER_ID + "='" + id + "' AND " + COMMENT_TOPIC_ID + "='" + topic_id + "';";
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


    /*------------------------------CART TABLE-----------------------------------------*/
    public void saveToCart(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(CART_TOPIC_ID, id);

        db.insert(TABLE_CART, null, cv);
        db.close();
    }

    public void removeToCart(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CART, CART_ID + "=?", new String[]{id});
    }

    public List<CartItems> getAllCartItems() {
        List<CartItems> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select " + TABLE_CART + "." + CART_ID + ", "
                + TABLE_TOPIC + "." + TOPIC_ID + ", "
                + TABLE_TOPIC + "." + TOPIC_CLASS + ", "
                + TABLE_TOPIC + "." + TOPIC_SUBJECT + ", "
                + TABLE_TOPIC + "." + TOPIC_CHAPTER + ", "
                + TABLE_TOPIC + "." + TOPIC_NAME + ", "
                + TABLE_TOPIC + "." + TOPIC_PRICE + " FROM "
                + TABLE_CART + " INNER JOIN " + TABLE_TOPIC + " ON "
                + TABLE_CART + "." + CART_TOPIC_ID + "=" + TABLE_TOPIC + "." + TOPIC_ID + ";";

        Log.d(TAG, "All Cart SQL=> "+sql);

        Cursor c = db.rawQuery(sql, null);
        if (c.getCount() > 0) {
            c.moveToFirst();
            do {
                String id = c.getString(c.getColumnIndex(CART_ID));

                String t_id = c.getString(c.getColumnIndex(TOPIC_ID));
                String t_class = c.getString(c.getColumnIndex(TOPIC_CLASS));
                String t_sub = c.getString(c.getColumnIndex(TOPIC_SUBJECT));
                String t_chapter = c.getString(c.getColumnIndex(TOPIC_CHAPTER));
                String t_name = c.getString(c.getColumnIndex(TOPIC_NAME));
                String t_price = c.getString(c.getColumnIndex(TOPIC_PRICE));

                TopicsInfo info = new TopicsInfo(t_id, t_class, t_sub, t_chapter, t_name, t_price);
                CartItems items = new CartItems(id, info);

                list.add(items);
            }
            while (c.moveToNext());
            c.close();
        }
        return list;
    }


}
