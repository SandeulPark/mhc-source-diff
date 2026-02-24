package kr.go.mhc.mhcweb.tg.controller;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.mhcweb.tg.service.PhisCnctTrgterCurService;
import kr.go.mhc.mhcweb.tg.service.PhisPreTrgterMngtService;
import kr.go.mhc.mhcweb.tg.service.PreTrgterApplyService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * @Class Name : PhisCnctTrgterCurController.java
 * @Description : PHIS 연계 대상자 현황 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2020.02.11		양현우			최초생성
 *
 * @author thejoin
 * @since 2018.04.11
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */
@Controller
@RequestMapping(value = "/tg")
public class PreTrgterApplyController extends DMultiActionController {

	@Resource(name ="web.tg.PreTrgterApplyService")
	private PreTrgterApplyService preTrgterApplyService;
	
	@ModelAttribute
	public Map initData(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	/**
	 * 예비대상자 승인 화면 호출
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/preTrgterApply.do")
	public String preTrgterApply(@ModelAttribute Map param, ModelMap model) throws Exception {
		return "web/tg/preTrgterApply";
	}
	
	/**
	 * 예비 대상자 승인 리스트 호출 
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/preTrgterApplyList.do")
	public @ResponseBody Map<String, Object> preTrgterApplyList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, String>> rsList = preTrgterApplyService.preTrgterApplyList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 예비 대상자 승인 팝업 호출
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/preTrgterApplyPop.do", method= RequestMethod.GET)
	public String preTrgterApplyPop(@ModelAttribute Map param, ModelMap model) throws Exception {
		model.addAttribute("registableYn", param.get("registableYn").toString());
		model.addAttribute("preTrgterSttus", param.get("preTrgterSttus").toString());
		model.addAttribute("approvalIndex", param.get("approvalBtn").toString().substring(12));
		return "web/tg/preTrgterApplyPop";
	}
	
	
	/**
	 * 예비 대상자 승인/반려
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/updateApprovalYn.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> updateApprovalYn(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		int rsInt = preTrgterApplyService.updateApprovalYn(param);
		
		if("Y".equals(String.valueOf(param.get("APPROVAL_YN")))){
			preTrgterDataInsert(param);
		}
		
		rsMap.put("rsInt", rsInt);
		return rsMap;
	}
	
	
	/**
	 * 예비대상자 승인한 대상자 예비대상자로 등록
	 * @param
	 * @return
	 * @throws Exception
	 */
	private void preTrgterDataInsert(@ModelAttribute Map<String, Object> param) throws Exception {
		preTrgterApplyService.preTrgterApplyRegit(param);
		
	}	
}
