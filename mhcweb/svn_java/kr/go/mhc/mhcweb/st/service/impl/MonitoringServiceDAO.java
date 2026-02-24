package kr.go.mhc.mhcweb.st.service.impl;

import java.util.List;
import java.util.Map;

import kr.go.mhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;

@Repository("web.st.MonitoringServiceDAO")
public class MonitoringServiceDAO extends DMultiEgovAbstractMapper{
	
	public List<Map<String, String>> deviceUtilizationSttusList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String, String>> rsList = selectList("mhc.web.st.monitoring.deviceUtilizationSttusList",param);
		return rsList;
	}	
	
	public List<Map<String, String>> deviceUtilizationDtlsSttusList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String, String>> rsList = selectList("mhc.web.st.monitoring.deviceUtilizationDtlsSttusList",param);
		return rsList;
	}	
	
	/***********************************************************************************************************/
	
	public List<Map<String, String>> healthRiskChangeSttusList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String, String>> rsList = selectList("mhc.web.st.monitoring.healthRiskChangeSttusList",param);
		return rsList;
	}	
	
	public List<Map<String, String>> healthRiskChangeDtlsSttusList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String, String>> rsList = selectList("mhc.web.st.monitoring.healthRiskChangeDtlsSttusList",param);
		return rsList;
	}	
	
	/***********************************************************************************************************/
	
	public List<Map<String, String>> healthServeySttusList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String, String>> rsList = selectList("mhc.web.st.monitoring.healthServeySttusList",param);
		return rsList;
	}	
	
	public List<Map<String, String>> healthServeyDtlsSttusList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String, String>> rsList = selectList("mhc.web.st.monitoring.healthServeyDtlsSttusList",param);
		return rsList;
	}	
	
	/***********************************************************************************************************/
	
	public List<Map<String, String>> weekContentList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String, String>> rsList = selectList("mhc.web.st.monitoring.weekContentList",param);
		return rsList;
	}	
	
	/***********************************************************************************************************/
	
	public List<Map<String, String>> healthcenterContentList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String, String>> rsList = selectList("mhc.web.st.monitoring.healthcenterContentList",param);
		return rsList;
	}	
	
}
