package com.roch.fupin.entity;

/**
 * 脱贫项目攻坚菜单实体类（只有serialVersionUID一个字段），继承自Menu实体类（内含所有字段），父类继承自BaseEntity，
 * 里面仅只有如下一个方法： 把json字符串通过gson转换成实体类，实现了Serializable序列化接口
 * @author ZhaoDongShao
 * 2016年6月1日 
 */
public class NoPoorProjectMenu extends Menu{

	private static final long serialVersionUID = 1L;

}
