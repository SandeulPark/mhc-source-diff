package kr.go.mhc.mhcweb.sm.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import kr.go.mhc.mhcweb.sm.service.OrgMngtMonitoringService;
import kr.go.mhc.mhcweb.sm.service.OrgMngtService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Class Name :OrgMngtServiceImpl.java
 * @Description : 관리자 WEB에서 사용하는 기관정보 화면에 필요한 DAO와 연동 관리하는 Class
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Service("web.sm.OrgMngtMonitoringService")
public class OrgMngtMonitoringServiceImpl extends EgovAbstractServiceImpl implements OrgMngtMonitoringService {

	@Resource(name="web.sm.OrgMngtMonitoringDAO")
	private OrgMngtMonitoringDAO orgMngtMonitoringDAO;
	
	@Override
	public List<Map<String, String>> getOrgMngtMonitoringList(Map<String, Object> param) throws Exception {
		return orgMngtMonitoringDAO.getOrgMngtMonitoringList(param);
	}

	@Override
	public void deleteOrgMngtDormant(Map<String, Object> param) throws Exception {
		orgMngtMonitoringDAO.deleteOrgMngtDormant(param);
	}

	@Override
	public List<Map<String, String>> getOrgMngtUnconnectList(Map<String, Object> param) throws Exception {
		return orgMngtMonitoringDAO.getOrgMngtUnconnectList(param);
	}

	@Override
	public void releaseOrgMngtDormant(Map<String, Object> param) throws Exception {
		orgMngtMonitoringDAO.releaseOrgMngtDormant(param);
	}

}
