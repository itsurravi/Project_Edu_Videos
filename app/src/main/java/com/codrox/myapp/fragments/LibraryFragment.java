package com.codrox.myapp.fragments;


import android.content.Context;
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
import com.codrox.myapp.Adapter.VideoLibraryAdapter;
import com.codrox.myapp.Models.Subjects;
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
    List<Subjects> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_library, container, false);
        video_list = v.findViewById(R.id.video_list);

        list = new ArrayList<>();

        for(int i=0;i<10;i++)
        {
            list.add(new Subjects("Video => "+(i+1), ((200*i)+(i*3))/4));
        }

        VideoLibraryAdapter adapter = new VideoLibraryAdapter(getContext(), list);

        video_list.setAdapter(adapter);

        video_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        return v;
    }

}
