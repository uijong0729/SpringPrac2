package org.scit.www.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.scit.www.service.BoardMapper;
import org.scit.www.vo.Board;
import org.scit.www.vo.Reply;
import org.springframework.stereotype.Repository;

@Repository
public class boardDAO implements BoardMapper{

	@Inject
	SqlSession session;

	@Override
	public int writeBoard(Board board) {
		// TODO Auto-generated method stub
		return session.getMapper(BoardMapper.class).writeBoard(board);
	}

	@Override
	public int deleteBoard(int boardNum) {
		// TODO Auto-generated method stub
		return session.getMapper(BoardMapper.class).deleteBoard(boardNum);
	}

	@Override
	public int updateBoard(Board board) {
		// TODO Auto-generated method stub
		return session.getMapper(BoardMapper.class).updateBoard(board);
	}

	@Override
	public List<Board> boardList(RowBounds rb) {
		// TODO Auto-generated method stub
		return session.getMapper(BoardMapper.class).boardList(rb);
	}

	@Override
	public Board readBoard(int boardNum) {
		// TODO Auto-generated method stub
		return session.getMapper(BoardMapper.class).readBoard(boardNum);
	}
	@Override
	public int hitsBoard(int boardNum)
	{
		return session.getMapper(BoardMapper.class).hitsBoard(boardNum);
	}

	@Override
	public int getBoardCount() {
		// TODO Auto-generated method stub
		return session.getMapper(BoardMapper.class).getBoardCount();
	}

	@Override
	public int insertReply(Reply reply) {
		// TODO Auto-generated method stub
		return session.getMapper(BoardMapper.class).insertReply(reply);
	}

	@Override
	public ArrayList<Reply> replyAll(int boardNum, RowBounds rb) {
		// TODO Auto-generated method stub
		return session.getMapper(BoardMapper.class).replyAll(boardNum, rb);
	}

	@Override
	public int deleteReply(int replyNum) {
		// TODO Auto-generated method stub
		return session.getMapper(BoardMapper.class).deleteReply(replyNum);
	}

	@Override
	public int updateReply(Reply reply) {
		// TODO Auto-generated method stub
		return session.getMapper(BoardMapper.class).updateReply(reply);
	}

	@Override
	public Reply getReply(int replyNum) {
		// TODO Auto-generated method stub
		return session.getMapper(BoardMapper.class).getReply(replyNum);
	}

	@Override
	public int getReplyCount(int BoardNum) {
		// TODO Auto-generated method stub
		return session.getMapper(BoardMapper.class).getReplyCount(BoardNum);
	}

	@Override
	public List<Board> searchBoard(Map<String, String> map) {
		// TODO Auto-generated method stub
		return session.getMapper(BoardMapper.class).searchBoard(map);
	}
	
	
}
