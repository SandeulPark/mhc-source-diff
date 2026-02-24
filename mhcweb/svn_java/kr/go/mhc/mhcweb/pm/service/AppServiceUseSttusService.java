package kr.go.mhc.mhcweb.pm.service;

import java.util.List;
import java.util.Map;

/**
 * @Class Name :AppServiceUseSttusService.java
 * @Description : 관리자 WEB에서 사용하는 대상자 집중상담 이용현황 실적관리 업무를 관리하는 컨트롤러 Class
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

public interface AppServiceUseSttusService {

	/**
	 * App서비스 이용현황 목록 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectAppServiceUseSttusList(Map<String, Object> param) throws Exception;
	
	
	/**
	 * App서비스 이용현황 대상자 목록 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectAppServiceUseSttusTrgterList(Map<String, Object> param) throws Exception;

	/**
	 * App서비스 이용현황 목록 조회(실적개편)
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectAppServiceUseSttusListNew(Map<String, Object> param)  throws Exception;

	/**
	 * App서비스 이용현황 대상자 목록 조회(실적개편)
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectAppServiceUseSttusTrgterListNew(Map<String, Object> param);

}
