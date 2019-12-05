package com.codrox.myapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.codrox.myapp.Models.TopicsInfo;
import com.codrox.myapp.R;

import java.util.List;

public class ChaptersAdapter extends BaseAdapter {

    Context c;
    List<String> chapters;

    public ChaptersAdapter(Context c, List<String> chapters) {
        this.c = c;
        this.chapters = chapters;
    }

    @Override
    public int getCount() {
        return chapters.size();
    }

    @Override
    public Object getItem(int position) {
        return chapters.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater li = LayoutInflater.from(c);
        View v = li.inflate(R.layout.adapter_chapters_list, null);

        TextView tv = v.findViewById(R.id.txt_chapters);
        tv.setText(chapters.get(position));

        return v;
    }
}
