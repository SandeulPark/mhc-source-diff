package kr.or.khealth.smhc.smhcweb.tg.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.or.khealth.smhc.smhcweb.tg.service.DeviceDistrbtMngtService;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * @Class Name :DeviceDistrbtMngtServiceImpl.java
 * @Description : 관리자 WEB에서 사용하는  디바이스 배포 관리 업무에 필요한 DAO와 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2018.04.11		이태석			최초생성
 
 * @author thejoin
 * @since 2018.04.11
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */
@Service(value="web.tg.DeviceDistrbtMngtService")
public class DeviceDistrbtMngtServiceImpl extends EgovAbstractServiceImpl implements DeviceDistrbtMngtService {
	
	@Resource(name= "web.tg.DeviceDistrbtMngtServiceDAO")
	private DeviceDistrbtMngtServiceDAO deviceDistrbtMngtServiceDAO;

	//디바이스 배포 건수 조회
	@Override
	public Map<String, Object> getDeviceDistrbtCnt(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return deviceDistrbtMngtServiceDAO.getDeviceDistrbtCnt(param);
	}

	//디바이스 배포 목록 조회
	@Override
	public List<Map<String, Object>> getDeviceDistrbtMngtList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String, Object>> rsList = deviceDistrbtMngtServiceDAO.getDeviceDistrbtMngtList(param);
		return rsList;
	}
	
	//디바이스 배포 건수 조회
	@Override
	public Map<String, Object> getDeviceDistrbtDtls(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return deviceDistrbtMngtServiceDAO.getDeviceDistrbtDtls(param);
	}
	
	//디바이스 배포 건수 조회
	@Override
	public void updateDeviceDistrbt(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		deviceDistrbtMngtServiceDAO.updateDeviceDistrbt(param);
	}
	
}
