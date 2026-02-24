package kr.or.khealth.smhc.smhcweb.sv.service.impl;

import java.util.List;
import java.util.Map;

import kr.or.khealth.smhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;


/**
 * @Class Name : NoticeDAO.java
 * @Description : 관리자 WEB에서 사용하는 긴급 공지 사항 DataBase 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2021.08.04		윤찬호			최초생성
 *
 * @author chyoon
 * @since 2021.08.04
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */


@Repository("web.sv.NoticeDAO")
public class NoticeDAO extends DMultiEgovAbstractMapper{
	
	public int selectNoticeListCount(Map<String, Object> param) {
		int rsInt = selectOne("smhc.web.sv.notice.selectNoticeListCount",param);
		return rsInt;
	}
	
	public List<Map<String, String>> selectNoticeList(Map<String, Object> param) {		
		List<Map<String,String>> rsList = selectList("smhc.web.sv.notice.selectNoticeList", param);		
		return rsList;
	}

	public Map<String, Object> noticeDupChk(Map<String, Object> param) {
		Map<String,Object> rsMap = selectOne("smhc.web.sv.notice.noticeDupChk", param);		
		return rsMap;
	}

	public int noticeReg(Map<String, Object> param) {		
		int rsInt = insert("smhc.web.sv.notice.noticeReg", param);
		return rsInt;
	}

	public Map<String, Object> selectNoticeDetailData(Map<String, Object> param) {
		return selectOne("smhc.web.sv.notice.selectNoticeDetailData", param);
	}

	public Map<String, Object> selectNoticeData(Map<String, Object> param) {		
		return selectOne("smhc.web.sv.notice.selectNoticeData", param);
	}

	public int noticeUpdate(Map<String, Object> param) {
		int rsInt = insert("smhc.web.sv.notice.noticeUpdate", param);
		return rsInt;
	}

	public int deleteNotice(Map<String, Object> param) {
		int rsInt = delete("smhc.web.sv.notice.deleteNotice", param);
		return rsInt;
	}

	
}