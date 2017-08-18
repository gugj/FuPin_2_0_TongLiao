/**
 * 
 */
package com.roch.fupin.entity;

/**
 * 文化程度
 * 
 * @author ZhaoDongShao
 *
 *         2016年5月19日
 *
 */
public class Whcd extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String value;
	private String text;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
