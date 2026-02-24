package kr.go.mhc.mhcweb.st.service.impl;

import java.util.List;
import java.util.Map;

import kr.go.mhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;

@Repository("web.st.TrgterSttusServiceDAO")
public class TrgterSttusServiceDAO extends DMultiEgovAbstractMapper{
	
	public List<Map<String, String>> trgterRegSttusList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String, String>> rsList = selectList("mhc.web.st.trgtersttus.trgterRegSttusList",param);
		return rsList;
	}	
	
	public List<Map<String, String>> trgterRegDtlsSttusList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String, String>> rsList = selectList("mhc.web.st.trgtersttus.trgterRegDtlsSttusList",param);
		return rsList;
	}	
	
	/***********************************************************************************************************/
	
	public List<Map<String, String>> svcJoinSttusList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String, String>> rsList = selectList("mhc.web.st.trgtersttus.svcJoinSttusList",param);
		return rsList;
	}	
	
	public List<Map<String, String>> svcJoinDtlsSttusList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String, String>> rsList = selectList("mhc.web.st.trgtersttus.svcJoinDtlsSttusList",param);
		return rsList;
	}	
	
	/***********************************************************************************************************/
	
	public List<Map<String, String>> healthExamRsltSttusList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String, String>> rsList = selectList("mhc.web.st.trgtersttus.healthExamRsltSttusList",param);
		return rsList;
	}	
	
	public List<Map<String, String>> healthExamRsltDtlsSttusList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String, String>> rsList = selectList("mhc.web.st.trgtersttus.healthExamRsltDtlsSttusList",param);
		return rsList;
	}	
	
	/***********************************************************************************************************/
	
	public List<Map<String, String>> deviceDistrbtSttusList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String, String>> rsList = selectList("mhc.web.st.trgtersttus.deviceDistrbtSttusList",param);
		return rsList;
	}	
	
	public List<Map<String, String>> deviceDistrbtDtlsSttusList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String, String>> rsList = selectList("mhc.web.st.trgtersttus.deviceDistrbtDtlsSttusList",param);
		return rsList;
	}	
	
	/***********************************************************************************************************/

	public List<Map<String, String>> svcBgnRegSttusTotalCnt(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String, String>> rsList = selectList("mhc.web.st.trgtersttus.svcBgnRegSttusTotalCnt",param);
		return rsList;
	}

	public List<Map<String, String>> svcBgnDeRegSttusList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String, String>> rsList = selectList("mhc.web.st.trgtersttus.svcBgnDeRegSttusList",param);
		return rsList;
	}	
	
	/***********************************************************************************************************/
	
	public List<Map<String, String>> trgterCnctSttusList(Map<String, Object> param) throws Exception{
		// TODO Auto-generated method stub
		List<Map<String, String>> rsList = selectList("mhc.web.st.trgtersttus.trgterCnctSttusList", param);
		return rsList;
	}
}
