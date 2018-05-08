package org.scit.www.vo;

public class Reply {
	int replyNum;
	int boardNum;
	String id;
	String content;
	String writeDate;
	
	public Reply() {
		// TODO Auto-generated constructor stub
	}
	public Reply(int boardNum, String id, String content)
	{
		this.boardNum = boardNum;
		this.id = id;
		this.content = content;
	}
	
	public int getReplyNum() {
		return replyNum;
	}
	public void setReplyNum(int replyNum) {
		this.replyNum = replyNum;
	}
	public int getBoardNum() {
		return boardNum;
	}
	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
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

	@Override
	public String toString() {
		return "Reply [replyNum=" + replyNum + ", boardNum=" + boardNum + ", id=" + id + ", content=" + content
				+ ", writeDate=" + writeDate + "]";
	}

	
	
	
}


