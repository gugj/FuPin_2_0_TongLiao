package com.roch.fupin.entity;

import java.util.List;

/**
 * 回复信息ResultList实体类
 * 作者：ZDS
 * 时间：2016/12/21/021 16:37
 */
public class HuiFuXinXi_ResultList extends BaseResult {

    private static final long serialVersionUID = 1L;

    private List<HuiFuXinXi> jsondata;

    public List<HuiFuXinXi> getJsondata() {
        return jsondata;
    }

    public void setJsondata(List<HuiFuXinXi> jsondata) {
        this.jsondata = jsondata;
    }
}
