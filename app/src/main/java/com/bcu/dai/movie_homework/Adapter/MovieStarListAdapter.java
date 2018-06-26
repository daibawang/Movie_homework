package com.bcu.dai.movie_homework.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bcu.dai.movie_homework.R;
import com.bcu.dai.movie_homework.View.ASmartimgView;
import com.bcu.dai.movie_homework.json.Actors;
import com.bcu.dai.movie_homework.json.Director;

import java.util.List;

/**
 * Created by dai on 2018/6/26.
 */

public class MovieStarListAdapter extends RecyclerView.Adapter<MovieStarListAdapter.ViewHolder>{
    private Context context;
    private LayoutInflater mInfalter;
    private List<Actors> actorsList;
    private Director director;

    public MovieStarListAdapter(Context context, List<Actors> actorsList) {
        this.context = context;
        mInfalter=LayoutInflater.from(context);
        this.actorsList = actorsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mInfalter.inflate(R.layout.item_movie_director,parent,false));
    }
    //绑定数据
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Actors actors = this.actorsList.get(position);
        holder.rolename.setText(actors.getRoleName());
        holder.truename.setText(actors.getName()+"\n"+actors.getNameEn());
        holder.photo.setId_(position);
        holder.photo.loadImageFromNet(actors.getImg());
    }

    @Override
    public int getItemCount() {
        return this.actorsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ASmartimgView photo;
        TextView truename;
        TextView rolename;
        public ViewHolder(View itemView) {
            super(itemView);
            photo = itemView.findViewById(R.id.iv_movie_star);
            truename=itemView.findViewById(R.id.tv_real_name);
            rolename=itemView.findViewById(R.id.tv_fake_name);
        }
    }
}
