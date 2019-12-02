package com.codrox.myapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.VideoView;
import android.os.Bundle;
import android.widget.Toast;
import android.media.MediaPlayer;
import android.net.Uri;

import com.codrox.myapp.R;

public class VideoPlayerActivity extends AppCompatActivity {

    VideoView video;
    int position = 0;
    MediaController mp;
    ListView list_comments;
    EditText ed_comment;
    Button btn_comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        video = (VideoView) findViewById(R.id.videoview);
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