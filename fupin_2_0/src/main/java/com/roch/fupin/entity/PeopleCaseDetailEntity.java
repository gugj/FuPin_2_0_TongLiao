package com.roch.fupin.entity;

/**
 * 致贫原因详情实体类，里面有34个字段：adl_nm、adl_cd、poorreson_b_pc、poorreson_b_fc....
 * @author ZhaoDongShao
 * 2016年8月9日
 */
public class PeopleCaseDetailEntity extends BaseEntity{

	private static final long serialVersionUID = 1L;
	private String adl_nm;//行政区
	private String adl_cd;//

	private int poorreson_b_pc;//因病致贫人数
	private int poorreson_b_fc;//因病致贫户数

	private int poorreson_l_pc;//无劳动能力致贫人数
	private int poorreson_l_fc;//无劳动能力致贫户数

	private int poorreson_z_pc;//缺资金致贫人数
	private int poorreson_z_fc;//缺资金致贫户数

	private int poorreson_j_pc;//缺技术致贫人数
	private int poorreson_j_fc;//缺技术致贫户数

	private int poorreson_c_fc;//因残致贫户数
	private int poorreson_c_pc;//因残致贫人数
	private int poorreson_c_wz_pc;//因残致贫无证人数
	private int poorreson_c_yz_pc;//因残致贫有证人数

	private int poorreson_x_pc;//因学致贫人数
	private int poorreson_x_fc;//因学致贫户数
	private int poorreson_x_pc_yey;//因学致贫幼儿园人数
	private int poorreson_x_pc_xxs;//因学致贫小学生人数
	private int poorreson_x_pc_czs;//因学致贫初中生人数
	private int poorreson_x_pc_gzs;//因学致贫高中生人数
	private int poorreson_x_pc_zz;//因学致贫职专人数
	private int poorreson_x_pc_dx;//因学致贫大学生人数

	private int poorreson_zh_fc;//因灾致贫户数

	private int poorreson_qtd_fc;//缺土地致贫户数

	private int poorreson_qs_fc;//缺水致贫户数

	private int poorreson_jttjlh_fc;//交通条件落后致贫户数

	private int poorreson_zsfzdlbz_fc;//自身发展动力不足致贫户数

	private int poorreson_b_cjmxb_pc;//
	private int poorreson_b_tsmxb_pc;//
	private int poorreson_b_zdjb_pc;//

	// 用于查询
	private int length;
	private String condition;
	private String startDate;
	private String endDate;


//	private int poorreson_qixiannei;//旗县内
//	private int poorreson_qixianwai;//旗县外（市内）
//	private int poorreson_shiwaisn;//市外（省内）
//	private int poorreson_shengwai;//省外
//	private String pp_poorreason;//
//	private String pp_poorreason_pc;//


	public int getPoorreson_zh_fc() {
		return poorreson_zh_fc;
	}

	public void setPoorreson_zh_fc(int poorreson_zh_fc) {
		this.poorreson_zh_fc = poorreson_zh_fc;
	}

	public int getPoorreson_qtd_fc() {
		return poorreson_qtd_fc;
	}

	public void setPoorreson_qtd_fc(int poorreson_qtd_fc) {
		this.poorreson_qtd_fc = poorreson_qtd_fc;
	}

	public int getPoorreson_qs_fc() {
		return poorreson_qs_fc;
	}

	public void setPoorreson_qs_fc(int poorreson_qs_fc) {
		this.poorreson_qs_fc = poorreson_qs_fc;
	}

	public int getPoorreson_jttjlh_fc() {
		return poorreson_jttjlh_fc;
	}

	public void setPoorreson_jttjlh_fc(int poorreson_jttjlh_fc) {
		this.poorreson_jttjlh_fc = poorreson_jttjlh_fc;
	}

	public int getPoorreson_zsfzdlbz_fc() {
		return poorreson_zsfzdlbz_fc;
	}

	public void setPoorreson_zsfzdlbz_fc(int poorreson_zsfzdlbz_fc) {
		this.poorreson_zsfzdlbz_fc = poorreson_zsfzdlbz_fc;
	}

	public int getPoorreson_b_cjmxb_pc() {
		return poorreson_b_cjmxb_pc;
	}

	public void setPoorreson_b_cjmxb_pc(int poorreson_b_cjmxb_pc) {
		this.poorreson_b_cjmxb_pc = poorreson_b_cjmxb_pc;
	}

	public int getPoorreson_b_tsmxb_pc() {
		return poorreson_b_tsmxb_pc;
	}

	public void setPoorreson_b_tsmxb_pc(int poorreson_b_tsmxb_pc) {
		this.poorreson_b_tsmxb_pc = poorreson_b_tsmxb_pc;
	}

	public int getPoorreson_b_zdjb_pc() {
		return poorreson_b_zdjb_pc;
	}

	public void setPoorreson_b_zdjb_pc(int poorreson_b_zdjb_pc) {
		this.poorreson_b_zdjb_pc = poorreson_b_zdjb_pc;
	}

	public String getAdl_nm() {
		return adl_nm;
	}
	public void setAdl_nm(String adl_nm) {
		this.adl_nm = adl_nm;
	}
	public String getAdl_cd() {
		return adl_cd;
	}
	public void setAdl_cd(String adl_cd) {
		this.adl_cd = adl_cd;
	}
	public int getPoorreson_b_pc() {
		return poorreson_b_pc;
	}
	public void setPoorreson_b_pc(int poorreson_b_pc) {
		this.poorreson_b_pc = poorreson_b_pc;
	}
	public int getPoorreson_b_fc() {
		return poorreson_b_fc;
	}
	public void setPoorreson_b_fc(int poorreson_b_fc) {
		this.poorreson_b_fc = poorreson_b_fc;
	}
	public int getPoorreson_l_pc() {
		return poorreson_l_pc;
	}
	public void setPoorreson_l_pc(int poorreson_l_pc) {
		this.poorreson_l_pc = poorreson_l_pc;
	}
	public int getPoorreson_l_fc() {
		return poorreson_l_fc;
	}
	public void setPoorreson_l_fc(int poorreson_l_fc) {
		this.poorreson_l_fc = poorreson_l_fc;
	}
	public int getPoorreson_z_pc() {
		return poorreson_z_pc;
	}
	public void setPoorreson_z_pc(int poorreson_z_pc) {
		this.poorreson_z_pc = poorreson_z_pc;
	}
	public int getPoorreson_z_fc() {
		return poorreson_z_fc;
	}
	public void setPoorreson_z_fc(int poorreson_z_fc) {
		this.poorreson_z_fc = poorreson_z_fc;
	}
	public int getPoorreson_j_pc() {
		return poorreson_j_pc;
	}
	public void setPoorreson_j_pc(int poorreson_j_pc) {
		this.poorreson_j_pc = poorreson_j_pc;
	}
	public int getPoorreson_j_fc() {
		return poorreson_j_fc;
	}
	public void setPoorreson_j_fc(int poorreson_j_fc) {
		this.poorreson_j_fc = poorreson_j_fc;
	}
	public int getPoorreson_c_fc() {
		return poorreson_c_fc;
	}
	public void setPoorreson_c_fc(int poorreson_c_fc) {
		this.poorreson_c_fc = poorreson_c_fc;
	}
	public int getPoorreson_c_pc() {
		return poorreson_c_pc;
	}
	public void setPoorreson_c_pc(int poorreson_c_pc) {
		this.poorreson_c_pc = poorreson_c_pc;
	}
	public int getPoorreson_c_wz_pc() {
		return poorreson_c_wz_pc;
	}
	public void setPoorreson_c_wz_pc(int poorreson_c_wz_pc) {
		this.poorreson_c_wz_pc = poorreson_c_wz_pc;
	}
	public int getPoorreson_c_yz_pc() {
		return poorreson_c_yz_pc;
	}
	public void setPoorreson_c_yz_pc(int poorreson_c_yz_pc) {
		this.poorreson_c_yz_pc = poorreson_c_yz_pc;
	}
	public int getPoorreson_x_pc() {
		return poorreson_x_pc;
	}
	public void setPoorreson_x_pc(int poorreson_x_pc) {
		this.poorreson_x_pc = poorreson_x_pc;
	}
	public int getPoorreson_x_fc() {
		return poorreson_x_fc;
	}
	public void setPoorreson_x_fc(int poorreson_x_fc) {
		this.poorreson_x_fc = poorreson_x_fc;
	}
	public int getPoorreson_x_pc_yey() {
		return poorreson_x_pc_yey;
	}
	public void setPoorreson_x_pc_yey(int poorreson_x_pc_yey) {
		this.poorreson_x_pc_yey = poorreson_x_pc_yey;
	}
	public int getPoorreson_x_pc_xxs() {
		return poorreson_x_pc_xxs;
	}
	public void setPoorreson_x_pc_xxs(int poorreson_x_pc_xxs) {
		this.poorreson_x_pc_xxs = poorreson_x_pc_xxs;
	}
	public int getPoorreson_x_pc_czs() {
		return poorreson_x_pc_czs;
	}
	public void setPoorreson_x_pc_czs(int poorreson_x_pc_czs) {
		this.poorreson_x_pc_czs = poorreson_x_pc_czs;
	}
	public int getPoorreson_x_pc_gzs() {
		return poorreson_x_pc_gzs;
	}
	public void setPoorreson_x_pc_gzs(int poorreson_x_pc_gzs) {
		this.poorreson_x_pc_gzs = poorreson_x_pc_gzs;
	}
	public int getPoorreson_x_pc_zz() {
		return poorreson_x_pc_zz;
	}
	public void setPoorreson_x_pc_zz(int poorreson_x_pc_zz) {
		this.poorreson_x_pc_zz = poorreson_x_pc_zz;
	}
	public int getPoorreson_x_pc_dx() {
		return poorreson_x_pc_dx;
	}
	public void setPoorreson_x_pc_dx(int poorreson_x_pc_dx) {
		this.poorreson_x_pc_dx = poorreson_x_pc_dx;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
}
