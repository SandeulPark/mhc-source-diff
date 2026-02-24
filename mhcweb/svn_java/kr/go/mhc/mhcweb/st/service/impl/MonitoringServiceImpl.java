package kr.go.mhc.mhcweb.st.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.go.mhc.mhcweb.st.service.MonitoringService;

import org.springframework.stereotype.Service;

@Service("web.st.MonitoringService")
public class MonitoringServiceImpl implements MonitoringService{
	
	@Resource(name="web.st.MonitoringServiceDAO")
	private MonitoringServiceDAO monitoringServiceDAO;

	@Override
	public List<Map<String, String>> deviceUtilizationSttusList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return monitoringServiceDAO.deviceUtilizationSttusList(param);
	}

	@Override
	public List<Map<String, String>> deviceUtilizationDtlsSttusList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return monitoringServiceDAO.deviceUtilizationDtlsSttusList(param);
	}
	
	/*******************************************************************************************************/
	
	@Override
	public List<Map<String, String>> healthRiskChangeSttusList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return monitoringServiceDAO.healthRiskChangeSttusList(param);
	}
	
	@Override
	public List<Map<String, String>> healthRiskChangeDtlsSttusList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return monitoringServiceDAO.healthRiskChangeDtlsSttusList(param);
	}
	
	/*******************************************************************************************************/
	
	@Override
	public List<Map<String, String>> healthServeySttusList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return monitoringServiceDAO.healthServeySttusList(param);
	}
	
	@Override
	public List<Map<String, String>> healthServeyDtlsSttusList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return monitoringServiceDAO.healthServeyDtlsSttusList(param);
	}
	
	/*******************************************************************************************************/
	
	@Override
	public List<Map<String, String>> weekContentList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return monitoringServiceDAO.weekContentList(param);
	}
	
	/*******************************************************************************************************/
	
	@Override
	public List<Map<String, String>> healthcenterContentList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return monitoringServiceDAO.healthcenterContentList(param);
	}
	
}
