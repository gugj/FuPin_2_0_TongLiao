package com.roch.fupin.entity;

/**
 * 工作日志实体类，里面包含20个字段：zhutiid、poorobject、imageHeight......
 * @author ZhaoDongShao 2016年5月26日
 */
public class GongZuoRiZhi extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String zhutiid;
	private String poorobject;
	private int imageHeight;
	private String zhutiname;
	private int rows;
	private int helptype;
	private String adl_cd;
	private String endtime;
	private int page;
	private int imageWidth;
	private String helptitle;
	private String orders;
	private String hvid;
	private String helpdate;
	private String id;
	private String helpdetail;
	private String imagePath;
	private String starttime;
	private String location;
	private String zhutiphone;

	public String getZhutiid() {
		return zhutiid;
	}

	public void setZhutiid(String zhutiid) {
		this.zhutiid = zhutiid;
	}

	public String getPoorobject() {
		return poorobject;
	}

	public void setPoorobject(String poorobject) {
		this.poorobject = poorobject;
	}

	public int getImageHeight() {
		return imageHeight;
	}

	public void setImageHeight(int imageHeight) {
		this.imageHeight = imageHeight;
	}

	public String getZhutiname() {
		return zhutiname;
	}

	public void setZhutiname(String zhutiname) {
		this.zhutiname = zhutiname;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getHelptype() {
		return helptype;
	}

	public void setHelptype(int helptype) {
		this.helptype = helptype;
	}

	public String getAdl_cd() {
		return adl_cd;
	}

	public void setAdl_cd(String adl_cd) {
		this.adl_cd = adl_cd;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getImageWidth() {
		return imageWidth;
	}

	public void setImageWidth(int imageWidth) {
		this.imageWidth = imageWidth;
	}

	public String getHelptitle() {
		return helptitle;
	}

	public void setHelptitle(String helptitle) {
		this.helptitle = helptitle;
	}

	public String getOrders() {
		return orders;
	}

	public void setOrders(String orders) {
		this.orders = orders;
	}

	public String getHvid() {
		return hvid;
	}

	public void setHvid(String hvid) {
		this.hvid = hvid;
	}

	public String getHelpdate() {
		return helpdate;
	}

	public void setHelpdate(String helpdate) {
		this.helpdate = helpdate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHelpdetail() {
		return helpdetail;
	}

	public void setHelpdetail(String helpdetail) {
		this.helpdetail = helpdetail;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getZhutiphone() {
		return zhutiphone;
	}

	public void setZhutiphone(String zhutiphone) {
		this.zhutiphone = zhutiphone;
	}
}
