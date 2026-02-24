package kr.go.mhc.mhcweb.gn.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.mhcweb.gn.service.GnrlSportActivityService;

/**
 * @Class Name : PointRankingController.java
 * @Description : 관리자 WEB에서 사용하는 스포츠 활동 인증 현황을 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일			수정자		수정내용
 * @	----------		------		---------------------------
 * @	2021.12.09		chyoon		최초생성
 *
 * @author	chyoon
 * @since	2021.12.09
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
@RequestMapping(value= "/gn")
public class GnrlSportActivityController extends DMultiActionController{

	@Resource(name="web.gn.SportActivityService")
	private GnrlSportActivityService sportActivityService;
	
	@ModelAttribute
	public Map initDate(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	
	/**
	 * 스포츠 활동 인증 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value="/sportActivity.do", method=RequestMethod.GET)
	public String sportActivity(@ModelAttribute Map<String,String> param, ModelMap model) throws Exception {
		return "web/gn/sportActivity";		
	}
	
	/**
	 * 스포츠 활동 인증 현황 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/sportActivityUserList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> sportActivityUserList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap  = new HashMap<String, Object>();
		Map<String,Object> userInfo = new HashMap<String, Object>();
		//userInfo=sportActivityService.userInfo((String) param.get("SESS_USER_ID"));
		param.put("TRGTER_MANAGER_CLF", sportActivityService.userInfo((String) param.get("SESS_USER_ID")).get("TRGTER_MANAGER_CLF"));
		List<Map<String, Object>> sportActivityUserList = sportActivityService.getSportActivityUserList(param);		
		Map<String, Object> rsMsg = sportActivityService.getSportActivityCertMsg(param);
		
		rsMap.put("rsList", sportActivityUserList);	
		rsMap.put("id", param.get("id"));
		rsMap.put("rsMsg", rsMsg);
		
		return rsMap;
	}
	
}
