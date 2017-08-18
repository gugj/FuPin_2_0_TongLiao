package com.roch.fupin.entity;

/**
 * 贫困户台账信息实体
 * @author wf
 */
public class PoorFamilyAccountPrint extends BaseEntity {

	private static final long serialVersionUID = 1L;
	private String householderid;//   户主ID
	private String incomeyear;//   收入年份
	private double gongzi;//工资性收入
	private double shengchan;//生产经营性收入
	private double caichan;//财产性收入
	private double jihuashengyu;//计划生育金
	private double dibao;//   低保
	private double wubao;//   五保
	private double yanglao;//   养老保险
	private double shengtai;//   生态补偿
	private double qitazhuanyi;//   其他转移
	private double zhichu;//生产经营性支出
	private double zhuanyitotal;//转移性收入总计
	private double total;//总收入
	private double total_perp;//人均收入

	public String getHouseholderid() {
		return householderid;
	}

	public void setHouseholderid(String householderid) {
		this.householderid = householderid;
	}

	public String getIncomeyear() {
		return incomeyear;
	}

	public void setIncomeyear(String incomeyear) {
		this.incomeyear = incomeyear;
	}

	public double getGongzi() {
		return gongzi;
	}

	public void setGongzi(double gongzi) {
		this.gongzi = gongzi;
	}

	public double getShengchan() {
		return shengchan;
	}

	public void setShengchan(double shengchan) {
		this.shengchan = shengchan;
	}

	public double getCaichan() {
		return caichan;
	}

	public void setCaichan(double caichan) {
		this.caichan = caichan;
	}

	public double getJihuashengyu() {
		return jihuashengyu;
	}

	public void setJihuashengyu(double jihuashengyu) {
		this.jihuashengyu = jihuashengyu;
	}

	public double getDibao() {
		return dibao;
	}

	public void setDibao(double dibao) {
		this.dibao = dibao;
	}

	public double getWubao() {
		return wubao;
	}

	public void setWubao(double wubao) {
		this.wubao = wubao;
	}

	public double getYanglao() {
		return yanglao;
	}

	public void setYanglao(double yanglao) {
		this.yanglao = yanglao;
	}

	public double getShengtai() {
		return shengtai;
	}

	public void setShengtai(double shengtai) {
		this.shengtai = shengtai;
	}

	public double getQitazhuanyi() {
		return qitazhuanyi;
	}

	public void setQitazhuanyi(double qitazhuanyi) {
		this.qitazhuanyi = qitazhuanyi;
	}

	public double getZhichu() {
		return zhichu;
	}

	public void setZhichu(double zhichu) {
		this.zhichu = zhichu;
	}

	public double getZhuanyitotal() {
		return zhuanyitotal;
	}

	public void setZhuanyitotal(double zhuanyitotal) {
		this.zhuanyitotal = zhuanyitotal;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getTotal_perp() {
		return total_perp;
	}

	public void setTotal_perp(double total_perp) {
		this.total_perp = total_perp;
	}
}
