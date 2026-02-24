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
import kr.go.mhc.common.util.StringUtil;
import kr.go.mhc.mhcweb.cm.service.PushService;
import kr.go.mhc.mhcweb.gn.service.GnrlNoticeSetMngService;

/**
 * @Class Name : GnrlNoticeSetMngController.java
 * @Description : 보편 관리자 WEB에서 사용하는 알림설정관리 controller
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2021.11.12		chyoon		최초생성
 *
 * @author chyoon
 * @since 2021.11.12
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */
@Controller
@RequestMapping(value = "/gn")
public class GnrlNoticeSetMngController extends DMultiActionController{
	
	@Resource(name = "web.gn.GnrlNoticeSetMngService")
	private GnrlNoticeSetMngService gnrlNoticeSetMngService;
	
	@Resource(name="common.pushService")
	private PushService pushService;		
	
	@ModelAttribute
	public Map<String,Object> initData(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	
	/**
	 * 수동알림설정 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/manualnoticeset.do")
	public String manualnoticeset(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {
		List<Map<String, String>> rsList = gnrlNoticeSetMngService.getTrgterGroup(param);
		List<Map<String, String>> rsList2 = gnrlNoticeSetMngService.manualLinkPageList(param);
		
		//알림 링크 추가
		Map<String,String> rsMap = new HashMap<String,String>();
		rsMap.put("MENU_URL", "view/cm/notificationMain.html");
		rsMap.put("MENU_NM", "알림");
		rsList2.add(rsMap);
		
	    model.addAttribute("linkList",rsList2);
		model.addAttribute("rsList",rsList);
	
		return "web/gn/manualNoticeSet";
		
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
		
		List<Map<String, String>> rsList = gnrlNoticeSetMngService.getPushNoticetrgterList(param);
		
		String sndSn = StringUtil.nvl(String.valueOf(param.get("SND_SN")));
		if(!"".equals(sndSn)){
			List<Map<String, String>> rsPushCont = gnrlNoticeSetMngService.getNoticeSttusList(param);
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
		
		List<Map<String, String>> rsList = gnrlNoticeSetMngService.getTodayManualSendList(param);
		
		
		System.out.println("todayManualSendList===>"+ rsList);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		
		return rsMap;
	}
	
	/**
	 * 알림발송현황 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/noticesendsttus.do")
	public String noticesendsttus(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {
	    
		return "web/gn/noticeSendSttus";
	}
	
	/**
	 * 알림 발송 현황 리스트 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/noticeSttusList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> noticeSttusList(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		
		List<Map<String, String>> rsList = gnrlNoticeSetMngService.getNoticeSttusList(param);
		
		
		System.out.println("noticeSttusList===>"+ rsList);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		
		return rsMap;
	}
	
	@RequestMapping(value = "/receiverList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> receiverList(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String, String>> rsList = gnrlNoticeSetMngService.getReceiverList(param);
		Map<String, Object> cntMap = gnrlNoticeSetMngService.getSttusCnt(param);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		rsMap.putAll(cntMap);
		
		return rsMap;
	}
	
	
	/**
	 * 대상자별 알림발송현황 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/trgterNoticeSendSttus.do")
	public String trgterNoticeSendSttus(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {
		
		model.addAllAttributes(param);
		return "web/gn/trgterNoticeSendSttus";
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
		
		List<Map<String, String>> rsList = gnrlNoticeSetMngService.selectTrgterNoticeSndSttus(param);
		if(rsList == null || rsList.size() == 0){
			Map<String,String> chkMap = gnrlNoticeSetMngService.checkTrgterJoinInfo(param);
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

}
