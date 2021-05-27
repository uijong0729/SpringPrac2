package org.scit.www;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.RowBounds;
import org.scit.www.dao.boardDAO;
import org.scit.www.util.FileService;
import org.scit.www.vo.Board;
import org.scit.www.vo.Reply;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/member")
public class BoardController {
	
	@Inject
	boardDAO bdao;
	
	//스프링이 설치된 최상위경로
	private final String UPLOAD_PATH = "/boardfile";
	
	@RequestMapping(value="/bd", method=RequestMethod.GET)
	public String bd(String boardNum, Model model)
	{
		bdao.hitsBoard(Integer.parseInt(boardNum));
		Board board = bdao.readBoard(Integer.parseInt(boardNum));
		System.out.println("현재 읽는 board의 정보 : " + board);
		model.addAttribute("board", board);
		
		return "board/ReadBoard";
	}

	@RequestMapping(value="/goBoard", method=RequestMethod.GET)
	public String goBoard(String page, Model model) {
		
		//페이지당 게시글 수
		int currentPage = 1;
		int pagePerList = 10;
		int offset = 0;
		int countBoardList = bdao.getBoardCount();
		int lastPage = (int)Math.ceil(((double)countBoardList / 10.0));
		
		if(page != null)
		{
			currentPage = Integer.parseInt(page);

		}
		offset = (pagePerList * currentPage) - pagePerList;
		RowBounds rb = new RowBounds(offset, pagePerList);
		List<Board> boardAll = bdao.boardList(rb);
		
		model.addAttribute("boardAll", boardAll);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("countBoardList", countBoardList);
		model.addAttribute("lastPage", lastPage);
		
		return "board/BoardMain";
	}
	
	@RequestMapping(value="/writeBoard", method=RequestMethod.GET)
	public String writeBoard() {
		return "board/WriteBoard";
	}
	
	@RequestMapping(value="/writeform", method=RequestMethod.POST)
	public String writeForm(Board board, MultipartFile upload) {
		
		System.out.println(upload);
		if(!(upload.isEmpty()))
		{
			String savedfile = FileService.saveFile(upload, UPLOAD_PATH);
			String originalfile = upload.getOriginalFilename();
			
			board.setOriginalfile(originalfile);
			board.setSavedfile(savedfile);
		}
		
		System.out.println(bdao.writeBoard(board));
		
		
		return "redirect:goBoard";
	}
	
	@RequestMapping(value="download", method=RequestMethod.GET)
	public String download(String boardNum, HttpServletResponse res)
	{
		//글 정보 가져오기
		Board board = bdao.readBoard(Integer.parseInt(boardNum));
		
		if(board == null)
		{
			return "redirect:/";
		}
		
		//파일명
		String savedfile = board.getSavedfile();
		String originalfile = board.getOriginalfile();
		
		//응답의 헤더를 수정
		try
		{
			res.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(originalfile, "UTF-8"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		//파일전송
		String fullpath = UPLOAD_PATH + "/" + savedfile;
		FileInputStream fis = null;
		ServletOutputStream sos = null;
		
		try {
			fis = new FileInputStream(fullpath);
			sos = res.getOutputStream();
			FileCopyUtils.copy(fis, sos);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(fis != null) 
			{
				try {
				fis.close();
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
			}
			if(sos != null) 
			{
				try {
				sos.close();
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
			}

		}
		
		return null;
		
	}
	
	@ResponseBody
	@RequestMapping(value="/insertReply", method=RequestMethod.POST)
	public void insertReply(Reply reply) {
		System.out.println(reply);
		
		System.out.println(bdao.insertReply(reply));
		
		
	}
	
	@ResponseBody
	@RequestMapping(value="/deleteReply", method=RequestMethod.POST)
	public void deleteReply(String replyNum) {
		int getReplyNum = Integer.parseInt(replyNum);
		System.out.println(replyNum);
		System.out.println(bdao.deleteReply(getReplyNum));
		
	}
	
	
	@ResponseBody
	@RequestMapping(value="/replyList", method=RequestMethod.GET)
	public Map<String, Object> replyList(String boardNum, String page){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		int currentPage = 1;
		int pagePerReply = 4;
		int offset = 0;
		
		if(page != null)
		{
			currentPage = Integer.parseInt(page);
		}
		offset = pagePerReply * currentPage - pagePerReply;
		RowBounds rb = new RowBounds(offset, pagePerReply);
		ArrayList<Reply> replyAll = bdao.replyAll(Integer.parseInt(boardNum), rb);
		
		//System.out.println(replyAll);
		
		map.put("replyAll", replyAll);
		map.put("replyCount", bdao.getReplyCount(Integer.parseInt(boardNum)));
		map.put("currentPage", currentPage);
		
		//System.out.println(map);
		
		return map;
	}
	
	
	@RequestMapping(value="/submitBoardForm", method=RequestMethod.POST)
	public String submitBoardForm(Board board, Model model, MultipartFile upload) {
		
		Board bod = bdao.readBoard(board.getBoardNum());
		System.out.println(bod);
		model.addAttribute("bod", bod);
		
		return "board/UpdateBoard";				
	}
	@RequestMapping(value="/rewriteBoardForm", method=RequestMethod.POST)
	public String rewriteBoardForm(Board board, MultipartFile upload, Model model) {

		if(!(upload.isEmpty()))
		{
			String savedfile = FileService.saveFile(upload, UPLOAD_PATH);
			String originalfile = upload.getOriginalFilename();
			
			board.setSavedfile(savedfile);
			board.setOriginalfile(originalfile);
			FileService.deleteFile(UPLOAD_PATH + "/" + savedfile);
		}

		System.out.println(board);
		System.out.println(bdao.updateBoard(board));
		model.addAttribute("board",board);
		
		return "redirect:goBoard";				
	}
	
	@RequestMapping(value="/deleteForm", method=RequestMethod.GET)
	public String submitBoardForm(int boardNum, HttpSession session) {
		
		Board board = bdao.readBoard(boardNum);
		if(board.getId().equals(session.getAttribute("loginId")))
		{
			
			FileService.deleteFile(UPLOAD_PATH + "/" + board.getSavedfile());
			System.out.println(bdao.deleteBoard(boardNum));
			
		}
		return "redirect:goBoard";				
	}
	
	@RequestMapping(value="/searchBoard", method=RequestMethod.GET)
	public String searchBoard(String keyword, String option, Model model) {
		
		Map<String, String> map = new HashMap<String, String>();
		System.out.println("=====검색영역=====");
		System.out.println("옵션" + option);
		System.out.println("키워드" + keyword);
		
		map.put(option, keyword);
		List<Board> searchBoard = bdao.searchBoard(map);
		
		model.addAttribute("boardAll", searchBoard);
		model.addAttribute("keyword", keyword);
		model.addAttribute("option", option);
		
		return "board/BoardMain";
	}
	
	
}
