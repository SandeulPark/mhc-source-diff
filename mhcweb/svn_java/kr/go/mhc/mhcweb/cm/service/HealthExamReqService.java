package kr.go.mhc.mhcweb.cm.service;

import java.util.List;
import java.util.Map;

/**
 * @Class Name : HealthExamReqService.java
 * @Description : 관리자 WEB에서 사용하는 건강검진 데이터 수정 요청사항 업무를 관리하는 서비스 interface
 * @Modification Information
 * @
 * @	  수정일		수정자			수정내용
 * @	----------		--------		---------------------------
 * @	2018.09.11		오샘이			최초생성
 *
 * @author theJoin
 * @since 2018.09.11
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */


public interface HealthExamReqService {
	

	/**
	 * 건강검진 데이터 수정 요청사항 목록 조회
	 * PK 정보로 ROW 조회
	 * @param param PK 정보
	 * @return 검색 된 ROW
	 * @throws Exception 
	 */
	public List<Map<String, String>> getHealthExamReqList(Map<String, Object> param) throws Exception;
	
	/**
	 * 건강검진 데이터 수정 요청사항 상세
	 * PK 정보로 단일 ROW 조회
	 * @param param PK 정보
	 * @return 검색된 ROW 
	 * @throws Exception
	 */
	public Map<String, String> getHealthExamReqDtls(Map<String, Object> param) throws Exception;	
	
	
	/**
	 * 건강검진 데이터 수정 요청사항 이전글, 다음글 조회
	 * PK 정보로 ROW 조회
	 * @param param PK 정보
	 * @return 검색 된 ROW
	 * @throws Exception 
	 */
	public List<Map<String, String>> getHealthExamReqBeAfList(Map<String, Object> param) throws Exception;

	
	
	
	
	/**
	 * 신규 건강검진 데이터 수정 요청사항 등록
	 * @param param 저장 정보
	 * @return
	 * @throws Exception  
	 */
	public void insertHealthExamReq(Map<String, Object> param) throws Exception;
	
	/**
	 * 건강검진 데이터 수정 요청사항 수정
	 * param 업데이트
	 * @param param 수정 정보
	 * @return
	 * @throws Exception
	 */
	public void updateHealthExamReq(Map<String, Object> param) throws Exception;
	
	/**
	 * 건강검진 데이터 수정 요청사항 수정
	 * param 업데이트
	 * @param param 수정 정보
	 * @return
	 * @throws Exception
	 */
	public void deleteHealthExamReq(Map<String, Object> param) throws Exception;	

	
	/**
	 * 건강검진 데이터 수정 요청사항 댓글 목록 조회
	 * PK 정보로 ROW 조회
	 * @param param PK 정보
	 * @return 검색 된 ROW
	 * @throws Exception 
	 */
	public List<Map<String, Object>> getHealthExamReqCommentList(Map<String, Object> param) throws Exception;
	
	/**
	 * 건강검진 데이터 수정 요청사항 댓글 등록
	 * @param param 저장 정보
	 * @return
	 * @throws Exception  
	 */
	public void insertHealthExamReqComment(Map<String, Object> param) throws Exception;
	
	/**
	 * 건강검진 데이터 수정 요청사항 댓글 저장
	 * @param param 저장 정보
	 * @return
	 * @throws Exception  
	 */
	public void updateHealthExamReqComment(Map<String, Object> param) throws Exception;	
	
	/**
	 * 건강검진 데이터 수정 요청사항 댓글 삭제
	 * @param param 저장 정보
	 * @return
	 * @throws Exception  
	 */
	public void deleteHealthExamReqComment(Map<String, Object> param) throws Exception;

	
	public List<Map<String, String>> getAttachFileList(Map<String, String> rsMap) throws Exception;

}
