package kr.go.mhc.mhcweb.mr.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.mhcweb.mr.service.ConcCnslInfoService;

import kr.go.mhc.mhcweb.sm.service.OrgMngtService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Class Name : ConcCnslInfoController.java
 * @Description : 관리자 WEB에서 사용하는 집중상담 정보 업무를 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일			수정자		수정내용
 * @	----------		------		---------------------------
 * @	2016.09.22		전정은		최초생성
 *
 * @author	gst
 * @since	2016.09.22
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
@RequestMapping(value= "/mr")
public class ConcCnslInfoController extends DMultiActionController {

	@Resource(name= "web.mr.ConcCnslInfoService")
	private ConcCnslInfoService concCnslInfoService;

	@Resource(name = "web.sm.OrgMngtService")
	private OrgMngtService orgMngtService;

	@ModelAttribute
	public Map initDate(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	
	/**
	 * 집중상담 정보 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/concCnslInfo.do", method= RequestMethod.GET)
	public String concCnslInfo(@ModelAttribute Map param, ModelMap model) throws Exception {
		return "web/mr/concCnslInfo";
	}
	
	/**
	 * 집중상담 정보 목록 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/concCnslInfoList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> concCnslInfoList(@ModelAttribute Map<String, Object> param
															 ,				  ModelMap			  model) throws Exception {
		Map<String, Object>		  rsMap  = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = concCnslInfoService.getConcCnslInfoList(param);

		// orgDtls 추가
		param.put("SCH_ORG_CD", param.get("SESS_ORG_CD"));
		List<Map<String, String>> rsOrgDtlsList = orgMngtService.getOrgDtlsList(param);

		rsMap.put("id"	  , param.get("id"));
		rsMap.put("rsList", rsList);
		rsMap.put("rsOrgDtlsList", rsOrgDtlsList);
		
		return rsMap;
	}
}
