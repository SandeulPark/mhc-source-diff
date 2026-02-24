package kr.or.khealth.smhc.smhcweb.tg.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.or.khealth.smhc.common.DMultiActionController;
import kr.or.khealth.smhc.smhcweb.tg.service.SeniorHealthExamMngtService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;



/**
 * @Class Name : SeniorHealthExamMngtController.java
 * @Description : 대면평가 정보 등록 - 생체정보 항목
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2020.09.29		정준호			최초생성
 *
 * @author thejoin
 * @since 2020.09.29
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
@RequestMapping(value = "/tg")
public class SeniorHealthExamMngtController extends DMultiActionController{

	@Resource(name="web.tg.SeniorHealthExamMngtService")
	private SeniorHealthExamMngtService  seniorHealthExamMngtService;
	
	@ModelAttribute
	public Map<String, Object> initDate(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	
	/**
	 * 유저 생체정보 입력 여부 확인 
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/mngtUserHealthChk.do")
	public @ResponseBody Map<String, Object> mngtUserHealthChk(@ModelAttribute Map<String, Object> param , ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>(); 
		Map<String, Object> rs = seniorHealthExamMngtService.mngtUserHealthChk(param);
		rsMap.put("result", rs);
		return rsMap;
	}
	
	/**
	 * 기존 유저 생체정보 값 불러오기 
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/setUserHealthDetail.do")
	public @ResponseBody Map<String, Object> setUserHealthDetail(@ModelAttribute Map<String, Object> param , ModelMap model) throws Exception{
		
		Map<String, Object> rsMap = new HashMap<String, Object>();
		Map<String, Object> rs = seniorHealthExamMngtService.setUserHealthDetail(param);
		rsMap.put("result", rs);
		return rsMap;
	}
	
	/**
	 * 생체정보 저장
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/regHealthInfo.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> regHealthInfo(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		int rsInt = seniorHealthExamMngtService.regHealthInfo(param);
		int rsSvcInt = seniorHealthExamMngtService.updateServiceManageHealth(param);
		
		rsMap.put("rsInt", rsInt);
		rsMap.put("rsSvcInt", rsSvcInt);
		return rsMap;
    }
}
