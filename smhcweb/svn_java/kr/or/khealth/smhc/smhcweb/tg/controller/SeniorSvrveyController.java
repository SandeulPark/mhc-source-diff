package kr.or.khealth.smhc.smhcweb.tg.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.or.khealth.smhc.common.DMultiActionController;
import kr.or.khealth.smhc.smhcweb.tg.service.SeniorSvrveyService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Class Name : SeniorSvrveyController.java
 * @Description : 관리자 WEB에서 사용하는 어르신 대면평가정보등록(면접조사지) 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2020.09.28		양현우			최초생성
 *
 * @author thejoin
 * @since 2020.09.28
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
@RequestMapping(value="/tg")
public class SeniorSvrveyController extends DMultiActionController{
	
	@Resource(name = "web.tg.SeniorSvrveyService")
	private SeniorSvrveyService seniorSvrveyService;
	
	@ModelAttribute
	public Map<String, Object> initDate(HttpServletRequest req) throws  Exception{
		return super.initData(req);
	}

	/**
	 * 면접조사지 설문  조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/selectSeniorSvrVeyList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> selectSeniorSvrVeyList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = seniorSvrveyService.selectSeniorSvrVeyList(param);
		rsMap.put("rsList", rsList);
		System.out.println("test");
		return rsMap;
	}
	
	/**
	 * 면접조사지 답변 insert
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/insertSeniorHealthSvrVey.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> insertSeniorHealthSvrVey(@ModelAttribute Map<String, Object> param , ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		int rsInt = seniorSvrveyService.insertSeniorHealthSvrVey(param);
		rsMap.put("rsInt", rsInt);
		return rsMap;
	}
	
	/**
	 * 면접조사지 답변 upd
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/updSeniorHealthSvrVey.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> updSeniorHealthSvrVey(@ModelAttribute Map<String, Object> param , ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		int rsInt = seniorSvrveyService.updSeniorHealthSvrVey(param);
		rsMap.put("rsInt", rsInt);
		return rsMap;
	}
	/**
	 * 면접조사지 설문  체크
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/selectSvrveyMastrResult.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> selectSvrveyMastrResult(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = seniorSvrveyService.selectSvrveyMastrResult(param);
		List<Map<String, Object>> rsChkList = seniorSvrveyService.selectSvrveyMastrChk(param);
		rsMap.put("rsList", rsList);
		rsMap.put("rsChkList", rsChkList);
		return rsMap;
	}
	
	/**
	 * 면접조사지 설문 답변 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/selectSvrveyAnswr.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> selectSvrveyAnswr(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = seniorSvrveyService.selectSvrveyAnswr(param);
		rsMap.put("rsList", rsList);
		return rsMap;
	}
	
	/**
	 * 디바이스 제공 insert
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/insertDeviceDistributes.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> insertDeviceDistributes(@ModelAttribute Map<String, Object> param , ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		int rsInt = seniorSvrveyService.insertDeviceDistributes(param);
		int resultInt = seniorSvrveyService.updateGetDeviceInfo(param);
		int rsFormInt = seniorSvrveyService.insertDeviceFormInfo(param);	
		
		rsMap.put("rsInt", rsInt);
		rsMap.put("resultInt", resultInt);
		rsMap.put("rsFormInt", rsFormInt);		
		
		return rsMap;
	}
	
	/**
	 * 디바이스 제공 upd
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/updDeviceDistributes.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> updDeviceDistributes(@ModelAttribute Map<String, Object> param , ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		int rsInt = seniorSvrveyService.updDeviceDistributes(param);
		rsMap.put("rsInt", rsInt);
		return rsMap;
	}
	
	
	
	/**
	 * 디바이스 제공 목록 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/selectDeviceDistributesList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> selectDeviceDistributesList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = seniorSvrveyService.selectDeviceDistributesList(param);
		rsMap.put("DEVICE_PYMNT_CD", seniorSvrveyService.selectSvcMngtDevice(param));
		rsMap.put("rsList", rsList);
		return rsMap;
	}
	
	/**
	 * 디바이스 수령 팝업 
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/selectDeviceDistributePop.do", method= RequestMethod.GET)
	public String selectDeviceDistributePop(@ModelAttribute Map param, ModelMap model) throws Exception {
		return "web/tg/seniorDeviceDistributePop";
	}
	
	/**
	 * 미션 목록 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/selectMissionNmList.do" , method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> selectMissionNmList(@ModelAttribute Map<String, Object> param , ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = seniorSvrveyService.selectMissionNmList(param);
		List<Map<String, Object>> rsGrpList = seniorSvrveyService.selectMissionGrpNmList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("rsGrpList", rsGrpList);
		return rsMap;
	}
	
}
