package com.roch.fupin.entity;

/**
 * @author ZhaoDongShao 2016年5月26日
 */
public class NoticBoard extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String id;// 公告ID
	private String typeid;// 公告类型ID,字典表 公告、信息宣传
	private String title;// 公告标题
	private String content;// 公告内容
	private String createtime;// 发布时间
	private String createuserid;// 发布人ID

	private String userid;
	private int ifread;// 是否已查看
	private java.util.Date readtime;// 查看时间
	private String username;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTypeid() {
		return this.typeid;
	}

	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getCreateuserid() {
		return this.createuserid;
	}

	public void setCreateuserid(String createuserid) {
		this.createuserid = createuserid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public int getIfread() {
		return ifread;
	}

	public void setIfread(int ifread) {
		this.ifread = ifread;
	}

	public java.util.Date getReadtime() {
		return readtime;
	}

	public void setReadtime(java.util.Date readtime) {
		this.readtime = readtime;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
