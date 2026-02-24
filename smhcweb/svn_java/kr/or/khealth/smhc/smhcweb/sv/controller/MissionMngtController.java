package kr.or.khealth.smhc.smhcweb.sv.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.or.khealth.smhc.common.DMultiActionController;
import kr.or.khealth.smhc.smhcweb.sv.service.MissionMngtService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Class Name : MissionMngtController.java
 * @Description : 관리자 WEB에서 사용하는 미션설정관리 업무를 관리하는 컨트롤러 Class
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
@RequestMapping(value = "/sv")
public class MissionMngtController extends DMultiActionController {
	
	@Resource(name="web.sv.MissionMngtService")
	private MissionMngtService missionMngtService;
	
	@ModelAttribute
	public Map<String,Object> initDate(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	
	/**
	 * 미션설정관리 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value="/missionMngt.do")
	public String missionMngt(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {
		return "web/sv/missionMngt";
	}

	/**
	 * 미션 설정 대상 리스트 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/selectMissionMngtList.do")
	public @ResponseBody Map<String, Object> selectMissionMngtList(@ModelAttribute Map<String, Object> param , ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = missionMngtService.selectMissionMngtList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 유저현재 미션값 셋팅
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/setMissonInfo.do")
	public @ResponseBody Map<String, Object> setMissonInfo(@ModelAttribute Map<String, Object> param , ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> deviceList = missionMngtService.setDeviceInfo(param);
		List<Map<String, Object>> missionList = missionMngtService.setMissionInfo(param);
		rsMap.put("deviceList", deviceList);
		rsMap.put("missionList", missionList);
		return rsMap;
	}
	
	/**
	 * 미션 upd
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value="/updMissionSet.do")
	public @ResponseBody Map<String, Object> updMissionSet(@ModelAttribute Map<String, Object> param , ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		int rsInt = missionMngtService.updMissionSet(param);
		rsMap.put("rsInt", rsInt);
		return rsMap;
	}
	
	/**
	 * 복약 upd
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value="/updDrugInfo.do")
	public @ResponseBody Map<String, Object> updDrugInfo(@ModelAttribute Map<String, Object> param , ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		int rsInt = missionMngtService.updDrugInfo(param);
		rsMap.put("rsInt", rsInt);
		return rsMap;
	}
	
	/**
	 * 복약 Mission_set upd
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value="/updDrugMissionSet.do")
	public @ResponseBody Map<String, Object> updDrugMissionSet(@ModelAttribute Map<String, Object> param , ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		int rsInt = missionMngtService.updDrugMissionSet(param);
		rsMap.put("rsInt", rsInt);
		return rsMap;
	}
	
}
