package kr.or.khealth.smhc.smhcweb.sv.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.or.khealth.smhc.common.DMultiActionController;
import kr.or.khealth.smhc.smhcweb.sv.service.SelfMissionMngtService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Class Name : SelfMissionMngtController.java
 * @Description : 관리자 WEB에서 사용하는 어르신 자체미션 등록을  관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2020.11.09		양현우 			최초생성
 *
 * @author theJoin
 * @since 2020.11.09
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
@RequestMapping(value="/sv")
public class SelfMissionMngtController extends DMultiActionController{
	
	@Resource(name="web.sv.SelfMissionMngtService")
	private SelfMissionMngtService selfMissionMngtService;
	
	@ModelAttribute
	public Map<String, Object> initDate(HttpServletRequest req) throws Exception{
		return super.initData(req);
	}

	
	/**
	 * 자체 미션 등록 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/selfMissionMngt.do")
	public String faceToFaceInfoReg(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		return "web/sv/selfMissionMngt";
	}
	
	/**
	 * 미션 목록 조회
	 * @param param 검색 조건
	 * @return rsMap
	 * @throws Exception 
	 */
	@RequestMapping(value= "/selectSelfMissionMngtList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> selectSelfMissionMngtList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = selfMissionMngtService.selectSelfMissionMngtList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;	
	}
	
	/**
	 * 미션 저장
	 * @param param 검색 조건
	 * @return rsMap
	 * @throws Exception 
	 */
	@RequestMapping(value= "/insertSelfMissionMngt.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> insertSelfMissionMngt(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		int rsInt = selfMissionMngtService.insertSelfMissionMngt(param);
		rsMap.put("rsInt", rsInt);
		return rsMap;	
	}
	
	/**
	 * 미션 수정
	 * @param param 검색 조건
	 * @return rsMap
	 * @throws Exception 
	 */
	@RequestMapping(value= "/updSelfMissionMngt.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> updSelfMissionMngt(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		int rsInt = selfMissionMngtService.updSelfMissionMngt(param);
		rsMap.put("rsInt", rsInt);
		return rsMap;	
	}
	
	/**
	 * 미션 정보 카운트
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/selectSelfMissionMngtCount.do")
	public @ResponseBody Map<String, Object> selectMeasrInfo(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		Map<String, Object> rsCnt = selfMissionMngtService.selectSelfMissionMngtCount(param);	
		rsMap.put("rsCnt", rsCnt);		
		
		return rsMap;
	}
}
