package com.roch.fupin.entity;

import java.util.List;

/**
 * 专项扶贫项目管理ResultList
 * 作者：ZDS
 * 时间：2016/12/21/021 16:37
 */
public class ZhuanXiangFuPinXiangMuGuanLi_ResultList extends BaseResult {

    private static final long serialVersionUID = 1L;

    private List<ZhuanXiangFuPinXiangMuGuanLi> jsondata;

    public List<ZhuanXiangFuPinXiangMuGuanLi> getJsondata() {
        return jsondata;
    }

    public void setJsondata(List<ZhuanXiangFuPinXiangMuGuanLi> jsondata) {
        this.jsondata = jsondata;
    }
}
