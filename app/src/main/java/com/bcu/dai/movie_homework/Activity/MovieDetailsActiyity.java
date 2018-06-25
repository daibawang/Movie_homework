package com.bcu.dai.movie_homework.Activity;


import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.bcu.dai.movie_homework.R;
import com.bcu.dai.movie_homework.com.bcu.Model.MovieCollection;
import com.bcu.dai.movie_homework.json.Actors;
import com.bcu.dai.movie_homework.json.Director;
import com.bcu.dai.movie_homework.json.Other;
import com.bcu.dai.movie_homework.json.StageImg;
import com.bcu.dai.movie_homework.json.Video;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import org.json.JSONArray;
import org.json.JSONObject;
import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class MovieDetailsActiyity extends AppCompatActivity {
    private SQLiteDatabase db;
    public long movie_id;
    private List<Actors> actorsDataList;
    //private List<Director> directorDataList;
    private Director directorData;
    private Other otherData;
    private Video videoData;
    private List<StageImg> stageImgsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details_actiyity);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar !=null){
            actionBar.hide();
        }
        Intent intent = getIntent();
        db = LitePal.getDatabase();
        movie_id = intent.getLongExtra("movieid",0);
        Log.i("movieid", "onCreate:这是"+movie_id);
        String m = movie_id+"";
        //loadother("https://ticket-api-m.mtime.cn/movie/detail.api?locationId=290&movieId=125805");
        int movie = LitePal.where("movieId==?",m).count(Other.class);
        Log.i("movie", "onCreate: "+movie);
        if(movie==0){
            loadactors("https://ticket-api-m.mtime.cn/movie/detail.api?locationId=290&movieId="+movie_id);
            Log.i("获取", "onCreate: "+"获取");
        }else{
            actorsDataList=LitePal.where("moviedid==?",m).find(Actors.class);
            directorData=LitePal.where("moviedid==?",m).find(Director.class).get(0);
            stageImgsList=LitePal.where("moviedid==?",m).find(StageImg.class);
            otherData=LitePal.where("moviedid==?",m).find(Other.class).get(0);
            videoData=LitePal.where("moviedid==?",m).find(Video.class).get(0);
            Log.i("直接去", "onCreate: "+"直接去");
        }
    }

    private void loadother(String s) {
        OkHttpUtils.get().url(s).build().execute(new MyCallback2());
    }

    private void loadactors(String path) {
        OkHttpUtils.get().url(path).build().execute(new MyCallback1());
    }
    private class MyCallback1 extends Callback<List<Actors>> {
        @Override
        public List<Actors> parseNetworkResponse(Response response, int id) throws Exception {
            String content = response.body().string();
            JSONObject jsonObject = new JSONObject(content);
            JSONObject  jsondata= (JSONObject) jsonObject.get("data");
            JSONObject  jsonbasic=(JSONObject) jsondata.get("basic");
            JSONArray jsonactors=(JSONArray) jsonbasic.get("actors");
            Gson gson = new Gson();
            List<Actors> actors = new ArrayList<>();
            for(int i = 0;i<jsonactors.length();i++){
                JSONObject actorJson = (JSONObject) jsonactors.get(i);
                //movieJson就是json的电影对象 ---> Movie
                Actors act  = gson.fromJson(actorJson.toString(),Actors.class);
                act.setMoviedid(movie_id);
                actors.add(act);

            }
            //存数据库
            LitePal.saveAll(actors);
            //解析img
            JSONObject jsonStageimg = (JSONObject) jsonbasic.get("stageImg");
            JSONArray jsonimg=(JSONArray) jsonStageimg.get("list");
            List<StageImg> stageImgs = new ArrayList<>();
            for(int i=0;i<jsonimg.length();i++){
                JSONObject imgJson = (JSONObject) jsonimg.get(i);
                StageImg stageImg=gson.fromJson(imgJson.toString(),StageImg.class);
                stageImg.setMoviedid(movie_id);
                stageImgs.add(stageImg);
            }
            LitePal.saveAll(stageImgs);
            //解析director
            JSONObject jsondirector = (JSONObject) jsonbasic.get("director");
            Director director = new Director();
            director=gson.fromJson(jsondirector.toString(),Director.class);
            director.setMoviedid( movie_id);
            director.save();
            //解析other
            Other other = new Other();
            other=gson.fromJson(jsonbasic.toString(),Other.class);
            other.save();
//            //解析Video
            JSONObject jsonvideo = (JSONObject) jsonbasic.get("video");
            Video video = new Video();
            video=gson.fromJson(jsonvideo.toString(),Video.class);
            video.setMoviedid(movie_id);
            video.save();


////            for(Actors actors2 : actors){
////                Log.i("actors2", "parseNetworkResponse: "+actors2);
////            }

            return actors;
        }
        @Override
        public void onError(Call call, Exception e, int id) {

        }

        @Override
        public void onResponse(List<Actors> response, int id) {

        }
    }

    private class MyCallback2 extends Callback {
        @Override
        public Object parseNetworkResponse(Response response, int id) throws Exception {
            String content = response.body().string();
            JSONObject jsonObject = new JSONObject(content);
            JSONObject  jsondata= (JSONObject) jsonObject.get("data");
            JSONObject  jsonbasic=(JSONObject) jsondata.get("basic");

            Other other = new Other();
            Gson gson = new Gson();
            other=gson.fromJson(jsonbasic.toString(),Other.class);
            other.save();
            Log.i("movieCollections", "parseNetworkResponse: bbbb"+ other);
            return other;
        }

        @Override
        public void onError(Call call, Exception e, int id) {

        }

        @Override
        public void onResponse(Object response, int id) {

        }
    }
}
