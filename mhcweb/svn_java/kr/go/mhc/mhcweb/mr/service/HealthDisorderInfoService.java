package kr.go.mhc.mhcweb.mr.service;

import java.util.List;
import java.util.Map;

/**
 * @Class Name : HealthDisorderInfoService.java
 * @Description : 관리자 WEB에서 사용하는 건강 이상 정보 업무를 관리하는 서비스 interface
 * @Modification Information
 * @
 * @	수정일			수정자		수정내용
 * @	----------		-----		---------------------------
 * @	2016.09.19		이현규		최초생성
 *
 * @author gst
 * @since 2016.09.19
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

public interface HealthDisorderInfoService {
	
	/**
	 * 건강 이상 정보
	 * 건강 이상 정보 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception
	 */
	public List<Map<String, String>> selectHealthDisorderInfoList(Map<String, Object> param) throws Exception;
}
