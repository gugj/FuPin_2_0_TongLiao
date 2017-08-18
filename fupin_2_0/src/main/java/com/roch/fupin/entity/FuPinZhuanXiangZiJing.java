package com.roch.fupin.entity;

/**
 * @author ZhaoDongShao
 * 2016年8月12日
 */
public class FuPinZhuanXiangZiJing extends BaseEntity{

	private static final long serialVersionUID = 1L;

	public int length;
	public String condition;

	public String adl_nm; //行政区
	public String adl_cd;

	public int zhongyang; //中央（自治区）资金
	public int shiji; //市级资金
	public int qixianji; //旗县级资金
	public int fzzjsxzj; //发展资金（三西资金）
	public int ygdzzj; //以工代账资金
	public int ssmzfzzj; //少数民族发展资金
	public int gypklczj; //国有贫困林场资金
	public int gypknczj; //国有贫困农场资金
	public int fzzjglf; //发展资金（管理费）

	
}
