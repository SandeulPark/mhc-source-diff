package kr.go.mhc.mhcweb.pm.controller;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.common.util.StringUtil;
import kr.go.mhc.mhcweb.pm.service.TrgterProcSttusService;
import kr.go.mhc.mhcweb.sm.service.OrgMngtService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Class Name : trgterProcSttus.java
 * @Description
 * @Modification Information
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
@RequestMapping(value= "/pm")
public class TrgterProcSttusController extends DMultiActionController {

	@Resource(name= "web.pm.TrgterProcSttusService")
	private TrgterProcSttusService trgterProcSttusService;

	@Resource(name = "web.sm.OrgMngtService")
	private OrgMngtService orgMngtService;

	@ModelAttribute
	public Map<String, Object> initDate(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}

	/**
	 * 대상자 진행 현황 메인 화면 호출
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/trgterProcSttusMain.do", method= RequestMethod.GET)
	public String trgterProcSttusMain(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		param.put("CMMN_CD","TC_CM_ORG");
		List<Map<String, String>> selList = cmmnService.selectCmmnCd(param);
		model.addAllAttributes(param);
		model.addAttribute("selList", selList);
		return "web/pm/trgterProcSttus";
	}

	/**
	 * 대상자 진행 현황 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/trgterProcSttusList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> selectTrgterProcSttusList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object>	rsMap = new HashMap<String, Object>();

		//일반
		List<Map<String, Object>> rsList = trgterProcSttusService.selectTrgterProcSttusList(param);
		// orgDtls 추가
		param.put("SCH_ORG_CD", param.get("SESS_ORG_CD"));
		List<Map<String, String>> rsOrgDtlsList = orgMngtService.getOrgDtlsList(param);

		System.out.println("param :::::::::: " + param);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		rsMap.put("rsOrgDtlsList", rsOrgDtlsList);
		model.addAllAttributes(param);
		
		return rsMap;
	}

	/**
	 * 추후관리 서비스 개시일 지정 팝업 화면 호출
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/trgterProcSttusPop.do", method= RequestMethod.GET)
	public String trgterProcSttusPop(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		
		model.addAllAttributes(param);
		return "web/pm/trgterProcSttusPop";
	}

	/**
	 * 추후관리 스케줄 생성
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception svcbgnappoint
	 */
	@RequestMapping(value="/trgterProcSttusAfterSchdule.do")
	public @ResponseBody Map<String, Object> createAfterSchdule(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = new ArrayList<Map<String, Object>>();
		String chkYn = "Y";
		try{
			trgterProcSttusService.trgterProcSttusAfterSchedule(param);
		}catch(Exception e){
			e.printStackTrace();
			chkYn = "N";
		}
		rsMap.put("rsList", rsList);
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}


	/**
	 * 추후관리 스케줄 생성 후 설문지 마스터 업데이트
	 * @param param 검색 조건
	 * @return
	 * @throws Exception
	 */
	@RequestMapping( value="/updateTrgterProcSttus.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> updateTrgterProcSttus(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();

		int rsList = trgterProcSttusService.updateTrgterProcSttus(param);

		rsMap.put("rsList", rsList);
		return rsMap;
	}

}