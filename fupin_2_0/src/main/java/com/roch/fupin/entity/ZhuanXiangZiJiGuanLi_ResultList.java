package com.roch.fupin.entity;

import java.util.List;

/**
 * 专项资金管理ResultList实体类
 * 作者：ZDS
 * 时间：2016/12/21/021 16:37
 */
public class ZhuanXiangZiJiGuanLi_ResultList extends BaseResult {

    private static final long serialVersionUID = 1L;

    private List<ZhuanXiangZiJinGuanLi> jsondata;

    public List<ZhuanXiangZiJinGuanLi> getJsondata() {
        return jsondata;
    }

    public void setJsondata(List<ZhuanXiangZiJinGuanLi> jsondata) {
        this.jsondata = jsondata;
    }
}
