package com.bcu.dai.movie_homework.Activity;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
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
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

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
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_collection);
        //movies= LitePal.findAll(MovieCollection.class);
        movies = LitePal.where("collection==0").find(MovieCollection.class);
        sc = findViewById(R.id.sc);
        movienums = findViewById(R.id.moviesnum);
        movieAdapter = new MoviecollectionAdapter(this, movies);
        movieAdapter.setOnDelListener(new MoviecollectionAdapter.onSwipeListener() {
            @Override
            public void onDel(int pos) {
                if (pos >= 0 && pos < movies.size()) {
                    Toast.makeText(MovieCollectionActivity.this, "删除:" + pos, Toast.LENGTH_SHORT).show();
                    movies.remove(pos);
                    movieAdapter.notifyItemRemoved(pos);

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

        int sqnum = LitePal.where("collection==?", "0").count(MovieCollection.class);
        String numistr = this.getResources().getString(R.string.movie_num);
        String nums = String.format(numistr, sqnum + "");
        movienums.setText(nums);
        //int sqnum = LitePal.count(MovieCollection.class);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //分割线
        DividerItemDecoration decoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        sc.setLayoutManager(linearLayoutManager);
        sc.addItemDecoration(decoration);
        sc.setAdapter(movieAdapter);
        IRecyclerView iRecyclerView=findViewById(R.id.XL);
        iRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //LoadMoreFooterView loadMoreFooterView = (LoadMoreFooterView) iRecyclerView.getLoadMoreFooterView();
//        iRecyclerView.addHeaderView(headerView);
//        iRecyclerView.addFooterView(footerView);


    }


//        //下拉刷新
//        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.recycler_view);
//        swipeRefresh.setColorSchemeColors(R.color.colorPrimary);
//        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                swipeRefresh.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        swipeRefresh.setRefreshing(true);
//                        //7\refresh();
//                    }
//                });
//                //网络请求
//                loadMovieDatasFromNet("https://api-m.mtime.cn/PageSubArea/HotPlayMovies.api?locationId=290");
//            }
//        });
//        swipeRefresh.setRefreshing(false);
//    }
//    private void loadMovieDatasFromNet(String path){
//        OkHttpUtils.get().url(path).build().execute(new MyCallback());
//
//    }
//
//     class MyCallback extends Callback <List<MovieCollection>>{
//         @Override
//         public List<MovieCollection> parseNetworkResponse(Response response, int id) throws Exception {
//             String content = response.body().string();
//             JSONObject jsonObject = new JSONObject(content);
//             List<MovieCollection> movienew = new ArrayList<>();
//             JSONArray moviesJson = (JSONArray) jsonObject.get("movies");
//             Gson gson = new Gson();
//             for(int i = 0;i<moviesJson.length();i++){
//                 JSONObject movieJson = (JSONObject) moviesJson.get(i);
//                 MovieCollection moviene  = gson.fromJson(movieJson.toString(),MovieCollection.class);
//                 movienew.add(moviene);
//             }
//             Log.i("movieCollections", "parseNetworkResponse: zzzz"+ movies);
//             swipeRefresh.setRefreshing(false);
//             return movienew;
//         }
//
//         @Override
//         public void onError(Call call, Exception e, int id) {
//         }
//
//         @Override
//         public void onResponse(List<MovieCollection> response, int id) {
//             movieAdapter = new MoviecollectionAdapter(MovieCollectionActivity.this,response);
//         }
//     }
     //刷新
    public void refresh() {
        onCreate(null);

    }

}
