package kr.go.mhc.mhcweb.cm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.mhcweb.cm.service.HealthExamReqService;

/**
 * @Class Name : HealthExamReqController.java
 * @Description : 관리자 WEB에서 사용하는 검진데이터 수정 요청 업무를 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	  수정일		수정자			수정내용
 * @	----------		--------		---------------------------
 * @	2018.09.11		오샘이			최초생성
 *
 * @author theJoin
 * @since 2018.09.11
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */


@Controller
public class HealthExamReqController extends DMultiActionController {

	@Resource(name = "web.cm.HealthExamReqService")
	private HealthExamReqService healthExamReqService;

	@ModelAttribute
	public Map initData(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}

	/**
	 * 건강검진 데이터 수정 요청 목록 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/healthExamReq.do", method = RequestMethod.GET) 
	public String healthExamReq(@ModelAttribute Map param, ModelMap model) throws Exception {
		return "web/cm/healthExamReq";
	}

	/**
	 * 건강검진 데이터 수정 요청 목록 조회
	 * @param param 검색 조건
	 * @return rsMap
	 * @throws Exception 
	 */
	@RequestMapping(value = "/getHealthExamReqList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getHealthExamReqList( @ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		
		Map<String, Object> rsMap = new HashMap<String, Object>();	
		List<Map<String, String>> rsList = healthExamReqService.getHealthExamReqList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));	
		return rsMap;
	}

	/**
	 * 건강검진 데이터 수정 요청 등록 화면 호출 및 조회
	 * @param
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/getHealthExamReqReg.do", method = RequestMethod.POST)
	public String getHealthExamReqReg(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		if(param.get("PAGE_CLF") != null){
			Map<String, String> rsMap = healthExamReqService.getHealthExamReqDtls(param);
			
			List<Map<String, String>> attachFileList = new ArrayList<Map<String, String>>();
								
			if(!StringUtils.isEmpty(String.valueOf(rsMap.get("ATTCH_FILE_SN")))) {
				attachFileList = healthExamReqService.getAttachFileList(rsMap);
			}
			
			model.addAttribute("rsMap", rsMap);
			model.addAttribute("attachFileList", attachFileList);
			model.addAllAttributes(param);					
		}

		return "web/cm/healthExamReqReg";
	}

	/**
	 * 건강검진 데이터 수정 요청 등록
	 * @param param 저장 정보
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/insertHealthExamReq.do", method = RequestMethod.POST)
	public String insertHealthExamReq(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		healthExamReqService.insertHealthExamReq(param);
		return "redirect:pageNavi.do?menuCd=NSV113";
	}

	/**
	 * 건강검진 데이터 수정 요청 상세 화면 호출 및 조회
	 * @param param PK 정보
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/getHealthExamReqDtls.do", method = RequestMethod.POST)
	public String healthExamReqDtls(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, String> rsMap = healthExamReqService.getHealthExamReqDtls(param);		
		List<Map<String, String>> befAftNoticeList = healthExamReqService.getHealthExamReqBeAfList(param);
		
		List<Map<String, String>> attachFileList = new ArrayList<Map<String, String>>();
				
		if(!StringUtils.isEmpty(String.valueOf(rsMap.get("ATTCH_FILE_SN")))) {
			attachFileList = healthExamReqService.getAttachFileList(rsMap);
		}

		String returnUrl = "web/cm/healthExamReqDtls";
		model.addAttribute("rsMap", rsMap);
		model.addAttribute("attachFileList", attachFileList);
		model.addAttribute("befAftNoticeList", befAftNoticeList);

		
		return returnUrl;
	}
	

	
	/**
	 * 건강검진 데이터 수정 요청 수정 화면 호출
	 * @param param 상세화면 정보
	 * @return 
	 * @throws Exception 
	 
	@RequestMapping(value = "/healthExamReqUpView.do", method = RequestMethod.POST)
	public String healthExamReqUpView(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		model.addAttribute("rsMap", param);
		return "web/cm/healthExamReqUpView";
	}
	*/
	

	/**
	 * 건강검진 데이터 수정 요청 수정 정보 삭제 
	 * @param param 수정 정보
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/deleteHealthExamReq.do", method = RequestMethod.GET)
	public String deleteHealthExamReq(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		healthExamReqService.deleteHealthExamReq(param);
		return "redirect:pageNavi.do?menuCd=NSV113";

	}

	/**
	 * 건강검진 데이터 수정 요청 수정 정보 업데이트 
	 * @param param 수정 정보
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/updateHealthExamReq.do", method = RequestMethod.POST)
	public String healthExamReqUp(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		//param.put("NEW_SHOW_BGN_DE", param.get("NEW_SHOW_BGN_DE").toString().replace("-", ""));
		healthExamReqService.updateHealthExamReq(param);
		return "redirect:pageNavi.do?menuCd=NSV113";
	}
	


	/**
	 * 건강검진 데이터 수정 요청사항 댓글 불러오기
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/getHealthExamReqCommentList.do")
	public @ResponseBody Map<String,Object> getHealthExamReqCommentList(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
			
		List<Map<String,Object>> rsList = healthExamReqService.getHealthExamReqCommentList(param); 
		rsMap.putAll(param);
		rsMap.put("rsList", rsList);
		if(rsList.size()==0){

		}else{
		
		}
		return rsMap;
	}	
	
	/**
	 * 건강검진 데이터 수정 요청사항 댓글 등록
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/insertHealthExamReqComment.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> insertHealthExamReqCommenct(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();

		healthExamReqService.insertHealthExamReqComment(param); 
		rsMap.putAll(param);
		return rsMap;
	}	
	
	
	/**
	 * 건강검진 데이터 수정 요청사항 댓글수정
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/updateHealthExamReqComment.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> updateHealthExamReqComment(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
			
		healthExamReqService.updateHealthExamReqComment(param); 
		rsMap.putAll(param);


		return rsMap;
	}	
	
	
	/**
	 * 건강검진 데이터 수정 요청사항 댓글삭제
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/deleteHealthExamReqComment.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> deleteHealthExamReqComment(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		
		String chkYn = "N";
		try{
			healthExamReqService.deleteHealthExamReqComment(param);
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}	
	
	
	
}
