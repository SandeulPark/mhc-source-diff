package kr.or.khealth.smhc.smhcweb.tg.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.or.khealth.smhc.common.DMultiActionController;
import kr.or.khealth.smhc.smhcweb.tg.service.SeniorMissionRsltInfoService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Class Name : SeniorMissionRsltInfoController.java
 * @Description : 관리자 WEB에서 사용하는 어르신 대면평가정보등록(미션실천현황) 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2020.10.19		
 *
 * @author theJoin
 * @since 2020.10.19
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */
@Controller
@RequestMapping(value = "/tg")
public class SeniorMissionRsltInfoController extends DMultiActionController{
	
	@Resource(name = "web.tg.SeniorMissionRsltInfoService")
	private SeniorMissionRsltInfoService seniorMissionRsltInfoService;
	
	@ModelAttribute
	public Map<String, Object> initDate(HttpServletRequest req) throws  Exception{
		return super.initData(req);
	}
	
	/**
	 * 미션실천현황 리스트 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/selectMissionRsltInfoList.do")
	public @ResponseBody Map<String, Object> selectMissionRsltInfoList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = seniorMissionRsltInfoService.selectMissionRsltInfoList(param);
		rsMap.put("rsList", rsList);
		return rsMap;
	}
	
	
	/**
	 * 외출미션인증사진 리스트 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/selectPhotoRsltInfoList.do")
	public @ResponseBody Map<String, Object> selectPhotoRsltInfoList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = seniorMissionRsltInfoService.selectPhotoRsltInfoList(param);
		rsMap.put("rsList", rsList);
		return rsMap;
	}

}
