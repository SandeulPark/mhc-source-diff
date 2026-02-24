package kr.go.mhc.mhcweb.sv.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.mhcweb.sv.service.ConcCnslMngtService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Class Name : ConcCnslMngtController.java
 * @Description : 관리자 WEB에서 사용하는 집중상담을 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.09.20		오샘이			최초생성
 *
 * @author gst
 * @since 2016.09.20
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
public class ConcCnslMngtController extends DMultiActionController {

	@Resource(name= "web.sv.ConcCnslMngtService")
	private ConcCnslMngtService ConcCnslMngtService;
	
	@ModelAttribute
	public Map initDate(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}

	/**
	 * 집중상담현황 조회 화면 호출
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/sv/ConcCnslMngtMain.do", method= RequestMethod.GET)
	public String cnslReqMain(@ModelAttribute Map param, ModelMap model) throws Exception {
		System.out.println(":::param:::" + param);				
		
		return "web/sv/concCnsl";
	}
	
	/**
	 * 집중상담현황 조회 리스트
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/sv/concCnslList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> concCnslList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, String>> rsList = ConcCnslMngtService.getConcCnslList(param);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));					
		return rsMap;
	}


}
