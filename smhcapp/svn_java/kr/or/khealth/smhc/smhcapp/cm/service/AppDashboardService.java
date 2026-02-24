package kr.or.khealth.smhc.smhcapp.cm.service;

import java.util.List;
import java.util.Map;

public interface AppDashboardService {
	
	public Map<String, Object> selectUserWalkInfo(Map<String, Object> param) throws Exception;
	
	public List<Map<String, String>> selectNotificationList(Map<String, Object> param) throws Exception;
	
	public void updateNotification(Map<String, Object> param) throws Exception;

	public int notificationDel(Map<String, Object> param)  throws Exception;

}
