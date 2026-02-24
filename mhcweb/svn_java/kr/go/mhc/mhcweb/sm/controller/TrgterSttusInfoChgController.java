package kr.go.mhc.mhcweb.sm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.mhcweb.sm.service.TrgterSttusInfoChgService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @Class Name : TrgterSttusInfoChgController.java
 * @Description : 대상자 상태 정보 변경 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일			수정자		수정내용
 * @	----------		------		---------------------------
 * @	2019.11.04		 양현우			최초생성
 * @author theJoin
 * @since 
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
@RequestMapping(value = "/sm")
public class TrgterSttusInfoChgController extends DMultiActionController{ 
	
	@Resource(name = "web.sm.TrgterSttusInfoChgService")
	private TrgterSttusInfoChgService trgterSttusInfoChgService;

	@ModelAttribute
	public Map initData(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	/**
	 * 대상자 상태 정보 변경 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/trgterSttusInfoChg.do")
	public String trgterSttusInfoChg(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		return "web/sm/trgterSttusInfoChg";
	}
	/**
	 * 대상자 리스트 목록 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value ="selectTrgterSttusInfoList.do")
	public @ResponseBody  Map<String, Object> selectTrgterSttusInfoList(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();

		List<Map<String, Object>> rsList = trgterSttusInfoChgService.selectTrgterSttusInfoList(param);

		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	/**
	 * 대상자 테스트 구분 변경
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value="trgterSttusInfoTestChg.do")
	public @ResponseBody Map<String,Object> trgterSttusInfoTestChg(@ModelAttribute Map<String,Object> param , ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		int rsInt = trgterSttusInfoChgService.trgterSttusInfoTestChg(param);
		int rsInt2= trgterSttusInfoChgService.trgterSttusInfoTestChange(param);
		int rsInt3 =trgterSttusInfoChgService.updateTrgterSttusInfoExamSn(param);
		if(rsInt ==0 && rsInt2==0 &&rsInt3==0) {
			rsMap.put("trgterSttusInfoTestChg", "N");
		}else if(rsInt!=0 && rsInt2 !=0 &&rsInt3!=0){
			rsMap.put("trgterSttusInfoTestChg", "Y");
		}
		return rsMap;
	}
	/**
	 * 대상자 스케줄 정보 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value="selectTrgterSttusInfoServiceSchedule.do")
	public @ResponseBody Map<String,Object> selectTrgterSttusInfoServiceSchedule(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{	
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String,Object>> rsList = trgterSttusInfoChgService.selectTrgterSttusInfoServiceSchedule(param);
		rsMap.put("rsList", rsList);
		return rsMap;
	}
	/**
	 * 대상자 정보 검진 데이터 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value="selectTrgterSttusInfoExamList.do")
	public @ResponseBody Map<String, Object> selectTrgterSttusInfoExamList(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{	
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String,Object>> rsList = trgterSttusInfoChgService.selectTrgterSttusInfoExamList(param);
		rsMap.put("rsList", rsList);
		return rsMap;
	}
	/**
	 * 대상자 검진 정보 변경
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value="trgterSttusChange.do")
	public @ResponseBody Map<String,Object> trgterSttusChange(@ModelAttribute Map<String,Object> param , ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		int rsInt = trgterSttusInfoChgService.trgterSttusChange(param);
		if(rsInt ==0) {
			rsMap.put("trgterSttusChange", "N");
		}else{
			rsMap.put("trgterSttusChange", "Y");
		}
		return rsMap;
	}
	/**
	 * 대상자 스케줄 정보 변경
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value="trgterScheduleChg.do")
	public @ResponseBody Map<String,Object> TrgterScheduleChg(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		rsMap.put("SVC_MNGT_NO", param.get("SVC_MNGT_NO"));
		rsMap.put("SVC_BGN_DE", param.get("SVC_BGN_DE"));
		String rsStr = trgterSttusInfoChgService.trgterScheduleChg(rsMap);
		List<Map<String, Object>> rsList = trgterSttusInfoChgService.selectTrgterSttusInfoServiceSchedule(param);
		rsMap.put("rsList", rsList);
		return rsMap;
	}
	/**
	 * 대상자 스케줄 삭제
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value="trgterScheduleDelete.do")
	public @ResponseBody Map<String,Object> trgterScheduleDelete(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		int rsInt = trgterSttusInfoChgService.trgterScheduleDelete(param);
		if(rsInt ==0){
			rsMap.put("trgterScheduleDelete", "N");
		}
		else {
			rsMap.put("trgterScheduleDelete", "Y");
		}
		return rsMap;
	}
	/**
	 * 대상자 정보 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value="selectTrgterSttusInfoExamSn.do")
	public @ResponseBody Map<String,Object> selectTrgterSttusInfoExamSn(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{	
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String,Object>> rsList = trgterSttusInfoChgService.selectTrgterSttusInfoExamSn(param);
		rsMap.put("rsList", rsList);
		return rsMap;
	}
}
