package kr.go.mhc.mhcweb.mr.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.mhcweb.mr.service.SvcJoinRateService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Class Name : SvcJoinRateController.java
 * @Description : 관리자 WEB에서 사용하는 서비스 참여율 업무를 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.11.15		이은주			최초생성
 * 
 * @author gst
 * @since 2016.11.15
 * @version 1.0
 * @see
 * 
 * Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
@RequestMapping(value= "/mr")
public class SvcJoinRateController extends DMultiActionController {
	
	@Resource(name= "web.mr.SvcJoinRateService")
	private SvcJoinRateService svcJoinRateService;
	
	@ModelAttribute
	public Map initDate(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	
	/**
	 * 서비스 참여율 화면 호출
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/svcJoinRate.do")
	public String svcJoinRate(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		
		return "web/mr/svcJoinRate";
		
	}
	
	/**
	 * 서비스 참여율 목록 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/svcJoinRateList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> svcJoinRateList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = svcJoinRateService.svcJoinRateList(param);
		
		rsMap.put("id", param.get("id"));
		rsMap.put("rsList", rsList);
		
		return rsMap;
	}
}
