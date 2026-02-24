package kr.go.mhc.mhcweb.gn.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.common.util.StringUtil;
import kr.go.mhc.mhcweb.gn.service.GnrlPointRankingService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Class Name : PointRankingController.java
 * @Description : 관리자 WEB에서 사용하는 포인트 및 랭킹 업무를 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일			수정자		수정내용
 * @	----------		------		---------------------------
 * @	2016.11.28		이태석		최초생성
 *
 * @author	gst
 * @since	2016.11.28
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
@RequestMapping(value= "/gn")
public class GnrlPointRankingController extends DMultiActionController {

	@Resource(name= "web.gn.GnrlPointRankingService")
	private GnrlPointRankingService gnrlPointRankingService;
	
	@ModelAttribute
	public Map initDate(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	
	/**
	 * 포인트 및 랭킹 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/pointRanking.do", method= RequestMethod.GET)
	public String pointRanking(@ModelAttribute Map param, ModelMap model) throws Exception {
		return "web/gn/pointRanking";
		
		
	}
	
	/**
	 * 포인트 및 랭킹 목록 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/pointRankingList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> pointRankingList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap  = new HashMap<String, Object>();
		List<Map<String, Object>> pointRankingList = gnrlPointRankingService.getPointRankingList(param);
//		List<Map<String, Object>> rankingAllPointList = gnrlPointRankingService.getRankingAllPointList(param);
		Map<String, Object> rsMsg = gnrlPointRankingService.getPointRankingMsg(param);

		rsMap.put("pointRankingList", pointRankingList);
//		rsMap.put("rankingAllPointList", rankingAllPointList);
		rsMap.put("rsMsg", rsMsg);		
		return rsMap;
	}
	
	/**
	 * 대상여부_초기화 및 선정 업데이트
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/updatepymntTrgtY.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> updatepymntTrgtY(@ModelAttribute Map<String, Object> param, Model model) throws Exception {
		Map<String, Object> rsMap  = new HashMap<String, Object>();
		int updateCnt = gnrlPointRankingService.updatepymntTrgtY(param);
		rsMap.put("updateCnt", updateCnt);	
		return rsMap;
	}
	

	/**
	 * 대상자 상세 포인트 목록 조회 
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/rankingAllPointList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> rankingAllPointList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();		
		List<Map<String, Object>> rsList = gnrlPointRankingService.getRankingAllPointList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 걸음수랭킹 화면 호출
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/stepRanking.do")
	public String stepRanking(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		return "web/gn/stepRanking";
	}
	
	/**
	 * 걸음수 랭킹 목록 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/stepRankingList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> stepRankingList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap  = new HashMap<String, Object>();
		List<Map<String, Object>> stepRankingList = gnrlPointRankingService.getStepRankingList(param);
		Map<String, Object> rsMsg = gnrlPointRankingService.getStepRankingMsg(param);

		rsMap.put("rsList", stepRankingList);
		rsMap.put("id", param.get("id"));
		rsMap.put("rsMsg", rsMsg);		
		return rsMap;
	}
	
	/**
	 * 건강랭킹 기준 및 점수
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/pointStndPop.do")
	public String pointStndPop(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		return "web/gn/pointStndPop";
	}
}