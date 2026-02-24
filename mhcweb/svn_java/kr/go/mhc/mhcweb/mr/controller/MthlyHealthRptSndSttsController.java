package kr.go.mhc.mhcweb.mr.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.mhcweb.mr.service.MthlyHealthRptSndSttsService;
import kr.go.mhc.mhcweb.sv.service.NoticeSetMngService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Class Name : MthlyHealthRptSndSttsController.java
 * @Description : 관리자 WEB에서 사용하는 월간 건강리포트 발송현황 업무를 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일			수정자		수정내용
 * @	----------		------		---------------------------
 * @	2016.09.20		전정은		최초생성
 *
 * @author	gst
 * @since	2016.09.20
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
@RequestMapping(value= "/mr")
public class MthlyHealthRptSndSttsController extends DMultiActionController {

	@Resource(name= "web.mr.MthlyHealthRptSndSttsService")
	private MthlyHealthRptSndSttsService mthlyHealthRptSndSttsService;
	
	@Resource(name = "web.sv.NoticeSetMngService")
	private NoticeSetMngService noticeSetMngService;
	
	@ModelAttribute
	public Map initDate(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	
	/**
	 * 월간 건강리포트 발송현황 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/mthlyHealthRptSndStts.do", method= RequestMethod.GET)
	public String mthlyHealthRptSndStts(@ModelAttribute Map 	 param
									   ,				ModelMap model) throws Exception {
		return "web/mr/mthlyHealthRptSndStts";
	}
	
	/**
	 * 월간 건강리포트 발송현황 목록 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/mthlyHealthRptSndSttsList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> mthlyHealthRptSndSttsList(@ModelAttribute Map<String, Object> param
																	  ,				   ModelMap			   model) throws Exception {
		Map<String, Object>		  rsMap  = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = mthlyHealthRptSndSttsService.getMthlyHealthRptSndSttsList(param);
		
		rsMap.put("id"	  , param.get("id"));
		rsMap.put("rsList", rsList);
		
		return rsMap;
	}

}
