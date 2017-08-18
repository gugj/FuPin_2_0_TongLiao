package com.roch.fupin.entity;

import java.util.List;

/**
 * @author ZhaoDongShao
 * 2016年6月27日
 */
public class PoorPeopleCase extends BaseEntity{

	private static final long serialVersionUID = 1L;

	private List<PeopleCaseEntity> data;

	public List<PeopleCaseEntity> getData() {
		return data;
	}

	public void setData(List<PeopleCaseEntity> data) {
		this.data = data;
	}
}
