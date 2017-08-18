package com.roch.fupin.entity;

import java.util.List;

/**
 * 作者：ZDS
 * 时间：2017/1/19/019 10:24
 */
public class PoorFamilyBase_ResultList extends BaseResult {

    private static final long serialVersionUID = 1L;

    private List<PoorFamilyBase> jsondata;

    public List<PoorFamilyBase> getJsondata() {
        return jsondata;
    }

    public void setJsondata(List<PoorFamilyBase> jsondata) {
        this.jsondata = jsondata;
    }
}
