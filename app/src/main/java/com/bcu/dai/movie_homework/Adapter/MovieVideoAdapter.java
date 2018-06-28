package com.bcu.dai.movie_homework.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bcu.dai.movie_homework.R;
import com.bcu.dai.movie_homework.View.ASmartimgView;
import com.bcu.dai.movie_homework.json.Video;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.Date;
import java.util.List;

/**
 * Created by dai on 2018/6/27.
 */

public class MovieVideoAdapter  extends BaseAdapter{
    private List<Video> videosList;
    private Context context;
    //标记是否选中
    private int selectedPosition = 0;

    public MovieVideoAdapter(List<Video> videosList, Context context) {
        this.videosList = videosList;
        this.context = context;
    }
    public MovieVideoAdapter(){

    }

    public List<Video> getVideosList() {
        return videosList;
    }
    //获得点击的位置
    public void setSelectedPosition(int position){
        selectedPosition=position;
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
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        Video video = videosList.get(position);
        View view;
        if (convertView == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_movie_video,viewGroup,false);
            holder=new ViewHolder();
            holder.vd_img=view.findViewById(R.id.vd_img);
            holder.vd_name=view.findViewById(R.id.vd_name);
            holder.vd_timelong=view.findViewById(R.id.vd_timelong);
            holder.vd_type=view.findViewById(R.id.vd_type);
            holder.vd_border=view.findViewById(R.id.vd_img_border);
            view.setTag(holder); //holder存储在View
        }else{
            view=convertView;
            holder=(ViewHolder)view.getTag();  //重新获取ViewHolder
        }
        holder.vd_img.setId_(position);
        holder.vd_img.loadImageFromNet(video.getImage());
        holder.vd_name.setText(video.getTitle());
        int n=video.getLength()/60;
        String time ="0"+video.getLength()/60+":"+(video.getLength()-n*60);
        //设置选中改变
        if(selectedPosition==position){
            holder.vd_timelong.setText("播放中");
            holder.vd_name.setTextColor(Color.parseColor("#d24d44"));
            holder.vd_border.setBackgroundColor(Color.parseColor("#d24d44"));
        }else{
            holder.vd_timelong.setText(time);
            holder.vd_name.setTextColor(Color.parseColor("#000000"));
            holder.vd_border.setBackgroundColor(Color.parseColor("#ffffff"));
        }

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
        TextView vd_border;

    }
}
