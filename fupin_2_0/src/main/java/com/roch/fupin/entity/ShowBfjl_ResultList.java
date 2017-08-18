package com.roch.fupin.entity;

import java.util.List;

/**
 * 展示帮扶记录的ResultList
 * 作者：GuGaoJie
 * 时间：2017/5/22/022 17:43
 */
public class ShowBfjl_ResultList extends BaseResult{

    private static final long serialVersionUID = 1L;

    private List<ShowBfjl> jsondata;

    public List<ShowBfjl> getJsondata() {
        return jsondata;
    }

    public void setJsondata(List<ShowBfjl> jsondata) {
        this.jsondata = jsondata;
    }
}
