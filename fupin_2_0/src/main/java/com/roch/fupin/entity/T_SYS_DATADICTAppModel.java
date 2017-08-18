package com.roch.fupin.entity;

/**
 * 致贫原因/帮扶措施
 * 
 * APP 请求返回值封装类
 * 
 * @author wf
 *
 */
public class T_SYS_DATADICTAppModel extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String fid;//父键ID
	private String name;//数据类型
	private String code;//对应键
	private String oid;//排序值
	private String isfix;//
	private String createtime;//创建时间
	private String descript;//描述
	private String cid;//对应值
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
		this.fid = fid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getIsfix() {
		return isfix;
	}
	public void setIsfix(String isfix) {
		this.isfix = isfix;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getDescript() {
		return descript;
	}
	public void setDescript(String descript) {
		this.descript = descript;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	
}
