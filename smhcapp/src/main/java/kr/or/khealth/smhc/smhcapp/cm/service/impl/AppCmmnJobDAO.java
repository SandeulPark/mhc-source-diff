package kr.or.khealth.smhc.smhcapp.cm.service.impl;

import java.util.List;
import java.util.Map;

import kr.or.khealth.smhc.common.DMultiEgovAbstractMapper;

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
	 * 공지사항 확인 체크
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String,String>> noticeCnfmChk(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.cm.appcmmnjob.noticeCnfmChk", param);	
		return rsList;  
	}
	
	/**
	 * 공지사항 확인 업데이트
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public int updateNoticeCnfm(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		int rsList = insert("mhcapp.cm.appcmmnjob.updateNoticeCnfm", param);	
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
	 * 새로온 게시물 갯수 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public Map<String, String> selectNewCnt(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		Map<String,String> rsList = selectOne("mhcapp.cm.appcmmnjob.selectNewCnt", param);	
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
	 * 설정화면 라디오 버튼 변경 값 수정
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public void updateSetting(Map<String, Object> param) {
		// TODO Auto-generated method stub
		update("mhcapp.cm.appcmmnjob.updateSetting", param);	
	}
	
	/**
	 * 알림 확인 유무 업데이트
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public void updateNotification(Map<String, Object> param) {
		// TODO Auto-generated method stub
		update("mhcapp.cm.appcmmnjob.updateNotification", param);	
	}

	public void updateThumbnail(Map<String, Object> param) {
		// TODO Auto-generated method stub
		update("mhcapp.cm.appcmmnjob.updateThumbnail", param);	
	}

	/**
	 * 알림내역 삭제 
	 */
	public int notificationDel(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		int rsList = update("mhcapp.cm.appcmmnjob.notificationDel", param);	
		return rsList;  
	}
	
	/**
	 * 메인대쉬 편집 저장
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public void updateMainEdit(Map<String, Object> param) {
		// TODO Auto-generated method stub
		update("mhcapp.cm.appcmmnjob.updateMainEdit", param);	
	}
	
	/**
	 * 목표설정 저장
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public void updateObjSet(Map<String, Object> param) {
		// TODO Auto-generated method stub
		update("mhcapp.cm.appcmmnjob.updateObjSet", param);	
	}
	
	
	/**
	 * 닉네임 저장
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public void updateNickname(Map<String, Object> param) {
		// TODO Auto-generated method stub
		update("mhcapp.cm.appcmmnjob.updateNickname", param);	
	}	
	
	/**
	 * 닉네임 사용 여부 저장
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public void updateNicknameUseYn(Map<String, Object> param) {
		// TODO Auto-generated method stub
		update("mhcapp.cm.appcmmnjob.updateNicknameUseYn", param);	
	}		
}

