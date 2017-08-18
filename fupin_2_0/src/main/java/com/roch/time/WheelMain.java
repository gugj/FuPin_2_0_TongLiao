package com.roch.time;

import android.os.Handler;
import android.view.View;

import com.roch.fupin_2_0.R;

import java.util.Arrays;
import java.util.List;

public class WheelMain {
	private View view;
	private WheelView wv_year;
	private WheelView wv_month;
	private WheelView wv_day;
	private WheelView wv_hours;
	private WheelView wv_mins;
	public int screenheight;
	private boolean hasSelectTime;
	private boolean hasSelectDay;
	private boolean hasSelectMonth;
	private static int START_YEAR = 1990, END_YEAR = 2100;

	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
	}

	public static int getSTART_YEAR() {
		return START_YEAR;
	}

	public static void setSTART_YEAR(int sTART_YEAR) {
		START_YEAR = sTART_YEAR;
	}

	public static int getEND_YEAR() {
		return END_YEAR;
	}

	public static void setEND_YEAR(int eND_YEAR) {
		END_YEAR = eND_YEAR;
	}

	public WheelMain(View view) {
		super();
		this.view = view;
		hasSelectTime = false;
		hasSelectDay = true;
		hasSelectMonth = true;
		setView(view);
	}

	public WheelMain(View view, boolean hasSelectTime, boolean hasSelectDay, boolean hasSelectMonth) {
		super();
		this.view = view;
		this.hasSelectMonth = hasSelectMonth;
		this.hasSelectDay = hasSelectDay;
		this.hasSelectTime = hasSelectTime;
		setView(view);
	}

	/**
	 * 初始化到天
	 * @param year
	 * @param month
	 * @param day
	 */
	public void initDateTimePicker(int year, int month, int day) {
		this.initDateTimePickera(year, month, day, 0, 0);
	}

	/**
	 * 初始化到天  生日的时候使用
	 * @param year
	 * @param month
	 * @param day
	 */
	public void initBirthdayPicker(int year, int month, int day) {
		this.initBirthdayPicker(year, month, day, 0, 0);
	}

	/**
	 * 初始化到年
	 * @param year
	 */
	public void initDateTimePickerYear(int year) {
		this.initQuarterPickeraYear(year);
	}

	/**
	 * 初始化到季度
	 * @param year
	 * @param quarter
	 */
	public void initDateTimePicker(int year, int quarter) {
		this.initQuarterPickera(year, quarter);
	}

	/**
	 * 初始化到季度
	 * @param hour
	 * @param minute
	 */
	public void initDateTimePickerHour(int hour, int minute) {
		this.initQuarterPickeraHour(hour, minute);
	}

	/**
	 * 初始化到时分
	 * @param year
	 * @param month
	 * @param day
	 * @param hour
	 * @param minute
	 */
	public void initDateTimePicker(int year, int month, int day, int hour, int minute) {
		this.initDateTimePickera(year, month, day, hour, minute);
	}

	/**
	 * @Description: TODO 弹出日期时间选择器
	 */
	public void initDateTimePickera(int year, int month, int day, int h, int m) {
		// int year = calendar.get(Calendar.YEAR);
		// int month = calendar.get(Calendar.MONTH);
		// int day = calendar.get(Calendar.DATE);
		// 添加大小月月份并将其转换为list,方便之后的判断
		String[] months_big = { "1", "3", "5", "7", "8", "10", "12" };
		String[] months_little = { "4", "6", "9", "11" };

		final List<String> list_big = Arrays.asList(months_big);
		final List<String> list_little = Arrays.asList(months_little);

		// 年
		wv_year = (WheelView) view.findViewById(R.id.year);
		wv_year.setAdapter(new NumericWheelAdapter(START_YEAR, END_YEAR));// 设置"年"的显示数据
		wv_year.setCyclic(true);// 可循环滚动
		wv_year.setLabel("年");// 添加文字
		wv_year.setCurrentItem(year - START_YEAR);// 初始化时显示的数据

		// 月
		wv_month = (WheelView) view.findViewById(R.id.month);
		wv_month.setAdapter(new NumericWheelAdapter(1, 12));
		wv_month.setCyclic(true);
		wv_month.setLabel("月");
		wv_month.setCurrentItem(month);

		// 日
		wv_day = (WheelView) view.findViewById(R.id.day);
		wv_day.setCyclic(true);
		// 判断大小月及是否闰年,用来确定"日"的数据
		if (list_big.contains(String.valueOf(month + 1))) {
			wv_day.setAdapter(new NumericWheelAdapter(1, 31));
		} else if (list_little.contains(String.valueOf(month + 1))) {
			wv_day.setAdapter(new NumericWheelAdapter(1, 30));
		} else {
			// 闰年
			if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)
				wv_day.setAdapter(new NumericWheelAdapter(1, 29));
			else
				wv_day.setAdapter(new NumericWheelAdapter(1, 28));
		}
		wv_day.setLabel("日");
		if (hasSelectTime) {

		}
		wv_day.setCurrentItem(day - 1);

		wv_hours = (WheelView) view.findViewById(R.id.hour);
		wv_mins = (WheelView) view.findViewById(R.id.min);
		if (hasSelectTime) {
			wv_hours.setVisibility(View.VISIBLE);
			wv_mins.setVisibility(View.VISIBLE);

			wv_hours.setAdapter(new NumericWheelAdapter(0, 23));
			wv_hours.setCyclic(true);// 可循环滚动
			wv_hours.setLabel("时");// 添加文字
			wv_hours.setCurrentItem(h);

			wv_mins.setAdapter(new NumericWheelAdapter(0, 59));
			wv_mins.setCyclic(true);// 可循环滚动
			wv_mins.setLabel("分");// 添加文字
			wv_mins.setCurrentItem(m);
		} else {
			wv_hours.setVisibility(View.GONE);
			wv_mins.setVisibility(View.GONE);
		}
		if (hasSelectDay) {

			wv_day.setVisibility(View.VISIBLE);

		}else {
			wv_day.setVisibility(View.GONE);
		}
		if (hasSelectMonth) {
			
			wv_month.setVisibility(View.VISIBLE);
			
		}else {
			wv_month.setVisibility(View.GONE);
		}

		// 添加"年"监听
		OnWheelChangedListener wheelListener_year = new OnWheelChangedListener() {
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				int year_num = newValue + START_YEAR;
				// 判断大小月及是否闰年,用来确定"日"的数据
				if (list_big.contains(String.valueOf(wv_month.getCurrentItem() + 1))) {
					wv_day.setAdapter(new NumericWheelAdapter(1, 31));
				} else if (list_little.contains(String.valueOf(wv_month.getCurrentItem() + 1))) {
					wv_day.setAdapter(new NumericWheelAdapter(1, 30));
				} else {
					if ((year_num % 4 == 0 && year_num % 100 != 0) || year_num % 400 == 0) {
						wv_day.setAdapter(new NumericWheelAdapter(1, 29));
						int day = wv_day.getCurrentItem()+1;
						if (day > 29) {
							wv_day.setCurrentItem(28);
						} else {
							wv_day.setCurrentItem(day - 1);
						}
					}

					else {
						wv_day.setAdapter(new NumericWheelAdapter(1, 28));
						int day = wv_day.getCurrentItem()+1;
						if (day > 28) {
							wv_day.setCurrentItem(27);
						} else {
							wv_day.setCurrentItem(day - 1);
						}
					}
				}
			}
		};
		// 添加"月"监听
		OnWheelChangedListener wheelListener_month = new OnWheelChangedListener() {
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				int month_num = newValue + 1;
				// 判断大小月及是否闰年,用来确定"日"的数据
				if (list_big.contains(String.valueOf(month_num))) {
					wv_day.setAdapter(new NumericWheelAdapter(1, 31));
				} else if (list_little.contains(String.valueOf(month_num))) {
					wv_day.setAdapter(new NumericWheelAdapter(1, 30));
					int day = wv_day.getCurrentItem() + 1;
					if (day> 30) {
						wv_day.setCurrentItem(29);
					} else {
						wv_day.setCurrentItem(day - 1);
					}
				} else {
					if (((wv_year.getCurrentItem() + START_YEAR) % 4 == 0 && (wv_year.getCurrentItem() + START_YEAR) % 100 != 0) || (wv_year.getCurrentItem() + START_YEAR) % 400 == 0) {
						wv_day.setAdapter(new NumericWheelAdapter(1, 29));
						int day = wv_day.getCurrentItem() + 1;
						// 判断如果是闰年
						if (day > 29) {
							wv_day.setCurrentItem(28);
						} else {
							wv_day.setCurrentItem(day - 1);
						}
					} else {
						wv_day.setAdapter(new NumericWheelAdapter(1, 28));
						int day = wv_day.getCurrentItem() + 1;
						if (day > 28) {
							wv_day.setCurrentItem(27);
						} else {
							wv_day.setCurrentItem(day - 1);
						}
					}
				}
			}

		};
		wv_year.addChangingListener(wheelListener_year);
		wv_month.addChangingListener(wheelListener_month);

		// 根据屏幕密度来指定选择器字体的大小(不同屏幕可能不同)
		int textSize = 0;
		if (hasSelectTime)
			textSize = (screenheight / 100) * 3;
		else
			textSize = (screenheight / 100) * 4;
		wv_day.TEXT_SIZE = textSize;
		wv_month.TEXT_SIZE = textSize;
		wv_year.TEXT_SIZE = textSize;
		wv_hours.TEXT_SIZE = textSize;
		wv_mins.TEXT_SIZE = textSize;

	}
	/**
	 * @Description: TODO 弹出日期时间选择器
	 */
	public void initBirthdayPicker(int year, int month, int day, int h, int m) {
		// int year = calendar.get(Calendar.YEAR);
		// int month = calendar.get(Calendar.MONTH);
		// int day = calendar.get(Calendar.DATE);
		// 添加大小月月份并将其转换为list,方便之后的判断
		String[] months_big = { "1", "3", "5", "7", "8", "10", "12" };
		String[] months_little = { "4", "6", "9", "11" };

		final List<String> list_big = Arrays.asList(months_big);
		final List<String> list_little = Arrays.asList(months_little);

		// 年
		wv_year = (WheelView) view.findViewById(R.id.year);
		wv_year.setAdapter(new NumericWheelAdapter(START_YEAR, END_YEAR));// 设置"年"的显示数据
		wv_year.setCyclic(true);// 可循环滚动
		wv_year.setLabel("年");// 添加文字
		wv_year.setCurrentItem(year-START_YEAR );// 初始化时显示的数据

		// 月
		wv_month = (WheelView) view.findViewById(R.id.month);
		wv_month.setAdapter(new NumericWheelAdapter(1, 12));
		wv_month.setCyclic(true);
		wv_month.setLabel("月");
		wv_month.setCurrentItem(month);

		// 日
		wv_day = (WheelView) view.findViewById(R.id.day);
		wv_day.setCyclic(true);
		// 判断大小月及是否闰年,用来确定"日"的数据
		if (list_big.contains(String.valueOf(month + 1))) {
			wv_day.setAdapter(new NumericWheelAdapter(1, 31));
		} else if (list_little.contains(String.valueOf(month + 1))) {
			wv_day.setAdapter(new NumericWheelAdapter(1, 30));
		} else {
			// 闰年
			if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)
				wv_day.setAdapter(new NumericWheelAdapter(1, 29));
			else
				wv_day.setAdapter(new NumericWheelAdapter(1, 28));
		}
		wv_day.setLabel("日");
		if (hasSelectTime) {

		}
		wv_day.setCurrentItem(day - 1);

		wv_hours = (WheelView) view.findViewById(R.id.hour);
		wv_mins = (WheelView) view.findViewById(R.id.min);
		if (hasSelectTime) {
			wv_hours.setVisibility(View.VISIBLE);
			wv_mins.setVisibility(View.VISIBLE);

			wv_hours.setAdapter(new NumericWheelAdapter(0, 23));
			wv_hours.setCyclic(true);// 可循环滚动
			wv_hours.setLabel("时");// 添加文字
			wv_hours.setCurrentItem(h);

			wv_mins.setAdapter(new NumericWheelAdapter(0, 59));
			wv_mins.setCyclic(true);// 可循环滚动
			wv_mins.setLabel("分");// 添加文字
			wv_mins.setCurrentItem(m);
		} else {
			wv_hours.setVisibility(View.GONE);
			wv_mins.setVisibility(View.GONE);
		}

		if (hasSelectDay) {

			wv_day.setVisibility(View.VISIBLE);

		}else {
			wv_day.setVisibility(View.GONE);
		}

		// 添加"年"监听
		OnWheelChangedListener wheelListener_year = new OnWheelChangedListener() {
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				int year_num = newValue + START_YEAR;
				// 判断大小月及是否闰年,用来确定"日"的数据
				if (list_big.contains(String.valueOf(wv_month.getCurrentItem() + 1))) {
					wv_day.setAdapter(new NumericWheelAdapter(1, 31));
				} else if (list_little.contains(String.valueOf(wv_month.getCurrentItem() + 1))) {
					wv_day.setAdapter(new NumericWheelAdapter(1, 30));
				} else {
					if ((year_num % 4 == 0 && year_num % 100 != 0) || year_num % 400 == 0) {
						wv_day.setAdapter(new NumericWheelAdapter(1, 29));
						int day = wv_day.getCurrentItem()+1;
						if (day > 29) {
							wv_day.setCurrentItem(28);
						} else {
							wv_day.setCurrentItem(day - 1);
						}
					}

					else {
						wv_day.setAdapter(new NumericWheelAdapter(1, 28));
						int day = wv_day.getCurrentItem()+1;
						if (day > 28) {
							wv_day.setCurrentItem(27);
						} else {
							wv_day.setCurrentItem(day - 1);
						}
					}
				}
			}
		};
		// 添加"月"监听
		OnWheelChangedListener wheelListener_month = new OnWheelChangedListener() {
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				int month_num = newValue + 1;
				// 判断大小月及是否闰年,用来确定"日"的数据
				if (list_big.contains(String.valueOf(month_num))) {
					wv_day.setAdapter(new NumericWheelAdapter(1, 31));
				} else if (list_little.contains(String.valueOf(month_num))) {
					wv_day.setAdapter(new NumericWheelAdapter(1, 30));
					int day = wv_day.getCurrentItem() + 1;
					if (day> 30) {
						wv_day.setCurrentItem(29);
					} else {
						wv_day.setCurrentItem(day - 1);
					}
				} else {
					if (((wv_year.getCurrentItem() + START_YEAR) % 4 == 0 && (wv_year.getCurrentItem() + START_YEAR) % 100 != 0) || (wv_year.getCurrentItem() + START_YEAR) % 400 == 0) {
						wv_day.setAdapter(new NumericWheelAdapter(1, 29));
						int day = wv_day.getCurrentItem() + 1;
						// 判断如果是闰年
						if (day > 29) {
							wv_day.setCurrentItem(28);
						} else {
							wv_day.setCurrentItem(day - 1);
						}
					} else {
						wv_day.setAdapter(new NumericWheelAdapter(1, 28));
						int day = wv_day.getCurrentItem() + 1;
						if (day > 28) {
							wv_day.setCurrentItem(27);
						} else {
							wv_day.setCurrentItem(day - 1);
						}
					}
				}
			}

		};
		wv_year.addChangingListener(wheelListener_year);
		wv_month.addChangingListener(wheelListener_month);

		// 根据屏幕密度来指定选择器字体的大小(不同屏幕可能不同)
		int textSize = 0;
		if (hasSelectTime)
			textSize = (screenheight / 100) * 3;
		else
			textSize = (screenheight / 100) * 4;
		wv_day.TEXT_SIZE = textSize;
		wv_month.TEXT_SIZE = textSize;
		wv_year.TEXT_SIZE = textSize;
		wv_hours.TEXT_SIZE = textSize;
		wv_mins.TEXT_SIZE = textSize;

	}

	/**
	 * @Description: TODO 弹出日期时间选择器(年)
	 */
	@SuppressWarnings("unused")
	public void initQuarterPickeraYear(int year) {
		// int year = calendar.get(Calendar.YEAR);
		// int month = calendar.get(Calendar.MONTH);
		// int day = calendar.get(Calendar.DATE);
		// 添加大小月月份并将其转换为list,方便之后的判断
		// 年
		wv_year = (WheelView) view.findViewById(R.id.year);
		wv_year.setAdapter(new NumericWheelAdapter(START_YEAR, END_YEAR));// 设置"年"的显示数据
		wv_year.setCyclic(true);// 可循环滚动
		wv_year.setLabel("年");// 添加文字
		wv_year.setCurrentItem(year - START_YEAR);// 初始化时显示的数据
		// wv_year.set

		// // // 月
		//		wv_month = (WheelView) view.findViewById(R.id.month);
		//		wv_month.setVisibility(View.GONE);
		//		wv_day = (WheelView) view.findViewById(R.id.day);
		//		wv_day.setVisibility(View.GONE);
		//		wv_hours = (WheelView) view.findViewById(R.id.hour);
		//		wv_hours.setVisibility(View.GONE);
		//		wv_mins = (WheelView) view.findViewById(R.id.min);
		//		wv_mins.setVisibility(View.GONE);
		// 添加"年"监听
		OnWheelChangedListener wheelListener_year = new OnWheelChangedListener() {
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				/*
				 * int year_num = newValue + START_YEAR; //
				 * 判断大小月及是否闰年,用来确定"日"的数据 if
				 * (list_big.contains(String.valueOf(wv_month.getCurrentItem() +
				 * 1))) { wv_day.setAdapter(new NumericWheelAdapter(1, 31)); }
				 * else if
				 * (list_little.contains(String.valueOf(wv_month.getCurrentItem
				 * () + 1))) { wv_day.setAdapter(new NumericWheelAdapter(1,
				 * 30)); } else { if ((year_num % 4 == 0 && year_num % 100 != 0)
				 * || year_num % 400 == 0) wv_day.setAdapter(new
				 * NumericWheelAdapter(1, 29)); else wv_day.setAdapter(new
				 * NumericWheelAdapter(1, 28)); }
				 */
			}
		};

		// 根据屏幕密度来指定选择器字体的大小(不同屏幕可能不同)
		int textSize = 0;
		if (hasSelectTime)
			textSize = (screenheight / 100) * 3;
		else
			textSize = (screenheight / 100) * 4;
		// wv_month.TEXT_SIZE = textSize;
		wv_year.TEXT_SIZE = textSize;
		//		wv_year.setPadding(0, 0, 200, 0);

	}

	/**
	 * @Description: TODO 弹出日期时间选择器(季度)
	 */
	@SuppressWarnings("unused")
	public void initQuarterPickera(int year, int quarter) {
		// int year = calendar.get(Calendar.YEAR);
		// int month = calendar.get(Calendar.MONTH);
		// int day = calendar.get(Calendar.DATE);
		// 添加大小月月份并将其转换为list,方便之后的判断
		// 年
		wv_year = (WheelView) view.findViewById(R.id.year);
		wv_year.setAdapter(new NumericWheelAdapter(START_YEAR, END_YEAR));// 设置"年"的显示数据
		wv_year.setCyclic(true);// 可循环滚动
		wv_year.setLabel("年");// 添加文字
		wv_year.setCurrentItem(year - START_YEAR);// 初始化时显示的数据

		// 月
		wv_month = (WheelView) view.findViewById(R.id.month);
		NumericWheelAdapter numericWheelAdapter = new NumericWheelAdapter(1, 17);
		numericWheelAdapter.setQuarterFlag(0);
		wv_month.setAdapter(numericWheelAdapter);
		wv_month.setCyclic(true);
		wv_month.setCurrentItem(quarter);
		// 添加"年"监听
		OnWheelChangedListener wheelListener_year = new OnWheelChangedListener() {
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				/*
				 * int year_num = newValue + START_YEAR; //
				 * 判断大小月及是否闰年,用来确定"日"的数据 if
				 * (list_big.contains(String.valueOf(wv_month.getCurrentItem() +
				 * 1))) { wv_day.setAdapter(new NumericWheelAdapter(1, 31)); }
				 * else if
				 * (list_little.contains(String.valueOf(wv_month.getCurrentItem
				 * () + 1))) { wv_day.setAdapter(new NumericWheelAdapter(1,
				 * 30)); } else { if ((year_num % 4 == 0 && year_num % 100 != 0)
				 * || year_num % 400 == 0) wv_day.setAdapter(new
				 * NumericWheelAdapter(1, 29)); else wv_day.setAdapter(new
				 * NumericWheelAdapter(1, 28)); }
				 */
			}
		};
		// 添加"月"监听
		OnWheelChangedListener wheelListener_month = new OnWheelChangedListener() {
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				/*
				 * int month_num = newValue + 1; if
				 * (list_little.contains(String.valueOf(month_num))) {
				 */
				// wv_day.setAdapter(new NumericWheelAdapter(1, 4));
				// }
				/*
				 * if(wv_month.getCurrentItem()==0) { wv_month.setLabel("全年");
				 * wv_month.setAdapter(new NumericWheelAdapter(0, 17)); }else
				 * if(
				 * wv_month.getCurrentItem()==1||wv_month.getCurrentItem()==2||
				 * wv_month.getCurrentItem()==3||wv_month.getCurrentItem()==4) {
				 * wv_month.setLabel("季度"); wv_month.setAdapter(new
				 * NumericWheelAdapter(0, 17)); }else { wv_month.setAdapter(new
				 * NumericWheelAdapter(1, 12)); wv_month.setLabel("月"); }
				 */
			}
		};
		// wv_year.addChangingListener(wheelListener_year);
		wv_month.addChangingListener(wheelListener_month);

		// 根据屏幕密度来指定选择器字体的大小(不同屏幕可能不同)
		int textSize = 0;
		if (hasSelectTime)
			textSize = (screenheight / 100) * 3;
		else
			textSize = (screenheight / 100) * 4;
		wv_month.TEXT_SIZE = textSize;
		wv_year.TEXT_SIZE = textSize;
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				wv_month.scroll(0, 20);
			}
		}, 50);

	}

	/**
	 * @Description: TODO 弹出日期时间选择器(只有年月的)
	 */
	public void initQuarterPickeraForMonth(int year, int month) {
		wv_year = (WheelView) view.findViewById(R.id.year);
		wv_year.setAdapter(new NumericWheelAdapter(START_YEAR, END_YEAR));// 设置"年"的显示数据
		wv_year.setCyclic(true);// 可循环滚动
		wv_year.setLabel("年");// 添加文字
		wv_year.setCurrentItem(year - START_YEAR);// 初始化时显示的数据
		// 月
		wv_month = (WheelView) view.findViewById(R.id.month);
		NumericWheelAdapter numericWheelAdapter = new NumericWheelAdapter(1, 12);
		numericWheelAdapter.setQuarterFlag(1);// 只含有月的标识
		wv_month.setAdapter(numericWheelAdapter);
		wv_month.setCyclic(true);
		wv_month.setCurrentItem(month);
		// 添加"年"监听
		OnWheelChangedListener wheelListener_year = new OnWheelChangedListener() {
			public void onChanged(WheelView wheel, int oldValue, int newValue) {


			}
		};
		// 添加"月"监听
		OnWheelChangedListener wheelListener_month = new OnWheelChangedListener() {
			public void onChanged(WheelView wheel, int oldValue, int newValue) {


			}
		};
		wv_year.addChangingListener(wheelListener_year);
		wv_month.addChangingListener(wheelListener_month);

		// 根据屏幕密度来指定选择器字体的大小(不同屏幕可能不同)
		int textSize = 0;
		if (hasSelectTime)
			textSize = (screenheight / 100) * 3;
		else
			textSize = (screenheight / 100) * 4;
		wv_month.TEXT_SIZE = textSize;
		wv_year.TEXT_SIZE = textSize;
		//		Handler handler = new Handler();
		//		handler.postDelayed(new Runnable() {
		//			@Override
		//			public void run() {
		//				wv_month.scroll(0, 20);
		//			}
		//		}, 50);

	}
	/**
	 * @Description: TODO 弹出日期时间选择器(只有时间)
	 */
	public void initQuarterPickeraHour(int hource, int minute) {
		wv_year = (WheelView) view.findViewById(R.id.year);
		wv_month = (WheelView) view.findViewById(R.id.month);
		wv_day = (WheelView) view.findViewById(R.id.day);
		wv_hours = (WheelView) view.findViewById(R.id.hour);
		wv_mins = (WheelView) view.findViewById(R.id.min);
		wv_year.setVisibility(View.GONE);
		wv_month.setVisibility(View.GONE);
		wv_day.setVisibility(View.GONE);

		wv_hours.setVisibility(View.VISIBLE);
		wv_mins.setVisibility(View.VISIBLE);

		wv_hours.setAdapter(new NumericWheelAdapter(0, 23));
		wv_hours.setCyclic(true);// 可循环滚动
		wv_hours.setLabel("时");// 添加文字
		wv_hours.setCurrentItem(hource);
		wv_mins.setAdapter(new NumericWheelAdapter(0, 59));
		wv_mins.setCyclic(true);// 可循环滚动
		wv_mins.setLabel("分");// 添加文字
		wv_mins.setCurrentItem(minute);
		// 根据屏幕密度来指定选择器字体的大小(不同屏幕可能不同)
		int textSize = 0;
		if (hasSelectTime)
			textSize = (screenheight / 100) * 2;
		wv_hours.TEXT_SIZE = textSize;
		wv_mins.TEXT_SIZE = textSize;

	}

	/**
	 * 得到选中的时间
	 * 
	 * @return
	 */
	public String getTime() {
		StringBuffer sb = new StringBuffer();
		if (!hasSelectTime)
			sb.append((wv_year.getCurrentItem() + START_YEAR)).append("-").append((wv_month.getCurrentItem() + 1)).append("-").append((wv_day.getCurrentItem() + 1));
		else
			sb.append((wv_year.getCurrentItem() + START_YEAR)).append("-").append((wv_month.getCurrentItem() + 1)).append("-").append((wv_day.getCurrentItem() + 1)).append(" ").append(wv_hours.getCurrentItem()).append(":").append(wv_mins.getCurrentItem());
		return sb.toString();
	}
	/**
	 * 得到选中的时间，时和分
	 * 
	 * @return
	 */
	public String getHourceTime() {
		StringBuffer sb = new StringBuffer();
		String hource ,min;
		if (wv_hours.getCurrentItem()<10) {
			hource = "0"+wv_hours.getCurrentItem();
		}else {
			hource = wv_hours.getCurrentItem()+"";
		}
		if (wv_mins.getCurrentItem()<10) {
			min = "0"+wv_mins.getCurrentItem();
		}else {
			min = wv_mins.getCurrentItem()+"";
		}
		sb.append(hource).append(":").append(min);
		return sb.toString();
	}

	/***
	 * 得到选中的年和月
	 * 
	 * @return
	 */
	public String getYearAndQuarter() {
		StringBuffer sb = new StringBuffer();
		if (!hasSelectTime)
			sb.append((wv_year.getCurrentItem() + START_YEAR)).append("-").append((wv_month.getCurrentItem()));
		else
			sb.append((wv_year.getCurrentItem() + START_YEAR)).append("-").append((wv_month.getCurrentItem()));
		System.out.println("--选中的季度--" + sb.toString());
		return sb.toString();
	}

	/***
	 * 得到选中的年和月和日
	 *
	 * @return
	 */
	public String getYearAndMonthAndDay() {
		StringBuffer sb = new StringBuffer();
		if (!hasSelectTime)
			sb.append((wv_year.getCurrentItem() + START_YEAR)).append("-").append((wv_month.getCurrentItem())).append("-").append(wv_day.getCurrentItem());
		else
			sb.append((wv_year.getCurrentItem() + START_YEAR)).append("-").append((wv_month.getCurrentItem())).append("-").append(wv_day.getCurrentItem());
		System.out.println("--选中的季度--" + sb.toString());
		return sb.toString();
	}

	/***
	 * 得到选中的年
	 * 
	 * @return
	 */
	public String getYear() {
		StringBuffer sb = new StringBuffer();
		if (!hasSelectTime)
			sb.append((wv_year.getCurrentItem() + START_YEAR));
		else
			sb.append((wv_year.getCurrentItem() + START_YEAR));
		System.out.println("--选中的年--" + sb.toString());
		return sb.toString();
	}
}
