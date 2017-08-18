package com.roch.fupin.entity;

/**
 * BaseResult实体类，只含有三个字段Boolean类型的success和String类型的code、msg，通过get和set方法获取，
 * 继承自BaseEntity实体类
 * @author 赵东绍
 * @date 2015年3月25日 上午10:59:13
 *
 */
public class BaseResult extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	private String code;
	private Boolean success;
	private String msg;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 获取是否成功的boolean值
	 * @return 是否成功
	 * 2016年10月26日
	 */
	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
