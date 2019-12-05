package com.codrox.myapp.fragments;


import android.app.ProgressDialog;
import android.os.AsyncTask;
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
import com.codrox.myapp.Database.DB_Handler;
import com.codrox.myapp.Models.TopicsInfo;
import com.codrox.myapp.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChaptersFragment extends Fragment {


    public ChaptersFragment() {
        // Required empty public constructor
    }

    ListView chapters_list;
    TextView subject_name;
    List<String> list;
    String subject;
    String className;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_chapters, container, false);
        chapters_list = v.findViewById(R.id.chapters_list);
        subject_name = v.findViewById(R.id.txt_subject_name);

        subject = getArguments().getString("subject");
        className = getArguments().getString("className");
        subject_name.setText(subject);

        new ChapterFetch().execute();

        chapters_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TopicsFragment tf = new TopicsFragment();

                Bundle bundle = new Bundle();
                bundle.putString("chapter", list.get(position));
                bundle.putString("subject", subject);
                bundle.putString("className", className);

                tf.setArguments(bundle);

                getFragmentManager().beginTransaction()
                        .replace(R.id.subject_container, tf).addToBackStack(null).commit();
            }
        });
        return v;
    }

    class ChapterFetch extends AsyncTask<Void, Void, Void> {

        ProgressDialog pd = new ProgressDialog(getContext());

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd.setCancelable(false);
            pd.setMessage("Please Wait");
            pd.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            DB_Handler db = new DB_Handler(getContext());

            list = db.getChaptersList(className, subject);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            pd.dismiss();
            if (list.size() > 0) {
                ChaptersAdapter ca = new ChaptersAdapter(getContext(), list);

                chapters_list.setAdapter(ca);
            }
            else
            {
                Toast.makeText(getContext(), "No Chapter Found", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
