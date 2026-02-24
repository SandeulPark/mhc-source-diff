package kr.go.mhc.common.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.common.service.GridService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Class Name :GridController.java
 * @Description : 그리드샘플 페이지를 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일				수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.07.05		이태석			최초생성
 *
 * @author gst
 * @since 2016.07.05
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */
@Controller
public class GridController extends DMultiActionController{ 
	@Resource(name="GridService")
	private GridService gridService;

	@ModelAttribute
	public Map initData(HttpServletRequest req) throws Exception{
		return super.initData(req);
	}
	
	/**
	 * 그리드 sample 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */	
	@RequestMapping( value="/gridSample.do", method = RequestMethod.GET)
	public String gridSample(@ModelAttribute Map param, ModelMap model) throws Exception{		
		
		return "web/sample/sampleGrid";   
	}
		
	/**
	 * 그리드 sample 화면 목록 조회
	 * @param param 검색 조건
	 * @return rsMap
	 * @throws Exception 
	 */
	@RequestMapping( value="/gridListSample.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> gridListSample(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		
		Map<String, Object> rsMap = new HashMap<String, Object>();		
		if(param.get("pagingUse").equals("pagingUse")){							
			int gridTotalRowCount = gridService.getGridTotalRowCount();			
			rsMap.put("gridTotalRowCount", gridTotalRowCount);
		}
		List<Map<String,String>> rsList = gridService.getGridDataList(param);		
		rsMap.put("rsList",rsList);				
		rsMap.put("id", param.get("id"));
		rsMap.put("pagingUse", param.get("pagingUse"));
		
		return rsMap;   
	}	
}
