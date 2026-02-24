package kr.go.mhc.mhcweb.cm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.common.util.StringUtil;
import kr.go.mhc.mhcweb.cm.service.MobileNoticeMngtService;
import kr.go.mhc.mhcweb.cm.service.PushService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Class Name : MobileNoticeMngtController.java
 * @Description : 관리자 WEB에서 사용하는 모바일 공지관리 업무를 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.08.10		이은주			최초생성
 *
 * @author gst
 * @since 2016.08.10
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
public class MobileNoticeMngtController extends DMultiActionController{
	
	@Resource(name= "web.cm.MobileNoticeMngtService")
	private MobileNoticeMngtService mobileNoticeMngtService;
	
	@Resource(name="common.pushService")
	private PushService pushService;
	
	@ModelAttribute
	public Map<String, Object> initDate(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	
	/**
	 * 모바일 공지관리 목록 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/mobileNoticeMngt.do", method= RequestMethod.GET)
	public String mobileNoticeMngt(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		
		return "web/cm/mobileNoticeMngt";
	}
	
	/**
	 * 모바일 공지관리 목록 조회
	 * @param param 검색 조건
	 * @return rsMap
	 * @throws Exception 
	 */
	@RequestMapping(value= "/mobileNoticeMngtList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> mobileNoticeMngtList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		
		Map<String, Object> rsMap = new HashMap<String, Object>();
		param.put("NOTICE_CLF", "M");
		if(param.get("pagingSet[gridRowsPerPage]") != null){
			int gridTotalRowCount = mobileNoticeMngtService.getMobileNoticeMngtListCount(param);
			rsMap.put("gridTotalRowCount", gridTotalRowCount);
		}
		List<Map<String, String>> rsList = mobileNoticeMngtService.getMobileNoticeMngtList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		
		return rsMap;
	}


	/**
	 * 모바일 신규 공지 등록 화면 호출 
	 * @param
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/mobileNoticeMngtReg.do", method= RequestMethod.GET)
	public String mobileNoticeMngtReg(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		
		List<Map<String, String>> rsList = mobileNoticeMngtService.getMobileNoticeReg(param);
		model.addAttribute("rsList", rsList);
		
		return "web/cm/mobileNoticeMngtReg";
	}
	
	/**
	 * 모바일 신규 공지 등록 화면 호출 
	 * @param
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/mobileNoticeMngtInsertNumber.do", method= RequestMethod.POST)
	public Map<String, String> mobileNoticeMngtInsertNumber(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String,String> count = mobileNoticeMngtService.mobileNoticeMngtInsertNumber(param);
		return count;
	}
	
	/**
	 * 모바일 신규 공지 저장	
	 * @param param 저장 정보
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/mobileNoticeMngtInsert.do", method= RequestMethod.POST)
	public String mobileNoticeMngtInsert(HttpServletRequest req, @ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {

		// 1. 게시물 등록
		param.put("NEW_SHOW_BGN_DE", param.get("NEW_SHOW_BGN_DE").toString().replace("-", ""));
		param.put("NEW_SHOW_END_DE", param.get("NEW_SHOW_END_DE").toString().replace("-", ""));
		mobileNoticeMngtService.getMobileNoticeRegInsert(param);
		 
		// 2. 푸시 전송 체크  20231213 jeeeeey 제외
		// chkPushSnd(req,param);
		
		return "redirect:pageNavi.do?menuCd=NSV112";
	}
	
	/**
	 * 모바일 공지 상세 화면 호출
	 * @param param PK 정보
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/mobileNoticeMngtDtls.do", method= RequestMethod.GET)
	public String mobileNoticeMngtDtls(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		
		param.put("NOTICE_CLF", "M");
		Map<String, String> rsMap = mobileNoticeMngtService.getMobileNoticeDtls(param);
		List<Map<String, String>> rsList = mobileNoticeMngtService.getMobileNoticeReg(param);
		model.addAttribute("rsList", rsList);
		model.addAttribute("rsMap", rsMap);
		
		return "web/cm/mobileNoticeMngtDtls";
	}
	
	/**
	 * 모바일 공지 수정 정보 업데이트 
	 * @param param 수정 정보
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/mobileNoticeMngtUp.do", method= RequestMethod.POST)
	public String mobileNoticeMngtUp(HttpServletRequest req, @ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		
		param.put("NEW_SHOW_BGN_DE", param.get("NEW_SHOW_BGN_DE").toString().replace("-", ""));
		param.put("NEW_SHOW_END_DE", param.get("NEW_SHOW_END_DE").toString().replace("-", ""));
		mobileNoticeMngtService.getMobileNoticeMngtUp(param);
		
		// 2. 푸시 전송 체크
		chkPushSnd(req,param);
		
		return "redirect:pageNavi.do?menuCd=NSV112";
	}
	
	/**
	 * 모바일 공지 삭제
	 * @param param PK 정보
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/mobileNoticeMngtDel.do", method= RequestMethod.GET)
	public String mobileNoticeMngtDel(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		
		mobileNoticeMngtService.getMobileNoticeMngtDel(param);
		
		
		
		return "web/cm/mobileNoticeMngt";
	}
	
	/**
	 * 모바일 공지 게시/게시취소
	 * @param param PK 정보
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/mobileNoticeMngtPostUp.do", method= RequestMethod.GET)
	public String mobileNoticeMngtPostUp(@ModelAttribute Map<String, Object> param, ModelMap model, HttpServletRequest req) throws Exception {
		
		mobileNoticeMngtService.getMobileNoticeMngtPostUp(param);
		
		// 2. 푸시 전송 체크 
		// 20240807 주석처리 20250310 주석 해제(DB에서 사용안함으로 변경)		
		chkPushSnd(req,param);
		
		return "web/cm/mobileNoticeMngt";
	}

	/**
	 * 글 게시시 푸시 메시지를 전송했는지 확인하고 미전송시 전송
	 * @param param 저장 정보
	 * @return 
	 * @throws Exception 
	 */
	private void chkPushSnd(HttpServletRequest req, Map<String, Object> param){
		
		System.out.println("MobileNoticeMngtController / chkPushSnd param ====> " + param);
		
		try{
			// 2. 해당 게시물 푸시가 전송 되었는지 확인.
			Map<String,String> pushSnd = pushService.getPushSndYnNotice(param);
			if(pushSnd != null){
				if("N".equals(pushSnd.get("PUSH_SND_YN"))){
					
					// 1. push 정보 확인
					Map<String, Object> pMap = new HashMap<String, Object>();
					pMap = pushService.getPushSetInfo(param);
					
					if(pMap != null && !pMap.isEmpty()) {
					
						String pushTitle = StringUtil.nvl(String.valueOf(pMap.get("PUSH_TITLE")),"");
						String pushCont = StringUtil.nvl(String.valueOf(pMap.get("PUSH_CONT")),"");
						String pushLinkPage = StringUtil.nvl(String.valueOf(param.get("pushLinkPage")),String.valueOf(pMap.get("PUSH_LINK_PAGE")));
						String noticeTitle = StringUtil.nvl(String.valueOf(pMap.get("NOTICE_TITLE")),"");
						String noticeCont = StringUtil.nvl(String.valueOf(pMap.get("NOTICE_CONT")),"");
						String noticeLinkPage = StringUtil.nvl(String.valueOf(param.get("noticeLinkPage")),String.valueOf(pMap.get("NOTICE_LINK_PAGE")));
						String selGroupList = StringUtil.nvl(String.valueOf(param.get("selGroupList")));
						
						// 2. 푸시 값 설정
						param.put("pushTitle", pushTitle);
						param.put("pushCont", pushCont);
						param.put("pushLinkPage", pushLinkPage);
						param.put("noticeTitle", noticeTitle);
						param.put("noticeCont", noticeCont);
						param.put("noticeLinkPage", noticeLinkPage);
											
						
						param.put("grpList", selGroupList);
						pushMessageUtil.setReqData(req,param);
						
						//모바일 공지 푸시 대상자 목록 조회
						int pushCnt = 0;
						//공지의 경우 푸시 안보이도록 설정
						param.put("pushYn","Y");
						
						List<Map<String,Object>> userList = pushService.selectNoticeUserList(param);
						for(int i=0;i<userList.size();i++){
							param.put("userList", StringUtil.nvl(String.valueOf(userList.get(i).toString())));
							boolean rsFlag = pushService.sendPushData(pushMessageUtil,param);
							if(rsFlag) pushCnt++;
						}
						
						if(userList.size()==pushCnt){
							pushService.setNoticePushSndYn(param);
						}
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
