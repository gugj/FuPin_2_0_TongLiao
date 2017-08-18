/**
 * 
 */
package com.roch.fupin.entity;

/**
 * 项目布局分类
 * 
 * @author ZhaoDongShao
 *
 * 2016年6月1日 
 *
 */
public class ViewItem extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int type;
	private MapEntity map;
	
	/**
	 * 
	 */
	public ViewItem(int type, MapEntity entity) {
		// TODO Auto-generated constructor stub
		this.type = type;
		this.map = entity;
	}
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public MapEntity getMap() {
		return map;
	}
	public void setMap(MapEntity map) {
		this.map = map;
	}
}
