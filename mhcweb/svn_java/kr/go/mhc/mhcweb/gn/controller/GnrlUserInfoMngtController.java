package kr.go.mhc.mhcweb.gn.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.mhcweb.gn.service.GnrlUserInfoMngtService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;





/**
 * @Class Name : GnrlUserInfoMngtController.java
 * @Description : 일반대상자 정보조회 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일			수정자		수정내용
 * @	----------		------		---------------------------
 * @	2019.11.22					최초생성
 * @author theJoin
 * @since 
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */
@Controller
@RequestMapping(value = "/gn")
public class GnrlUserInfoMngtController extends DMultiActionController{

	@Resource(name = "web.gn.GnrlUserInfoMngtService")
	private GnrlUserInfoMngtService gnrlUserInfoMngtService;
	
	@ModelAttribute
	public Map initData(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	
	/**
	 * 일반대상자 정보 조회 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/gnrlUserInfo.do")
	public String gnrlUserInfo(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		return "web/gn/gnrlUserInfo";
	}
	/**
	 * 일반대상자 정보 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value="/selectGnrlUserInfoList.do")
	public @ResponseBody Map<String, Object> selectGnrlUserInfoList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = gnrlUserInfoMngtService.selectGnrlUserInfoList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	/**
	 * 일반대상자 운동일기 정보 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value="/selectGnrlUserExcsList.do")
	public @ResponseBody Map<String, Object> selectGnrlUserExcsList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = gnrlUserInfoMngtService.selectGnrlUserExcsList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	/**
	 * 일반대상자 활동 정보 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value="/selectGnrlUserActList.do")
	public @ResponseBody Map<String, Object> selectGnrlUserActList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = gnrlUserInfoMngtService.selectGnrlUserActList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	/**
	 * 일반대상자 체중 정보 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value="/selectGnrlUserBodyCompList.do")
	public @ResponseBody Map<String, Object> selectGnrlUserBodyCompList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = gnrlUserInfoMngtService.selectGnrlUserBodyCompList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	/**
	 * 일반대상자 혈압 정보 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value="/selectGnrlUserBloodPressList.do")
	public @ResponseBody Map<String, Object> selectGnrlUserBloodPressList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = gnrlUserInfoMngtService.selectGnrlUserBloodPressList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	/**
	 * 일반대상자 혈당 정보 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value="/selectGnrlUserBloodSugarList.do")
	public @ResponseBody Map<String, Object> selectGnrlUserBloodSugarList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = gnrlUserInfoMngtService.selectGnrlUserBloodSugarList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	/**
	 * 일반대상자 식사 정보 조회 1118추가 (대상자정보관리 식사일기 탭)
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/selectGnrlUserMealDiaryList.do")
	public @ResponseBody Map<String, Object> selectGnrlUserMealDiaryList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String,Object> rsMap = new HashMap<String,Object>();		
		List<Map<String, Object>> rsList = gnrlUserInfoMngtService.selectGnrlUserMealDiaryList(param);	 			
		rsMap.put("mealDiary", rsList);	
		return rsMap;
	}
	/**
	 * 일반대상자 식사일기 팝업 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value="/selectGnrlMealDiaryDtlsPop.do",method=RequestMethod.GET)
	public String selectGnrlMealDiaryDtlsPop(@ModelAttribute Map param, ModelMap model) throws Exception {
		List<Map<String, Object>> rsList = gnrlUserInfoMngtService.selectGnrlMealDiaryDtlsPop(param);
		model.addAllAttributes(param);
		model.addAttribute("mealList", rsList);
		return "web/gn/gnrlMealDiaryDtlsPop";
				
	}
	
	/**
	 * 일반대상자 이름,생년월일 ,성별 수정 
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/updateUserInfo.do", method = RequestMethod.POST)
	public void updateUserInfo(@ModelAttribute Map param, ModelMap model) throws Exception{

		 gnrlUserInfoMngtService.updateUserInfo(param); 
					
	}
	
	/**
	 * 일반대상자 수면 정보 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/selectGnrlUserSleepList.do")
	public @ResponseBody Map<String, Object> selectGnrlUserSleepList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = gnrlUserInfoMngtService.selectGnrlUserSleepList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
		
	
}