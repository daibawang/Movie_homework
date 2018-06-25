package com.bcu.dai.movie_homework.json;

import org.litepal.crud.LitePalSupport;

/**
 * Created by dai on 2018/6/24.
 */

public class Director extends LitePalSupport {
    public int directorId;
    public String img;
    public String name;
    public String nameEn;
    private Other other;
    private int id;
    private long moviedid;
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
    public Other getOther() {
        return other;
    }

    public void setOther(Other other) {
        this.other = other;
    }

    public int getDirectorId() {
        return directorId;
    }

    public void setDirectorId(int directorId) {
        this.directorId = directorId;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }
}
