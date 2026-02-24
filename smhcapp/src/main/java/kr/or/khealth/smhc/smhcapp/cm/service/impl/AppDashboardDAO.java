package kr.or.khealth.smhc.smhcapp.cm.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.or.khealth.smhc.common.DMultiEgovAbstractMapper;

@Repository("smhcapp.cm.AppDashboardDAO")
public class AppDashboardDAO extends DMultiEgovAbstractMapper{

	public Map<String, Object> selectUserWalkInfo(Map<String, Object> param) throws Exception{
		Map<String, Object> rsMap = selectOne("smhcapp.cm.appdashboard.selectUserWalkInfo", param);
		return rsMap;
	}
	
	public List<Map<String, String>> selectNotificationList(Map<String, Object> param) throws Exception {
		List<Map<String, String>> rsList = selectList("smhcapp.cm.appdashboard.selectNotificationList", param);
		return rsList;  
	}
	
	public void updateNotification(Map<String, Object> param) throws Exception {
		update("smhcapp.cm.appdashboard.updateNotification", param);
	}

	public int notificationDel(Map<String, Object> param) throws Exception  {
		// TODO Auto-generated method stub
		int rsList = update("smhcapp.cm.appdashboard.notificationDel", param);	
		return rsList;  
	}
}
