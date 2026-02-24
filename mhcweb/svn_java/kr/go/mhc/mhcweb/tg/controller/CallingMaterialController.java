package kr.go.mhc.mhcweb.tg.controller;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.common.util.StringUtil;
import kr.go.mhc.mhcweb.tg.service.CallingMaterialService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Class Name : callingMaterialController.java
 * @Description : 관리자 WEB에서 통합관제 시스템 소명자료를 관리 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2020.07.23      최성진          최초개발
 *
 * @author
 * @author
 * @since 2020.07.23
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
@RequestMapping(value = "/tg")
public class CallingMaterialController extends DMultiActionController{
	
	@Resource(name = "web.tg.CallingMaterialService")
	private CallingMaterialService callingMaterialService;
	
	@ModelAttribute
	public Map initData(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	
	/**
	 * 소명자료 관리 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/callingMaterial.do")
	public String callingMaterial(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {
		param.put("CMMN_CD","TC_CM_ORG");
		if("HLTH099".equals(param.get("SESS_AUTH_TYPE"))){
			param.put("ORG_CD", param.get("SESS_ORG_CD")); // 접속자가 보건소 일때 보건소 코드 세팅
		}
		Map<String, String> rsMap = callingMaterialService.getCallingMaterialEachOtherCnt(param);
		List<Map<String, String>> selList = cmmnService.selectCmmnCd(param);
		model.addAttribute("selList", selList);
		model.addAttribute("rsMap", rsMap);

		return "web/tg/callingMaterial";
	}

	/**
	 * 소명 대상자 현황 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getCallingMaterialEachOtherCnt.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> getCallingMaterialEachOtherCnt(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String, String> rsMap = callingMaterialService.getCallingMaterialEachOtherCnt(param);

		return rsMap;
	}

	/**
	 *  소명자료 목록 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/callingMaterialList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> callingMaterialList(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String, String>> rsList = callingMaterialService.getCallingMaterialList(param);

		for (int i = 0; i < rsList.size(); i++) {
			String judgmentState = rsList.get(i).get("JUDGMENT_STATE");
			switch (judgmentState) {
				case "1" :
					rsList.get(i).put("JUDGMENT_STATE_NM", "적정");
					break;
				case "2" :
					rsList.get(i).put("JUDGMENT_STATE_NM", "부적정");
					break;
				case "3" :
					rsList.get(i).put("JUDGMENT_STATE_NM", "지연");
					break;
				case "4" :
					rsList.get(i).put("JUDGMENT_STATE_NM", "미입력");
					break;
				case "5" :
					rsList.get(i).put("JUDGMENT_STATE_NM", "입력");
					break;
			}
		}
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));

		return rsMap;
	}

	/**
	 *  소명자료 엑셀 다운 로드 팝업 호출
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/callingMaterialDownloadPop.do")
	public String callingMaterialDownloadPop(@ModelAttribute Map param, ModelMap model) throws Exception {
		return "web/tg/callingMaterialDownloadPop";
	}
	/**
	 *  소명자료 엑셀 업로드 팝업 호출
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/callingMaterialUploadPop.do")
	public String callingMaterialUploadPop(@ModelAttribute Map param, ModelMap model) throws Exception {
		return "web/tg/callingMaterialUploadPop";
	}

	/**
	 * 소명자료 엑셀 업로드 유효성 체크
	 * @param
	 * @return validData
	 * @throws Exception
	 */
	@RequestMapping(value = "/callingMaterialExcelValidChk.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> callingMaterialExcelValidChk(@ModelAttribute Map param, ModelMap model) throws Exception {
		Map<Integer,List<Integer>> validChkMap = callingMaterialExcelValidChk(param);
		Map<String,Object> rsMap = new HashMap<String, Object>();
		rsMap.put("id", param.get("gridId"));
		rsMap.put("validChkMap", validChkMap);
		if(validChkMap == null){
			rsMap.put("validChkMap", "complete");
		}
		return rsMap;
	}

	/**
	 * 소명 자료등록 저장
	 * @param param 소명 자료 정보 List
	 * @return totalData
	 * @throws Exception
	 */
	@RequestMapping(value = "/callingMaterialExcelGridInsert.do", method = RequestMethod.POST)
	public Map<String, Integer> callingMaterialExcelGridInsert(@ModelAttribute Map param, ModelMap model, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
		List<Map<String, Object>> paramList = callingMaterialExcelExportData(param);
		int insertCount = callingMaterialService.callingMaterialExcelGridInsert(paramList);
		Map<String, Integer> rsMap = new HashMap<String, Integer>();
		rsMap.put("insertPreCount", paramList.size());
		rsMap.put("insertCount", insertCount);
		return rsMap;
	}

	/**
	 * 소명 자료 등록
	 * @param param
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/updateCallingMaterInfo.do")
	public @ResponseBody Map<String, Object> updPopNoticeCnfm(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		String chkYn = "Y";
		try{
			callingMaterialService.updateCallingMaterInfo(param);
		}catch(Exception e){
			chkYn = "N";
			e.printStackTrace();
		}
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}

	/**
	 * 소명 자료 삭제
	 * @param param
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteCallingMaterInfo.do", method = RequestMethod.POST)
	public @ResponseBody int deleteCallingMaterInfo(@ModelAttribute Map <String, Object> param, ModelMap model) throws Exception {
		int delCnt = callingMaterialService.deleteCallingMaterInfo(param);
		return delCnt;
	}
	/**
	 * 엑셀 등록시 중복 체크
	 * @param param
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value = "/callingMaterialDupCheck.do", method = RequestMethod.POST)
	public @ResponseBody int callingMaterialDupCheck(@ModelAttribute Map <String, Object> param, ModelMap model) throws Exception {
		int dupCnt = callingMaterialService.callingMaterialDupCheck(param);
		return dupCnt;
	}


}
