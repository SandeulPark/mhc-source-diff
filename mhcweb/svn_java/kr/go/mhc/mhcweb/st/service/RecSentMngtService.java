package kr.go.mhc.mhcweb.st.service;

import java.util.List;
import java.util.Map;

/**
 * @Class Name : RecSentMngtService.java
 * @Description : 관리자 WEB에서 사용하는 추천문장 관리하는  서비스 interface
 * @Modification Information
 * @
 * @	수정일			수정자		수정내용
 * @	----------		------		---------------------------
 * @	2020.04.13		양현우 		최초생성
 * @author theJoin
 * @since 2020.04.13
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

public interface RecSentMngtService {
	
	/**
	 * 추천문장 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception 
	 */
	public List<Map<String, Object>> getRecSentMngt(Map<String, Object> param) throws Exception;
	
	/**
	 * 추천문장 히스트 저장
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception 
	 */
	public int recSentMngtListHist(Map<String, Object> param) throws Exception;
	
	
	
	public List<Map<String, Object>> getSelRecSentMngt(Map<String, Object> param) throws Exception;
}
