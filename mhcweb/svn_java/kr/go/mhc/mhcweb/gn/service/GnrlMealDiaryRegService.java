package kr.go.mhc.mhcweb.gn.service;

import java.util.List;
import java.util.Map;

/**
 * @Class Name : ConcCnslInfoService.java
 * @Description : 관리자 WEB에서 사용하는 식사일기 등록 정보 업무를 관리하는 서비스 interface
 * @Modification Information
 * @
 * @	수정일			수정자		수정내용
 * @	----------		------		---------------------------
 * @	2017.05.11				최초생성
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

public interface GnrlMealDiaryRegService {

	/**
	 * 식사일기 등록 정보 목록 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getMealDiaryRegList(Map<String, Object> param) throws Exception;
	
	/**
	 * 식사일기 등록정보 액셀 다운로드 조회 
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getMealDiaryExcel(Map<String, Object> param) throws Exception;
	
}
