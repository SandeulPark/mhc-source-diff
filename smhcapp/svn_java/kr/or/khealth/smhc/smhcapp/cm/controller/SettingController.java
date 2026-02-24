package kr.or.khealth.smhc.smhcapp.cm.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
import kr.or.khealth.smhc.smhcapp.cm.service.SettingService;

@Controller
@RequestMapping(value="/smhcapp/cm")
public class SettingController extends DMultiActionController{ 

	@Resource(name="smhcapp.cm.SettingService")
	private SettingService settingService;
	
	@ModelAttribute
	public Map<String,Object> initData(HttpServletRequest req) throws Exception{
		return super.initData(req);
	}
	
	@RequestMapping( value="/selectUserDrugInfo.do", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, String>> selectUserDrugInfo(HttpServletRequest req, @ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		
		List<Map<String, String>> rsList = settingService.selectUserDrugInfo(param); 
		return rsList;
	}
	
	@RequestMapping( value="/selectUserObj.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,String> selectUserObj(HttpServletRequest req, @ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		
		 Map<String,String> rtnMap = settingService.selectUserObj(param); 
		
		return rtnMap;
	}
	
	@RequestMapping( value="/insertUserObj.do", method = RequestMethod.POST)
	public void insertUserObj(HttpServletRequest req, @ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		
		settingService.insertUserObj(param); 
	}
	
	@RequestMapping( value="/userImgUpload.do", method = RequestMethod.POST)
	public void userImgUpload(@ModelAttribute Map<String, Object> param) throws Exception{
		
		settingService.userImgUpload(param);
	}
	
	@RequestMapping(value = "/getUserImg.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getUserImg(@ModelAttribute Map<String, Object> param) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		try {
			rsMap.put("user_inf", settingService.getUserImg(param));
			rsMap.put("chkYn", "Y");
		} catch (Exception e) {
			// TODO: handle exception
			rsMap.put("chkYn", "N");
		}
		return rsMap;
	}
	
	@RequestMapping( value="/getLastConnectDt.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getLastConnectDt(HttpServletRequest req, @ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		return settingService.getLastConnectDt(param);
	}
	
	@RequestMapping( value="/selectWriteMeasrUseYn.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> selectWriteMeasrUseYn(HttpServletRequest req, @ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		
		Map<String, Object> rsMap = settingService.selectWriteMeasrUseYn(param); 
		
		return rsMap;
	}
	
	@RequestMapping( value="/updateWriteMeasrUseYn.do", method = RequestMethod.POST)
	public @ResponseBody void updateWriteMeasrUseYn(HttpServletRequest req, @ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		settingService.updateWriteMeasrUseYn(param);		
	}
	
	@RequestMapping( value="/updateWriteBloodSugarUseYn.do", method = RequestMethod.POST)
	public @ResponseBody void updateWriteBloodSugarUseYn(HttpServletRequest req, @ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		settingService.updateWriteBloodSugarUseYn(param);
	}
	
	@RequestMapping( value="/updateWriteBloodPressUseYn.do", method = RequestMethod.POST)
	public @ResponseBody void updateWriteBloodPressUseYn(HttpServletRequest req, @ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		settingService.updateWriteBloodPressUseYn(param);
	}
	
	@RequestMapping( value="/updateWriteBodycompUseYn.do", method = RequestMethod.POST)
	public @ResponseBody void updateWriteBodycompUseYn(HttpServletRequest req, @ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		settingService.updateWriteBodycompUseYn(param);
	}
	
	@RequestMapping( value="/selectPushUseYn.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> selectPushUseYn(HttpServletRequest req, @ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		
		Map<String, Object> rsMap = settingService.selectPushUseYn(param); 
		
		return rsMap;
	}
	
	@RequestMapping( value="/updatePushUseYn.do", method = RequestMethod.POST)
	public @ResponseBody void updatePushUseYn(HttpServletRequest req, @ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		
		settingService.updatePushUseYn(param);		
	}
	
	@RequestMapping( value="/selectVerticalModeYn.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> selectVerticalModeYn(HttpServletRequest req, @ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = settingService.selectVerticalModeYn(param); 
		return rsMap;
	}
	
	@RequestMapping( value="/updateVerticalUseYn.do", method = RequestMethod.POST)
	public @ResponseBody void updateVerticalUseYn(HttpServletRequest req, @ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		
		settingService.updateVerticalUseYn(param);		
	}
	
	@RequestMapping( value="/updateMealSeqUseYn.do", method = RequestMethod.POST)
	public @ResponseBody void updateMealSeqUseYn(HttpServletRequest req, @ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		
		settingService.updateMealSeqUseYn(param);		
	}
	
	@RequestMapping( value="/updateSelfMeasrUseYn.do", method = RequestMethod.POST)
	public @ResponseBody void updateSelfMeasrUseYn(HttpServletRequest req, @ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		
		settingService.updateSelfMeasrUseYn(param);		
	}
	
	@RequestMapping( value="/selectUsersOption.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> selectUsersOption(HttpServletRequest req, @ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = settingService.selectUsersOption(param);
		return rsMap;
	}
	
	@RequestMapping( value="/selectUserBloodMission.do", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> selectUserBloodMission(HttpServletRequest req, @ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		List<Map<String, Object>> rsList = settingService.selectUserBloodMission(param); 
		return rsList;
	}
	
	@RequestMapping( value="/selectUserBloodPushInfo.do", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> selectBloodPushInfo(HttpServletRequest req, @ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		List<Map<String, Object>> rsList = settingService.selectUserBloodPushInfo(param); 
		return rsList;
	}
	
	@RequestMapping( value="/delUserBloodPushInfo.do", method = RequestMethod.POST)
	public void delBloodPushInfo(HttpServletRequest req, @ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		settingService.delUserBloodPushInfo(param); 
	}
	
	@RequestMapping( value="/insertUserBloodPushInfo.do", method = RequestMethod.POST)
	public void insertBloodPushInfo(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{		
		settingService.insertUserBloodPushInfo(param); 		
				
	}
	
	@RequestMapping( value="/updateUserBloodPushInfo.do", method = RequestMethod.POST)
	public void updateBloodPushInfo(HttpServletRequest req, @ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		settingService.updateUserBloodPushInfo(param); 
	}
}
