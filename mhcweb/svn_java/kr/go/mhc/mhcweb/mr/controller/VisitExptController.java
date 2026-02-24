package kr.go.mhc.mhcweb.mr.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.go.mhc.common.util.StringUtil;
import kr.go.mhc.mhcweb.sm.service.OrgMngtService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.mhcweb.mr.service.VisitExptService;

/**
 * @Class Name : VisitExptController.java
 * @Description : 관리자 WEB에서 사용하는 방문예정 업무를 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일			수정자		수정내용
 * @	----------		-----		---------------------------
 * @	2016.10.21		이은주		최초생성
 *
 * @author gst
 * @since 2016.10.21
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
@RequestMapping(value = "/mr")
public class VisitExptController extends DMultiActionController {

	@Resource(name = "web.mr.VisitExptService")
	private VisitExptService visitExptService;

	@Resource(name = "web.sm.OrgMngtService")
	private OrgMngtService orgMngtService;

	@ModelAttribute
	public Map initData(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	
	/**
	 * 방문예정 화면 호출
	 * @param
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/visitExpt.do")
	public String visitExpt(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {
		
		return "web/mr/visitExpt";

	}
	
	/**
	 * 방문예정 목록 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/visitExptList.do")
	public @ResponseBody Map<String, Object> visitExptList(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();

		// orgDtls 추가
		param.put("SCH_ORG_CD", param.get("SESS_ORG_CD"));

		// 검색조건 년도 안 넘어오면 올해 값 넣어주기
		if("".equals(StringUtil.nvl(String.valueOf(param.get("TRGT_YY"))))) {
			param.put("THIS_YEAR", "Y"); // 올해
		}
		List<Map<String, String>> rsOrgDtlsList = orgMngtService.getOrgDtlsList(param);

		// org_dtls 에 데이터 있음.
		if(!rsOrgDtlsList.isEmpty()) {
			param.put("MID_EXAM_USE_YN", rsOrgDtlsList.get(0).get("MID_EXAM_USE_YN")); // 중간검진 진행 여부 저장
		}

		List<Map<String, Object>> rsList = visitExptService.visitExptList(param);

		rsMap.put("rsList", rsList);
		rsMap.put("rsOrgDtlsList", rsOrgDtlsList);
		rsMap.put("id", param.get("id"));
		
		return rsMap;
	}
	
	/**
	 * 방문 일정 예약현황 화면
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/visitSchSttHis.do", method= RequestMethod.GET)
	public String visitSchSttHis(@ModelAttribute Map<String,Object> param,	ModelMap model) throws Exception {
		
		param.put("CMMN_CD","TC_CM_ORG");
		List<Map<String, String>> selList = cmmnService.selectCmmnCd(param);
		model.addAttribute("selList", selList);		
		
		return "web/mr/visitSchSttHis";
	}
	
	@RequestMapping(value="/selectVisitAllList.do")
	public @ResponseBody Map<String, Object> selectVisitAllList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = visitExptService.selectVisitAllList(param);
		
		rsMap.put("rsList", rsList);
		rsMap.putAll(param);
		return rsMap;
	}
	
	@RequestMapping(value="/resetVisitAllList.do")
	public @ResponseBody Map<String, Object> resetVisitAllList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List sndList = new ArrayList<String>();
		
		System.out.println("updateFlag ===> " + param.get("updateFlag"));
		
		List<Map<String, Object>> rsList = visitExptService.selectVisitAllList(param);
		
		for(int i=0; i<rsList.size(); i++) {
			if(rsList.get(i).get("SND_SN") != null) {
				sndList.add(rsList.get(i).get("SND_SN"));
			}
		}
		for(int i=0; i<sndList.size(); i++) {
			param.put("SND_SN", sndList.get(i));
			param.put("PUSH_FLAG", "Y");
			visitExptService.updateVisitList(param);
		}
		rsMap.put("rsList", rsList);
		rsMap.putAll(param);
		return rsMap;
	}
	
	@RequestMapping( value="/selectVisitAllListCnt.do", method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectVisitAllListCnt(@ModelAttribute Map<String, Object> param, ModelMap model){
		Map<String,Object> rsMap = new HashMap<String,Object>();
		try {
			 Calendar calendar = Calendar.getInstance();
			 int year = calendar.get(Calendar.YEAR);
			 param.put("TRGT_YY", year);
			 param.put("updateFlag", "Y");
			List<Map<String,Object>> selectVisitAllListCnt = visitExptService.selectVisitAllList(param);
			rsMap.put("selectVisitAllListCnt", selectVisitAllListCnt);
			rsMap.put("chkYn", "Y");
		} catch (Exception e) {
			rsMap.put("chkYn", "N");
		}
		return rsMap;
	}
}
