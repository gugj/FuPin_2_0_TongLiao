package com.roch.fupin.entity;

import java.util.List;

/**
 * 作者：ZDS
 * 时间：2016/12/21/021 16:37
 */
public class PoorQiXian_ResultList extends BaseResult {

    private static final long serialVersionUID = 1L;

    private List<PoorQiXian> jsondata;

    public List<PoorQiXian> getJsondata() {
        return jsondata;
    }

    public void setJsondata(List<PoorQiXian> jsondata) {
        this.jsondata = jsondata;
    }
}
