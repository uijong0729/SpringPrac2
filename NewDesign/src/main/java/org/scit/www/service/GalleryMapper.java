package org.scit.www.service;


import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.scit.www.vo.Gallery;

public interface GalleryMapper {
	
	//작성
	public int writeGallery(Gallery Gallery);
	
	//삭제
	public int deleteGallery(int galleryNum);
	
	//수정
	public int updateGallery(Gallery gallery);
	
	//출력
	public List<Gallery> galleryList(RowBounds rb);
	
	//읽기
	public Gallery readGallery(int galleryNum);
	
	//전체 게시글 수
	public int getGalleryCount();
	
}
