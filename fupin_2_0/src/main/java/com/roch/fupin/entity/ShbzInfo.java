package com.roch.fupin.entity;
/**
 * 
 * 社会保障信息
 * 
 * <br>
 * <b>功能：</b>TudiAppModel<br>
 * <b>作者：</b>nq生成器<br>
 */
public class ShbzInfo extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//台账属性
	private String comncyl;
	private String comczzg;
	private String comncxjm;
	public String getComncyl() {
		return this.comncyl;
	}
	public void setComncyl(String comncyl) {
		this.comncyl = comncyl;
	}
	public String getComczzg() {
		return this.comczzg;
	}
	public void setComczzg(String comczzg) {
		this.comczzg = comczzg;
	}
	public String getComncxjm() {
		return this.comncxjm;
	}
	public void setComncxjm(String comncxjm) {
		this.comncxjm = comncxjm;
	}
	
}

