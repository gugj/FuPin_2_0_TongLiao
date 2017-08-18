package com.roch.fupin.entity;

import java.util.List;

/**
 * 展示帮扶记录的实体类
 * 作者：GuGaoJie
 * 时间：2017/5/22/022 17:45
 */
public class ShowBfjl extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String page;
    private String orders;
    private String rows;
    private List<Bfjl_Detail> detail;
    private List<Bfjl_Detail_Img> img;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public List<Bfjl_Detail_Img> getImg() {
        return img;
    }

    public void setImg(List<Bfjl_Detail_Img> img) {
        this.img = img;
    }

    public String getOrders() {
        return orders;
    }

    public void setOrders(String orders) {
        this.orders = orders;
    }

    public String getRows() {
        return rows;
    }

    public void setRows(String rows) {
        this.rows = rows;
    }

    public List<Bfjl_Detail> getDetail() {
        return detail;
    }

    public void setDetail(List<Bfjl_Detail> detail) {
        this.detail = detail;
    }
}
