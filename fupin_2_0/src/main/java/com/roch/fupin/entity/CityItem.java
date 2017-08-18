package com.roch.fupin.entity;

import java.util.List;

/**
 * 城市列表的Item实体类CityItem，里面包含了Integer类型的Type和存有行政区AdlCode对象的List集合两个字段，
 * 继承自BaseEntity，父类里面仅只有如下一个方法： 把json字符串通过gson转换成实体类，实现了Serializable序列化接口
 * @author ZhaoDongShao 2016年7月13日
 */
public class CityItem extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private Integer Type;
	
	/**
	 * 存有行政区AdlCode对象的List集合
	 */
	private List<AdlCode> list;

	public CityItem() {
	}

	public CityItem(Integer type, List<AdlCode> list) {
		this.list = list;
		this.Type = type;
	}

	public Integer getType() {
		return Type;
	}

	public void setType(Integer type) {
		Type = type;
	}

	public List<AdlCode> getList() {
		return list;
	}

	public void setList(List<AdlCode> list) {
		this.list = list;
	}
}
