package kr.go.mhc.mhcweb.pm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.mhcweb.pm.service.IntensiveUseSttusService;
import kr.go.mhc.mhcweb.pm.service.MajorResultIndexService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @Class Name : IntensiveUseSttus.java
 * @Description : 관리자 WEB에서 사용하는 집중상담 이용현황 실적관리 업무를 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2018.10.11		유준영			최초생성
 *
 * @author theJoin
 * @since 2018.10.11
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
@RequestMapping(value= "/pm")
public class IntensiveUseSttusController extends DMultiActionController {
	
	@Resource(name= "web.pm.IntensiveUseSttusService")
	private IntensiveUseSttusService intensiveUseSttusService;	
	
	@ModelAttribute
	public Map<String, Object> initDate(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	
	/**
	 * 집중상담 이용현황 메인 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/intensiveUseSttusMain.do", method= RequestMethod.GET)
	public String intensiveUseSttus(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		param.put("CMMN_CD","TC_CM_ORG");
		List<Map<String, String>> selList = cmmnService.selectCmmnCd(param);
		Map<String,Object> rsMap   = cmmnService.selectTodayWeekNm(param);		
				
		model.addAllAttributes(param);
		model.addAttribute("selList", selList);
		model.addAttribute("rsMap",   rsMap);		
		
		return "web/pm/intensiveUseSttus";
	}
	
	/**
	 * 집중상담 이용현황 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/intensiveUseSttusList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> selectIntensiveUseSttusList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		
		String trgtYy = param.get("TRGT_YY").toString();
		
		String COLUMN_YY = trgtYy.substring(2,4);
		param.put("COLUMN_YY", COLUMN_YY);

		Map<String, Object>	rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList  = new ArrayList<Map<String, Object>>(); 
		if(Integer.parseInt(trgtYy) >= 2025) {
			rsList = intensiveUseSttusService.selectIntensiveUseSttusListNew(param);
		}else {
			rsList = intensiveUseSttusService.selectIntensiveUseSttusList(param);
		}
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		model.addAllAttributes(param);
		
		return rsMap;
	}
	
	/**
	 * 집중상담 이용현황 대상자 목록 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/intensiveUseSttusTrgter.do", method= RequestMethod.POST)
	public String intensiveUseSttusUserList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		param.put("CMMN_CD","TC_CM_ORG");
		List<Map<String, String>> selList = cmmnService.selectCmmnCd(param);
		Map<String,Object> rsMap   = cmmnService.selectTodayWeekNm(param); // 2019.03.21 유준영 상세화면 현재 주차 가져오게 수정
		model.addAllAttributes(param);
		model.addAttribute("selList", selList);
		model.addAttribute("rsMap",   rsMap); // 2019.03.21 유준영 상세화면 현재 주차 가져오게 수정
		return "web/pm/intensiveUseSttusTrgter";
	}
	
	/**
	 * 집중상담 이용현황 대상자 목록 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/intensiveUseSttusTrgterList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> selectIntensiveUseSttusTrgterList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object>	rsMap = new HashMap<String, Object>();
		
		String trgtYy = param.get("TRGT_YY").toString();
		
		List<Map<String, Object>> rsList = new ArrayList<Map<String, Object>>(); 
		
		if(Integer.parseInt(trgtYy) >= 2025) {
			rsList = intensiveUseSttusService.selectIntensiveUseSttusTrgterListNew(param);
		}else {
			rsList = intensiveUseSttusService.selectIntensiveUseSttusTrgterList(param);
		}
		
		System.out.println("param :::::::::::::::::::::: " + param);

		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		model.addAllAttributes(param);

		System.out.println("rsList ::::::::::::::::::::: " + rsList.size());
		return rsMap;
	}	
	
	
	
}