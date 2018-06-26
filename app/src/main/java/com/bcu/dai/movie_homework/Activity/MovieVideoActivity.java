package com.bcu.dai.movie_homework.Activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.VideoView;

import com.bcu.dai.movie_homework.R;
import com.bumptech.glide.Glide;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class MovieVideoActivity extends AppCompatActivity {
    private String video_url;
    private String movie_name;
    private String movie_date;
    private String video_img;
    private String video_title;
    private JCVideoPlayerStandard jcVideoPlayerStandard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_video);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar !=null){
            actionBar.hide();
        }
        Intent intent = getIntent();
        video_url = intent.getStringExtra("video_url");
        movie_name=intent.getStringExtra("movie_name");
        movie_date=intent.getStringExtra("movie_time");
        video_img=intent.getStringExtra("video_img");
        video_title=intent.getStringExtra("video_title");
        jcVideoPlayerStandard=findViewById(R.id.video_view);
        jcVideoPlayerStandard.setUp(video_url,jcVideoPlayerStandard.SCREEN_LAYOUT_NORMAL,video_title);
        Glide.with(this).load(video_img).into(jcVideoPlayerStandard.thumbImageView);
    }
    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }
    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }

}
