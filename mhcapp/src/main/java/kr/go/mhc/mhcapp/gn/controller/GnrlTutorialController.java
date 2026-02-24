package kr.go.mhc.mhcapp.gn.controller;

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
import kr.go.mhc.mhcapp.gn.service.GnrlTutorialService;

/**
 * @Class Name : GnrlTutorialController.java
 * @Description : 보편건강 App에서 사용하는 튜토리얼을 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2021.10.29		chyoon			최초생성
 * 		
 *
 * @author chyoon
 * @since 2021.10.29
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */
@Controller
@RequestMapping(value="/gn")
public class GnrlTutorialController extends DMultiActionController{
	
	@Resource(name="mhcapp.gn.gnrlTutorialService")
	private GnrlTutorialService gnrlTutorialService;

	@ModelAttribute
	public Map initData(HttpServletRequest req) throws Exception{
		return super.initData(req);
	}
	
	/**
	 * 튜토리얼 사용 여부 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */	
	@RequestMapping( value="/tutoUseYn.do", method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> tutoUseYn(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		try{
			List<Map<String,String>> rsList = gnrlTutorialService.tutoUseYn(param); 
			rsMap.put("rsList", rsList);
		}catch(Exception e){
			e.printStackTrace();
		}
		return rsMap;
	}	
	
	/**
	 * 튜토리얼 실행 여부 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */	
	@RequestMapping( value="/checkTutoYn.do", method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> checkTutoYn(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		try{
			List<Map<String,String>> rsList = gnrlTutorialService.checkTutoYn(param); 
			rsMap.put("rsList", rsList);
		}catch(Exception e){
			e.printStackTrace();
		}
		return rsMap;
	}	
	
	
	/**
	 * 튜토리얼 확인 업데이트
	 * @param 
	 * @return 
	 * @throws Exception 
	 */	
	@RequestMapping( value="/updateTutoYn.do", method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> updateTutoYn(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		String chkYn = "N";
		
		try{
			gnrlTutorialService.updateTutoYn(param);
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}
	
	/**
	 * 튜토리얼 초기화
	 * @param 
	 * @return 
	 * @throws Exception 
	 */	
	@RequestMapping( value="/resetTutorial.do", method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> resetTutorial(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();	
		try{
			gnrlTutorialService.resetTutorial(param);			
		}catch(Exception e){
			e.printStackTrace();
		}		
		return rsMap;
	}
	
}
