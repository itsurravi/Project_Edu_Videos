package com.codrox.myapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

import com.codrox.myapp.Models.CartItems;
import com.codrox.myapp.Models.TopicsInfo;
import com.codrox.myapp.Models.VideoLib;

import java.lang.reflect.Array;
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
    public static final String TOPIC_SUBTOPIC_NAME = "sub_topic_name";
    public static final String TOPIC_PRICE = "topic_price";
    public static final String TOPIC_VIDEO_URL = "topic_video_url";

    /*----------------Cart Table-----------------*/
    public static final String TABLE_CART = "Cart";
    public static final String CART_ID = "cart_id";
    public static final String CART_USER_ID = "user_id";
    public static final String CART_TOPIC_ID = "topic_id";

    /*----------------VideoLib Table-------------*/
    public static final String TABLE_VIDEO_LIB = "Video_Library";
    public static final String VIDEO_ID = "video_id";
    public static final String VIDEO_USER_ID = "user_id";
    public static final String VIDEO_TOPIC_ID = "topic_id";

    Context c;

    public DB_Handler(Context context) {
        super(context, DB_NAME, null, 1);
        this.c = context;
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
                + TOPIC_SUBTOPIC_NAME + " VARCHAR, "
                + TOPIC_PRICE + " VARCHAR, "
                + TOPIC_VIDEO_URL + " VARCHAR);";

        db.execSQL(topicsTable);

        String cartTable = "CREATE TABLE " + TABLE_CART
                + "(" + CART_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CART_USER_ID + " VARCHAR, "
                + CART_TOPIC_ID + " VARCHAR UNIQUE);";
        db.execSQL(cartTable);

        String videoTable = "CREATE TABLE " + TABLE_VIDEO_LIB
                + "(" + VIDEO_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + VIDEO_USER_ID + " VARCHAR, "
                + VIDEO_TOPIC_ID + " VARCHAR);";
        db.execSQL(videoTable);


        insertTopics(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    private String getVideoUri(String video_name) {
        String packagename = c.getPackageName();
        int resourceid = c.getResources().getIdentifier(video_name, "raw", packagename);
        return String.valueOf(Uri.parse("android.resource://" + packagename + "/" + resourceid));
    }

    /*-------------------------------Chapter Topics TABLE-----------------------------*/
    private void insertTopics(SQLiteDatabase db) {
        String sql = "INSERT INTO `topic` (`topic_id`, `class`, `subject`, `chapter`, `topic_name`, `sub_topic_name`, `topic_price`, `topic_video_url`) VALUES" +
                "(1, 'Class 9', 'Hindi', 'Chapter 1', 'Topic 1', 'SubTopic 1', '123', '" + getVideoUri("video") + "')," +
                "(2, 'Class 9', 'Hindi', 'Chapter 1', 'Topic 1', 'SubTopic 2', '123', '" + getVideoUri("video") + "')," +
                "(3, 'Class 9', 'Hindi', 'Chapter 1', 'Topic 1', 'SubTopic 3', '123', '" + getVideoUri("video") + "')," +
                "(4, 'Class 9', 'Hindi', 'Chapter 1', 'Topic 2', 'SubTopic 1', '124', '" + getVideoUri("video") + "')," +
                "(5, 'Class 9', 'Hindi', 'Chapter 1', 'Topic 2', 'SubTopic 2', '124', '" + getVideoUri("video") + "')," +
                "(6, 'Class 9', 'Hindi', 'Chapter 1', 'Topic 2', 'SubTopic 3', '124', '" + getVideoUri("video") + "')," +
                "(7, 'Class 9', 'Hindi', 'Chapter 1', 'Topic 3', 'SubTopic 1', '125', '" + getVideoUri("video") + "')," +
                "(8, 'Class 9', 'Hindi', 'Chapter 1', 'Topic 3', 'SubTopic 2', '125', '" + getVideoUri("video") + "')," +
                "(9, 'Class 9', 'Hindi', 'Chapter 1', 'Topic 3', 'SubTopic 3', '125', '" + getVideoUri("video") + "')," +
                "(10, 'Class 9', 'Hindi', 'Chapter 2', 'Topic 1', 'SubTopic 1', '126', '" + getVideoUri("video") + "')," +
                "(11, 'Class 9', 'Hindi', 'Chapter 2', 'Topic 1', 'SubTopic 2', '126', '" + getVideoUri("video") + "')," +
                "(12, 'Class 9', 'Hindi', 'Chapter 2', 'Topic 1', 'SubTopic 3', '126', '" + getVideoUri("video") + "')," +
                "(13, 'Class 9', 'Hindi', 'Chapter 2', 'Topic 2', 'SubTopic 1', '127', '" + getVideoUri("video") + "')," +
                "(14, 'Class 9', 'Hindi', 'Chapter 2', 'Topic 2', 'SubTopic 2', '127', '" + getVideoUri("video") + "')," +
                "(15, 'Class 9', 'Hindi', 'Chapter 2', 'Topic 2', 'SubTopic 3', '127', '" + getVideoUri("video") + "')," +
                "(16, 'Class 9', 'Hindi', 'Chapter 3', 'Topic 1', 'SubTopic 1', '128', '" + getVideoUri("video") + "')," +
                "(17, 'Class 9', 'Hindi', 'Chapter 3', 'Topic 1', 'SubTopic 2', '128', '" + getVideoUri("video") + "')," +
                "(18, 'Class 9', 'Hindi', 'Chapter 3', 'Topic 1', 'SubTopic 3', '128', '" + getVideoUri("video") + "')," +
                "(19, 'Class 9', 'Hindi', 'Chapter 3', 'Topic 2', 'SubTopic 1', '129', '" + getVideoUri("video") + "')," +
                "(20, 'Class 9', 'Hindi', 'Chapter 3', 'Topic 2', 'SubTopic 2', '129', '" + getVideoUri("video") + "')," +
                "(21, 'Class 9', 'Hindi', 'Chapter 3', 'Topic 2', 'SubTopic 3', '129', '" + getVideoUri("video") + "')," +
                "(22, 'Class 10', 'Hindi', 'Chapter 1', 'Topic 1', 'SubTopic 1', '130', '" + getVideoUri("video") + "')," +
                "(23, 'Class 10', 'Hindi', 'Chapter 1', 'Topic 1', 'SubTopic 2', '130', '" + getVideoUri("video") + "')," +
                "(24, 'Class 10', 'Hindi', 'Chapter 1', 'Topic 1', 'SubTopic 3', '130', '" + getVideoUri("video") + "')," +
                "(25, 'Class 10', 'Hindi', 'Chapter 1', 'Topic 2', 'SubTopic 1', '131', '" + getVideoUri("video") + "')," +
                "(26, 'Class 10', 'Hindi', 'Chapter 1', 'Topic 2', 'SubTopic 2', '131', '" + getVideoUri("video") + "')," +
                "(27, 'Class 10', 'Hindi', 'Chapter 1', 'Topic 2', 'SubTopic 3', '131', '" + getVideoUri("video") + "')," +
                "(28, 'Class 10', 'Hindi', 'Chapter 2', 'Topic 1', 'SubTopic 1', '132', '" + getVideoUri("video") + "')," +
                "(29, 'Class 10', 'Hindi', 'Chapter 2', 'Topic 1', 'SubTopic 2', '132', '" + getVideoUri("video") + "')," +
                "(30, 'Class 10', 'Hindi', 'Chapter 2', 'Topic 1', 'SubTopic 3', '132', '" + getVideoUri("video") + "')," +
                "(31, 'Class 10', 'Hindi', 'Chapter 2', 'Topic 2', 'SubTopic 1', '133', '" + getVideoUri("video") + "')," +
                "(32, 'Class 10', 'Hindi', 'Chapter 2', 'Topic 2', 'SubTopic 2', '133', '" + getVideoUri("video") + "')," +
                "(33, 'Class 10', 'Hindi', 'Chapter 2', 'Topic 2', 'SubTopic 3', '133', '" + getVideoUri("video") + "')," +
                "(34, 'Class 10', 'Hindi', 'Chapter 2', 'Topic 3', 'SubTopic 1', '134', '" + getVideoUri("video") + "')," +
                "(35, 'Class 10', 'Hindi', 'Chapter 2', 'Topic 3', 'SubTopic 2', '134', '" + getVideoUri("video") + "')," +
                "(36, 'Class 10', 'Hindi', 'Chapter 2', 'Topic 3', 'SubTopic 3', '134', '" + getVideoUri("video") + "')," +
                "(37, 'Class 10', 'Hindi', 'Chapter 2', 'Topic 4', 'SubTopic 1', '135', '" + getVideoUri("video") + "')," +
                "(38, 'Class 10', 'Hindi', 'Chapter 2', 'Topic 4', 'SubTopic 2', '135', '" + getVideoUri("video") + "')," +
                "(39, 'Class 10', 'Hindi', 'Chapter 2', 'Topic 4', 'SubTopic 3', '135', '" + getVideoUri("video") + "')," +
                "(40, 'Class 10', 'Hindi', 'Chapter 3', 'Topic 1', 'SubTopic 1', '136', '" + getVideoUri("video") + "')," +
                "(41, 'Class 10', 'Hindi', 'Chapter 3', 'Topic 1', 'SubTopic 2', '136', '" + getVideoUri("video") + "')," +
                "(42, 'Class 10', 'Hindi', 'Chapter 3', 'Topic 1', 'SubTopic 3', '136', '" + getVideoUri("video") + "')," +
                "(43, 'Class 10', 'Hindi', 'Chapter 3', 'Topic 2', 'SubTopic 1', '137', '" + getVideoUri("video") + "')," +
                "(44, 'Class 10', 'Hindi', 'Chapter 3', 'Topic 2', 'SubTopic 2', '137', '" + getVideoUri("video") + "')," +
                "(45, 'Class 10', 'Hindi', 'Chapter 3', 'Topic 2', 'SubTopic 3', '137', '" + getVideoUri("video") + "')," +
                "(46, 'Class 10', 'Hindi', 'Chapter 3', 'Topic 3', 'SubTopic 1', '138', '" + getVideoUri("video") + "')," +
                "(47, 'Class 10', 'Hindi', 'Chapter 3', 'Topic 3', 'SubTopic 2', '138', '" + getVideoUri("video") + "')," +
                "(48, 'Class 10', 'Hindi', 'Chapter 3', 'Topic 3', 'SubTopic 3', '138', '" + getVideoUri("video") + "')," +
                "(49, 'Class 11', 'Hindi', 'Chapter 1', 'Topic 1', 'SubTopic 1', '139', '" + getVideoUri("video") + "')," +
                "(50, 'Class 11', 'Hindi', 'Chapter 1', 'Topic 1', 'SubTopic 2', '139', '" + getVideoUri("video") + "')," +
                "(51, 'Class 11', 'Hindi', 'Chapter 1', 'Topic 1', 'SubTopic 3', '139', '" + getVideoUri("video") + "')," +
                "(52, 'Class 11', 'Hindi', 'Chapter 1', 'Topic 2', 'SubTopic 1', '140', '" + getVideoUri("video") + "')," +
                "(53, 'Class 11', 'Hindi', 'Chapter 1', 'Topic 2', 'SubTopic 2', '140', '" + getVideoUri("video") + "')," +
                "(54, 'Class 11', 'Hindi', 'Chapter 1', 'Topic 2', 'SubTopic 3', '140', '" + getVideoUri("video") + "')," +
                "(55, 'Class 11', 'Hindi', 'Chapter 1', 'Topic 3', 'SubTopic 1', '141', '" + getVideoUri("video") + "')," +
                "(56, 'Class 11', 'Hindi', 'Chapter 1', 'Topic 3', 'SubTopic 2', '141', '" + getVideoUri("video") + "')," +
                "(57, 'Class 11', 'Hindi', 'Chapter 1', 'Topic 3', 'SubTopic 3', '141', '" + getVideoUri("video") + "')," +
                "(58, 'Class 11', 'Hindi', 'Chapter 2', 'Topic 1', 'SubTopic 1', '142', '" + getVideoUri("video") + "')," +
                "(59, 'Class 11', 'Hindi', 'Chapter 2', 'Topic 1', 'SubTopic 2', '142', '" + getVideoUri("video") + "')," +
                "(60, 'Class 11', 'Hindi', 'Chapter 2', 'Topic 1', 'SubTopic 3', '142', '" + getVideoUri("video") + "')," +
                "(61, 'Class 11', 'Hindi', 'Chapter 2', 'Topic 2', 'SubTopic 1', '143', '" + getVideoUri("video") + "')," +
                "(62, 'Class 11', 'Hindi', 'Chapter 2', 'Topic 2', 'SubTopic 2', '143', '" + getVideoUri("video") + "')," +
                "(63, 'Class 11', 'Hindi', 'Chapter 2', 'Topic 2', 'SubTopic 3', '143', '" + getVideoUri("video") + "')," +
                "(64, 'Class 11', 'Hindi', 'Chapter 2', 'Topic 3', 'SubTopic 1', '144', '" + getVideoUri("video") + "')," +
                "(65, 'Class 11', 'Hindi', 'Chapter 2', 'Topic 3', 'SubTopic 2', '144', '" + getVideoUri("video") + "')," +
                "(66, 'Class 11', 'Hindi', 'Chapter 2', 'Topic 3', 'SubTopic 3', '144', '" + getVideoUri("video") + "')," +
                "(67, 'Class 12', 'Hindi', 'Chapter 1', 'Topic 1', 'SubTopic 1', '145', '" + getVideoUri("video") + "')," +
                "(68, 'Class 12', 'Hindi', 'Chapter 1', 'Topic 1', 'SubTopic 2', '145', '" + getVideoUri("video") + "')," +
                "(69, 'Class 12', 'Hindi', 'Chapter 1', 'Topic 1', 'SubTopic 3', '145', '" + getVideoUri("video") + "')," +
                "(70, 'Class 12', 'Hindi', 'Chapter 1', 'Topic 2', 'SubTopic 1', '146', '" + getVideoUri("video") + "')," +
                "(71, 'Class 12', 'Hindi', 'Chapter 1', 'Topic 2', 'SubTopic 2', '146', '" + getVideoUri("video") + "')," +
                "(72, 'Class 12', 'Hindi', 'Chapter 1', 'Topic 2', 'SubTopic 3', '146', '" + getVideoUri("video") + "')," +
                "(73, 'Class 12', 'Hindi', 'Chapter 1', 'Topic 3', 'SubTopic 1', '147', '" + getVideoUri("video") + "')," +
                "(74, 'Class 12', 'Hindi', 'Chapter 1', 'Topic 3', 'SubTopic 2', '147', '" + getVideoUri("video") + "')," +
                "(75, 'Class 12', 'Hindi', 'Chapter 1', 'Topic 3', 'SubTopic 3', '147', '" + getVideoUri("video") + "')," +
                "(76, 'Class 12', 'Hindi', 'Chapter 1', 'Topic 4', 'SubTopic 1', '148', '" + getVideoUri("video") + "')," +
                "(77, 'Class 12', 'Hindi', 'Chapter 1', 'Topic 4', 'SubTopic 2', '148', '" + getVideoUri("video") + "')," +
                "(78, 'Class 12', 'Hindi', 'Chapter 1', 'Topic 4', 'SubTopic 3', '148', '" + getVideoUri("video") + "');";

        db.execSQL(sql);

        Log.d(TAG, "Command Inserted");
    }

    public List<String> getChaptersList(String className, String subject) {
        List<String> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "SELECT DISTINCT " + TOPIC_CHAPTER + " FROM " + TABLE_TOPIC + " WHERE "
                + TOPIC_CLASS + "='" + className + "' AND " + TOPIC_SUBJECT + "='" + subject + "';";
        Cursor c = db.rawQuery(sql, null);
        if (c.getCount() > 0) {
            c.moveToFirst();
            do {

                String t_chapter = c.getString(c.getColumnIndex(TOPIC_CHAPTER));

                list.add(t_chapter);
            }
            while (c.moveToNext());
            c.close();
        }
        return list;
    }

    public List<String> getTopicTitleList(String className, String subject, String chapter) {
        List<String> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "SELECT DISTINCT " + TOPIC_NAME + " FROM " + TABLE_TOPIC + " WHERE "
                + TOPIC_CLASS + "='" + className + "' AND "
                + TOPIC_CHAPTER + "='" + chapter + "' AND "
                + TOPIC_SUBJECT + "='" + subject + "';";

        Cursor c = db.rawQuery(sql, null);
        if (c.getCount() > 0) {
            c.moveToFirst();
            do {

                String t_chapter = c.getString(c.getColumnIndex(TOPIC_NAME));

                list.add(t_chapter);
            }
            while (c.moveToNext());
            c.close();
        }
        return list;
    }

    public List<TopicsInfo> getSubTopicsList(String className, String subject, String chapter, String topic) {
        List<TopicsInfo> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "SELECT * FROM " + TABLE_TOPIC + " WHERE " + TOPIC_CLASS + "='" + className + "' AND "
                + TOPIC_SUBJECT + "='" + subject + "' AND " + TOPIC_NAME + "='" + topic + "' AND "
                + TOPIC_CHAPTER + "='" + chapter + "';";

        Cursor c = db.rawQuery(sql, null);
        if (c.getCount() > 0) {
            c.moveToFirst();
            do {
                String t_id = c.getString(c.getColumnIndex(TOPIC_ID));
                String t_class = c.getString(c.getColumnIndex(TOPIC_CLASS));
                String t_sub = c.getString(c.getColumnIndex(TOPIC_SUBJECT));
                String t_chapter = c.getString(c.getColumnIndex(TOPIC_CHAPTER));
                String t_name = c.getString(c.getColumnIndex(TOPIC_NAME));
                String t_s_name = c.getString(c.getColumnIndex(TOPIC_SUBTOPIC_NAME));
                String t_price = c.getString(c.getColumnIndex(TOPIC_PRICE));
                String t_url = c.getString(c.getColumnIndex(TOPIC_VIDEO_URL));

                TopicsInfo info = new TopicsInfo(t_id, t_class, t_sub, t_chapter, t_name, t_s_name, t_price, t_url);

                list.add(info);
            }
            while (c.moveToNext());
            c.close();
        }
        return list;

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
    public void saveComment(String id, String comment, String topic_id  /*need to add video id here*/) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COMMENT_USER_ID, id);
        cv.put(COMMENT, comment);
        cv.put(COMMENT_TOPIC_ID, topic_id);

        db.insert(TABLE_COMMENTS, null, cv);
        db.close();
    }

    public List<String> getComments(String id, String topic_id  /*need to add video id here*/) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_COMMENTS + " WHERE " + COMMENT_USER_ID + "='" + id + "' AND " + COMMENT_TOPIC_ID + "='" + topic_id + "';";
//        String sql = "SELECT * FROM " + TABLE_COMMENTS + " WHERE " + COMMENT_USER_ID + "='" + id + "';";
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
    public void saveToCart(String id, String user_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(CART_TOPIC_ID, id);
        cv.put(CART_USER_ID, user_id);

        db.insert(TABLE_CART, null, cv);
        db.close();
    }

    public void removeToCart(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CART, CART_ID + "=?", new String[]{id});
    }

    public List<CartItems> getAllCartItems(String user_id) {
        List<CartItems> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT " + TABLE_CART + "." + CART_ID + ", "
                + TABLE_TOPIC + "." + TOPIC_ID + ", "
                + TABLE_TOPIC + "." + TOPIC_CLASS + ", "
                + TABLE_TOPIC + "." + TOPIC_SUBJECT + ", "
                + TABLE_TOPIC + "." + TOPIC_CHAPTER + ", "
                + TABLE_TOPIC + "." + TOPIC_NAME + ", "
                + TABLE_TOPIC + "." + TOPIC_SUBTOPIC_NAME + ", "
                + TABLE_TOPIC + "." + TOPIC_VIDEO_URL + ", "
                + TABLE_TOPIC + "." + TOPIC_PRICE + " FROM "
                + TABLE_CART + " INNER JOIN " + TABLE_TOPIC + " ON "
                + TABLE_CART + "." + CART_TOPIC_ID + "=" + TABLE_TOPIC + "." + TOPIC_ID + " WHERE "
                + TABLE_CART + "." + CART_USER_ID + "='" + user_id + "';";

        Log.d(TAG, "All Cart SQL=> " + sql);

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
                String t_s_name = c.getString(c.getColumnIndex(TOPIC_SUBTOPIC_NAME));
                String t_price = c.getString(c.getColumnIndex(TOPIC_PRICE));
                String t_url = c.getString(c.getColumnIndex(TOPIC_VIDEO_URL));

                TopicsInfo info = new TopicsInfo(t_id, t_class, t_sub, t_chapter, t_name, t_s_name, t_price, t_url);
                CartItems items = new CartItems(id, info);

                list.add(items);
            }
            while (c.moveToNext());
            c.close();
        }
        return list;
    }


    /*-----------------------------VIDEO LIBRARY TABLE---------------------------------*/
    public void saveToLibrary(String id, String user_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(VIDEO_TOPIC_ID, id);
        cv.put(VIDEO_USER_ID, user_id);

        db.insert(TABLE_VIDEO_LIB, null, cv);
        db.close();
    }

    public List<VideoLib> getSavedVideos(String user_id) {
        List<VideoLib> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select " + TABLE_VIDEO_LIB + "." + VIDEO_ID + ", "
                + TABLE_TOPIC + "." + TOPIC_ID + ", "
                + TABLE_TOPIC + "." + TOPIC_CLASS + ", "
                + TABLE_TOPIC + "." + TOPIC_SUBJECT + ", "
                + TABLE_TOPIC + "." + TOPIC_CHAPTER + ", "
                + TABLE_TOPIC + "." + TOPIC_NAME + ", "
                + TABLE_TOPIC + "." + TOPIC_SUBTOPIC_NAME + ", "
                + TABLE_TOPIC + "." + TOPIC_VIDEO_URL + ", "
                + TABLE_TOPIC + "." + TOPIC_PRICE + " FROM "
                + TABLE_VIDEO_LIB + " INNER JOIN " + TABLE_TOPIC + " ON "
                + TABLE_VIDEO_LIB + "." + VIDEO_TOPIC_ID + "=" + TABLE_TOPIC + "." + TOPIC_ID + " WHERE "
                + TABLE_VIDEO_LIB + "." + VIDEO_USER_ID + "='" + user_id + "';";

        Log.d(TAG, "All Cart SQL=> " + sql);

        Cursor c = db.rawQuery(sql, null);
        if (c.getCount() > 0) {
            c.moveToFirst();
            do {
                String id = c.getString(c.getColumnIndex(VIDEO_ID));
                String t_id = c.getString(c.getColumnIndex(TOPIC_ID));
                String t_class = c.getString(c.getColumnIndex(TOPIC_CLASS));
                String t_sub = c.getString(c.getColumnIndex(TOPIC_SUBJECT));
                String t_chapter = c.getString(c.getColumnIndex(TOPIC_CHAPTER));
                String t_name = c.getString(c.getColumnIndex(TOPIC_NAME));
                String t_s_name = c.getString(c.getColumnIndex(TOPIC_SUBTOPIC_NAME));
                String t_price = c.getString(c.getColumnIndex(TOPIC_PRICE));

                String t_url = c.getString(c.getColumnIndex(TOPIC_VIDEO_URL));

                TopicsInfo info = new TopicsInfo(t_id, t_class, t_sub, t_chapter, t_name, t_s_name, t_price, t_url);
                VideoLib lib = new VideoLib(id, info);

                list.add(lib);
            }
            while (c.moveToNext());
            c.close();
        }
        return list;
    }

}
