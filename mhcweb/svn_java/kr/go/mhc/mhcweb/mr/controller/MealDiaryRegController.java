package kr.go.mhc.mhcweb.mr.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.common.util.StringUtil;
import kr.go.mhc.mhcweb.mr.service.MealDiaryRegService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Class Name : MealDiaryRegController.java
 * @Description : 관리자 WEB에서 사용하는 식사일기 등록 업무를 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일			수정자		수정내용
 * @	----------		------		---------------------------
 * @	2017.05.11		 		     최초생성
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
@RequestMapping(value= "/mr")
public class MealDiaryRegController extends DMultiActionController {

	@Resource(name= "web.mr.MealDiaryRegService")
	private MealDiaryRegService mealDiaryRegService;
	
	@ModelAttribute
	public Map initDate(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	
	/**
	 * 식사일기 등록 정보 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/mealDiaryReg.do", method= RequestMethod.GET)
	public String mealDiaryReg(@ModelAttribute Map param, ModelMap model) throws Exception {
		return "web/mr/mealDiaryReg";
	}
	
	/**
	 * 식사일기 등록 정보 목록 조회 
 	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/mealDiaryRegList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> mealDiaryRegList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object>		  rsMap  = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = mealDiaryRegService.getMealDiaryRegList(param);
		
		rsMap.put("id"	  , param.get("id"));
		rsMap.put("rsList", rsList);
		
		return rsMap;
	}
	
	/**
	 * 2019.07.18 유준영 추가
	 * 식사일기 등록정보 엑셀 다운로드 조회 
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/mealDiaryExcel.do")
	public @ResponseBody Map<String, Object> getMealDiaryExcel(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		String searchInfo = StringUtil.nvl((String)param.get("USER_ID"));
		if(!"".equals(searchInfo)){
			param.put("searchInfoList", StringUtil.makeStringToIterator(searchInfo));
		}
		List<Map<String, Object>> melaDiaryData = mealDiaryRegService.getMealDiaryExcel(param);
		rsMap.put("rsList",melaDiaryData);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
}
