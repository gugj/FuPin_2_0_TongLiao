/**
 * 
 */
package com.roch.fupin.entity;

import java.util.List;

/**
 * @author ZhaoDongShao
 *
 * 2016年6月25日 
 *
 */
public class PoorcityLedgerModelListResult extends BaseResult{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<PoorcityLedgerModel> jsondata;

	public List<PoorcityLedgerModel> getJsondata() {
		return jsondata;
	}

	public void setJsondata(List<PoorcityLedgerModel> jsondata) {
		this.jsondata = jsondata;
	}

}
