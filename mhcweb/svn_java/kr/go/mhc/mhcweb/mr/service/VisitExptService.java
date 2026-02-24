package kr.go.mhc.mhcweb.mr.service;

import java.util.List;
import java.util.Map;


/**
 * @Class Name : VisitExptService.java
 * @Description : 관리자 WEB에서 사용하는 방문예정 업무를 관리하는 서비스 interface
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.10.24		이은주			최초생성
 *
 * @author gst
 * @since 2016.10.24
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

public interface VisitExptService {
	
	/**
	 * 방문예정 목록조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> visitExptList(Map<String, Object> param) throws Exception;
	
	/**
	 * 방문 일정 예약현황
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectVisitAllList(Map<String, Object> param) throws Exception;
		
	public void updateVisitList(Map<String, Object> param) throws Exception;
	
	
}
