package kr.or.khealth.smhc.smhcweb.cm.service.impl;

import java.util.List;
import java.util.Map;



import kr.or.khealth.smhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;

/**
 * @Class Name : MobileNoticeMngtDAO.java
 * @Description : 관리자 WEB에서 사용하는 모바일 공지 관리업무 DataBase 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.08.10		이은주			최초생성
 *
 * @author gst
 * @since 2016.08.10
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Repository("web.cm.MobileNoticeMngtDAO")
public class MobileNoticeMngtDAO extends DMultiEgovAbstractMapper{
	
	public int getMobileNoticeMngtListCount(Map<String, Object> param) throws Exception {
		int rsList = selectOne("mhc.web.cm.mobilenoticemngt.selectMobileNoticeCount", param);
		return rsList;
	}
	
	public List<Map<String, String>> getMobileNoticeMngtList(Map<String, Object> param) throws Exception {
		List<Map<String, String>> rsList = selectList("mhc.web.cm.mobilenoticemngt.selectMobileNoticeList", param);
		return rsList;
	}
	
	public List<Map<String, String>> getMobileNoticeReg(Map<String, Object> param) throws Exception {
		List<Map<String, String>> rsList = selectList("mhc.web.cm.mobilenoticemngt.selectMobileNoticeReg", param);
		return rsList;
	}
	
	public void getMobileNoticeRegInsert(Map<String, Object> param) throws Exception {
		insert("mhc.web.cm.mobilenoticemngt.insertMobileNotice", param);
	}
	
	public Map<String, String> getMobileNoticeDtls(Map<String, Object> param) throws Exception {
		Map<String, String> rsMap = selectOne("mhc.web.cm.mobilenoticemngt.selectMobileNoticeDtls", param);
		return rsMap;
	}
	
	public List<Map<String, String>> getBefAftMobileNoticeList(Map<String, Object> param) throws Exception {		
		List<Map<String,String>> rsList = selectList("mhc.web.cm.mobilenoticemngt.selectBefAftMobileNoticeList", param);		
		return rsList;  
	}
	
	public void getMobileNoticeMngtUp(Map<String, Object> param) throws Exception {
		update("mhc.web.cm.mobilenoticemngt.updateMobileNoticeDtls", param);	
	}
	
	public void getMobileNoticeMngtDel(Map<String, Object> param) throws Exception {
		update("mhc.web.cm.mobilenoticemngt.deleteMobileNotice", param);	
	}
	
	public void getMobileNoticeMngtPostUp(Map<String, Object> param) throws Exception {
		update("mhc.web.cm.mobilenoticemngt.updateMobileNoticePost", param);
	}

	public Map<String, String> mobileNoticeMngtInsertNumber(Map param) {
		Map<String, String> count = selectOne("mhc.web.cm.mobilenoticemngt.selectMobileNoticeInsertNumber", param);
		return count;
	}

}
