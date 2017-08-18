package com.roch.fupin.entity;

/**
 * 扶贫新闻实体类
 * @author ZhaoDongShao 2016年5月26日
 */
public class FuPinXinWen extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String id;//   id
	private String householderid;//   户主ID
	private String title;//   标题
	private String detail;//   反馈内容
	private String ts;//   时间
	private String newsType;//   新闻类型
	private String startDate;//   查询开始日期
	private String endDate;//   查询结束日期
	private String householderNm;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHouseholderid() {
		return householderid;
	}

	public void setHouseholderid(String householderid) {
		this.householderid = householderid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getTs() {
		return ts;
	}

	public void setTs(String ts) {
		this.ts = ts;
	}

	public String getNewsType() {
		return newsType;
	}

	public void setNewsType(String newsType) {
		this.newsType = newsType;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getHouseholderNm() {
		return householderNm;
	}

	public void setHouseholderNm(String householderNm) {
		this.householderNm = householderNm;
	}
}
