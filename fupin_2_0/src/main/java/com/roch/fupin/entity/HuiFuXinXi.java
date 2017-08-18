package com.roch.fupin.entity;

/**
 * 回复信息实体类,里面含有6个字段：id、recordid、userid、detail、ts、replyname
 * 作者：ZDS
 * 时间：2016/12/21/021 16:38
 */
public class HuiFuXinXi extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String id;//   id
    private String recordid;//   反馈id
    private String userid;//   用户id
    private String detail;//   回复内容
    private String ts;//   时间

    private String replyname;//回复用户名

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRecordid() {
        return recordid;
    }

    public void setRecordid(String recordid) {
        this.recordid = recordid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }

    public String getReplyname() {
        return replyname;
    }

    public void setReplyname(String replyname) {
        this.replyname = replyname;
    }
}
