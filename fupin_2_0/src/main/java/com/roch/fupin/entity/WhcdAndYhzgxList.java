/**
 * 
 */
package com.roch.fupin.entity;

import java.util.List;

/**
 * 文化程度、与户主关系的实体类，里面包含文化程度和与户主关系两个实体类对象的集合
 * 
 * @author ZhaoDongShao
 *
 * 2016年5月19日
 *
 */
public class WhcdAndYhzgxList extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private List<Yhzgx> wordbook_yhzgx;
	private List<Whcd> wordbook_whcd;

	public List<Yhzgx> getWordbook_yhzgx() {
		return wordbook_yhzgx;
	}

	public void setWordbook_yhzgx(List<Yhzgx> wordbook_yhzgx) {
		this.wordbook_yhzgx = wordbook_yhzgx;
	}

	public List<Whcd> getWordbook_whcd() {
		return wordbook_whcd;
	}

	public void setWordbook_whcd(List<Whcd> wordbook_whcd) {
		this.wordbook_whcd = wordbook_whcd;
	}

}
