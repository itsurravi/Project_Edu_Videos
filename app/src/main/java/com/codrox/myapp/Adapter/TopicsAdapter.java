package com.codrox.myapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.codrox.myapp.Models.TopicsInfo;
import com.codrox.myapp.R;

import java.util.List;

public class TopicsAdapter extends BaseAdapter {

    Context c;
    List<TopicsInfo> list;

    public TopicsAdapter(Context c, List<TopicsInfo> list) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater li = LayoutInflater.from(c);

        View v = li.inflate(R.layout.adapter_topics,null);

        TextView txt_title = v.findViewById(R.id.txt_topic_title);
        TextView txt_price = v.findViewById(R.id.txt_topic_price);
        ImageButton btn_addToCart = v.findViewById(R.id.btn_add_cart);

        final TopicsInfo topicsInfo = list.get(position);

        txt_title.setText(topicsInfo.getTitle());
        txt_price.setText(topicsInfo.getPrice());

        btn_addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(c, topicsInfo.getPrice(), Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }
}
