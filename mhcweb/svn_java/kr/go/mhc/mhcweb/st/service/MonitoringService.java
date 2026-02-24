package kr.go.mhc.mhcweb.st.service;

import java.util.List;
import java.util.Map;



public interface MonitoringService {

	public List<Map<String, String>> deviceUtilizationSttusList(Map<String, Object> param) throws Exception;
	
	public List<Map<String, String>> deviceUtilizationDtlsSttusList(Map<String, Object> param) throws Exception;
	
	/**********************************************************************************************************/
	
	public List<Map<String, String>> healthRiskChangeSttusList(Map<String, Object> param) throws Exception;
	
	public List<Map<String, String>> healthRiskChangeDtlsSttusList(Map<String, Object> param) throws Exception;
	
	/**********************************************************************************************************/
	
	public List<Map<String, String>> healthServeySttusList(Map<String, Object> param) throws Exception;
	
	public List<Map<String, String>> healthServeyDtlsSttusList(Map<String, Object> param) throws Exception;
	
	/**********************************************************************************************************/
	
	public List<Map<String, String>> weekContentList(Map<String, Object> param) throws Exception;
	
	/**********************************************************************************************************/
	
	public List<Map<String, String>> healthcenterContentList(Map<String, Object> param) throws Exception;

}
