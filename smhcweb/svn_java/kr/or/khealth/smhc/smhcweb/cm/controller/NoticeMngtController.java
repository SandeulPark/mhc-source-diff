package kr.or.khealth.smhc.smhcweb.cm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.or.khealth.smhc.common.DMultiActionController;
import kr.or.khealth.smhc.smhcweb.cm.service.NoticeMngtService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;



/**
 * @Class Name : TrgtSelMngtController.java
 * @Description : 관리자 WEB에서 사용하는 공지관리 업무를 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일				수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.07.07		이태석			최초생성
 *
 * @author gst
 * @since 2016.07.07
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */
@Controller
public class NoticeMngtController extends DMultiActionController {

	@Resource(name = "web.cm.NoticeMngtService")
	private NoticeMngtService noticeMngtService;

	@ModelAttribute
	public Map initData(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}

	/**
	 * 공지관리 목록 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/noticeMngt.do", method = RequestMethod.GET) 
	public String noticeMngt(@ModelAttribute Map param, ModelMap model) throws Exception {

		return "web/cm/noticeMngt";
	}

	/**
	 * 공지관리 목록 조회
	 * @param param 검색 조건
	 * @return rsMap
	 * @throws Exception 
	 */
	@RequestMapping(value = "/noticeMngtList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> noticeMngtList( @ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		if (param.get("pagingSet[gridRowsPerPage]") != null) {
			int gridTotalRowCount = noticeMngtService.getNoticeMngtListCount(param);
			rsMap.put("gridTotalRowCount", gridTotalRowCount);
		}		
		List<Map<String, String>> rsList = noticeMngtService.getNoticeMngtList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));	
		return rsMap;
	}

	/**
	 * 신규 공지 등록 화면 호출 
	 * @param
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/noticeMngtReg.do", method = RequestMethod.POST)
	public String noticeMngtReg(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		if(param.get("PAGE_CLF") != null){
			Map<String, String> rsMap = noticeMngtService.getNoticeDtls(param);
			model.addAttribute("rsMap", rsMap);
			model.addAllAttributes(param);			
		}
		return "web/cm/noticeMngtReg";
	}

	/**
	 * 신규 공지_저장	
	 * @param param 저장 정보
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/noticeMngtRegInsert.do", method = RequestMethod.POST)
	public String noticeMngtRegInsert(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		param.put("NEW_SHOW_BGN_DE", param.get("NEW_SHOW_BGN_DE").toString().replace("-", ""));
		noticeMngtService.getNoticeMngtRegInsert(param);
		return "redirect:pageNavi.do?menuCd=NSV111";
	}

	/**
	 * 공지 상세 화면 호출
	 * @param param PK 정보
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/noticeDtls.do", method = RequestMethod.POST)
	public String noticeDtls(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, String> rsMap = noticeMngtService.getNoticeDtls(param);
		List<Map<String, String>> befAftNoticeList = noticeMngtService.getBefAftNoticeList(param);
		
		String returnUrl = "";
		
		if(rsMap.get("USER_ORG_CD").toString().equals("K001")){
			returnUrl = "web/cm/noticeMngtDtls";
		}else{
			returnUrl = "web/cm/noticeMngtCommentDtls";
		}
		
		model.addAttribute("rsMap", rsMap);
		model.addAttribute("befAftNoticeList", befAftNoticeList);
		return returnUrl;
	}
	

	
	/**
	 * 공지 수정 화면 호출
	 * @param param 상세화면 정보
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/noticeMngtUpView.do", method = RequestMethod.POST)
	public String noticeMngtUpView(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {

		model.addAttribute("rsMap", param);
		return "web/cm/noticeMngtUpView";
	}

	/**
	 * 공지 수정 정보 업데이트 
	 * @param param 수정 정보
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/noticeMngtUp.do", method = RequestMethod.POST)
	public String noticeMngtUp(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		param.put("NEW_SHOW_BGN_DE", param.get("NEW_SHOW_BGN_DE").toString().replace("-", ""));
		noticeMngtService.getNoticeMngtUp(param);
		return "redirect:pageNavi.do?menuCd=NSV111";
	}

	/**
	 * 공지 미사용(삭제)
	 * @param param PK 정보
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/noticeMngtDel.do", method = RequestMethod.POST)
	public String noticeMngtDel(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		noticeMngtService.getNoticeMngtDel(param);
		return "web/cm/noticeMngt";
	}
	

	/**
	 * 공지사항 댓글 불러오기
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/selectNoticeCommentList.do")
	public @ResponseBody Map<String,Object> selectNoticeCommentList(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
			
		List<Map<String,Object>> rsList = noticeMngtService.selectNoticeCommentList(param); 
		rsMap.putAll(param);
		rsMap.put("rsList", rsList);
		if(rsList.size()==0){

		}else{
		
		}
		return rsMap;
	}	
	
	/**
	 * 공지사항 댓글 등록
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/insertNoticeComment.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> insertNoticeCommenct(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		
		System.out.println("param :::::::::: " + param);
		
		noticeMngtService.insertNoticeComment(param); 
		rsMap.putAll(param);
		return rsMap;
	}	
	
	
	/**
	 * 공지사항 댓글수정
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/updateNoticeComment.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> updateNoticeComment(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
			
		noticeMngtService.updateNoticeComment(param); 
		rsMap.putAll(param);


		return rsMap;
	}	
	
	
	/**
	 * 공지사항 댓글삭제
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/deleteNoticeComment.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> deleteNoticeComment(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		
		String chkYn = "N";
		try{
			noticeMngtService.deleteNoticeComment(param);
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}	
	
	
	
}
