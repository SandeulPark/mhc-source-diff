package kr.or.khealth.smhc.smhcapp.cm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.khealth.smhc.common.DMultiActionController;
import kr.or.khealth.smhc.smhcapp.cm.service.AppDashboardService;

@Controller
@RequestMapping(value = "/smhcapp/cm")
public class AppDasboardController extends DMultiActionController {
	@Resource(name = "smhcapp.cm.AppDashboardService")
	private AppDashboardService appDashboardService;
	
	@ModelAttribute
	public Map initData(HttpServletRequest req) throws Exception{
		return super.initData(req);
	}
	
	@RequestMapping(value = "/selectUserWalkInfo.do")
	public @ResponseBody Map<String, Object> selectUserWalkInfo(@ModelAttribute Map<String, Object> param) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		try {
			Map<String, Object> userWalkInf = appDashboardService.selectUserWalkInfo(param);
			rsMap.put("USER_WALK_INF", userWalkInf);
			rsMap.put("chkYn", "Y");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			rsMap.put("chkYn", "N");
			System.out.println(e.toString());
		}
		return rsMap;
	}
	
	@RequestMapping(value = "/setTNYWalkCount.do")
	public @ResponseBody Map<String, Object> setTNYWalkCount(@ModelAttribute Map<String, Object> param) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		try {
			Map<String, Object> todayWalkInf =  new HashMap<String, Object>();
			Map<String, Object> yesterdayWalkInf =  new HashMap<String, Object>();
			
			if(param.containsKey("SELECT_TDAY")){
				param.put("SELECT_DT", param.get("SELECT_TDAY"));
				todayWalkInf =  appDashboardService.selectUserWalkInfo(param);
				todayWalkInf.put("SELECT_DT", param.get("SELECT_TDAY"));
			}
			if(param.containsKey("SELECT_YDAY")){
				param.put("SELECT_DT", param.get("SELECT_YDAY"));
				yesterdayWalkInf = appDashboardService.selectUserWalkInfo(param);
				yesterdayWalkInf.put("SELECT_DT", param.get("SELECT_YDAY"));
			}
			
			rsMap.put("TDAY_WALK_INF", todayWalkInf);
			rsMap.put("YDAY_WALK_INF", yesterdayWalkInf);
			rsMap.put("chkYn", "Y");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			rsMap.put("chkYn", "N");
			System.out.println(e.toString());
		}
		
		return rsMap;
	}
	
	@RequestMapping( value="/selectNotificationList.do", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, String>> selectNotificationList(HttpServletRequest req, @ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		
		List<Map<String, String>> rsList = appDashboardService.selectNotificationList(param); 
		return rsList;
	}
	
	@RequestMapping( value="/updateNotification.do", method = RequestMethod.POST)
	public void updateNotification(HttpServletRequest req, @ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		appDashboardService.updateNotification(param); 
	}
	
	/**
	 * 알림내역 삭제
	 */
	@RequestMapping( value="/notificationDel.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> notificationDel(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		int rsCount = appDashboardService.notificationDel(param);
		rsMap.put("rsList", rsCount);
		return rsMap;
	}
}
