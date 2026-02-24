package kr.go.mhc.mhcweb.sm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.mhcweb.sm.service.OrgMngtService;

/**
 * @Class Name : OrgMngtController.java
 * @Description : 기관관리 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일			수정자		수정내용
 * @	----------		------		---------------------------
 * @	2017.04.12		이현규		최초생성
 * @author theJoin
 * @since 2017.04.12
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
@RequestMapping(value = "/sm")
public class OrgMngtController extends DMultiActionController {
	
	@Resource(name = "web.sm.OrgMngtService")
	private OrgMngtService orgMngtService;

	@ModelAttribute
	public Map initData(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	
	/**
	 * 기관관리 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/orgMngt.do")
	public String orgMngt(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		return "web/sm/orgMngt";
	}
	
	/**
	 * 기관관리 기관 정보 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/getOrgMngtList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> getOrgMngtList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, String>> rsList = orgMngtService.getOrgMngtList(param);
		List<Map<String, String>> rsOrgDtlsList = orgMngtService.getOrgDtlsList(param); // 자가관리군 집중상담/중간검진 진행 여부
		rsMap.put("rsList", rsList);
		rsMap.put("rsOrgDtlsList", rsOrgDtlsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 기관 등록 및 수정
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/saveOrgMngt.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> saveOrgMngt(HttpSession session, @ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		int rsInt = orgMngtService.saveOrgMngt(param);

		// 자가관리군 집중상담/중간검진 진행 여부 저장시 세션 저장
		if(param.get("SAVE_FLAG").toString().equals("true")) {
			session.setAttribute("SESS_INTENS_CNSL_USE_YN", param.get("INTENS_CNSL_USE_YN").toString());
			session.setAttribute("SESS_MID_EXAM_USE_YN", param.get("MID_EXAM_USE_YN").toString());
		}

		rsMap.put("rsInt", rsInt);
		return rsMap;
	}
	//20191203 양현우 추가
	/**
	 * 상담진행순서 변경 팝업
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/selectOrgMngtPop.do",method=RequestMethod.GET)
	public String selectOrgMngtPop(@ModelAttribute Map param,ModelMap model) throws Exception{
		List<Map<String, Object>> rsList = orgMngtService.selectOrgMngtPop(param);
		model.addAllAttributes(param);
		model.addAttribute("rsList", rsList);
		return "web/sm/orgMngt_pop";
	}
	//20191203 양현우 추가 끝
	
	/**
	 * 기관,관리자 등록 및 수정
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	
	@RequestMapping(value= "/saveMngt.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> saveMngt(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		Map<String, Object> rsMap2 = new HashMap<String, Object>();
		try {
			int rsInt = orgMngtService.saveOrgMngter(param); //기관등록
			rsMap.put("rsInt2", param.get("rsInt2"));
			rsMap.put("rsInt", rsInt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rsMap;
	}
	
	/**
	 * 관리자,기관 등록 승인 팝업 호출
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/orgMngtPop.do", method= RequestMethod.GET)
	public String mngterRegMngtPop(@ModelAttribute Map param, ModelMap model) throws Exception {
		model.addAttribute("approvalIndex", param.get("approvalBtn").toString().substring(12));
		return "web/sm/orgMngtPop";
	}
	
	/**
	 * 관리자 등록 승인
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/updateOrgApprovalYn.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> updateApprovalYn(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		int rsInt = orgMngtService.updateOrgApprovalYn(param);
		rsMap.put("rsInt", rsInt);
		return rsMap;
	}

	/**
	 * 보건소 기관 콤보박스
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/selectOrgList.do")
	public @ResponseBody List<Map<String, String>> selectOrgList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		List<Map<String, String>> rsList = orgMngtService.selectOrgList(param);
		return rsList;
	}



}
