package com.codrox.myapp.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.codrox.myapp.Adapter.ChaptersAdapter;
import com.codrox.myapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChaptersFragment extends Fragment {


    public ChaptersFragment() {
        // Required empty public constructor
    }

    ListView chapters_list;
    TextView subject_name;

    String chapters[] = {
            "Chapter 1",
            "Chapter 2",
            "Chapter 3",
            "Chapter 4",
            "Chapter 5",
            "Chapter 6",
            "Chapter 7",
            "Chapter 8"
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_chapters, container, false);
        chapters_list = v.findViewById(R.id.chapters_list);
        subject_name = v.findViewById(R.id.txt_subject_name);

        final String subject = getArguments().getString("subject");
        subject_name.setText(subject);

        ChaptersAdapter ca = new ChaptersAdapter(getContext(), chapters);

        chapters_list.setAdapter(ca);

        chapters_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), chapters[position], Toast.LENGTH_SHORT).show();
                TopicsFragment tf = new TopicsFragment();

                Bundle bundle = new Bundle();
                bundle.putString("chapter", subject+"->"+chapters[position]);

                tf.setArguments(bundle);

                getFragmentManager().beginTransaction()
                        .replace(R.id.subject_container, tf).addToBackStack(null).commit();
            }
        });
        return v;
    }

}
