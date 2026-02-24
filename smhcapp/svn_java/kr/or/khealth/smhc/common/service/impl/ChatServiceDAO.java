package kr.or.khealth.smhc.common.service.impl;

import java.util.List;
import java.util.Map;

import kr.or.khealth.smhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;

/**
 * @Class Name : ChatServiceDAO.java
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

@Repository("common.ChatServiceDAO")
public class ChatServiceDAO extends DMultiEgovAbstractMapper{

	/**
	 * 방목록 조회 
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectRoomList(Map<String, Object> param) {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("common.chat.selectRoomList", param);	
		return rsList;  
	}
	
	/**
	 * 방번호 조회 (sequence)
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectRoomNumber(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("common.chat.selectRoomNumber", param);	
		return rsList;  
	}

	/**
	 * 방등록 (sequence)
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public void insertChatMastr(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		insert("common.chat.insertChatMastr", param);
		
	}
	
	/**
	 * 방인원 등록 (sequence)
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public void insertChatRoom(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		insert("common.chat.insertChatRoom", param);
		
	}

	/**
	 * 메세지 등록하기
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public void insertChatMessage(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		insert("common.chat.insertChatMessage", param);
	}
	
	/**
	 * 채팅 대화내용 불러오기 
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectChatTalkList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("common.chat.selectChatTalkList", param);	
		return rsList;  
	}

	/**
	 * 썸네일 불러오기 
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectThumNail(Map<String, Object> param) {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("common.chat.selectThumNail", param);	
		return rsList;  
	}

	/**
	 * 실시간상담 문의 등록
	 * @param param 검색 조건
	 * @throws Exception 
	 */
	public void insertChatCnsl(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		insert("common.chat.insertChatCnsl", param);	
	}
	
	/**
	 * 실시간상담 글 확인 업데이트
	 * @param param 검색 조건
	 * @throws Exception 
	 */
	public void updateChatCnfm(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		insert("common.chat.updateChatCnfm", param);	
	}
	
	/**
	 * 상담 테이블 순번 조회
	 * @param param 검색 조건
	 * @throws Exception 
	 */
	public Map<String, String> selectCnslSn() throws Exception {
		// TODO Auto-generated method stub
		return selectOne("common.chat.selectCnslSn");	
	}
	
	/**
	 * 실시간상담 이력 목록 조회
	 * @param param 검색 조건
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectChatHistList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return selectList("common.chat.selectChatHistList",param);	
	}
	
}
