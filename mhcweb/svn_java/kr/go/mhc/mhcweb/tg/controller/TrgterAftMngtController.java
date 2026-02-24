package kr.go.mhc.mhcweb.tg.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.common.util.StringUtil;
import kr.go.mhc.mhcweb.tg.service.TrgterAftMngtService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Class Name : TrgterAftMngtController.java
 * @Description : 관리자 WEB에서 사용하는 대상자정보관리 업무를 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2018.10.02		나연이			최초생성
 *
 * @author thejoin
 * @since 2018.10.02
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
@RequestMapping(value = "/tg")
public class TrgterAftMngtController extends DMultiActionController {

	@Resource(name= "web.tg.TrgterAftMngtService")
	private TrgterAftMngtService trgterAftMngtService;
	
	
	@ModelAttribute
	public Map<String, Object> initDate(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	
	/**
	 * 추후관리 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/trgterAftMngt.do")
	public String tgrterInfoMngt(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		return "web/tg/trgterAftMngt";
	}
	
	/**
	 * 추후관리 서비스 일정 화면 호출
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/trgterAftMngtSch.do")
	public String trgterAftMngtSch(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		return "web/tg/trgterAftMngtSch";
	}
	
	/**
	 * 추후관리 대상자 목록 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/trgterAftMngtList.do")
	public @ResponseBody Map<String, Object> trgterAftMngtList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = trgterAftMngtService.trgterAftMngtList(param);
		rsMap.put("id", param.get("id"));
		rsMap.put("rsList", rsList);
		return rsMap;
	}
	
	/**
	 * 추후관리 대상자 상세 정보 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value=" /trgterAftMngtDtls.do")
	public @ResponseBody Map<String, Object> trgterAftMngtDtls(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = trgterAftMngtService.trgterAftMngtDtls(param);
		return rsMap;
	}
	
	/**
	 * 추후관리 대상자 활동량 및 컨텐츠 정보 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value=" /trgterAftMngtInfo.do")
	public @ResponseBody Map<String, Object> trgterAftMngtInfo(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = trgterAftMngtService.trgterAftMngtInfo(param);
		rsMap.put("id", param.get("id"));
		rsMap.put("rsList", rsList);
		return rsMap;
	}
	
	/**
	 * 추후관리 스케줄 생성
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value=" /createAfterSchdule.do")
	public @ResponseBody Map<String, Object> createAfterSchdule(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = new ArrayList<Map<String, Object>>();
		String chkYn = "Y";
		try{
			String svcMngtNo = StringUtil.nvl(String.valueOf(param.get("SVC_MNGT_NO")));
			List<Map<String,String>> svcIter = StringUtil.makeStringToIterator(svcMngtNo);
			param.put("svcIter", svcIter);
			//System.out.println("svcIter>>>"+svcIter);
			trgterAftMngtService.createAfterSchedule(param);
		}catch(Exception e){
			e.printStackTrace();
			chkYn = "N";
		}
		rsMap.put("rsList", rsList);
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}
	
	/**
	 * 추후관리 종료 여부 업데이트
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/updateAftMngtEndYn.do")
	public @ResponseBody Map<String, Object> updateAftMngtEndYn(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		String chkYn = "Y";
		try{
			trgterAftMngtService.updateAftMngtEndYn(param);
		}catch(Exception e){
			e.printStackTrace();
			chkYn = "N";
		}
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}
	
	/**
	 * 추후관리 서비스 제공 리스트 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/trgterAftMngtSchList.do")
	public @ResponseBody Map<String, Object> trgterAfterMngtSchList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = trgterAftMngtService.trgterAfterMngtSchList(param);
		rsMap.put("id", param.get("id"));
		rsMap.put("rsList", rsList);
		return rsMap;
	}
	
	/**
	 * 추후관리 서비스 종료
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/trgterAftMngtEnd.do")
	public @ResponseBody Map<String, Object> trgterAftMngtEnd(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String, Object>();
		String chkYn = "Y";
		try{
			trgterAftMngtService.trgterAftMngtEnd(param);
		}catch(Exception e){
			e.printStackTrace();
			chkYn = "N";
		}
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}
	
}
