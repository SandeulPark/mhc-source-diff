package kr.go.mhc.mhcweb.pm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.mhcweb.pm.service.AppServiceUseSttusService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @Class Name : AppServiceUseSttus.java
 * @Description : 관리자 WEB에서 사용하는 App 서비스 이용현황 실적관리 업무를 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2018.11.21		오샘이			최초생성
 *
 * @author theJoin
 * @since 2018.11.21
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
@RequestMapping(value= "/pm")
public class AppServiceUseSttusController extends DMultiActionController {
	
	@Resource(name= "web.pm.AppServiceUseSttusService")
	private AppServiceUseSttusService appServiceUseSttusService;
	
	@ModelAttribute
	public Map<String, Object> initDate(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	
	/**
	 * App서비스 이용현황 메인 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/appServiceUseSttusMain.do", method= RequestMethod.GET)
	public String appServiceUseSttus(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		param.put("CMMN_CD","TC_CM_ORG");
		List<Map<String, String>> selList = cmmnService.selectCmmnCd(param);
		Map<String,Object> rsMap   = cmmnService.selectTodayWeekNm(param);		
				
		model.addAllAttributes(param);
		model.addAttribute("selList", selList);
		model.addAttribute("rsMap",   rsMap);				
		
		return "web/pm/appServiceUseSttus";
	}
	
	/**
	 * App서비스 이용현황 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/appServiceUseSttusList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> selectAppServiceUseSttusList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		
		String trgtYy = param.get("TRGT_YY").toString();

		String COLUMN_YY = trgtYy.substring(2,4);
		param.put("COLUMN_YY", COLUMN_YY);

		Map<String, Object>	rsMap = new HashMap<String, Object>();
		
		List<Map<String, Object>> rsList = new ArrayList<Map<String, Object>>();
		if(Integer.parseInt(trgtYy) >= 2025) {
			rsList = appServiceUseSttusService.selectAppServiceUseSttusListNew(param);
		}else {
			rsList = appServiceUseSttusService.selectAppServiceUseSttusList(param);
		}

		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		model.addAllAttributes(param);
		
		return rsMap;
	}
	
	
	/**
	 * App서비스 이용현황 대상자 목록 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/appServiceUseSttusTrgter.do", method= RequestMethod.POST)
	public String appServiceUseSttusTrgter(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		param.put("CMMN_CD","TC_CM_ORG");
		List<Map<String, String>> selList = cmmnService.selectCmmnCd(param);
		Map<String,Object> rsMap   = cmmnService.selectTodayWeekNm(param); // 2019.03.25 유준영 app서비스 이용현황 상세 화면에서 현재 주차 가져오기 추가
		
		model.addAllAttributes(param);
		model.addAttribute("selList", selList);
		model.addAttribute("rsMap",   rsMap);
		return "web/pm/appServiceUseSttusTrgter";
	}
	
	/**
	 * App서비스 이용현황 대상자 목록 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/appServiceUseSttusTrgterList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> appServiceUseSttusTrgterList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object>	rsMap = new HashMap<String, Object>();
		
		String trgtYy = param.get("TRGT_YY").toString();
		
		List<Map<String, Object>> rsList = new ArrayList<Map<String, Object>>();
		if(Integer.parseInt(trgtYy) >= 2025) {
			rsList = appServiceUseSttusService.selectAppServiceUseSttusTrgterListNew(param);
		}else {
			rsList = appServiceUseSttusService.selectAppServiceUseSttusTrgterList(param);
		}	
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		model.addAllAttributes(param);
		
		return rsMap;
	}	
	
	
	
}