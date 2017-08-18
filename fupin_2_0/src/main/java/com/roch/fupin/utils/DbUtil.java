package com.roch.fupin.utils;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.db.sqlite.WhereBuilder;
import com.roch.fupin.app.MyApplication;
import com.roch.fupin.entity.FuPinDaShiJiMenu;
import com.roch.fupin.entity.GanBuBaoLianMenu;
import com.roch.fupin.entity.HelpObjectMenu;
import com.roch.fupin.entity.HomeMenu;
import com.roch.fupin.entity.MapEntity;
import com.roch.fupin.entity.Menu;
import com.roch.fupin.entity.MoreMenu;
import com.roch.fupin.entity.NoPoorProjectMenu;
import com.roch.fupin.entity.NoticeMenu;
import com.roch.fupin.entity.PinKunHuHuDongMenu;
import com.roch.fupin.entity.ProjectInfoAppEntity;
import com.roch.fupin.entity.StatisticMenu;
import com.roch.fupin.entity.ViewItem;
import com.roch.fupin.entity.XinXiFuWuMenu;
import com.roch.fupin.entity.ZiJinJianGuanMenu;
import java.util.ArrayList;
import java.util.List;

/**
 * 自定义的保存获取的菜单
 * @author ZhaoDongShao 2016年5月13日
 */
public class DbUtil {

	static DbUtils dbUtils;

	/**
	 * 将侧滑菜单Menu对象的集合保存到数据库中
	 * @param menus
	 * @throws Exception
	 */
	public static void SaveMenu(List<Menu> menus) throws Exception {
		//获取数据库实例
		dbUtils = MyApplication.getInstance().getDbUtilsInstance(Common.LoginName);
		List<Menu> Menus = null; //二级子菜单
		for (Menu menu : menus) { //遍历侧滑菜单---9个
			// 先判断侧滑菜单的名字
			if (menu.getName().equals(Common.EXTS_NOTIC_NAME)) { //1.通知公告
				Menus = new ArrayList<Menu>();
				// 然后在拿到该侧滑菜单下的子菜单---即通知栏和公告栏
				Menus = menu.getChildren();
				//遍历二级子菜单---2个或4个或5个...
				for (Menu menu2 : Menus) {
					NoticeMenu mNoticeMenu = new NoticeMenu();
					if (menu2 != null) { //遍历二级子菜单时，如果二级子菜单不为空
						//将子菜单放到首页---将子菜单保存到数据库中：首页数据库或更多数据库
						SaveHomeMenu(menu2, dbUtils);
						// 将子菜单转换为json
						String menujson = GSONUtil.objectToJson(menu2);
						// 将json转换为子菜单
						mNoticeMenu = (NoticeMenu) GSONUtil.fromJson(menujson, NoticeMenu.class);
					}
					//设置二级子菜单的图标
					mNoticeMenu.setIcon(URLs.IMAGE_URL + menu2.getIcon());
					//在数据库中查找该二级子菜单
					NoticeMenu noticeMenu = dbUtils.findFirst(Selector.from(NoticeMenu.class).where(WhereBuilder.b("mid", "=", mNoticeMenu.getMid())));
					if (noticeMenu == null) {
						//保存到数据库中
						dbUtils.save(mNoticeMenu);
					} else {
						//在数据库中进行更新
						dbUtils.update(mNoticeMenu, WhereBuilder.b("mid", "=", mNoticeMenu.getMid()));
					}
				}
			} else if (menu.getName().equals("扶贫大事记")) { // 2.扶贫大事记
				Menus = new ArrayList<Menu>();
				Menus = menu.getChildren();
				for (Menu menu2 : Menus) {
					FuPinDaShiJiMenu mFuPinDaShiJiMenu = new FuPinDaShiJiMenu();
					if (menu2 != null) {
						SaveMoreMenu(menu2, dbUtils);
						// 将菜单转换为json
						String menujson = GSONUtil.objectToJson(menu2);
						// 将json转换为菜单
						mFuPinDaShiJiMenu = (FuPinDaShiJiMenu) GSONUtil.fromJson(menujson, FuPinDaShiJiMenu.class);
					}

					mFuPinDaShiJiMenu.setIcon(URLs.IMAGE_URL + menu2.getIcon());
					FuPinDaShiJiMenu fuPinDaShiJiMenu = dbUtils.findFirst(
							Selector.from(FuPinDaShiJiMenu.class).where(WhereBuilder.b("mid", "=", mFuPinDaShiJiMenu.getMid())));
					if (fuPinDaShiJiMenu == null) {
						dbUtils.save(mFuPinDaShiJiMenu);
					} else {
						dbUtils.update(mFuPinDaShiJiMenu, WhereBuilder.b("mid", "=", mFuPinDaShiJiMenu.getMid()));
					}
				}
			}else if (menu.getName().equals("信息服务")) { // 3.信息服务
				Menus = new ArrayList<Menu>();
				Menus = menu.getChildren();
				for (Menu menu2 : Menus) {
					XinXiFuWuMenu mXinXiFuWu = new XinXiFuWuMenu();
					if (menu2 != null) {
						SaveMoreMenu(menu2, dbUtils);
						// 将菜单转换为json
						String menujson = GSONUtil.objectToJson(menu2);
						// 将json转换为菜单
						mXinXiFuWu = (XinXiFuWuMenu) GSONUtil.fromJson(menujson, XinXiFuWuMenu.class);
					}
					mXinXiFuWu.setIcon(URLs.IMAGE_URL + menu2.getIcon());
					XinXiFuWuMenu xinXiFuWuMenu = dbUtils.findFirst(
							Selector.from(XinXiFuWuMenu.class).where(WhereBuilder.b("mid", "=", mXinXiFuWu.getMid())));
					if (xinXiFuWuMenu == null) {
						dbUtils.save(mXinXiFuWu);
					} else {
						dbUtils.update(mXinXiFuWu, WhereBuilder.b("mid", "=", mXinXiFuWu.getMid()));
					}
				}
			}else if (menu.getName().equals("贫困户互动")) { // 4.贫困户互动
				Menus = new ArrayList<Menu>();
				Menus = menu.getChildren();
				for (Menu menu2 : Menus) {
					PinKunHuHuDongMenu mPinKunHuHuDongMenu = new PinKunHuHuDongMenu();
					if (menu2 != null) {
						SaveMoreMenu(menu2, dbUtils);
						// 将菜单转换为json
						String menujson = GSONUtil.objectToJson(menu2);
						// 将json转换为菜单
						mPinKunHuHuDongMenu = (PinKunHuHuDongMenu) GSONUtil.fromJson(menujson, PinKunHuHuDongMenu.class);
					}
					mPinKunHuHuDongMenu.setIcon(URLs.IMAGE_URL + menu2.getIcon());
					PinKunHuHuDongMenu pinKunHuHuDongMenu = dbUtils.findFirst(
							Selector.from(PinKunHuHuDongMenu.class).where(WhereBuilder.b("mid", "=", mPinKunHuHuDongMenu.getMid())));
					if (pinKunHuHuDongMenu == null) {
						dbUtils.save(mPinKunHuHuDongMenu);
					} else {
						dbUtils.update(mPinKunHuHuDongMenu, WhereBuilder.b("mid", "=", mPinKunHuHuDongMenu.getMid()));
					}
				}
			}else if (menu.getName().equals("资金监管")) { // 5.资金监管
				Menus = new ArrayList<Menu>();
				Menus = menu.getChildren();
				for (Menu menu2 : Menus) {
					ZiJinJianGuanMenu mZinJinJianGuanMenu = new ZiJinJianGuanMenu();
					if (menu2 != null) {
						SaveMoreMenu(menu2, dbUtils);
						// 将菜单转换为json
						String menujson = GSONUtil.objectToJson(menu2);
						// 将json转换为菜单
						mZinJinJianGuanMenu = (ZiJinJianGuanMenu) GSONUtil.fromJson(menujson, ZiJinJianGuanMenu.class);
					}
					mZinJinJianGuanMenu.setIcon(URLs.IMAGE_URL + menu2.getIcon());
					ZiJinJianGuanMenu ziJinJianGuanMenu = dbUtils.findFirst(
							Selector.from(ZiJinJianGuanMenu.class).where(WhereBuilder.b("mid", "=", mZinJinJianGuanMenu.getMid())));
					if (ziJinJianGuanMenu == null) {
						dbUtils.save(mZinJinJianGuanMenu);
					} else {
						dbUtils.update(mZinJinJianGuanMenu, WhereBuilder.b("mid", "=", mZinJinJianGuanMenu.getMid()));
					}
				}
			}else if (menu.getName().equals("干部包联")) { // 6.干部包联
				Menus = new ArrayList<Menu>();
				Menus = menu.getChildren();
				for (Menu menu2 : Menus) {
					GanBuBaoLianMenu mGanBuBaoLiaoMenu = new GanBuBaoLianMenu();
					if (menu2 != null) {
						SaveMoreMenu(menu2, dbUtils);
						// 将菜单转换为json
						String menujson = GSONUtil.objectToJson(menu2);
						// 将json转换为菜单
						mGanBuBaoLiaoMenu = (GanBuBaoLianMenu) GSONUtil.fromJson(menujson, GanBuBaoLianMenu.class);
					}
					mGanBuBaoLiaoMenu.setIcon(URLs.IMAGE_URL + menu2.getIcon());
					GanBuBaoLianMenu ganBuBaoLianMenu = dbUtils.findFirst(
							Selector.from(GanBuBaoLianMenu.class).where(WhereBuilder.b("mid", "=", mGanBuBaoLiaoMenu.getMid())));
					if (ganBuBaoLianMenu == null) {
						dbUtils.save(mGanBuBaoLiaoMenu);
					} else {
						dbUtils.update(mGanBuBaoLiaoMenu, WhereBuilder.b("mid", "=", mGanBuBaoLiaoMenu.getMid()));
					}
				}
			}else if (menu.getName().equals(Common.EXTS_HELP_OBJECT_NAME)
					|| menu.getName().equals(Common.EXTS_HELP_SUBJECT_NAME)
					|| menu.getName().equals("基础信息")) { //7.基础信息--------------------------
				// 获取子菜单
				Menus = new ArrayList<Menu>();
				Menus = menu.getChildren();
				for (Menu menu2 : Menus) {
					HelpObjectMenu helpObject = new HelpObjectMenu();
					if (menu2 != null) {
						SaveHomeMenu(menu2, dbUtils); //-----------------------------------
						String menujson = GSONUtil.objectToJson(menu2);
						helpObject = (HelpObjectMenu) GSONUtil.fromJson(menujson, HelpObjectMenu.class);
					}
					helpObject.setIcon(URLs.IMAGE_URL + menu2.getIcon());
					HelpObjectMenu mHelpObject = dbUtils.findFirst(
							Selector.from(HelpObjectMenu.class).where(WhereBuilder.b("mid", "=", helpObject.getMid())));
					if (mHelpObject == null) {
						dbUtils.save(helpObject);
					} else {
						dbUtils.update(helpObject, WhereBuilder.b("mid", "=", helpObject.getMid()));
					}
				}
			} else if (menu.getName().equals(Common.EXTS_NO_POOR_PROJECT_NAME)
					|| menu.getName().equals("项目跟踪")) { //8.脱贫攻坚项目***合并---改为项目跟踪----------------------
				// 获取子菜单
				List<Menu> nopoorprojectMenu = new ArrayList<Menu>();
				nopoorprojectMenu = menu.getChildren();
				for (int j = 0; j < 3; j++) {
					SaveHomeMenu(nopoorprojectMenu.get(j), dbUtils); //-----------------------------------------
				}
				for (int i = 0; i < nopoorprojectMenu.size(); i++) {
					NoPoorProjectMenu noPoorProject = new NoPoorProjectMenu();
					if (nopoorprojectMenu.get(i) != null) {
						SaveMoreMenu(nopoorprojectMenu.get(i), dbUtils); //---------------------------------------------
						String menujson = GSONUtil.objectToJson(nopoorprojectMenu.get(i));
						noPoorProject = (NoPoorProjectMenu) GSONUtil.fromJson(menujson, NoPoorProjectMenu.class);
					}
					noPoorProject.setIcon(URLs.IMAGE_URL + nopoorprojectMenu.get(i).getIcon());
					NoPoorProjectMenu mNoPoorProject = dbUtils.findFirst(Selector.from(NoPoorProjectMenu.class)
							.where(WhereBuilder.b("mid", "=", noPoorProject.getMid())));
					if (mNoPoorProject == null) {
						dbUtils.save(noPoorProject);
					} else {
						dbUtils.update(noPoorProject, WhereBuilder.b("mid", "=", noPoorProject.getMid()));
					}
				}
			} else if (menu.getName().equals(Common.EXTS_STATISTIC)) { // 9.数据分析
				// 获取子菜单
				Menus = new ArrayList<Menu>();
				Menus = menu.getChildren();
				for (int j = 0; j < 2; j++) {
					SaveHomeMenu(Menus.get(j), dbUtils); //---------------------------------------
				}
				for (int i = 0; i < Menus.size(); i++) {
					StatisticMenu statisticMenu = new StatisticMenu();
					if (Menus.get(i) != null) {
						SaveMoreMenu(Menus.get(i), dbUtils); //-------------------------------------------
						String menujson = GSONUtil.objectToJson(Menus.get(i));
						statisticMenu = (StatisticMenu) GSONUtil.fromJson(menujson, StatisticMenu.class);
					}
					statisticMenu.setIcon(URLs.IMAGE_URL + Menus.get(i).getIcon());
					StatisticMenu mNoPoorProject = dbUtils.findFirst(
							Selector.from(StatisticMenu.class).where(WhereBuilder.b("mid", "=", statisticMenu.getMid())));
					if (mNoPoorProject == null) {
						dbUtils.save(statisticMenu);
					} else {
						dbUtils.update(statisticMenu, WhereBuilder.b("mid", "=", statisticMenu.getMid()));
					}
				}
			}
		}
	}

	/**
	 * 将菜单放到首页
	 * @param menu
	 * @param dbUtils
	 * 2016年7月7日  ZhaoDongShao
	 */
	private static void SaveHomeMenu(Menu menu, DbUtils dbUtils) throws Exception {
		HomeMenu homeMenu = new HomeMenu();
		if (menu != null) { //如果传过来的Menu对象不为空，将其赋值给HomeMenu
			String menujson = GSONUtil.objectToJson(menu);
			homeMenu = (HomeMenu) GSONUtil.fromJson(menujson, HomeMenu.class);
		}
		//设置Menu的图标
		homeMenu.setIcon(URLs.IMAGE_URL + menu.getIcon());

		//在数据里分别获取MoreMenu和HomeMenu
		MoreMenu mMore = dbUtils
				.findFirst(Selector.from(MoreMenu.class)
				.where(WhereBuilder.b("mid", "=", homeMenu.getMid())));
		HomeMenu mHome = dbUtils
				.findFirst(Selector.from(HomeMenu.class)
				.where(WhereBuilder.b("mid", "=", homeMenu.getMid())));

		if (mMore == null && mHome == null) { //如果两者都为空就保存到数据库
			dbUtils.save(homeMenu);
		} else if (mMore == null && mHome != null) { //如果HomeMenu不为空就更新HomeMenu的数据库
			dbUtils.update(homeMenu, WhereBuilder.b("mid", "=", homeMenu.getMid()));
		} else if (mMore != null && mHome == null) { //如果MoreMenu不为空就更新MoreMenu的数据库
			String home = GSONUtil.objectToJson(homeMenu);
			MoreMenu more2 = (MoreMenu) GSONUtil.fromJson(home, MoreMenu.class);
			dbUtils.update(more2, WhereBuilder.b("mid", "=", homeMenu.getMid()));
		}
	}

	/**
	 * 保存到更多菜里面
	 * @param dbUtils
	 * @param menu
	 * 2016年5月16日  ZhaoDongShao
	 */
	private static void SaveMoreMenu(Menu menu, DbUtils dbUtils) throws Exception {
		MoreMenu more = new MoreMenu();
		if (menu != null) {
			String menujson = GSONUtil.objectToJson(menu);
			more = (MoreMenu) GSONUtil.fromJson(menujson, MoreMenu.class);
		}
		more.setIcon(URLs.IMAGE_URL + menu.getIcon());

		MoreMenu mMore = dbUtils
				.findFirst(Selector.from(MoreMenu.class)
				.where(WhereBuilder.b("mid", "=", more.getMid())));
		HomeMenu mHome = dbUtils
				.findFirst(Selector.from(HomeMenu.class)
				.where(WhereBuilder.b("mid", "=", more.getMid())));

		if (mMore == null && mHome == null) {
			dbUtils.save(more);
		} else if (mMore == null && mHome != null) {
			String home = GSONUtil.objectToJson(more);
			HomeMenu homeMenu = (HomeMenu) GSONUtil.fromJson(home, HomeMenu.class);
			dbUtils.update(homeMenu, WhereBuilder.b("mid", "=", more.getMid()));
		} else if (mMore != null && mHome == null) {
			String home = GSONUtil.objectToJson(more);
			MoreMenu more2 = (MoreMenu) GSONUtil.fromJson(home, MoreMenu.class);
			dbUtils.update(more2, WhereBuilder.b("mid", "=", more.getMid()));
		}
	}

	/**
	 * 删除不需要的菜单
	 * 2016年8月21日
	 * ZhaoDongShao
	 */
	public static void deleteMenu(List<Menu> menus) throws Exception {
		//获取数据库操作对象
		dbUtils = MyApplication.getInstance().getDbUtilsInstance(Common.LoginName);
		List<Menu> Menus = null;
		//遍历侧滑菜单---9个
		for (Menu menu : menus) {
			// 先判断侧滑菜单的名字
			if (menu.getName().equals(Common.EXTS_NOTIC_NAME)) { //1.通知公告
				Menus = new ArrayList<Menu>();
				// 然后在拿到该侧滑菜单下的项目管理对象---即通知栏和公告栏
				Menus = menu.getChildren();
				if (StringUtil.isNotEmpty(Menus)) { //如果二级子菜单不为空
					// 获取数据库中存放的通知公告的集合
					List<NoticeMenu> noticeMenus = dbUtils.findAll(Selector.from(NoticeMenu.class));
					if (StringUtil.isNotEmpty(noticeMenus)) {
						//遍历数据库中的集合
						for (int i = 0; i < noticeMenus.size(); i++) {
							//遍历该侧滑菜单下的项目管理对象---即通知栏和公告栏的集合
							for (int j = 0; j < Menus.size(); j++) {
								if (noticeMenus.get(i).mid.equals(Menus.get(j).mid)) {
									//将从数据库中获取到的集合删除
									noticeMenus.remove(i);
								}
							}
						}

						//如果此时集合的大小还大于零
						if (noticeMenus.size() > 0) {
							for (int i = 0; i < noticeMenus.size(); i++) {
								String json = GSONUtil.objectToJson(noticeMenus.get(i));
								deleteHome(json);
								deleteMore(json);
							}
							dbUtils.deleteAll(noticeMenus);
						}
					}
				}
			}else if (menu.getName().equals("扶贫大事记")) { //2.扶贫大事记
				Menus = new ArrayList<Menu>();
				Menus = menu.getChildren();
				if (StringUtil.isNotEmpty(Menus)) {
					List<FuPinDaShiJiMenu> fuPinDaShiJiMenus = dbUtils.findAll(Selector.from(FuPinDaShiJiMenu.class));
					if (StringUtil.isNotEmpty(fuPinDaShiJiMenus)) {
						for (int i = 0; i < fuPinDaShiJiMenus.size(); i++) {
							for (int j = 0; j < Menus.size(); j++) {
								if (fuPinDaShiJiMenus.get(i).mid.equals(Menus.get(j).mid)) {
									fuPinDaShiJiMenus.remove(i);
								}
							}
						}
						if (fuPinDaShiJiMenus.size() > 0) {
							for (int i = 0; i < fuPinDaShiJiMenus.size(); i++) {
								String json = GSONUtil.objectToJson(fuPinDaShiJiMenus.get(i));
								deleteHome(json);
								deleteMore(json);
							}
							dbUtils.deleteAll(fuPinDaShiJiMenus);
						}
					}
				}
			}else if (menu.getName().equals("信息服务")) { //3.信息服务
				Menus = new ArrayList<Menu>();
				Menus = menu.getChildren();
				if (StringUtil.isNotEmpty(Menus)) {
					List<XinXiFuWuMenu> xinXiFuWuMenus = dbUtils.findAll(Selector.from(XinXiFuWuMenu.class));
					if (StringUtil.isNotEmpty(xinXiFuWuMenus)) {
						for (int i = 0; i < xinXiFuWuMenus.size(); i++) {
							for (int j = 0; j < Menus.size(); j++) {
								if (xinXiFuWuMenus.get(i).mid.equals(Menus.get(j).mid)) {
									xinXiFuWuMenus.remove(i);
								}
							}
						}
						if (xinXiFuWuMenus.size() > 0) {
							for (int i = 0; i < xinXiFuWuMenus.size(); i++) {
								String json = GSONUtil.objectToJson(xinXiFuWuMenus.get(i));
								deleteHome(json);
								deleteMore(json);
							}
							dbUtils.deleteAll(xinXiFuWuMenus);
						}
					}
				}
			}else if (menu.getName().equals("资金监管")) { //4.资金监管
				Menus = new ArrayList<Menu>();
				Menus = menu.getChildren();
				if (StringUtil.isNotEmpty(Menus)) {
					List<ZiJinJianGuanMenu> ziJinJianGuanMenus = dbUtils.findAll(Selector.from(ZiJinJianGuanMenu.class));
					if (StringUtil.isNotEmpty(ziJinJianGuanMenus)) {
						for (int i = 0; i < ziJinJianGuanMenus.size(); i++) {
							for (int j = 0; j < Menus.size(); j++) {
								if (ziJinJianGuanMenus.get(i).mid.equals(Menus.get(j).mid)) {
									ziJinJianGuanMenus.remove(i);
								}
							}
						}
						if (ziJinJianGuanMenus.size() > 0) {
							for (int i = 0; i < ziJinJianGuanMenus.size(); i++) {
								String json = GSONUtil.objectToJson(ziJinJianGuanMenus.get(i));
								deleteHome(json);
								deleteMore(json);
							}
							dbUtils.deleteAll(ziJinJianGuanMenus);
						}
					}
				}
			}else if (menu.getName().equals("干部包联")) { //5.干部包联
				Menus = new ArrayList<Menu>();
				Menus = menu.getChildren();
				if (StringUtil.isNotEmpty(Menus)) {
					List<GanBuBaoLianMenu> ganBuBaoLianMenus = dbUtils.findAll(Selector.from(GanBuBaoLianMenu.class));
					if (StringUtil.isNotEmpty(ganBuBaoLianMenus)) {
						for (int i = 0; i < ganBuBaoLianMenus.size(); i++) {
							for (int j = 0; j < Menus.size(); j++) {
								if (ganBuBaoLianMenus.get(i).mid.equals(Menus.get(j).mid)) {
									ganBuBaoLianMenus.remove(i);
								}
							}
						}
						if (ganBuBaoLianMenus.size() > 0) {
							for (int i = 0; i < ganBuBaoLianMenus.size(); i++) {
								String json = GSONUtil.objectToJson(ganBuBaoLianMenus.get(i));
								deleteHome(json);
								deleteMore(json);
							}
							dbUtils.deleteAll(ganBuBaoLianMenus);
						}
					}
				}
			}else if (menu.getName().equals("贫困户互动")) { //6.贫困户互动
				Menus = new ArrayList<Menu>();
				Menus = menu.getChildren();
				if (StringUtil.isNotEmpty(Menus)) {
					List<PinKunHuHuDongMenu> pinKunHuHuDongMenus = dbUtils.findAll(Selector.from(PinKunHuHuDongMenu.class));
					if (StringUtil.isNotEmpty(pinKunHuHuDongMenus)) {
						for (int i = 0; i < pinKunHuHuDongMenus.size(); i++) {
							for (int j = 0; j < Menus.size(); j++) {
								if (pinKunHuHuDongMenus.get(i).mid.equals(Menus.get(j).mid)) {
									pinKunHuHuDongMenus.remove(i);
								}
							}
						}
						if (pinKunHuHuDongMenus.size() > 0) {
							for (int i = 0; i < pinKunHuHuDongMenus.size(); i++) {
								String json = GSONUtil.objectToJson(pinKunHuHuDongMenus.get(i));
								deleteHome(json);
								deleteMore(json);
							}
							dbUtils.deleteAll(pinKunHuHuDongMenus);
						}
					}
				}
			}else if (
//					menu.getName().equals(Common.EXTS_HELP_OBJECT_NAME)
//					|| menu.getName().equals(Common.EXTS_HELP_SUBJECT_NAME)
//					||
							menu.getName().equals("基础信息")) { //7.扶贫对象或者帮扶主体--基础信息
				// 获取子菜单
				Menus = new ArrayList<Menu>();
				Menus = menu.getChildren();
				if (StringUtil.isNotEmpty(Menus)) {
					List<HelpObjectMenu> helpObjectMenus = dbUtils.findAll(Selector.from(HelpObjectMenu.class));
					if (StringUtil.isNotEmpty(helpObjectMenus)) {
						for (int i = 0; i < helpObjectMenus.size(); i++) {
							for (int j = 0; j < Menus.size(); j++) {
								if (helpObjectMenus.get(i).mid.equals(Menus.get(j).mid)) {
									helpObjectMenus.remove(i);
								}
							}
						}
						if (helpObjectMenus.size() > 0) {
							for (int i = 0; i < helpObjectMenus.size(); i++) {
								String json = GSONUtil.objectToJson(helpObjectMenus.get(i));
								deleteHome(json);
								deleteMore(json);
							}
							dbUtils.deleteAll(helpObjectMenus);
						}
					}
				}
			} else if (menu.getName().equals(Common.EXTS_NO_POOR_PROJECT_NAME)
					   || menu.getName().equals("项目跟踪")) { //8.攻坚脱贫项目和项目跟踪
				// 获取子菜单
				List<Menu> nopoorprojectMenu = new ArrayList<Menu>();
				nopoorprojectMenu = menu.getChildren();
				if (StringUtil.isNotEmpty(Menus)) {
					List<NoPoorProjectMenu> noPoorProjectMenus = dbUtils.findAll(Selector.from(NoPoorProjectMenu.class));
					if (StringUtil.isNotEmpty(noPoorProjectMenus)) {
						for (int i = 0; i < noPoorProjectMenus.size(); i++) {
							for (int j = 0; j < nopoorprojectMenu.size(); j++) {
								if (noPoorProjectMenus.get(i).mid.equals(nopoorprojectMenu.get(j).mid)) {
									noPoorProjectMenus.remove(i);
								}
							}
						}
						if (noPoorProjectMenus.size() > 0) {
							for (int i = 0; i < noPoorProjectMenus.size(); i++) {
								String json = GSONUtil.objectToJson(noPoorProjectMenus.get(i));
								deleteHome(json);
								deleteMore(json);
							}
							dbUtils.deleteAll(noPoorProjectMenus);
						}
					}
				}
			} else if (menu.getName().equals(Common.EXTS_STATISTIC)) { // 9.数据分析
				// 获取子菜单
				Menus = new ArrayList<Menu>();
				Menus = menu.getChildren();
				if (StringUtil.isNotEmpty(Menus)) {
					List<StatisticMenu> statisticMenus = dbUtils.findAll(Selector.from(StatisticMenu.class));
					if (StringUtil.isNotEmpty(statisticMenus)) {
						for (int i = 0; i < statisticMenus.size(); i++) {
							for (int j = 0; j < Menus.size(); j++) {
								if (statisticMenus.get(i).mid.equals(Menus.get(j).mid)) {
									statisticMenus.remove(i);
								}
							}
						}
						if (statisticMenus.size() > 0) {
							for (int i = 0; i < statisticMenus.size(); i++) {
								String json = GSONUtil.objectToJson(statisticMenus.get(i));
								deleteHome(json);
								deleteMore(json);
							}
							dbUtils.deleteAll(statisticMenus);
						}
					}
				}
			}
//			else if (menu.getName().equals("项目跟踪")) { // 10.专项扶贫项目---改为项目跟踪
//				// 获取子菜单
//				Menus = new ArrayList<Menu>();
//				Menus = menu.getChildren();
//				if (StringUtil.isNotEmpty(Menus)) {
//					List<ZhuanXiangFuPinMenu> statisticMenus = dbUtils.findAll(Selector.from(ZhuanXiangFuPinMenu.class));
//					if (StringUtil.isNotEmpty(statisticMenus)) {
//						for (int i = 0; i < statisticMenus.size(); i++) {
//							for (int j = 0; j < Menus.size(); j++) {
//								if (statisticMenus.get(i).mid.equals(Menus.get(j).mid)) {
//									statisticMenus.remove(i);
//								}
//							}
//						}
//						if (statisticMenus.size() > 0) {
//							for (int i = 0; i < statisticMenus.size(); i++) {
//								String json = GSONUtil.objectToJson(statisticMenus.get(i));
//								deleteHome(json);
//								deleteMore(json);
//							}
//							dbUtils.deleteAll(statisticMenus);
//						}
//					}
//				}
//			}
		}
	}

	/**
	 * 删除更多里面不需要的的菜单
	 * 2016年8月21日
	 * ZhaoDongShao
	 * @param json
	 */
	private static void deleteMore(String json) throws Exception {
		if (StringUtil.isNotEmpty(json)) {
			MoreMenu menu = (MoreMenu) GSONUtil.fromJson(json, MoreMenu.class);
			if (StringUtil.isNotEmpty(menu)) {
				dbUtils.delete(menu);
			}
		}
	}

	/**
	 * 删除首页中的不需要的菜单
	 * 2016年8月21日
	 * ZhaoDongShao
	 * @param json
	 */
	private static void deleteHome(String json) throws Exception {
		if (StringUtil.isNotEmpty(json)) {
			HomeMenu menu = (HomeMenu) GSONUtil.fromJson(json, HomeMenu.class);
			if (StringUtil.isNotEmpty(menu)) {
				dbUtils.delete(menu);
			}
		}
	}

	/**
	 * 将数据转换保存到map里面
	 * @param mAppEntity
	 * @return
	 * 2016年6月1日  ZhaoDongShao
	 */
	public static List<ViewItem> getMapList(ProjectInfoAppEntity mAppEntity) {
		List<ViewItem> lItems = new ArrayList<ViewItem>();
		lItems.add(new ViewItem(0, new MapEntity("项目", "")));
		lItems.add(new ViewItem(1, new MapEntity("项目类型", mAppEntity.getProjectcategoryidcall())));
		lItems.add(new ViewItem(1, new MapEntity("项目名称", mAppEntity.getProjectname())));
		lItems.add(new ViewItem(2, new MapEntity("建设内容", mAppEntity.getBuildcontent())));
		lItems.add(new ViewItem(1, new MapEntity("扶贫对象", mAppEntity.getProjectcategoryidcall())));
		lItems.add(new ViewItem(1, new MapEntity("项目效益", mAppEntity.getProjecteffect())));
		lItems.add(new ViewItem(1, new MapEntity("项目责任单位", mAppEntity.getDutydeptname())));
		lItems.add(new ViewItem(1, new MapEntity("项目责任人", mAppEntity.getDutyperson())));
		lItems.add(new ViewItem(1, new MapEntity("联系方式", mAppEntity.getDutypersonphone())));
		lItems.add(new ViewItem(1, new MapEntity("项目进度", mAppEntity.getProjectscheduleidcall())));
		lItems.add(new ViewItem(1, new MapEntity("项目状态", mAppEntity.getProjectstatusidcall())));
		lItems.add(new ViewItem(1, new MapEntity("中标单位名称", mAppEntity.getZhongbiaocompany())));
		lItems.add(new ViewItem(0, new MapEntity("项目时间", "")));
		lItems.add(new ViewItem(1, new MapEntity("计划开始时间", CommonUtil.getSpliteDate(mAppEntity.getPlanstartdate()))));
		lItems.add(new ViewItem(1, new MapEntity("计划结束时间", CommonUtil.getSpliteDate(mAppEntity.getPlanenddate()))));
		lItems.add(new ViewItem(1, new MapEntity("立项日期", CommonUtil.getSpliteDate(mAppEntity.getLixiangdate()))));
		lItems.add(new ViewItem(1, new MapEntity("报备日期", CommonUtil.getSpliteDate(mAppEntity.getBaobeidate()))));
		lItems.add(new ViewItem(1, new MapEntity("招标日期", CommonUtil.getSpliteDate(mAppEntity.getZhaobiaodate()))));
		lItems.add(new ViewItem(1, new MapEntity("开工日期", CommonUtil.getSpliteDate(mAppEntity.getKaigongdate()))));
		lItems.add(new ViewItem(1, new MapEntity("竣工日期", CommonUtil.getSpliteDate(mAppEntity.getJungongdate()))));
		lItems.add(new ViewItem(1, new MapEntity("验收日期", CommonUtil.getSpliteDate(mAppEntity.getYanshoudate()))));
		lItems.add(new ViewItem(0, new MapEntity("项目资金", "")));
		lItems.add(new ViewItem(1, new MapEntity("中省资金", String.valueOf(mAppEntity.getZszj()) + "万元")));
		lItems.add(new ViewItem(1, new MapEntity("市级资金", String.valueOf(mAppEntity.getSjzj()) + "万元")));
		lItems.add(new ViewItem(1, new MapEntity("镇村配套", String.valueOf(mAppEntity.getZcpt()) + "万元")));
		lItems.add(new ViewItem(1, new MapEntity("群众自筹", String.valueOf(mAppEntity.getQzzc()) + "万元")));

		lItems.add(new ViewItem(1, new MapEntity("已完成投资", String.valueOf(mAppEntity.getYwctz()) + "万元")));
		lItems.add(new ViewItem(1, new MapEntity("投资比例",
				String.valueOf(CommonUtil.getBili(mAppEntity.getYwctz(), mAppEntity.getInvesttotal())))));

		lItems.add(new ViewItem(1, new MapEntity("投资合计", String.valueOf(mAppEntity.getInvesttotal()) + "万元")));
		return lItems;
	}
}
