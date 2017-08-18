package com.roch.fupin.entity;

/**
 * list菜单实体类
 * @author 赵东绍
 *
 */
public class ListMenu extends BaseEntity{

	private static final long serialVersionUID = 1L;

	private String name;
	private int Rid;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRid() {
		return Rid;
	}
	public void setRid(int rid) {
		Rid = rid;
	}
}
