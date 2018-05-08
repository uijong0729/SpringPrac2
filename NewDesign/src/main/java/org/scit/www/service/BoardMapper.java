package org.scit.www.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.scit.www.vo.Board;
import org.scit.www.vo.Reply;

public interface BoardMapper {
	
	//작성
	public int writeBoard(Board board);
	
	//삭제
	public int deleteBoard(int boardNum);
	
	//수정
	public int updateBoard(Board board);
	
	//출력
	public List<Board> boardList(RowBounds rb);
	
	//읽기
	public Board readBoard(int boardNum);
	public int hitsBoard(int boardNum);
	
	//전체 게시글 수
	public int getBoardCount();
	
	public List<Board> searchBoard(Map<String, String> map);
	//-----------------------덧글---------------------------
	
	//입력
	public int insertReply(Reply reply);
	
	//전체출력
	public ArrayList<Reply> replyAll(int boardNum, RowBounds rb);
	//1개
	public Reply getReply(int replyNum);
	
	//삭제
	public int deleteReply(int replyNum);
	
	//수정
	public int updateReply(Reply reply);
	
	public int getReplyCount(int BoardNum);
	
}
