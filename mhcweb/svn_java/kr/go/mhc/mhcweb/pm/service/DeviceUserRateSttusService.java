package kr.go.mhc.mhcweb.pm.service;

import java.util.List;
import java.util.Map;

/**
 * @Class Name : DeviceUserRateSttusService.java
 * @Description : 관리자 WEB에서 사용하는 디바이스 이용률 실적관리 업무를 관리하는 컨트롤러 Class
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

public interface DeviceUserRateSttusService {

	/**
	 * 디바이스 이용률 목록 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectDeviceUserRateSttusList(Map<String, Object> param) throws Exception;

	
	/**
	 * 디바이스 이용률 대상자 목록 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectDeviceUserRateSttusTrgterList(Map<String, Object> param) throws Exception;

	/**
	 * 디바이스 이용률 목록 조회(실적 개편)
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectDeviceUserRateSttusListNew(Map<String, Object> param) throws Exception;

	/**
	 * 디바이스 이용률 대상자 목록 조회(실적 개편)
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectDeviceUserRateSttusTrgterListNew(Map<String, Object> param) throws Exception;
	
}
