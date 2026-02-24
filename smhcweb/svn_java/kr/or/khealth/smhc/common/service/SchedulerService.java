package kr.or.khealth.smhc.common.service;

import java.util.List;
import java.util.Map;

public interface SchedulerService {

	/**
	 * 프로시져 호출
	 * @param 
	 * @return  
	 * @throws Exception 
	 */
	public String CALL_PRC_AUTO_PUSH_INS(Map<String, Object> param) throws Exception;
	
	public String CALL_PRC_TN_SV_MONTH_TOT_POINT(Map<String, Object> param) throws Exception;

	public void CALL_PRC_ACT_MISSION_SUCC_INS(Map<String, Object> rsMap) throws Exception;

	public List<Map<String, Object>> getMinMaxWeekDate(Map<String, Object> rsMap) throws Exception;
	
	public String CALL_PRC_TN_CM_CONNECT_LOG_CRE() throws Exception;

	public void CALL_PRC_TN_SV_MONTH_REPORT(Map<String, Object> param)  throws Exception;
	
	//public void initCertificationCnt() throws Exception;
	
	public void resetExpiredTokens() throws Exception;
}
