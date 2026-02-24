package kr.go.mhc.mhcweb.sm.service.impl;

import kr.go.mhc.common.DMultiEgovAbstractMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Class Name : OrgMngtDAO.java
 * @Description : 관리자 WEB에서 사용하는 기관 정보 DataBase 연동 관리하는 Class
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Repository("web.sm.OrgMngtMonitoringDAO")
public class OrgMngtMonitoringDAO extends DMultiEgovAbstractMapper {

	/**
	 * 기관관리 정보 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> getOrgMngtMonitoringList(Map<String, Object> param) throws Exception {
		List<Map<String,String>> rsList = selectList("mhc.web.sm.orgmngtmonitoring.selectOrgMngtMonitoringList", param);
		return rsList;
	}


	public void deleteOrgMngtDormant(Map<String, Object> param) throws Exception {
		update("mhc.web.sm.orgmngtmonitoring.deleteOrgMngtDormant", param);
	}

	public List<Map<String, String>> getOrgMngtUnconnectList(Map<String, Object> param) {
		List<Map<String,String>> rsList = selectList("mhc.web.sm.orgmngtmonitoring.selectOrgMngtUnconnectList", param);
		return rsList;
	}

	public void releaseOrgMngtDormant(Map<String, Object> param) throws Exception {
		update("mhc.web.sm.orgmngtmonitoring.releaseOrgMngtDormant", param);
	}
}
