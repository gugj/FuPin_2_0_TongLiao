package com.roch.fupin.entity;

/**
 * @author ZhaoDongShao
 * 2016年5月10日
 */
public class Photo extends BaseEntity{

	private static final long serialVersionUID = 1L;

	private String url;

	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
