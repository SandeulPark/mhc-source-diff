package kr.go.mhc.mhcweb.st.service;

import java.util.List;
import java.util.Map;



public interface CnslSttusService {

	public List<Map<String, String>> bodyActIntensiveCnslSttusList(Map<String, Object> param) throws Exception;
	
	public List<Map<String, String>> bodyActIntensiveCnslDtlsSttusList(Map<String, Object> param) throws Exception;
	
	/**********************************************************************************************************/
	
	public List<Map<String, String>> nutriIntensiveCnslSttusList(Map<String, Object> param) throws Exception;
	
	public List<Map<String, String>> nutriIntensiveCnslDtlsSttusList(Map<String, Object> param) throws Exception;
	
	/**********************************************************************************************************/
	
	public List<Map<String, String>> nosmokTmprrncIntensiveSttusList(Map<String, Object> param) throws Exception;
	
	/**********************************************************************************************************/
	
	public List<Map<String, String>> comnCnslSttusList(Map<String, Object> param) throws Exception;
	
	public List<Map<String, String>> comnCnslDtlsSttusList(Map<String, Object> param) throws Exception;
	
	/**********************************************************************************************************/
	
	public List<Map<String, String>> mthlyHealthRptSttusList(Map<String, Object> param) throws Exception;
	
	public List<Map<String, String>> mthlyHealthRptDtlsSttusList(Map<String, Object> param) throws Exception;
	
}
