package kr.go.mhc.mhcweb.gn.service;

import java.util.List;
import java.util.Map;

/**
 * @Class Name : MissionCodeMngtService.java
 * @Description : 관리자 WEB에서 사용하는 미션코드관리 업무를 관리하는 서비스 interface
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.08.11		이은주			최초생성
 *
 * @author gst
 * @since 2016.08.11
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

public interface GnrlMissionCodeMngtService {
	
	/**
	 * 미션코드관리 목록 조회
	 * @param 
	 * @return 
	 * @throws Exception
	 */
	public List<Map<String, String>> getMissionCodeList(Map<String, Object> param) throws Exception;
	
	/**
	 * 미션코드관리 신규 저장
	 * @param param 저장 정보
	 * @return
	 * @throws Exception  
	 */	
	public void getMissionCodeInsert(Map<String, Object> param) throws Exception;
	
	/**
	 * 미션코드관리 수정
	 * @param param 저장 정보
	 * @return
	 * @throws Exception
	 */
	public void getMissionCodeUp(Map<String, Object> param) throws Exception;
	
	/**
	 * 미션코드관리 상세 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getMissionCodeDtls(Map<String, Object> param) throws Exception;
	
	/**
	 * 미션코드관리 삭제	
	 * @param param PK 정보
	 * @return
	 * @throws Exception
	 */
	public void getMissionCodeDel(Map<String, Object> param) throws Exception;

	/**
	 * 신규 미션코드 조회
	 * PK 정보로 단일 ROW 조회
	 * @param param PK 정보
	 * @return 검색 된 단일 ROW
	 * @throws Exception 
	 */
	public Map<String,Object> selectNewMissionCode(Map<String, Object> param) throws Exception;

}
