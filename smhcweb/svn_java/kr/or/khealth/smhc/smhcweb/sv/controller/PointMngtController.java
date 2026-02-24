package kr.or.khealth.smhc.smhcweb.sv.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.or.khealth.smhc.common.DMultiActionController;
import kr.or.khealth.smhc.smhcweb.sv.service.PointMngtService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Class Name : PointRankingController.java
 * @Description : 관리자 WEB에서 사용하는 포인트 및 랭킹 업무를 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일			수정자		수정내용
 * @	----------		------		---------------------------
 * @	2020.09.16		양현우			수정
 *
 * @author thejoin
 * @since 2020.09.16	
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
@RequestMapping(value= "/sv")
public class PointMngtController extends DMultiActionController {

	@Resource(name= "web.sv.PointMngtService")
	private PointMngtService pointMngtService;
	
	@ModelAttribute
	public Map initDate(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	
	/**
	 * 포인트 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/pointMngt.do", method= RequestMethod.GET)
	public String pointRanking(@ModelAttribute Map param, ModelMap model) throws Exception {
		return "web/sv/pointMngt";
	}
	
	/**
	 * 포인트 대상자 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/selectPointMngtList.do")
	public @ResponseBody Map<String, Object> selectPointMngtList(@ModelAttribute Map<String, Object> param , ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = pointMngtService.selectPointMngtList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 포인트 내역 팝업
	 * @param 
	 * @return 
	 s* @throws Exception 
	 */
	@RequestMapping(value= "/pointLogPop.do", method= RequestMethod.GET)
	public String pointLogPop(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		model.addAttribute("USER_ID", param.get("USER_ID"));
		return "web/sv/pointLogPop";
	}
	
	/**
	 * 유저 개인 포인트 내역 조회 팝업
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/searchPointLogPop.do")
	public @ResponseBody Map<String, Object> searchPointLogPop(@ModelAttribute Map<String, Object> param , ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = pointMngtService.searchPointLogPop(param);
		rsMap.put("rsList", rsList);
		return rsMap;
	}
	
}