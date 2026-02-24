package kr.or.khealth.smhc.smhcweb.tg.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;









import kr.or.khealth.smhc.common.DMultiActionController;
import kr.or.khealth.smhc.common.util.StringUtil;
import kr.or.khealth.smhc.smhcweb.sv.service.IntensiveBodyActObstyCnslService;
import kr.or.khealth.smhc.smhcweb.tg.service.DeviceDistrbtMngtService;
import kr.or.khealth.smhc.smhcweb.tg.service.HealthMngtCnslService;
import kr.or.khealth.smhc.smhcweb.tg.service.SeniorTrgterInfoService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Class Name : DeviceDistrbtController.java
 * @Description : 관리자 WEB에서 사용하는 어르신 대상자 조회을 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2020.09.16		양현우			수정
 *
 * @author thejoin
 * @since 2020.09.16
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
@RequestMapping(value = "/tg")
public class SeniorTrgterInfoController extends DMultiActionController {

	@Resource(name= "web.tg.TrgterInfoMngtService")
	private SeniorTrgterInfoService trgterInfoMngtService;

	
	@ModelAttribute
	public Map<String, Object> initDate(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	
	/**
	 * 대상자총괄관리 대상자 정보관리 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/seniorTrgterInfo.do")
	public String seniorTrgterInfo(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		param.put("CMMN_CD","TC_CM_ORG");
		model.addAttribute("REQ_SEARCH_INFO", param.get("REQ_SEARCH_INFO"));
		//2023-06-29 추가 (검색조건 시도값)
		List<Map<String, String>> selList = cmmnService.selectCmmnCd(param);
		model.addAttribute("selList", selList);
		
		return "web/tg/seniorTrgterInfo";
	}
	
	/**
	 * 대상자총괄관리 대상자 목록 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/selectSeniorTrgterInfoList.do")
	public @ResponseBody Map<String, Object> selectSeniorTrgterInfoList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		String searchInfo = StringUtil.nvl(String.valueOf(param.get("REQ_SEARCH_INFO")));
		if(!"".equals(searchInfo)){
			param.put("searchInfoList", StringUtil.makeStringToIterator(searchInfo));
		}
		if(param.get("ORG_CD") != null) {
			if(param.get("ORG_CD").equals("")) {
				param.put("ORG_CD", "ALL");
			}
		}
		if(param.get("SIDO_CD") != null) {
			param.put("SIDO_CD", param.get("SIDO_CD"));
		}
		
		List<Map<String, Object>> rsList = trgterInfoMngtService.selectSeniorTrgterInfoList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 대상자총괄관리 대상자 달력 조회 
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/selectSeniorTrgterCalendarList.do")
	public @ResponseBody Map<String, Object> selectSeniorTrgterCalendarList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = trgterInfoMngtService.selectSeniorTrgterCalendarList(param);
		rsMap.put("rsList", rsList);
		return rsMap;
	}
	
}
