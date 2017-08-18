package com.roch.fupin.entity;

/**
 * 贫困户照片实体
 * @author ZhaoDongShao
 * 2016年5月18日
 */
public class PoorFamilyPhoto extends BaseEntity{

	private static final long serialVersionUID = 1L;
	private String id;
	private String itemid;
	private int itemtype;
	private String imagename;
	private String imagepath;
	private String createtime;
	private String householderid;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getItemid() {
		return itemid;
	}
	public void setItemid(String itemid) {
		this.itemid = itemid;
	}
	public int getItemtype() {
		return itemtype;
	}
	public void setItemtype(int itemtype) {
		this.itemtype = itemtype;
	}
	public String getImagename() {
		return imagename;
	}
	public void setImagename(String imagename) {
		this.imagename = imagename;
	}
	public String getImagepath() {
		return imagepath;
	}
	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getHouseholderid() {
		return householderid;
	}
	public void setHouseholderid(String householderid) {
		this.householderid = householderid;
	}

}
