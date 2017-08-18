/**
 * 
 */
package com.roch.fupin.entity;

/**
 * @author ZhaoDongShao
 *
 * 2016年8月1日 
 *
 */
public class UploadPhoto extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String path;
	private boolean isDownload;
	private int progress;
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public boolean isDownload() {
		return isDownload;
	}
	public void setDownload(boolean isDownload) {
		this.isDownload = isDownload;
	}
	public int getProgress() {
		return progress;
	}
	public void setProgress(int progress) {
		this.progress = progress;
	}

}
