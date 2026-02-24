package kr.go.mhc.mhcweb.st.controller;

import java.util.ArrayList;
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
import kr.go.mhc.mhcweb.sm.service.OrgMngtService;
import kr.go.mhc.mhcweb.st.service.MthlyHealthRptService;
import kr.go.mhc.mhcweb.sv.service.IntensiveBodyActObstyCnslService;
import kr.go.mhc.mhcweb.sv.service.IntensiveCnslMngtService;
import kr.go.mhc.mhcweb.tg.service.TrgterInfoMngtService;

/**
 * @Class Name : MthlyHealthRptController.java
 * @Description : 건강관리의 월간리포트 하위 메뉴들을 컨트롤하는 컨트롤러
 * @Modification Information
 * @
 * @	수정일				수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.12.21		윤봉훈			최초생성
 *
 * @author gst
 * @since 2016.12.21
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
@RequestMapping(value = "/st")
public class MthlyHealthRptController extends DMultiActionController {

	@Resource(name = "web.sv.IntensiveCnslMngtService")
	private IntensiveCnslMngtService intensiveCnslMngtSerivce;
	
	@Resource(name= "web.st.MthlyHealthRptService")
	private MthlyHealthRptService mthlyHealthRptService;
	
	@Resource(name= "web.tg.TrgterInfoMngtService")
	private TrgterInfoMngtService trgterInfoMngtService;
	
	@Resource(name = "web.sv.IntensiveBodyActObstyCnslService")
	private IntensiveBodyActObstyCnslService intensiveBodyActObstyCnslService;

	@Resource(name = "web.sm.OrgMngtService")
	private OrgMngtService orgMngtService;

	@ModelAttribute
	public Map<String,Object> initDate(HttpServletRequest req) throws Exception {
		return super.initData(req);
	}

	/**
	 * 월간리포트제공 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value= "/mthlyHealthRpt.do")
	public String mthlyHealthRpt(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception {
		System.out.println("=================================================="+param);
		model.addAllAttributes(param);
		return "web/st/mthlyHealthRpt";
	}
	
	/**
	 * 월간리포트제공 리스트 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/mthlyHealthRptList.do", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> mthlyHealthRptList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<Map<String,Object>> rsList = mthlyHealthRptService.mthlyHealthRptList(param);

		// orgDtls 추가
		param.put("SCH_ORG_CD", param.get("SESS_ORG_CD"));
		List<Map<String, String>> rsOrgDtlsList = orgMngtService.getOrgDtlsList(param);

		rsMap.put("id", param.get("id"));
		rsMap.put("rsList", rsList);
		rsMap.put("rsOrgDtlsList", rsOrgDtlsList);
		return rsMap;
	}
	
	/**
	 * 월간리포트 제공 상세 화면 호출
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/mthlyHealthRptDtls.do")
	public String mthlyHealthRptDtls(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> userInfo = trgterInfoMngtService.trgterInfoMngtDtls(param);
		List<Map<String, Object>> healthExam = intensiveBodyActObstyCnslService.getHelthExam(param);
		List<Map<String, Object>> dateList = mthlyHealthRptService.mthlyHealthRptDateList(param);
		List<Map<String, Object>> bodyGList = mthlyHealthRptService.bodyGoalList(param);
		List<Map<String, Object>> nutriGList = mthlyHealthRptService.nutriGoalList(param);
		List<Map<String, Object>> templateList = mthlyHealthRptService.selectCnslTemplateNmList(param);
		Map<String, Object> cnslYn = mthlyHealthRptService.selectCnslYnList(param);
		Map<String ,String> manager = intensiveCnslMngtSerivce.getManagerType(param);

		// orgDtls 추가
		param.put("SCH_ORG_CD", param.get("SESS_ORG_CD"));
		List<Map<String, String>> rsOrgDtlsList = orgMngtService.getOrgDtlsList(param);

		model.addAttribute("userInfo", userInfo);
		model.addAttribute("healthExam", healthExam);
		model.addAttribute("dateList", dateList);
		model.addAttribute("bodyGList", bodyGList);
		model.addAttribute("nutriGList", nutriGList);
		model.addAttribute("templateList", templateList);
		model.addAttribute("cnslYn", cnslYn);
		model.addAllAttributes(param);
		model.addAttribute("mangerinfo", manager);
		model.addAttribute("rsOrgDtlsList", rsOrgDtlsList);

		return "web/st/mthlyHealthRptDtls";
	}
	
	/**
	 * 월간리포트 해당 차수 정보 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/mthlyHealthRptCnsl.do")
	public @ResponseBody Map<String, Object> mthlyHealthRptCnsl(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String, Object> rsMap = new HashMap<String, Object>();
		
		Map<String, Object> cnslMap = mthlyHealthRptService.selectMonthlyCnsl(param);							//상담내용조회
		List<Map<String, Object>> cnslAttchList = new ArrayList<Map<String, Object>>();
		
		if(!cnslMap.isEmpty()){
			if(!"".equals(cnslMap.get("ATTCH_FILE_SN"))){
				param.put("ATTCH_FILE_SN", cnslMap.get("ATTCH_FILE_SN"));
				cnslAttchList = mthlyHealthRptService.selectMonthlyCnslAttchFiles(param);							//상담 첨부파일 조회
			}
		}
		
		List<Map<String, Object>> dayExceActList = mthlyHealthRptService.selectDayExceActList(param);			//요일별 평균 걸음수
		Map<String, Object> objActMap = mthlyHealthRptService.selectObeActSuc(param);							//목표걸음달성 평균걸음수

		List<Map<String, Object>> weightChart = mthlyHealthRptService.selectWeightChartList(param);				//체중변화 차트
		List<Map<String, Object>> weightList = mthlyHealthRptService.selectWeightList(param);					//체중 목표, 현재
		
		List<Map<String, Object>> weekMealCalList = mthlyHealthRptService.selectWeekMealCalList(param);			//요일별, 끼니별 칼로리
		Map<String, Object> dayMealCal = mthlyHealthRptService.selectDayMealCal(param);							//식사기록일수, 평균섭취칼로리
		
		List<Map<String, Object>> bodyChart = mthlyHealthRptService.selectMonthlyBodyCompChartList(param);		//금월 체성분 차트
		List<Map<String, Object>> bodyDiff = mthlyHealthRptService.selectMonthlyWeightList(param);				//체성분 차이값
		
		List<Map<String, Object>> bPressChart = mthlyHealthRptService.selectMonthlyBloodPressChartList(param);	//혈압 차트
		List<Map<String, Object>> bPressAvg = mthlyHealthRptService.selectMonthlyBloodPressData(param);			//혈압 평균 정보
		
		List<Map<String, Object>> bSugarChart = mthlyHealthRptService.selectMonthlyBloodSugarChartList(param);	//혈당 차트
		List<Map<String, Object>> bSugarAvg = mthlyHealthRptService.selectMonthlyBloodSugarData(param);			//혈당 평균 정보
		
		List<Map<String, Object>> kcalTime = mthlyHealthRptService.selectMonthlyKcalTime(param);				//칼로리, 운동시간 
		Map<String, Object>	missionSurPer = mthlyHealthRptService.selectNurtMissionSucPer(param);				//영양미션 실천율
		Map<String, Object>	drugMissionSurPer = mthlyHealthRptService.selectDrugMissionSucPer(param);			//복약미션 실천율

		Map<String, Object> rqstDtls = mthlyHealthRptService.selectRqstDtls(param);								// 의사 지시내용 차수별 조회

		rsMap.put("cnslMap", cnslMap);
		rsMap.put("cnslAttchList", cnslAttchList);
		rsMap.put("dayExceActList", dayExceActList);
		rsMap.put("objActMap", objActMap);
		rsMap.put("weightChart", weightChart);
		rsMap.put("weightList", weightList);
		rsMap.put("weekMealCalList", weekMealCalList);
		rsMap.put("dayMealCal", dayMealCal);
		rsMap.put("bodyChart", bodyChart);
		rsMap.put("bodyDiff", bodyDiff);
		rsMap.put("bPressChart", bPressChart);
		rsMap.put("bPressAvg", bPressAvg);
		rsMap.put("bSugarChart", bSugarChart);
		rsMap.put("bSugarAvg", bSugarAvg);
		rsMap.put("kcalTime", kcalTime);
		rsMap.put("missionSurPer", missionSurPer);
		rsMap.put("drugMissionSurPer", drugMissionSurPer);
		rsMap.put("rqstDtls", rqstDtls);
		return rsMap;
	}
	
	/**
	 * 월간리포트 저장
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateMonthlyCnsl.do")
	public @ResponseBody int updateMonthlyCnsl(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		int rsInt = 0;
		rsInt = mthlyHealthRptService.updateMonthlyCnsl(param);
		return rsInt;
	}
	
	/**
	 * 월간리포트 삭제
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteMonthlyCnsl.do")
	public @ResponseBody int deleteMonthlyCnsl(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		int rsInt = 0;
		rsInt = mthlyHealthRptService.deleteMonthlyCnsl(param);
		return rsInt;
	}
	
	/**
	 * 월간리포트 푸시 전송
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateMonthlySubmit.do")
	public @ResponseBody int updateMonthlySubmit(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		int rsInt = 0;
		rsInt = mthlyHealthRptService.updateSubmit(param);
		return rsInt;
	}
	
	/**
	 * 운동일기 내역 팝업 호출
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/excsHistPop.do")
	public String excsHistPop(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		return "web/st/excsHistPop";
	}
	
	
}
