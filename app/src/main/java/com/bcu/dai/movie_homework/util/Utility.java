package com.bcu.dai.movie_homework.util;

import com.bcu.dai.movie_homework.json.Actors;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by dai on 2018/6/24.
 */

public class Utility {
    private List<Actors> actors;
    private void loadactors(String path) {
        OkHttpUtils.get().url(path).build().execute(new MyCallback1());
    }

    private class MyCallback1 extends Callback {
        @Override
        public Object parseNetworkResponse(Response response, int id) throws Exception {
            String content = response.body().string();
            JSONObject jsonObject = new JSONObject(content);
            JSONObject  jsondata= (JSONObject) jsonObject.get("data");
            JSONObject  jsonbasic=(JSONObject) jsondata.get("basic");
            JSONArray  jsonactors=(JSONArray) jsonbasic.get("actors");
            actors = new ArrayList<>();
            Gson gson = new Gson();
            for(int i = 0;i<jsonactors.length();i++){

                JSONObject actorJson = (JSONObject) jsonactors.get(i);
                //movieJson就是json的电影对象 ---> Movie
                Actors act  = gson.fromJson(actorJson.toString(),Actors.class);
                actors.add(act);
            }
            return actors;
        }

        @Override
        public void onError(Call call, Exception e, int id) {

        }

        @Override
        public void onResponse(Object response, int id) {

        }
    }

}
