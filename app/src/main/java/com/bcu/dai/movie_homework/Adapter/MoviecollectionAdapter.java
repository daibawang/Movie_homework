package com.bcu.dai.movie_homework.Adapter;


import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bcu.dai.movie_homework.Activity.MovieDetailsActiyity;
import com.bcu.dai.movie_homework.R;
import com.bcu.dai.movie_homework.View.ASmartimgView;
import com.bcu.dai.movie_homework.com.bcu.Model.MovieCollection;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by dai on 2018/6/14.
 */

public class MoviecollectionAdapter extends RecyclerView.Adapter<MoviecollectionAdapter.ViewHolder> {
    private Context context;
    private List<MovieCollection> movieCollectionList;
    private LayoutInflater mInfalter;
    private SQLiteDatabase db;
    private MovieCollection delMovie;
    private  MovieCollection clickMovie;

    public MoviecollectionAdapter(Context context, List<MovieCollection> datas) {
        this.context = context;
        mInfalter=LayoutInflater.from(context);
        this.movieCollectionList = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return  new ViewHolder(mInfalter.inflate(R.layout.collect_item,parent,false));
    }

    //绑定数据
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final MovieCollection movieCollection = this.movieCollectionList.get(position);
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movieCollectionList.remove(position);
                notifyDataSetChanged();
                Log.i("linear", "onClick: "+"删除"+position);
                ContentValues values = new ContentValues();
                values.put("collection", 1);
                delMovie=movieCollectionList.get(position);
                //LitePal.updateAll(MovieCollection.class, values,"movieid=?","delMovie.getMovieId()" );
                LitePal.update(MovieCollection.class, values, delMovie.getId()-1);

                Log.i("name", "onClick: "+delMovie.getId()+"");
                if (null != mOnSwipeListener) {
                    //如果删除时，不使用mAdapter.notifyItemRemoved(pos)，则删除没有动画效果，
                    //且如果想让侧滑菜单同时关闭，需要同时调用 ((CstSwipeDelMenu) holder.itemView).quickClose();
                    //((CstSwipeDelMenu) holder.itemView).quickClose();
                    mOnSwipeListener.onDel(holder.getAdapterPosition());
                }
                //notifyItemRangeChanged(0,holder.getAdapterPosition()+1);
            }
        });
        //设置item点击
        (holder.click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("TAG", "onClick()"+"点击了"+position);
                clickMovie=movieCollectionList.get(position);
                Intent intent = new Intent(context, MovieDetailsActiyity.class);
                intent.putExtra("movieid",clickMovie.getMovieId());
                context.startActivity(intent);
            }
        });
        //置顶
        holder.top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ContentValues values = new ContentValues();
////                delMovie=movieCollectionList.get(position);
//                values.put("id",48 );
////                LitePal.updateAll(MovieCollection.class, values);
//                LitePal.update(MovieCollection.class, values, delMovie.getId()-1);
                if (null!=mOnSwipeListener){
                    mOnSwipeListener.onTop(holder.getAdapterPosition());
                }

            }
        });

        //设置电影名字
        holder.nameText.setText(movieCollection.getTitleCn());
        //设置导演
        holder.director.setText(movieCollection.getDirectorName());
        holder.actor1Text.setText(movieCollection.getActorName1());
        holder.actor2Text.setText(movieCollection.getActorName2());
        //电影时间
        String sytime = context.getResources().getString(R.string.movie_time);
        String time = String.format(sytime, movieCollection.getrYear()+"", movieCollection.getrMonth()+"", movieCollection.getrDay()+"");
        holder.timeText.setText(time);
        //电影图片
        Glide.with(context).load(R.drawable.load).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.imgText);
        holder.imgText.setId_(position);
        holder.imgText.loadImageFromNet(movieCollection.getImg());
    }
    @Override
    public int getItemCount() {return this.movieCollectionList.size();}
    /**
     * 和Activity通信的接口
     */
    public interface onSwipeListener {
        void onDel(int pos);

        void onTop(int pos);
    }
    private onSwipeListener mOnSwipeListener;

    public onSwipeListener getOnDelListener() {
        return mOnSwipeListener;
    }

    public void setOnDelListener(onSwipeListener mOnDelListener) {
        this.mOnSwipeListener = mOnDelListener;
    }



    class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameText;
        TextView director;
        TextView actor1Text;
        TextView actor2Text;
        TextView timeText;
        ASmartimgView imgText;
        Button btnDelete;
        RelativeLayout click;
        Button top;
        public ViewHolder(View itemView) {
            super(itemView);
            nameText= itemView.findViewById(R.id.movieName);
            director=itemView.findViewById(R.id.directorName);
            actor1Text=itemView.findViewById(R.id.actor1);
            actor2Text=itemView.findViewById(R.id.actor2);
            timeText=itemView.findViewById(R.id.sytime);
            imgText= itemView.findViewById(R.id.movieImage);
            btnDelete=itemView.findViewById(R.id.btnDelete);
            click=itemView.findViewById(R.id.click);
            top=itemView.findViewById(R.id.btnTop);
        }
    }

}
