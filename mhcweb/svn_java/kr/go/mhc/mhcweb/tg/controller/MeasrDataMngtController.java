package kr.go.mhc.mhcweb.tg.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.common.util.StringUtil;
import kr.go.mhc.mhcweb.tg.service.MeasrDataMngtService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Class Name : MeasrDataMngtController.java
 * @Description : 관리자 WEB의 측정 데이터를 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2017.02.20		이태석			최초생성
 *
 * @author thejoin
 * @since 2017.02.20
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
@RequestMapping(value = "/tg")
public class MeasrDataMngtController extends DMultiActionController {

	@Resource(name = "web.tg.MeasrDataMngtService")
	private MeasrDataMngtService measrDataMngtService;
	
	@ModelAttribute
	public Map initData(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	
	/**
	 * 체성분 데이터 관리 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/bodyCompDataMngt.do", method = RequestMethod.GET)
	public String bodyCompDataMngt(@ModelAttribute Map param, ModelMap model) throws Exception {

		return "web/tg/bodyCompDataMngt";
	}
	
	/**
	 * 혈당 데이터 관리 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/bloodSugarDataMngt.do", method = RequestMethod.GET)
	public String bloodSugarDataMngt(@ModelAttribute Map param, ModelMap model) throws Exception {
		
		return "web/tg/bloodSugarDataMngt";
	}
	
	/**
	 * 대상자 중복 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/trgterDuplicationChkList.do", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, String>> trgterDuplicationChkList(@ModelAttribute Map param, ModelMap model) throws Exception {
		
		List<Map<String, String>> rsList = measrDataMngtService.getTrgterDuplicationChkList(param);
		
		return rsList;
	}
	
	/**
	 * 대상자 중복 체크 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/trgterDuplicationChkPop.do", method = RequestMethod.GET)
	public String trgterDuplicationChkPop(@ModelAttribute Map param, ModelMap model) throws Exception {
		
		return "web/tg/trgterDuplicationChkPop";
	}
	
	/**
	 * 대상자 체성분 데이터 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/trgterBodyCompDataList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getTrgterBodyCompDataList(@ModelAttribute Map param, ModelMap model) throws Exception {
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String, String>> rsList = measrDataMngtService.getTrgterBodyCompDataList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 대상자 혈당 데이터 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/trgterBloodSugarDataList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getTrgterBloodSugarDataList(@ModelAttribute Map param, ModelMap model) throws Exception {
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String, String>> rsList = measrDataMngtService.getTrgterBloodSugarDataList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 대상자 체성분 데이터 백업 및 삭제
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/bodyCompDataDel.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getBodyCompDataDel(@ModelAttribute Map param, ModelMap model) throws Exception {
		Map<String,Object> rsMap = new HashMap<String,Object>();
		String selDataMeasrSn = StringUtil.nvl((String)param.get("selDataMeasrSn"));
		if(!"".equals(selDataMeasrSn)){
			param.put("selDataMeasrSn", StringUtil.makeStringToIterator(selDataMeasrSn));
		}
		List<Map<String, String>> rsList = measrDataMngtService.getBodyCompDataDel(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 대상자 혈당 데이터 수정
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/bloodSugarDataUp.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getBloodSugarDataUp(@ModelAttribute Map param, ModelMap model) throws Exception {
		Map<String,Object> rsMap = new HashMap<String,Object>();
		String selDataMeasrSn = StringUtil.nvl((String)param.get("selDataMeasrSn"));
		if(!"".equals(selDataMeasrSn)){
			param.put("selDataMeasrSn", StringUtil.makeStringToIterator(selDataMeasrSn));
		}
		List<Map<String, String>> rsList = measrDataMngtService.getBloodSugarDataUp(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
}
