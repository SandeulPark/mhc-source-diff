package kr.go.mhc.mhcweb.mr.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.common.util.StringUtil;
import kr.go.mhc.mhcweb.mr.service.SvcJoinInfoService;
import kr.go.mhc.mhcweb.sv.service.NoticeSetMngService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Class Name : SvcJoinInfoController.java
 * @Description : 관리자 WEB에서 사용하는 서비스 참여정보 업무를 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.09.19		이은주			최초생성
 * @    2016.11.15		이은주			2주간 미사용자 추가.
 * @	2016.11.16		이은주			서비스 참여율에서 날짜 가지고 넘어노는거 추가.
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
public class SvcJoinInfoController extends DMultiActionController {
	
	@Resource(name= "web.mr.SvcJoinInfoService")
	private SvcJoinInfoService svcJoinInfoService;
	
	@Resource(name = "web.sv.NoticeSetMngService")
	private NoticeSetMngService noticeSetMngService;
	
	@ModelAttribute
	public Map initDate(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	
	/**
	 * 서비스 참여정보 서비스 참여정보 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/svcJoinInfo.do")
	public String svcJoinInfo(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		String mainYn = "N";
		if(param.get("mainYn") != null){
			mainYn = param.get("mainYn").toString();
			model.addAttribute("mainTrgtYY",param.get("mainTrgtYY"));
		}
		model.addAttribute("mainYn", mainYn);
		return "web/mr/svcJoinInfo";
	}
	
	/**
	 * 서비스 참여 정보 목록 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/svcJoinInfoList.do")
	public @ResponseBody Map<String, Object> svcJoinInfoList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object>	rsMap = new HashMap<String, Object>();
		
		//2016.11.16 추가.
		String searchInfo = StringUtil.nvl((String)param.get("REQ_SEARCH_INFO"));
		if(!"".equals(searchInfo)){
			param.put("searchInfoList", StringUtil.makeStringToIterator(searchInfo));
		}
		//2016.11.16 끝.
		
		List<Map<String, Object>> rsList = svcJoinInfoService.svcJoinInfoList(param);
		
		rsMap.put("id", param.get("id"));
		rsMap.put("rsList", rsList);
		
		return rsMap;
	}
	
	/**
	 * 서비스 참여 정보 2주간 미사용자 목록 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/svcJoinInfoTwoWeeksNull.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> svcJoinInfoTwoWeeksNull(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = svcJoinInfoService.svcJoinInfoTwoWeeksNull(param);
		
		rsMap.put("id", param.get("id"));
		rsMap.put("rsList", rsList);
		
		return rsMap;
		
	}

}
