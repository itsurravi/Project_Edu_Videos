package com.codrox.myapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.VideoView;
import android.os.Bundle;
import android.widget.Toast;
import android.media.MediaPlayer;
import android.net.Uri;

import com.codrox.myapp.Database.DB_Handler;
import com.codrox.myapp.Database.PrefManger;
import com.codrox.myapp.R;

import java.util.ArrayList;
import java.util.List;

public class VideoPlayerActivity extends AppCompatActivity {

    VideoView video;
    int position = 0;
    MediaController mp;
    ListView list_comments;
    EditText ed_comment;
    Button btn_comment;
    DB_Handler db;
    PrefManger prefManger;

    List<String> list;

    ArrayAdapter ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        db = new DB_Handler(this);
        prefManger = new PrefManger(this);

        list = new ArrayList<>();

        video = (VideoView) findViewById(R.id.videoview);
        list_comments = findViewById(R.id.list_comments);
        ed_comment = findViewById(R.id.ed_comment);
        btn_comment = findViewById(R.id.btn_comment);

        getAllComments(db.getUserId(prefManger.getStringValues(DB_Handler.USER_EMAIL)));

        if (mp == null) {
            mp = new MediaController(VideoPlayerActivity.this);
            mp.setAnchorView(video);
            video.setMediaController(mp);
        }
        try {
            int id = this.getRaw("video");
            video.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + id));

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();

        }

        video.requestFocus();
        video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                video.seekTo(position);
                if (position == 0) {
                    video.start();

                }
                mediaPlayer.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                    @Override
                    public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i1) {

                        mp.setAnchorView(video);
                    }
                });
            }
        });

        btn_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comment = ed_comment.getText().toString();
                int id = db.getUserId(prefManger.getStringValues(DB_Handler.USER_EMAIL));
                db.saveComment(String.valueOf(id), comment);
                getAllComments(id);
                ed_comment.setText("");
            }
        });
    }

    private void getAllComments(int userId) {
        list = db.getComments(String.valueOf(userId));
        if (list != null && list.size() > 0) {
            ad = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
            list_comments.setAdapter(ad);
        }
    }

    public int getRaw(String a) {
        String packagename = this.getPackageName();
        int resourceid = this.getResources().getIdentifier(a, "raw", packagename);
        return resourceid;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("current position", video.getCurrentPosition());
        video.pause();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        position = savedInstanceState.getInt("current position");
        video.seekTo(position);
    }
}