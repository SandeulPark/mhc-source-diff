package kr.or.khealth.smhc.smhcweb.tg.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.or.khealth.smhc.common.DMultiActionController;
import kr.or.khealth.smhc.smhcweb.sv.service.SvcBgnAppointService;
import kr.or.khealth.smhc.smhcweb.tg.service.HealthMngtCnslService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Class Name : HealthMngtCnslController.java
 * @Description : 관리자 WEB에서 사용하는 건강관리 상담 업무를 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일			수정자		수정내용
 * @	----------		-----		---------------------------
 * @	2016.08.28		이현규		최초생성
 * @	2016.11.24		이은주		상담일자 변경시 관리목표 탭 내용 변경 추가.
 * @	2016.12.01		이은주		설문 구분값 수정.
 *
 * @author gst
 * @since 2016.08.28
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
@RequestMapping(value = "/tg")
public class HealthMngtCnslController extends DMultiActionController {
	
	@Resource(name = "web.tg.HealthMngtCnslService")
	private HealthMngtCnslService healthMngtCnslService;
	
	@Resource(name = "web.sv.SvcBgnAppointService")
	private SvcBgnAppointService svcBgnApService;
	
	@ModelAttribute
	public Map initData(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}
	
	/**
	 * 건강관리 상담 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/healthMngtCnsl.do", method= RequestMethod.POST)
	public String healthMngtCnsl(@ModelAttribute Map param, ModelMap model) throws Exception {
		// 기본정보
		Map<String, String> rsMap = healthMngtCnslService.selectHealthMngtCnslInfo(param);
		param.put("SVC_MNGT_NO", rsMap.get("SVC_MNGT_NO"));
		param.put("CNSL_NO", rsMap.get("CNSL_NO"));
		param.put("PRE_TRGTER_NO", rsMap.get("PRE_TRGTER_NO"));
		List<Map<String, Object>> examList = healthMngtCnslService.selectCnslRsltExamList(param);						// 대상자정보, 건강검진 데이터
		model.addAttribute("examList", examList);
		
		// 금연절주 설문내용
		List<Map<String, String>> serveyList = healthMngtCnslService.selectSmokServeyList(param);
		// 상담내용 및 관리계획
		Map<String, String> cnslMap = healthMngtCnslService.selectCnslContMngt(param);
		// 이전상상담  금연,절주 발송 여부 조회
		Map<String, Object> cnslYn = healthMngtCnslService.selectCnslYnList(param);
		// 관리군, 영양 목표
		Map<String, String> mngtObjMap = healthMngtCnslService.selectMngtObj(param);
		// 신체활동비만관리 목표
		Map<String, String> bodyFatObjMap = healthMngtCnslService.selectBodyActFatMngtObj(param);
		// 서비스 일정
		List<Map<String, String>> serviceSchedule = healthMngtCnslService.selectServiceSchedule(param);
		// 서비스 일정 재생성 가능 여부
		Map<String, String> chkCreateSch = healthMngtCnslService.selectChkCreateSch(param);		
		
		// 상담일자
		List<Map<String, String>> healthCnslDe = healthMngtCnslService.selectHealthCnslDe(param);	
		
		model.addAttribute("rsMap", rsMap);
		model.addAttribute("serveyList", serveyList);
		model.addAttribute("cnslMap", cnslMap);
		model.addAttribute("cnslYn", cnslYn);
		model.addAttribute("mngtObjMap", mngtObjMap);
		model.addAttribute("bodyFatObjMap", bodyFatObjMap);
		model.addAttribute("serviceSchedule", serviceSchedule);
		model.addAttribute("chkCreateSch", chkCreateSch);		
		
		model.addAttribute("healthCnslDe", healthCnslDe);
		
		return "web/tg/healthMngtCnsl";
    }
	
	/**
	 * 건강관리 상담 화면 위험요소 목록 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/dangerFactorList.do")
	public @ResponseBody Map<String, Object> dangerFactorList(@ModelAttribute Map param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, String>> rsList = healthMngtCnslService.selectDangerFactorList(param);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		
		return rsMap;
    }
	
	/**
	 * 건강관리 상담 화면 디바이스 배포 목록 조회
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/deviceDistList.do")
	public @ResponseBody Map<String, Object> deviceDistList(@ModelAttribute Map param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String, String>> rsList = healthMngtCnslService.selectDeviceDistList(param);
		
		rsMap.put("rsList", rsList);
		rsMap.put("id", param.get("id"));
		
		return rsMap;
    }
	
	/**
	 * 건강관리 상담 화면 상담내용 저장
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/saveCnslContMngt.do", method= RequestMethod.POST)
	public @ResponseBody void saveCnslContMngt(@ModelAttribute Map param, ModelMap model) throws Exception {		
		healthMngtCnslService.updateCnslContMngt(param);
		healthMngtCnslService.updateCnsl(param);
    }
	
	/**
	 * 건강관리 상담 화면 지급완료
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/saveDevicePymnt.do")
	public @ResponseBody void saveDevicePymnt(@ModelAttribute Map param, ModelMap model) throws Exception {
				
		if("30".equals(param.get("EQUIP_CLF"))){
			healthMngtCnslService.updateBloodPressPymnt(param);
		}else if("40".equals(param.get("EQUIP_CLF"))){
			healthMngtCnslService.updateBloodSugarPymnt(param);
		}
    }
	
	
	/**
	 * 건강관리 상담 화면 스케줄생성
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/createSchdule.do")
	public @ResponseBody Map<String, Object> createSchdule(@ModelAttribute Map param, ModelMap model) throws Exception {
		
		Map<String, Object> rsMap = new HashMap<String, Object>();
		
		healthMngtCnslService.insertCreateSchedule(param);		
		List<Map<String, String>> rsList = healthMngtCnslService.selectServiceSchedule(param);

		rsMap.put("rsList", rsList);
		
		return rsMap;
    }
	
	/**
	 * 스케줄 생성 시 버튼 클릭 시 , 프로시저 호출 및 스케줄 조회			
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/schduleAfter.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> schduleAfter(@ModelAttribute Map param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		
		rsMap.put("SVC_MNGT_NO", param.get("SVC_MNGT_NO"));
		rsMap.put("SVC_BGN_DE", param.get("SVC_BGN_DE"));
		
		
		String rsStr = healthMngtCnslService.callProcCnslAllIns(rsMap);
		
		//List<Map<String, String>> rsList = healthMngtCnslService.selectServiceSchedule(param);
		List<Map<String, Object>> rsList = svcBgnApService.selectServiceSchedule(param);

		
		rsMap.put("rsList", rsList);
		
		return rsMap;
	}
	
	/**
	 * 건강관리 상담 화면 설문결과보기 팝업 열기
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/serveyResultView_pop.do")
	public String serveyResultView_pop(@ModelAttribute Map param, ModelMap model) throws Exception {
		
		Map<String, String> serveyMap = healthMngtCnslService.selectServeyList(param);
		
//		param.put("MONTH_CD", "L1");
		List<Map<String, String>> preServeyList = healthMngtCnslService.selectServeyResultList(param);
		
		//L1 = 사전설문, L2 = 3개월 방문 설문, L3 = 6개월 방문 설문 			2016.12.01
//		if("3".equals(param.get("CNSL_NO"))) {
//			param.put("MONTH_CD", "L2");			
//		} else if("6".equals(param.get("CNSL_NO"))) {
//			param.put("MONTH_CD", "L3");			
//		}
		
//		param.put("SERVEY_TYPE", "10");
		List<Map<String, String>> lifeHabitList = healthMngtCnslService.selectServeyResultList(param);
		
//		param.put("SERVEY_TYPE", "20");
		List<Map<String, String>> satisfList = healthMngtCnslService.selectServeyResultList(param);
		
		model.addAttribute("serveyMap", serveyMap);
		model.addAttribute("preServeyList", preServeyList);
		model.addAttribute("lifeHabitList", lifeHabitList);
		model.addAttribute("satisfList", satisfList);
		model.addAllAttributes(param);
		return "web/tg/serveyResultViewPop";
    }
	
	@RequestMapping(value = "/serveyResultViewPrint.do")
	public String serveyResultViewPrint(@ModelAttribute Map param, ModelMap model) throws Exception {
		Map<String, Object> userMap = healthMngtCnslService.selectBasicUserInfo(param);
		Map<String, String> serveyMap = healthMngtCnslService.selectServeyList(param);
		
		List<Map<String, String>> preServeyList = healthMngtCnslService.selectServeyResultList(param);
		List<Map<String, String>> lifeHabitList = healthMngtCnslService.selectServeyResultList(param);
		
		List<Map<String, String>> satisfList = healthMngtCnslService.selectServeyResultList(param);
		
		model.addAttribute("userMap", userMap);
		model.addAttribute("serveyMap", serveyMap);
		model.addAttribute("preServeyList", preServeyList);
		model.addAttribute("lifeHabitList", lifeHabitList);
		model.addAttribute("satisfList", satisfList);
		
		return "web/tg/serveyResultViewPrint";
    }
	
	/**
	 * 건강관리 상담일자변경
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/healthMngtCnslDeChange.do")
	public @ResponseBody Map<String, Object> healthMngtCnslDeChange(@ModelAttribute Map param, ModelMap model) throws Exception {

		Map<String, Object> rsMap = new HashMap<String, Object>();
		
		// 금연절주 설문내용
		List<Map<String, String>> serveyList = healthMngtCnslService.selectSmokServeyList(param);
		// 상담내용 및 관리계획
		Map<String, String> cnslMap = healthMngtCnslService.selectCnslContMngt(param);
		// 관리군, 영양 목표
		Map<String, String> cnslObjMap = healthMngtCnslService.selectMngtObj(param);
		// 신체활동비만관리 목표
		Map<String, String> bodyFatObjMap = healthMngtCnslService.selectBodyActFatMngtObj(param);
		
		rsMap.put("serveyList", serveyList);
		rsMap.put("cnslMap", cnslMap);
		rsMap.put("cnslObjMap", cnslObjMap);
		rsMap.put("bodyFatObjMap", bodyFatObjMap);

		return rsMap;
    }
	
	/**
	 * 방문상담일 변경 팝업  호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/visitCnslDeUpdPop.do", method= RequestMethod.GET)
	public String visitCnslDeUpdPop(@ModelAttribute Map param, ModelMap model) throws Exception {
		List<Map<String, Object>> serviceSchedule = svcBgnApService.selectServiceSchedule(param);
		model.addAttribute("weekCntCdList", serviceSchedule);
		return "web/tg/visitCnslDeUpdPop";
    }
	
	/**
	 * 방문상담일 수정
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/updateVisitCnslDe.do", method= RequestMethod.POST)
	public @ResponseBody Map<String, Object> updateVisitCnslDe(@ModelAttribute Map param, ModelMap model) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		int rsInt = healthMngtCnslService.updateVisitCnslDe(param);
		rsMap.put("updateChkCnt", rsInt);
		if(rsInt != 0){
			List<Map<String, String>> rsList = healthMngtCnslService.selectServiceSchedule(param);			
			rsMap.put("rsList", rsList);
		}
		return rsMap;
    }
	
	/**
	 * 결과지 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/cnslRslt.do")
	public String cnslRslt(@ModelAttribute Map param, ModelMap model) throws Exception {
		
		String tabNo = (String) param.get("tabNo");
		
		List<Map<String, Object>> examList = healthMngtCnslService.selectCnslRsltExamList(param);						// 대상자정보, 건강검진 데이터
		model.addAttribute("examList", examList);
		
		if("2".equals(tabNo)){
			List<Map<String, Object>> nurtList = healthMngtCnslService.selectNurtObjList(param);						// 영양관리목표
			List<Map<String, Object>> missionList = healthMngtCnslService.selectMissionResultList(param);				// 실천미션결과
			List<Map<String, Object>> missionList2 = healthMngtCnslService.selectMissionResultList2(param);				// 실천미션결과 요약 
			List<Map<String, Object>> mealDiaryWeekCalList = healthMngtCnslService.selectMealDiaryWeekCalList(param);	// 주차별 일평균 섭취칼로리(차트)
			List<Map<String, Object>> mealDiaryResult = healthMngtCnslService.selectMealDiaryResult(param);				// 식사일기 
			Map<String, Object> mealDiaryDayAvgPer = healthMngtCnslService.selectMealDiaryDayAvgPer(param);				// 일평균 섭취현황(차트)
			Map<String, Object> mealDiaryCPFPer = healthMngtCnslService.selectCPFPer(param);							// 탄단지 비율(차트)
			List<Map<String, Object>> intensiveNurtList = healthMngtCnslService.selectIntensiveNurtCnslList(param);		// 집중상담 식품군 섭취 평가
			Map<String, Object> dataCntInfo = healthMngtCnslService.selectNoDataCheckInfo(param);						// 식사일기, 신체활동 데이터 유무
			
			model.addAttribute("nurtList", nurtList);
			model.addAttribute("missionList", missionList);
			model.addAttribute("missionList2", missionList2);
			model.addAttribute("mealDiaryWeekCalList", mealDiaryWeekCalList);
			model.addAttribute("mealDiaryResult", mealDiaryResult);
			model.addAttribute("mealDiaryDayAvgPer", mealDiaryDayAvgPer);
			model.addAttribute("mealDiaryCPFPer", mealDiaryCPFPer);
			model.addAttribute("intensiveNurtList", intensiveNurtList);
			model.addAttribute("dataCntInfo", dataCntInfo);
		}else if("3".equals(tabNo)){
			List<Map<String, Object>> bodyActObjList = healthMngtCnslService.selectBodyActObjList(param);				// 신체활동 관리목표
			Map<String, Object> bodyActAnalysis = healthMngtCnslService.selectBodyActAnalysis(param);					// 신체활동 분석
			List<Map<String, Object>> bodyActWeekList = healthMngtCnslService.selectBodyActWeekList(param);				// 신체활동 주차별 데이터(차트)
			Map<String, Object> dataCntInfo = healthMngtCnslService.selectNoDataCheckInfo(param);						// 식사일기, 신체활동 데이터 유무
			List<Map<String, Object>> bodyActWeekDayList = healthMngtCnslService.selectWeekDayActList(param);			// 요일별 활동량
			
			model.addAttribute("bodyActObjList", bodyActObjList);
			model.addAttribute("bodyActAnalysis", bodyActAnalysis);
			model.addAttribute("bodyActWeekList", bodyActWeekList);
			model.addAttribute("dataCntInfo", dataCntInfo);
			model.addAttribute("bodyActWeekDayList", bodyActWeekDayList);
		}
		
		model.addAttribute("tabNo", tabNo);
		model.addAttribute("PRE_TRGTER_NO", param.get("PRE_TRGTER_NO"));
		model.addAttribute("USER_ID", param.get("USER_ID"));
		model.addAttribute("CNSL_SN", param.get("CNSL_SN"));
		model.addAttribute("CNSL_STTUS", param.get("CNSL_STTUS"));
		model.addAttribute("CNSL_NO", param.get("CNSL_NO"));
		model.addAllAttributes(param);
		return "web/tg/cnslRslt";
    }
	
	/**
	 * 종합상담 영양 섭취평가 정보 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/nutriRsltInfo.do")
	public @ResponseBody Map<String, Object> nutriRsltInfo(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		Map<String, Object> mealNutriInfo = healthMngtCnslService.selectMealtNutriRsltInfo(param);
		return mealNutriInfo;
	}
    
    /**
	 * 결과지 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/cnslRsltPrint.do")
	public String cnslRsltPrint(@ModelAttribute Map param, ModelMap model) throws Exception {
		
		List<Map<String, Object>> examList = healthMngtCnslService.selectCnslRsltExamList(param);					// 대상자정보, 건강검진 데이터
		
		List<Map<String, Object>> nurtList = healthMngtCnslService.selectNurtObjList(param);						// 영양관리목표
		List<Map<String, Object>> missionList = healthMngtCnslService.selectMissionResultList(param);				// 실천미션결과
		List<Map<String, Object>> missionList2 = healthMngtCnslService.selectMissionResultList2(param);				// 실천미션결과 요약
		List<Map<String, Object>> mealDiaryResult = healthMngtCnslService.selectMealDiaryResult(param);				// 식사일기
		List<Map<String, Object>> mealDiaryWeekCalList = healthMngtCnslService.selectMealDiaryWeekCalList(param);	// 주차별 일평균 섭취칼로리(차트)
		Map<String, Object> mealDiaryDayAvgPer = healthMngtCnslService.selectMealDiaryDayAvgPer(param);				// 일평균 섭취현황(차트)
		Map<String, Object> mealDiaryCPFPer = healthMngtCnslService.selectCPFPer(param);							// 탄단지 비율(차트)
		List<Map<String, Object>> intensiveNurtList = healthMngtCnslService.selectIntensiveNurtCnslList(param);		// 집중상담 식품군 섭취 평가
		List<Map<String, Object>> bodyActObjList = healthMngtCnslService.selectBodyActObjList(param);				// 신체활동 관리목표
		Map<String, Object> bodyActAnalysis = healthMngtCnslService.selectBodyActAnalysis(param);					// 신체활동 분석
		List<Map<String, Object>> bodyActWeekList = healthMngtCnslService.selectBodyActWeekList(param);				// 신체활동 주차별 데이터(차트)
		Map<String, Object> dataCntInfo = healthMngtCnslService.selectNoDataCheckInfo(param);						// 식사일기, 신체활동 데이터 유무
		List<Map<String, Object>> bodyActWeekDayList = healthMngtCnslService.selectWeekDayActList(param);			// 요일별 활동량
		
		model.addAttribute("examList", examList);
		model.addAttribute("nurtList", nurtList);
		model.addAttribute("missionList", missionList);		
		model.addAttribute("missionList2", missionList2);
		model.addAttribute("mealDiaryResult", mealDiaryResult);
		model.addAttribute("mealDiaryWeekCalList", mealDiaryWeekCalList);
		model.addAttribute("mealDiaryDayAvgPer", mealDiaryDayAvgPer);
		model.addAttribute("mealDiaryCPFPer", mealDiaryCPFPer);
		model.addAttribute("intensiveNurtList", intensiveNurtList);
		model.addAttribute("bodyActObjList", bodyActObjList);
		model.addAttribute("bodyActAnalysis", bodyActAnalysis);
		model.addAttribute("bodyActWeekList", bodyActWeekList);
		model.addAttribute("dataCntInfo", dataCntInfo);
		model.addAttribute("bodyActWeekDayList", bodyActWeekDayList);
		model.addAttribute("CNSL_NO", param.get("CNSL_NO"));
		model.addAttribute("tabNo", param.get("tabNo"));
		model.addAttribute("CNSL_SN", param.get("CNSL_SN"));
		model.addAllAttributes(param);
		
		return "web/tg/cnslRsltPrint";
    }
	
	@RequestMapping(value ="/cvdPointPop.do", method= RequestMethod.GET)
	public String cvdPointPop(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		List<Map<String, Object>> cvdList = healthMngtCnslService.selectCvdPointList(param);
		List<Map<String, Object>> m_cvdList = new ArrayList<Map<String ,Object>>();
		List<Map<String, Object>> f_cvdList = new ArrayList<Map<String ,Object>>();
		for(int i=0;i<cvdList.size();i++){
			if("M".equals(cvdList.get(i).get("GENDER"))){
				m_cvdList.add(cvdList.get(i));
			}else if("F".equals(cvdList.get(i).get("GENDER"))){
				f_cvdList.add(cvdList.get(i));
			}
		}
		model.addAttribute("m_cvdList", m_cvdList);
		model.addAttribute("f_cvdList", f_cvdList);
		return "web/tg/cvdPointPop";
	}
	
}

