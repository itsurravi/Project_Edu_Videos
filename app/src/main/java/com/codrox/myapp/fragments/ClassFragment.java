package com.codrox.myapp.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.codrox.myapp.Activity.MainActivity;
import com.codrox.myapp.Activity.SubjectListActivity;
import com.codrox.myapp.Adapter.ClassFragGridAdapter;
import com.codrox.myapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClassFragment extends Fragment {


    public ClassFragment() {
        // Required empty public constructor
    }

    private GridView gv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_class, container, false);
        gv = v.findViewById(R.id.gview);

        ClassFragGridAdapter ad = new ClassFragGridAdapter(getContext());

        gv.setAdapter(ad);

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(getContext(), SubjectListActivity.class);
                i.putExtra("className", String.valueOf(parent.getItemAtPosition(position)));
                getActivity().startActivity(i);

                FragmentManager m = getFragmentManager();
                m.popBackStackImmediate();

            }
        });
        return v;
    }
}
