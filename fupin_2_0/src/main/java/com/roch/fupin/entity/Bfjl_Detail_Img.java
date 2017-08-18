package com.roch.fupin.entity;

/**
 * 作者：GuGaoJie
 * 时间：2017/5/22/022 17:47
 */
public class Bfjl_Detail_Img extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String imagepath;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }
}
