package kr.go.mhc.mhcweb.mr.service;

import java.util.List;
import java.util.Map;

/**
 * @Class Name : ExcsRecService.java
 * @Description : 관리자 WEB에서 사용하는 서비스 참여정보 업무를 관리하는 서비스 interface
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.11.10		허광일			최초생성
 *
 * @author gst
 * @since 2016.11.10
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

public interface ExcsRecService {

	/**
	 * 운동 정보 목록조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectExcsRecList(Map<String, Object> param) throws Exception;
	
}
