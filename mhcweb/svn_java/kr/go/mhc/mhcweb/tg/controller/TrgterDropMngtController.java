package kr.go.mhc.mhcweb.tg.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.common.util.StringUtil;
import kr.go.mhc.mhcweb.tg.service.TrgterDropMngtService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Class Name : TrgterDropMngtController.java
 * @Description : 관리자 WEB에서 사용하는 대상자탈락관리 업무를 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2018.07.12		이태석			최초생성
 *
 * @author thejoin
 * @since 2018.07.12
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
@RequestMapping(value = "/tg")
public class TrgterDropMngtController extends DMultiActionController {
	
	@Resource(name= "web.tg.TrgterDropMngtService")
	private TrgterDropMngtService trgterDropMngtService;

	@ModelAttribute
	public Map<String, Object> initDate(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	
	/**
	 * 대상자탈락관리 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/trgterDropMngt.do")
	public String trgterDropMngt(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		model.addAllAttributes(param);
		return "web/tg/trgterDropMngt";
	}
	
	/**
	 * 대상자정보관리 대상자 목록 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/trgterDropMngtList.do")
	public @ResponseBody Map<String, Object> trgterDropMngtList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		
		String searchInfo = StringUtil.nvl((String)param.get("REQ_SEARCH_INFO"));
		if(!"".equals(searchInfo)){
			param.put("searchInfoList", StringUtil.makeStringToIterator(searchInfo));
		}
		List<Map<String, Object>> rsList = trgterDropMngtService.trgterDropMngtList(param);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		
		return rsMap;
	}
	
	/**
	 * 중도탈락 업데이트
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateTrgterDrop.do", method= RequestMethod.POST)
	public void updateTrgterDrop(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		trgterDropMngtService.updateTrgterDrop(param);
	}
	
	/**
	 * 중도탈락 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectTrgterDrop.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> selectTrgterDrop(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMapDrop = trgterDropMngtService.selectTrgterDrop(param);
		return rsMapDrop;
	}
	
	/**
	 * 중도탈락 취소
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/cancelTrgterDrop.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> cancelTrgterDrop(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMapPreDropInfo = trgterDropMngtService.cancelTrgterDrop(param);
		return rsMapPreDropInfo;
	}
	
}
