package kr.go.mhc.mhcweb.sv.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.go.mhc.mhcweb.sm.service.OrgMngtService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.mhcweb.sv.service.WeekSchMngtService;


/**
 * @Class Name : WeekSchMngtController.java
 * @Description : 관리자 WEB에서 사용하는 주차별 일정 관리 업무를 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일				수정자			수정내용
 * @	----------		----		---------------------------
 * @	2018.04.10		오샘이			최초생성
 *
 * @author theJoin
 * @since 2018.04.10
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
@RequestMapping(value = "/sv")
public class WeekSchMngtController extends DMultiActionController{
	
	@Resource(name = "web.sv.WeekSchMngtService")
	private WeekSchMngtService weekSchMngtService;

	@Resource(name = "web.sm.OrgMngtService")
	private OrgMngtService orgMngtService;

	@ModelAttribute
	public Map<String,Object> initData(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	
	/**
	 * 주차별 일정 현황 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/weekSchStatusMain.do", method = RequestMethod.GET)
	public String weekSchStatus(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {

		model.addAllAttributes(param);
		return "web/sv/weekSchSttus";
	}	
	
	
	/**
	 * 주차별 일정 관리 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/weekSchMngtMain.do", method = RequestMethod.GET)
	public String weekSchStatusMngtMain(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {

		model.addAllAttributes(param);
		return "web/sv/weekSchMngt";
	}	
		
	
	/**
	 * 주차별 일정 관리화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/weekSchMngt.do", method = RequestMethod.GET)
	public String weekSchMngt(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {

		model.addAllAttributes(param);
		return "web/sv/weekSchMngt";
	}
	
	/**
	 * 주차별 일정 관리화면  목록 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectWeekSchMngtList.do")
	public @ResponseBody Map<String, Object> serviceBeginApList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();

		// orgDtls 추가
		param.put("SCH_ORG_CD", param.get("SESS_ORG_CD"));
		List<Map<String, String>> rsOrgDtlsList = orgMngtService.getOrgDtlsList(param);
		
		// org_dtls 에 데이터 있음.
		if(!rsOrgDtlsList.isEmpty()) {
			param.put("INTENS_CNSL_USE_YN", rsOrgDtlsList.get(0).get("INTENS_CNSL_USE_YN")); // 집중상담 진행 여부 저장
		}

		List<Map<String, String>> rsList = weekSchMngtService.selectWeekSchMngtList(param);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		rsMap.put("rsOrgDtlsList", rsOrgDtlsList);
		return rsMap;
	}
	
	
	
	
	/**
	 * 주차별 일정 대상자 목록 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectWeekSchMngtUserList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> weekSchMngtUserList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();		
		List<Map<String, String>> rsList = weekSchMngtService.selectWeekSchMngtUserList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));	
	
		return rsMap;
	}
	
	
	/**
	 * 주차별 일정관리 화면 변경 전 주차 정보
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectWeekSchMngtBeforeWeekInfo.do")
	public @ResponseBody Map<String, Object> selectWeekSchMngtBeforeWeekInfo(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();

		//List<Map<String, String>> rsList = weekSchMngtService.selectWeekSchMngtWeekList(param);
		
		Map<String, String> beforeMap = weekSchMngtService.selectWeekSchMngtBeforeWeekInfo(param);

		
		rsMap.put("beforeMap", beforeMap);
		//rsMap.put("id", param.get("id"));
		return rsMap;
	}	
	
	/**
	 * 주차별 일정 관리화면 주차 목록 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectWeekSchMngtAfterWeekList.do")
	public @ResponseBody Map<String, Object> selectWeekSchMngtWeekList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, String>> afterList = weekSchMngtService.selectWeekSchMngtAfterWeekList(param);
		rsMap.put("afterList", afterList);
		return rsMap;
	}	
	

	
	/**
	 * 주차별 일정 저장
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/saveWeekSchMngt.do")
	public @ResponseBody Map<String, Object> saveWeekSchMngt(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		
		int rsInt = weekSchMngtService.saveWeekSchMngt(param);		
		rsMap.put("rsInt", rsInt);

		return rsMap;
	}
	
	
	/**
	 * 주차별 일정현황 팝업 호출
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/weekSchSttusPop.do")
	public String weekSchSttusPop(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		model.addAllAttributes(param);
		return "web/sv/weekSchSttusPop";
	}
	

}
