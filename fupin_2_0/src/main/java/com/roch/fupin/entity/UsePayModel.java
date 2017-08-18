package com.roch.fupin.entity;

import java.util.Date;

//资金使用
public class UsePayModel extends BaseEntity {

	private static final long serialVersionUID = 1L;
	private String depName;// 部委名称
	private String investTotal;// 投资金额
	private String zszj;// 省财政金额
	private String sjzj;// 市财政金额
	private String zcpt;// 自筹金额
	private String approveAmount;// 市财政批准金额
	private String paidAmount;// 市财政已拨付金额
	private String paymentRatio;// 支付比例
	private Date grantDate;// 拨付时间

	private String adl_cd;

	public String getAdl_cd() {
		return adl_cd;
	}

	public void setAdl_cd(String adl_cd) {
		this.adl_cd = adl_cd;
	}

	public String getDepName() {
		return depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

	public String getInvestTotal() {
		return investTotal;
	}

	public void setInvestTotal(String investTotal) {
		this.investTotal = investTotal;
	}

	public String getApproveAmount() {
		return approveAmount;
	}

	public void setApproveAmount(String approveAmount) {
		this.approveAmount = approveAmount;
	}

	public String getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(String paidAmount) {
		this.paidAmount = paidAmount;
	}

	public String getPaymentRatio() {
		return paymentRatio;
	}

	public void setPaymentRatio(String paymentRatio) {
		this.paymentRatio = paymentRatio;
	}

	public Date getGrantDate() {
		return grantDate;
	}

	public void setGrantDate(Date grantDate) {
		this.grantDate = grantDate;
	}

	public String getZszj() {
		return zszj;
	}

	public void setZszj(String zszj) {
		this.zszj = zszj;
	}

	public String getSjzj() {
		return sjzj;
	}

	public void setSjzj(String sjzj) {
		this.sjzj = sjzj;
	}

	public String getZcpt() {
		return zcpt;
	}

	public void setZcpt(String zcpt) {
		this.zcpt = zcpt;
	}
}
