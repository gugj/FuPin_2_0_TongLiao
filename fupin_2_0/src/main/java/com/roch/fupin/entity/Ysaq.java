package com.roch.fupin.entity;
/**
 * 饮水安全
 * <br>
 * <b>功能：</b>TudiAppModel<br>
 * <b>作者：</b>nq生成器<br>
 */
public class Ysaq extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//台账属性
	private String waterwsx;
	private String waterkn;
	public String getWaterwsx() {
		return this.waterwsx;
	}
	public void setWaterwsx(String waterwsx) {
		this.waterwsx = waterwsx;
	}
	public String getWaterkn() {
		return this.waterkn;
	}
	public void setWaterkn(String waterkn) {
		this.waterkn = waterkn;
	}
	
}

