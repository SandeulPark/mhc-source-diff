package kr.go.mhc.mhcweb.st.service;

import java.util.List;
import java.util.Map;



public interface TrgterSttusService {

	public List<Map<String, String>> trgterRegSttusList(Map<String, Object> param) throws Exception;
	
	public List<Map<String, String>> trgterRegDtlsSttusList(Map<String, Object> param) throws Exception;
	
	/**********************************************************************************************************/
	
	public List<Map<String, String>> svcJoinSttusList(Map<String, Object> param) throws Exception;
	
	public List<Map<String, String>> svcJoinDtlsSttusList(Map<String, Object> param) throws Exception;

	/**********************************************************************************************************/
	
	public List<Map<String, String>> healthExamRsltSttusList(Map<String, Object> param) throws Exception;
	
	public List<Map<String, String>> healthExamRsltDtlsSttusList(Map<String, Object> param) throws Exception;
	
	/**********************************************************************************************************/
	
	public List<Map<String, String>> deviceDistrbtSttusList(Map<String, Object> param) throws Exception;
	
	public List<Map<String, String>> deviceDistrbtDtlsSttusList(Map<String, Object> param) throws Exception;
	
	/**********************************************************************************************************/

	public List<Map<String, String>> svcBgnRegSttusTotalCnt(Map<String, Object> param) throws Exception;

	public List<Map<String, String>> svcBgnDeRegSttusList(Map<String, Object> param) throws Exception;
	
	/**********************************************************************************************************/
	
	public List<Map<String, String>> trgterCnctSttusList(Map<String, Object> param) throws Exception;
}
