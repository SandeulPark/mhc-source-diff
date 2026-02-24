package kr.go.mhc.mhcapp.sv.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.mhcapp.sv.service.MonthlyReportService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class MonthlyReportController extends DMultiActionController{ 
	@Resource(name="mhcapp.sv.MonthlyReportService")
	private MonthlyReportService monthlyReportService;

	@ModelAttribute
	public Map initData(HttpServletRequest req) throws Exception{
		return super.initData(req);
	}
	
	/**
	 * 월간리포트 리스트 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/monthlyReportMain.do", method = RequestMethod.GET)
	public String monthlyMain(@ModelAttribute Map param, ModelMap model) throws Exception{
		
		return "app/sv/monthlyReport";
	}	
	
	/**
	 * 월간리포트 카테고리 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 **/
	@RequestMapping( value="/monthlyReportCategoryList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectServeyList(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		
		List<Map<String,String>> rsList = monthlyReportService.selectCategoryList(param);   

		rsMap.put("rsList", rsList);
		if(rsList.size()==0){
			//rsMap.put("msg", getMsg("common.list.null"));
		}else{
			//rsMap.put("msg", getMsg("common.list.succ"));
		}
		
		return rsMap;
	}	
	
	/**
	 * 리포트 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 **/
	@RequestMapping( value="/monthlyReportList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectMonthlyReportList(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		
		List<Map<String,String>> rsList = monthlyReportService.selectMonthlyReportList(param);   
		
		rsMap.put("rsList", rsList);
		if(rsList.size()==0){
			//rsMap.put("msg", getMsg("common.list.null"));
		}else{
			//rsMap.put("msg", getMsg("common.list.succ"));
		}
		
		return rsMap;
	}	
	
	/**
	 * 리포트 리스트 1 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 **/
	@RequestMapping( value="/cmmn/monthlyReportTotList1.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> monthlyReportTotList1(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		Map<String,Object> rsMap_sb = new HashMap<String,Object>();
		
		List<Map<String,String>> periodList = monthlyReportService.selectPeriod(param);						// 평가기간 조회
		
		List<Map<String,String>> dayExceActList = monthlyReportService.selectDayExceActList(param);   	// 요일별 평균 걸음수 조회
		Map<String,String> objActMap = monthlyReportService.selectObeActSuc(param);						// 목표걸음달성 평균걸음수 조회
		
		List<Map<String,String>> rsList8 = monthlyReportService.selectMonthlyDstc(param); //총 누적 거리 조회
		
		rsMap_sb.put("period", periodList);
		
		rsMap_sb.put("dayExceActList", dayExceActList);
		rsMap_sb.put("objActMap", objActMap);
		
		rsMap_sb.put("dstc", rsList8);
		
		rsMap.put("rsList", rsMap_sb);
		return rsMap;
	}
	
	/**
	 * 리포트 리스트 2 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 **/
	@RequestMapping( value="/cmmn/monthlyReportTotList2.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> monthlyReportTotList2(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		Map<String,Object> rsMap_sb = new HashMap<String,Object>();
		
		
		List<Map<String,String>> weekMealCalList = monthlyReportService.selectWeekMealCalList(param);	// 식사기록 요일별, 끼니별 조회
		Map<String,String> dayMealCal = monthlyReportService.selectDayMealCal(param);					// 식사기록일수, 평균섭취칼로리 조회		
		
		rsMap_sb.put("weekMealCalList", weekMealCalList);
		rsMap_sb.put("dayMealCal", dayMealCal);
		
		rsMap.put("rsList", rsMap_sb);
		return rsMap;
	}
	
	/**
	 * 리포트 리스트 3 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 **/
	@RequestMapping( value="/cmmn/monthlyReportTotList3.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> monthlyReportTotList3(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		Map<String,Object> rsMap_sb = new HashMap<String,Object>();

		List<Map<String,String>> rsList1 = monthlyReportService.selectWeightChartList(param);  //체중변화 차트
		List<Map<String,String>> rsList2 = monthlyReportService.selectMonthlyBodyCompChartList(param);  //금월 체성분 차트
		List<Map<String,String>> rsList3 = monthlyReportService.selectWeightList(param);   //목표/현재 차트
		List<Map<String,String>> rsList4 = monthlyReportService.selectMonthlyWeightList(param);   //체성분 차이값
		
		rsMap_sb.put("chart1", rsList1);
		rsMap_sb.put("chart2", rsList2);
		rsMap_sb.put("weightlist", rsList3);
		rsMap_sb.put("bodycomp", rsList4);		
		
		rsMap.put("rsList", rsMap_sb);
		return rsMap;
	}
	
	/**
	 * 리포트 리스트 4 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 **/
	@RequestMapping( value="/cmmn/monthlyReportTotList4.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> monthlyReportTotList4(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		Map<String,Object> rsMap_sb = new HashMap<String,Object>();
		
		List<Map<String,String>> rsList9 = monthlyReportService.selectMonthlyBloodPressChartList(param);   //혈압 차트 조회
		List<Map<String,String>> rsList10 = monthlyReportService.selectMonthlyBloodPressData(param);   //혈압 차트 평균 등..
		
		rsMap_sb.put("bloodpresschart", rsList9);
		rsMap_sb.put("bloodpressavg", rsList10);
		
		rsMap.put("rsList", rsMap_sb);
		return rsMap;
	}
	
	/**
	 * 리포트 리스트 5 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 **/
	@RequestMapping( value="/cmmn/monthlyReportTotList5.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> monthlyReportTotList6(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		Map<String,Object> rsMap_sb = new HashMap<String,Object>();
		
		List<Map<String,String>> rsList11 = monthlyReportService.selectMonthlyBloodSugarChartList(param);   //혈당 차트 조회
		List<Map<String,String>> rsList12 = monthlyReportService.selectMonthlyBloodSugarData(param);   //혈당 차트 평균 등..
		
		rsMap_sb.put("bloodsugarchart", rsList11);
		rsMap_sb.put("bloodsugaravg", rsList12);

		rsMap.put("rsList", rsMap_sb);
		return rsMap;
	}
	
	/**
	 * 리포트 리스트 6 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 **/
	@RequestMapping( value="/cmmn/monthlyReportTotList6.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> monthlyReportTotList7(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		Map<String,Object> rsMap_sb = new HashMap<String,Object>();
		
		List<Map<String,String>> rsList13 = monthlyReportService.selectMeasrComplete(param);   //측정 달성 조회
		List<Map<String,String>> rsList14 = monthlyReportService.selectContentsList(param);   //컨텐츠리스트 조회
		List<Map<String,String>> rsList15 = monthlyReportService.selectUncnfmCnslList(param); //미확인상담 조회
		Map<String,String> nurtMissionSurPer = monthlyReportService.selectNurtMissionSucPer(param);		// 영양미션실천율 조회
		List<Map<String,String>> rsList7 = monthlyReportService.selectMonthlyKcalTime(param); //칼로리, 운동시간 조회
		
		rsMap_sb.put("kcaltime", rsList7);
		
		rsMap_sb.put("complete", rsList13);
		rsMap_sb.put("contentslist", rsList14);
		rsMap_sb.put("uncnfmCnslList", rsList15);
		rsMap_sb.put("nurtMissionSurPer", nurtMissionSurPer);		
		
		rsMap.put("rsList", rsMap_sb);
		return rsMap;
	}
	
	/**
	 * 리포트 리스트 7 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 **/
	@RequestMapping( value="/cmmn/monthlyReportTotList7.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> monthlyReportTotList8(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		Map<String,Object> rsMap_sb = new HashMap<String,Object>();
		
		List<Map<String,String>> rsList1 = monthlyReportService.selectCnslCont(param);   		// 월간리포트 상담 내용 조회
		List<Map<String,String>> rsList2 = monthlyReportService.selectAttachFileList(param);   	// 월간리포트 추가 컨텐츠 조회
		
		//자동발송 여부 로직 추가
		Map<String,String> autoChkMap = monthlyReportService.selectAutoSendYn(param);		
		String autoSendYn  = String.valueOf(autoChkMap.get("AUTO_SEND_YN"));
		String visitCnslSn = ""; 
		
		if("Y".equals(autoSendYn)){
			visitCnslSn = String.valueOf(autoChkMap.get("VISIT_CNSL_SN"));		
			rsMap_sb.put("VISIT_CNSL_SN", visitCnslSn);	//중간 방문상담 건강관리 정보 조회			
		}
		rsMap_sb.put("AUTO_SEND_YN", autoSendYn);			//자동발송 여부 조회		
		
		rsMap_sb.put("contentList", rsList1);
		rsMap_sb.put("cnslAttachList", rsList2);
		rsMap.put("rsList", rsMap_sb);

		return rsMap;
	}
	
	/**
	 * 월간리포트 종합 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/monthlyReportTotalMain.do", method = RequestMethod.GET)
	public String monthlyReportMain(@ModelAttribute Map param, ModelMap model) throws Exception{
		model.addAllAttributes(param);
		return "app/sv/monthlyReportTotal";
	}
	
	/**
	 * 월간리포트 종합 1페이지(체성분관련)
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 **/
	@RequestMapping( value="/monthlyReport1List.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectMonthlyReport1List(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		Map<String,Object> rsMap_sb = new HashMap<String,Object>();
		
		List<Map<String,String>> rsList1 = monthlyReportService.selectWeightChartList(param);  //체중변화 차트
		List<Map<String,String>> rsList2 = monthlyReportService.selectMonthlyBodyCompChartList(param);  //금월 체성분 차트
		List<Map<String,String>> rsList3 = monthlyReportService.selectWeightList(param);   //목표/현재 차트
		List<Map<String,String>> rsList4 = monthlyReportService.selectMonthlyWeightList(param);   //체성분 차이값
		
		rsMap_sb.put("chart1", rsList1);
		rsMap_sb.put("chart2", rsList2);
		rsMap_sb.put("weightlist", rsList3);
		rsMap_sb.put("bodycomp", rsList4);
		
		rsMap.put("rsList", rsMap_sb);
		return rsMap;
	}	
	
	/**
	 * 월간리포트 종합 2페이지(활동량)
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 **/
	@RequestMapping( value="/monthlyReport2List.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectMonthlyReport2List(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		Map<String,Object> rsMap_sb = new HashMap<String,Object>();
		
		List<Map<String,String>> rsList1 = monthlyReportService.selectMonthlyActChartList(param);   //활동량 차트 데이터 조회
		List<Map<String,String>> rsList2 = monthlyReportService.selectMonthlyActList(param);   // 총 활동량,평균 활동량
		List<Map<String,String>> rsList3 = monthlyReportService.selectMonthlyKcalTime(param); //칼로리, 운동시간 조회
		List<Map<String,String>> rsList4 = monthlyReportService.selectMonthlyDstc(param); //총 누적 거리 조회
		
		rsMap_sb.put("actchart", rsList1);
		rsMap_sb.put("act", rsList2);
		rsMap_sb.put("kcaltime", rsList3);
		rsMap_sb.put("dstc", rsList4);
		rsMap.put("rsList", rsMap_sb);
		return rsMap;
	}	
	
	/**
	 * 월간리포트 종합 3페이지(혈압)
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 **/
	@RequestMapping( value="/monthlyReport3List.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectMonthlyReport3List(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		Map<String,Object> rsMap_sb = new HashMap<String,Object>();
		
		List<Map<String,String>> rsList1 = monthlyReportService.selectMonthlyBloodPressChartList(param);   //혈압 차트 조회
		List<Map<String,String>> rsList2 = monthlyReportService.selectMonthlyBloodPressData(param);   //혈압 차트 평균 등..
		
		rsMap_sb.put("bloodpresschart", rsList1);
		rsMap_sb.put("bloodpressavg", rsList2);
		rsMap.put("rsList", rsMap_sb);
		return rsMap;
	}	
	
	/**
	 * 월간리포트 종합 4페이지(혈당)
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 **/
	@RequestMapping( value="/monthlyReport4List.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectMonthlyReport4List(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		Map<String,Object> rsMap_sb = new HashMap<String,Object>();
		
		List<Map<String,String>> rsList1 = monthlyReportService.selectMonthlyBloodSugarChartList(param);   //혈당 차트 조회
		List<Map<String,String>> rsList2 = monthlyReportService.selectMonthlyBloodSugarData(param);   //혈당 차트 평균 등..
		
		rsMap_sb.put("bloodsugarchart", rsList1);
		rsMap_sb.put("bloodsugaravg", rsList2);
		rsMap.put("rsList", rsMap_sb);
		return rsMap;
	}	
	
	/**
	 * 월간리포트 종합 페이지(최종)
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 **/
	@RequestMapping( value="/monthlyReport5List.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectMonthlyReport5List(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		Map<String,Object> rsMap_sb = new HashMap<String,Object>();
		
		List<Map<String,String>> rsList1 = monthlyReportService.selectMeasrComplete(param);   //측정 달성 조회
		List<Map<String,String>> rsList2 = monthlyReportService.selectContentsList(param);   //컨텐츠리스트 조회
		
		rsMap_sb.put("complete", rsList1);
		rsMap_sb.put("contentslist", rsList2);
		rsMap.put("rsList", rsMap_sb);
		return rsMap;
	}	
	
	/**
	 * 리포트 확인 업데이트
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 **/
	@RequestMapping( value="/reportCnfmChk.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> updateReportCnfmChk(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		int rsCount=monthlyReportService.updateReportCnfmChk(param);
		rsMap.put("rsList", rsCount);
		return rsMap;
	}

	/**
	 * 월간리포트 종합 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/cmmn/callMonthlyReportTotalMain.do", method = RequestMethod.GET)
	public String callMonthlyReportMain(@ModelAttribute Map param, ModelMap model) throws Exception{
		model.addAllAttributes(param);
		return "app/sv/monthlyReportTotal";
	}
	
	/**
	 * 월간리포트 카테고리 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 **/
	@RequestMapping( value="/cmmn/monthlyReportCategoryList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> callSelectServeyList(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		
		List<Map<String,String>> rsList = monthlyReportService.selectCategoryList(param);   

		rsMap.put("rsList", rsList);
		if(rsList.size()==0){
			//rsMap.put("msg", getMsg("common.list.null"));
		}else{
			//rsMap.put("msg", getMsg("common.list.succ"));
		}
		
		return rsMap;
	}	
}
