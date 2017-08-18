/**
 * 
 */
package com.roch.fupin.entity;

/**
 * @author ZhaoDongShao
 *
 * 2016年5月24日 
 *
 */
public class PoorVillageAccountPrint extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TudiInfo td; //土地信息
	private Wfgz wfgz; //房屋改造
	private Whjs whjs; //文化建设
	private Wsjhsy wsjhsy; //卫生和计划生育
	private Ysaq ysaq; //饮水安全
	private Tscy tscy; //特色产业增收
	private ShbzInfo shbz; //社会保障信息
	private RenkouInfo renkou; //人口信息
	private Pkcxxh pkcxxh; //贫困村信息化
	private Cjdl cjdl; //村级道路
	public TudiInfo getTd() {
		return td;
	}
	public void setTd(TudiInfo td) {
		this.td = td;
	}
	public Wfgz getWfgz() {
		return wfgz;
	}
	public void setWfgz(Wfgz wfgz) {
		this.wfgz = wfgz;
	}
	public Whjs getWhjs() {
		return whjs;
	}
	public void setWhjs(Whjs whjs) {
		this.whjs = whjs;
	}
	public Wsjhsy getWsjhsy() {
		return wsjhsy;
	}
	public void setWsjhsy(Wsjhsy wsjhsy) {
		this.wsjhsy = wsjhsy;
	}
	public Ysaq getYsaq() {
		return ysaq;
	}
	public void setYsaq(Ysaq ysaq) {
		this.ysaq = ysaq;
	}
	public Tscy getTscy() {
		return tscy;
	}
	public void setTscy(Tscy tscy) {
		this.tscy = tscy;
	}
	public ShbzInfo getShbz() {
		return shbz;
	}
	public void setShbz(ShbzInfo shbz) {
		this.shbz = shbz;
	}
	public RenkouInfo getRenkou() {
		return renkou;
	}
	public void setRenkou(RenkouInfo renkou) {
		this.renkou = renkou;
	}
	public Pkcxxh getPkcxxh() {
		return pkcxxh;
	}
	public void setPkcxxh(Pkcxxh pkcxxh) {
		this.pkcxxh = pkcxxh;
	}
	public Cjdl getCjdl() {
		return cjdl;
	}
	public void setCjdl(Cjdl cjdl) {
		this.cjdl = cjdl;
	}
}
