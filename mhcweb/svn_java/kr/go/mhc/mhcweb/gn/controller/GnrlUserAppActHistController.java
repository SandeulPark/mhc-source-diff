package kr.go.mhc.mhcweb.gn.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.mhcweb.gn.service.GnrlUserAppActHistService;
import kr.go.mhc.mhcweb.gn.service.GnrlUserInfoMngtService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;





/**
 * @Class Name : GnrlUserInfoMngtController.java
 * @Description : 일반대상자 정보조회 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일			수정자		수정내용
 * @	----------		------		---------------------------
 * @	2019.11.22					최초생성
 * @author theJoin
 * @since 
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */
@Controller
@RequestMapping(value = "/gn")
public class GnrlUserAppActHistController extends DMultiActionController{

	@Resource(name = "web.gn.GnrlUserAppActHistService")
	private GnrlUserAppActHistService gnrlUserAppActHistService;
	
	@ModelAttribute
	public Map initData(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	
	/**
	 * 대상자 앱 활동기록 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value="/trgtAppActHist.do")
	public String trgtAppActHist(@ModelAttribute Map<String,Object> param , ModelMap model) throws Exception{
		return "web/gn/trgtAppActHist";
	}
	
	/**
	 * 대상자 앱 활동기록 리스트 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/trgtAppActList.do")
	public @ResponseBody Map<String, Object> trgtAppActList(@ModelAttribute Map<String,Object> param , ModelMap model)throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		if (param.get("pagingSet[gridRowsPerPage]") != null) {
			int gridTotalRowCount = gnrlUserAppActHistService.trgtAppActListCount(param);
			rsMap.put("gridTotalRowCount", gridTotalRowCount);
		}
		
		List<Map<String, Object>> rsList = gnrlUserAppActHistService.trgtAppActList(param);
		rsMap.put("rsList",rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	
}