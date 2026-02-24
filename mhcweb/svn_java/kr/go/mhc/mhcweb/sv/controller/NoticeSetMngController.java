package kr.go.mhc.mhcweb.sv.controller;

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
import kr.go.mhc.common.util.PushMessageUtil;
import kr.go.mhc.common.util.StringUtil;
import kr.go.mhc.mhcweb.cm.service.PushService;
import kr.go.mhc.mhcweb.sv.service.NoticeSetMngService;

/**
 * @Class Name : NoticeSetMngController.java
 * @Description : 관리자 WEB에서 사용하는 알림설정관리 controller
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.08.12		장슬기		최초생성
 * @    2016.08.16       장슬기         수동,자동알림설정 추가	
 *
 * @author gst
 * @since 2016.08.12
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
@RequestMapping(value = "/sv")
public class NoticeSetMngController extends DMultiActionController{
	
	@Resource(name = "web.sv.NoticeSetMngService")
	private NoticeSetMngService noticeSetMngService;

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
				
		int gridTotalRowCount = noticeSetMngService.getNoticeSttusListCount(param);
		rsMap.put("gridTotalRowCount", gridTotalRowCount);
		
		List<Map<String, String>> rsList = noticeSetMngService.getNoticeSttusList(param);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		
		return rsMap;
	}
	
	@RequestMapping(value = "/receiverList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> receiverList(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String, String>> rsList = noticeSetMngService.getReceiverList(param);
		Map<String, Object> cntMap = noticeSetMngService.getSttusCnt(param);
		
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
	@RequestMapping(value = "/manualnoticeset.do")
	public String manualnoticeset(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {
		List<Map<String, String>> rsList = noticeSetMngService.getTrgterGroup(param);
		List<Map<String, String>> rsList2 = noticeSetMngService.manualLinkPageList(param);
		param.put("CMMN_CD","TC_CM_ORG");
		List<Map<String, String>> selList = cmmnService.selectCmmnCd(param);
		
		//알림 링크 추가
		Map<String,String> rsMap = new HashMap<String,String>();
		rsMap.put("MENU_URL", "view/cm/notificationMain.html");
		rsMap.put("MENU_NM", "알림");
		rsList2.add(rsMap);
		
	    model.addAttribute("linkList",rsList2);
		model.addAttribute("rsList",rsList);
		model.addAttribute("selList", selList);
	
		return "web/sv/manualNoticeSet";
		
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
		
		List<Map<String, String>> rsList = noticeSetMngService.getPushNoticetrgterList(param);
		
		String sndSn = StringUtil.nvl(String.valueOf(param.get("SND_SN")));
		if(!"".equals(sndSn)){
			List<Map<String, String>> rsPushCont = noticeSetMngService.getNoticeSttusList(param);
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
		
		List<Map<String, String>> rsList = noticeSetMngService.getTodayManualSendList(param);
		
		
		System.out.println("todayManualSendList===>"+ rsList);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		
		return rsMap;
	}
	
	/**
	 * 자동알림설정화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/autonoticeset.do")
	public String autonoticeset(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {
		List<Map<String, String>> rsList = noticeSetMngService.getAutoPushMsgList(param);
		List<Map<String, String>> rsList2 = noticeSetMngService.manualLinkPageList(param);
		
		//알림 링크 추가
		Map<String,String> addMap = new HashMap<String,String>();
		addMap.put("MENU_URL", "view/cm/notificationMain.html");
		addMap.put("MENU_NM", "알림");
		rsList2.add(addMap);
		
	    model.addAttribute("linkList",rsList2);
	    
		if(rsList.size()>0){
			model.addAttribute("rs",rsList.get(0));
		}
		return "web/sv/autoNoticeSet";
	}
	
	/**
	 * 수동알림대상자목록 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/autoPushMsgList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> autoPushMsgList(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		
		List<Map<String, String>> rsList1 = noticeSetMngService.getAutoPushMsgList(param);
		
		System.out.println("autoPushMsgList===>"+ rsList1);
		
		rsMap.put("rsList", rsList1);
		rsMap.put("id", param.get("id"));
		
		return rsMap;
	}
	
	/**
	 * 알림메시지 수정
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/saveAutoPushMsg.do", method = RequestMethod.POST)
	public String saveAutoPushMsg(@ModelAttribute Map<String, Object> param, ModelMap model)
			throws Exception {

		noticeSetMngService.saveAutoPushMsg(param);
		
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
		
		List<Map<String, String>> rsList = noticeSetMngService.getDetailList(param);
		
		
		System.out.println("getDetailList===>"+ rsList);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		
		return rsMap;
	}
	
	/**
	 * 자동알림설정화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/trgterNoticeSendSttus.do")
	public String trgterNoticeSendSttus(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {
		
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
		
		List<Map<String, String>> rsList = noticeSetMngService.selectTrgterNoticeSndSttus(param);
		if(rsList == null || rsList.size() == 0){
			Map<String,String> chkMap = noticeSetMngService.checkTrgterJoinInfo(param);
			String chkCnt = String.valueOf(chkMap.get("CNT"));
			if(Integer.parseInt(chkCnt) > 0){
				rsMap.put(MESSAGE_NAME, getMsg("common.list.null"));
			}else{
				rsMap.put(MESSAGE_NAME, getMsg("common.trgter.null"));
			}
		}
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		
		return rsMap;
	}
	
	/**
	 * 푸쉬전송정보 삭제 
	 * @param 삭제할 param 정보
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/deletePushHis.do", method = RequestMethod.POST)
	public  @ResponseBody Map<String, Object>  deletePushInfoHis(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		noticeSetMngService.insertPushInfoHis(param);
		noticeSetMngService.deletePushInfoHis(param);
		return rsMap;
	}
	
	/**
	 * 푸쉬전송정보 수정 
	 * @param 수정된 param 정보
	 * @return 
	 * @throws Exception 
	 */
	/*
	 * @RequestMapping(value = "/updatePushHisUp.do", method = RequestMethod.POST)
	 * public @ResponseBody Map<String, Object> updatePushInfoHis(@ModelAttribute
	 * Map<String, Object> param, ModelMap model,HttpServletRequest req) throws
	 * Exception { Map<String,Object> rsMap = new HashMap<String,Object>();
	 * noticeSetMngService.updatePushInfoHis(param); return rsMap; }
	 */
	
	/**
	 * 푸쉬 알림 수정 및 취소 현황 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/trgterNoticeChangeSttus.do")
	public String test(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {
		
		model.addAllAttributes(param);
		return "web/sv/trgterNoticeChangeSttus";
	}
	
	/**
	 * 푸쉬 알림 수정 및 취소 리스트 현황 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/trgterNoticeChangeSttusList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> testList(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		
		Map<String,Object> rsMap = new HashMap<String,Object>();
		
		List<Map<String, String>> rsList = noticeSetMngService.selectPushInfoHisList(param);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		
		return rsMap;
	}
	
}
	