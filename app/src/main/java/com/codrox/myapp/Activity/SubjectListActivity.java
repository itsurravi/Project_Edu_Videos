package com.codrox.myapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.codrox.myapp.R;

public class SubjectListActivity extends AppCompatActivity {

    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_list);

        lv = findViewById(R.id.list_subject);

        Intent i = getIntent();
        String className = i.getStringExtra("className");

        Toast.makeText(this, className, Toast.LENGTH_SHORT).show();
    }
}
