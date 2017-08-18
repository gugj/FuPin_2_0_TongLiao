package com.roch.fupin.entity;

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Id;
import com.lidroid.xutils.db.annotation.Table;

/**
 * 选择城市历史
 * @author Administrator
 *
 */
@Table(name="Recentcity")
public class Recentcity extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getDate() {
		return date;
	}
	public void setDate(Long date) {
		this.date = date;
	}
	public String getAdl_cd() {
		return adl_cd;
	}
	public void setAdl_cd(String adl_cd) {
		this.adl_cd = adl_cd;
	}
	@Id
	private Integer id;
	@Column(column="name")
	private String name;
	@Column(column="adl_cd")
	private String adl_cd;
	@Column(column="date")
	private Long date;
	
	
}
