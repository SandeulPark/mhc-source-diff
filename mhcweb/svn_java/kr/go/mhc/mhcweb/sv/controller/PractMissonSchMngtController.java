package kr.go.mhc.mhcweb.sv.controller;

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
import kr.go.mhc.mhcweb.sv.service.PractMissonSchMngtService;
import kr.go.mhc.mhcweb.sv.service.SvcMngtService;

/**
 * @Class Name : PractMissonSchMngtController.java
 * @Description : 관리자 WEB에서 실천미션 일정을 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2017.04.06		이태석			최초생성
 *
 * @author thejoin
 * @since 2017.04.06
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
@RequestMapping(value = "/sv")
public class PractMissonSchMngtController extends DMultiActionController {

	@Resource(name = "web.sv.PractMissonSchMngtService")
	private PractMissonSchMngtService practMissonSchMngtService;
	
	@Resource(name = "web.sv.SvcMngtService")
	private SvcMngtService svcMngtService;

	
	@ModelAttribute
	public Map initData(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	
	/**
	 * 실천미션 일정 관리 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/practMissonSchMngt.do",method= {RequestMethod.GET,RequestMethod.POST})
	public String practMissonSchMngt(@ModelAttribute Map param, ModelMap model) throws Exception {
		List<Map<String, Object>> rsList = practMissonSchMngtService.getPractMissonSchList(param);
		 List<Map<String,Object>> rs = practMissonSchMngtService.selectPublicMissionFile(param);
		model.addAttribute("rsList", rsList);
		model.addAttribute("rsList2", rs);
		return "web/sv/practMissonSchMngt";
	}
	
	/**
	 * 실천미션 일정 목록 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/getPractMissonSchList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getPractMissonSchList(@ModelAttribute Map param, ModelMap model) throws Exception {
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String, Object>> rsList = practMissonSchMngtService.getPractMissonSchList(param);
		rsMap.put("rsList", rsList);
		return rsMap;
	}
		
	/**
	 * 실천미션 선택 팝업 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/practMissonSelPop.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String practMissonSelPop(@ModelAttribute Map param, ModelMap model) throws Exception {
		int rsInt = practMissonSchMngtService.getSelWeekTrgterChk(param);
		
		if(rsInt >= Integer.parseInt(param.get("selWeekCnt").toString())){
			model.addAttribute("selWeekTrgterChk", "Y");
		}
		List<Map<String, Object>> rsList = practMissonSchMngtService.getPractMissonCdList(param);
		model.addAttribute("practMissonCdList_pop", rsList);
		return "web/sv/practMissonSelPop";
	}
	
	//추가
	/** 
	 * 보건소 미션 생성 팝업 호출
	 */
	@RequestMapping(value="/publicHealthMissonSelPop")
	public String publicHealthMissonSelPop(@ModelAttribute Map param,ModelMap model) throws Exception {
		 List<Map<String, Object>> rsList2 = practMissonSchMngtService.getAllMissionCDList(param);
		 model.addAttribute("rs", rsList2);
		 return "web/sv/publicHealthMissonSelPop";
	}
	
	//추가
	@RequestMapping(value = "practMissonSelPopList.do",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> practMissonSelPopList(@ModelAttribute Map param, ModelMap model) throws Exception {
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String, Object>> rsList = practMissonSchMngtService.getPractMissonCdList(param);
		rsMap.put("practMissonCdList_pop", rsList);
		return rsMap;
	}
	//추가
	/**
	 * 미션 리스트  목록 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	 @RequestMapping(value = "/getAllMissionCDList.do", method = {RequestMethod.POST,RequestMethod.GET})
	 public @ResponseBody Map<String, Object> getAllMissionCDList(@ModelAttribute Map param, ModelMap model) throws Exception {
		 Map<String,Object> rsMap = new HashMap<String,Object>();
		 List<Map<String, Object>> rsList = practMissonSchMngtService.getAllMissionCDList(param);
		 List<Map<String,Object>> rs = practMissonSchMngtService.selectPublicMissionFile(param);
		 if(rs.size()==0){ 
			 rsMap.put("size","N");
		 }
		 else{
			 rsMap.put("size", "Y"); 
			 rsMap.put("rs", rs);
		 }
		 rsMap.put("rsList", rsList);
		 rsMap.put("id", param.get("id"));
		 return rsMap;
	 }

	//추가
	/** 보건소미션  삭제
	 */
	@RequestMapping(value="/updatePublicHealthMissionDelete.do",method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> updatePublicHealthMissionDelete(@ModelAttribute Map param,ModelMap model) throws Exception {
		Map<String,Object> rsMap = new HashMap<String,Object>();
		int rsInt = practMissonSchMngtService.updatePublicHealthMissionDelete(param);
		if(rsInt ==0){
			rsMap.put("updateHealtCdChk", "N");
		}else{
			rsMap.put("updateHealtCdChk", "Y");
		}
		return rsMap;
	}
	//추가
	/** 보건소미션 수정
	 */
	@RequestMapping(value="/updatePublicHealthMissionUpdate.do",method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> updatePublicHealthMissionUpdate(@ModelAttribute Map param , ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		int rsInt = practMissonSchMngtService.updatePublicHealthMissionUpdate(param);
		if(rsInt == 0){
			rsMap.put("updateClear", "N");
		}else{
			rsMap.put("updateClear", "Y");			
		}
		return rsMap;
	}
	//추가
	/** 보건소미션 생성
	 */
	@RequestMapping(value="insertPublicHealthMission.do",	method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> insertPublicHealthMission(@ModelAttribute Map param , ModelMap model)throws Exception	{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		 Map<String, Object> rsInt = practMissonSchMngtService.insertPublicHealthMisson(param);
		 	rsMap.put("PRACT_MISSION_CD",rsInt.get("PRACT_MISSION_CD"));
		return rsMap;
	}
	
	/**
	 * 실천미션 일정 코드 수정
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/updatePractMissionSchCd.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> updatePractMissionSchCd(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		int rsInt = practMissonSchMngtService.updatePractMissionSchCd(param);
		if(rsInt == 0){
			rsMap.put("updateSchCdChk", "N");
		}else{
			rsMap.put("updateSchCdChk", "Y");			
		}
		return rsMap;
	}

	/* ################################################################################# */
	/* ######################### 만성질환 실천미션 추가 202304 ######################### */

	/**
	 * 실천미션 일정 관리 화면 호출 - 만성질환 - O
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/practMissonChronicSchMngt.do",method= {RequestMethod.GET,RequestMethod.POST})
	public String practMissonChronicSchMngt(@ModelAttribute Map param, ModelMap model) throws Exception {
		List<Map<String, Object>> rsList = practMissonSchMngtService.getPractMissonChronicSchList(param);
		List<Map<String,Object>> rs = practMissonSchMngtService.selectPublicMissionFile(param);

		param.put("CMMN_CD", "TG015");
		List<Map<String,String>> chronicList = cmmnService.selectCmmnCd(param);

		model.addAttribute("rsList", rsList);
		model.addAttribute("rsList2", rs);
		model.addAttribute("chronicList", chronicList);

		return "web/sv/practMissonChronicSchMngt";
	}

	/**
	 * 실천미션 일정 목록 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getPractMissonChronicSchList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getPractMissonChronicSchList(@ModelAttribute Map param, ModelMap model) throws Exception {
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String, Object>> rsList = practMissonSchMngtService.getPractMissonChronicSchList(param);
		rsMap.put("rsList", rsList);
		return rsMap;
	}


	/**
	 * 보건소 만성질환 미션 생성 팝업 호출 - O
	 */
	@RequestMapping(value="/publicHealthMissonChronicSelPop")
	public String publicHealthMissonChronicSelPop(@ModelAttribute Map param,ModelMap model) throws Exception {
		List<Map<String, Object>> rsList2 = practMissonSchMngtService.getAllMissionCDChronicList(param);

		param.put("CMMN_CD", "TG015");
		List<Map<String, String>> chronicGubunList = cmmnService.selectCmmnCd(param);

		model.addAttribute("chronicGubunList", chronicGubunList);
		model.addAttribute("rs", rsList2);
		return "web/sv/publicHealthMissonChronicSelPop";
	}

	//추가
	/**
	 *  만성질환 미션 리스트  목록 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getAllMissionCDChronicList.do", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody Map<String, Object> getAllMissionCDChronicList(@ModelAttribute Map param, ModelMap model) throws Exception {
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String, Object>> rsList = practMissonSchMngtService.getAllMissionCDChronicList(param);
		List<Map<String,Object>> rs = practMissonSchMngtService.selectPublicMissionFile(param);
		if(rs.size()==0){
			rsMap.put("size","N");
		}
		else{
			rsMap.put("size", "Y");
			rsMap.put("rs", rs);
		}
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}

	//추가
	/** 보건소 만성질환 미션  삭제
	 */
	@RequestMapping(value="/updatePublicHealthMissionChronicDelete.do",method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> updatePublicHealthMissionChronicDelete(@ModelAttribute Map param,ModelMap model) throws Exception {
		Map<String,Object> rsMap = new HashMap<String,Object>();
		int rsInt = practMissonSchMngtService.updatePublicHealthMissionDelete(param);
		if(rsInt ==0){
			rsMap.put("updateHealtCdChk", "N");
		}else{
			rsMap.put("updateHealtCdChk", "Y");
		}
		return rsMap;
	}
	//추가
	/** 보건소 만성질환 미션 수정
	 */
	@RequestMapping(value="/updatePublicHealthMissionChronicUpdate.do",method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> updatePublicHealthMissionChronicUpdate(@ModelAttribute Map param , ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		int rsInt = practMissonSchMngtService.updatePublicHealthMissionChronicUpdate(param);
		if(rsInt == 0){
			rsMap.put("updateClear", "N");
		}else{
			rsMap.put("updateClear", "Y");
		}
		return rsMap;
	}
	//추가
	/** 보건소 만성질환 미션 생성
	 */
	@RequestMapping(value="insertPublicHealthMissionChronic.do",	method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> insertPublicHealthMissionChronic(@ModelAttribute Map param , ModelMap model)throws Exception	{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		Map<String, Object> rsInt = practMissonSchMngtService.insertPublicHealthMissonChronic(param);
		rsMap.put("PRACT_MISSION_CD",rsInt.get("PRACT_MISSION_CD"));
		return rsMap;
	}

	/**
	 *  만성질환 실천미션 일정 코드 수정
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updatePractMissionChronicSchCd.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> updatePractMissionChronicSchCd(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		int rsInt = practMissonSchMngtService.updatePractMissionChronicSchCd(param);
		if(rsInt == 0){
			rsMap.put("updateSchCdChk", "N");
		}else{
			rsMap.put("updateSchCdChk", "Y");
		}
		return rsMap;
	}

	/**
	 * 실천미션 선택 팝업 호출 - 리스트에서 변경 버튼 클릭
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/practMissonSelChronicPop.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String practMissonSelChronicPop(@ModelAttribute Map param, ModelMap model) throws Exception {
		int rsInt = practMissonSchMngtService.getSelWeekTrgterChk(param);

		if(rsInt >= Integer.parseInt(param.get("selWeekCnt").toString())){
			model.addAttribute("selWeekTrgterChk", "Y");
		}
		param.put("CHRONIC_CD", param.get("chronicCd").toString());
		List<Map<String, Object>> rsList = practMissonSchMngtService.getPractMissonCdChronicList(param);
		model.addAttribute("practMissonCdChronicList_pop", rsList);
		return "web/sv/practMissonSelChronicPop";
	}

	@RequestMapping(value = "practMissonSelChronicPopList.do",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> practMissonSelChronicPopList(@ModelAttribute Map param, ModelMap model) throws Exception {
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String, Object>> rsList = practMissonSchMngtService.getPractMissonCdChronicList(param);
		rsMap.put("practMissonCdList_pop", rsList);
		return rsMap;
	}


}
