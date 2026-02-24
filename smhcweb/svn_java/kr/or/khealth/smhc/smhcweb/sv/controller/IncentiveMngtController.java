package kr.or.khealth.smhc.smhcweb.sv.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.or.khealth.smhc.common.util.StringUtil;
import kr.or.khealth.smhc.common.DMultiActionController;
import kr.or.khealth.smhc.smhcweb.sv.service.IncentiveMngtService;

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
 * @	2020.10.07		정준호			최초생성
 *
 * @author thejoin
 * @since 2020.10.07
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
@RequestMapping(value = "/sv")
public class IncentiveMngtController extends DMultiActionController{

	@Resource(name="web.sv.IncentiveMngtService")
	private IncentiveMngtService incentiveMngtService;
	
	@ModelAttribute
	public Map<String, Object> initDate(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	
	/**
	 * 인센티브 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/incentiveMngt.do")
	public String missionImplStatus(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		return "web/sv/incentiveMngt";
	}
	
	/**
	 * 인센티브 관리 대상자 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/selectIncentiveTarget.do")
	public @ResponseBody Map<String, Object> selectIncentiveTarget(@ModelAttribute Map<String, Object> param , ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = incentiveMngtService.selectIncentiveTarget(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 인센티브 내역 팝업
	 * @param 
	 * @return 
	 s* @throws Exception 
	 */
	@RequestMapping(value= "/incentiveMngt_pop.do", method= RequestMethod.GET)
	public String incentiveMngt_pop(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		return "web/sv/incentiveLogPop";
	}
	
	/**
	 * 유저 개인 인센티브 내역 조회 팝업
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/searchIncentiveLogPop.do")
	public @ResponseBody Map<String, Object> searchIncentiveLogPop(@ModelAttribute Map<String, Object> param , ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = incentiveMngtService.searchIncentiveLogPop(param);
		rsMap.put("rsList", rsList);
		return rsMap;
	}
	
	/**
	 * 인센티브 내역 팝업
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/incentivePayment_pop.do", method= RequestMethod.GET)
	public String incentivePayment_pop(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		return "web/sv/incentivePaymentPop";
	}
	
	/**
	 * 인센티브 지급
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/paymentPoint.do")
	public @ResponseBody Map<String,Object> paymentPoint(@ModelAttribute Map<String, Object> param, ModelMap model){
		Map<String,Object> rsMap = new HashMap<String,Object>();
		int rsInt = 0;
		
		String UESR_ID_LIST = StringUtil.nvl((String)param.get("UESR_ID"));
		param.put("UESR_ID_LIST", StringUtil.makeStringToIterator(UESR_ID_LIST));
		
		try{
			rsInt = incentiveMngtService.paymentPoint(param);
			rsMap.put("chkYn", "Y");
		}catch(Exception e){
			rsMap.put("chkYn", "N");
		}
		
		return rsMap;
	}
}
