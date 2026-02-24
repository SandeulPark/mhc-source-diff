package kr.go.mhc.mhcweb.tg.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.go.mhc.common.DMultiEgovAbstractMapper;

/**
 * @Class Name : DeviceMngtServiceDAO.java
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


@Repository("web.tg.DeviceMngtServiceDAO")
public class DeviceMngtServiceDAO extends DMultiEgovAbstractMapper  {

	//디바이스 배포 건수 조회
	public Map<String, Object> getDeviceCnt(Map<String, Object> param) throws Exception {
		Map<String, Object> rsMap = selectOne("mhc.web.tg.devicemngt.deviceCnt", param);
		return rsMap;
	}
	
	//디바이스 배포 목록 조회
	public List<Map<String, Object>> getDeviceStockList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.tg.devicemngt.deviceStrockList", param);
		return rsList;
	}
	//디바이스 메뉴 리스트 조회
	public List<Map<String, Object>> getDeviceMenuList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.tg.devicemngt.devicMenuList", param);
		return rsList;
	}
	
	//디바이스 등록
	public void addDevice(Map<String, Object> param) throws Exception {
		insert("mhc.web.tg.devicemngt.addDevice", param);		
	}
	
	//디바이스 반납
		public void returnDevice(Map<String, Object> param) throws Exception {
			insert("mhc.web.tg.devicemngt.returnDevice", param);		
		}
		
	//디바이스 메뉴 리스트 조회
	public List<Map<String, Object>> getDeviceVenderList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.tg.devicemngt.deviceVenderList", param);
		return rsList;
	}
	//디바이스 메뉴 리스트 조회
	public List<Map<String, Object>> getDeviceSelectModelList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.tg.devicemngt.deviceSelectModelList", param);
		return rsList;
	}	

	//디바이스  재고 상세 조회
	public Map<String, Object> getDeviceInvenDtls(Map<String, Object> param) throws Exception {
		Map<String, Object> rsMap = selectOne("mhc.web.tg.devicemngt.getDeviceInvenDtls", param);
		return rsMap;
	}

	//디바이스 재고 대상자 조회
	public List<Map<String, Object>> getDeviceTargetList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.tg.devicemngt.getDeviceTargetList", param);
		return rsList;
	}
	
	//디바이스 등록
	public void pymntDevice(Map<String, Object> param) throws Exception {
		insert("mhc.web.tg.devicemngt.pymntDevice", param);		
	}
	
}
