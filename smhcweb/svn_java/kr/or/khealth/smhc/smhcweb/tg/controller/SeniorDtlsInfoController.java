package kr.or.khealth.smhc.smhcweb.tg.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.or.khealth.smhc.common.DMultiActionController;
import kr.or.khealth.smhc.smhcweb.tg.service.SeniorDtlsInfoService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;



/**
 * @Class Name : SeniorDtlsInfoController.java
 * @Description : 대면평가 등록 - 대상자 상세 정보
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
public class SeniorDtlsInfoController extends DMultiActionController{

	@Resource(name="web.tg.SeniorDtlsInfoService")
	private SeniorDtlsInfoService  seniorDtlsInfoService;
	
	@ModelAttribute
	public Map<String, Object> initDate(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	
	/**
	 * 유저 상세정보 입력 여부 확인 
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/mngtUserInfoDetailChk.do")
	public @ResponseBody Map<String, Object> mngtUserInfoDetailChk(@ModelAttribute Map<String, Object> param , ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>(); 
		Map<String, Object> rs = seniorDtlsInfoService.mngtUserInfoDetailChk(param);
		rsMap.put("result", rs);
		return rsMap;
	}
	
	/**
	 * 기존 유저 상세정보 값 불러오기 
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/setUserInfoDetail.do")
	public @ResponseBody Map<String, Object> setUserInfoDetail(@ModelAttribute Map<String, Object> param , ModelMap model) throws Exception{
		
		Map<String, Object> rsMap = new HashMap<String, Object>();
		Map<String, Object> rs = seniorDtlsInfoService.setUserInfoDetail(param);
		rsMap.put("result", rs);
		return rsMap;
	}
	

	@RequestMapping(value= "/selectUserInfoDetail.do")
	public @ResponseBody Map<String, Object> selectUserInfoDetail(@ModelAttribute Map<String, Object> param , ModelMap model) throws Exception{
		
		Map<String, Object> rsMap = new HashMap<String, Object>();
		Map<String, Object> rs = seniorDtlsInfoService.selectUserInfoDetail(param);
		rsMap.put("result", rs);
		return rsMap;
	}
	
	/**
	 * 기존 유저 전회차 값 불러오기 
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/setMultipleTimes.do")
	public @ResponseBody Map<String, Object> setMultipleTimes(@ModelAttribute Map<String, Object> param , ModelMap model) throws Exception{
		
		Map<String, Object> rsMap = new HashMap<String, Object>();
		Map<String, Object> rs = seniorDtlsInfoService.setMultipleTimes(param);
		rsMap.put("result", rs);
		return rsMap;
	}

	/**
	 * 유저 상세등록 
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/regUserDetail.do")
	public @ResponseBody Map<String, Object> regUserDetail(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		int rsInt = seniorDtlsInfoService.regUserDetail(param);
		int rsSvcInt = seniorDtlsInfoService.updateServiceManageDetail(param);
		int rsFormInt = seniorDtlsInfoService.insertUserFormInfo(param);		
		
		rsMap.put("rsInt", rsInt);
		rsMap.put("rsSvcInt", rsSvcInt);
		rsMap.put("rsFormInt", rsFormInt);
		
		return rsMap;
    }
	
	/**
	 * 개인정보 활용 동의 팝업
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/PersonalInfoPop.do", method= RequestMethod.GET)
	public String PersonalInfoPop(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		return "web/tg/seniorGeneralPop";
	}
}
