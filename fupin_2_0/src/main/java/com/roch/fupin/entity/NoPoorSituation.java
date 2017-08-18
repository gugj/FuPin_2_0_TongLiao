package com.roch.fupin.entity;

/**
 * @author ZhaoDongShao
 * 2016年8月12日
 */
public class NoPoorSituation extends BaseEntity{

	private static final long serialVersionUID = 1L;

	public String adl_nm;//行政区
	public String adl_cd;//
	public int pass_f_c;//达到人均纯收入最低标准户数
	public int pass_p_c;//达到人均纯收入最低标准人数
	public int unpass_f_c;//未达到人均纯收入最低标准户数
	public int unpass_p_c;//未达到人均纯收入最低标准人数
	// 用于查询
	public int length;
	public String condition;
	
}
