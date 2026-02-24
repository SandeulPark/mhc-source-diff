package kr.go.mhc.mhcweb.tg.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.go.mhc.common.DMultiEgovAbstractMapper;

/**
 * @Class Name : DeviceDistrbtMngtServiceDAO.java
 * @Description : 관리자 WEB에서 사용하는 디바이스 배포 관리 업무 DataBase 연동 관리하는 Class
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


@Repository("web.tg.DeviceDistrbtMngtServiceDAO")
public class DeviceDistrbtMngtServiceDAO extends DMultiEgovAbstractMapper  {

	//디바이스 배포 건수 조회
	public Map<String, Object> getDeviceDistrbtCnt(Map<String, Object> param) throws Exception {
		Map<String, Object> rsMap = selectOne("mhc.web.tg.devicedistrbtmngt.deviceDistrbtCnt", param);
		return rsMap;
	}
	
	//디바이스 배포 목록 조회
	public List<Map<String, Object>> getDeviceDistrbtMngtList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.tg.devicedistrbtmngt.deviceDistrbtMngtList", param);
		return rsList;
	}
	
	//디바이스 배포 건수 조회
	public Map<String, Object> getDeviceDistrbtDtls(Map<String, Object> param) throws Exception {
		Map<String, Object> rsMap = selectOne("mhc.web.tg.devicedistrbtmngt.deviceDistrbtDtls", param);
		return rsMap;
	}
	
	//디바이스 배포 완료
	public void updateDeviceDistrbt(Map<String, Object> param) throws Exception {
		update("mhc.web.tg.devicedistrbtmngt.updateDeviceDistrbtPymnt", param);
		update("mhc.web.tg.devicedistrbtmngt.updateDeviceDistrbtPymnt", param);
	}
	
}
