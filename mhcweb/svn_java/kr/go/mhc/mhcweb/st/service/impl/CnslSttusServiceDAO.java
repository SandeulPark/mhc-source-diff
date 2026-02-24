package kr.go.mhc.mhcweb.st.service.impl;

import java.util.List;
import java.util.Map;

import kr.go.mhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;

@Repository("web.st.CnslSttusServiceDAO")
public class CnslSttusServiceDAO extends DMultiEgovAbstractMapper{
	
	public List<Map<String, String>> bodyActIntensiveCnslSttusList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String, String>> rsList = selectList("mhc.web.st.cnslsttus.bodyActIntensiveCnslSttusList",param);
		return rsList;
	}	
	
	public List<Map<String, String>> bodyActIntensiveCnslDtlsSttusList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String, String>> rsList = selectList("mhc.web.st.cnslsttus.bodyActIntensiveCnslDtlsSttusList",param);
		return rsList;
	}	
	
	/***********************************************************************************************************/
	
	public List<Map<String, String>> nutriIntensiveCnslSttusList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String, String>> rsList = selectList("mhc.web.st.cnslsttus.nutriIntensiveCnslSttusList",param);
		return rsList;
	}	
	
	public List<Map<String, String>> nutriIntensiveCnslDtlsSttusList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String, String>> rsList = selectList("mhc.web.st.cnslsttus.nutriIntensiveCnslDtlsSttusList",param);
		return rsList;
	}	
	
	/***********************************************************************************************************/
	
	public List<Map<String, String>> nosmokTmprrncIntensiveSttusList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String, String>> rsList = selectList("mhc.web.st.cnslsttus.nosmokTmprrncIntensiveSttusList",param);
		return rsList;
	}	
	
	/***********************************************************************************************************/
	
	public List<Map<String, String>> comnCnslSttusList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String, String>> rsList = selectList("mhc.web.st.cnslsttus.comnCnslSttusList",param);
		return rsList;
	}	
	
	public List<Map<String, String>> comnCnslDtlsSttusList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String, String>> rsList = selectList("mhc.web.st.cnslsttus.comnCnslDtlsSttusList",param);
		return rsList;
	}	
	
	/***********************************************************************************************************/
	
	public List<Map<String, String>> mthlyHealthRptSttusList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String, String>> rsList = selectList("mhc.web.st.cnslsttus.mthlyHealthRptSttusList",param);
		return rsList;
	}	
	
	public List<Map<String, String>> mthlyHealthRptDtlsSttusList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String, String>> rsList = selectList("mhc.web.st.cnslsttus.mthlyHealthRptDtlsSttusList",param);
		return rsList;
	}	
	
}
