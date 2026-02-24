package kr.or.khealth.smhc.smhcweb.sv.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import kr.or.khealth.smhc.smhcweb.sv.service.PhisMonitoringService;

/**
 * @Class Name : PhisMonitoringServiceImpl.java
 * @Description : 관리자 WEB에서 사용하는 phis 연계 모니터링 serviceImpl Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2021.06.14		윤찬호			최초생성
 *
 * @author chyoon
 * @since 2021.06.14
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Service(value="web.sv.PhisMonitoringService")
public class PhisMonitoringServiceImpl extends EgovAbstractServiceImpl implements PhisMonitoringService{

	@Resource(name="web.sv.PhisMonitoringDAO")
	private PhisMonitoringDAO phisMonitoringDAO;	
	
	@Override
	public List<Map<String, String>> selectPhisInterfaceList(Map<String, Object> param) {		
		return phisMonitoringDAO.selectPhisInterfaceList(param);
	}

	@Override
	public List<Map<String, String>> selectPhisActList(Map<String, Object> param) {		
		return phisMonitoringDAO.selectPhisActList(param);
	}

	@Override
	public List<Map<String, String>> selectPhisBodyList(Map<String, Object> param) {		
		return phisMonitoringDAO.selectPhisBodyList(param);
	}

	@Override
	public List<Map<String, String>> selectPhisBloodpressList(Map<String, Object> param) {		
		return phisMonitoringDAO.selectPhisBloodpressList(param);
	}

	@Override
	public List<Map<String, String>> selectPhisBloodsugarList(Map<String, Object> param) {	
		return phisMonitoringDAO.selectPhisBloodsugarList(param);
	}

}
