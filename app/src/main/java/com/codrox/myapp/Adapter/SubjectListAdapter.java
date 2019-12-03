package com.codrox.myapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.codrox.myapp.R;

public class SubjectListAdapter extends BaseAdapter {

    Context c;
    String subjects[];

    public SubjectListAdapter(Context c, String[] subjects) {
        this.c = c;
        this.subjects = subjects;
    }

    @Override
    public int getCount() {
        return subjects.length;
    }

    @Override
    public Object getItem(int position) {
        return subjects[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater li = LayoutInflater.from(c);
        View v = li.inflate(R.layout.adapter_subject_list, null);
        TextView tv = v.findViewById(R.id.txt_subject_name);
        tv.setText(subjects[position]);

        return v;
    }
}
