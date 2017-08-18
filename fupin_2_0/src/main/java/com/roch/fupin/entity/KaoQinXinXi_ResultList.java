package com.roch.fupin.entity;

import java.util.List;

/**
 * 考勤信息ResultList
 * 作者：ZDS
 * 时间：2016/12/21/021 16:37
 */
public class KaoQinXinXi_ResultList extends BaseResult {

    private static final long serialVersionUID = 1L;

    private List<KaoQinXinXi> jsondata;

    public List<KaoQinXinXi> getJsondata() {
        return jsondata;
    }

    public void setJsondata(List<KaoQinXinXi> jsondata) {
        this.jsondata = jsondata;
    }
}
