package kr.go.mhc.mhcweb.pm.controller;

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
import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.mhcweb.pm.service.TrgterRegSttusService;


/**
 * @Class Name : TrgterRegSttus.java
 * @Description : 관리자 WEB에서 사용하는 대상자 등록현황 실적관리 업무를 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2018.10.11		오샘이			최초생성
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
public class TrgterRegSttusController extends DMultiActionController {
	
	@Resource(name= "web.pm.TrgterRegSttusService")
	private TrgterRegSttusService trgterRegSttusService;
	
	
	
	@ModelAttribute
	public Map<String, Object> initDate(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	
	/**
	 * 대상자 등록현황 메인 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/trgterRegSttusMain.do", method= RequestMethod.GET)
	public String excsRecMain(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		param.put("CMMN_CD","TC_CM_ORG");
		List<Map<String, String>> selList = cmmnService.selectCmmnCd(param);
		model.addAllAttributes(param);
		model.addAttribute("selList", selList);
		return "web/pm/trgterRegSttus";
	}
	
	/**
	 * 대상자 등록현황조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/trgterRegSttusList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> selectTrgterRegSttusList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object>	rsMap = new HashMap<String, Object>();

		String COLUMN_YY = param.get("TRGT_YY").toString().substring(2,4);
		param.put("COLUMN_YY", COLUMN_YY);
		
		//일반
		List<Map<String, Object>> rsGenList = trgterRegSttusService.selectTrgterRegSttusGenList(param);
		//위험요인
		List<Map<String, Object>> rsDenList = trgterRegSttusService.selectTrgterRegSttusDenList(param);		
		//군분류
		List<Map<String, Object>> rsDivList = trgterRegSttusService.selectTrgterRegSttusDivList(param);			
		
		
		System.out.println("param :::::::::: " + param);
		
		rsMap.put("rsGenList", rsGenList);
		rsMap.put("rsDenList", rsDenList);
		rsMap.put("rsDivList", rsDivList);
		
		rsMap.put("id", param.get("id"));
		model.addAllAttributes(param);
		
		return rsMap;
	}
	
	
	
	/**
	 * 대상자 등록현황 대상자 목록 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/trgterRegSttusTrgter.do", method= RequestMethod.POST)
	public String trgterRegSttusUser(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		param.put("CMMN_CD","TC_CM_ORG");
		List<Map<String, String>> selList = cmmnService.selectCmmnCd(param);
		model.addAllAttributes(param);
		model.addAttribute("selList", selList);
		return "web/pm/trgterRegSttusTrgter";
	}
	
	/**
	 * 대상자 등록현황 대상자 목록 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/trgterRegSttusTrgterList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> selectTrgterRegSttusTrgterList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object>	rsMap = new HashMap<String, Object>();
		

		//대상자 등록현황 대상자 목록 조회(종합)
		List<Map<String, Object>> rsList = trgterRegSttusService.selectTrgterRegSttusGenTrgterList(param);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		model.addAllAttributes(param);
		
		return rsMap;
	}
	
	
	/**
	 * 대상자 등록현황 메인 화면 호출(실적 개편)
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/trgterRegSttusMainNew.do", method= RequestMethod.GET)
	public String trgterRegSttusMainNew(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		param.put("CMMN_CD","TC_CM_ORG");
		List<Map<String, String>> selList = cmmnService.selectCmmnCd(param);
		model.addAllAttributes(param);
		model.addAttribute("selList", selList);
		return "web/pm/trgterRegSttusNew";
	}
	
	/**
	 * 대상자 등록현황조회(실적 개편)
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/trgterRegSttusListNew.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> trgterRegSttusListNew(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object>	rsMap = new HashMap<String, Object>();

		String trgtYy = param.get("TRGT_YY").toString();
				
		String COLUMN_YY = trgtYy.substring(2,4);
		param.put("COLUMN_YY", COLUMN_YY);
		
		
		//일반
		List<Map<String, Object>> rsGenList = new ArrayList<Map<String, Object>>();
		if(Integer.parseInt(trgtYy) >= 2025) {
			rsGenList = trgterRegSttusService.selectTrgterRegSttusGenListNew(param);
		}else {
			rsGenList = trgterRegSttusService.selectTrgterRegSttusGenList(param);
		}
		//위험요인
		List<Map<String, Object>> rsDenList = new ArrayList<Map<String, Object>>();
		if(Integer.parseInt(trgtYy) >= 2025) {
			rsDenList = trgterRegSttusService.selectTrgterRegSttusDenListNew(param);
		}else {
			rsDenList = trgterRegSttusService.selectTrgterRegSttusDenList(param);
		}
		//군분류
		List<Map<String, Object>> rsDivList = new ArrayList<Map<String, Object>>();
		if(Integer.parseInt(trgtYy) < 2025) {
			rsDivList = trgterRegSttusService.selectTrgterRegSttusDivList(param);
		}
				
		System.out.println("param :::::::::: " + param);
		
		rsMap.put("rsGenList", rsGenList);
		rsMap.put("rsDenList", rsDenList);
		rsMap.put("rsDivList", rsDivList);
		
		rsMap.put("id", param.get("id"));
		model.addAllAttributes(param);
		
		return rsMap;
	}
	
	
	
	/**
	 * 대상자 등록현황 대상자 목록 화면 호출(실적 개편)
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/trgterRegSttusTrgterNew.do", method= RequestMethod.POST)
	public String trgterRegSttusTrgterNew(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		param.put("CMMN_CD","TC_CM_ORG");
		List<Map<String, String>> selList = cmmnService.selectCmmnCd(param);
		model.addAllAttributes(param);
		model.addAttribute("selList", selList);
		return "web/pm/trgterRegSttusTrgterNew";
	}
	
	/**
	 * 대상자 등록현황 대상자 목록 조회(실적 개편)
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/trgterRegSttusTrgterListNew.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> trgterRegSttusTrgterListNew(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object>	rsMap = new HashMap<String, Object>();
		
		String trgtYy = param.get("TRGT_YY").toString();

		//대상자 등록현황 대상자 목록 조회(종합)
		List<Map<String, Object>> rsList = new ArrayList<Map<String, Object>>();
		if(Integer.parseInt(trgtYy) >= 2025) {
			rsList = trgterRegSttusService.selectTrgterRegSttusGenTrgterListNew(param);
		}else {
			rsList = trgterRegSttusService.selectTrgterRegSttusGenTrgterList(param);
		}
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		model.addAllAttributes(param);
		
		return rsMap;
	}
		
	
	
}