package kr.go.mhc.mhcapp.cm.service;

import java.util.List;
import java.util.Map;

/**
 * @Class Name : AppCmmnJobService.java
 * @Description : 모바일 헬스케어 App에서 사용하는 공통업무 서비스 Class
 * @Modification Information
 * @
 * @	수정일				수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.06.27		윤봉훈			최초생성
 * @	2016.06.28		오명빈			알림내역 추가
 *
 * @author gst
 * @since 2016.06.27
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

public interface AppCmmnJobService {
	
	/**
	 * 공지사항 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectNoticeList(Map<String, Object> param) throws Exception;
	
	/**
	 * 알림내역 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectNotificationList(Map<String, Object> param) throws Exception;
	
	/**
	 * 개인정보 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectMyInfoList(Map<String, Object> param) throws Exception;
	
	
	/**
	 * 건강 설문 실행 여부 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectMyInfoServey(Map<String, Object> param) throws Exception;
	
	/**
	 * 비밀번호 변경 
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public int myInfoPwdUpdate(Map<String, Object> param) throws Exception;
	
	/**
	 * 월간리포트 유무확인
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public int reportNullChk(Map<String, Object> param) throws Exception;
	
	/**
	 * 비밀번호 수정 전 정보 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */	
	public List<Map<String, String>> selectChkMyInfo(Map<String, String> param) throws Exception;


	/**
	 * 푸쉬상태 on off
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public void updatePushSttus(Map<String, Object> param) throws Exception;


	/**
	 * 신체상태 on off
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public void updateBodySttus(Map<String, Object> param) throws Exception;

	public void updateThumbnail(Map<String, Object> param) throws Exception;


}
