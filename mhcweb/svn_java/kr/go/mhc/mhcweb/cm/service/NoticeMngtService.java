package kr.go.mhc.mhcweb.cm.service;

import java.util.List;
import java.util.Map;

/**
 * @Class Name : NoticeMngtService.java
 * @Description : 관리자 WEB에서 사용하는 공지관리 업무를 관리하는 서비스 interface
 * @Modification Information
 * @
 * @	수정일				수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.07.07		이태석			최초생성
 *
 * @author gst
 * @since 2016.07.07
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */
public interface NoticeMngtService {
	
	/**
	 * 공지 관리 목록 수 조회
	 * @param 
	 * @return ROW count 정보 
	 * @throws Exception
	 */
	public int getNoticeMngtListCount(Map<String, Object> param) throws Exception;

	/**
	 * 공지 관리 목록 조회
	 * PK 정보로 ROW 조회
	 * @param param PK 정보
	 * @return 검색 된 ROW
	 * @throws Exception 
	 */
	public List<Map<String, String>> getNoticeMngtList(Map<String, Object> param) throws Exception;
	
	/**
	 * 신규 공지 저장
	 * @param param 저장 정보
	 * @return
	 * @throws Exception  
	 */
	public void getNoticeMngtRegInsert(Map<String, Object> param) throws Exception;
	
	/**
	 * 공지 상세
	 * PK 정보로 단일 ROW 조회
	 * @param param PK 정보
	 * @return 검색된 ROW 
	 * @throws Exception
	 */
	public Map<String, String> getNoticeDtls(Map<String, Object> param) throws Exception;
	
	/**
	 * 이전글_다음글 목록 조회
	 * PK 정보로 ROW 조회
	 * @param param PK 정보
	 * @return 검색 된 ROW
	 * @throws Exception 
	 */
	public List<Map<String, String>> getBefAftNoticeList(Map<String, Object> param) throws Exception;
	
	/**
	 * 공지 수정
	 * param 업데이트
	 * @param param 수정 정보
	 * @return
	 * @throws Exception
	 */
	public void getNoticeMngtUp(Map<String, Object> param) throws Exception;
	
	/**
	 * 공지_미사용(삭제)	
	 * @param param PK 정보
	 * @return
	 * @throws Exception
	 */
	public void getNoticeMngtDel(Map<String, Object> param) throws Exception;
	
	
	
	
	/**
	 * 공지 댓글 목록 조회
	 * PK 정보로 ROW 조회
	 * @param param PK 정보
	 * @return 검색 된 ROW
	 * @throws Exception 
	 */
	public List<Map<String, Object>> selectNoticeCommentList(Map<String, Object> param) throws Exception;
	
	/**
	 * 공지 댓글 등록
	 * @param param 저장 정보
	 * @return
	 * @throws Exception  
	 */
	public void insertNoticeComment(Map<String, Object> param) throws Exception;
	
	/**
	 * 공지 댓글 저장
	 * @param param 저장 정보
	 * @return
	 * @throws Exception  
	 */
	public void updateNoticeComment(Map<String, Object> param) throws Exception;	
	
	/**
	 * 공지 댓글 삭제
	 * @param param 저장 정보
	 * @return
	 * @throws Exception  
	 */
	public void deleteNoticeComment(Map<String, Object> param) throws Exception;

	/**
	 * 공지 대댓글 등록
	 * @param param 저장 정보
	 * @return
	 * @throws Exception  
	 */
	public void insertNoticeChildComment(Map<String, Object> param) throws Exception;
	
	/**
	 * 공지 대댓글 삭제
	 * @param param 저장 정보
	 * @return
	 * @throws Exception  
	 */
	public void deleteNoticeChildComment(Map<String, Object> param) throws Exception;

	/**
	 * 공지 상세 파일목록
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> getAttachFileList(Map<String, String> param) throws Exception;
	
	/**
	 * 공지 조회자 정보
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> getNoticeInquire(Map<String, Object> param) throws Exception;
	
	/**
	 * 공지 조회수 등록
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public void insertNoticeInquire(Map<String, Object> param) throws Exception;
	
}
