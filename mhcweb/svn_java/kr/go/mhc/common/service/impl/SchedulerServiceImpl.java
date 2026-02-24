package kr.go.mhc.common.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import kr.go.mhc.common.service.SchedulerService;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("common.schedulerService")
public class SchedulerServiceImpl extends EgovAbstractServiceImpl implements SchedulerService{
	
	@Resource(name="SchedulerDAO")
    private SchedulerDAO chartDAO;
	
	@Override
	public String CALL_PRC_TN_SV_CNSL_INS_NEW(Map<String, Object> param)
			throws Exception {
		
		return chartDAO.CALL_PRC_TN_SV_CNSL_INS_NEW(param);
	}
	
	@Override
	public String CALL_PRC_TN_SV_MISSION_ACHV_INS(Map<String, Object> param)
			throws Exception {
		
		return chartDAO.CALL_PRC_TN_SV_MISSION_ACHV_INS(param);
	}
	
	@Override
	public String CALL_PRC_TN_MS_ACT_UPD(Map<String, Object> param)
			throws Exception {
		
		return chartDAO.CALL_PRC_TN_MS_ACT_UPD(param);
	}
	
	@Override
	public String CALL_PRC_AUTO_PUSH_INS(Map<String, Object> param)
			throws Exception {
		
		return chartDAO.CALL_PRC_AUTO_PUSH_INS(param);
	}
	
	@Override
	public String CALL_PRC_DAILY_JOB_UPD(Map<String, Object> param)
			throws Exception {
		
		return chartDAO.CALL_PRC_DAILY_JOB_UPD(param);
	}
	
	@Override
	public String CALL_PRC_TN_MS_HEART_RATE_INS(Map<String, Object> param)
			throws Exception {
		
		return chartDAO.CALL_PRC_TN_MS_HEART_RATE_INS(param);
	}
	
	public String CALL_PRC_AUTO_RETRY_PUSH_INS(Map<String, Object> param)
			throws Exception {
		return chartDAO.CALL_PRC_AUTO_RETRY_PUSH_INS(param);
	}
	
	public String CALL_PRC_TN_MS_RUNNING_CALORIE_UPD(Map<String, Object> param)
			throws Exception {
		return chartDAO.CALL_PRC_TN_MS_RUNNING_CALORIE_UPD(param);
	}
	
	
	public String CALL_PRC_TM_PM_STATS_ALL_INS(Map<String, Object> param)
			throws Exception {
		return chartDAO.CALL_PRC_TM_PM_STATS_ALL_INS(param);
	}	
	
	public String CALL_PRC_AUTO_DROP_UPD(Map<String, Object> param)
			throws Exception {
		return chartDAO.CALL_PRC_AUTO_DROP_UPD(param);
	}	
	
	public String CALL_PRC_EXCS_SCH_PUSH(Map<String, Object> param)
			throws Exception {
		return chartDAO.CALL_PRC_EXCS_SCH_PUSH(param);
	}	
	
	@Override
	public String CALL_PRC_AUTO_LST_CONNECT_UPD(Map<String, Object> param) throws Exception {
		return chartDAO.CALL_PRC_AUTO_LST_CONNECT_UPD(param);		
	}

	@Override
	public String CALL_PRC_AUTO_MNGR_MNGT(Map<String, Object> rsMap) throws Exception {
		// TODO Auto-generated method stub
		return chartDAO.CALL_PRC_AUTO_MNGR_MNGT(rsMap);		
	}
}
