package com.codrox.myapp.Database;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManger {
    private SharedPreferences sp;
    private SharedPreferences.Editor ed;

    private final String PREF_FILE = "PrefFile";
    public static final String USER_LOGIN = "loggedin";

    public PrefManger(Context c) {
        sp = c.getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE);
        ed = sp.edit();
    }

    public void setStringValues(String key, String value)
    {
        ed.putString(key,value);
        ed.commit();
    }

    public void setLoggedIn(String key, boolean value)
    {
        ed.putBoolean(key, value);
        ed.commit();
    }

    public String getStringValues(String key)
    {
        return sp.getString(key, "");
    }

    public boolean isLoggedIn(String key)
    {
        return sp.getBoolean(key, false);
    }
}
