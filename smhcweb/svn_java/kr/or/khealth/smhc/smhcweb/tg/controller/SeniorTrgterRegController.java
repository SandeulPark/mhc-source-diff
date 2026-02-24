package kr.or.khealth.smhc.smhcweb.tg.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.or.khealth.smhc.common.DMultiActionController;
import kr.or.khealth.smhc.smhcweb.tg.service.SeniorTrgterRegService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;



/**
 * @Class Name : DeviceDistrbtController.java
 * @Description : 관리자 WEB에서 사용하는 어르신 대상자 등록을 관리하는 컨트롤러 Class
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
@RequestMapping(value = "/tg")
public class SeniorTrgterRegController extends DMultiActionController{

	@Resource(name="web.tg.SeniorTrgterRegService")
	private SeniorTrgterRegService  seniorTrgterRegService;
	
	@ModelAttribute
	public Map<String, Object> initDate(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	
	/**
	 * 어르신 대상자 등록 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/seniorTrgterRegList.do")
	public String seniorTrgterReg(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		param.put("CMMN_CD","TC_CM_ORG");
		model.addAttribute("REQ_SEARCH_INFO", param.get("REQ_SEARCH_INFO"));
		List<Map<String, String>> selList = cmmnService.selectCmmnCd(param);
		model.addAttribute("selList", selList);
		
		return "web/tg/seniorTrgterRegList";
	}
	
	/**
	 * 어르신 대상자 목록 조회 
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/seniorTrgterList.do")
	public @ResponseBody Map<String, Object> selectSeniorTrgterList(@ModelAttribute Map<String, Object> param , ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = seniorTrgterRegService.selectSeniorTrgterList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}	
	
	/**
	 * 어르신 대상자 상세 조회 
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/seniorTrgterRegDtls.do") 
	public String selectSeniorTrgterDtls(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {				
		Map<String, Object> rsMap = seniorTrgterRegService.selectSeniorTrgterDtls(param);
		String returnUrl = "web/tg/seniorTrgterRegDtls";
		model.addAttribute("rsMap", rsMap);
		return returnUrl;
	}	
	
	/**
	 * 담당자 SELECTBOX 
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/selectManagerCombo.do")
	public @ResponseBody Map<String, Object> selectManagerCombo(@ModelAttribute Map<String, Object> param , ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = seniorTrgterRegService.selectManagerCombo(param);
		rsMap.put("rsList", rsList);
		return rsMap;
	}
	
	/**
	 * 어르신 대상자 수정 화면 호출
	 * @param
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/seniorTrgterUpdatePage.do", method = RequestMethod.POST)
	public String seniorTrgterUpdatePage(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		model.addAllAttributes(param);			
		return "web/tg/seniorTrgterRegUpdate";
	}	
	
	/**
	 * 어르신 수정화면 데이터 조회
	 * @param
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/seniorTrgterUpdateInfo.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> selectSeniorTrgterUpdateInfo(@ModelAttribute Map<String, Object> param , ModelMap model) throws Exception{
		Map<String, Object> rsMap =seniorTrgterRegService.selectSeniorTrgterDtls(param);
		return rsMap;
	}	
	
	/**
	 * 어르신 사용자 신규 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/seniorTrgterInsertPage.do", method = RequestMethod.POST)
	public String seniorTrgterInsertPage(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		model.addAllAttributes(param);			
		return "web/tg/seniorTrgterRegInsert";
	}		
	
	
	/**
	 * 어르신 사용자 신규 등록
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/insertSeniorTrgter.do")
	public @ResponseBody Map<String, Object> insertSeniorTrgter(@ModelAttribute Map param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		int rsInt = seniorTrgterRegService.insertSeniorTrgter(param);
		rsMap.put("rsInt", rsInt);
		return rsMap;
    }
	
	/**
	 * 어르신 사용자 정보 업데이트 
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/updateSeniorTrgter.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> updateSeniorTrgter(@ModelAttribute Map param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		int rsInt = seniorTrgterRegService.updateSeniorTrgter(param);
		rsMap.put("rsInt", rsInt);
		return rsMap;
    }
	
	/**
	 * 중복검사 
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/seniorDuplicationSch.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> selectSeniorDuplicationSch(@ModelAttribute Map param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = seniorTrgterRegService.selectSeniorDuplicationSch(param);	
		return rsMap;
    }	
	
	/**
	 * 핸드폰 번호 중복검사 
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/seniorDuplicationMobileNo.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> selectSeniorDuplicationMobileNo(@ModelAttribute Map param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = seniorTrgterRegService.selectSeniorDuplicationMobileNo(param);	
		return rsMap;
    }	
	
	/**
	 * 어르신 사용자 삭제
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/seniorTrgterDeleteInfo.do")
	public @ResponseBody Map<String, Object> seniorTrgterDeleteInfo(@ModelAttribute Map param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		int rsInt = seniorTrgterRegService.seniorTrgterDeleteInfo(param);
		rsMap.put("rsInt", rsInt);
		return rsMap;
    }
	
	@RequestMapping(value = "/updateLoginFailCnt.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> updateLoginFailCnt(@ModelAttribute Map param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		boolean isSuccess = seniorTrgterRegService.updateLoginFailCnt(param);
		if(isSuccess) seniorTrgterRegService.insertUnlockHist(param);
		return rsMap;
    }
}
