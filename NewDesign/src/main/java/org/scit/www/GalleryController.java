package org.scit.www;


import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.RowBounds;
import org.scit.www.dao.galleryDAO;
import org.scit.www.util.FileService;
import org.scit.www.vo.Gallery;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


/**
 * Handles requests for the application home page.
 */
@Controller
public class GalleryController {
	
	@Inject
	galleryDAO gdao;
	

	
	@RequestMapping(value = "/goGallery", method = RequestMethod.GET)
	public String goGallery(String page, Model model) {
		
		int galleryPage = 1;
		int pagePerList = 4;
		int offset = 0;
		int countGallery = gdao.getGalleryCount();
		int lastPage = (int)Math.ceil(((double)countGallery/ 4.0));
		
		if(page != null)
		{
			galleryPage = Integer.parseInt(page);
		}
				
		offset = (pagePerList * galleryPage) - pagePerList;
				// 4 * 1 - 4 = 0 : 0부터 4개
				// 4 * 2 - 4 = 4 : 4부터 4개
		
		RowBounds rb = new RowBounds(offset, pagePerList);
		List<Gallery> galleryAll = gdao.galleryList(rb);
		
		model.addAttribute("galleryAll", galleryAll);
		model.addAttribute("galleryPage", galleryPage);
		model.addAttribute("countGallery", countGallery);
		model.addAttribute("lastPage", lastPage);
		
		return "gallery/GalleryMain";
	}
	
	
	@RequestMapping(value = "/uploadform", method = RequestMethod.GET)
	public String writeGallery() {
		
		
		return "gallery/uploadform";
	}

	@ResponseBody
	@RequestMapping(value="/uploadSubmit", method=RequestMethod.POST)
	public Gallery uploadSubmit(Gallery gallery, MultipartFile upload) 
	{
		System.out.println(gallery);
		System.out.println(upload);
		
	
		
		String savedfile = FileService.saveFile(upload, "/boardfile");
		String originalfile = upload.getOriginalFilename();
	
		gallery.setOriginalfile(originalfile);
		gallery.setSavedfile(savedfile);
		
		
		System.out.println(gdao.writeGallery(gallery));
		
		
		
		
		return gallery;
	}
	
	
	@RequestMapping(value = "download", method=RequestMethod.GET)
	public String download(String galleryNum, HttpServletResponse res) {
		
		//1. 글 정보 가져오기 (boardnum을 통해)
		Gallery g = gdao.readGallery(Integer.parseInt(galleryNum));
		
		//이상한 경로로 접근하려 할 때 홈화면으로 이동
		if(g == null) {
			return "redirect:/";
		}
		
		//2. 저장된 파일명 & 실제 파일명 취득
		String savedfile = g.getSavedfile();
		String originalfile = g.getOriginalfile();
		
		//3. response의 header를 수정
		try
		{
			//key : value				기질			부착				//파일명을 인코딩
			res.setHeader("Content-Disposition", "attachment;filename=" 
							+ URLEncoder.encode(originalfile,"UTF-8"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		//4. String을 가지고와서 파일을 전송합니다.
		String fullpath = "/boardfile" + "/" + savedfile;
		
		FileInputStream fis = null;
		ServletOutputStream sos = null;
		
		try
		{
			fis = new FileInputStream(fullpath);
			sos = res.getOutputStream();
			
			FileCopyUtils.copy(fis, sos);
		}
		catch (Exception e) 
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
	@RequestMapping(value="/deleteGallery", method=RequestMethod.POST)
	public void deleteGallery(String galleryNum) 
	{	
		int getGalleryNum = Integer.parseInt(galleryNum);
		FileService.deleteFile("/boardFile" + "/" + gdao.readGallery(getGalleryNum).getSavedfile());
		System.out.println(gdao.deleteGallery(getGalleryNum));
	}
	
	

}