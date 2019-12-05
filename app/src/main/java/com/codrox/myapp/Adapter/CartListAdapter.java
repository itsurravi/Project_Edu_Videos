package com.codrox.myapp.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.codrox.myapp.Models.CartItems;

import java.util.List;

public class CartListAdapter extends BaseAdapter {

    Context c;
    List<CartItems> list;

    public CartListAdapter(Context c, List<CartItems> list) {
        this.c = c;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
