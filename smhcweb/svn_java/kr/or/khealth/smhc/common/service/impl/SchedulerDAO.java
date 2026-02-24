package kr.or.khealth.smhc.common.service.impl;

import java.util.List;
import java.util.Map;

import kr.or.khealth.smhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;

@Repository("SchedulerDAO")
public class SchedulerDAO extends DMultiEgovAbstractMapper{
	
	public String CALL_PRC_AUTO_PUSH_INS(Map<String, Object> param) throws Exception {
		return selectOne("smhc.common.scheduler.CALL_PRC_AUTO_PUSH_INS", param);	
	}
	
	public String CALL_PRC_TN_SV_MONTH_TOT_POINT(Map<String, Object> param) throws Exception {
		return selectOne("smhc.common.scheduler.PRC_TN_SV_MONTH_TOT_POINT", param);	
	}

	public void CALL_PRC_ACT_MISSION_SUCC_INS() {
		selectOne("smhc.common.scheduler.CALL_PRC_ACT_MISSION_SUCC_INS");			
	}

	public List<Map<String, Object>> getMinMaxWeekDate(Map<String, Object> rsMap) {
		// TODO Auto-generated method stub
		return selectList("smhc.common.scheduler.getMinMaxWeekDate",rsMap);	
	}

	public void CALL_PRC_TN_SV_MONTH_REPORT(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		selectOne("smhc.common.scheduler.CALL_PRC_TN_SV_MONTH_REPORT", param);
	}
	
	public String CALL_PRC_TN_CM_CONNECT_LOG_CRE() throws Exception {
		return selectOne("smhc.common.scheduler.CALL_PRC_TN_CM_CONNECT_LOG_CRE");	
	}
	
	/*public void initCertificationCnt() {
		update("smhc.common.scheduler.initCertificationCnt");
	}*/
	
	public void resetExpiredTokens() {
		update("smhc.common.scheduler.resetExpiredTokens");
	}
}
