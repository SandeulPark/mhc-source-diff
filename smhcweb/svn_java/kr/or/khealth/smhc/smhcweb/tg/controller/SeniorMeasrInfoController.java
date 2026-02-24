package kr.or.khealth.smhc.smhcweb.tg.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.or.khealth.smhc.common.DMultiActionController;
import kr.or.khealth.smhc.smhcweb.tg.service.SeniorMeasrInfoService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Class Name : SeniorMeasrInfoController.java
 * @Description : 관리자 WEB에서 사용하는 어르신 대상자 측정정보 조회를 관리하는 컨트롤러
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2020.10.15		오샘이			수정
 *
 * @author thejoin
 * @since 2020.10.15
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
@RequestMapping(value = "/tg")
public class SeniorMeasrInfoController extends DMultiActionController {

	@Resource(name= "web.tg.SeniorMeasrInfoService")
	private SeniorMeasrInfoService seniorMeasrInfoService;

	
	@ModelAttribute
	public Map<String, Object> initDate(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	
	/**
	 * 대상자총괄관리 측정 정보 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/measrInfo.do")
	public @ResponseBody Map<String, Object> selectMeasrInfo(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		
		Map<String, Object> rsMap = new HashMap<String, Object>();
		
		Map<String, Object> rsCnt = seniorMeasrInfoService.selectMeasrWeekCntInfo(param);
		Map<String, Object> rsDay = seniorMeasrInfoService.selectMeasrNoMeasrInfo(param);				
		
		rsMap.put("rsCnt", rsCnt);
		rsMap.put("rsDay", rsDay);
		rsMap.put("id", param.get("id"));
		
		return rsMap;
	}
	
	/**
	 * 대상자총괄관리 활동 정보 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/actChartList.do")
	public @ResponseBody Map<String, Object> selectActChartList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = seniorMeasrInfoService.selectActChartList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 대상자총괄관리 체성분 정보 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/bodyCompChartList.do")
	public @ResponseBody Map<String, Object> selectBodyCompChartList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = seniorMeasrInfoService.selectBodyCompChartList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 대상자총괄관리 혈압 정보 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/bloodPessChartList.do")
	public @ResponseBody Map<String, Object> selectBloodPessChartList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = seniorMeasrInfoService.selectBloodPressChartList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}	
	
	/**
	 * 대상자총괄관리 혈당 정보 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/bloodSugarChartList.do")
	public @ResponseBody Map<String, Object> selectBloodSugarChartList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = seniorMeasrInfoService.selectBloodSugarChartList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}		
	
}
