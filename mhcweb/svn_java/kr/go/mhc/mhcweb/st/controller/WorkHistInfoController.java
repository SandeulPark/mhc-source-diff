package kr.go.mhc.mhcweb.st.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.mhcweb.st.service.WorkHistInfoService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Class Name : WorkHistInfoController.java
 * @Description : 업무 이력 정보를 조회하는 컨트롤러 class
 * @Modification Information
 * @
 * @	수정일				수정자			수정내용
 * @	----------		----		---------------------------
 * @	2019.10.15		오샘이			최초생성
 *
 * @author theJoin
 * @since 2019.10.15
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
public class WorkHistInfoController extends DMultiActionController {

	@Resource(name= "web.st.WorkHistInfoService")
	private WorkHistInfoService workHistInfoService;
	
	@ModelAttribute
	public Map<String,Object> initDate(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}

	/**
	 * 업무 이력 조회 화면 호출
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/st/workHistInfo.do", method=RequestMethod.GET)
	public String workHistInfo(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {		
		param.put("CMMN_CD","TC_CM_ORG");
		List<Map<String, String>> selList = cmmnService.selectCmmnCd(param);
		model.addAllAttributes(param);
		model.addAttribute("selList", selList);		
		return "web/st/workHistInfo";
	}
	
	/**
	 * 화면 접속 이력 정보 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/st/workHistConInfoList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> workHistConInfoList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		
		Map<String, Object> rsMap = new HashMap<String, Object>();
		if(param.get("pagingSet[gridRowsPerPage]") != null){
			int gridTotalRowCount = workHistInfoService.workHistConInfoListCount(param);
			rsMap.put("gridTotalRowCount", gridTotalRowCount);
		}
		List<Map<String, String>> rsList = workHistInfoService.workHistConInfoList(param);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}

	/**
	 * 개인정보조회 이력 정보 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/st/workHistPerSchInfoList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> workHistPerSchInfoList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		
		Map<String, Object> rsMap = new HashMap<String, Object>();
		if(param.get("pagingSet[gridRowsPerPage]") != null){
			int gridTotalRowCount = workHistInfoService.workHistPerSchInfoListCount(param);
			rsMap.put("gridTotalRowCount", gridTotalRowCount);
		}
		List<Map<String, String>> rsList = workHistInfoService.workHistPerSchInfoList(param);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	
	/**
	 * 엑셀다운로드 이력 정보 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/st/workHistDownloadInfoList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> workHistDownloadInfoList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		
		Map<String, Object> rsMap = new HashMap<String, Object>();
		if(param.get("pagingSet[gridRowsPerPage]") != null){
			int gridTotalRowCount = workHistInfoService.workHistDownloadInfoListCount(param);
			rsMap.put("gridTotalRowCount", gridTotalRowCount);
		}
		List<Map<String, String>> rsList = workHistInfoService.workHistDownloadInfoList(param);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
}
