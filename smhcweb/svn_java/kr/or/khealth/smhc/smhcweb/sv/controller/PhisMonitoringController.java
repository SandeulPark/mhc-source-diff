package kr.or.khealth.smhc.smhcweb.sv.controller;

import java.util.ArrayList;
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
import kr.or.khealth.smhc.smhcweb.sv.service.PhisMonitoringService;

/**
 * @Class Name : PhisMonitoringController.java
 * @Description : 관리자 WEB에서 사용하는 phis 연계 모니터링 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2021.06.14		윤찬호			최초생성
 *
 * @author chyoon
 * @since 2021.06.14
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
@RequestMapping(value = "/sv")
public class PhisMonitoringController extends DMultiActionController{
	
	@Resource(name="web.sv.PhisMonitoringService")
	private PhisMonitoringService phisMonitoringService;
	
	@ModelAttribute
	public Map<String, Object> initDate(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	
	/**
	 * phis 연계 모니터링 페이지
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/phisMonitoring.do")
	public String phisMonitoring(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		return "web/sv/phisMonitoring";
	}
	
	/**
	 * phis 인터페이스 리스트 호출
	 * @param 
	 * @return 
	 * @throws Exception
	 */
	@RequestMapping(value= "/selectPhisInterfaceList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> selectPhisInterfaceList(@ModelAttribute Map<String, Object> param , ModelMap model) throws Exception{
		
		Map<String, Object> rsMap = new HashMap<String, Object>();
		
		List<Map<String, String>> rsList = phisMonitoringService.selectPhisInterfaceList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
		
	}
	
	/**
	 * phis 상세 조회 리스트 호출
	 * @param 
	 * @return 
	 * @throws Exception
	 */
	@RequestMapping(value= "/selectPhisDtlsList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> selectPhisActList(@ModelAttribute Map<String, Object> param , ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		
		List<Map<String, String>> rsList = new ArrayList<Map<String, String>>();
		
		String tabId = String.valueOf(param.get("tab"));
		
		if(tabId.equals("act")) {
			rsList = phisMonitoringService.selectPhisActList(param);
		}
		if(tabId.equals("body")){
			rsList = phisMonitoringService.selectPhisBodyList(param);
		}
		if(tabId.equals("bloodpress")) {
			rsList = phisMonitoringService.selectPhisBloodpressList(param);
		}
		if(tabId.equals("bloodsugar")) {
			rsList = phisMonitoringService.selectPhisBloodsugarList(param);
		}
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		
		return rsMap;
	}
}
