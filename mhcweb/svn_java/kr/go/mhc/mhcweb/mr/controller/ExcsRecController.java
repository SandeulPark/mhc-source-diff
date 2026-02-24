package kr.go.mhc.mhcweb.mr.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.mhcweb.mr.service.ExcsRecService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @Class Name : ExcsRecController.java
 * @Description : 관리자 WEB에서 사용하는 서비스 참여정보 업무를 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.11.10		허광일			최초생성
 *
 * @author gst
 * @since 2016.11.10
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
@RequestMapping(value= "/mr")
public class ExcsRecController extends DMultiActionController {
	
	@Resource(name= "web.mr.ExcsRecService")
	private ExcsRecService excsRecService;
	
	@ModelAttribute
	public Map<String, Object> initDate(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	
	/**
	 * 운동기록 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/excsRecMain.do", method= RequestMethod.GET)
	public String excsRecMain(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		model.addAllAttributes(param);
		return "web/mr/excsRecMain";
	}
	
	/**
	 * 서비스 참여 정보 목록 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/excsRecList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> excsRecList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object>	rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = excsRecService.selectExcsRecList(param);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		model.addAllAttributes(param);
		
		return rsMap;
	}
	
	/**
	 * 운동 기록 팝업 화면 호출
	 */
	@RequestMapping(value= "/excsRecPop.do", method = RequestMethod.GET)
	public String excsRecPop(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		model.addAllAttributes(param);
		return "web/mr/excsRecPop";
	}
	
}