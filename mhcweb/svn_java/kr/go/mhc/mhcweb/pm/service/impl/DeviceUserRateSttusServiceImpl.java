package kr.go.mhc.mhcweb.pm.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.go.mhc.mhcweb.pm.service.DeviceUserRateSttusService;

import org.springframework.stereotype.Service;


/**
 * @Class Name DeviceUserRateSttusServiceImpl.java
 * @Description : 관리자 WEB에서 사용하는 디바이스 이용률 실적관리 업무에 필요한 DAO와 연동 관리하는 Class
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

@Service(value= "web.pm.DeviceUserRateSttusService")
public class DeviceUserRateSttusServiceImpl implements DeviceUserRateSttusService {

	@Resource(name= "web.pm.DeviceUserRateSttusDAO")
	private DeviceUserRateSttusDAO deviceUserRateSttusDAO;

	@Override
	public List<Map<String, Object>> selectDeviceUserRateSttusList(Map<String, Object> param) throws Exception {
		return deviceUserRateSttusDAO.selectDeviceUserRateSttusList(param);
	}

	@Override
	public List<Map<String, Object>> selectDeviceUserRateSttusTrgterList(Map<String, Object> param) throws Exception {
		return deviceUserRateSttusDAO.selectDeviceUserRateSttusTrgterList(param);
	}

	@Override
	public List<Map<String, Object>> selectDeviceUserRateSttusListNew(Map<String, Object> param) throws Exception {
		return deviceUserRateSttusDAO.selectDeviceUserRateSttusListNew(param);
	}

	@Override
	public List<Map<String, Object>> selectDeviceUserRateSttusTrgterListNew(Map<String, Object> param) throws Exception {
		return deviceUserRateSttusDAO.selectDeviceUserRateSttusTrgterListNew(param);
	}
	
}
