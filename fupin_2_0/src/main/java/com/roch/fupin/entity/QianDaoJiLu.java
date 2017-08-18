package com.roch.fupin.entity;

/**
 * 作者：GuGaoJie
 * 时间：2017/5/27/027 10:23
 */
public class QianDaoJiLu extends BaseEntity{

    private static final long serialVersionUID = 1L;

    private String signlocation;
    private String count;
    private String signdatetext;
    private String longitude;
    private String latitude;
    private String rows;
    private String id;
    private String usertype;
    private String signdate;
    private String ts;
    private String orders;
    private String userid;
    private String page;

    public String getSignlocation() {
        return signlocation;
    }

    public void setSignlocation(String signlocation) {
        this.signlocation = signlocation;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getSigndatetext() {
        return signdatetext;
    }

    public void setSigndatetext(String signdatetext) {
        this.signdatetext = signdatetext;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getRows() {
        return rows;
    }

    public void setRows(String rows) {
        this.rows = rows;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getSigndate() {
        return signdate;
    }

    public void setSigndate(String signdate) {
        this.signdate = signdate;
    }

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }

    public String getOrders() {
        return orders;
    }

    public void setOrders(String orders) {
        this.orders = orders;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }
}
