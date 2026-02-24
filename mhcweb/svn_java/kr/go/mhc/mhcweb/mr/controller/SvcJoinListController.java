package kr.go.mhc.mhcweb.mr.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.common.util.StringUtil;
import kr.go.mhc.mhcweb.mr.service.SvcJoinListService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Class Name : SvcJoinListController.java
 * @Description : 관리자 WEB에서 사용하는 서비스 참여정보 업무를 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일				수정자			수정내용
 * @	----------		----		---------------------------
 * @	2017.06.20		나연이			최초생성
 *
 * @author gst
 * @since 2016.09.19
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
@RequestMapping(value= "/mr")
public class SvcJoinListController extends DMultiActionController {
	
	@Resource(name= "web.mr.SvcJoinListService")
	private SvcJoinListService svcJoinListService;
	
	@ModelAttribute
	public Map initDate(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	
	/**
	 * 서비스 참여정보 서비스 참여목록 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/svcJoinListMain.do")
	public String svcJoinListMain(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		model.addAllAttributes(param);
		return "web/mr/svcJoinList";
	}
	
	/**
	 * 서비스 참여목록 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/svcJoinList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> svcJoinList(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String, String>> rsList = svcJoinListService.selectSvcJoinList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}

}
