package com.bcu.dai.movie_homework.json;

import org.litepal.crud.LitePalSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dai on 2018/6/24.
 */

public class Other extends LitePalSupport {

    public String img;
    public boolean is3D;
    public boolean isDMAX;
    public boolean isEggHunt;
    public boolean isFilter;
    public boolean isIMAX;
    public boolean isIMAX3D;
    //上映时间地区
    private String releaseArea;
    private String releaseDate;
    private String mins;
    public long movieId;
    public String name;
    public String nameEn;
    public String story;
    private Video vedio;
    private int id;
    private List<Actors> actorsList = new ArrayList<Actors>();
    private Director director;

    public String getReleaseArea() {
        return releaseArea;
    }

    public void setReleaseArea(String releaseArea) {
        this.releaseArea = releaseArea;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getMins() {
        return mins;
    }

    public void setMins(String mins) {
        this.mins = mins;
    }

    public List<Actors> getActorsList() {
        return actorsList;
    }

    public void setActorsList(List<Actors> actorsList) {
        this.actorsList = actorsList;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Video getVedio() {
        return vedio;
    }

    public void setVedio(Video vedio) {
        this.vedio = vedio;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public boolean isIs3D() {
        return is3D;
    }

    public void setIs3D(boolean is3D) {
        this.is3D = is3D;
    }

    public boolean isDMAX() {
        return isDMAX;
    }

    public void setDMAX(boolean DMAX) {
        isDMAX = DMAX;
    }

    public boolean isEggHunt() {
        return isEggHunt;
    }

    public void setEggHunt(boolean eggHunt) {
        isEggHunt = eggHunt;
    }

    public boolean isFilter() {
        return isFilter;
    }

    public void setFilter(boolean filter) {
        isFilter = filter;
    }

    public boolean isIMAX() {
        return isIMAX;
    }

    public void setIMAX(boolean IMAX) {
        isIMAX = IMAX;
    }

    public boolean isIMAX3D() {
        return isIMAX3D;
    }

    public void setIMAX3D(boolean IMAX3D) {
        isIMAX3D = IMAX3D;
    }

    public long getMovieId() {
        return movieId;
    }

    public void setMovieId(long movieId) {
        this.movieId = movieId;
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

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }
}
