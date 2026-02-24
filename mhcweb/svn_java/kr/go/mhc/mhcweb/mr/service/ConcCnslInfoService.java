package kr.go.mhc.mhcweb.mr.service;

import java.util.List;
import java.util.Map;

/**
 * @Class Name : ConcCnslInfoService.java
 * @Description : 관리자 WEB에서 사용하는 집중상담 정보 업무를 관리하는 서비스 interface
 * @Modification Information
 * @
 * @	수정일			수정자		수정내용
 * @	----------		------		---------------------------
 * @	2016.09.22		전정은		최초생성
 * 
 * @author	gst
 * @since	2016.09.22
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

public interface ConcCnslInfoService {

	/**
	 * 집중상담 정보 목록 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getConcCnslInfoList(Map<String, Object> param) throws Exception;
	
}
