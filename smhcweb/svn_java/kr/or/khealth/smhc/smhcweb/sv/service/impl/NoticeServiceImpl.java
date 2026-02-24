package kr.or.khealth.smhc.smhcweb.sv.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import kr.or.khealth.smhc.smhcweb.sv.service.NoticeService;


/**
 * @Class Name :NoticeServiceImpl.java
 * @Description : 관리자 WEB에서 사용하는 긴급 공지 사항 화면에 필요한 DAO와 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2021.08.04		윤찬호			최초생성
 
 * @author chyoon
 * @since 2021.08.04
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Service(value="web.sv.NoticeService")
public class NoticeServiceImpl extends EgovAbstractServiceImpl implements NoticeService{
	
	@Resource(name="web.sv.NoticeDAO")
	private NoticeDAO noticeDAO;
	
	@Override
	public int selectNoticeListCount(Map<String, Object> param) throws Exception {
		return noticeDAO.selectNoticeListCount(param);
	}

	@Override
	public List<Map<String, String>> selectNoticeList(Map<String, Object> param) throws Exception {
		return noticeDAO.selectNoticeList(param);
	}

	@Override
	public Map<String, Object> noticeDupChk(Map<String, Object> param) throws Exception {		
		return noticeDAO.noticeDupChk(param);
	}

	@Override
	public int noticeReg(Map<String, Object> param) throws Exception {		
		return noticeDAO.noticeReg(param);
	}

	@Override
	public Map<String, Object> selectNoticeDetailData(Map<String, Object> param) throws Exception {
		return noticeDAO.selectNoticeDetailData(param);
	}

	@Override
	public Map<String, Object> selectNoticeData(Map<String, Object> param) throws Exception {
		return noticeDAO.selectNoticeData(param);
	}

	@Override
	public int noticeUpdate(Map<String, Object> param) throws Exception {
		return noticeDAO.noticeUpdate(param);
	}

	@Override
	public int deleteNotice(Map<String, Object> param) throws Exception {		
		return noticeDAO.deleteNotice(param);
	}

	
}
