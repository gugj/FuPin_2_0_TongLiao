package com.roch.fupin.entity;

import java.util.List;

/**
 * 作者：ZDS
 * 时间：2017/1/19/019 10:43
 */
public class PoorFamilyPeople_ResultList extends BaseResult {

    private static final long serialVersionUID = 1L;

    private List<PoorFamilyPeople> jsondata;

    public List<PoorFamilyPeople> getJsondata() {
        return jsondata;
    }

    public void setJsondata(List<PoorFamilyPeople> jsondata) {
        this.jsondata = jsondata;
    }
}
