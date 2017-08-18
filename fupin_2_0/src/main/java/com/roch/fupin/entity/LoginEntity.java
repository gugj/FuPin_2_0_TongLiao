package com.roch.fupin.entity;

import java.util.List;

/**
 * App 用户登录成功返回的LoginEntity实体类，里面包含三个字段：
 * 登陆用户对象User、存有Menu对象List的集合navigation、和当前用户的权限对象Sroles
 * @author Administrator
 */
public class LoginEntity {
	
	private User loginUser;
	private List<Menu> navigation;
	private Sroles sroles;

	public User getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(User loginUser) {
		this.loginUser = loginUser;
	}

	public List<Menu> getNavigation() {
		return navigation;
	}

	public void setNavigation(List<Menu> navigation) {
		this.navigation = navigation;
	}

	public Sroles getSroles() {
		return sroles;
	}

	public void setSroles(Sroles sroles) {
		this.sroles = sroles;
	}
}
