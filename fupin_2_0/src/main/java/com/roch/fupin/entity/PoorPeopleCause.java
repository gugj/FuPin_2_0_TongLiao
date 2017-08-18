package com.roch.fupin.entity;

/**
 * 致贫原因实体类,里面有35个字段：adl_nm、adl_cd、poorreson_b_pc、poorreson_b_fc、poorreson_l_pc、poorreson_l_fc...
 * @author ZhaoDongShao
 * 2016年6月27日
 */
public class PoorPeopleCause extends BaseEntity{

	private static final long serialVersionUID = 1L;

	private String adl_nm;//行政区
	private String adl_cd;//

	private String poorreson_b_pc;//因病致贫人数
	private String poorreson_b_fc;//因病致贫户数

	private String poorreson_l_pc;//无劳动能力致贫人数
	private String poorreson_l_fc;//无劳动能力致贫户数

	private String poorreson_z_pc;//缺资金致贫人数
	private String poorreson_z_fc;//缺资金致贫户数

	private String poorreson_j_fc;//缺技术致贫户数

	private String poorreson_c_fc;//因残致贫户数
	private String poorreson_c_pc;//因残致贫人数

	private String poorreson_c_wz_pc;//因残致贫无证人数
	private String poorreson_c_yz_pc;//因残致贫有证人数

	private String poorreson_x_pc;//因学致贫人数
	private String poorreson_x_fc;//因学致贫户数
	private String poorreson_x_pc_yey;//因学致贫幼儿园人数
	private String poorreson_x_pc_xxs;//因学致贫小学生人数
	private String poorreson_x_pc_czs;//因学致贫初中生人数
	private String poorreson_x_pc_gzs;//因学致贫高中生人数
	private String poorreson_x_pc_zz;//因学致贫职专人数
	private String poorreson_x_pc_dx;//因学致贫大学生人数

	private String poorreson_zh_fc;//因灾致贫户数

	private String poorreson_qtd_fc;//缺土地致贫户数

	private String poorreson_qs_fc;//缺水致贫户数

	private String poorreson_jttjlh_fc;//交通条件落后致贫户数

	private String poorreson_zsfzdlbz_fc;//自身发展动力不足致贫户数

	private String poorreson_b_cjmxb_pc;//常见慢性病
	private String poorreson_b_tsmxb_pc;//特殊慢性病
	private String poorreson_b_zdjb_pc;//重大

	private int poorreson_qixiannei;//旗县内
	private int poorreson_qixianwai;//旗县外（市内）
	private int poorreson_shiwaisn;//市外（省内）
	private int poorreson_shengwai;//省外

	private String pp_poorreason;//
	private String pp_poorreason_pc;//



//	private int poorreson_j_pc;//缺技术致贫人数
//	// 用于查询
//	private int length;
//	private String condition;
//	private String startDate;
//	private String endDate;


	public int getPoorreson_qixiannei() {
		return poorreson_qixiannei;
	}

	public void setPoorreson_qixiannei(int poorreson_qixiannei) {
		this.poorreson_qixiannei = poorreson_qixiannei;
	}

	public int getPoorreson_qixianwai() {
		return poorreson_qixianwai;
	}

	public void setPoorreson_qixianwai(int poorreson_qixianwai) {
		this.poorreson_qixianwai = poorreson_qixianwai;
	}

	public int getPoorreson_shiwaisn() {
		return poorreson_shiwaisn;
	}

	public void setPoorreson_shiwaisn(int poorreson_shiwaisn) {
		this.poorreson_shiwaisn = poorreson_shiwaisn;
	}

	public int getPoorreson_shengwai() {
		return poorreson_shengwai;
	}

	public void setPoorreson_shengwai(int poorreson_shengwai) {
		this.poorreson_shengwai = poorreson_shengwai;
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

	public String getPoorreson_b_pc() {
		return poorreson_b_pc;
	}

	public void setPoorreson_b_pc(String poorreson_b_pc) {
		this.poorreson_b_pc = poorreson_b_pc;
	}

	public String getPoorreson_b_fc() {
		return poorreson_b_fc;
	}

	public void setPoorreson_b_fc(String poorreson_b_fc) {
		this.poorreson_b_fc = poorreson_b_fc;
	}

	public String getPoorreson_l_pc() {
		return poorreson_l_pc;
	}

	public void setPoorreson_l_pc(String poorreson_l_pc) {
		this.poorreson_l_pc = poorreson_l_pc;
	}

	public String getPoorreson_l_fc() {
		return poorreson_l_fc;
	}

	public void setPoorreson_l_fc(String poorreson_l_fc) {
		this.poorreson_l_fc = poorreson_l_fc;
	}

	public String getPoorreson_z_pc() {
		return poorreson_z_pc;
	}

	public void setPoorreson_z_pc(String poorreson_z_pc) {
		this.poorreson_z_pc = poorreson_z_pc;
	}

	public String getPoorreson_z_fc() {
		return poorreson_z_fc;
	}

	public void setPoorreson_z_fc(String poorreson_z_fc) {
		this.poorreson_z_fc = poorreson_z_fc;
	}

	public String getPoorreson_j_fc() {
		return poorreson_j_fc;
	}

	public void setPoorreson_j_fc(String poorreson_j_fc) {
		this.poorreson_j_fc = poorreson_j_fc;
	}

	public String getPoorreson_c_fc() {
		return poorreson_c_fc;
	}

	public void setPoorreson_c_fc(String poorreson_c_fc) {
		this.poorreson_c_fc = poorreson_c_fc;
	}

	public String getPoorreson_c_pc() {
		return poorreson_c_pc;
	}

	public void setPoorreson_c_pc(String poorreson_c_pc) {
		this.poorreson_c_pc = poorreson_c_pc;
	}

	public String getPoorreson_c_wz_pc() {
		return poorreson_c_wz_pc;
	}

	public void setPoorreson_c_wz_pc(String poorreson_c_wz_pc) {
		this.poorreson_c_wz_pc = poorreson_c_wz_pc;
	}

	public String getPoorreson_c_yz_pc() {
		return poorreson_c_yz_pc;
	}

	public void setPoorreson_c_yz_pc(String poorreson_c_yz_pc) {
		this.poorreson_c_yz_pc = poorreson_c_yz_pc;
	}

	public String getPoorreson_x_pc() {
		return poorreson_x_pc;
	}

	public void setPoorreson_x_pc(String poorreson_x_pc) {
		this.poorreson_x_pc = poorreson_x_pc;
	}

	public String getPoorreson_x_fc() {
		return poorreson_x_fc;
	}

	public void setPoorreson_x_fc(String poorreson_x_fc) {
		this.poorreson_x_fc = poorreson_x_fc;
	}

	public String getPoorreson_x_pc_yey() {
		return poorreson_x_pc_yey;
	}

	public void setPoorreson_x_pc_yey(String poorreson_x_pc_yey) {
		this.poorreson_x_pc_yey = poorreson_x_pc_yey;
	}

	public String getPoorreson_x_pc_xxs() {
		return poorreson_x_pc_xxs;
	}

	public void setPoorreson_x_pc_xxs(String poorreson_x_pc_xxs) {
		this.poorreson_x_pc_xxs = poorreson_x_pc_xxs;
	}

	public String getPoorreson_x_pc_czs() {
		return poorreson_x_pc_czs;
	}

	public void setPoorreson_x_pc_czs(String poorreson_x_pc_czs) {
		this.poorreson_x_pc_czs = poorreson_x_pc_czs;
	}

	public String getPoorreson_x_pc_gzs() {
		return poorreson_x_pc_gzs;
	}

	public void setPoorreson_x_pc_gzs(String poorreson_x_pc_gzs) {
		this.poorreson_x_pc_gzs = poorreson_x_pc_gzs;
	}

	public String getPoorreson_x_pc_zz() {
		return poorreson_x_pc_zz;
	}

	public void setPoorreson_x_pc_zz(String poorreson_x_pc_zz) {
		this.poorreson_x_pc_zz = poorreson_x_pc_zz;
	}

	public String getPoorreson_x_pc_dx() {
		return poorreson_x_pc_dx;
	}

	public void setPoorreson_x_pc_dx(String poorreson_x_pc_dx) {
		this.poorreson_x_pc_dx = poorreson_x_pc_dx;
	}

	public String getPoorreson_zh_fc() {
		return poorreson_zh_fc;
	}

	public void setPoorreson_zh_fc(String poorreson_zh_fc) {
		this.poorreson_zh_fc = poorreson_zh_fc;
	}

	public String getPoorreson_qtd_fc() {
		return poorreson_qtd_fc;
	}

	public void setPoorreson_qtd_fc(String poorreson_qtd_fc) {
		this.poorreson_qtd_fc = poorreson_qtd_fc;
	}

	public String getPoorreson_qs_fc() {
		return poorreson_qs_fc;
	}

	public void setPoorreson_qs_fc(String poorreson_qs_fc) {
		this.poorreson_qs_fc = poorreson_qs_fc;
	}

	public String getPoorreson_jttjlh_fc() {
		return poorreson_jttjlh_fc;
	}

	public void setPoorreson_jttjlh_fc(String poorreson_jttjlh_fc) {
		this.poorreson_jttjlh_fc = poorreson_jttjlh_fc;
	}

	public String getPoorreson_zsfzdlbz_fc() {
		return poorreson_zsfzdlbz_fc;
	}

	public void setPoorreson_zsfzdlbz_fc(String poorreson_zsfzdlbz_fc) {
		this.poorreson_zsfzdlbz_fc = poorreson_zsfzdlbz_fc;
	}

	public String getPoorreson_b_cjmxb_pc() {
		return poorreson_b_cjmxb_pc;
	}

	public void setPoorreson_b_cjmxb_pc(String poorreson_b_cjmxb_pc) {
		this.poorreson_b_cjmxb_pc = poorreson_b_cjmxb_pc;
	}

	public String getPoorreson_b_tsmxb_pc() {
		return poorreson_b_tsmxb_pc;
	}

	public void setPoorreson_b_tsmxb_pc(String poorreson_b_tsmxb_pc) {
		this.poorreson_b_tsmxb_pc = poorreson_b_tsmxb_pc;
	}

	public String getPoorreson_b_zdjb_pc() {
		return poorreson_b_zdjb_pc;
	}

	public void setPoorreson_b_zdjb_pc(String poorreson_b_zdjb_pc) {
		this.poorreson_b_zdjb_pc = poorreson_b_zdjb_pc;
	}

	public String getPp_poorreason() {
		return pp_poorreason;
	}

	public void setPp_poorreason(String pp_poorreason) {
		this.pp_poorreason = pp_poorreason;
	}

	public String getPp_poorreason_pc() {
		return pp_poorreason_pc;
	}

	public void setPp_poorreason_pc(String pp_poorreason_pc) {
		this.pp_poorreason_pc = pp_poorreason_pc;
	}
}
