package kr.go.mhc.mhcweb.mr.service;

import java.util.List;
import java.util.Map;

/**
 * @Class Name : SvcJoinInfoService.java
 * @Description : 관리자 WEB에서 사용하는 서비스 참여정보 업무를 관리하는 서비스 interface
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.09.19		이은주			최초생성
 *
 * @author gst
 * @since 2016.09.19
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

public interface SvcJoinInfoService {

	/**
	 * 서비스 참여 정보 목록조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> svcJoinInfoList(Map<String, Object> param) throws Exception;
	
	/**
	 * 서비스 참여 정보 2주간 미사용자 목록 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> svcJoinInfoTwoWeeksNull(Map<String, Object> param) throws Exception;
}
