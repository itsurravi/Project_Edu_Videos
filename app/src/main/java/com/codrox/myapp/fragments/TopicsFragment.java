package com.codrox.myapp.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.codrox.myapp.Adapter.TopicsAdapter;
import com.codrox.myapp.Models.TopicsInfo;
import com.codrox.myapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopicsFragment extends Fragment {


    public TopicsFragment() {
        // Required empty public constructor

    }

    ListView list_topics;
    TextView chapter_name;

    List<TopicsInfo> topicsInfo;

    String topics[] = {
            "Topic 1",
            "Topic 2",
            "Topic 3",
            "Topic 4",
            "Topic 5",
            "Topic 6"
    };

    String price[] = {
            "120",
            "120",
            "120",
            "120",
            "120",
            "120"
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_topics, container, false);
        list_topics = v.findViewById(R.id.list_topics);
        chapter_name = v.findViewById(R.id.txt_chapter_name);
        final String chapter = getArguments().getString("chapter");
        chapter_name.setText(chapter);
        topicsInfo = new ArrayList<>();
        for (int i = 0; i < topics.length; i++) {
            topicsInfo.add(new TopicsInfo(String.valueOf(i), topics[i], price[i]));
        }

        TopicsAdapter ad = new TopicsAdapter(getContext(), topicsInfo);
        list_topics.setAdapter(ad);

        return v;
    }

}
