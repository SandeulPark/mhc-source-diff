package kr.or.khealth.smhc.smhcweb.mr.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.or.khealth.smhc.common.DMultiActionController;
import kr.or.khealth.smhc.smhcweb.mr.service.MeasrStatusService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Class Name : DeviceDistrbtController.java
 * @Description : 관리자 WEB에서 사용하는 어르신 측정 현황을 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2020.09.16		양현우			수정
 *
 * @author thejoin
 * @since 2020.09.16
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
@RequestMapping(value = "/mr")
public class MeasrStatusController extends DMultiActionController {

	@Resource(name= "web.mr.MeasrStatusService")
	private MeasrStatusService measrStatusService;
	
	@ModelAttribute
	public Map<String, Object> initDate(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	
	/**
	 * 대상자정보관리 대상자 정보관리 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/measrStatus.do")
	public String seniorTrgterInfo(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		return "web/mr/measrStatus";
	}
	
	/**
	 * 측정현황 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/selectMeasrStatusList.do")
	public @ResponseBody Map<String, Object> selectMeasrStatusList(@ModelAttribute Map<String, Object> param , ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = measrStatusService.selectMeasrStatusList(param);
		rsMap.put("id", param.get("id"));
		rsMap.put("rsList", rsList);
		return rsMap;
	}
	
	/**
	 * 미측정유저 COUNT 
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/unmeasuredCount.do")
	public @ResponseBody Map<String, Object> unmeasuredCount(@ModelAttribute Map<String, Object> param , ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>(); 
		Map<String, Object> rs = measrStatusService.unmeasuredCount(param);
		rsMap.put("result", rs);
		return rsMap;
	}
}
