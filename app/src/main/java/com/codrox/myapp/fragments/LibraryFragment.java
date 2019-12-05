package com.codrox.myapp.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.codrox.myapp.Activity.MainActivity;
import com.codrox.myapp.Activity.VideoPlayerActivity;
import com.codrox.myapp.Adapter.VideoLibraryAdapter;
import com.codrox.myapp.Database.DB_Handler;
import com.codrox.myapp.Database.PrefManger;
import com.codrox.myapp.Models.TopicsInfo;
import com.codrox.myapp.Models.VideoLib;
import com.codrox.myapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class LibraryFragment extends Fragment {


    public LibraryFragment() {
        // Required empty public constructor
    }

    ListView video_list;
    List<VideoLib> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_library, container, false);
        video_list = v.findViewById(R.id.video_list);

        list = new ArrayList<>();

        video_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getContext(), VideoPlayerActivity.class);
                i.putExtra("topic_id", list.get(position).getTopicsInfo().getId());
                i.putExtra("videouri", list.get(position).getTopicsInfo().getVideoUrl());
                getContext().startActivity(i);
            }
        });

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("FragmentPauseResume", LibraryFragment.class.getSimpleName());
        DB_Handler db = new DB_Handler(getContext());
        PrefManger prefManger = new PrefManger(getContext());

        list = db.getSavedVideos(prefManger.getStringValues(DB_Handler.USER_EMAIL));

        VideoLibraryAdapter adapter = new VideoLibraryAdapter(getContext(), list);

        video_list.setAdapter(adapter);
    }
}
