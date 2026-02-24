package kr.go.mhc.mhcweb.pm.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

/**
 * @Class Name : DeviceUserRateSttusDAO.java
 * @Description : 관리자 WEB에서 사용하는 디바이스 이용률 실적관리 업무 DataBase 연동 관리하는 Class
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

@Repository("web.pm.DeviceUserRateSttusDAO")
public class DeviceUserRateSttusDAO extends EgovAbstractMapper {

	//디바이스 이용률 조회
	public List<Map<String, Object>> selectDeviceUserRateSttusList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.pm.deviceuserratesttus.selectDeviceUserRateSttusList", param);
		return rsList;
	}
	
	//디바이스 이용률 상세 조회
	public List<Map<String, Object>> selectDeviceUserRateSttusTrgterList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.pm.deviceuserratesttus.selectDeviceUserRateSttusTrgterList", param);
		return rsList;
	}
	
	//디바이스 이용률 조회(실적 개편)
	public List<Map<String, Object>> selectDeviceUserRateSttusListNew(Map<String, Object> param) {
		List<Map<String, Object>> rsList = selectList("mhc.web.pm.deviceuserratesttus.selectDeviceUserRateSttusListNew", param);
		return rsList;
	}
	
	//디바이스 이용률 상세 조회(실적 개편)
	public List<Map<String, Object>> selectDeviceUserRateSttusTrgterListNew(Map<String, Object> param) {
		List<Map<String, Object>> rsList = selectList("mhc.web.pm.deviceuserratesttus.selectDeviceUserRateSttusTrgterListNew", param);
		return rsList;
	}	
	
	
	
	
}
