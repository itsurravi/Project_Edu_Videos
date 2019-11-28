package com.codrox.myapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.codrox.myapp.R;

public class ClassFragGridAdapter extends BaseAdapter {

    private String[] className = {"Class 9", "Class 10", "Class 11", "Class 12"};

    public Context c;

    public ClassFragGridAdapter(Context c) {
        this.c = c;
    }

    @Override
    public int getCount() {
        return className.length;
    }

    @Override
    public Object getItem(int position) {
        return className[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater li = LayoutInflater.from(c);

        convertView = li.inflate(R.layout.adapter_class_frag_grid, null);

        TextView tv = convertView.findViewById(R.id.txt_class);

        tv.setText(className[position]);

        return convertView;
    }
}
