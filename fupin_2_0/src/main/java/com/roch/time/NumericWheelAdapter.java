/*
 *  Copyright 2010 Yuri Kanivets
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.roch.time;

/**
 * Numeric Wheel adapter.
 */
public class NumericWheelAdapter implements WheelAdapter {
	
	/** The default min value */
	public static final int DEFAULT_MAX_VALUE = 9;

	/** The default max value */
	private static final int DEFAULT_MIN_VALUE = 0;
	
	// Values
	private int minValue;
	private int maxValue;
	
	// format
	private String format;
	
	private int mFlag=-1;
	
	private String[] items = new String[]{"全年","1季度","2季度","3季度","4季度","01月","02月","03月","04月","05月","06月","07月","08月","09月","10月","11月","12月"};
	
	private String[] itemMonths = new String[]{"01月","02月","03月","04月","05月","06月","07月","08月","09月","10月","11月","12月"};
	/**
	 * Default constructor
	 */
	public NumericWheelAdapter() {
		this(DEFAULT_MIN_VALUE, DEFAULT_MAX_VALUE);
	}
	/**
	 * 0是带季度的
	 * 1是只带月的
	 * @param flag
	 */
	public void setQuarterFlag(int flag)
	{
		mFlag=flag;
	}
	/**
	 * Constructor
	 * @param minValue the wheel min value
	 * @param maxValue the wheel max value
	 */
	public NumericWheelAdapter(int minValue, int maxValue) {
		this(minValue, maxValue, null);
	}
	/**
	 * Constructor
	 * @param minValue the wheel min value
	 * @param maxValue the wheel max value
	 * @param format the format string
	 */
	public NumericWheelAdapter(int minValue, int maxValue, String format) {
		this.minValue = minValue;
		this.maxValue = maxValue;
		this.format = format;
	}

	@Override
	public String getItem(int index) {
		if(mFlag==0)//带季度的
		{
			if (index >= 0 && index < getItemsCount()) {
				return format != null ? String.format(format, items[index]) :items[index];
			}
		}
		else if(mFlag==1)//只含月的
		{
			if (index >= 0 && index < getItemsCount()) {
				return format != null ? String.format(format, itemMonths[index]) :itemMonths[index];
			}
		}
		else
		{
			if (index >= 0 && index < getItemsCount()) {
				int value = minValue + index;
				return format != null ? String.format(format, value) : Integer.toString(value);
			}
		}
		return null;
	}

	@Override
	public int getItemsCount() {
		if(mFlag==0)
		{
			return items.length;
		}else if(mFlag == 1){
			return itemMonths.length;
		}
		return maxValue - minValue + 1;
	}
	
	@Override
	public int getMaximumLength() {
		int max = Math.max(Math.abs(maxValue), Math.abs(minValue));
		int maxLen = Integer.toString(max).length();
		if (minValue < 0) {
			maxLen++;
		}
		return maxLen;
	}
}
