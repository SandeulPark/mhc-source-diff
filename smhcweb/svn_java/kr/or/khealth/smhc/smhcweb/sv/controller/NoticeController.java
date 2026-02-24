package kr.or.khealth.smhc.smhcweb.sv.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.or.khealth.smhc.smhcweb.cm.service.PushService;
import kr.or.khealth.smhc.common.util.StringUtil;
import kr.or.khealth.smhc.common.DMultiActionController;
import kr.or.khealth.smhc.smhcweb.sv.service.CommunityService;
import kr.or.khealth.smhc.smhcweb.sv.service.NoticeService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Class Name : NoticeController.java
 * @Description : 관리자 WEB에서 사용하는 긴급 공지 사항 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2021.08.04		윤찬호			최초생성
 *
 * @author chyoon
 * @since 2021.08.04
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
@RequestMapping(value = "/sv")
public class NoticeController extends DMultiActionController{

	@Resource(name="web.sv.NoticeService")
	private NoticeService noticeService;
	
	
	@ModelAttribute
	public Map<String, Object> initDate(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	
	/**
	 * 긴급 공지 사항 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/noticeMngt.do")
	public String noticeMngt(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		return "web/sv/noticeMngt";
	}
	
	/**
	 * 긴급 공지 사항 리스트 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/selectNoticeList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> selectNoticeList(@ModelAttribute Map<String, Object> param , ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		if (param.get("pagingSet[gridRowsPerPage]") != null) {
			int gridTotalRowCount = noticeService.selectNoticeListCount(param);
			rsMap.put("gridTotalRowCount", gridTotalRowCount);
		}
		
		List<Map<String, String>> rsList = noticeService.selectNoticeList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
		
	}
	
	/**
	 * 긴급 공지 사항  등록페이지
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/noticeInsertPage.do", method = RequestMethod.POST)
	public String noticeInsertPage(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		model.addAllAttributes(param);			
		return "web/sv/noticeInsert";
	}
	
	/**
	 * 긴급 공지 사항 중복 확인
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/noticeDupChk.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> noticeDupChk(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String,Object> rsMap = new HashMap<String,Object>();
		try {
		 rsMap = noticeService.noticeDupChk(param);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return rsMap;	
	}
	
	/**
	 * 긴급 공지 사항 등록 및 수정
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/noticeSave.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> noticeSave(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		
		String pageType = StringUtil.nvl(String.valueOf(param.get("pageType")),"N");
				
		int rsInt = 0;		
		try{
			//긴급 공지사항  등록
			if(pageType.equals("N")) {
				rsInt = noticeService.noticeReg(param);
			}else {
				rsInt = noticeService.noticeUpdate(param);
			}					
			rsMap.put("chkYn", "Y");
		}catch(Exception e){
			rsMap.put("chkYn", "N");
		}		
		return rsMap;
		
	}
	
	/**
	 * 긴급 공지 사항  상세페이지 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/noticeDtlsPage.do", method = RequestMethod.POST)
	public String noticeDtlsPage(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		model.addAllAttributes(param);			
		return "web/sv/noticeDtls";
	}
	
	/**
	 * 긴급 공지 사항 상세 페이지 내용
	 * @param param
	 * @param model
	 * @param req
	 * @return
	 */
	@RequestMapping(value= "/selectNoticeDetail.do")
	public @ResponseBody Map<String,Object> selectNoticeDetail(@ModelAttribute Map<String, Object> param, ModelMap model, HttpServletRequest req){
		Map<String,Object> rsMap = new HashMap<String,Object>();
		try{
			Map<String, Object> noticeData = noticeService.selectNoticeDetailData(param);
			
			
			rsMap.put("noticeData", noticeData);
			rsMap.put("chkYn", "Y");
		}catch(Exception e){
			rsMap.put("chkYn", "N");
		}
		return rsMap;
	}
	
	
	/**
	 * 긴급 공지 사항  수정 페이지
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/noticeEditPage.do", method = RequestMethod.POST)
	public String noticeEditPage(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		model.addAllAttributes(param);			
		return "web/sv/noticeInsert";
	}
	/**
	 * 긴급 공지 사항  수정시 기본데이터 가저오기
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/selectNoticeData.do")
	public @ResponseBody Map<String,Object> selectBoardData(@ModelAttribute Map<String, Object> param, ModelMap model, HttpServletRequest req){
		Map<String,Object> rsMap = new HashMap<String,Object>();
		
		try{
			Map<String,Object> data = noticeService.selectNoticeData(param);			
			rsMap.put("data", data);
			rsMap.put("chkYn", "Y");
		}catch(Exception e){
			rsMap.put("chkYn", "N");
		}
		return rsMap;
	}
	
	/**
	 * 긴급 공지 사항  수정시 기본데이터 가저오기
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/deleteNotice.do")
	public @ResponseBody Map<String,Object> deleteNotice(@ModelAttribute Map<String, Object> param, ModelMap model, HttpServletRequest req){
		Map<String,Object> rsMap = new HashMap<String,Object>();
		int rsInt = 0;
		try{
			rsInt = noticeService.deleteNotice(param);
			rsMap.put("chkYn", "Y");
		}catch(Exception e){
			rsMap.put("chkYn", "N");
		}
		return rsMap;
	}
	
	
}
