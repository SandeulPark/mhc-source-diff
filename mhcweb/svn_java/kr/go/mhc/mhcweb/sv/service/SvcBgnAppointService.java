package kr.go.mhc.mhcweb.sv.service;

import java.util.List;
import java.util.Map;

/**
 * @Class Name : SvcBgnAppointService.java
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

public interface SvcBgnAppointService {

	public List<Map<String, String>> selectServiceBeginApList(Map<String, Object> param) throws Exception;
	
	public Map<String, Object> selectServiceBeginApDtls(Map<String, Object> param) throws Exception;
	
	public List<Map<String, Object>> selectServiceSchedule(Map<String, Object> param) throws Exception;
	
	public void updateReExamSttus(Map<String, Object> param) throws Exception;
}
