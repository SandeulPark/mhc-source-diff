package kr.go.mhc.mhcweb.sm.controller;

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

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.mhcweb.sm.service.HealthDisValMngtService;

/**
 * @Class Name : HealthDisValMngtController.java
 * @Description : 관리자 WEB에서 사용하는 건강이상수치 설정 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일				수정자			수정내용
 * @	----------		----		---------------------------
 * @	2017.02.17		나연이			최초생성
 * @author theJoin
 * @since 2017.02.17
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
@RequestMapping(value = "/sm")
public class HealthDisValMngtController extends DMultiActionController{

	@Resource(name = "web.sm.HealthDisValMngtService")
	private HealthDisValMngtService healthDisValMngtService;
	
	@ModelAttribute
	public Map initData(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	
	/**
	 * 건강이상수치설정 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/healthDisValMngt.do")
	public String healthDisValMngt(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {
		return "web/sm/healthDisValMngt";
	}
	
	@RequestMapping(value = "/orgCdList.do")
	public @ResponseBody Map<String, Object> getOrgCdList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		List<Map<String,Object>> orgList = healthDisValMngtService.getOrgCdList(param);
		model.addAttribute("orgList", orgList);
		return model;
	}
	
	/**
	 * 기관별 건강이상수치 정보 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/healthDisValList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> getHealthDisValList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, String>> rsList = healthDisValMngtService.getHealthDisValList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 기관별 건강이상수치 신규 등록 및 수정
	 * @param param
	 * @param model
	 * @param req
	 * @throws Exception
	 */
	@RequestMapping(value = "/mergeHealthDisVal.do", method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> mergeHealthDisVal(@ModelAttribute Map<String, Object> param, ModelMap model, HttpServletRequest req) throws Exception {
		Map<String,Object> rsMap = new HashMap<String, Object>();
		int rsInt = healthDisValMngtService.mergeHealthDisVal(param);
		rsMap.put("rsInt", rsInt);
		return rsMap;
	}
	

}
