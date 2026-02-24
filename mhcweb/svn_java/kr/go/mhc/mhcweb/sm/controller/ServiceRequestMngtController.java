package kr.go.mhc.mhcweb.sm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.mhcweb.sm.service.ServiceRequestMngtService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Class Name : ServiceRequestMngtController.java
 * @Description : 운영처리 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일			수정자		수정내용
 * @	----------		------		---------------------------
 * @	2017.03.30		이현규		최초생성
 * @author theJoin
 * @since 2017.03.30
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
@RequestMapping(value = "/sm")
public class ServiceRequestMngtController extends DMultiActionController {

	@Resource(name = "web.sm.ServiceRequestMngtService")
	private ServiceRequestMngtService serviceRequestMngtService;
	
	@ModelAttribute
	public Map initData(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	
	/**
	 * 운영처리 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/serviceRequestMngt.do")
	public String boardQna(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		param.put("CMMN_CD","TC_CM_ORG");
		List<Map<String, String>> selList = cmmnService.selectCmmnCd(param);
		model.addAttribute("selList", selList);
		return "web/sm/serviceRequestMngt";
	}
	
	/**
	 * 운영처리 목록 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/serviceRequestList.do")
	public @ResponseBody Map<String, Object> serviceRequestList(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {
		
		Map<String, Object> rsMap = new HashMap<String, Object>();
		if (param.get("pagingSet[gridRowsPerPage]") != null) {
			int gridTotalRowCount = serviceRequestMngtService.getServiceRequestListCount(param);
			rsMap.put("gridTotalRowCount", gridTotalRowCount);
		}
		
		List<Map<String, Object>> rsList = serviceRequestMngtService.getServiceRequestList(param);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		
		return rsMap;
	}
	
	/**
	 * 운영처리 신규 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/SRReg.do")
	public String SRReg(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		param.put("CMMN_CD","TC_CM_ORG");
		List<Map<String, String>> selList = cmmnService.selectCmmnCd(param);
		List<String> mngtList = serviceRequestMngtService.selectMngtList();
		model.addAttribute("selList", selList);
		model.addAttribute("mngtList", mngtList);
		return "web/sm/serviceRequestMngtDtls";
	}
	
	/**
	 * 운영처리 저장
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/saveSRMngt.do")
	public String saveSRMngt(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {
		
		serviceRequestMngtService.saveServiceRequestMngt(param);
		
		return "redirect:../pageNavi.do?menuCd=NCM332";
	}
	
	/**
	 * 운영처리 신규 상세화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/SRDtls.do")
	public String SRDtls(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = serviceRequestMngtService.getServiceRequestDtls(param);
		param.put("CMMN_CD","TC_CM_ORG");
		List<Map<String, String>> selList = cmmnService.selectCmmnCd(param);
		List<String> mngtList = serviceRequestMngtService.selectMngtList();
		model.addAttribute("selList", selList);
		model.addAttribute("rsMap",rsMap);
		model.addAttribute("mngtList", mngtList);
		model.addAllAttributes(param);
		return "web/sm/serviceRequestMngtDtls";
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
		List<Map<String, Object>> rsList = serviceRequestMngtService.getTrgtMenuCombo(param);
		
		rsMap.put("rsList", rsList);
		
		return rsMap;
	}
	
	/**
	 * 운영처리 엑셀 목록 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/serviceRequestExcelList.do")
	public @ResponseBody Map<String, Object> serviceRequestExcelList(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {
		
		Map<String, Object> rsMap = new HashMap<String, Object>();
		
		List<Map<String, Object>> rsList = serviceRequestMngtService.getServiceRequestExcelList(param);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		
		return rsMap;
	}
	
	/**
	 * 운영처리 삭제
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/delSRMngt.do")
	public String delSRMngt(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {
		
		serviceRequestMngtService.delServiceRequest(param);
		
		return "redirect:../pageNavi.do?menuCd=NCM332";
	}
	
	/**
	 * 기관 검색 팝업 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/orgSerch_pop.do", method = RequestMethod.GET)
	public String duplicationChk_pop(@ModelAttribute Map param, ModelMap model) throws Exception {
		return "web/sm/serviceRequestMngtDtlsPop";
	}
	
	/**
	 *  기관 검색 팝업 기관 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/orgChkList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> orgChkList(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String, String>> rsList = serviceRequestMngtService.getOrgChkList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		
		return rsMap;
	}
}