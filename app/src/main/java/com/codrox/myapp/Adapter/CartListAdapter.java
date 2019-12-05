package com.codrox.myapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codrox.myapp.Models.CartItems;
import com.codrox.myapp.R;

import java.util.List;

public class CartListAdapter extends BaseAdapter {

    Context c;
    List<CartItems> list;

    ItemRemoveClick click;

    public CartListAdapter(Context c, List<CartItems> list, ItemRemoveClick click) {
        this.c = c;
        this.list = list;
        this.click=click;
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
        convertView = li.inflate(R.layout.adapter_cart_list, null);

        TextView title, price;
        ImageView iv;

        title = convertView.findViewById(R.id.txt_topic_title);
        price = convertView.findViewById(R.id.txt_topic_price);
        iv = convertView.findViewById(R.id.img_remove);

        title.setText(list.get(position).getTopicsInfo().getSubtitle());
        price.setText(list.get(position).getTopicsInfo().getPrice());

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.onItemRemoveClick(position);
            }
        });

        return convertView;
    }

    public interface ItemRemoveClick{
        void onItemRemoveClick(int position);
    }
}
