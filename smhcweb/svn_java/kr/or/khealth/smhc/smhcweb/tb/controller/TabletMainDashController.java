package kr.or.khealth.smhc.smhcweb.tb.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.or.khealth.smhc.common.DMultiActionController;
import kr.or.khealth.smhc.smhcweb.tb.service.TabletMainDashService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Class Name : TabletMainDashController.java
 * @Description : 관리자 TABLET에서 사용하는 어르신 건강  업무를 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일			수정자		수정내용
 * @	----------		-----		---------------------------
 * @	2020.10.19		양현우		
 *
 * @author thejoin
 * @since 2020.10.19
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
@RequestMapping(value = "/tb")
public class TabletMainDashController extends DMultiActionController{
	
	@Resource(name = "web.tb.TabletMainDashService")
	private TabletMainDashService tabletMainDashService;
	
	@ModelAttribute
	public Map initData(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	
	/**
	 * 태블릿 메인 화면정보 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/selectTrgterCount.do")
	public @ResponseBody Map<String, Object> selectTrgterCount(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		Map<String, Object> rsTot = tabletMainDashService.selectTrgterTodayTotalCount(param);	 //전체
		Map<String, Object> rsRsv = tabletMainDashService.selectTrgterTodayIngCount(param);		 //예정
		Map<String, Object> rsCmp = tabletMainDashService.selectTrgterTodayEndCount(param);		//완료
		List<Map<String, Object>> rsList = tabletMainDashService.selectTrgterList(param);
		
		rsMap.put("rsTot", rsTot);		
		rsMap.put("rsRsv", rsRsv);		
		rsMap.put("rsCmp", rsCmp);		
		rsMap.put("rsList", rsList);
		
		System.out.println("rsMap :::::: " + rsMap);
		
		return rsMap;
	}
	
	/**
	 * 태블릿 메인 화면 대상자 리스트 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/selectTrgterList.do")
	public @ResponseBody Map<String, Object> selectTrgterList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = tabletMainDashService.selectTrgterList(param);
		rsMap.put("rsList", rsList);
		return rsMap;
	}
	
	/**
	 * 태블릿 대상자 검색 화면 목록 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/selectAllTrgterList.do")
	public @ResponseBody Map<String, Object> selectAllTrgterList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = tabletMainDashService.selectAllTrgterList(param);
		rsMap.put("rsList", rsList);
		return rsMap;
	}	
	
	/**
	 * 태블릿 대면평가 기본정보 화면 호출
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/tabletFaceToFace.do")
	public String seniorDtls(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		model.addAttribute("USER_ID",param.get("USER_ID"));
		model.addAttribute("SVC_NO",param.get("SVC_NO"));
		model.addAllAttributes(param);
		return "web/tb/tabletFaceToFace";
	}
}
