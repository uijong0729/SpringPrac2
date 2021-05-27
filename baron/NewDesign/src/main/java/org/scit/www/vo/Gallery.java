package org.scit.www.vo;

public class Gallery {
	private int galleryNum;
	private String title;
	private String originalfile;
	private String savedfile;
	
	public Gallery() {
		// TODO Auto-generated constructor stub
	}
	
	public Gallery(String title, String content, String origianlfile, String savedfile) {
		
	}
	
	public int getGalleryNum() {
		return galleryNum;
	}

	public void setGalleryNum(int galleryNum) {
		this.galleryNum = galleryNum;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public String getOriginalfile() {
		return originalfile;
	}

	public void setOriginalfile(String originalfile) {
		this.originalfile = originalfile;
	}

	public String getSavedfile() {
		return savedfile;
	}

	public void setSavedfile(String savedfile) {
		this.savedfile = savedfile;
	}

	@Override
	public String toString() {
		return "Gallery [galleryNum=" + galleryNum  + ", title=" + title +
				 ", originalfile=" + originalfile + ", savedfile="
				+ savedfile + "]";
	}
	
	
}