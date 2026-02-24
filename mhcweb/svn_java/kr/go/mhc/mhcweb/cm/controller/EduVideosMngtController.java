package kr.go.mhc.mhcweb.cm.controller;



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
import kr.go.mhc.mhcweb.cm.service.EduVideosMngtService;

/**
 * @Class Name : EduVideosController.java
 * @Description : 관리자 WEB에서 사용하는 교육동영상 업무를 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일				수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.07.07		이태석			최초생성
 *
 * @author Thejoin
 * @since 2019.01.09
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
@RequestMapping(value = "/cm") 
public class EduVideosMngtController extends DMultiActionController{
	@ModelAttribute
	public Map initData(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}

	@Resource(name = "web.cm.EduVideosMngtService")
	private EduVideosMngtService eduVideosMngtService;
	
	
	/**
	 * 교육동영상 목록 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/eduVideos.do", method = RequestMethod.GET) 
	public String eduVideosMngt(@ModelAttribute Map param, ModelMap model) throws Exception {

		return "web/cm/eduVideosMngt";
	}
	
	/**
	 * 교육동영상 목록 조회
	 * @param param 검색 조건
	 * @return rsMap
	 * @throws Exception 
	 */
	@RequestMapping(value = "/eduVideosMngtList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> eduVideosMngtList( @ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
//		if (param.get("pagingSet[gridRowsPerPage]") != null) {
//			int gridTotalRowCount = noticeMngtService.getNoticeMngtListCount(param);
//			rsMap.put("gridTotalRowCount", gridTotalRowCount);
//		}		
		List<Map<String, String>> rsList = eduVideosMngtService.getEduVideosMngtList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));	
		return rsMap;
	}
	
	/**
	 * 교육동영상 상세 화면 호출
	 * @param param PK 정보
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/eduVideosDtls.do", method = RequestMethod.POST)
	public String eduVidosMngtDtls(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, String> rsMap = eduVideosMngtService.getEduVideosDtls(param);
		

		model.addAttribute("rsMap", rsMap);
		
		return "web/cm/eduVideosMngtDtls";
	}
	
	/**
	 * 신규 교육동영상 등록 화면 호출 
	 * @param
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/eduVideosMngtReg.do", method = RequestMethod.POST)
	public String noticeMngtReg(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		if(param.get("PAGE_CLF") != null){
			Map<String, String> rsMap = eduVideosMngtService.getEduVideosDtls(param);
			model.addAttribute("rsMap", rsMap);
			model.addAllAttributes(param);			
		}
		return "web/cm/eduVideosMngtReg";
	}
	
	/**
	 * 신규 공지_저장	
	 * @param param 저장 정보
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/eduVideosMngtRegInsert.do", method = RequestMethod.POST)
	public String eduVideosMngtRegInsert(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		eduVideosMngtService.getEduVideosMngtRegInsert(param);
		return "redirect:../pageNavi.do?menuCd=NBM190";
	}
	
	/**
	 * 교육동영상 삭제
	 * @param param PK 정보
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/eduVideosMngtDel.do", method= RequestMethod.GET)
	public String eduVideosMngtDel(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		
		eduVideosMngtService.getEduVideosMngtDel(param);
		
		
		
		return "web/cm/eduVideosMngt";
	}
	
	
}
