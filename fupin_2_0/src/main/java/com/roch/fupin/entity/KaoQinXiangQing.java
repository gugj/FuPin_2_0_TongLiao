package com.roch.fupin.entity;

/**
 * 考勤信息详情表格实体类
 * 作者：ZDS
 * 时间：2016/12/21/021 16:38
 */
public class KaoQinXiangQing extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String name;
    private String first;
    private String second;
    private String third;
    private String forth;

    public KaoQinXiangQing(String name,String first, String second, String third, String forth) {
        this.name = name;
        this.first = first;
        this.second = second;
        this.third = third;
        this.forth = forth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getSecond() {
        return second;
    }

    public void setSecond(String second) {
        this.second = second;
    }

    public String getThird() {
        return third;
    }

    public void setThird(String third) {
        this.third = third;
    }

    public String getForth() {
        return forth;
    }

    public void setForth(String forth) {
        this.forth = forth;
    }
}
