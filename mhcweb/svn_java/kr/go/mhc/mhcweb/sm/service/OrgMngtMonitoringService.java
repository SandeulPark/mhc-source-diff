package kr.go.mhc.mhcweb.sm.service;

import java.util.List;
import java.util.Map;

/**
 * @Class Name : OrgMngtMonitoringService.java
 * @Description :
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

public interface OrgMngtMonitoringService {
	
	/**
	 * 기관관리자 정보 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception
	 */
	public List<Map<String, String>> getOrgMngtMonitoringList(Map<String, Object> param) throws Exception;

	/**
	 * 휴면계정 관리자 삭제
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public void deleteOrgMngtDormant(Map<String, Object> param) throws Exception;

	/**
	 * 2개월이상 미접속 관리자 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception
	 */
	public List<Map<String, String>> getOrgMngtUnconnectList(Map<String, Object> param) throws Exception;

	/**
	 * 휴면계정 관리자 휴면상태해제
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public void releaseOrgMngtDormant(Map<String, Object> param) throws Exception;
}
