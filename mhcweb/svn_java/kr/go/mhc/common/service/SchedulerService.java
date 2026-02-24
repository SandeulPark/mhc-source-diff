package kr.go.mhc.common.service;

import java.util.Map;

public interface SchedulerService {
	
	/**
	 * 프로시져 호출
	 * @param 
	 * @return  
	 * @throws Exception 
	 */
	public String CALL_PRC_TN_SV_CNSL_INS_NEW(Map<String, Object> param) throws Exception;

	/**
	 * 프로시져 호출
	 * @param 
	 * @return  
	 * @throws Exception 
	 */
	public String CALL_PRC_TN_SV_MISSION_ACHV_INS(Map<String, Object> param) throws Exception;

	/**
	 * 프로시져 호출
	 * @param 
	 * @return  
	 * @throws Exception 
	 */
	public String CALL_PRC_TN_MS_ACT_UPD(Map<String, Object> param) throws Exception;

	/**
	 * 프로시져 호출
	 * @param 
	 * @return  
	 * @throws Exception 
	 */
	public String CALL_PRC_AUTO_PUSH_INS(Map<String, Object> param) throws Exception;

	/**
	 * 프로시져 호출
	 * @param 
	 * @return  
	 * @throws Exception 
	 */
	public String CALL_PRC_DAILY_JOB_UPD(Map<String, Object> param) throws Exception;
	
	/**
	 * 프로시져 호출
	 * @param 
	 * @return  
	 * @throws Exception 
	 */
	public String CALL_PRC_TN_MS_HEART_RATE_INS(Map<String, Object> param) throws Exception;
	
	/**
	 * 프로시져 호출
	 * @param 
	 * @return  
	 * @throws Exception 
	 */
	public String CALL_PRC_AUTO_RETRY_PUSH_INS(Map<String, Object> param) throws Exception;
	
	
	/**
	 * 프로시져 호출
	 * @param 
	 * @return  
	 * @throws Exception 
	 */
	public String CALL_PRC_TN_MS_RUNNING_CALORIE_UPD(Map<String, Object> param) throws Exception;
	
	
	/**
	 * 프로시져 호출
	 * @param 
	 * @return  
	 * @throws Exception 
	 */
	public String CALL_PRC_TM_PM_STATS_ALL_INS(Map<String, Object> param) throws Exception;
		
	/**
	 * 프로시져 호출
	 * @param 
	 * @return  
	 * @throws Exception 
	 */
	public String CALL_PRC_AUTO_DROP_UPD(Map<String, Object> param) throws Exception;
	
	/**
	 * 프로시져 호출
	 * @param 
	 * @return  
	 * @throws Exception 
	 */
	public String CALL_PRC_EXCS_SCH_PUSH(Map<String, Object> param) throws Exception;
	
	
	/**
	 * 프로시져 호출
	 * @param 
	 * @return  
	 * @throws Exception 
	 */
	public String CALL_PRC_AUTO_LST_CONNECT_UPD(Map<String, Object> rsMap) throws Exception;
	
	/**
	 * 프로시져 호출
	 * @param 
	 * @return  
	 * @throws Exception 
	 */
	public String CALL_PRC_AUTO_MNGR_MNGT(Map<String, Object> rsMap) throws Exception;
	
}
