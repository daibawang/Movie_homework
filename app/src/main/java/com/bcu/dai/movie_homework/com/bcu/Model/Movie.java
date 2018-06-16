package com.bcu.dai.movie_homework.com.bcu.Model;

/**
 * Created by byy on 2018/6/8.
 */

public class Movie {
    private String actorName1;
    private String actorName2;
    private String directorName;
    private String commonSpecial;
    private String img;
    private boolean is3D;
    private boolean isIMAX;
    private boolean isIMAX3D;
    private double length;
    private long movieId;
    private int rDay;
    private int rMonth;
    private int rYear;
    private double ratingFinal;
    private String titleCn;
    private String type;
    public String getActorName1() {
        return actorName1;
    }

    public void setActorName1(String actorName1) {
        this.actorName1 = actorName1;
    }

    public String getActorName2() {
        return actorName2;
    }

    public void setActorName2(String actorName2) {
        this.actorName2 = actorName2;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public String getCommonSpecial() {
        return commonSpecial;
    }

    public void setCommonSpecial(String commonSpecial) {
        this.commonSpecial = commonSpecial;
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

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public long getMovieId() {
        return movieId;
    }

    public void setMovieId(long movieId) {
        this.movieId = movieId;
    }

    public int getrDay() {
        return rDay;
    }

    public void setrDay(int rDay) {
        this.rDay = rDay;
    }

    public int getrMonth() {
        return rMonth;
    }

    public void setrMonth(int rMonth) {
        this.rMonth = rMonth;
    }

    public int getrYear() {
        return rYear;
    }

    public void setrYear(int rYear) {
        this.rYear = rYear;
    }

    public double getRatingFinal() {
        return ratingFinal;
    }

    public void setRatingFinal(double ratingFinal) {
        this.ratingFinal = ratingFinal;
    }

    public String getTitleCn() {
        return titleCn;
    }

    public void setTitleCn(String titleCn) {
        this.titleCn = titleCn;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "actorName1='" + actorName1 + '\'' +
                ", actorName2='" + actorName2 + '\'' +
                ", directorName='" + directorName + '\'' +
                ", commonSpecial='" + commonSpecial + '\'' +
                ", img='" + img + '\'' +
                ", is3D=" + is3D +
                ", isIMAX=" + isIMAX +
                ", isIMAX3D=" + isIMAX3D +
                ", length=" + length +
                ", movieId=" + movieId +
                ", rDay=" + rDay +
                ", rMonth=" + rMonth +
                ", rYear=" + rYear +
                ", ratingFinal=" + ratingFinal +
                ", titleCn='" + titleCn + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
