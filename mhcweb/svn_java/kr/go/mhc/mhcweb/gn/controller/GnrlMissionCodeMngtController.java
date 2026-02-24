package kr.go.mhc.mhcweb.gn.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.mhcweb.gn.service.GnrlMissionCodeMngtService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Class Name : MissionCodeMngtController.java
 * @Description : 관리자 WEB에서 사용하는 미션코드관리 업무를 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.08.11		이은주			최초생성
 *
 * @author gst
 * @since 2016.08.11
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
public class GnrlMissionCodeMngtController extends DMultiActionController{
	
	@Resource(name= "web.gn.GnrlMissionCodeMngtService")
	private GnrlMissionCodeMngtService gnrlMissionCodeMngtService;
	
	@ModelAttribute
	public Map initDate(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	
	/**
	 * 미션코드관리 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/gn/missionCodeMngt.do", method= RequestMethod.GET)
	public String missionCodeMngt(@ModelAttribute Map param, ModelMap model) throws Exception {
		return "web/gn/missionCodeMngt";
	}
	
	/**
	 * 미션코드관리 목록 조회
	 * @param param 검색 조건
	 * @return rsMap
	 * @throws Exception 
	 */
	@RequestMapping(value= "/gn/missionCodeList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> missionCodeList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, String>> rsList = gnrlMissionCodeMngtService.getMissionCodeList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		
		return rsMap;
	}
	
	/**
	 * 신규 미션코드 조회
	 * @param param 검색 조건
	 * @return rsMap
	 * @throws Exception 
	 */
	@RequestMapping(value = "/gn/selectNewMissionCode.do" , method= RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectNewMissionCode( @ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String,Object> rsMap = gnrlMissionCodeMngtService.selectNewMissionCode(param);
		return rsMap;
	}
	
	/**
	 * 미션코드관리 상세 화면 호출 
	 * @param
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/gn/missionCodeReg.do", method= RequestMethod.GET)
	public @ResponseBody Map<String, Object> missionCodeReg(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = gnrlMissionCodeMngtService.getMissionCodeDtls(param);
		return rsMap;
		
	}

	/**
	 * 미션코드관리 저장	
	 * @param param 저장 정보
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/gn/missionCodeInsert.do", method= RequestMethod.POST)
	public void missionCodeInsert(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		gnrlMissionCodeMngtService.getMissionCodeInsert(param);
	}
	
	/**
	 * 미션코드관리 수정	
	 * @param param 저장 정보
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/gn/missionCodeUpdate.do", method= RequestMethod.POST)
	public void getMissionCodeUp(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		gnrlMissionCodeMngtService.getMissionCodeUp(param);
	}
	
	/**
	 * 미션코드관리 삭제
	 * @param param PK 정보
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/gn/missionCodeDel.do", method= RequestMethod.POST)
	public void missionCodeDel(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		
		gnrlMissionCodeMngtService.getMissionCodeDel(param);
		
	}
}
