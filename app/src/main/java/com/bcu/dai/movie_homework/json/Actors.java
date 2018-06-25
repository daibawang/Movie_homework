package com.bcu.dai.movie_homework.json;


import org.litepal.crud.LitePalSupport;

import java.util.List;

/**
 * Created by dai on 2018/6/24.
 */

public class Actors extends LitePalSupport {
        /**
         * actorId : 913378
         * img : http://img31.mtime.cn/ph/2014/09/01/170748.64755972_1280X720X2.jpg
         * name : 范·迪塞尔
         * nameEn : Vin Diesel
         * roleImg : http://img5.mtime.cn/mg/2017/01/05/162613.85098094.jpg
         * roleName : 桑德·凯奇
         */

        private int actorId;
        private String img;
        private String name;
        private String nameEn;
        private String roleImg;
        private String roleName;
        private Other other;
        private long moviedid;
        private int id;
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


    public int getActorId() {
            return actorId;
        }

        public void setActorId(int actorId) {
            this.actorId = actorId;
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

        public String getRoleImg() {
            return roleImg;
        }

        public void setRoleImg(String roleImg) {
            this.roleImg = roleImg;
        }

        public String getRoleName() {
            return roleName;
        }

        public void setRoleName(String roleName) {
            this.roleName = roleName;
        }

}
