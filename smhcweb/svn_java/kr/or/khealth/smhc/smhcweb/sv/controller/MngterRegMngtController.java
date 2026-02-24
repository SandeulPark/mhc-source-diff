package kr.or.khealth.smhc.smhcweb.sv.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.or.khealth.smhc.common.DMultiActionController;
import kr.or.khealth.smhc.smhcweb.sv.service.MngterRegMngtService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @Class Name : MngterRegMngtController.java
 * @Description : 관리자 WEB에서 사용하는 관리자 등록 관리를 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2020.09.22		양현우			최초생성
 *
 * @author thejoin
 * @since 2020.09.22
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
@RequestMapping(value = "/sv")
public class MngterRegMngtController extends DMultiActionController{
	
	@Resource(name = "web.sv.MngterRegMngtService")
	private MngterRegMngtService mngterRegMngtService;
	
	@ModelAttribute
	public Map<String, Object> initDate(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	
	/**
	 * 관리자 등록 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/mngterRegMngt.do")
	public String mngterRegMngt(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		param.put("CMMN_CD","TC_CM_ORG");
		model.addAttribute("REQ_SEARCH_INFO", param.get("REQ_SEARCH_INFO"));
		List<Map<String, String>> selList = cmmnService.selectCmmnCd(param);
		model.addAttribute("selList", selList);
		
		return "web/sv/mngterRegMngt";
	}
	
	/**
	 * 등록된 관리자 정보 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/selectMngterRegMngtList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> selectMngterRegMngtList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, String>> rsList = mngterRegMngtService.selectMngterRegMngtList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 관리자 등록 및 (승인 필요한것)수정 
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/saveManagerInfo.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> saveManagerInfo(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		int rsInt = mngterRegMngtService.saveManagerInfo(param);
		rsMap.put("rsInt", rsInt);
		return rsMap;
	}
	
	/**
	 * 관리자 공인인증서1 제한 해제
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/updatedn1Use.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> updatedn1Use(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		int rsInt = mngterRegMngtService.updatedn1Use(param);
		rsMap.put("rsInt", rsInt);
		return rsMap;
	}
	
	/**
	 * 관리자 공인인증서2 제한 해제
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/updatedn2Use.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> updatedn2Use(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		int rsInt = mngterRegMngtService.updatedn2Use(param);
		rsMap.put("rsInt", rsInt);
		return rsMap;
	}
	
	/**
	 * 관리자 등록 승인 팝업 호출
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/mngterRegMngtPop.do", method= RequestMethod.GET)
	public String mngterRegMngtPop(@ModelAttribute Map param, ModelMap model) throws Exception {
		model.addAttribute("approvalIndex", param.get("approvalBtn").toString().substring(12));
		return "web/sv/mngterRegMngtPop";
	}
	
	/**
	 * 관리자 등록 승인
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/updateApprovalYn.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> updateApprovalYn(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		int rsInt = mngterRegMngtService.updateApprovalYn(param);
		rsMap.put("rsInt", rsInt);
		return rsMap;
	}
	
	/**
	 *  관리자 중복 체크
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/managerDuplicationCnt.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getManagerDuplicationCnt(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = mngterRegMngtService.getManagerDuplicationCnt(param);
						
		return rsMap;
	}

	/**
	 * CSR 등록 관리 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/serviceRequestMngt.do")
	public String serviceRequestMngt(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		param.put("CMMN_CD","TC_CM_ORG");
		List<Map<String, String>> selList = cmmnService.selectCmmnCd(param);
		model.addAttribute("selList", selList);
		return "web/sv/csrMngt";
	}

	/**
	 * CSR 목록 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/serviceRequestList.do")
	public @ResponseBody Map<String, Object> serviceRequestList(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		if (param.get("pagingSet[gridRowsPerPage]") != null) {
			int gridTotalRowCount = mngterRegMngtService.getServiceRequestListCount(param);
			rsMap.put("gridTotalRowCount", gridTotalRowCount);
		}
		List<Map<String, Object>> rsList = mngterRegMngtService.getServiceRequestList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}

	/**
	 * CSR 처리 엑셀 목록 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/serviceRequestExcelList.do")
	public @ResponseBody Map<String, Object> serviceRequestExcelList(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {
		
		Map<String, Object> rsMap = new HashMap<String, Object>();
		
		List<Map<String, Object>> rsList = mngterRegMngtService.getServiceRequestExcelList(param);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		
		return rsMap;
	}

	/**
	 * CSR 처리 신규 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/SRReg.do")
	public String SRReg(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		param.put("CMMN_CD","TC_CM_ORG");
		List<Map<String, String>> selList = cmmnService.selectCmmnCd(param);
		List<Map<String, String>> mngtList = mngterRegMngtService.selectMngtList();
		model.addAttribute("selList", selList);
		model.addAttribute("mngtList", mngtList);
		return "web/sv/csrMngtDtls";
	}

	/**
	 * 대상메뉴 콤보 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/getTrgtMenuCombo.do")
	public @ResponseBody Map<String, Object> getTrgtMenuCombo(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = mngterRegMngtService.getTrgtMenuCombo(param);
		rsMap.put("rsList", rsList);
		return rsMap;
	}

	/**
	 * CSR 처리 저장
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/saveSRMngt.do")
	public String saveSRMngt(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {
		mngterRegMngtService.saveServiceRequestMngt(param);
		return "redirect:../pageNavi.do?menuCd=SSV143";
	}

	/**
	 * CSR 처리 신규 상세화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/SRDtls.do")
	public String SRDtls(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = mngterRegMngtService.getServiceRequestDtls(param);
		param.put("CMMN_CD","TC_CM_ORG");
		List<Map<String, String>> selList = cmmnService.selectCmmnCd(param);
		List<Map<String, String>> mngtList = mngterRegMngtService.selectMngtList();
		model.addAttribute("selList", selList);
		model.addAttribute("rsMap",rsMap);
		model.addAttribute("mngtList", mngtList);
		model.addAllAttributes(param);
		return "web/sv/csrMngtDtls";
	}

	/**
	 * CSR 처리 삭제
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/delSRMngt.do")
	public String delSRMngt(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {
		mngterRegMngtService.delServiceRequest(param);
		return "redirect:../pageNavi.do?menuCd=SSV143";
	}
}
