package com.roch.fupin.entity;
/**
 * 卫生和计划生育
 * 
 * <br>
 * <b>功能：</b>TudiAppModel<br>
 * <b>作者：</b>nq生成器<br>
 */
public class Wsjhsy extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//台账属性
	private String wsgs;
	private String wswc;
	private String wsys;
	private String wslj;
	public String getWsgs() {
		return this.wsgs;
	}
	public void setWsgs(String wsgs) {
		this.wsgs = wsgs;
	}
	public String getWswc() {
		return this.wswc;
	}
	public void setWswc(String wswc) {
		this.wswc = wswc;
	}
	public String getWsys() {
		return this.wsys;
	}
	public void setWsys(String wsys) {
		this.wsys = wsys;
	}
	public String getWslj() {
		return this.wslj;
	}
	public void setWslj(String wslj) {
		this.wslj = wslj;
	}
	
}

