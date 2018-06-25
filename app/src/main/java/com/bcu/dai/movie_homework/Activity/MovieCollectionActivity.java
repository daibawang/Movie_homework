package com.bcu.dai.movie_homework.Activity;


import android.annotation.SuppressLint;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.aspsine.irecyclerview.IRecyclerView;
import com.bcu.dai.movie_homework.Adapter.MoviecollectionAdapter;
import com.bcu.dai.movie_homework.R;
import com.bcu.dai.movie_homework.com.bcu.Model.MovieCollection;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import org.json.JSONArray;
import org.json.JSONObject;
import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicDefaultHeader;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import okhttp3.Call;
import okhttp3.Response;

public class MovieCollectionActivity extends AppCompatActivity {
    private SQLiteDatabase db;
    private List<MovieCollection> movies;
    private RecyclerView sc;
    private LinearLayoutManager linearLayoutManager;
    private MoviecollectionAdapter movieAdapter;
    private SwipeRefreshLayout swipeRefresh;
    private TextView movienums;
    private PtrClassicFrameLayout mPtrFrame;
    private RecyclerView recyclerView;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_collection);
        sc = findViewById(R.id.sc);
        movienums = findViewById(R.id.moviesnum);
        //movies= LitePal.findAll(MovieCollection.class);
        int sqnum = LitePal.where("collection==?", "0").count(MovieCollection.class);
        String numistr = this.getResources().getString(R.string.movie_num);
        String nums = String.format(numistr, sqnum + "");
        movienums.setText(nums);
        movies = LitePal.where("collection==0").find(MovieCollection.class);
        movieAdapter = new MoviecollectionAdapter(this, movies);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //分割线
        DividerItemDecoration decoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        sc.setLayoutManager(linearLayoutManager);
        sc.addItemDecoration(decoration);
        sc.setAdapter(movieAdapter);
        movieAdapter.setOnMyItemClickListener(new MoviecollectionAdapter.OnMyItemClickListener() {
            @Override
            public void myClick(View v, int pos) {
                Log.i("listenclick", "myClick: "+pos);
            }

            @Override
            public void mLongClick(View v, int pos) {

            }
        });
        movieAdapter.setOnDelListener(new MoviecollectionAdapter.onSwipeListener() {
            @Override
            public void onDel(int pos) {
                if (pos >= 0 && pos < movies.size()) {
                    movies.remove(pos);
                    movieAdapter.notifyItemRemoved(pos);
                    loadnum();
                }
            }
            @Override
            public void onTop(int pos) {
                if (pos > 0 && pos < movies.size()) {
                    MovieCollection moviess = movies.get(pos);
                    movies.remove(moviess);
                    movieAdapter.notifyItemInserted(0);
                    movies.add(0, moviess);
                    movieAdapter.notifyItemRemoved(pos + 1);
                    if (linearLayoutManager.findFirstVisibleItemPosition() == 0) {
                        sc.scrollToPosition(0);
                    }
//                    notifyItemRangeChanged(0,holder.getAdapterPosition()+1);
                }
            }

        });
        //int sqnum = LitePal.count(MovieCollection.class);
        //下拉刷新//刷新布局
        mPtrFrame = findViewById(R.id.xl);
        final PtrClassicDefaultHeader header = new PtrClassicDefaultHeader(this);
        mPtrFrame.setLastUpdateTimeRelateObject(this);
        mPtrFrame.postDelayed(new Runnable() {
            @Override
            public void run() {
                //调用下拉刷新监听
                mPtrFrame.autoRefresh(true);
            }
        },200);
        //设置下拉刷新监听
        mPtrFrame.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(final PtrFrameLayout frame) {
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //loadData();这里有待思索,
                        loadDateBase();
                        movieAdapter.notifyDataSetChanged();
                        frame.refreshComplete();
                    }
                },2000);
            }
        });
    }
    public void loadnum(){
        int sqnum = LitePal.where("collection==?", "0").count(MovieCollection.class);
        String numistr = this.getResources().getString(R.string.movie_num);
        String nums = String.format(numistr, sqnum + "");
        movienums.setText(nums);
    }
    public void loadDateBase(){
        int sqnum = LitePal.where("collection==?", "0").count(MovieCollection.class);
        String numistr = this.getResources().getString(R.string.movie_num);
        String nums = String.format(numistr, sqnum + "");
        movienums.setText(nums);
        movies = LitePal.where("collection==0").find(MovieCollection.class);
        movieAdapter = new MoviecollectionAdapter(MovieCollectionActivity.this, movies);
        sc.setAdapter(movieAdapter);
    }
           private void loadData(){
                String path="https://api-m.mtime.cn/PageSubArea/HotPlayMovies.api?locationId=290";
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
            Log.i("movieCollections", "parseNetworkResponse: zzzz"+ movies);
            return movies;
        }
        @Override
        public void onError(Call call, Exception e, int id) {
        }
        @Override
        public void onResponse(List<MovieCollection> response, int id) {
            movieAdapter = new MoviecollectionAdapter(MovieCollectionActivity.this, movies);
            sc.setAdapter(movieAdapter);
        }
    }
    public void updata(){
        int sqnum = LitePal.where("collection==?", "0").count(MovieCollection.class);
        String numistr = this.getResources().getString(R.string.movie_num);
        String nums = String.format(numistr, sqnum + "");
        movienums.setText(nums);
    }

}
