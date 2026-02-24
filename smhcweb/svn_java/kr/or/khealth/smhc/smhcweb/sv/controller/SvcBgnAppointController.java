package kr.or.khealth.smhc.smhcweb.sv.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.or.khealth.smhc.common.DMultiActionController;
import kr.or.khealth.smhc.smhcweb.sv.service.SvcBgnAppointService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;



/**
 * @Class Name : SvcBgnAppointController.java
 * @Description : 관리자 WEB에서 사용하는 서비스 개시일 지정 업무를 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일				수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.08.08		이태석			최초생성
 *
 * @author gst
 * @since 2016.08.08
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
@RequestMapping(value = "/sv")
public class SvcBgnAppointController extends DMultiActionController{
	
	@Resource(name = "web.sv.SvcBgnAppointService")
	private SvcBgnAppointService svcBgnApService;

	@ModelAttribute
	public Map<String,Object> initData(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	
	/**
	 * 서비스 개시일 지정 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/serviceBeginAppoint.do", method = RequestMethod.GET)
	public String healthInfoMngt(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {
		model.addAllAttributes(param);
		return "web/sv/svcBgnAppoint";
	}
	
	/**
	 * 서비스 개시일 지정 화면 목록 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/serviceBgnApList.do")
	public @ResponseBody Map<String, Object> serviceBeginApList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, String>> rsList = svcBgnApService.selectServiceBeginApList(param);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	
	/**
	 * 서비스 개시일 지정 상세 화면 호출
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/serviceBeginApDtls.do")
	public String serviceBeginApDtls(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object>  rsMap = svcBgnApService.selectServiceBeginApDtls(param);
		
		param.put("SVC_MNGT_NO", rsMap.get("SVC_MNGT_NO"));
		List<Map<String, Object>> rsList = svcBgnApService.selectServiceSchedule(param);
		
		model.addAttribute("userMap", rsMap);
		model.addAttribute("schList", rsList);
		return "web/sv/svcBgnAppointDtls";
	}

	
	/**
	 * 서비스 일정 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/serviceBeginApSch.do")
	public @ResponseBody Map<String, Object> serviceBeginApSch(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = svcBgnApService.selectServiceSchedule(param);
		
		rsMap.put("rsList", rsList);
		return rsMap;
	}
	
	/**
	 * 재검진 대상자 상태 업데이트
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/updateReExamSttus.do")
	public @ResponseBody Map<String, Object> updateReExamSttus(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		String chkYn = "N";
		try{
			svcBgnApService.updateReExamSttus(param);
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}

}
