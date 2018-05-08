package org.scit.www.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.management.monitor.GaugeMonitorMBean;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.scit.www.service.BoardMapper;
import org.scit.www.service.GalleryMapper;
import org.scit.www.vo.Board;
import org.scit.www.vo.Gallery;
import org.scit.www.vo.Reply;
import org.springframework.stereotype.Repository;

@Repository
public class galleryDAO implements GalleryMapper{

	@Inject
	SqlSession session;

	@Override
	public int writeGallery(Gallery Gallery) {
		// TODO Auto-generated method stub
		return session.getMapper(GalleryMapper.class).writeGallery(Gallery);
	}

	@Override
	public int deleteGallery(int galleryNum) {
		// TODO Auto-generated method stub
		return session.getMapper(GalleryMapper.class).deleteGallery(galleryNum);
	}

	@Override
	public int updateGallery(Gallery gallery) {
		// TODO Auto-generated method stub
		return session.getMapper(GalleryMapper.class).updateGallery(gallery);
	}

	@Override
	public List<Gallery> galleryList(RowBounds rb) {
		// TODO Auto-generated method stub
		return session.getMapper(GalleryMapper.class).galleryList(rb);
	}

	@Override
	public Gallery readGallery(int galleryNum) {
		// TODO Auto-generated method stub
		return session.getMapper(GalleryMapper.class).readGallery(galleryNum);
	}

	@Override
	public int getGalleryCount() {
		// TODO Auto-generated method stub
		return session.getMapper(GalleryMapper.class).getGalleryCount();
	}

}
