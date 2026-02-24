package kr.go.mhc.mhcweb.sm.service;

import java.util.List;
import java.util.Map;

/**
 * @Class Name : BoardService.java
 * @Description : 관리자 WEB에서 사용하는 질의응답 관리하는 서비스 interface
 * @Modification Information
 * @
 * @	수정일			수정자		수정내용
 * @	----------		------		---------------------------
 * @	2017.03.16		이현규		최초생성
 *
 * @author theJoin
 * @since 2017.03.16
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

public interface BoardService {

	/**
	 * 질의응답 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception 
	 */
	public List<Map<String, Object>> getBoardQnaList(Map<String, Object> param) throws Exception;
	
	/**
	 * 질의응답 목록 전체건수 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception 
	 */
	public int getBoardQnaListCount(Map<String, Object> param) throws Exception;
	
	/**
	 * 질의응답 질의 상세 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception 
	 */
	public Map<String, Object> getQueDtls(Map<String, Object> param) throws Exception;
	
	/**
	 * 질의응답 응답 상세 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception 
	 */
	public Map<String, Object> getAnsDtls(Map<String, Object> param) throws Exception;
	
	
	/**
	 * 질의응답 저장
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception 
	 */
	public int saveBoardQna(Map<String, Object> param) throws Exception;
	
	/**
	 * 질의응답 첨부 파일 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception 
	 */
	public List<Map<String, Object>> getQnaAttchFileList(Map<String, Object> param) throws Exception;
	
	/**
	 * 조회수 update
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception 
	 */
	public void setBoardInquireCnt(Map<String, Object> param) throws Exception;
	
	/**
	 * 게시판 삭제
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception 
	 */
	public void deleteBoard(Map<String, Object> param) throws Exception;
}
