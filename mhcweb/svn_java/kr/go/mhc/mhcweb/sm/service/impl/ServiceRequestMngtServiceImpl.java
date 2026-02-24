package kr.go.mhc.mhcweb.sm.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.go.mhc.mhcweb.sm.service.BoardService;
import kr.go.mhc.mhcweb.sm.service.ServiceRequestMngtService;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * @Class Name : ServiceRequestMngtServiceImpl.java
 * @Description : 관리자 WEB에서 사용하는 운영처리 필요한 DAO와 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자		수정내용
 * @	----------		------		---------------------------
 * @	2017.03.30		이현규		최초생성
 *
 * @author theJoin
 * @since 2017.03.30
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Service("web.sm.ServiceRequestMngtService")
public class ServiceRequestMngtServiceImpl extends EgovAbstractServiceImpl implements ServiceRequestMngtService {
	
	@Resource(name="web.sm.ServiceRequestMngtDAO")
	private ServiceRequestMngtDAO serviceRequestMngtDAO;	
	
	@Override
	public List<Map<String, Object>> getServiceRequestList(Map<String, Object> param) throws Exception {
		return serviceRequestMngtDAO.getServiceRequestList(param);
	}
	
	@Override
	public int getServiceRequestListCount(Map<String, Object> param) throws Exception {
		return serviceRequestMngtDAO.getServiceRequestListCount(param);
	}
	
	@Override
	public void saveServiceRequestMngt(Map<String, Object> param) throws Exception {
		serviceRequestMngtDAO.saveServiceRequestMngt(param);
	}
	
	@Override
	public Map<String, Object> getServiceRequestDtls(Map<String, Object> param) throws Exception {
		return serviceRequestMngtDAO.getServiceRequestDtls(param);
	}

	@Override
	public List<Map<String, Object>> getTrgtMenuCombo(Map<String, Object> param) throws Exception {
		return serviceRequestMngtDAO.getTrgtMenuCombo(param);
	}
	
	@Override
	public List<Map<String, Object>> getServiceRequestExcelList(Map<String, Object> param) throws Exception {
		return serviceRequestMngtDAO.getServiceRequestExcelList(param);
	}
	
	@Override
	public void delServiceRequest(Map<String, Object> param) throws Exception {
		serviceRequestMngtDAO.delServiceRequest(param);
	}
	
	@Override
	public List<Map<String, String>> getOrgChkList(Map<String, Object> param) throws Exception {
		return serviceRequestMngtDAO.getOrgChkList(param);
	}
	
	@Override
	public List<String> selectMngtList() throws Exception {
		return serviceRequestMngtDAO.selectMngtList();
	}
}
