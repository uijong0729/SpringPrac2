package org.scit.www.vo;

public class Board {
	private int boardNum;
	private String id;
	private String title;
	private String content;
	private String writeDate;
	private int hits;	//조회수
	private String originalfile;
	private String savedfile;
	
	public Board() {
		// TODO Auto-generated constructor stub
	}
	
	public int getBoardNum() {
		return boardNum;
	}
	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
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
		return "Board [boardNum=" + boardNum + ", title=" + title + ", id=" + id + ", content=" + content
				+ ", writeDate=" + writeDate + ", hits=" + hits + ", originalfile=" + originalfile + ", savedfile="
				+ savedfile + "]";
	}
	
	
	
	
}
