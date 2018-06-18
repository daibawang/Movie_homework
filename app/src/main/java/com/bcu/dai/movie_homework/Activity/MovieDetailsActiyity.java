package com.bcu.dai.movie_homework.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.bcu.dai.movie_homework.R;

public class MovieDetailsActiyity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details_actiyity);
        Intent intent = getIntent();
        Long movieid = intent.getLongExtra("movieid",0);
        Log.i("movieid", "onCreate:这是 "+movieid);

    }
}
