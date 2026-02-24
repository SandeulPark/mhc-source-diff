package kr.or.khealth.smhc.smhcweb.tg.service;

import java.util.List;
import java.util.Map;

/**
 * @Class Name : DeviceDistrbtMngtService.java
 * @Description : 관리자 WEB에서 사용하는 디바이스 배포 관리 업무를 관리하는 서비스 interface
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2018.04.11		이태석			최초생성
 *
 * @author thejoin
 * @since 2018.04.11
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

public interface DeviceDistrbtMngtService {
	
	/**
	 * 디바이스 배포 건수 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getDeviceDistrbtCnt(Map<String, Object> param) throws Exception;
	
	/**
	 * 디바이스 배포 리스트 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getDeviceDistrbtMngtList(Map<String, Object> param) throws Exception;
	
	/**
	 * 디바이스 배포 상세 정보 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getDeviceDistrbtDtls(Map<String, Object> param) throws Exception;

	/**
	 * 디바이스 배포 완료
	 * @param
	 * @return
	 * @throws Exception
	 */
	public void updateDeviceDistrbt(Map<String, Object> param) throws Exception;
}
