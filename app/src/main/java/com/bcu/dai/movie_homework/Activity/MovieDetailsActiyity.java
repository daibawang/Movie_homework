package com.bcu.dai.movie_homework.Activity;


import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bcu.dai.movie_homework.Adapter.MoviePhotoListAdapter;
import com.bcu.dai.movie_homework.Adapter.MovieStarListAdapter;
import com.bcu.dai.movie_homework.Adapter.MoviecollectionAdapter;
import com.bcu.dai.movie_homework.R;
import com.bcu.dai.movie_homework.View.ASmartimgView;
import com.bcu.dai.movie_homework.com.bcu.Model.MovieCollection;
import com.bcu.dai.movie_homework.json.Actors;
import com.bcu.dai.movie_homework.json.Director;
import com.bcu.dai.movie_homework.json.Other;
import com.bcu.dai.movie_homework.json.StageImg;
import com.bcu.dai.movie_homework.json.Video;
import com.google.gson.Gson;
import com.lcodecore.extextview.ExpandTextView;
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
    //电影信息
    private TextView movie_name_c;
    private TextView movie_d;
    private TextView movie_name_en;
    private TextView movie_type;
    private TextView movie_place_time;
    private TextView movie_shangyintime;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private MovieStarListAdapter movieAdapter;
    private ExpandTextView expandTextView;
    private ASmartimgView aSmartimgView;
    private RecyclerView recyclerView_photo;
    private MoviePhotoListAdapter photoListAdapter;
    private LinearLayoutManager linearLayoutManager2;
    private String  date;
    //视频
    private FrameLayout framevideo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__detail);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar !=null){
            actionBar.hide();
        }
        Intent intent = getIntent();
        movie_id = intent.getLongExtra("movieid",0);
        Log.i("movieid", "onCreate:这是"+movie_id);
        String m = movie_id+"";
        //loadother("https://ticket-api-m.mtime.cn/movie/detail.api?locationId=290&movieId=125805");
        int movie = LitePal.where("movieId==?",m).count(Other.class);
        Log.i("movie", "onCreate: "+movie);
        if(movie==0){
            loadactors("https://ticket-api-m.mtime.cn/movie/detail.api?locationId=290&movieId="+movie_id);
            Log.i("获取", "onCreate: "+"获取");
            Log.i("zsszzzzzzzzzzzz", "onCreate: "+otherData);
        }else{
            actorsDataList=LitePal.where("moviedid==?",m).find(Actors.class);
            directorData=LitePal.where("moviedid==?",m).find(Director.class).get(0);
            stageImgsList=LitePal.where("moviedid==?",m).find(StageImg.class);
            otherData=LitePal.where("movieid==?",m).find(Other.class).get(0);
            videoData=LitePal.where("moviedid==?",m).find(Video.class).get(0);
            Log.i("直接去", "onCreate: "+"直接去");
        }
        movie_name_c=findViewById(R.id.tv_movie_name);
        movie_d=findViewById(R.id.tv_movie_3D);
        movie_name_en=findViewById(R.id.tv_movie_english_name);
        movie_type=findViewById(R.id.tv_movie_type);
        movie_place_time=findViewById(R.id.tv_src_place_time);
        movie_shangyintime=findViewById(R.id.tv_movie_time);
        expandTextView=findViewById(R.id.rv_movie_story);
        aSmartimgView=findViewById(R.id.iv_movie_img);
        framevideo=findViewById(R.id.fl_movie_img);

        Log.i("zsszzzzzzzzzzzz", "onCreate: "+otherData);
        //绑定数据
        movie_name_c.setText(otherData.getName());
        movie_name_en.setText(otherData.getNameEn());
        String type="";
        for (int i=0;i<otherData.getType().size();i++){
            type=type+otherData.getType().get(i)+" ";
        }
        movie_type.setText(type);
        movie_place_time.setText(otherData.getReleaseArea()+" / "+otherData.getMins());
        date=otherData.getReleaseDate().substring(0,4)+"-"+otherData.getReleaseDate().substring(4,6)+"-"+otherData.getReleaseDate().substring(6,8);
        movie_shangyintime.setText(date);
        String screenTypeS = "2D";
        if (otherData.isIs3D()){
            screenTypeS = "3D";
        }else if (otherData.isIMAX()){
            screenTypeS = "IMAX";
        }else if(otherData.isIMAX3D()){
            screenTypeS = "IMAX3D";
        }
        movie_d.setText(screenTypeS);
        expandTextView.setText(otherData.getStory());
        aSmartimgView.loadImageFromNet(otherData.getImg());
        //显示
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        recyclerView =findViewById(R.id.rv_movie_star);
        movieAdapter=new MovieStarListAdapter(this,actorsDataList);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(movieAdapter);
        linearLayoutManager2 = new LinearLayoutManager(this);
        linearLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView_photo=findViewById(R.id.rv_movie_video_photo);
        photoListAdapter=new MoviePhotoListAdapter(this,stageImgsList);
        recyclerView_photo.setLayoutManager(linearLayoutManager2);
        recyclerView_photo.setAdapter(photoListAdapter);
        //视频播放
        framevideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MovieDetailsActiyity.this, MovieVideoActivity.class);
                intent.putExtra("video_url",videoData.getHightUrl());
                intent.putExtra("video_title",videoData.getTitle());
                intent.putExtra("video_img",videoData.getImg());
                intent.putExtra("movie_name",otherData.getName());
                intent.putExtra("movie_time",date);
                MovieDetailsActiyity.this.startActivity(intent);
            }
        });
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
            actorsDataList=actors;
            directorData=director;
            videoData =video;
            stageImgsList =stageImgs;
            otherData=other;


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
