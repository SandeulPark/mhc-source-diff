package kr.go.mhc.mhcapp.mr.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.mhcapp.mr.service.AppGeneralCnslService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Class Name : AppGeneralCnslController.java
 * @Description : 모바일 헬스케어 App에서 사용하는 나의건강-일반상담을 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일				수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.06.29		오명빈			최초생성
 * 		
 *
 * @author gst
 * @since 2016.06.29
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
public class AppGeneralCnslController extends DMultiActionController{ 
	@Resource(name="mhcapp.mr.AppGeneralCnslService")
	private AppGeneralCnslService agcService;

	@ModelAttribute
	public Map initData(HttpServletRequest req) throws Exception{
		return super.initData(req);
	}

	/**
	 * 일반상담 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/generalCnslMain.do", method = RequestMethod.GET)
	public String noticeMain(@ModelAttribute Map param, ModelMap model) throws Exception{
		
		return "app/mr/generalCnsl";
	}	

	/**
	 * 일반상담 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@RequestMapping( value="/generalCnslList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectGeneralCnslList(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		
		List<Map<String,String>> rsList = agcService.selectGeneralCnslList(param);   
		rsMap.put("rsList", rsList);
		/*if(rsList.size()==0){
			rsMap.put("msg", getMsg("common.list.null"));
		}*/
		//rsMap.put("msg", getMsg("common.list.succ"));
		return rsMap;
	}	
	
	/**
	 * 일반상담 문의 등록
	 * @param param 검색 조건
	 * @throws Exception 
	 */
	@RequestMapping( value="/generalCnslInsert.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectNoticeList(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		//문의 등록후 
		agcService.insertGeneralCnsl(param);
		//rsMap.put("msg", getMsg("common.write.succ"));
		return rsMap;
	}	
	
	/**
	 * 일반상담 상세 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@RequestMapping( value="/cnslDetailList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectCnslDetailList(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		
		List<Map<String,String>> rsList = agcService.selectCnslDetailList(param);   
		if(rsList.get(0).get("CNSLER_ID") != null && rsList.get(0).get("CNSLER_ID") != ""){
			agcService.updateCnslCnfm(param);
		}
		rsMap.put("rsList", rsList);
		
		return rsMap;
	}	
}
