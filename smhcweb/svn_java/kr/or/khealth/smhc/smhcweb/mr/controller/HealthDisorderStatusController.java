package kr.or.khealth.smhc.smhcweb.mr.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.or.khealth.smhc.common.DMultiActionController;
import kr.or.khealth.smhc.smhcweb.mr.service.HealthDisorderStatusService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Class Name : HealthDisorderInfoController.java
 * @Description : 관리자 WEB에서 사용하는 어르신 건강 이상 정보 업무를 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일			수정자		수정내용
 * @	----------		-----		---------------------------
 * @	2020.09.16		양현우		수정
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
public class HealthDisorderStatusController extends DMultiActionController {
	
	@Resource(name = "web.mr.HealthDisorderStatusService")
	private HealthDisorderStatusService healthDisorderInfoService;
	
	@ModelAttribute
	public Map initData(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	
	/**
	 * 건강 이상 정보 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/healthDisorderStatus.do")
	public String healthDisorderStatus(@ModelAttribute Map param, ModelMap model) throws Exception {		
		return "web/mr/healthDisorderStatus";
    }
	
	/**
	 * 이상수치 대상자 리스트 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/selectHealthDisorderStatusList.do")
	public @ResponseBody Map<String, Object> selectHealthDisorderStatusList(@ModelAttribute Map<String, Object> param , ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = healthDisorderInfoService.selectHealthDisorderStatusList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 처리여부 팝업
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/pressProcContSave_pop.do", method= RequestMethod.GET)
	public String pressProcContSave_pop(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		model.addAttribute("MEASR_SN", param.get("MEASR_SN"));
		return "web/mr/healthDisorderStatusPop";
	}
	
	/**
	 * 처리여부 처리내용 저장
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/updateDisorderExamProc.do", method= RequestMethod.POST)
	public @ResponseBody void updateDisorderExamProc(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		healthDisorderInfoService.updateDisorderExamProc(param);
	}
	
	/**
	 * 이상수치 대상자 인원 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/selectDisorderStatusCount.do")
	public @ResponseBody Map<String, Object> selectDisorderStatusCount(@ModelAttribute Map<String, Object> param , ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = healthDisorderInfoService.selectDisorderStatusCount(param);
		rsMap.put("rsList", rsList);
		return rsMap;
	}
}
