package com.roch.fupin.entity;

import java.util.List;

/**
 * 
 * <br>
 * <b>功能：</b>ProjectFulianPage<br>
 * <b>作者：</b>nq生成器<br>
 */
public class ProjectFulianAppModel extends BaseProject {
	
	
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;//   ID
	private String traintypeidcall;//   培训类别
	private String traindate;//   培训时间
	private String trainmodeidcall;//   培训方式
	private String personname;//   培训负责人
	private List<ProjectFulianItemAppModel> pam;
	public String getId() {
	public String getTraintypeidcall() {
		return traintypeidcall;
	}
	public void setTraintypeidcall(String traintypeidcall) {
		this.traintypeidcall = traintypeidcall;
	}
	public String getTrainmodeidcall() {
		return trainmodeidcall;
	}
	public void setTrainmodeidcall(String trainmodeidcall) {
		this.trainmodeidcall = trainmodeidcall;
	}
	public List<ProjectFulianItemAppModel> getPam() {
		return pam;
	}
	public void setPam(List<ProjectFulianItemAppModel> pam) {
		this.pam = pam;
	}
	
	
}