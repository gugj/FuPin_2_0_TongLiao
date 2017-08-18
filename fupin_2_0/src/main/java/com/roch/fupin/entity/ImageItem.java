/**
 * 
 */
package com.roch.fupin.entity;

/**
 * @author ZhaoDongShao
 *
 * 2016年6月24日 
 *
 */
public class ImageItem {

	/**
	 * 图片路径
	 */
	private String path;
	/**
	 * 判断该图片当前的选中状态
	 */
	private boolean isSelected;
	
	/**
	 * 
	 */
	public ImageItem() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 */
	public ImageItem(String path, boolean isSelected) {
		// TODO Auto-generated constructor stub
		this.isSelected = isSelected;
		this.path = path;
	}
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public boolean isSelected() {
		return isSelected;
	}
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
	
	
	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		if (this == o) {
			return true;
		}
		if (o.getClass() == ImageItem.class) {
			ImageItem imageItem = (ImageItem)o;
			return imageItem.getPath().equals(path);
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return path.hashCode();
	}
}
