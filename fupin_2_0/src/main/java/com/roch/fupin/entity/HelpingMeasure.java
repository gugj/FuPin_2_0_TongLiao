/**
 * 
 */
package com.roch.fupin.entity;

/**
 * 帮扶措施统计
 * 
 * @author ZhaoDongShao
 *
 * 2016年8月11日 
 *
 */
public class HelpingMeasure extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String adl_nm;//   行政区
	public String adl_cd;// 
	public int jy_p_c;//  就业人数 
	public int jy_f_c;//  就业户数 
	public int dhzc_f_c;// 到户增收户数  
	public int tpd_p_c;//  脱贫贷人数
	public int tpd_f_c;// 脱贫贷户数  
	public int px_p_c;// 参加培训人数  
	public int px_f_c;//  参加培训户数 
	public int cyfc_f_c;// 产业扶持户数  
	public int cyfc_p_c;// 产业扶持受益人数 
	public int dhzc_p_c;// 到户增收人数    	
	public int fpbq_f_c;// 扶贫搬迁户数  
	public int fpbq_p_c;// 扶贫搬迁受益人数

	// 用于查询
	public int length;
	public String condition;
	public String startDate;
	public String endDate;

}
