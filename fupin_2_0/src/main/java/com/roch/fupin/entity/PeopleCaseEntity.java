package com.roch.fupin.entity;

/**
 * @author ZhaoDongShao
 * 2016年8月9日
 */
public class PeopleCaseEntity extends MapEntity{

	private String id;

	/**
	 * @param key
	 * @param value
	 */
	public PeopleCaseEntity(String key, String value,String id) {
		super(key, value);
		this.id = id;
	}

	private static final long serialVersionUID = 1L;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
