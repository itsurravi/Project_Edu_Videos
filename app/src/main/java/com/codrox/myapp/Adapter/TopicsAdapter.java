package com.codrox.myapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.codrox.myapp.Database.DB_Handler;
import com.codrox.myapp.Database.PrefManger;
import com.codrox.myapp.Models.SubTopicsInfo;
import com.codrox.myapp.Models.TopicsInfo;
import com.codrox.myapp.R;
import com.codrox.myapp.fragments.TopicsFragment;

import java.util.List;

public class TopicsAdapter extends BaseExpandableListAdapter {

    Context c;

    List<TopicsInfo> topicsInfos;

    public TopicsAdapter(Context c, List<TopicsInfo> topicsInfos) {
        this.c = c;
        this.topicsInfos = topicsInfos;
    }

    @Override
    public int getGroupCount() {
        return topicsInfos.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return topicsInfos.get(groupPosition).getSubTopicList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return topicsInfos.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return topicsInfos.get(groupPosition).getSubTopicList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = topicsInfos.get(groupPosition).getTopicName();

        if(convertView==null)
        {
            LayoutInflater infalInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.adapter_topics, null);
        }

        TextView txt_topics = convertView.findViewById(R.id.txt_topics);

        txt_topics.setText(headerTitle);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.adapter_sub_topics, null);
        }

        TextView txt_title = convertView.findViewById(R.id.txt_topic_title);
        TextView txt_price = convertView.findViewById(R.id.txt_topic_price);
        ImageButton btn_addToCart = convertView.findViewById(R.id.btn_add_cart);

        final SubTopicsInfo subTopicsInfo = (SubTopicsInfo) getChild(groupPosition,childPosition);

        txt_title.setText(subTopicsInfo.getSubtitle());
        txt_price.setText(subTopicsInfo.getPrice());

        btn_addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PrefManger prefManger = new PrefManger(c);
                DB_Handler db = new DB_Handler(c);

                if(TopicsFragment.topic.contains(subTopicsInfo.getSubtitle()))
                {
                    Toast.makeText(c, "Item is already in your Cart", Toast.LENGTH_SHORT).show();
                    return;
                }

                TopicsFragment.topic.add(subTopicsInfo.getSubtitle());

                db.saveToCart(subTopicsInfo.getId(), prefManger.getStringValues(DB_Handler.USER_EMAIL));
                Toast.makeText(c, "Added to Cart", Toast.LENGTH_SHORT).show();

                if(TopicsFragment.btn_gotocart.getVisibility()==View.GONE)
                {
                    TopicsFragment.btn_gotocart.setVisibility(View.VISIBLE);
                }
            }
        });

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    /*Context c;
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

        TextView txt_topics = v.findViewById(R.id.txt_topics);

        txt_topics.setText(list.get(position).getTopicName());

        return v;
    }*/
}
