package kr.go.mhc.mhcweb.cm.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.go.mhc.mhcweb.cm.service.NoticeMngtService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * @Class Name :NoticeMngtServiceImpl.java
 * @Description : 관리자 WEB에서 사용하는 공지 관리업무에 필요한 DAO와 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일				수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.07.07		이태석			최초생성
 *
 * @author gst
 * @since 2016.07.07
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Service("web.cm.NoticeMngtService")
public class NoticeMngtServiceImpl extends EgovAbstractServiceImpl implements NoticeMngtService{
	
	@Resource(name="web.cm.NoticeMngtDAO")
	private NoticeMngtDAO noticeMngtDAO;

	@Override
	public int getNoticeMngtListCount(Map<String, Object> param) throws Exception {
		
		return noticeMngtDAO.getNoticeMngtListCount(param);
	}

	@Override
	public List<Map<String, String>> getNoticeMngtList(Map<String, Object> param) throws Exception {
		
		return noticeMngtDAO.getNoticeMngtList(param);
	}

	@Override
	public void getNoticeMngtRegInsert(Map<String, Object> param) throws Exception {
		
		noticeMngtDAO.getNoticeMngtRegInsert(param);
	}

	@Override
	public Map<String, String> getNoticeDtls(Map<String, Object> param) throws Exception {
		
		return noticeMngtDAO.getNoticeDtls(param);
	}

	@Override
	public List<Map<String, String>> getBefAftNoticeList(Map<String, Object> param) throws Exception {
		
		return noticeMngtDAO.getBefAftNoticeList(param);
	}
	
	@Override
	public void getNoticeMngtUp(Map<String, Object> param) throws Exception {
		
		noticeMngtDAO.getNoticeMngtUp(param);
	}

	@Override
	public void getNoticeMngtDel(Map<String, Object> param) throws Exception {
		noticeMngtDAO.getNoticeMngtDel(param);
		
	}
	
	
	
	@Override
	public List<Map<String, Object>>  selectNoticeCommentList(Map<String, Object> param) throws Exception{
		return noticeMngtDAO.selectNoticeCommentList(param);	
		
	}
	
	@Override
	public void insertNoticeComment(Map<String, Object> param) throws Exception{
		noticeMngtDAO.insertNoticeComment(param);
	}
	
	@Override
	public void updateNoticeComment(Map<String, Object> param) throws Exception{
		noticeMngtDAO.updateNoticeComment(param);
	}	
	
	@Override
	public void deleteNoticeComment(Map<String, Object> param) throws Exception{
		noticeMngtDAO.deleteNoticeComment(param);
	}
	
	@Override
	public void insertNoticeChildComment(Map<String, Object> param) throws Exception {
		noticeMngtDAO.insertNoticeChildComment(param);
	}
	
	@Override
	public void deleteNoticeChildComment(Map<String, Object> param) throws Exception {
		noticeMngtDAO.deleteNoticeChildComment(param);
	}
	
	@Override
	public List<Map<String, String>> getAttachFileList(Map<String, String> param) throws Exception {
		return noticeMngtDAO.getAttachFileList(param);
	}
	
	@Override
	public Map<String, String> getNoticeInquire(Map<String, Object> param) throws Exception {
		return noticeMngtDAO.getNoticeInquire(param);
	}
	
	@Override
	public void insertNoticeInquire(Map<String, Object> param) throws Exception {
		noticeMngtDAO.insertNoticeInquire(param);
	}
}
