package com.bcu.dai.movie_homework.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bcu.dai.movie_homework.R;
import com.bcu.dai.movie_homework.View.ASmartimgView;
import com.bcu.dai.movie_homework.json.Video;

import java.util.Date;
import java.util.List;

/**
 * Created by dai on 2018/6/27.
 */

public class MovieVideoAdapter  extends BaseAdapter{
    private List<Video> videosList;
    private Context context;

    public MovieVideoAdapter(List<Video> videosList, Context context) {
        this.videosList = videosList;
        this.context = context;
    }
    public MovieVideoAdapter(){

    }

    @Override
    public int getCount() {
        return videosList.size();
    }

    @Override
    public Object getItem(int i) {
        return videosList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View cellView = null;
        ViewHolder holder;
        Video video = videosList.get(position);
        if (view == null){
            cellView = LayoutInflater.from(context).inflate(R.layout.item_movie_video,viewGroup,false);
            holder=new ViewHolder();
            holder.vd_img=view.findViewById(R.id.vd_img);
            holder.vd_name=view.findViewById(R.id.vd_name);
            holder.vd_timelong=view.findViewById(R.id.vd_timelong);
            holder.vd_type=view.findViewById(R.id.vd_type);
            view.setTag(holder); //holder存储在View
        }else{
            cellView = view;
            holder=(ViewHolder)view.getTag();  //重新获取ViewHolder
        }
        holder.vd_img.setId_(position);
        holder.vd_img.loadImageFromNet(video.getImg());
        holder.vd_name.setText(video.getTitle());
        //需要设置点击改变
        int n=video.getLength()/60;
        String time ="0"+video.getLength()/60+":"+(video.getLength()-n*60);
        holder.vd_timelong.setText(time);
        String vdtype="";
        if(video.getType()==0){
            vdtype="预告片";
        }else if(video.getType()==1){
            vdtype="人物片段";
        }else if (video.getType()==2){
            vdtype="制作特辑";
        }else if (video.getType()==3){
            vdtype="未知";
        }else if (video.getType()==4){
            vdtype="采访";
        }else if (video.getType()==5){
            vdtype="MV";
        }
        holder.vd_type.setText(vdtype);
        return view;
    }
    class ViewHolder{
        ASmartimgView vd_img;
        TextView vd_timelong;
        TextView vd_name;
        TextView vd_type;

    }
}
