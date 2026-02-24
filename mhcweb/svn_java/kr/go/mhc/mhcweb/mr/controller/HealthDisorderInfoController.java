package kr.go.mhc.mhcweb.mr.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.mhcweb.mr.service.HealthDisorderInfoService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Class Name : HealthDisorderInfoController.java
 * @Description : 관리자 WEB에서 사용하는 건강 이상 정보 업무를 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일			수정자		수정내용
 * @	----------		-----		---------------------------
 * @	2016.09.19		이현규		최초생성
 *
 * @author gst
 * @since 2016.09.19
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
@RequestMapping(value = "/mr")
public class HealthDisorderInfoController extends DMultiActionController {
	
	@Resource(name = "web.mr.HealthDisorderInfoService")
	private HealthDisorderInfoService healthDisorderInfoService;
	
	@ModelAttribute
	public Map initData(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	
	/**
	 * 건강 이상 정보 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/healthDisorderInfo.do")
	public String healthDisorderInfo(@ModelAttribute Map param, ModelMap model) throws Exception {
		String mainYn = "N";
		if(param.get("mainYn") != null){
			mainYn = param.get("mainYn").toString();
			model.addAttribute("mainTrgtYY",param.get("mainTrgtYY"));
		}
		model.addAttribute("mainYn", mainYn);
		return "web/mr/healthDisorderInfo";
    }
	
	/**
	 * 건강 이상 정보 목록 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/healthDisorderInfoList.do")
	public @ResponseBody Map<String, Object> healthDisorderInfoList(@ModelAttribute Map param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, String>> rsList = healthDisorderInfoService.selectHealthDisorderInfoList(param);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		
		return rsMap;
    }

}
