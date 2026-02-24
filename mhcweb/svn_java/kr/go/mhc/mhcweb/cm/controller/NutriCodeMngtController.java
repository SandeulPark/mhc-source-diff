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
import kr.go.mhc.mhcweb.cm.service.NutriCodeMngtService;

/**
 * @Class Name : NutriCodeMngtController.java
 * @Description : 관리자 WEB에서 영양코드를 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2019.07.03		오샘이			최초생성
 *
 * @author thejoin
 * @since 2019.07.03
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
@RequestMapping(value = "/cm")
public class NutriCodeMngtController  extends DMultiActionController {
	@Resource(name = "web.cm.NutriCodeMngtService")
	private NutriCodeMngtService nutriCodeMngtService;
	
	@ModelAttribute
	public Map initData(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	
	/**
	 * 영양코드 조회 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/nutriCodeList.do")
	public String nutriCodeList(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {
		return "web/cm/nutriCodeList";
	}
	
	/**
	 * 영양코드 정보 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/getNutriCodeList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> getNutriCodeList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, String>> rsList = nutriCodeMngtService.getNutriCodeList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}
	
	/**
	 * 영양소 상세정보 팝업 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/nutrientInfoPop.do", method = RequestMethod.GET)
	public String nutrientList(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {
		
		model.addAttribute("SCH_FOOD_CD", param.get("SCH_FOOD_CD"));
		model.addAttribute("CRUD_DIV"   , param.get("CRUD_DIV"));
		

		return "web/cm/nutrientInfoPop";
	}	
	
	
	
	/**
	 * 영양소 정보 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/getNutrientList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> getNutrientList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, String>> rsList = nutriCodeMngtService.getNutrientList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}	

	
	/**
	 * 영양코드 신청 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/nutriCodeMngt.do")
	public String nutriCodeRequest(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {
		return "web/cm/nutriCodeMngt";
	}
	
	
	/**
	 * 영양코드 정보 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/getNutriCodeReqMngtList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> getNutriCodeReqMngtList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {

		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, String>> rsList = nutriCodeMngtService.getNutriCodeReqMngtList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}	

	
	/**
	 * 영양소 중복조회 팝업 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/nutrientInfoDupPop.do", method = RequestMethod.GET)
	public String nutrientInfoDupPop(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {
		
		model.addAttribute("SEL_FOOD_NM", param.get("SEL_FOOD_NM"));

		return "web/cm/nutriCodeDuplicationChkPop";
	}	
	
		
	
	/**
	 * 영양소 중복 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/getNutrientDupList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> getNutrientDupList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {

		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, String>> rsList = nutriCodeMngtService.getNutrientDupList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}		
	
	
	/**
	 * 가공식품 정보 신규 입력
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/insertProcFoodReq.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> insertProcFoodReq(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		
		Map<String, Object> rsMap = new HashMap<String, Object>();

		int rsInt = nutriCodeMngtService.insertProcFoodReq(param);
		rsMap.put("rsInt", rsInt);
		return rsMap;
	}	
	
	
	/**
	 * 가공식품 정보 저장
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/updateProcFoodReq.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> updateProcFoodReq(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		int rsInt = nutriCodeMngtService.updateProcFoodReq(param);
		rsMap.put("rsInt", rsInt);
		return rsMap;
	}	
	
	
	/**
	 * 가공식품 등록 승인 팝업 호출
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/procFoodReqMngtPop.do", method= RequestMethod.GET)
	public String mngterRegMngtPop(@ModelAttribute Map param, ModelMap model) throws Exception {
		model.addAttribute("approvalIndex", param.get("approvalBtn").toString().substring(12));
		model.addAttribute("tabVal", param.get("tabVal"));
		model.addAttribute("selFoodCd", param.get("selFoodCd"));
		return "web/cm/procFoodReqMngtPop";
	}
	
	/**
	 * 가공식품 관리자 등록 승인
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/updateProcFoodReqApprovalYn.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> updateProcFoodReqApprovalYn(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		int rsInt = nutriCodeMngtService.updateProcFoodReqApprovalYn(param);
		rsMap.put("rsInt", rsInt);
		return rsMap;
	}	
	
	/**
	 * 첨부파일 정보 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/getNutriAttchFileList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> getAttchFileList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, String>> rsContAttch = nutriCodeMngtService.getNutriAttchFileList(param);
		rsMap.put("rsContAttch", rsContAttch);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}	
	
	
	/**
	 * 조리식품 정보 신규 입력
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/insertCookFoodReq.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> insertCookFoodReq(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		
		Map<String, Object> rsMap = new HashMap<String, Object>();

		int rsInt = nutriCodeMngtService.insertCookFoodReq(param);
		rsMap.put("rsInt", rsInt);
		return rsMap;
	}	
	
	
	/**
	 * 조리식품 정보 저장
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/updateCookFoodReq.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> updateCookFoodReq(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		int rsInt = nutriCodeMngtService.updateCookFoodReq(param);
		rsMap.put("rsInt", rsInt);
		return rsMap;
	}	
	
	
	/**
	 * 조리식품 등록 승인 팝업 호출
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/cookFoodReqMngtPop.do", method= RequestMethod.GET)
	public String cookFoodReqMngtPop(@ModelAttribute Map param, ModelMap model) throws Exception {
		model.addAttribute("approvalIndex", param.get("approvalBtn").toString().substring(12));
		return "web/cm/cookFoodReqMngtPop";
	}
	
	/**
	 * 조리식품 신청 처리상태 변경
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/updateCookFoodReqApprovalYn.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> updateCookFoodReqApprovalYn(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		int rsInt = nutriCodeMngtService.updateCookFoodReqApprovalYn(param);
		rsMap.put("rsInt", rsInt);
		return rsMap;
	}	
	
	/**
	 * 조리식품 일괄신청 유효성 체크 
	 * @param 
	 * @return validData
	 * @throws Exception 
	 */
	@RequestMapping(value = "/excelValidFoodCookReqChk.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> excelValidFoodCookReqChk(@ModelAttribute Map param, ModelMap model) throws Exception {		

		Map<Integer,List<Integer>> validChkMap = excelValidFoodCookReqChk(param);
		Map<String,Object> rsMap = new HashMap<String, Object>();
		rsMap.put("id", param.get("gridId"));
		rsMap.put("validChkMap", validChkMap);
		if(validChkMap == null){
			rsMap.put("validChkMap", "complete");
		}
		return rsMap;
	}	
	
	
	/**
	 * 조리식품 일괄신청 데이터 저장
	 * @param param 예비대상자 정보 List
	 * @return totalData
	 * @throws Exception 
	 */
	@RequestMapping(value = "/importExcelGridCookReqInsert.do", method = RequestMethod.POST)
	public Map<String, Integer> importExcelGridCookReqInsert(@ModelAttribute Map param, ModelMap model) throws Exception {
		List<Map<String, Object>> paramList = excelExportDataReqList(param);		
		
		int insertCount = nutriCodeMngtService.importExcelGridCookReqInsert(paramList);
		
		Map<String, Integer> rsMap = new HashMap<String, Integer>();

		rsMap.put("insertPreCount", paramList.size());
		rsMap.put("insertCount", insertCount);
		return rsMap;
	}
		
	
	/**
	 * 조리식품 검증 결과 유효성 체크
	 * @param 
	 * @return validData
	 * @throws Exception 
	 */
	@RequestMapping(value = "/excelValidFoodCookCompChk.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> excelValidFoodCookCompChk(@ModelAttribute Map param, ModelMap model) throws Exception {		
		Map<Integer,List<Integer>> validChkMap = excelValidFoodCookCompChk(param);
		Map<String,Object> rsMap = new HashMap<String, Object>();
		rsMap.put("id", param.get("gridId"));
		rsMap.put("validChkMap", validChkMap);
		if(validChkMap == null){
			rsMap.put("validChkMap", "complete");
		}
		return rsMap;
	}	
	
	
	/**
	 * 조리식품 검증 결과 일괄 저장
	 * @param param 예비대상자 정보 List
	 * @return totalData
	 * @throws Exception 
	 */
	@RequestMapping(value = "/importExcelGridCookCompInsert.do", method = RequestMethod.POST)
	public Map<String, Integer> importExcelGridCookCompInsert(@ModelAttribute Map param, ModelMap model) throws Exception {
		List<Map<String, Object>> paramList = excelExportDataCompList(param);		

		int insertCount = nutriCodeMngtService.importExcelGridCookCompInsert(paramList);
		
		Map<String, Integer> rsMap = new HashMap<String, Integer>();
		rsMap.put("insertPreCount", paramList.size());
		rsMap.put("insertCount", insertCount);
		return rsMap;
	}	
	
	
	/**
	 * 조리식품 일괄 신청화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/batchCookFoodReqPop.do")
	public String batchCookFoodReqPop(@ModelAttribute Map param, ModelMap model) throws Exception {
		return "web/cm/batchCookFoodReqPop";
	}
	
	/**
	 * 조리식품 일괄 등록화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/batchCookFoodInfoInsertPop.do")
	public String batchCookFoodInfoInsertPop(@ModelAttribute Map param, ModelMap model) throws Exception {
		return "web/cm/batchCookFoodInfoInsertPop";
	}	
	
	/**
	 * 조리식품 다운로드
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/getNutriCodeReqMngtExcelList.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> getNutriCodeReqMngtExcelList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {

		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, String>> rsList = nutriCodeMngtService.getNutriCodeReqMngtExcelList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}	
	
	/**
	 * 가공식품 일괄 등록화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/batchProcInfoInsertPop.do")
	public String batchProcInfoInsertPop(@ModelAttribute Map param, ModelMap model) throws Exception {
		return "web/cm/batchProcInfoInsertPop";
	}		
	
	
	/**
	 * 가공식품 검증 결과 유효성 체크
	 * @param 
	 * @return validData
	 * @throws Exception 
	 */
	@RequestMapping(value = "/excelValidFoodProcChk.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> excelValidFoodProcChk(@ModelAttribute Map param, ModelMap model) throws Exception {		
		Map<Integer,List<Integer>> validChkMap = excelValidFoodProcChk(param);
		Map<String,Object> rsMap = new HashMap<String, Object>();
		rsMap.put("id", param.get("gridId"));
		rsMap.put("validChkMap", validChkMap);
		if(validChkMap == null){
			rsMap.put("validChkMap", "complete");
		}
		return rsMap;
	}	
	
	
	/**
	 * 가공식품 검증 결과 일괄 저장
	 * @param param 예비대상자 정보 List
	 * @return totalData
	 * @throws Exception 
	 */
	@RequestMapping(value = "/importExcelGridProcInsert.do", method = RequestMethod.POST)
	public Map<String, Integer> importExcelGridProcInsert(@ModelAttribute Map param, ModelMap model) throws Exception {
		List<Map<String, Object>> paramList = excelExportDataProcList(param);		

		int insertCount = nutriCodeMngtService.importExcelGridProcInsert(paramList);
		
		Map<String, Integer> rsMap = new HashMap<String, Integer>();
		rsMap.put("insertPreCount", paramList.size());
		rsMap.put("insertCount", insertCount);
		return rsMap;
	}		
}
