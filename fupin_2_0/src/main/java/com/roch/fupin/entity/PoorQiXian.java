package com.roch.fupin.entity;

/**
 * 作者：ZDS
 * 时间：2016/12/21/021 16:38
 */
public class PoorQiXian extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String id;//
    private String poortypeid;//   贫困属性
    private String povertyalleviation;//   脱贫情况
    private int povertyhousenm;//   总户数
    private int povertypeoplenm;//   总人数
    private String povertyhead;//   旗县负责人
    private String povertyheadoffice;//   旗县负责人职务
    private String adl_cd;//   行政区划
    private String ts;//
    private int disable;//
    private String poortypeidname;//贫困属性名称
    private String povertyalleviationname;//脱贫情况名称
    private int poorhousenm;//贫困户数
    private int poorpeoplenm;//贫困人数
    private String povertypercent;//贫困发生率
    private String povertyareanm;//行政区划

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPoortypeid() {
        return poortypeid;
    }

    public void setPoortypeid(String poortypeid) {
        this.poortypeid = poortypeid;
    }

    public String getPovertyalleviation() {
        return povertyalleviation;
    }

    public void setPovertyalleviation(String povertyalleviation) {
        this.povertyalleviation = povertyalleviation;
    }

    public int getPovertyhousenm() {
        return povertyhousenm;
    }

    public void setPovertyhousenm(int povertyhousenm) {
        this.povertyhousenm = povertyhousenm;
    }

    public int getPovertypeoplenm() {
        return povertypeoplenm;
    }

    public void setPovertypeoplenm(int povertypeoplenm) {
        this.povertypeoplenm = povertypeoplenm;
    }

    public String getPovertyhead() {
        return povertyhead;
    }

    public void setPovertyhead(String povertyhead) {
        this.povertyhead = povertyhead;
    }

    public String getPovertyheadoffice() {
        return povertyheadoffice;
    }

    public void setPovertyheadoffice(String povertyheadoffice) {
        this.povertyheadoffice = povertyheadoffice;
    }

    public String getAdl_cd() {
        return adl_cd;
    }

    public void setAdl_cd(String adl_cd) {
        this.adl_cd = adl_cd;
    }

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }

    public int getDisable() {
        return disable;
    }

    public void setDisable(int disable) {
        this.disable = disable;
    }

    public String getPoortypeidname() {
        return poortypeidname;
    }

    public void setPoortypeidname(String poortypeidname) {
        this.poortypeidname = poortypeidname;
    }

    public String getPovertyalleviationname() {
        return povertyalleviationname;
    }

    public void setPovertyalleviationname(String povertyalleviationname) {
        this.povertyalleviationname = povertyalleviationname;
    }

    public int getPoorhousenm() {
        return poorhousenm;
    }

    public void setPoorhousenm(int poorhousenm) {
        this.poorhousenm = poorhousenm;
    }

    public int getPoorpeoplenm() {
        return poorpeoplenm;
    }

    public void setPoorpeoplenm(int poorpeoplenm) {
        this.poorpeoplenm = poorpeoplenm;
    }

    public String getPovertypercent() {
        return povertypercent;
    }

    public void setPovertypercent(String povertypercent) {
        this.povertypercent = povertypercent;
    }

    public String getPovertyareanm() {
        return povertyareanm;
    }

    public void setPovertyareanm(String povertyareanm) {
        this.povertyareanm = povertyareanm;
    }
}
