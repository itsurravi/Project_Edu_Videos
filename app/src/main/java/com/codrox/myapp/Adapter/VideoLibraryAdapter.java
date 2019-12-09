package com.codrox.myapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.codrox.myapp.Models.VideoLib;
import com.codrox.myapp.R;

import java.util.List;

public class VideoLibraryAdapter extends BaseAdapter {

    Context c;
    List<VideoLib> list;

    public VideoLibraryAdapter(Context c, List<VideoLib> list) {
        this.c = c;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater li = LayoutInflater.from(c);

        View v = li.inflate(R.layout.adapter_video_library, parent, false);

        TextView tv = v.findViewById(R.id.txt_subject);
        TextView topic = v.findViewById(R.id.txt_topic);

        tv.setText(list.get(position).getSubTopicsInfo().getSubtitle());
        topic.setText(list.get(position).getSubTopicsInfo().getTitle());

        return v;
    }
}
