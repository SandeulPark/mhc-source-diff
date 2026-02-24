package kr.go.mhc.mhcweb.pm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.mhcweb.pm.service.WorkManagerSttusService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @Class Name : WorkManagerSttus.java
 * @Description : 관리자 WEB에서 사용하는 업무담당자 현황 실적관리 업무를 관리하는 컨트롤러 Class
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
public class WorkManagerSttusController extends DMultiActionController {
	
	@Resource(name= "web.pm.WorkManagerSttusService")
	private WorkManagerSttusService workManagerSttusService;
	
	@ModelAttribute
	public Map<String, Object> initDate(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	
	/**
	 * 업무담당자 현황 메인 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/workManagerSttusMain.do", method= RequestMethod.GET)
	public String excsRecMain(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		param.put("CMMN_CD","TC_CM_ORG");
		List<Map<String, String>> selList = cmmnService.selectCmmnCd(param);
		model.addAllAttributes(param);
		model.addAttribute("selList", selList);
		return "web/pm/workManagerSttus";
	}
	
	/**
	 * 업무담당자 현황 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/workManagerSttusList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> selectWorkManagerSttusList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		String COLUMN_YY = param.get("TRGT_YY").toString().substring(2,4);
		param.put("COLUMN_YY", COLUMN_YY);

		Map<String, Object>	rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = workManagerSttusService.selectWorkManagerSttusList(param);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		model.addAllAttributes(param);
		
		return rsMap;
	}
	
	
	/**
	 * 업무담당자 현황 담당자 목록 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/workManagerSttusTrgter.do", method= RequestMethod.POST)
	public String trgterRegSttusUser(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		param.put("CMMN_CD","TC_CM_ORG");
		List<Map<String, String>> selList = cmmnService.selectCmmnCd(param);
		model.addAllAttributes(param);
		model.addAttribute("selList", selList);
		return "web/pm/workManagerSttusTrgter";
	}	
	
	/**
	 * 업무담당자 현황 담당자 목록 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/workManagerSttusTrgterList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> workManagerSttusTrgterList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object>	rsMap = new HashMap<String, Object>();

		List<Map<String, Object>> rsList = workManagerSttusService.selectWorkManagerSttusTrgterList(param);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		model.addAllAttributes(param);

		return rsMap;
	}	
	
	
	
}