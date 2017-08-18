/**
 * 
 */
package com.roch.fupin.entity;

import java.util.Map;

/**
 * 序列化map，以供传递
 * 
 * @author ZhaoDongShao
 *
 * 2016年5月25日 
 *
 */
public class SerializableMap extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Map<String, String>> map;

	public Map<String, Map<String, String>> getMap() {
		return map;
	}

	public void setMap(Map<String, Map<String, String>> map) {
		this.map = map;
	}
	
}
