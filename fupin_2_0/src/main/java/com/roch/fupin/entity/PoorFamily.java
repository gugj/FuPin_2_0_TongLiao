package com.roch.fupin.entity;

import java.util.List;

/**
 * 贫困户实体，里面有四个字段，包含贫困户所以信息和户ID的PoorFamilyBase对象和三个List集合，
 * 分别 存的是PoorFamilyPeople、PoorFamilyPhoto、PoorFamilyAccountPrint
 * @author wf
 */
public class PoorFamily extends BaseEntity {

	private static final long serialVersionUID = 1L;
	// private PoorfamilyEn pn;
	private PoorFamilyBase pn;    //
	private List<PoorFamilyPeople> pa;  // 贫困人信息
	private List<PoorFamilyPhoto> le; // 贫困照片
	private List<PoorFamilyAccountPrint> pia; // 贫困台账

	public PoorFamilyBase getPn() {
		return pn;
	}

	public void setPn(PoorFamilyBase pn) {
		this.pn = pn;
	}

	public List<PoorFamilyPeople> getPa() {
		return pa;
	}

	public void setPa(List<PoorFamilyPeople> pa) {
		this.pa = pa;
	}

	public List<PoorFamilyPhoto> getLe() {
		return le;
	}

	public void setLe(List<PoorFamilyPhoto> le) {
		this.le = le;
	}

	public List<PoorFamilyAccountPrint> getPia() {
		return pia;
	}

	public void setPia(List<PoorFamilyAccountPrint> pia) {
		this.pia = pia;
	}
}
