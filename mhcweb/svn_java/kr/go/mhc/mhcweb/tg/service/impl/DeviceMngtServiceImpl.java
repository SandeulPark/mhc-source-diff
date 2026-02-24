package kr.go.mhc.mhcweb.tg.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import kr.go.mhc.mhcweb.tg.service.DeviceMngtService;

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
@Service(value="web.tg.DeviceMngtService")
public class DeviceMngtServiceImpl extends EgovAbstractServiceImpl implements DeviceMngtService {
	
	@Resource(name= "web.tg.DeviceMngtServiceDAO")
	private DeviceMngtServiceDAO deviceMngtServiceDAO;

	//디바이스 배포 건수 조회
	@Override
	public Map<String, Object> getDeviceCnt(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return deviceMngtServiceDAO.getDeviceCnt(param);
	}

	@Override
	public List<Map<String, Object>> getDeviceStockList(Map<String, Object> param) throws Exception {		
		List<Map<String, Object>> rsList = deviceMngtServiceDAO.getDeviceStockList(param);
		return rsList;		
	}
	
	@Override
	public List<Map<String, Object>> getDeviceMenuList(Map<String, Object> param) throws Exception {		
		List<Map<String, Object>> rsList = deviceMngtServiceDAO.getDeviceMenuList(param);
		return rsList;		
	}
	
	@Override
	public void addDevice(Map<String, Object> param) throws Exception {		
		deviceMngtServiceDAO.addDevice(param);			
	}
	
	@Override
	public void returnDevice(Map<String, Object> param) throws Exception {		
		deviceMngtServiceDAO.returnDevice(param);			
	}

	@Override
	public List<Map<String, Object>> getDeviceVenderList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = deviceMngtServiceDAO.getDeviceVenderList(param);
		return rsList;		
	}

	@Override
	public List<Map<String, Object>> getDeviceSelectModelList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = deviceMngtServiceDAO.getDeviceSelectModelList(param);
		return rsList;		
	}

	@Override
	public Map<String, Object> getDeviceInvenDtls(Map<String, Object> param) throws Exception {
		return deviceMngtServiceDAO.getDeviceInvenDtls(param);
	}

	@Override
	public List<Map<String, Object>> getDeviceTargetList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = deviceMngtServiceDAO.getDeviceTargetList(param);
		return rsList;
	}
	
	@Override
	public void pymntDevice(Map<String, Object> param) throws Exception {		
		deviceMngtServiceDAO.pymntDevice(param);			
	}
	
}
