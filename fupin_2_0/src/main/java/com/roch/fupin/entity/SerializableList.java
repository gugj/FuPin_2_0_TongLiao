/**
 * 
 */
package com.roch.fupin.entity;

import java.util.List;

/**
 * 序列化list，以供传递
 * 
 * @author ZhaoDongShao
 *
 * 2016年5月25日 
 *
 */
public class SerializableList extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<ProjectNongweiItemAppModel> list;

	public List<ProjectNongweiItemAppModel> getList() {
		return list;
	}

	public void setList(List<ProjectNongweiItemAppModel> list) {
		this.list = list;
	}
	
}
