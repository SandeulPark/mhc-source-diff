package kr.go.mhc.mhcweb.mr.service;

import java.util.List;
import java.util.Map;

/**
 * @Class Name : SvcJoinRateService.java
 * @Description : 관리자 WEB에서 사용하는 서비스 참여율 업무를 관리하는 서비스 interface
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.11.15		이은주			최초생성
 *
 * @author gst
 * @since 2016.11.15
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

public interface SvcJoinRateService {

	//서비스 참여율 목록 조회
	public List<Map<String, Object>> svcJoinRateList(Map<String, Object> param) throws Exception;
}
