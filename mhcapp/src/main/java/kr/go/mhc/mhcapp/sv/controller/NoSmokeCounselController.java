package kr.go.mhc.mhcapp.sv.controller;

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
import kr.go.mhc.mhcapp.sv.service.NoSmokeCounselService;

/**
 * @Class Name : NoSmokeCounselController.java
 * @Description : 모바일 헬스케어 App에서 사용하는 집중상담 (금연절주) Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		------		---------------------------
 * @	2016.07.01		허광일			최초생성
 *
 * @author gst
 * @since 2016.07.08
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
public class NoSmokeCounselController extends DMultiActionController{ 
	
	@Resource(name="mhcapp.sv.NoSmokeCounselService")
	private NoSmokeCounselService noSmokeCounselService;

	@ModelAttribute
	public Map initData(HttpServletRequest req) throws Exception{
		return super.initData(req);
	}

	/**
	 * 금연절주 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/noSmokeCounselMain.do", method = RequestMethod.GET)
	public String noSmokeCounselMain(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		model.addAllAttributes(param);
		return "app/sv/nosmokeCounsel";
	}
	
	/**
	 * 리포트 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 **/
	@RequestMapping( value="/noSmokeCounselList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectNoSmokeCounselList(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		
		List<Map<String,String>> rsList = noSmokeCounselService.selectNoSmokeCounselList(param);   
		
		rsMap.put("rsList", rsList);
		return rsMap;
	}	
	
	/**
	 * 금연절주 상세 내용 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/noSmokeCounselDtls.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> noSmokeCounselDtls(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{

		Map<String,Object> rsMap = new HashMap<String,Object>();
		Map<String,Object> rsMapTmp = new HashMap<String,Object>();
			
		List<Map<String,String>> rsList = noSmokeCounselService.selectNoSmokeCounselDtls(param);  // 내용
		List<Map<String,String>> rsListFile = noSmokeCounselService.selectNoSmokeCounselAddFiles(param); // 첨부
		
		rsMapTmp.put("rsList", rsList);
		rsMapTmp.put("rsListFile", rsListFile);
		rsMapTmp.putAll(param);
		
		rsMap.put("rsList", rsMapTmp);
		
		return rsMap;
	}	
}