package kr.or.khealth.smhc.smhcapp.cm.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import kr.or.khealth.smhc.smhcapp.cm.service.AppDashboardService;

@Service("smhcapp.cm.AppDashboardService")
public class AppDashboardServiceImpl extends EgovAbstractServiceImpl implements AppDashboardService {
	
	@Resource(name = "smhcapp.cm.AppDashboardDAO")
	private AppDashboardDAO appDashboardDAO;

	@Override
	public Map<String, Object> selectUserWalkInfo(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return appDashboardDAO.selectUserWalkInfo(param);
	}
	
	@Override
	public List<Map<String, String>> selectNotificationList(Map<String, Object> param) throws Exception {
		return appDashboardDAO.selectNotificationList(param);
	}
	
	@Override
	public void updateNotification(Map<String, Object> param) throws Exception {
		appDashboardDAO.updateNotification(param);
	}

	@Override
	public int notificationDel(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return appDashboardDAO.notificationDel(param);
	}
	
}
