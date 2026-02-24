package kr.or.khealth.smhc.smhcweb.sv.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.or.khealth.smhc.common.DMultiActionController;
import kr.or.khealth.smhc.common.util.StringUtil;
import kr.or.khealth.smhc.smhcweb.cm.service.PushService;
import kr.or.khealth.smhc.smhcweb.sv.service.PushSendService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Class Name : NoticeSetMngController.java
 * @Description : 관리자 WEB에서 사용하는 알림설정관리 controller
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
@RequestMapping(value = "/sv")
public class PushSendController extends DMultiActionController{
	
	@Resource(name = "web.sv.PushSendService")
	private PushSendService pushSendService;

	@Resource(name="common.pushService")
	private PushService pushService;

	@ModelAttribute
	public Map<String,Object> initData(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	
	/**
	 * 알림발송현황 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/noticesendsttus.do")
	public String noticesendsttus(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {
	    
		return "web/sv/noticeSendSttus";
	}
	
	/**
	 * 상담요청목록 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/noticeSttusList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> noticeSttusList(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		
		List<Map<String, String>> rsList = pushSendService.getNoticeSttusList(param);
		
		
		System.out.println("noticeSttusList===>"+ rsList);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		
		return rsMap;
	}
	
	@RequestMapping(value = "/receiverList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> receiverList(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String, String>> rsList = pushSendService.getReceiverList(param);
		Map<String, Object> cntMap = pushSendService.getSttusCnt(param);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		rsMap.putAll(cntMap);
		
		return rsMap;
	}
	
	/**
	 * 수동알림설정 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/pushSend.do")
	public String manualnoticeset(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {
		List<Map<String, String>> rsList2 = pushSendService.manualLinkPageList(param);
		param.put("CMMN_CD","TC_CM_ORG");
		//알림 링크 추가
		Map<String,String> rsMap = new HashMap<String,String>();
		rsMap.put("MENU_URL", "/page/sv/community.html");
		rsMap.put("MENU_NM", "커뮤니티");
		rsList2.add(rsMap);
		
		//2023-06-29 추가 (검색조건 시도값)
		List<Map<String, String>> selList = cmmnService.selectCmmnCd(param);
		model.addAttribute("selList", selList);
		
	    model.addAttribute("linkList",rsList2);
	
		return "web/sv/pushSend";
		
	}
	
	
	/**
	 * 수동알림대상자목록 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/pushNoticetrgterList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> pushNoticetrgterList(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		
		String searchInfo = StringUtil.nvl(String.valueOf(param.get("REQ_SEARCH_INFO")));
		if(!"".equals(searchInfo)){
			param.put("searchInfoList", StringUtil.makeStringToIterator(searchInfo));
		}
		
		if(param.get("ORG_CD") != null) {
			if(param.get("ORG_CD").equals("")) {
				param.put("ORG_CD", "ALL");
			}
		}
		if(param.get("SIDO_CD") != null) {
			param.put("SIDO_CD", param.get("SIDO_CD"));
		}
		
		List<Map<String, String>> rsList = pushSendService.getPushNoticetrgterList(param);
		
		String sndSn = StringUtil.nvl(String.valueOf(param.get("SND_SN")));
		if(!"".equals(sndSn)){
			List<Map<String, String>> rsPushCont = pushSendService.getNoticeSttusList(param);
			rsMap.put("rsPushCont", rsPushCont);
		}
		
		System.out.println("pushNoticetrgterList===>"+ rsList);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		
		return rsMap;
	}
	
	/**
	 * 금일 수동알림 발송목록 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/todayManualSendList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> todayManualSendList(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		if(param.get("ORG_CD") != null) {
			if(param.get("ORG_CD").equals("")) {
				param.put("ORG_CD", "ALL");
			}
		}
		if(param.get("SIDO_CD") != null) {
			param.put("SIDO_CD", param.get("SIDO_CD"));
		}
		List<Map<String, String>> rsList = pushSendService.getTodayManualSendList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		
		return rsMap;
	}
	
	/**rarara
	 * 자동알림설정화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/autonoticeset.do")
	public String autonoticeset(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {
		List<Map<String, String>> rsList = pushSendService.getAutoPushMsgList(param);
		List<Map<String, String>> rsList2 = pushSendService.manualLinkPageList(param);
		
		//알림 링크 추가
		Map<String,String> addMap = new HashMap<String,String>();
		addMap.put("MENU_URL", "/page/cm/index.html");
		addMap.put("MENU_NM", "메인");
		rsList2.add(addMap);
		
	    model.addAttribute("linkList",rsList2);
	    
		if(rsList.size()>0){
			model.addAttribute("rs",rsList.get(0));
		}
		return "web/sv/autoNoticeSet";
	}
	
	/**rarara
	 * 수동알림대상자목록 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/autoPushMsgList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> autoPushMsgList(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		
		List<Map<String, String>> rsList1 = pushSendService.getAutoPushMsgList(param);
		
		System.out.println("autoPushMsgList===>"+ rsList1);
		
		rsMap.put("rsList", rsList1);
		rsMap.put("id", param.get("id"));
		
		return rsMap;
	}
	
	/**rarara
	 * 알림메시지 수정
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/saveAutoPushMsg.do", method = RequestMethod.POST)
	public String saveAutoPushMsg(@ModelAttribute Map<String, Object> param, ModelMap model)
			throws Exception {

		pushSendService.saveAutoPushMsg(param);
		
		return "web/sv/autoNoticeSet";
	}
	
	/**
	 * 수동알림대상자 상세 목록 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/getDetailList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getDetailList(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		
		List<Map<String, String>> rsList = pushSendService.getDetailList(param);
		
		
		System.out.println("getDetailList===>"+ rsList);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		
		return rsMap;
	}
	
	/**
	 * 알림발송현황
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/noticeSendSttus.do")
	public String noticeSendSttus(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {
		param.put("CMMN_CD","TC_CM_ORG");
		List<Map<String, String>> selList = cmmnService.selectCmmnCd(param);
		model.addAttribute("selList", selList);
		model.addAllAttributes(param);
		return "web/sv/noticeSendSttus";
	}
	
	/**
	 * 알림발송현황 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/noticeSendSttusList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> noticeSendSttusList(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		if (param.get("pagingSet[gridRowsPerPage]") != null) {
			int gridTotalRowCount = pushSendService.selectNoticeSndCount(param);
			rsMap.put("gridTotalRowCount", gridTotalRowCount);
		}
		List<Map<String, String>> rsList = pushSendService.selectNoticeSndSttus(param);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		
		return rsMap;
	}
	
	/**
	 * 대상자별 알림발송현황
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/trgterNoticeSendSttus.do")
	public String trgterNoticeSendSttus(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {
		param.put("CMMN_CD","TC_CM_ORG");
		List<Map<String, String>> selList = cmmnService.selectCmmnCd(param);
		model.addAttribute("selList", selList);
		model.addAttribute("SND_SN", param.get("SND_SN"));
		model.addAllAttributes(param);
		return "web/sv/trgterNoticeSendSttus";
	}
	
	/**
	 * 대상자별 알림발송현황 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/trgterNoticeSttusList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> trgterNoticeSttusList(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		if (param.get("pagingSet[gridRowsPerPage]") != null) {
			int gridTotalRowCount = pushSendService.selectTrgterNoticeSndCount(param);
			rsMap.put("gridTotalRowCount", gridTotalRowCount);
		}
		List<Map<String, String>> rsList = pushSendService.selectTrgterNoticeSndSttus(param);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
}
	