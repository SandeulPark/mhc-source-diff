package kr.go.mhc.mhcweb.pm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.go.mhc.common.DMultiActionController;


import kr.go.mhc.mhcweb.pm.service.MajorResultIndexService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @Class Name : MajorResultIndexController.java
 * @Description : 관리자 WEB에서 사용하는 대상자별 주요성과 지표 실적관리 업무를 관리하는 컨트롤러 Class
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
public class MajorResultIndexController extends DMultiActionController {
	
	@Resource(name= "web.pm.MajorResultIndexService")
	private MajorResultIndexService majorResultIndexService;
	
	@ModelAttribute
	public Map<String, Object> initDate(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	
	/**
	 *  메인 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/majorResultIndexMain.do", method= RequestMethod.GET)
	public String majorResultIndex(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		param.put("CMMN_CD","TC_CM_ORG");
		List<Map<String, String>> selList = cmmnService.selectCmmnCd(param);
		Map<String,Object> rsMap   = cmmnService.selectTodayWeekNm(param);				
		
		model.addAllAttributes(param);
		model.addAttribute("selList", selList);
		model.addAttribute("rsMap",   rsMap);		
		
		
		return "web/pm/majorResultIndexSttus";
	}	
	
	
	/**
	 * 대상자 등록현황조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/majorResultIndexList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> selectTrgterRegSttusList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object>	rsMap = new HashMap<String, Object>();
		
		System.out.println("majorResultIndexList");

		String COLUMN_YY = param.get("TRGT_YY").toString().substring(2,4);
		param.put("COLUMN_YY", COLUMN_YY);

		//개요
		List<Map<String, Object>> rsSum  = majorResultIndexService.selectSummaryList(param);
		//지속참여자 및 중도탈락자
		List<Map<String, Object>> rsPart = majorResultIndexService.selectTrgterPartDropList(param);		
		//건강행태1개이상개선자
		List<Map<String, Object>> rsImp  = majorResultIndexService.selectHealthResultImpList(param);			
		//건강위험요인1개이상감소자
		List<Map<String, Object>> rsRds  = majorResultIndexService.selectHealthDangerDecList(param);					
		//만족도점수
		List<Map<String, Object>> rsPnt  = majorResultIndexService.selectServeySatisScoreList(param);		

		
		rsMap.put("rsSumList",  rsSum);
		rsMap.put("rsPartList", rsPart);
		rsMap.put("rsImpList",  rsImp);
		rsMap.put("rsRdsList",  rsRds);
		rsMap.put("rsPntList",  rsPnt);		
		
		rsMap.put("id", param.get("id"));
		model.addAllAttributes(param);

		return rsMap;
	}	
	
	
	
	
	/**
	 *  상세화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/majorResultIndexTrgter.do", method= RequestMethod.POST)
	public String majorResultIndexTrgter(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		param.put("CMMN_CD","TC_CM_ORG");
		List<Map<String, String>> selList = cmmnService.selectCmmnCd(param);
		Map<String,Object> rsMap   = cmmnService.selectTodayWeekNm(param);	
		
		model.addAllAttributes(param);
		model.addAttribute("selList", selList);
		model.addAttribute("rsMap",   rsMap);		

		return "web/pm/majorResultIndexTrgter";
	}	
	
	
	/**
	 * 상세화면 대상자정보 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/majorResultIndexTrgterList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> majorResultIndexTrgterList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object>	rsMap = new HashMap<String, Object>();
		
		List<Map<String, Object>> rsList = new ArrayList<Map<String, Object>>();

		
		if("gridDtls0".equals(param.get("id"))) {
			 rsList =  majorResultIndexService.selectSummaryTrgerList(param);         
	 	}else if("gridDtls1".equals(param.get("id"))){
			rsList =  majorResultIndexService.selectTrgterPartDropTrgterList(param);	
			
		}else if("gridDtls2".equals(param.get("id"))){
			rsList =  majorResultIndexService.selectHealthResultImpTrgterList(param);				
			
		}else if("gridDtls3".equals(param.get("id"))){
			rsList =  majorResultIndexService.selectHealthDangerDecTrgterList(param);				
			
		}else if("gridDtls4".equals(param.get("id"))){
			rsList =  majorResultIndexService.selectServeySatisScoreTrgterList(param);				
			
		}	

		rsMap.put("rsList", rsList);

		rsMap.put("id", param.get("id"));
		model.addAllAttributes(param);

		return rsMap;
	}	
	
	
	/**
	 * 주요성과지표 화면 호출(실적 개편)
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/majorResultIndexMainNew.do", method= RequestMethod.GET)
	public String majorResultIndexNew(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		param.put("CMMN_CD","TC_CM_ORG");
		List<Map<String, String>> selList = cmmnService.selectCmmnCd(param);
		Map<String,Object> rsMap   = cmmnService.selectTodayWeekNm(param);				
		
		model.addAllAttributes(param);
		model.addAttribute("selList", selList);
		model.addAttribute("rsMap",   rsMap);		
		
		
		return "web/pm/majorResultIndexSttusNew";
	}	
	
	
	/**
	 * 주요성과지표 대상자 조회(실적 개편)
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/majorResultIndexListNew.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> selectTrgterRegSttusListNew(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object>	rsMap = new HashMap<String, Object>();
		
		System.out.println("majorResultIndexList");
		
		String trgtYy = param.get("TRGT_YY").toString();

		String COLUMN_YY = trgtYy.substring(2,4);
		param.put("COLUMN_YY", COLUMN_YY);

		//개요
		List<Map<String, Object>> rsSum  = new ArrayList<Map<String, Object>>(); 
		if(Integer.parseInt(trgtYy) >= 2025) {
			rsSum = majorResultIndexService.selectSummaryListNew(param);
		}else {
			rsSum = majorResultIndexService.selectSummaryList(param);
		}
		//지속참여자 및 중도탈락자
		List<Map<String, Object>> rsPart = new ArrayList<Map<String, Object>>(); 
		if(Integer.parseInt(trgtYy) >= 2025) {
			rsPart = majorResultIndexService.selectTrgterPartDropListNew(param);
		}else {
			rsPart = majorResultIndexService.selectTrgterPartDropList(param);
		}
		//건강행태1개이상개선자
		List<Map<String, Object>> rsImp  = new ArrayList<Map<String, Object>>();
		if(Integer.parseInt(trgtYy) >= 2025) {
			rsImp = majorResultIndexService.selectHealthResultImpListNew(param);
		}else {
			rsImp = majorResultIndexService.selectHealthResultImpList(param);
		}
		//건강위험요인1개이상감소자
		List<Map<String, Object>> rsRds  = new ArrayList<Map<String, Object>>();
		if(Integer.parseInt(trgtYy) >= 2025) {
			rsRds =	majorResultIndexService.selectHealthDangerDecListNew(param);
			//rsRds =	majorResultIndexService.selectHealthDangerDecList(param);
		}else {
			rsRds =	majorResultIndexService.selectHealthDangerDecList(param);
		}
		//만족도점수
		List<Map<String, Object>> rsPnt  = new ArrayList<Map<String, Object>>();
		if(Integer.parseInt(trgtYy) >= 2025) {
			rsPnt =	majorResultIndexService.selectServeySatisScoreListNew(param);
			//rsPnt =	majorResultIndexService.selectServeySatisScoreList(param);
		}else {
			rsPnt =	majorResultIndexService.selectServeySatisScoreList(param);
		}
		
		rsMap.put("rsSumList",  rsSum);
		rsMap.put("rsPartList", rsPart);
		rsMap.put("rsImpList",  rsImp);
		rsMap.put("rsRdsList",  rsRds);
		rsMap.put("rsPntList",  rsPnt);		
		
		rsMap.put("id", param.get("id"));
		model.addAllAttributes(param);

		return rsMap;
	}	
	
	
	
	
	/**
	 * 주요성과지표 상세화면 호출(실적 개편)
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/majorResultIndexTrgterNew.do", method= RequestMethod.POST)
	public String majorResultIndexTrgterNew(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		param.put("CMMN_CD","TC_CM_ORG");
		List<Map<String, String>> selList = cmmnService.selectCmmnCd(param);
		Map<String,Object> rsMap   = cmmnService.selectTodayWeekNm(param);	
		
		model.addAllAttributes(param);
		model.addAttribute("selList", selList);
		model.addAttribute("rsMap",   rsMap);		

		return "web/pm/majorResultIndexTrgterNew";
	}	
	
	
	/**
	 * 주요성과지표 상세화면 대상자정보 조회(실적 개편)
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/majorResultIndexTrgterListNew.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> majorResultIndexTrgterListNew(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object>	rsMap = new HashMap<String, Object>();
		
		List<Map<String, Object>> rsList = new ArrayList<Map<String, Object>>();
		
		String trgtYy = param.get("TRGT_YY").toString();
		
		if("gridDtls0".equals(param.get("id"))) {
			if(Integer.parseInt(trgtYy) >= 2025) {
				rsList =  majorResultIndexService.selectSummaryTrgerListNew(param);
			}else {
				rsList =  majorResultIndexService.selectSummaryTrgerList(param);
			}
	 	}else if("gridDtls1".equals(param.get("id"))){
	 		if(Integer.parseInt(trgtYy) >= 2025) {
	 			rsList =  majorResultIndexService.selectTrgterPartDropTrgterListNew(param);
	 		}else {
	 			rsList =  majorResultIndexService.selectTrgterPartDropTrgterList(param);
	 		}
		}else if("gridDtls2".equals(param.get("id"))){
			if(Integer.parseInt(trgtYy) >= 2025) {
				rsList =  majorResultIndexService.selectHealthResultImpTrgterListNew(param);
			}else {
				rsList =  majorResultIndexService.selectHealthResultImpTrgterList(param);
			}
		}else if("gridDtls3".equals(param.get("id"))){
			if(Integer.parseInt(trgtYy) >= 2025) {
				rsList =  majorResultIndexService.selectHealthDangerDecTrgterListNew(param);
			}else {
				rsList =  majorResultIndexService.selectHealthDangerDecTrgterList(param);
			}			
		}else if("gridDtls4".equals(param.get("id"))){
			if(Integer.parseInt(trgtYy) >= 2025) {
				rsList =  majorResultIndexService.selectServeySatisScoreTrgterListNew(param);
			}else {
				rsList =  majorResultIndexService.selectServeySatisScoreTrgterList(param);
			}
		}	

		rsMap.put("rsList", rsList);

		rsMap.put("id", param.get("id"));
		model.addAllAttributes(param);

		return rsMap;
	}
		

	
}	
	
	
	
	
	
	
	
	
	