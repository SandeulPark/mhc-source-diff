package kr.go.mhc.mhcweb.gn.service;

import java.util.List;
import java.util.Map;


/**
 * @Class Name : GnrlUserInfoMngtService.java
 * @Description : 일반 대상자 정보 조회 서비스 interface
 * @Modification Information
 * @
 * @	수정일			수정자		수정내용
 * @	----------		------		---------------------------
 * @	2019.11.22					최초생성
 *
 * @author theJoin
 * @since 
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

public interface GnrlUserAppActHistService {

	/**
	 * 대상자 앱 활동기록
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception 
	 */
	public List<Map<String, Object>> trgtAppActList(Map<String, Object> param) throws Exception;
	
	/**
	 * 대상자 앱 활동기록 카운트
	 * @param 
	 * @return ROW count 정보 
	 * @throws Exception
	 */
	public int trgtAppActListCount(Map<String, Object> param) throws Exception;

}
