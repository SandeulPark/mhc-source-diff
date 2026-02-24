package kr.go.mhc.mhcweb.sv.service;

import java.util.List;
import java.util.Map;

/**
 * @Class Name : SvcMngtService.java
 * @Description : 관리자 WEB에서 사용하는 건강정보관리 업무를 관리하는 서비스 interface
 * @Modification Information
 * @
 * @	수정일				수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.08.08		이태석			최초생성
 *
 * @author gst
 * @since 2016.08.08
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

public interface SvcMngtService {

	/**
	 * 건강정보관리 목록 수 조회
	 * @param 
	 * @return ROW count 정보 
	 * @throws Exception
	 */
	public int getHealthInfoMngtListCount(Map<String, Object> param) throws Exception;

	/**
	 * 건강정보관리 목록 조회
	 * PK 정보로 ROW 조회
	 * @param param PK 정보
	 * @return 검색 된 ROW
	 * @throws Exception 
	 */
	public List<Map<String, String>> getHealthInfoMngtList(Map<String, Object> param) throws Exception;
	
	/**
	 * 건강정보 상세 화면 호출 
	 * PK 정보로 단일 ROW 조회
	 * @param param PK 정보
	 * @return 검색 된 단일 ROW
	 * @throws Exception 
	 */
	public Map<String, Object> getHealthInfoDtls(Map<String, Object> param) throws Exception;
	
	/**
	 * 건강정보관리_적용그룹 목록 조회
	 * PK 정보로 ROW 조회
	 * @param param PK 정보
	 * @return 검색 된 ROW
	 * @throws Exception 
	 */
	public List<Map<String, String>> getCmntyGroupList(Map<String, Object> param) throws Exception;
	
	/**
	 * 그룹 목록 조회
	 * PK 정보로 ROW 조회
	 * @param param PK 정보
	 * @return 검색 된 ROW
	 * @throws Exception 
	 */
	public List<Map<String, String>> getAllCmntyGroupList(Map<String, Object> param) throws Exception;

	/**
	 * 선택한 군 분류 조회 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> getCmntyMclasList(Map<String,Object> param) throws  Exception;

	/**
	 * 군분류 전체 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> getAllCmntyMclasList(Map<String, Object> param) throws Exception;
	/**
	 * 선택한 만성질환 군 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> getCmntyChronicList(Map<String,Object> param) throws  Exception;

	/**
	 * 댓글 정보 조회
	 * PK 정보로 ROW 조회
	 * @param param PK 정보
	 * @return 검색 된 ROW
	 * @throws Exception 
	 */
	public List<Map<String, String>> getHealthInfoCmmntList(Map<String, Object> param) throws Exception;
	
	/**
	 * 댓글 첨부파일 조회
	 * PK 정보로 ROW 조회
	 * @param param PK 정보
	 * @return 검색 된 ROW
	 * @throws Exception 
	 */
	public List<Map<String, String>> getHealthInfoCmmntAttchList(Map<String, Object> param) throws Exception;
	
	/**
	 * 표현 정보 조회
	 * PK 정보로 ROW 조회
	 * @param param PK 정보
	 * @return 검색 된 ROW
	 * @throws Exception 
	 */
	public List<Map<String, String>> getHealthInfoGoodList(Map<String, Object> param) throws Exception;
	
	/**
	 * 건강정보상세 수정  
	 * @param 수정된 param 정보
	 * @return 
	 * @throws Exception 
	 */
	public void getHealthInfoDtlsUpdate(Map<String, Object> param) throws Exception;
	
	/**
	 * 댓글 저장  
	 * @param param 저장 정보
	 * @return 
	 * @throws Exception 
	 */
	public void getHealthInfoCmmntInsert(Map<String, Object> param) throws Exception;
	
	/**
	 * 답변 저장  
	 * @param param 저장 정보
	 * @return 
	 * @throws Exception 
	 */
	public void getHealthInfoReCmmntInsert(Map<String, Object> param) throws Exception;
	
	/**
	 * 답변 삭제  
	 * @param param 저장 정보
	 * @return 
	 * @throws Exception 
	 */
	public void gethealthInfoReCmmntDelete(Map<String, Object> param) throws Exception;
	
	/**
	 * 신규 건강정보 저장  
	 * @param param 정보
	 * @return 
	 * @throws Exception 
	 */
	public void getnewHealthInfoInsert(Map<String, Object> param) throws Exception;

	/**
	 * 상세 글 첨부파일 조회
	 * @param param 정보
	 * @return 
	 * @throws Exception 
	 */
	public List<Map<String, String>> getSelectAttchFileList(Map<String, Object> param) throws Exception;

	/**
	 * 건강정보 게시여부 컨트롤  
	 * @param param 저장 정보
	 * @return 
	 * @throws Exception 
	 */
	public void updateArticlePostClf(Map<String, Object> param) throws Exception;

	/**
	 * 건강정보 상세 삭제
	 * @param param
	 * @throws Exception
	 */
	public void healthInfoDtlsDelete(Map<String,Object> param) throws Exception;
	
	/**
	 * 댓글 목록 수 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public int getReCnt(Map<String, Object> param) throws Exception;
	
	/**
	 * 좋아요 목록 수 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public int getGoodCnt(Map<String, Object> param) throws Exception;
}
