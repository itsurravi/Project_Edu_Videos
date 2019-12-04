package com.codrox.myapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.codrox.myapp.Adapter.SubjectListAdapter;
import com.codrox.myapp.R;
import com.codrox.myapp.fragments.ChaptersFragment;

public class SubjectListActivity extends AppCompatActivity {

    ListView lv;

    String subjects[];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_list);

        lv = findViewById(R.id.list_subject);

        Intent i = getIntent();
        String className = i.getStringExtra("className");

        setTitle(className);

        switch (className) {
            case "Class 9":
                subjects = getResources().getStringArray(R.array.subjects_ix);
                break;
            case "Class 10":
                subjects = getResources().getStringArray(R.array.subjects_x);
                break;
            case "Class 11":
                subjects = getResources().getStringArray(R.array.subjects_xi);
                break;
            case "Class 12":
                subjects = getResources().getStringArray(R.array.subjects_xii);
                break;
        }

        SubjectListAdapter ad = new SubjectListAdapter(this, subjects);

        lv.setAdapter(ad);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ChaptersFragment cf = new ChaptersFragment();
                Bundle bundle = new Bundle();
                bundle.putString("subject", subjects[position]);

                cf.setArguments(bundle);
                Toast.makeText(SubjectListActivity.this, subjects[position], Toast.LENGTH_SHORT).show();

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.subject_container, cf).addToBackStack(null).commit();
            }
        });
    }
}
