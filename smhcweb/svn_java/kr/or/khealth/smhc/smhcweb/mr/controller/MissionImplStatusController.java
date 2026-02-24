package kr.or.khealth.smhc.smhcweb.mr.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.or.khealth.smhc.common.DMultiActionController;
import kr.or.khealth.smhc.smhcweb.mr.service.MissionImplStatusService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Class Name : DeviceDistrbtController.java
 * @Description : 관리자 WEB에서 사용하는 어르신 미션실천현황 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2020.09.16		양현우			최초생성
 *
 * @author thejoin
 * @since 2020.09.16
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
@RequestMapping(value = "/mr")
public class MissionImplStatusController extends DMultiActionController{

	@Resource(name="web.mr.MissionImplStatusService")
	private MissionImplStatusService  missionImplStatusService;
	
	@ModelAttribute
	public Map<String, Object> initDate(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	
	/**
	 * 간편 알림 및 푸쉬 팝업
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/mrPushPop.do", method= RequestMethod.GET)
	public String mrPushPop(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		return "web/mr/mrPushPop";
	}
	
	/**
	 * 어르신 미션실천현황 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/missionImplStatus.do")
	public String missionImplStatus(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		return "web/mr/missionImplStatus";
	}
	
	/**
	 * 미션 실천현황 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/selectMissonTrgterList.do")
	public @ResponseBody Map<String, Object> selectMissonTrgterList(@ModelAttribute Map<String, Object> param , ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = missionImplStatusService.selectMissonTrgterList(param);
		rsMap.put("id", param.get("id"));
		rsMap.put("rsList", rsList);
		return rsMap;
	}
	
	/**
	 * 미션 미입력자 COUNT 
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/selectMissonNotEnteredCount.do")
	public @ResponseBody Map<String, Object> selectMissonNotEnteredCount(@ModelAttribute Map<String, Object> param , ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>(); 
		Map<String, Object> rs = missionImplStatusService.selectMissonNotEnteredCount(param);
		rsMap.put("result", rs);
		return rsMap;
	}
}
