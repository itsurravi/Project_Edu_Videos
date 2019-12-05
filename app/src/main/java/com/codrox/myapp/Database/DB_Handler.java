package com.codrox.myapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
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


    /*-------------------------------Chapter Topics TABLE-----------------------------*/
    private void insertTopics(SQLiteDatabase db) {
        String sql = "INSERT INTO `topic` (`topic_id`, `class`, `subject`, `chapter`, `topic_name`,`sub_topic_name`, `topic_price`, `topic_video_url`) VALUES" +
                "(1, 'Class 9', 'Hindi', 'Chapter 1', 'pehla 1 paath', 'Subtopic 1', '123', 'abcdabcd')," +
                "(2, 'Class 9', 'Hindi', 'Chapter 1', 'pehla 2 paath', 'Subtopic 12','124', 'abcdabcd')," +
                "(3, 'Class 9', 'Hindi', 'Chapter 1', 'pehla 3 paath', 'Subtopic 13','125', 'abcdabcd')," +
                "(4, 'Class 9', 'Hindi', 'Chapter 2', 'pehla 4 paath', 'Subtopic 1','126', 'abcdabcd')," +
                "(5, 'Class 9', 'Hindi', 'Chapter 2', 'pehla 5 paath', 'Subtopic 12','127', 'abcdabcd')," +
                "(6, 'Class 9', 'Hindi', 'Chapter 3', 'pehla 6 paath', 'Subtopic 1','128', 'abcdabcd')," +
                "(7, 'Class 9', 'Hindi', 'Chapter 3', 'pehla 7 paath', 'Subtopic 12','129', 'abcdabcd')," +
                "(8, 'Class 10', 'Hindi', 'Chapter 1', 'pehla 1 paath', 'Subtopic 1','130', 'abcdabcd')," +
                "(9, 'Class 10', 'Hindi', 'Chapter 1', 'pehla 2 paath', 'Subtopic 12','131', 'abcdabcd')," +
                "(10, 'Class 10', 'Hindi', 'Chapter 2', 'pehla 3 paath', 'Subtopic 1','132', 'abcdabcd')," +
                "(11, 'Class 10', 'Hindi', 'Chapter 2', 'pehla 4 paath', 'Subtopic 12','133', 'abcdabcd')," +
                "(12, 'Class 10', 'Hindi', 'Chapter 2', 'pehla 5 paath', 'Subtopic 13','134', 'abcdabcd')," +
                "(13, 'Class 10', 'Hindi', 'Chapter 2', 'pehla 6 paath', 'Subtopic 14','135', 'abcdabcd')," +
                "(14, 'Class 10', 'Hindi', 'Chapter 3', 'pehla 7 paath', 'Subtopic 1','136', 'abcdabcd')," +
                "(15, 'Class 10', 'Hindi', 'Chapter 3', 'pehla 8 paath', 'Subtopic 12','137', 'abcdabcd')," +
                "(16, 'Class 10', 'Hindi', 'Chapter 3', 'pehla 9 paath', 'Subtopic 13','138', 'abcdabcd')," +
                "(17, 'Class 11', 'Hindi', 'Chapter 1', 'pehla 1 paath', 'Subtopic 1','139', 'abcdabcd')," +
                "(18, 'Class 11', 'Hindi', 'Chapter 1', 'pehla 2 paath', 'Subtopic 12','140', 'abcdabcd')," +
                "(19, 'Class 11', 'Hindi', 'Chapter 1', 'pehla 3 paath', 'Subtopic 13','141', 'abcdabcd')," +
                "(20, 'Class 11', 'Hindi', 'Chapter 2', 'pehla 4 paath', 'Subtopic 1','142', 'abcdabcd')," +
                "(21, 'Class 11', 'Hindi', 'Chapter 2', 'pehla 5 paath', 'Subtopic 12','143', 'abcdabcd')," +
                "(22, 'Class 11', 'Hindi', 'Chapter 2', 'pehla 6 paath', 'Subtopic 13','144', 'abcdabcd')," +
                "(23, 'Class 12', 'Hindi', 'Chapter 1', 'pehla 1 paath', 'Subtopic 1','145', 'abcdabcd')," +
                "(24, 'Class 12', 'Hindi', 'Chapter 1', 'pehla 2 paath', 'Subtopic 12','146', 'abcdabcd')," +
                "(25, 'Class 12', 'Hindi', 'Chapter 1', 'pehla 3 paath', 'Subtopic 13','147', 'abcdabcd')," +
                "(26, 'Class 12', 'Hindi', 'Chapter 1', 'pehla 4 paath', 'Subtopic 14','148', 'abcdabcd');";

        db.execSQL(sql);

        Log.d(TAG, "Command Inserted");
    }

    public List<String> getChaptersList(String className, String subject) {
        List<String> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "SELECT DISTINCT " + TOPIC_CHAPTER + " FROM " + TABLE_TOPIC + " WHERE " + TOPIC_CLASS + "='" + className + "' AND " + TOPIC_SUBJECT + "='" + subject + "';";
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

    public List<TopicsInfo> getTopicsList(String className, String subject, String chapter) {
        List<TopicsInfo> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "SELECT * FROM " + TABLE_TOPIC + " WHERE " + TOPIC_CLASS + "='" + className + "' AND " + TOPIC_SUBJECT + "='" + subject + "' AND " + TOPIC_CHAPTER + "='" + chapter + "';";
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
    public void saveComment(String id, String comment/*,String topic_id*/  /*need to add video id here*/) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COMMENT_USER_ID, id);
        cv.put(COMMENT, comment);
//        cv.put(COMMENT_TOPIC_ID, topic_id);

        db.insert(TABLE_COMMENTS, null, cv);
        db.close();
    }

    public List<String> getComments(String id/*, String topic_id*/  /*need to add video id here*/) {
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
        String sql = "select " + TABLE_CART + "." + CART_ID + ", "
                + TABLE_TOPIC + "." + TOPIC_ID + ", "
                + TABLE_TOPIC + "." + TOPIC_CLASS + ", "
                + TABLE_TOPIC + "." + TOPIC_SUBJECT + ", "
                + TABLE_TOPIC + "." + TOPIC_CHAPTER + ", "
                + TABLE_TOPIC + "." + TOPIC_NAME + ", "
                + TABLE_TOPIC + "." + TOPIC_SUBTOPIC_NAME + ", "
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
                String t_s_name = c.getString(c.getColumnIndex(TOPIC_NAME));
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
