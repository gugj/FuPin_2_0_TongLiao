package com.roch.fupin.entity;

/**
 * 当前用户权限实体类，继承自BaseEntity，父类里面仅只有如下一个方法： 把json字符串通过gson转换成实体类，实现了Serializable序列化接口
 * @author ZhaoDongShao 2016年7月2日
 */
public class Sroles extends BaseEntity {

	private static final long serialVersionUID = 1L;
	private String id;
	private String departmentid;
	private String oid;
	private int pdatafilter;
	private String name;
	private String createdate;
	private String descript;
	private String systemtype;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDepartmentid() {
		return departmentid;
	}

	public void setDepartmentid(String departmentid) {
		this.departmentid = departmentid;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public int getPdatafilter() {
		return pdatafilter;
	}

	public void setPdatafilter(int pdatafilter) {
		this.pdatafilter = pdatafilter;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public String getSystemtype() {
		return systemtype;
	}

	public void setSystemtype(String systemtype) {
		this.systemtype = systemtype;
	}
}
