package kr.go.mhc.mhcweb.pm.service;

import java.util.List;
import java.util.Map;

/**
 * @Class Name : WorkManagerSttusService.java
 * @Description : 관리자 WEB에서 사용하는 업무담당자 현황 실적관리 업무를 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2018.10.11		유준영			최초생성
 *
 * @author theJoin
 * @since 2018.10.11
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

public interface WorkManagerSttusService {

	/**
	 * 업무담당자 현황 목록 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectWorkManagerSttusList(Map<String, Object> param) throws Exception;
	
	/**
	 * 업무담당자 현황 담당자 목록 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectWorkManagerSttusTrgterList(Map<String, Object> param) throws Exception;
	
}



