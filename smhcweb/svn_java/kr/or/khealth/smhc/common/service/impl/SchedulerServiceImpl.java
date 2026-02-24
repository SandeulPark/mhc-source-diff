package kr.or.khealth.smhc.common.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.or.khealth.smhc.common.DMultiEgovAbstractMapper;
import kr.or.khealth.smhc.common.service.SchedulerService;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("common.schedulerService")
public class SchedulerServiceImpl extends EgovAbstractServiceImpl implements SchedulerService{
	
	@Resource(name="SchedulerDAO")
    private SchedulerDAO chartDAO;
	
	@Override
	public String CALL_PRC_AUTO_PUSH_INS(Map<String, Object> param) throws Exception {
		return chartDAO.CALL_PRC_AUTO_PUSH_INS(param);
	}
	
	@Override
	public String CALL_PRC_TN_SV_MONTH_TOT_POINT(Map<String, Object> param) throws Exception {
		return chartDAO.CALL_PRC_TN_SV_MONTH_TOT_POINT(param);
	}

	@Override
	public void CALL_PRC_ACT_MISSION_SUCC_INS(Map<String, Object> rsMap) throws Exception {
		chartDAO.CALL_PRC_ACT_MISSION_SUCC_INS();		
	}

	@Override
	public List<Map<String, Object>> getMinMaxWeekDate(Map<String, Object> rsMap) throws Exception {
		// TODO Auto-generated method stub
		return chartDAO.getMinMaxWeekDate(rsMap);
	}

	@Override
	public void CALL_PRC_TN_SV_MONTH_REPORT(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		chartDAO.CALL_PRC_TN_SV_MONTH_REPORT(param);
	}
	
	
	@Override
	public String CALL_PRC_TN_CM_CONNECT_LOG_CRE() throws Exception {
		return chartDAO.CALL_PRC_TN_CM_CONNECT_LOG_CRE();
	}

	/*@Override
	public void initCertificationCnt() throws Exception {
		chartDAO.initCertificationCnt();
	}*/
	
	@Override
	public void resetExpiredTokens() throws Exception {
		chartDAO.resetExpiredTokens();
	}
}
