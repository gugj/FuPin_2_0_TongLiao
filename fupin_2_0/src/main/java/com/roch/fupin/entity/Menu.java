package com.roch.fupin.entity;

import com.lidroid.xutils.db.annotation.Id;
import com.lidroid.xutils.db.annotation.Unique;

import java.util.List;

/**
 * Menu实体类，里面有9个字段：id、icon、navType、ordernum、name、checked、view_path、parent_id和存有Menu对象（自己）的集合children，
 * 继承自BaseEntity，父类里面仅只有如下一个方法： 把json字符串通过gson转换成实体类，实现了Serializable序列化接口
 * @author ZhaoDongShao 2016年5月13日
 */
public class Menu extends BaseEntity {

	public static final long serialVersionUID = 1L;

	@Id
	public int _id;
	@Unique
	public String mid;
	public String icon;
	public String navType;
	public int ordernum;
	public String name;
	public Boolean checked;
	public String view_path;
	public String parent_id;
	public List<Menu> children;


	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

//		public String getId() {
//		return mid;
//	}
//
//	public void setId(String id) {
//		this.mid = id;
//	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getNavType() {
		return navType;
	}

	public void setNavType(String navType) {
		this.navType = navType;
	}

	public int getOrdernum() {
		return ordernum;
	}

	public void setOrdernum(int ordernum) {
		this.ordernum = ordernum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getView_path() {
		return view_path;
	}

	public void setView_path(String view_path) {
		this.view_path = view_path;
	}

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

	public List<Menu> getChildren() {
		return children;
	}

	public void setChildren(List<Menu> children) {
		this.children = children;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
}
