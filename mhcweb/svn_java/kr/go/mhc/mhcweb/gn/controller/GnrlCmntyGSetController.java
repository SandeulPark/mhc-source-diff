package kr.go.mhc.mhcweb.gn.controller;

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
import kr.go.mhc.mhcweb.gn.service.GnrlCmntyGSetService;

/**
 * @Class Name : CmntyGSetController.java
 * @Description : 관리자 WEB에서 사용하는 그룹설정 업무를 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일				수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.08.18		이태석			최초생성
 * @	2016.08.29		허광일			수정
 * @author gst
 * @since 2016.08.18
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
@RequestMapping(value = "/gn")
public class GnrlCmntyGSetController extends DMultiActionController{

	@Resource(name = "web.gn.GnrlCmntyGSetService")
	private GnrlCmntyGSetService gnrlCmntyGSetService;
	
	@ModelAttribute
	public Map initData(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	
	/**
	 * 그룹설정 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/cmntyGSet.do", method = RequestMethod.GET)
	public String cmntyGSet(@ModelAttribute Map param, ModelMap model) throws Exception {

		return "web/gn/cmntyGSet";
	}

	/**
	 * 그룹 목록 조회
	 * @param param 검색 조건
	 * @return rsMap
	 * @throws Exception 
	 */
	@RequestMapping(value = "/cmntyGList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> cmntyGList( @ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();		
		List<Map<String, String>> rsList = gnrlCmntyGSetService.getCmntyGList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));	
		rsMap.put("gridTotalRowCount", null);
		return rsMap;
	}

	/**
	 * 그룹 대상자 조회
	 * @param param 검색 조건
	 * @return rsMap
	 * @throws Exception 
	 */
	@RequestMapping(value = "/cmntyGTrgterList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> cmntyGTrgterList( @ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();		
		List<Map<String, String>> rsList = gnrlCmntyGSetService.getCmntyGTrgterList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 신규 그룹 코드 조회
	 * @param param 검색 조건
	 * @return rsMap
	 * @throws Exception 
	 */
	@RequestMapping(value = "/newCmntyGSn.do", method = RequestMethod.GET)
	public @ResponseBody Map<String,Object> newCmntyGSn( @ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String,Object> rsMap = gnrlCmntyGSetService.getNewCmntyGSn(param);
		return rsMap;
	}
	
	/**
	 * 추가 대상자 팝업 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/cmntyGSetPop.do", method = RequestMethod.GET)
	public String cmntyGSetPop(@ModelAttribute Map param, ModelMap model) throws Exception {
		List<Map<String,String>> gclasList = gnrlCmntyGSetService.getGclasList();
		model.addAttribute("gclasList",gclasList);
		return "web/gn/cmntyGSetPop";
	}
	
	/**
	 * 그룹 등록  
	 * @param param 저장 정보
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/cmntyGInsert.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> cmntyGInsert(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		gnrlCmntyGSetService.getCmntyGInsert(param);
		Map<String, Object> rsMap = new HashMap<String, Object>();		
		List<Map<String, String>> rsList = gnrlCmntyGSetService.getCmntyGList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));		
		return rsMap;
	}
	
	/**
	 * 그룹 수정
	 * @param param 저장 정보
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/cmntyGUpd.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> cmntyGUpd(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		gnrlCmntyGSetService.getCmntyGUpd(param);
		Map<String, Object> rsMap = new HashMap<String, Object>();		
		List<Map<String, String>> rsList = gnrlCmntyGSetService.getCmntyGList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));		
		return rsMap;
	}
	
	/**
	 * 그룹 삭제(사용안함)  
	 * @param param 저장 정보
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/cmntyGUseN.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> cmntyGUseN(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		gnrlCmntyGSetService.getCmntyGUseN(param);
		Map<String, Object> rsMap = new HashMap<String, Object>();		
		List<Map<String, String>> rsList = gnrlCmntyGSetService.getCmntyGList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));		
		return rsMap;
	}

	/**
	 * 그룹 대상자 삭제(사용안함)
	 * @param param 저장 정보
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/cmntyGTrgterUseN.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> cmntyGTrgterUseN(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		gnrlCmntyGSetService.getCmntyGTrgterUseN(param);
		Map<String, Object> rsMap = new HashMap<String, Object>();		
		List<Map<String, String>> rsList = gnrlCmntyGSetService.getCmntyGTrgterList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));		
		return rsMap;
	}
	
	/**
	 * 그룹 등록  
	 * @param param 저장 정보
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/cmntyGTrgterInsert.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> cmntyGTrgterInsert(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		gnrlCmntyGSetService.getCmntyGTrgterInsert(param);
		Map<String, Object> rsMap = new HashMap<String, Object>();		
		List<Map<String, String>> rsList = gnrlCmntyGSetService.getCmntyGList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));		
		return rsMap;
	}
	
	/**
	 * 추가 대상자 조회 (팝업)
	 * @param param 검색 조건
	 * @return rsMap
	 * @throws Exception 
	 */
	@RequestMapping(value = "/addTrgterList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> addTrgterList( @ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();		
		List<Map<String, String>> rsList = gnrlCmntyGSetService.getAddTrgterList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
}
