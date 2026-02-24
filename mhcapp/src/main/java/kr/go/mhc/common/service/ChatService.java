package kr.go.mhc.common.service;

import java.util.List;
import java.util.Map;

/**
 * @Class Name : ChatService.java
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

public interface ChatService {
	
	/**
	 * 개설된 방목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */	
	public List<Map<String, String>> selectRoomList(Map<String, Object> param) throws Exception;

	/**
	 * 방번호 조회 (sequence)
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectRoomNumber(Map<String, Object> param) throws Exception;

	/**
	 * 신규 방(master) 등록
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public void insertChatMastr(Map<String, Object> param) throws Exception;
	
	/**
	 * 신규 방(room) 등록
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public void insertChatRoom(Map<String, Object> param) throws Exception;

	/**
	 * 메세지 저장하기
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public void insertChatMessage(Map<String, Object> param) throws Exception;

	/**
	 * 채팅 대화내용 불러오기 
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectChatTalkList(Map<String, Object> param)  throws Exception;

	/**
	 * 썸네일 불러오기 
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectThumNail(Map<String, Object> param)throws Exception;

	/**
	 * 상담 테이블 순번 조회
	 * @param param 검색 조건
	 * @throws Exception 
	 */
	public Map<String, String> selectCnslSn() throws Exception;
	
	/**
	 * 실시간상담 문의 등록
	 * @param param 검색 조건
	 * @throws Exception 
	 */
	public void insertChatCnsl(Map<String, Object> param) throws Exception;
	
	/**
	 * 실시간상담 글 확인 업데이트
	 * @param param 검색 조건
	 * @throws Exception 
	 */
	public void updateChatCnfm(Map<String, Object> param) throws Exception;
	
	/**
	 * 실시간상담 이력 목록 조회
	 * @param param 검색 조건
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectChatHistList(Map<String, Object> param) throws Exception;
	
}
