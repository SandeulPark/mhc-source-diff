package kr.go.mhc.mhcweb.sm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.mhcweb.sm.service.OpenApiMngtService;

import org.apache.catalina.connector.Request;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Class Name : OpenApiMngtController.java
 * @Description : 오픈api 게시판 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일			수정자		수정내용
 * @	----------	------	---------------------------
 * @	2017.11.27	나연이		최초생성
 * @author theJoin
 * @since 2017.11.27
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
@RequestMapping(value = "/sm")
public class OpenApiMngtController extends DMultiActionController {
	
	@Resource(name = "web.sm.OpenApiMngtService")
	private OpenApiMngtService openApiMngtService;
	
	@ModelAttribute
	public Map initData(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	
	/**
	 * 연동신청확인 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/linkEquipReqMngt.do", method = RequestMethod.GET)
	public String linkEquipReqMngt(@ModelAttribute Map param, ModelMap model) throws Exception {
		return "web/sm/linkEquipReqMngt";
	}
	
	/**
	 * 연동신청 리스트 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectLinkEquipReqList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectLinkEquipReqList(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String, Object>> rsList = openApiMngtService.selectLinkEquipReqList(param);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 연동신청 상세 화면 호출
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectLinkEquipReqDtls.do", method = RequestMethod.POST)
	public String selectLinkEquipReqDtls(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		Map<String,Object> dtlsMap = openApiMngtService.selectLinkEquipReqDtls(param);
		
		model.addAttribute("dtlsMap",dtlsMap);
		model.addAllAttributes(param);
		return "web/sm/linkEquipReqDtls";
	}

	/**
	 * 연동기기 리스트 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectLinkEquipModelList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectLinkEquipModelList(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String, Object>> rsList = openApiMngtService.selectLinkEquipModelList(param);
		
		rsMap.put("rsList",rsList);
		rsMap.put("id", "grid1");
		return rsMap;
	}
	
	/**
	 * 연동기기 등록 팝업 상세 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectLinkEquipReqPop.do", method = RequestMethod.GET)
	public String selectLinkEquipReqPop(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = openApiMngtService.selectLinkEquipReqPop(param);
		
		model.addAttribute("rsMap", rsMap);
		return "web/sm/linkEquipReqPop";
	}
	
	/**
	 * 연동기기 신청 결과 저장
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateLinkEquipReq.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> updateLinkEquipReq(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		String chkYn = "N";
		int rsInt=0;
		try{
			rsInt = openApiMngtService.updateLinkEquipReq(param);
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		model.addAttribute("rsInt", rsInt);
		model.addAttribute("chkYn", chkYn);
		return model;
	}
	
	
	/**
	 * 검증신청 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/vrfcReqMngt.do", method = RequestMethod.GET)
	public String vrfcReqMngt(@ModelAttribute Map param, ModelMap model) throws Exception {
		return "web/sm/vrfcReqMngt";
	}
	
	/**
	 * 검증신청 확인 리스트 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectVrfcReqList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectVrfcReqList(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String,Object>> rsList = openApiMngtService.selectVrfcReqList(param);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", "grid1");
		return rsMap;
	}
	
	
	/**
	 * 검증신청 확인 상세 화면 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectVrfcReqDtls.do", method = RequestMethod.POST)
	public String selectVrfcReqDtls(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = openApiMngtService.selectVrfcReqDtls(param);
		
		model.addAttribute("rsMap", rsMap);
		model.addAllAttributes(param);
		return "web/sm/vrfcReqDtls";
	}
	
	/**
	 * 검증신청 결과 저장
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateVrfcReq.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> updateVrfcReq(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		String chkYn = "N";
		int rsInt=0;
		try{
			rsInt = openApiMngtService.updateVrfcReq(param);
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		model.addAttribute("rsInt", rsInt);
		model.addAttribute("chkYn", chkYn);
		return model;
	}
	
	/**
	 * 연동로그 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/pairingLog.do", method = RequestMethod.GET)
	public String pairingLog(@ModelAttribute Map param, ModelMap model) throws Exception {
		return "web/sm/pairingLog";
	}
	
	/**
	 * 연동로그 목록
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectPairingLog.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectPairingLog(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String,Object>> rsList = openApiMngtService.selectPairingLog(param);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", "grid");
		return rsMap;
	}
}
