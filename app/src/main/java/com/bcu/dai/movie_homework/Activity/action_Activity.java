package com.bcu.dai.movie_homework.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.bcu.dai.movie_homework.R;
import com.bcu.dai.movie_homework.com.bcu.Model.MovieCollection;
import com.bcu.dai.movie_homework.com.bcu.Model.MovieDetailBean;
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
public class action_Activity extends AppCompatActivity {

    private SQLiteDatabase db;
    private Button button;
    private List<MovieCollection> moviess = new ArrayList<>();
    private List<MovieDetailBean> moviedetail = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action);
        db = LitePal.getDatabase();
        //loadMovieDatasFromNet("https://api-m.mtime.cn/PageSubArea/HotPlayMovies.api?locationId=290");
        loadMovieDetailDatasFromNet("https://ticket-api-m.mtime.cn/movie/detail.api?locationId=290&movieId=125805");
        button = findViewById(R.id.gotoMovie);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(action_Activity.this,MovieCollectionActivity.class);
                action_Activity.this.startActivity(intent);
            }
        });
    }
    private void loadMovieDetailDatasFromNet(String path) {
        OkHttpUtils.get().url(path).build().execute(new MyCallback1());
    }
    class MyCallback1 extends Callback<List<MovieDetailBean>>{

        @Override
        public List<MovieDetailBean> parseNetworkResponse(Response response, int id) throws Exception {
            String content = response.body().string();
            JSONObject jsonObject = new JSONObject(content);
            List<MovieDetailBean> moviess = new ArrayList<>();
//            Log.i("JSON", "JSON: "+moviesJson.get(0));
            //new一个Gson对象
//            JSONObject  jsondata= (JSONObject) jsonObject.get("data");
//            JSONObject  jsonbasic=(JSONObject) jsondata.get("basic");
//            JSONArray  jsonactors=(JSONArray) jsonbasic.get("actors");

            JSONArray moviesJson = (JSONArray) jsonObject.get("data");
            Gson gson = new Gson();
            for(int i = 0;i<moviesJson.length();i++){
                JSONObject movieJson = (JSONObject) moviesJson.get(i);
                //movieJson就是json的电影对象 ---> Movie
                //将json字符串转为bean对象
                MovieDetailBean movie  = gson.fromJson(movieJson.toString(),MovieDetailBean.class);
                moviess.add(movie);
            }
            //存数据库
            LitePal.deleteAll(MovieDetailBean.class);
            LitePal.saveAll(moviess);
            //moviess = DataSupport.where("rDay==15").find(MovieCollection.class);
            Log.i("JSON", "JSON: zzzz"+ moviess);
            return moviess;
        }

        @Override
        public void onError(Call call, Exception e, int id) {

        }
        @Override
        public void onResponse(List<MovieDetailBean> response, int id) {
            Log.i("movies", "onResponse: "+response);
        }
    }


    private void loadMovieDatasFromNet(String path) {
        OkHttpUtils.get().url(path).build().execute(new MyCallback());
    }
    class MyCallback extends Callback<List<MovieCollection>>{

        @Override
        public List<MovieCollection> parseNetworkResponse(Response response, int id) throws Exception {
            String content = response.body().string();
            JSONObject jsonObject = new JSONObject(content);
            List<MovieCollection> movies = new ArrayList<>();
            JSONArray moviesJson = (JSONArray) jsonObject.get("movies");
            Gson gson = new Gson();
            for(int i = 0;i<moviesJson.length();i++){
                JSONObject movieJson = (JSONObject) moviesJson.get(i);
                //movieJson就是json的电影对象 ---> Movie
                MovieCollection movie  = gson.fromJson(movieJson.toString(),MovieCollection.class);
                movies.add(movie);
            }
            //存数据库
            LitePal.deleteAll(MovieCollection.class);
            LitePal.saveAll(movies);
            //moviess = DataSupport.where("rDay==15").find(MovieCollection.class);
            Log.i("movieCollections", "parseNetworkResponse: bbbb"+ movies);
            return movies;
        }

        @Override
        public void onError(Call call, Exception e, int id) {

        }
        @Override
        public void onResponse(List<MovieCollection> response, int id) {
            Log.i("movies", "onResponse: "+response);
        }
    }

}
