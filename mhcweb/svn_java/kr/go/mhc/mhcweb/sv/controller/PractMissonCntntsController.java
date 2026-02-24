package kr.go.mhc.mhcweb.sv.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.mhcweb.sv.service.PractMissonCntntsService;

/**
 * @Class Name : PractMissonCntntsController.java
 * @Description : 관리자 WEB에서 실천미션 콘텐츠를 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2017.04.04		이태석			최초생성
 *
 * @author thejoin
 * @since 2017.04.04
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
@RequestMapping(value = "/sv")
public class PractMissonCntntsController extends DMultiActionController{

	@Resource(name = "web.sv.PractMissonCntntsService")
	private PractMissonCntntsService practMissonCntntsService;
	
	@ModelAttribute
	public Map initData(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	
	/**
	 * 실천미션 콘텐츠 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/practMissonCntnts.do", method = RequestMethod.GET)
	public String practMissonCntnts(@ModelAttribute Map param, ModelMap model) throws Exception {
				
		return "web/sv/practMissonCntnts";
	}
	
	/**
	 * 실천미션 콘텐츠 목록 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/getPractMissonCntntsList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getPractMissonCntntsList(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String, Object>> rsList = practMissonCntntsService.getPractMissonCntntsList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 실천미션 콘텐츠 등록 팝업 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/practMissonCntntsRegPop.do", method = RequestMethod.GET)
	public String practMissonCntntsRegPop(@ModelAttribute Map param, ModelMap model) throws Exception {
		List<Map<String, Object>> rsList = practMissonCntntsService.getPractMissonCntntsList(param);
		model.addAttribute("practMissonList_pop", rsList);
		return "web/sv/practMissonCntntsRegPop";
	}
	
	/**
	 * 실천미션 코드 내용 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/getPractMissonContPop.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getPractMissonContPop(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String, Object>> rsList = practMissonCntntsService.getPractMissonCont(param);
		rsMap.put("rsList", rsList);
		return rsMap;
	}
	
	/**
	 * 실천미션 코드 내용 수정
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/updatePractMissonCont.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> updatePractMissonCont(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		int rsInt = practMissonCntntsService.updatePractMissonCont(param);
		if(rsInt == 0){
			rsMap.put("updateChk", "N");
		}else{
			rsMap.put("updateChk", "Y");			
		}
		return rsMap;
	}
}
