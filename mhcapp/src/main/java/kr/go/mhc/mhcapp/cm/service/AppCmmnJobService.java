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
	 * 공지사항 확인 체크
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String,String>> noticeCnfmChk(Map<String, Object> param) throws Exception;
	
	/**
	 * 공지사항 확인 업데이트
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public int updateNoticeCnfm(Map<String, Object> param) throws Exception;

	/**
	 * 공지사항 팝업 여부 업데이트
	 * 20231213 긴급공지사항 jeeeeey 추가
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public int insertNoticePopYn(Map<String, Object> param) throws Exception;

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
	 * 대상자용 설문조사 리스트조회
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception
	 */
	public List<Map<String, String>> selectMyInfoTrgtServey(Map<String, Object> param) throws Exception;

	/**
	 * 새로온 게시물 갯수 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public Map<String, String> selectNewCnt(Map<String, Object> param) throws Exception;
	
	/**
	 * 비밀번호 변경 
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public int myInfoPwdUpdate(Map<String, Object> param) throws Exception;
	
	
	/**
	 * 비밀번호 수정 전 정보 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */	
	public List<Map<String, String>> selectChkMyInfo(Map<String, String> param) throws Exception;

	/**
	 * 설정화면 라디오 버튼 변경 값 수정
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public void updateSetting(Map<String, Object> param) throws Exception;
	
	/**
	 * 알림 확인 유무 업데이트
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public void updateNotification(Map<String, Object> param) throws Exception;

	public void updateThumbnail(Map<String, Object> param) throws Exception;

	/**
	 * 알림내역 삭제 
	 */
	public int notificationDel(Map<String, Object> param) throws Exception;

	/**
	 * 메인대쉬 편집 저장
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public void updateMainEdit(Map<String, Object> param) throws Exception;
	
	/**
	 * 목표설정 저장
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public void updateObjSet(Map<String, Object> param) throws Exception;

	/**
	 * 닉네임 저장
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public void updateNickname(Map<String, Object> param) throws Exception;	
	
	/**
	 * 닉네임 사용여부 저장
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public void updateNicknameUseYn(Map<String, Object> param) throws Exception;		

}


