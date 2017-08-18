package com.roch.fupin.entity;

/**
 * 
 * <br>
 * <b>功能：</b>ProjectLinyeEconomyAppPage<br>
 * <b>作者：</b>nq生成器<br>
 */
public class ProjectLinyeEconomyAppModel extends BaseProject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;//   ID
	private String projectid;//   项目ID
	private String poorpeopleid;//   贫困户ID
	private String breedtypeid;//   种养殖品种ID,字典表
	private String breedtypeidcall;//   种养殖品种名称

	private double breedarea;//   种养殖面积
	private int breednumber;//   种养殖数量
	private double allowance;//   补助金额（每亩）
	private String personname;//   贫困户姓名
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProjectid() {
		return projectid;
	}
	public void setProjectid(String projectid) {
		this.projectid = projectid;
	}
	public String getPoorpeopleid() {
		return poorpeopleid;
	}
	public void setPoorpeopleid(String poorpeopleid) {
		this.poorpeopleid = poorpeopleid;
	}
	public String getBreedtypeid() {
		return breedtypeid;
	}
	public void setBreedtypeid(String breedtypeid) {
		this.breedtypeid = breedtypeid;
	}
	public String getBreedtypeidcall() {
		return breedtypeidcall;
	}
	public void setBreedtypeidcall(String breedtypeidcall) {
		this.breedtypeidcall = breedtypeidcall;
	}
	public double getBreedarea() {
		return breedarea;
	}
	public void setBreedarea(double breedarea) {
		this.breedarea = breedarea;
	}
	public int getBreednumber() {
		return breednumber;
	}
	public void setBreednumber(int breednumber) {
		this.breednumber = breednumber;
	}
	public double getAllowance() {
		return allowance;
	}
	public void setAllowance(double allowance) {
		this.allowance = allowance;
	}
	public String getPersonname() {
		return personname;
	}
	public void setPersonname(String personname) {
		this.personname = personname;
	}
		
}
