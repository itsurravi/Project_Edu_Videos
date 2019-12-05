package com.codrox.myapp.fragments;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.codrox.myapp.Adapter.SubTopicsAdapter;
import com.codrox.myapp.Database.DB_Handler;
import com.codrox.myapp.Models.TopicsInfo;
import com.codrox.myapp.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SubTopicsFragment extends Fragment {


    public SubTopicsFragment() {
        // Required empty public constructor

    }

    ListView list_topics;
    TextView txt_topic_name;

    List<TopicsInfo> topicsInfo;

    String chapter = "";
    String subject = "";
    String className = "";
    String topic = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_sub_topics, container, false);
        list_topics = v.findViewById(R.id.list_sub_topics);
        txt_topic_name = v.findViewById(R.id.txt_topic_name);

        chapter = getArguments().getString("chapter");
        subject = getArguments().getString("subject");
        className = getArguments().getString("className");
        topic = getArguments().getString("topic");

        txt_topic_name.setText(topic);

        new TopicsFetch().execute();

        return v;
    }

    class TopicsFetch extends AsyncTask<Void, Void, Void> {
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

            topicsInfo = db.getSubTopicsList(className, subject, chapter, topic);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            pd.dismiss();
            if (topicsInfo.size() > 0) {
                SubTopicsAdapter ad = new SubTopicsAdapter(getContext(), topicsInfo);
                list_topics.setAdapter(ad);
            } else {
                Toast.makeText(getContext(), "No Chapter Found", Toast.LENGTH_SHORT).show();
            }

        }
    }

}
