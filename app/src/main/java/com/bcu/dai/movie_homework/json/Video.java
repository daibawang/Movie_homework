package com.bcu.dai.movie_homework.json;

import org.litepal.crud.LitePalSupport;

/**
 * Created by dai on 2018/6/25.
 */

public class Video  extends LitePalSupport {
    private int count;
    private String hightUrl;
    private String img;
    private String title;
    private String url;
    private int videoId;
    private int videoSourceType;
    private int id;
    private long moviedid;
    private int length;
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public long getMoviedid() {
        return moviedid;
    }

    public void setMoviedid(long moviedid) {
        this.moviedid = moviedid;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getHightUrl() {
        return hightUrl;
    }

    public void setHightUrl(String hightUrl) {
        this.hightUrl = hightUrl;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getVideoId() {
        return videoId;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }

    public int getVideoSourceType() {
        return videoSourceType;
    }

    public void setVideoSourceType(int videoSourceType) {
        this.videoSourceType = videoSourceType;
    }

}
