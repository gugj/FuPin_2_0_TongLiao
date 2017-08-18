package com.roch.fupin.entity;

import java.util.List;

/**
 * 贫困户帮扶记录的实体类，里面含有帮扶记录的内部类
 * 2016年10月31日
 */
public class PoorHouseBangFuJiLu extends BaseResult{

	private static final long serialVersionUID = 1L;
	
	/**
	 * list集合，里面含有多条帮扶记录对象的信息
	 */
	private List<BangFuJiLu> jsondata;
	
	/**
	 * 获取List<PoorFamily>类型的jsondata集合
	 * @return
	 * 2016年10月31日
	 */
	public List<BangFuJiLu> getJsondata() {
		return jsondata;
	}

	/**
	 * 设置List<PoorFamily>类型的jsondata集合
	 * @param jsondata
	 * 2016年10月31日
	 */
	public void setJsondata(List<BangFuJiLu> jsondata) {
		this.jsondata = jsondata;
	}
	
	public class BangFuJiLu{

		public String helpdate;
		public String helpdetail;
		public String helptitle;
		public String householderid;
		public String id;
		public String zhutiid;
		public String page;
		public String rows;
		public String name;
		public String location;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getLocation() {
			return location;
		}

		public void setLocation(String location) {
			this.location = location;
		}

		public String getZhutiid() {
			return zhutiid;
		}

		public void setZhutiid(String zhutiid) {
			this.zhutiid = zhutiid;
		}

		public String getHelpdate() {
			return helpdate;
		}

		public void setHelpdate(String helpdate) {
			this.helpdate = helpdate;
		}

		public String getHelpdetail() {
			return helpdetail;
		}

		public void setHelpdetail(String helpdetail) {
			this.helpdetail = helpdetail;
		}

		public String getHelptitle() {
			return helptitle;
		}

		public void setHelptitle(String helptitle) {
			this.helptitle = helptitle;
		}

		public String getHouseholderid() {
			return householderid;
		}

		public void setHouseholderid(String householderid) {
			this.householderid = householderid;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getPage() {
			return page;
		}

		public void setPage(String page) {
			this.page = page;
		}

		public String getRows() {
			return rows;
		}

		public void setRows(String rows) {
			this.rows = rows;
		}
	}

}
