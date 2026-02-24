package kr.or.khealth.smhc.smhcweb.tg.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.or.khealth.smhc.common.DMultiActionController;
import kr.or.khealth.smhc.smhcweb.tg.service.FaceToFaceInfoRegService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Class Name : FaceToFaceInfoRegController.java
 * @Description : 관리자 WEB에서 사용하는 어르신 대면평가정보등록을 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2020.09.16		양현우			최초생성
 *
 * @author thejoin
 * @since 2020.09.16
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
@RequestMapping(value = "/tg")
public class FaceToFaceInfoRegController extends DMultiActionController{

	@Resource(name="web.tg.FaceToFaceInfoRegService")
	private FaceToFaceInfoRegService faceToFaceInfoRegService;
	
	@ModelAttribute
	public Map<String, Object> initDate(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	
	
	/**
	 * 어르신 대면평가정보등록 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/faceToFaceInfoReg.do")
	public String faceToFaceInfoReg(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		return "web/tg/faceToFaceInfoReg";
	}
	
	/**
	 * 어르신 대면평가정보 목록 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/selectFaceToFaceInfoList.do")
	public @ResponseBody Map<String, Object>selectFaceToFaceInfoList(@ModelAttribute Map<String, Object> param , ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = faceToFaceInfoRegService.selectFaceToFaceInfoList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	
	/**
	 * 대면평가 기본정보 화면 호출
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/seniorDtlsInfo.do")
	public String seniorDtls(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		model.addAttribute("USER_ID",param.get("USER_ID"));
		model.addAttribute("SVC_NO",param.get("SVC_NO"));
		model.addAllAttributes(param);
		return "web/tg/seniorDtlsInfo";
	}
	
	/**
	 * 대면평가 상세 기본 정보 조회
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value="/selectFaceToFaceSeniorDtls.do")
	public @ResponseBody Map<String, Object> selectFaceToFaceSeniorDtls(@ModelAttribute Map param , ModelMap model) throws Exception{
		Map<String, Object> rsMap = faceToFaceInfoRegService.selectFaceToFaceSeniorDtls(param);
		return rsMap;
	}
	
}
