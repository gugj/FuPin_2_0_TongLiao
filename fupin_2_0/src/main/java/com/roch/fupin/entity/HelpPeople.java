package com.roch.fupin.entity;

/**
 * 帮扶责任人实体
 * <br>
 * <b>功能：</b>HelpdutypersonAppPage<br>
 * <b>作者：</b>nq生成器<br>
 */
public class HelpPeople extends BaseEntity {
	
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;//   ID	private String name;//   帮扶责任人姓名	private int sex;//   性别	private String idno;//   身份证号	private String companyname;//   帮扶责任人单位	private String title;//   帮扶责任人职务	private String phone;//   帮扶责任人联系方式	private String remark;//   备注
	//贫困户-帮扶责任人关系表字段
	private String map_id;//   ID
	private String householderid;//   户主ID
	private String helpdutypersonid;//   帮扶责任人ID
	private String sexName;//性别
	private PoorFamilyBase  pfa;
	public PoorFamilyBase getPfa() {
		return pfa;
	}
	public void setPfa(PoorFamilyBase pfa) {
		this.pfa = pfa;
	}
	public String getSexName() {
		return this.sexName;
	}
	public void setSexName(String sexName) {
		this.sexName = sexName;
	}
	public String getId() {	    return this.id;	}	public void setId(String id) {	 this.id=id;	}	public String getName() {	    return this.name;	}	public void setName(String name) {	 this.name=name;	}	public int getSex() {	    return this.sex;	}	public void setSex(int sex) {	 this.sex=sex;	}	public String getIdno() {	    return this.idno;	}	public void setIdno(String idno) {	 this.idno=idno;	}	public String getCompanyname() {	    return this.companyname;	}	public void setCompanyname(String companyname) {	 this.companyname=companyname;	}	public String getTitle() {	    return this.title;	}	public void setTitle(String title) {	 this.title=title;	}	public String getPhone() {	    return this.phone;	}	public void setPhone(String phone) {	 this.phone=phone;	}	public String getRemark() {	    return this.remark;	}	public void setRemark(String remark) {	 this.remark=remark;	}
	public String getMap_id() {
		return map_id;
	}
	public void setMap_id(String map_id) {
		this.map_id = map_id;
	}
	public String getHouseholderid() {
		return householderid;
	}
	public void setHouseholderid(String householderid) {
		this.householderid = householderid;
	}
	public String getHelpdutypersonid() {
		return helpdutypersonid;
	}
	public void setHelpdutypersonid(String helpdutypersonid) {
		this.helpdutypersonid = helpdutypersonid;
	}
	
}
