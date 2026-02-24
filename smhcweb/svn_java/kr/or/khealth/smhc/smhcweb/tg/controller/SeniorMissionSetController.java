package kr.or.khealth.smhc.smhcweb.tg.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.or.khealth.smhc.common.DMultiActionController;
import kr.or.khealth.smhc.common.util.StringUtil;
import kr.or.khealth.smhc.smhcweb.tg.service.SeniorMissionSetService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Class Name : SeniorMissionSetController.java
 * @Description : 관리자 WEB에서 사용하는 어르신 대면평가정보등록(미션설정) 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2020.10.10		오샘이			최초생성
 *
 * @author theJoin
 * @since 2020.10.10
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
@RequestMapping(value="/tg")
public class SeniorMissionSetController extends DMultiActionController{
	
	@Resource(name = "web.tg.SeniorMissionSetService")
	private SeniorMissionSetService seniorMissionSetService;
	
	@ModelAttribute
	public Map<String, Object> initDate(HttpServletRequest req) throws  Exception{
		return super.initData(req);
	}

	/**
	 * 미션 설정 목록 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/missionSetList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> selectMissionSetList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = seniorMissionSetService.selectMissionSetList(param);		
		rsMap.put("rsList", rsList);
		return rsMap;
	}
	
	/**
	 * 미션 설정 저장
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/insertMissionSet.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> insertSeniorHealthSvrVey(@ModelAttribute Map<String, Object> param , ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		int rsInt = seniorMissionSetService.insertMissionSet(param);		
		rsMap.put("rsInt", rsInt);
		return rsMap;
	}
		
	/**
	 * 사용자 미션 목록 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/selectUserMissionList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> selectUserMissionList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = seniorMissionSetService.selectUserMissionList(param);
		List<Map<String, Object>> ChkList = seniorMissionSetService.selectUserMissionChk(param);
		rsMap.put("SVC_CD", seniorMissionSetService.selectUserSvcMngt(param));
		rsMap.put("rsList", rsList);
		rsMap.put("ChkList", ChkList);
		return rsMap;
	}	
	
	/**
	 * 복약미션 정보 입력 팝업 호출
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/missionSetDrugPop.do", method= RequestMethod.GET, produces="text/html; charset=UTF-8")
	public String mngterRegMngtPop(@ModelAttribute Map param, ModelMap model) throws Exception {
		model.addAttribute("EDIT_YN", param.get("EDIT_YN")); //수정 신규 여부 확인
		model.addAttribute("MODIFY_DRUG", param.get("MODIFY_DRUG")); //복약 미션 관련
		model.addAttribute("MODIFY_CLASS_NM", param.get("MODIFY_CLASS_NM")); //클래스 명
		return "web/tg/seniorMissionSetDrugPop";
	}	
	
	/**
	 * 복약정보 저장 
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/insertSeniorDrugInfo.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> insertSeniorDrugInfo(@ModelAttribute Map<String, Object> param , ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		String drugTime = StringUtil.nvl((String)param.get("DRUG_TIME"));
		if(!"".equals("drugTime")){
			param.put("drugTime", StringUtil.makeStringToIterator(drugTime));
		}
		int rsInt = seniorMissionSetService.insertSeniorDrugInfo(param);		
		rsMap.put("rsInt", rsInt);
		return rsMap;
	}
}
