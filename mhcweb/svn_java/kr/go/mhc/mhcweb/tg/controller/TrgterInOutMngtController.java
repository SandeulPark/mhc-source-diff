package kr.go.mhc.mhcweb.tg.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.mhcweb.tg.service.TrgterInOutMngtService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Class Name : TrgterInOutMngtController.java
 * @Description : 관리자 WEB에서 사용하는 대상자 전입/전출 업무를 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2019.11.06		오샘이			최초생성
 *
 * @author thejoin
 * @since 2019.11.06
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
@RequestMapping(value = "/tg")
public class TrgterInOutMngtController extends DMultiActionController {
	
	@Resource(name= "web.tg.TrgterInOutMngtService")
	private TrgterInOutMngtService trgterInOutMngtService;
 	        

	@ModelAttribute
	public Map<String, Object> initDate(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	
	/**
	 * 대상자 전입/전출 관리 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/trgterInOutMngt.do")
	public String trgterInOutMngt(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		model.addAllAttributes(param);
		return "web/tg/trgterInOutMngt";
	}
	
	/**
	 * 대상자 전입/전출 관리 목록 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/trgterInOutMngtList.do")
	public @ResponseBody Map<String, Object> trgterInOutMngtList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = trgterInOutMngtService.getTrgterInOutMngtList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 대상자 전입/전출 관리 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/trgterInInfoChkPop.do")
	public String trgterInInfoChkPop(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		model.addAllAttributes(param);
		return "web/tg/trgterInInfoChkPop";
	}
	
	
	/**
	 * 대상자 전입 검색 확인
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/trgterInInfoChk.do")
	public @ResponseBody Map<String, Object> trgterInInfoChk(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		rsMap = trgterInOutMngtService.getTrgterInInfoChk(param);

		return rsMap;
	}
	
	
	/**
	 * 대상자 전입 요청 정보 신규 입력
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/interTrgterInReqInfo.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> insertExcsCode(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();

		int rsInt = trgterInOutMngtService.insertTrgterInReqInfo(param);
		rsMap.put("rsInt", rsInt);
		return rsMap;
	}	
	
	
	/**
	 * 대상자 전입 정보 저장
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/updateTrgterInReqInfo.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> updateTrgterInReqInfo(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		int rsInt = trgterInOutMngtService.updateTrgterInReqInfo(param);
		rsMap.put("rsInt", rsInt);
		return rsMap;
	}	
	
	
	/**
	 * 대상자 전입요청 승인 팝업 호출
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/trgterInReqApprovalPop.do", method= RequestMethod.GET)
	public String mngterRegMngtPop(@ModelAttribute Map param, ModelMap model) throws Exception {
		model.addAttribute("approvalIndex", param.get("approvalBtn").toString().substring(12));
		return "web/tg/trgterInReqApprovalPop";		
	}
	
	/**
	 * 대상자 전입 요청 승인 완료 및 반려
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/updateTrgterInApprovalYn.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> updateTrgterInApprovalYn(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		rsMap = trgterInOutMngtService.updateTrgterInApprovalYn(param);
		return rsMap;
	}	
}
