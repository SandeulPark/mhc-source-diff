package kr.or.khealth.smhc.common.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import kr.or.khealth.smhc.common.service.ChatService;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * @Class Name : ChatServiceImpl.java
 * @Description : 모바일 헬스케어 App에서 사용하는 공통업무에서 필요한 DAO와 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.06.27		허광일			최초생성
 * @author gst
 * @since 2016.06.27
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Service("common.ChatService")
public class ChatServiceImpl extends EgovAbstractServiceImpl implements ChatService{
	
	@Resource(name="common.ChatServiceDAO")
    private ChatServiceDAO chatServiceDAO;

	/**
	 * 방 목록 조회하기
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectRoomList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return chatServiceDAO.selectRoomList(param);
	}
	
	/**
	 * 방번호 조회하기 (sequence)
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectRoomNumber(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return chatServiceDAO.selectRoomNumber(param);
	}


	/**
	 * 방(mastr) 등록
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public void insertChatMastr(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		chatServiceDAO.insertChatMastr(param);
	}
	
	/**
	 * 방 인원(room) 등록
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public void insertChatRoom(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		chatServiceDAO.insertChatRoom(param);
	}

	/**
	 * 메세지 저장히기
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public void insertChatMessage(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		chatServiceDAO.insertChatMessage(param);
	}


	/**
	 * 채팅 내용 불러오기
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectChatTalkList(Map<String, Object> param) 
			throws Exception {
		return chatServiceDAO.selectChatTalkList(param);
	}


	/**
	 * 썸네일 불러오기
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectThumNail(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return chatServiceDAO.selectThumNail(param);
	}

	/**
	 * 실시간상담 문의 등록
	 * @param param 검색 조건
	 * @throws Exception 
	 */
	@Override
	public void insertChatCnsl(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		chatServiceDAO.insertChatCnsl(param);
	}
	
	/**
	 * 실시간상담 글 확인 업데이트
	 * @param param 검색 조건
	 * @throws Exception 
	 */
	@Override
	public void updateChatCnfm(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		chatServiceDAO.updateChatCnfm(param);
	}
	
	/**
	 * 상담 테이블 순번 조회
	 * @param param 검색 조건
	 * @throws Exception 
	 */
	@Override
	public Map<String, String> selectCnslSn() throws Exception {
		// TODO Auto-generated method stub
		return chatServiceDAO.selectCnslSn();
	}
	
	/**
	 * 실시간상담 이력 목록 조회
	 * @param param 검색 조건
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, String>> selectChatHistList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return chatServiceDAO.selectChatHistList(param);
	}
	
}
