package com.roch.fupin.entity;

/**
 * 贫困户、贫困村筛选页面二级村数据的实体类，只有两个字段name、poor_type;
 * 作者：GuGaoJie
 * 时间：2017/4/25/025 9:15
 */
public class PinKunCun {

    private String name; //贫困村的名字
    private String poor_type; //如果poor_type == ‘01’标记为红色，并在name后边加（贫）

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPoor_type() {
        return poor_type;
    }

    public void setPoor_type(String poor_type) {
        this.poor_type = poor_type;
    }
}
