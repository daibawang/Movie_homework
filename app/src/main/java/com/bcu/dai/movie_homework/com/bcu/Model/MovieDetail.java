package com.bcu.dai.movie_homework.com.bcu.Model;

import org.litepal.crud.LitePalSupport;

/**
 * Created by dai on 2018/6/17.
 */

public class MovieDetail extends LitePalSupport {

    private String actorName1;
    private String actorName2;
    private String directorName;
    //private String commonSpecial;
    private String img;
    private boolean is3D;
    private boolean isIMAX;
    private boolean isIMAX3D;
    private double length;
    private long movieId;
    private int rDay;
    private int rMonth;
    private int rYear;
    //评分
    private double ratingFinal;
    //英文名
    private String  titleEn;
    private String titleCn;
    private String type;
    //gxing

}
