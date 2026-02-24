package kr.go.mhc.mhcweb.sm.service;

import java.util.List;
import java.util.Map;

/**
 * @Class Name : HealthDisValMngtService.java
 * @Description : 관리자 WEB에서 사용하는 건강이상수치 설정 관리하는 서비스 interface
 * @Modification Information
 * @
 * @	수정일				수정자			수정내용
 * @	----------		----		---------------------------
 * @	2017.02.17		나연이			최초생성
 *
 * @author theJoin
 * @since 2017.02.17
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

public interface HealthDisValMngtService {
	
	/**
	 * 기관별 건강이상수치 정보 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception 
	 */
	public List<Map<String, String>> getHealthDisValList(Map<String, Object> param) throws Exception;
	
	/**
	 * 기관명 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getOrgCdList(Map<String, Object> param) throws Exception;
	
	/**
	 * 기관별 건강이상수치 정보 신규등록 및 수정
	 * @param param
	 * @throws Exception
	 */
	public int mergeHealthDisVal(Map<String, Object> param) throws Exception;
}
