package kr.go.mhc.mhcweb.mr.service;

import java.util.List;
import java.util.Map;

/**
 * @Class Name : MealRegService.java
 * @Description : 관리자 WEB에서 사용하는 식단등록 업무를 관리하는 서비스 interface
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.12.05		이은주			최초생성
 *
 * @author gst
 * @since 2016.12.05
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

public interface MealRegService {
	
	//식단등록 목록 조회
	public List<Map<String, Object>> mealRegList(Map<String, Object> param) throws Exception;
	
	//식단등록 상세 조회
	public Map<String, Object> mealRegPopDtls(Map<String, Object> param) throws Exception;
	
	//식단등록 상세 조회 2
	public List<Map<String, Object>> mealRegPopDtls2(Map<String, Object> param) throws Exception;
}
