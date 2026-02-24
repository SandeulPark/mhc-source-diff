package kr.go.mhc.mhcweb.mr.service;

import java.util.List;
import java.util.Map;

/**
 * @Class Name : MthlyHealthRptSndSttsService.java
 * @Description : 관리자 WEB에서 사용하는 월간 건강리포트 발송현황 업무를 관리하는 서비스 interface
 * @Modification Information
 * @
 * @	수정일			수정자		수정내용
 * @	----------		------		---------------------------
 * @	2016.09.20		전정은		최초생성
 * 
 * @author	gst
 * @since	2016.09.20
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

public interface MthlyHealthRptSndSttsService {

	/**
	 * 월간 건강리포트 발송현황 목록 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getMthlyHealthRptSndSttsList(Map<String, Object> param) throws Exception;
	
}
