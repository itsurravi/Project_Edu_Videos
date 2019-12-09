package com.codrox.myapp.fragments;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.codrox.myapp.Activity.MainActivity;
import com.codrox.myapp.Adapter.TopicsAdapter;
import com.codrox.myapp.Database.DB_Handler;
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

    ExpandableListView list_topics;
    TextView chapter_name;
    public static Button btn_gotocart;

    List<TopicsInfo> topicsInfo;

    public static List<String> topic;

    String chapter = "";
    String subject = "";
    String className = "";

    private int lastExpandedPosition = -1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_topics, container, false);
        topic = new ArrayList<>();
        list_topics = v.findViewById(R.id.list_topics);
        chapter_name = v.findViewById(R.id.txt_chapter_name);
        btn_gotocart = v.findViewById(R.id.btn_gotocart);

        chapter = getArguments().getString("chapter");
        subject = getArguments().getString("subject");
        className = getArguments().getString("className");

        chapter_name.setText(subject + " => " + chapter);

        new TopicsFetch().execute();

        list_topics.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                if (lastExpandedPosition != -1 && groupPosition != lastExpandedPosition) {
                    list_topics.collapseGroup(lastExpandedPosition);
                }
                lastExpandedPosition = groupPosition;
            }
        });

        btn_gotocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
                MainActivity.navigationItemView.setSelectedItemId(R.id.nav_cart);
            }
        });

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

            topicsInfo = db.getTopicTitleList(className, subject, chapter);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            pd.dismiss();
            if (topicsInfo.size() > 0) {
                TopicsAdapter ad = new TopicsAdapter(getContext(), topicsInfo);
                list_topics.setAdapter(ad);
            } else {
                Toast.makeText(getContext(), "No Topic Found", Toast.LENGTH_SHORT).show();
            }

        }
    }

}
