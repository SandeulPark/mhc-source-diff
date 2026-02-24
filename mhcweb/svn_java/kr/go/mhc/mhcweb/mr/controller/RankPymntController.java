package kr.go.mhc.mhcweb.mr.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.common.util.StringUtil;
import kr.go.mhc.mhcweb.mr.service.RankPymntService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @Class Name : RankPymntController.java
 * @Description : 관리자 WEB에서 사용하는 배송및지급 업무를 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.11.28		이은주			최초생성
 * 
 * @author gst
 * @since 2016.11.28
 * @version 1.0
 * @see
 * 
 * Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
@RequestMapping(value= "/mr")
public class RankPymntController extends DMultiActionController {
	
	@Resource(name= "web.mr.RankPymntService")
	private RankPymntService rankPymntService;
	
	@ModelAttribute
	public Map initDate(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	
	/**
	 * 배송 및 지급 화면 호출
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/rankPymnt.do")
	public String rankPymnt(@ModelAttribute Map<String, Object> param, Model model) throws Exception {
		List<Map<String, Object>> orgCdList = rankPymntService.orgCdList(param);
		model.addAttribute("orgCdList", orgCdList);
		return "web/mr/rankPymnt";
		
	}
	

	/**
	 * 배송 및 지급 목록 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/rankPymntList.do",  method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> rankPymntList(@ModelAttribute Map<String, Object> param, Model model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = rankPymntService.rankPymntList(param);
		Map<String, Object> rankPymntCnt = rankPymntService.rankPymntCnt(param);
		
		rsMap.put("rsList", rsList);
		rsMap.put("rankPymntCnt", rankPymntCnt);
		rsMap.put("id", param.get("id"));
		
		return rsMap;
	}

	/**
	 * 배송 및 지급 배송완료
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/updatePymnt.do", method= RequestMethod.POST)
	public void updatePymnt(@ModelAttribute Map<String, Object> param, Model model) throws Exception {
		System.out.println("controller param::: "+param);
		rankPymntService.updatePymnt(param);
	}
	
	/**
	 * 배송 및 지급 팝업창 호출
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/rankPymntPop.do")
	public String rankPymntPop(@ModelAttribute Map<String, Object> param, Model model) throws Exception {
		return "web/mr/rankPymntPop";
	}
	
	/**
	 * 배송 및 지급 건강포인트 내역
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/healthPointList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> healhPointList(@ModelAttribute Map<String, Object> param, Model model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = rankPymntService.healthPointList(param);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		
		return rsMap;
	}
	
	/**
	 * 배송 및 지급 수상내역
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/awardList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> awardList(@ModelAttribute Map<String, Object> param, Model model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = rankPymntService.awardList(param);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		
		return rsMap;
	}
}
