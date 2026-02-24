package kr.go.mhc.mhcweb.tg.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.mhcweb.tg.service.PhisCnctTrgterCurService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
public class PhisCnctTrgterCurController extends DMultiActionController {

	@Resource(name ="web.tg.PhisCnctTrgterCurService")
	private PhisCnctTrgterCurService phisCnctTrgterCurService;
	
	@ModelAttribute
	public Map initData(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	/**
	 * 연계 대상자 등록 현황 조회 화면 호출
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/phisCnctTrgterCur.do")
	public String phisCnctTrgterCur(@ModelAttribute Map param, ModelMap model) throws Exception {
		return "web/tg/phisCnctTrgterCur";
	}
	/**
	 * 연계 대상자 등록 현황 수 
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/phisCnctTrgterCurCount.do")
	public @ResponseBody Map<String, Object> phisCnctTrgterCurCount(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, String>> rsList = phisCnctTrgterCurService.phisCnctTrgterCurCount(param);
		rsMap.put("rsList", rsList);
		return rsMap;
	}
	
	/**
	 * 연계 대상자 등록 현황 대상자 리스트 
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/phisCnctTrgterCurTrgterList.do")
	public @ResponseBody Map<String, Object> phisCnctTrgterCurTrgterList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, String>> rsList = phisCnctTrgterCurService.phisCnctTrgterCurTrgterList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 연계 대상자 등록 현황 대상자 건강 검진 리스트 
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/phisCnctTrgterCurPop.do")
	public String phisCnctTrgterCurTrgterExamList(@ModelAttribute Map param, ModelMap model) throws Exception {
		return "web/tg/phisCnctTrgterCurPop";
	}
	
	/**
	 * 연계 대상자 등록 검진 정보  
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/phisCnctTrgterCurPopTrgterExamList.do")
	public @ResponseBody Map<String, Object> phisCnctTrgterCurPopTrgterExamList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, String>> rsList = phisCnctTrgterCurService.phisCnctTrgterCurPopTrgterExamList(param);
		rsMap.put("rsList", rsList);
		return rsMap;
	}
	
	
	
}
