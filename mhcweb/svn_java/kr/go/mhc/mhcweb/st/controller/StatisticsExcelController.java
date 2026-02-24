package kr.go.mhc.mhcweb.st.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.mhcweb.st.service.StatisticsExcelService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Class Name : TrgterSttusController.java
 * @Description : 총괄 관리 시스템 현황 및 통계 메뉴의 대상자현황 하위 메뉴들을 컨트롤하는 컨트롤러
 * @Modification Information
 * @
 * @	수정일				수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.12.21		윤봉훈			최초생성
 *
 * @author gst
 * @since 2016.12.21
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
public class StatisticsExcelController extends DMultiActionController {

	@Resource(name= "web.st.StatisticsExcelService")
	private StatisticsExcelService statisticsExcelService;
	
	@ModelAttribute
	public Map<String,Object> initDate(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}

	@RequestMapping(value="/st/statisticsExcel.do", method=RequestMethod.GET)
	public String statisticsExcel(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		return "web/st/statisticsExcel";
	}
	
	@RequestMapping(value="/st/statisticsExcelPop.do", method=RequestMethod.GET)
	public String statisticsExcelPop(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		return "web/st/statisticsExcelPop";
	}
	
	@RequestMapping(value="/st/statisticsList.do", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> statisticsList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String, Object>();
		List<Map<String,String>> rsList = statisticsExcelService.statisticsList(param);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	@RequestMapping(value="/st/statisticsStndList.do", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> statisticsStndList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String, Object>();
		List<Map<String,String>> rsList = statisticsExcelService.statisticsStndList(param);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	@RequestMapping(value="/st/statisticsInfo.do", method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> statisticsInfo(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String,String>> rsList = statisticsExcelService.statisticsInfo(param);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	@RequestMapping(value="/st/statisticsTableColInfo.do", method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> statisticsTableColInfo(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String,String>> rsList = statisticsExcelService.statisticsTableColInfo(param);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		rsMap.put("TABLE_NM", param.get("TABLE_NM"));
		return rsMap;
	}
	
	@RequestMapping(value="/st/statisticsAgr.do", method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> statisticsAgr(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		String result = "";
		String chkYn = "Y";
		try{
			rsMap.put("STND_DE", param.get("STND_DE"));
			result = statisticsExcelService.CALL_PRC_TM_STATS_ALL_INS(rsMap);
		}catch(Exception e){
			e.printStackTrace();
			chkYn = "N";
		}
		
		rsMap.put("result", result);
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}
	
}
