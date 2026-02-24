package kr.go.mhc.mhcapp.cm.service.impl;

import java.util.List;
import java.util.Map;

import kr.go.mhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;

/**
 * @Class Name : AppCmmnJobDAO.java
 * @Description : 모바일 헬스케어 App에서 사용하는 공통업무 DataBase 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일				수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.06.27		윤봉훈			최초생성
 * @	2016.06.28		오명빈			알림내역 추가	
 * @author gst
 * @since 2016.06.27
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Repository("mhcapp.cm.AppCmmnJobDAO")
public class AppCmmnJobDAO extends DMultiEgovAbstractMapper{

	/**
	 * 공지사항 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectNoticeList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.cm.appcmmnjob.selectNoticeList", param);	
		return rsList;  
	}
	
	/**
	 * 알림내역 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectNotificationList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.cm.appcmmnjob.selectNotificationList", param);	
		return rsList;  
	}
	
	/**
	 * 개인정보 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectMyInfoList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.cm.appcmmnjob.selectMyInfoList", param);	
		return rsList;  
	}
	
	/**
	 * 건강설문 실행 여부 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectMyInfoServey(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.cm.appcmmnjob.selectMyInfoServey", param);	
		return rsList;  
	}
	
	/**
	 * 비밀번호 변경
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	public int myInfoPwdUpdate(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		int rsList = update("mhcapp.cm.appcmmnjob.myInfoPwdUpdate", param);	
		return rsList;  
	}
	
	/**
	 * 월간리포트 유무 확인
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public int reportNullChk(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		int rsList = selectOne("mhcapp.cm.appcmmnjob.reportNullChk", param);	
		return rsList;  
	}
	

	/**
	 * 비밀번호 수정 전 정보 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectChkMyInfo(Map<String, String> param) {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.cm.appcmmnjob.selectChkMyInfo", param);	
		return rsList;
	}
	
	/**
	 * 푸쉬상태 on off 
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public void updatePushSttus(Map<String, Object> param) {
		update("mhcapp.cm.appcmmnjob.updatePushSttus", param);	
	}


	/**
	 * 신체상태 on off
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public void updateBodySttus(Map<String, Object> param) {
		// TODO Auto-generated method stub
		update("mhcapp.cm.appcmmnjob.updateBodySttus", param);	
		
	}

	public void updateThumbnail(Map<String, Object> param) {
		// TODO Auto-generated method stub
		update("mhcapp.cm.appcmmnjob.updateThumbnail", param);	
	}

	
	
}
