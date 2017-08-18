/**
 * 
 */
package com.roch.fupin.entity;

import java.util.List;

/**
 * @author ZhaoDongShao
 *
 * 2016年5月24日 
 *
 */
public class PoorVillage extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PoorVillageBase pn; //基本信息
	private PoorVillageAccountPrint pt; //台账
	private List<PoorFamilyPhoto> le; //照片
	public PoorVillageBase getPn() {
		return pn;
	}
	public void setPn(PoorVillageBase pn) {
		this.pn = pn;
	}
	public PoorVillageAccountPrint getPt() {
		return pt;
	}
	public void setPt(PoorVillageAccountPrint pt) {
		this.pt = pt;
	}
	public List<PoorFamilyPhoto> getLe() {
		return le;
	}
	public void setLe(List<PoorFamilyPhoto> le) {
		this.le = le;
	}
	
}
