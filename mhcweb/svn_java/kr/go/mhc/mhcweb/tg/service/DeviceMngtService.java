package kr.go.mhc.mhcweb.tg.service;

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

public interface DeviceMngtService {
	
	/**
	 * 디바이스 배포 건수 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getDeviceCnt(Map<String, Object> param) throws Exception;
	
	/**
	 * 디바이스 배포 리스트 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getDeviceStockList(Map<String, Object> param) throws Exception;
	
	/**
	 * 디바이스 배포 리스트 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getDeviceMenuList(Map<String, Object> param) throws Exception;
	
	/**
	 * 디바이스 추가
	 * @param
	 * @return
	 * @throws Exception
	 */
	public void addDevice(Map<String, Object> param) throws Exception;

	/**
	 * 디바이스 반납
	 * @param
	 * @return
	 * @throws Exception
	 */
	public void returnDevice(Map<String, Object> param) throws Exception;
	
	/**
	 * 디바이스 배포 리스트 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getDeviceVenderList(Map<String, Object> param) throws Exception;
	
	/**
	 * 디바이스 배포 리스트 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getDeviceSelectModelList(Map<String, Object> param) throws Exception;
	
	
	
	/**
	 * 디바이스 재고 상세 정보 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getDeviceInvenDtls(Map<String, Object> param) throws Exception;
	
	/**
	 * 디바이스 재고 대상자조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getDeviceTargetList(Map<String, Object> param) throws Exception;
	
	/**
	 * 디바이스 지급
	 * @param
	 * @return
	 * @throws Exception
	 */
	public void pymntDevice(Map<String, Object> param) throws Exception;
}
