package com.roch.fupin.entity;

/**
 * 考勤信息实体类
 * 作者：ZDS
 * 时间：2016/12/21/021 16:38
 */
public class KaoQinXinXi extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String att_personname;//   考勤对象
    private String id;//   考勤id
    private String att_mobilephone;//   电话
    private int att_persontype;//   对象类型
    private int att_count;//   考勤次数
    private int att_relcount;//   实际考勤次数
    private String att_persontypename;//   对象类型名称
    public String adl_cd;
    public String orders;
    public String helpyear;
    public String rows;
    public String page;
    public String personid;
    private String att_valcount;//有效签到次数
    private String adl_nm;//所属行政层级

    private String poorobject;//帮扶对象
    private String zhutiname;//帮扶人姓名
    private String zhutiphone;//帮扶人电话
    private String helpdate;//帮扶日期
    private String helptype;//

    public String april_sixth;
    public String january_twenty_seven;
    public String may_thirteenth;
    public String september_twenty_seven;
    public String march_thirteenth;
    public String february_twenty_seven;
    public String july_twenty_seven;
    public String september_sixth;
    public String june_twenty;
    public String june_sixth;
    public String january_twenty;
    public String july_thirteenth;
    public String january_thirteenth;
    public String march_twenty_seven;
    public String october_thirteenth;
    public String december_twenty_seven;
    public String may_twenty;
    public String september_twenty;
    public String october_twenty;
    public String april_thirteenth;
    public String february_sixth;
    public String november_sixth;
    public String january_sixth;
    public String aguest_thirteenth;
    public String october_twenty_seven;
    public String february_twenty;
    public String november_thirteenth;
    public String aguest_sixth;
    public String february_thirteenth;
    public String aguest_twenty;
    public String june_thirteenth;
    public String march_sixth;
    public String march_twenty;
    public String june_twenty_seven;
    public String aguest_twenty_seven;
    public String november_twenty_seven;
    public String july_twenty;
    public String december_sixth;
    public String july_sixth;
    public String november_twenty;
    public String may_twenty_seven;
    public String may_sixth;
    public String october_sixth;
    public String april_twenty_seven;
    public String december_thirteenth;
    public String april_twenty;
    public String september_thirteenth;
    public String december_twenty;

    public String getHelptype() {
        return helptype;
    }

    public void setHelptype(String helptype) {
        this.helptype = helptype;
    }

    public String getPoorobject() {
        return poorobject;
    }

    public void setPoorobject(String poorobject) {
        this.poorobject = poorobject;
    }

    public String getHelpdate() {
        return helpdate;
    }

    public void setHelpdate(String helpdate) {
        this.helpdate = helpdate;
    }

    public String getZhutiname() {
        return zhutiname;
    }

    public void setZhutiname(String zhutiname) {
        this.zhutiname = zhutiname;
    }

    public String getZhutiphone() {
        return zhutiphone;
    }

    public void setZhutiphone(String zhutiphone) {
        this.zhutiphone = zhutiphone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAtt_valcount() {
        return att_valcount;
    }

    public void setAtt_valcount(String att_valcount) {
        this.att_valcount = att_valcount;
    }

    public String getAdl_nm() {
        return adl_nm;
    }

    public void setAdl_nm(String adl_nm) {
        this.adl_nm = adl_nm;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getAdl_cd() {
        return adl_cd;
    }

    public void setAdl_cd(String adl_cd) {
        this.adl_cd = adl_cd;
    }

    public String getApril_sixth() {
        return april_sixth;
    }

    public void setApril_sixth(String april_sixth) {
        this.april_sixth = april_sixth;
    }

    public String getJanuary_twenty_seven() {
        return january_twenty_seven;
    }

    public void setJanuary_twenty_seven(String january_twenty_seven) {
        this.january_twenty_seven = january_twenty_seven;
    }

    public String getMay_thirteenth() {
        return may_thirteenth;
    }

    public void setMay_thirteenth(String may_thirteenth) {
        this.may_thirteenth = may_thirteenth;
    }

    public String getSeptember_twenty_seven() {
        return september_twenty_seven;
    }

    public void setSeptember_twenty_seven(String september_twenty_seven) {
        this.september_twenty_seven = september_twenty_seven;
    }

    public String getMarch_thirteenth() {
        return march_thirteenth;
    }

    public void setMarch_thirteenth(String march_thirteenth) {
        this.march_thirteenth = march_thirteenth;
    }

    public String getFebruary_twenty_seven() {
        return february_twenty_seven;
    }

    public void setFebruary_twenty_seven(String february_twenty_seven) {
        this.february_twenty_seven = february_twenty_seven;
    }

    public String getJuly_twenty_seven() {
        return july_twenty_seven;
    }

    public void setJuly_twenty_seven(String july_twenty_seven) {
        this.july_twenty_seven = july_twenty_seven;
    }

    public String getSeptember_sixth() {
        return september_sixth;
    }

    public void setSeptember_sixth(String september_sixth) {
        this.september_sixth = september_sixth;
    }

    public String getHelpyear() {
        return helpyear;
    }

    public void setHelpyear(String helpyear) {
        this.helpyear = helpyear;
    }

    public String getJune_twenty() {
        return june_twenty;
    }

    public void setJune_twenty(String june_twenty) {
        this.june_twenty = june_twenty;
    }

    public String getJune_sixth() {
        return june_sixth;
    }

    public void setJune_sixth(String june_sixth) {
        this.june_sixth = june_sixth;
    }

    public String getJanuary_twenty() {
        return january_twenty;
    }

    public void setJanuary_twenty(String january_twenty) {
        this.january_twenty = january_twenty;
    }

    public String getJuly_thirteenth() {
        return july_thirteenth;
    }

    public void setJuly_thirteenth(String july_thirteenth) {
        this.july_thirteenth = july_thirteenth;
    }

    public String getJanuary_thirteenth() {
        return january_thirteenth;
    }

    public void setJanuary_thirteenth(String january_thirteenth) {
        this.january_thirteenth = january_thirteenth;
    }

    public String getMarch_twenty_seven() {
        return march_twenty_seven;
    }

    public void setMarch_twenty_seven(String march_twenty_seven) {
        this.march_twenty_seven = march_twenty_seven;
    }

    public String getOctober_thirteenth() {
        return october_thirteenth;
    }

    public void setOctober_thirteenth(String october_thirteenth) {
        this.october_thirteenth = october_thirteenth;
    }

    public String getDecember_twenty_seven() {
        return december_twenty_seven;
    }

    public void setDecember_twenty_seven(String december_twenty_seven) {
        this.december_twenty_seven = december_twenty_seven;
    }

    public String getMay_twenty() {
        return may_twenty;
    }

    public void setMay_twenty(String may_twenty) {
        this.may_twenty = may_twenty;
    }

    public String getSeptember_twenty() {
        return september_twenty;
    }

    public void setSeptember_twenty(String september_twenty) {
        this.september_twenty = september_twenty;
    }

    public String getOctober_twenty() {
        return october_twenty;
    }

    public void setOctober_twenty(String october_twenty) {
        this.october_twenty = october_twenty;
    }

    public String getApril_thirteenth() {
        return april_thirteenth;
    }

    public void setApril_thirteenth(String april_thirteenth) {
        this.april_thirteenth = april_thirteenth;
    }

    public String getFebruary_sixth() {
        return february_sixth;
    }

    public void setFebruary_sixth(String february_sixth) {
        this.february_sixth = february_sixth;
    }

    public String getNovember_sixth() {
        return november_sixth;
    }

    public void setNovember_sixth(String november_sixth) {
        this.november_sixth = november_sixth;
    }

    public String getRows() {
        return rows;
    }

    public void setRows(String rows) {
        this.rows = rows;
    }

    public String getJanuary_sixth() {
        return january_sixth;
    }

    public void setJanuary_sixth(String january_sixth) {
        this.january_sixth = january_sixth;
    }

    public String getAguest_thirteenth() {
        return aguest_thirteenth;
    }

    public void setAguest_thirteenth(String aguest_thirteenth) {
        this.aguest_thirteenth = aguest_thirteenth;
    }

    public String getOctober_twenty_seven() {
        return october_twenty_seven;
    }

    public void setOctober_twenty_seven(String october_twenty_seven) {
        this.october_twenty_seven = october_twenty_seven;
    }

    public String getFebruary_twenty() {
        return february_twenty;
    }

    public void setFebruary_twenty(String february_twenty) {
        this.february_twenty = february_twenty;
    }

    public String getNovember_thirteenth() {
        return november_thirteenth;
    }

    public void setNovember_thirteenth(String november_thirteenth) {
        this.november_thirteenth = november_thirteenth;
    }

    public String getAguest_sixth() {
        return aguest_sixth;
    }

    public void setAguest_sixth(String aguest_sixth) {
        this.aguest_sixth = aguest_sixth;
    }

    public String getFebruary_thirteenth() {
        return february_thirteenth;
    }

    public void setFebruary_thirteenth(String february_thirteenth) {
        this.february_thirteenth = february_thirteenth;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getAguest_twenty() {
        return aguest_twenty;
    }

    public void setAguest_twenty(String aguest_twenty) {
        this.aguest_twenty = aguest_twenty;
    }

    public String getJune_thirteenth() {
        return june_thirteenth;
    }

    public void setJune_thirteenth(String june_thirteenth) {
        this.june_thirteenth = june_thirteenth;
    }

    public String getMarch_sixth() {
        return march_sixth;
    }

    public void setMarch_sixth(String march_sixth) {
        this.march_sixth = march_sixth;
    }

    public String getMarch_twenty() {
        return march_twenty;
    }

    public void setMarch_twenty(String march_twenty) {
        this.march_twenty = march_twenty;
    }

    public String getJune_twenty_seven() {
        return june_twenty_seven;
    }

    public void setJune_twenty_seven(String june_twenty_seven) {
        this.june_twenty_seven = june_twenty_seven;
    }

    public String getPersonid() {
        return personid;
    }

    public void setPersonid(String personid) {
        this.personid = personid;
    }

    public String getAguest_twenty_seven() {
        return aguest_twenty_seven;
    }

    public void setAguest_twenty_seven(String aguest_twenty_seven) {
        this.aguest_twenty_seven = aguest_twenty_seven;
    }

    public String getNovember_twenty_seven() {
        return november_twenty_seven;
    }

    public void setNovember_twenty_seven(String november_twenty_seven) {
        this.november_twenty_seven = november_twenty_seven;
    }

    public String getOrders() {
        return orders;
    }

    public void setOrders(String orders) {
        this.orders = orders;
    }

    public String getJuly_twenty() {
        return july_twenty;
    }

    public void setJuly_twenty(String july_twenty) {
        this.july_twenty = july_twenty;
    }

    public String getDecember_sixth() {
        return december_sixth;
    }

    public void setDecember_sixth(String december_sixth) {
        this.december_sixth = december_sixth;
    }

    public String getJuly_sixth() {
        return july_sixth;
    }

    public void setJuly_sixth(String july_sixth) {
        this.july_sixth = july_sixth;
    }

    public String getNovember_twenty() {
        return november_twenty;
    }

    public void setNovember_twenty(String november_twenty) {
        this.november_twenty = november_twenty;
    }

    public String getMay_twenty_seven() {
        return may_twenty_seven;
    }

    public void setMay_twenty_seven(String may_twenty_seven) {
        this.may_twenty_seven = may_twenty_seven;
    }

    public String getMay_sixth() {
        return may_sixth;
    }

    public void setMay_sixth(String may_sixth) {
        this.may_sixth = may_sixth;
    }

    public String getOctober_sixth() {
        return october_sixth;
    }

    public void setOctober_sixth(String october_sixth) {
        this.october_sixth = october_sixth;
    }

    public String getApril_twenty_seven() {
        return april_twenty_seven;
    }

    public void setApril_twenty_seven(String april_twenty_seven) {
        this.april_twenty_seven = april_twenty_seven;
    }

    public String getDecember_thirteenth() {
        return december_thirteenth;
    }

    public void setDecember_thirteenth(String december_thirteenth) {
        this.december_thirteenth = december_thirteenth;
    }

    public String getApril_twenty() {
        return april_twenty;
    }

    public void setApril_twenty(String april_twenty) {
        this.april_twenty = april_twenty;
    }

    public String getSeptember_thirteenth() {
        return september_thirteenth;
    }

    public void setSeptember_thirteenth(String september_thirteenth) {
        this.september_thirteenth = september_thirteenth;
    }

    public String getDecember_twenty() {
        return december_twenty;
    }

    public void setDecember_twenty(String december_twenty) {
        this.december_twenty = december_twenty;
    }

    public String getAtt_personname() {
        return att_personname;
    }

    public void setAtt_personname(String att_personname) {
        this.att_personname = att_personname;
    }

    public String getAtt_mobilephone() {
        return att_mobilephone;
    }

    public void setAtt_mobilephone(String att_mobilephone) {
        this.att_mobilephone = att_mobilephone;
    }

    public int getAtt_persontype() {
        return att_persontype;
    }

    public void setAtt_persontype(int att_persontype) {
        this.att_persontype = att_persontype;
    }

    public int getAtt_count() {
        return att_count;
    }

    public void setAtt_count(int att_count) {
        this.att_count = att_count;
    }

    public int getAtt_relcount() {
        return att_relcount;
    }

    public void setAtt_relcount(int att_relcount) {
        this.att_relcount = att_relcount;
    }

    public String getAtt_persontypename() {
        return att_persontypename;
    }

    public void setAtt_persontypename(String att_persontypename) {
        this.att_persontypename = att_persontypename;
    }
}
