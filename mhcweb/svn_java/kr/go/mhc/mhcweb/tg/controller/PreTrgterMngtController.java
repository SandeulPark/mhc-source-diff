package kr.go.mhc.mhcweb.tg.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.go.mhc.mhcweb.tg.service.HealthExamMngtService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.common.util.StringUtil;
import kr.go.mhc.mhcweb.cm.service.PushService;
import kr.go.mhc.mhcweb.tg.service.PreTrgterMngtService;

/**
 * @Class Name : PreTrgterMngtController.java
 * @Description : 관리자 WEB에서 사용하는 실시간 상담업무를 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.08.18		장슬기			최초생성
 * @    2017.02.16		이태석			대상 중복체크, 자체 검진 등록 추가
 *
 * @author gst
 * @author thejoin
 * @since 2016.08.18
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
@RequestMapping(value = "/tg")
public class PreTrgterMngtController extends DMultiActionController{
	
	@Resource(name = "web.tg.PreTrgterMngtService")
	private PreTrgterMngtService preTrgterMngtService;

	@Resource(name= "web.tg.HealthExamMngtService")
	private HealthExamMngtService healthExamMngtService;

	@Resource(name="common.pushService")
	private PushService pushService;
	
	@ModelAttribute
	public Map initData(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	
	/**
	 * 예비대상자 등록 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/preTrgterRegit.do")
	public String preTrgterRegit(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception {
		param.put("SCH_TEST_TYPE", "NORM");
		Map<String, String> rsMap = preTrgterMngtService.getSttusCnt(param);
		model.addAttribute("rsMap",rsMap);
		return "web/tg/preTrgterRegit";
	}
	
	/**
	 *  예비대상자 목록 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/preTrgtMngtList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> preTrgtMngtList(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		
		List<Map<String, String>> rsList = preTrgterMngtService.getPreTrgtMngtList(param);
		System.out.println("preTrgtMngtList===>"+ rsList);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		
		return rsMap;
	}
	
	/**
	 *  예비대상자 건수 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/preTrgtMngtSttusCnt.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> preTrgtMngtSttusCnt(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String, String> rsMap = preTrgterMngtService.getSttusCnt(param);
				
		return rsMap;
	}
	
	
	/**
	 * 예비대상자 등록화면 호출
	 * @param param PK 정보
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/preTrgterRegitDtls.do", method = RequestMethod.POST)
	public String preTrgterRegitDtls(@ModelAttribute Map<String, Object> param, ModelMap model)throws Exception {
		
		Map<String, String> rsInfo = preTrgterMngtService.getPreTrgterInfo(param);
		Map<String, String> rsResult = preTrgterMngtService.getChkHealthResult(param);
		
		model.addAttribute("exam_de", param.get("EXAM_DE"));
		model.addAttribute("exam_sn", param.get("EXAM_SN"));
		model.addAttribute("CNSL_NO", param.get("CNSL_NO"));							//20161122
		model.addAttribute("rsInfo", rsInfo);
		model.addAttribute("rsResult", rsResult);
		
		return "web/tg/preTrgterRegitDtls";
	}
	
	/**
	 * 예비대상자 상세정보 조회
	 */
	@RequestMapping(value = "/preTrgterRegitDtlsInfo.do", method = RequestMethod.POST)
	public @ResponseBody  Map<String, Object> preTrgterRegitDtlsInfo(@ModelAttribute Map<String, Object> param, ModelMap model)throws Exception {
		Map<String,Object> rsMap = new HashMap<String,Object>();
		Map<String, String> rsInfo = preTrgterMngtService.getPreTrgterInfo(param);
		
		model.addAttribute("exam_de", param.get("EXAM_DE"));
		model.addAttribute("exam_sn", param.get("EXAM_SN"));
		model.addAttribute("CNSL_NO", param.get("CNSL_NO"));							//20161122
		model.addAttribute("rsInfo", rsInfo);
		
		rsMap.put("rsInfo", rsInfo);
		return rsMap;
	}
	
	
	/**
	 * 예비대상자 참여경로 호출
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/joinPath.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> joinPath(@ModelAttribute Map param, ModelMap model) throws Exception {
		Map<String, String> rsJPMap = preTrgterMngtService.getPreTrgterInfo(param);
		return rsJPMap;
	}
	
	/**
	 *  건강검진 결과 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/halthResult.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> halthResult(@ModelAttribute Map param, ModelMap model) throws Exception{
		
		Map<String, String> rsMap = preTrgterMngtService.getChkHealthResult(param);
		return rsMap;
	}
	
	/**
	 * 콤보박스 호출
	 * @param param PK 정보
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/requestComboBox.do", method = RequestMethod.GET)
	public String requestComboBox(@ModelAttribute Map<String, Object> param, ModelMap model)throws Exception {
		
		List<Map<String, String>> rsList = preTrgterMngtService.requestComboBox(param);
		
		model.addAttribute("rsCombo", rsList);
		
		return "web/tg/preTrgterRegitDtls";
	}
	
	/**
	 * 선정의뢰 update
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/choiceRequest.do")
	public String choiceRequest(@ModelAttribute Map param, ModelMap model) throws Exception {
		
		preTrgterMngtService.updateChoiceRequest(param);

		return "web/tg/preTrgterRegitDtls";
	}
	
	/**
	 * 자체 검진의뢰 insert
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/selfHealthChkRequest.do")
	public @ResponseBody Map<String, String> selfHealthChkRequest(@ModelAttribute Map param, ModelMap model) throws Exception {
		
		Map<String, String> rsMap = preTrgterMngtService.insertSelfHealthChkRequest(param);

		return rsMap;
	}
	
	/**
	 * 참여결정 update
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/decisionEnter.do")
	public String decisionEnter(@ModelAttribute Map param, ModelMap model) throws Exception {
		
		preTrgterMngtService.updateDecisionEnter(param);

		return "web/tg/preTrgterRegitDtls";
	}
	
	/**
	 * 참여거부 update
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/denyEnter.do")
	public String denyEnter(@ModelAttribute Map param, ModelMap model) throws Exception {
		
		preTrgterMngtService.updateDenyEnter(param);

		return "web/tg/preTrgterRegitDtls";
	}
	
	/**
	 * 예비대상자 정보 수정
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/preTrgterCorrect.do", method = RequestMethod.POST)
	public @ResponseBody void preTrgterCorrect(@ModelAttribute Map param, ModelMap model) throws Exception {
		preTrgterMngtService.preTrgterInfoCorrect(param);
		preTrgterMngtService.preTrgterClfCorrect(param);
	}
	
	/**
	 * 예비대상자 화면 호출(신규)
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/preTrgterRegitDtl.do")
	public String preTrgterRegitDtl(@ModelAttribute Map param, ModelMap model) throws Exception {
		
		model.addAttribute("trgterChkYn", "N");
		return "web/tg/preTrgterRegitDtls";
	}
	
	/**
	 * 예비대상자 신규 등록
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/newPreTrgterRegit.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> newPreTrgterRegit(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		
		Map<String, Object> rsMap = preTrgterMngtService.newPreTrgterRegit(param);
		
		return rsMap;
	}
	
	/**
	 * 예비대상자 일괄 등록 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/batchRegitPreTrgter.do")
	public String batchRegitPreTrgter(@ModelAttribute Map param, ModelMap model) throws Exception {
		

		return "web/tg/batchRegitPreTrgter";
	}
	
	/**
	 * 신규 예비대상자 일괄 등록 저장 
	 * @param param 예비대상자 정보 List
	 * @return totalData
	 * @throws Exception 
	 */
	@RequestMapping(value = "/importExcelGridInsert.do", method = RequestMethod.POST)
	public Map<String, Integer> importExcelGridInsert(@ModelAttribute Map param, ModelMap model) throws Exception {
		List<Map<String, Object>> paramList = excelExportDataList(param);		
		int insertCount = preTrgterMngtService.importExcelGridInsert(paramList);
		Map<String, Integer> rsMap = new HashMap<String, Integer>();
		rsMap.put("insertPreCount", paramList.size());
		rsMap.put("insertCount", insertCount);
		return rsMap;
	}
	
	/**
	 * 유효성 체크 
	 * @param 
	 * @return validData
	 * @throws Exception 
	 */
	@RequestMapping(value = "/excelValidChk.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> excelValidChk(@ModelAttribute Map param, ModelMap model) throws Exception {		
		Map<Integer,List<Integer>> validChkMap = excelDataValidChk(param);
		Map<String,Object> rsMap = new HashMap<String, Object>();
		rsMap.put("id", param.get("gridId"));
		rsMap.put("validChkMap", validChkMap);
		if(validChkMap == null){
			rsMap.put("validChkMap", "complete");
		}
		return rsMap;
	}
	
	/**
	 *  아이디 중복 체크
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/checkOverlapId.do", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> checkOverlapId(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		
		Map<String, String> rsCheckId = preTrgterMngtService.checkOverlapId(param);
		
		rsMap.put("rsCheckId", rsCheckId);
		
		return rsMap;
	}
	
	/**
	 *  검진의뢰 대기중 체크
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/healthExamCnt.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> selectHealthExamCnt(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		
		Map<String, String> healthExamCntMap = preTrgterMngtService.selectHealthExamCnt(param);
		
		rsMap.put("healthExamCntMap", healthExamCntMap);
		
		return rsMap;
	}
	
	/**
	 * 중복 체크 팝업 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/duplicationChk_pop.do", method = RequestMethod.GET)
	public String duplicationChk_pop(@ModelAttribute Map param, ModelMap model) throws Exception {
		return "web/tg/preTrgterDuplicationChkPop";
	}
	
	/**
	 *  예비대상자 중복 리스트 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/preTrgterDuplicationChkList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> preTrgterDuplicationChkList(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String, String>> rsList = preTrgterMngtService.getPreTrgterDuplicationChkList(param);
//		model.addAttribute("duplicationChkList", rsList);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		
		return rsMap;
	}
	
	/**
	 * 자체 검진 등록 팝업 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/examReg_pop.do", method = RequestMethod.GET)
	public String examReg_pop(@ModelAttribute Map param, ModelMap model) throws Exception {
		return "web/tg/examReg_pop";
	}
	
	/**
	 * 예비대상자 삭제
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/delPreTrgterInfo.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> delPreTrgterInfo(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();

		int delChk = preTrgterMngtService.delPreTrgterInfo(param);
		
		rsMap.put("delChk", delChk);
		return rsMap;
	}
	
	/**
	 *  예비대상자 중복 체크
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/duplicationCnt.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getDuplicationCnt(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = preTrgterMngtService.getDuplicationCnt(param);
						
		return rsMap;
	}
	
	/**
	 * 예비대상자 동의서 출력
	 * @param
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/preTrgterRegitPrint.do", method = RequestMethod.GET)
	public String preTrgterRegitPrint(@ModelAttribute Map<String, Object> param, ModelMap model)throws Exception {
		
		Map<String, String> rsInfo = preTrgterMngtService.getPreTrgterInfo(param);
		model.addAttribute("rsInfo", rsInfo);
		
		return "web/tg/preTrgterRegitPrint";
	}
	
	/**
	 * 20200131 양현우  PHIS연계 대상자 일괄 등록 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/phisAllRegitPreTrgter.do")
	public String phisAllRegitPreTrgter(@ModelAttribute Map param, ModelMap model) throws Exception {
		return "web/tg/phisAllRegitPreTrgter";
	}
	
	/**
	 * 20200131 양현우  PHIS연계 대상자 개별 등록 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/phisRegitPreTrgter.do")
	public String phisRegitPreTrgter(@ModelAttribute Map param, ModelMap model) throws Exception {
		return "web/tg/phisRegitPreTrgter";
	}
	
	/**
	 * 20200131 양현우  PHIS연계 대상자 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/phisAllRegitPreTrgterList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> phisAllRegitPreTrgterList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, Object>> rsList = preTrgterMngtService.selectphisAllRegitPreTrgterList(param);
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;

	}
	
	/**
	 * 20200203 양현우  PHIS연계 대상자 엑셀 다운로드
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/phisTrgterExcel.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> phisTrgterExcel(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		String searchInfo = StringUtil.nvl((String)param.get("REG_NO"));
		List<Map<String, Object>> rsList = new ArrayList<Map<String, Object>>();
		
		if(!"".equals(searchInfo)){
			Set<Map<String, String>> set = new LinkedHashSet<>(StringUtil.makeStringToIterator(searchInfo));
			List<Map<String,String>> searchInfoList = new ArrayList<Map<String, String>>(set);
			int lastIndex = searchInfoList.size()/1000; // TODO 1000
			
			for (int i = 0; i < lastIndex + 1; i++) {
				if(i != lastIndex) {
					param.put("searchInfoList", searchInfoList.subList(i * 1000, (i + 1) * 1000)); // TODO 1000
				} else {
					param.put("searchInfoList", searchInfoList.subList(i * 1000, i * 1000 + searchInfoList.size()%1000)); // TODO 1000
				}
				rsList.addAll(preTrgterMngtService.getphisExcel(param));
			}
		}else{
			rsList = preTrgterMngtService.getphisExcel(param);
		}
		rsMap.put("rsList",rsList);
		rsMap.put("id", param.get("id"));
		return rsMap;
	}

	/**
	 * 신규 연계대상자 일괄 등록 저장 
	 * @param param 예비대상자 정보 List
	 * @return totalData
	 * @throws Exception 
	 */
	@RequestMapping(value = "/phisImportExcelGridInsert.do", method = RequestMethod.POST)
	public Map<String, Integer> phisImportExcelGridInsert(@ModelAttribute Map param, ModelMap model) throws Exception {
		List<Map<String, Object>> paramList = excelExportDataList(param);		
		int insertCount = preTrgterMngtService.importExcelGridInsert(paramList);
		
		// 신규 연계대상자 일괄 등록 저장   phisImportExcelGridInsert 로직변경  주석처리 20241022 jeeeeey 
		// int updateCount = preTrgterMngtService.phisImportExcelGridInsert(paramList);
		
		Map<String, Integer> rsMap = new HashMap<String, Integer>();
		rsMap.put("insertPreCount", paramList.size());
		rsMap.put("insertCount", insertCount);
		// rsMap.put("updateCount",updateCount);
		return rsMap;
	}

	/**
	 * 미수검처리 update
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/setNoexamProc.do", method = RequestMethod.POST)
//	public @ResponseBody int setNoexamProc(@ModelAttribute Map param, ModelMap model) throws Exception {
	public @ResponseBody int setNoexamProc(HttpServletRequest req, @ModelAttribute Map <String, Object> param, ModelMap model) throws Exception {
		// 미수검 처리 시, 해당 상담차수 검진데이터 삭제
		healthExamMngtService.delHealthMngt(param);

		int updtCnt = preTrgterMngtService.updateNoexamProc(param);
		
		//미수검 처리시 푸시 or 알림 발송 여부 확인
		param.put("noticeCd", "SR05");
		param.put("noticeSn", "1");
		Map<String, Object> pMap = pushService.getPushSetInfo(param);	
		
		if(pMap != null && !pMap.isEmpty()) {
			sendNoexamPushNotice(req, param, pMap);
		}
				
		return updtCnt;
	}

	/**
	 * 미수검 처리 팝업 호출
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/noexamPop.do", method = RequestMethod.GET)
	public String selectNoexamPop(@ModelAttribute Map param, ModelMap model) throws Exception {
		return "web/tg/noexamPop";
	}

	/**
	 * 미수검 상세정보 조회
	 */
	@RequestMapping(value = "/noexamDtlsInfo.do", method = RequestMethod.POST)
	public @ResponseBody  Map<String, Object> noexamDtlsInfo(@ModelAttribute Map<String, Object> param, ModelMap model)throws Exception {
		Map<String,Object> rsMap = new HashMap<String,Object>();
		Map<String, String> rsInfo = preTrgterMngtService.getNoexamDtlsInfo(param);

//		model.addAttribute("NO_EXAM_SET_YN", param.get("NO_EXAM_SET_YN"));
//		model.addAttribute("NO_EXAM_REASN", param.get("NO_EXAM_REASN"));
//		model.addAttribute("NO_EXAM_STND", param.get("NO_EXAM_STND"));
//		model.addAttribute("rsInfo", rsInfo);

		rsMap.put("rsInfo", rsInfo);
		return rsMap;
	}


	/**
	 * 최종검진 미수검처리 update 210831
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/setfinNoexamProc.do", method = RequestMethod.POST)
	public @ResponseBody int setfinNoexamProc(HttpServletRequest req, @ModelAttribute Map <String, Object> param, ModelMap model) throws Exception {
		// 미수검 처리 시, 해당 상담차수 검진데이터 삭제
		healthExamMngtService.delHealthMngt(param);

		int updtCnt = preTrgterMngtService.updatefinNoexamProc(param);
		
		//미수검 처리시 푸시 or 알림 발송 여부 확인
		param.put("noticeCd", "SR05");
		param.put("noticeSn", "2");
		Map<String, Object> pMap = pushService.getPushSetInfo(param);	
				
		if(pMap != null && !pMap.isEmpty()) {
			sendNoexamPushNotice(req, param, pMap);
		}

		return updtCnt;
	}

	/**
	 * 최종검진 미수검 처리 팝업 호출 210831
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/finNoexamPop.do", method = RequestMethod.GET)
	public String selectFinNoexamPop(@ModelAttribute Map param, ModelMap model) throws Exception {
		return "web/tg/finNoexamPop";
	}

	/**
	 * 최종검진 미수검 상세정보 조회 210831
	 */
	@RequestMapping(value = "/finNoexamDtlsInfo.do", method = RequestMethod.POST)
	public @ResponseBody  Map<String, Object> finNoexamDtlsInfo(@ModelAttribute Map<String, Object> param, ModelMap model)throws Exception {
		Map<String, Object> rsMap = new HashMap<String,Object>();
		Map<String, String> rsInfo = preTrgterMngtService.getFinNoexamDtlsInfo(param);

		rsInfo.put("NO_EXAM_SET_YN", rsInfo.get("FIN_NO_EXAM_SET_YN"));
		rsInfo.put("NO_EXAM_REASN", rsInfo.get("FIN_NO_EXAM_REASN"));
		rsInfo.put("NO_EXAM_STND", rsInfo.get("FIN_NO_EXAM_STND"));

		rsMap.put("rsInfo", rsInfo);
		return rsMap;
	}

	/**
	 * 최종검진 미수검 + 최종설문 했을 시 졸업처리 210831
	 */
	@RequestMapping(value = "/trgterGraduation.do", method = RequestMethod.POST)
	public @ResponseBody void trgterGraduation(@ModelAttribute Map<String, Object> param, ModelMap model)throws Exception {
		preTrgterMngtService.updateTrgterGraduation(param);
	}
	
	
	/**
	 * 중간 / 최종 검진 미수검 처리시 알림 / 푸시
	 */
	private void sendNoexamPushNotice(HttpServletRequest req, Map<String, Object> param, Map<String, Object> pMap) throws Exception {
		
	    String pushTitle       = StringUtil.nvl(String.valueOf(pMap.get("PUSH_TITLE")), "");
	    String pushCont        = StringUtil.nvl(String.valueOf(pMap.get("PUSH_CONT")), "");
	    String pushLinkPage    = StringUtil.nvl(String.valueOf(pMap.get("PUSH_LINK_PAGE")));
	    String pushUseYn       = StringUtil.nvl(String.valueOf(pMap.get("PUSH_USE_YN")), "N");

	    String noticeTitle     = StringUtil.nvl(String.valueOf(pMap.get("NOTICE_TITLE")), "");
	    String noticeCont      = StringUtil.nvl(String.valueOf(pMap.get("NOTICE_CONT")), "");
	    String noticeLinkPage  = StringUtil.nvl(String.valueOf(pMap.get("NOTICE_LINK_PAGE")));
	    String noticeUseYn     = StringUtil.nvl(String.valueOf(pMap.get("NOTICE_USE_YN")), "N");

	    // A:푸시+알림 / P:푸시 / N:알림
	    String noticeClf = "";
	    if (pushUseYn.equals("Y") && noticeUseYn.equals("Y")) {
	        noticeClf = "A";
	    } else if (pushUseYn.equals("Y")) {
	        noticeClf = "P";
	    } else if (noticeUseYn.equals("Y")) {
	        noticeClf = "N";
	    }

	    if ("".equals(noticeClf)) return;

	    String adminUserId = req.getSession().getAttribute("SESS_USER_ID") == null
	            ? ""
	            : (String) req.getSession().getAttribute("SESS_USER_ID");

	    String userIds[] = param.get("USER_IDS").toString()
	            .replaceAll("&quot;", "")
	            .replaceAll("\\[", "")
	            .replaceAll("\\]", "")
	            .split(",");

	    for (String userId : userIds) {

	        Date from = new Date();
	        SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	        String sndSn = transFormat.format(from);
	        sndSn += (int) (Math.random() * 9999);

	        String orgCd = pushService.selectOrgCd(userId);

	        Map<String, Object> insPushMasMap = new HashMap<>();
	        insPushMasMap.put("sndSn", sndSn);
	        insPushMasMap.put("noticeClf", noticeClf);
	        insPushMasMap.put("sndOrgCd", orgCd);
	        insPushMasMap.put("sndUserId", adminUserId);
	        insPushMasMap.put("sndSttus", "N".equals(noticeClf) ? "S" : "N");
	        insPushMasMap.put("sndCnt", "1");
	        insPushMasMap.put("msgClf", "NT");
	        insPushMasMap.put("pushTitle", pushTitle);
	        insPushMasMap.put("pushCont", pushCont);
	        insPushMasMap.put("pushLinkPage", pushLinkPage);
	        insPushMasMap.put("noticeTitle", noticeTitle);
	        insPushMasMap.put("noticeCont", noticeCont);
	        insPushMasMap.put("noticeLinkPage", noticeLinkPage);
	        insPushMasMap.put("rcvClf", "I");
	        insPushMasMap.put("autoManuClf", "M");
	        insPushMasMap.put("reqClf", "20");
	        insPushMasMap.put("resrvtDe", sndSn.substring(0, 8));
	        insPushMasMap.put("resrvtTm", sndSn.substring(8, 14));

	        pushService.insertResvrtPushMas(insPushMasMap);

	        Map<String, Object> insPushHisMap = new HashMap<>();
	        insPushHisMap.put("sndSn", sndSn);
	        insPushHisMap.put("rcvUserId", userId);
	        insPushHisMap.put("sndUserId", adminUserId);
	        insPushHisMap.put("sndSttus", "N".equals(noticeClf) ? "20" : "12");

	        pushService.insertResvrtPushHis(insPushHisMap);
	    }
	}
	
	/**
	 * 이름 변경 확인 팝업
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/changeUserNmSttusPop.do", method= RequestMethod.GET)
	public String deviceDistrbtDtlsPop(@ModelAttribute Map param, ModelMap model) throws Exception {
		model.addAttribute("USER_ID", param.get("USER_ID"));
		return "web/tg/changeUserNmSttusPop";
	}
	
	
	/**
	 * 이름 변경 확인
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/getChangeUserNm.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> getChangeUserNm(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		
		Map<String, Object> rsMap = preTrgterMngtService.getChangeUserNm(param);
		return rsMap;
	}
		
	/**
	 * 이름 변경 확인 여부 업데이트
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/updateChgUserNmCnfm.do", method= RequestMethod.POST)
	public void changeUserNmSttusPop(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {		
		preTrgterMngtService.updateChgUserNmCnfm(param);
	}
	
}
