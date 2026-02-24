package kr.go.mhc.mhcapp.sv.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kr.go.mhc.common.DMultiActionController;


import kr.go.mhc.mhcapp.sv.service.MyHealthMainDashService;
import kr.go.mhc.mhcapp.sv.service.VisitReservationService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Class Name : MyHealthMainDashController.java
 * @Description : 모바일 헬스케어 App에서 사용하는 나의건강-메인 대시를 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일				수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.07.01		오명빈			최초생성
 * 		
 *
 * @author gst
 * @since 2016.07.01
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
public class MyHealthMainDashController extends DMultiActionController{ 
	@Resource(name="mhcapp.sv.MyHealthMainDashService")
	private MyHealthMainDashService myhealthService;
	
	@Resource(name="mhcapp.sv.VisitReservationService")
	private VisitReservationService visitReservationService;

	@ModelAttribute
	public Map initData(HttpServletRequest req) throws Exception{
		return super.initData(req);
	}


	/**
	 * 나의건강 메인 대시 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/myhealthMain.do", method = RequestMethod.GET)
	public ModelAndView noticeMain(@ModelAttribute Map param, ModelMap model) throws Exception{
		  ModelAndView modelAndView = new ModelAndView();
		  modelAndView.setViewName("app/sv/myhealthMainDash");
		  modelAndView.addObject("flag", param.get("flag"));
		return modelAndView;
	}	
	
	
	/**
	 * 나의건강 상세 활동량 목록들 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@RequestMapping( value="/actMaindash.do", method=RequestMethod.POST)	//, method = RequestMethod.POST
	public @ResponseBody Map<String,Object> selectActMainDashList(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		Map<String,Object> rsMap_sb = new HashMap<String,Object>();
		String chkYn = "N";
		
		try{
			List<Map<String,String>> rsList1 = myhealthService.selectActDtlsList(param);   
			List<Map<String,String>> rsList2 = myhealthService.selectActTot(param);     
			List<Map<String,String>> rsList3 = myhealthService.selectActChartList(param);
			rsMap_sb.put("actlist", rsList1); //활동량 리스트
			rsMap_sb.put("acttot", rsList2); //활동량 총걸음수, 총거리, 칼로리, 운동시간
			rsMap_sb.put("actchart", rsList3); //활동량 차트
			
			rsMap.put("rsList", rsMap_sb);
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}	
	
	/**
	 * 나의건강 상세 활동량 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@RequestMapping( value="/selectActList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> actList(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String,String>> rsList = new ArrayList<Map<String,String>>();
		String chkYn = "N";
		
		try{
			 rsList = myhealthService.selectActDtlsList(param);
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("rsList", rsList);
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}	
	
	/**
	 * 활동량  차트 1일 데이터 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@RequestMapping( value="/actDtlsChart1.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectActDtlsChart(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String,String>> rsList = new ArrayList<Map<String,String>>();
		String chkYn = "N";
		
		try{
			rsList = myhealthService.selectActDtlsChart(param);
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("rsList", rsList);
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}	
	/**
	 * 메인 활동량  데이터 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@RequestMapping( value="/actDtlsStat1.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectActDtlsStat1(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String,String>> rsList = new ArrayList<Map<String,String>>();
		String chkYn = "N";
		
		try{
			rsList = myhealthService.selectActDtlsStat1(param);
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("rsList", rsList);
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}	
	/**
	 * 활동량  상세 통계 1주일 데이터 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@RequestMapping( value="/actDtlsStat2.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectActDtlsStat2(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String,String>> rsList =new ArrayList<Map<String,String>>();
		String chkYn = "N";
		
		try{
			rsList = myhealthService.selectActDtlsStat2(param);
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("rsList", rsList);
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}	
	/**
	 * 활동량  상세 통계 1,3개월 데이터 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@RequestMapping( value="/actDtlsStat3.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectActDtlsStat3(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String,String>> rsList = new ArrayList<Map<String,String>>();
		String chkYn = "N";
				
		try{
			rsList = myhealthService.selectActDtlsStat3(param);
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("rsList", rsList);
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}	
	
	/**
	 * 활동량  차트 1주일 데이터 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@RequestMapping( value="/actDtlsChart2.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectActDtlsChart2(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String,String>> rsList = new ArrayList<Map<String,String>>();
		String chkYn = "N";
		
		try{
			rsList = myhealthService.selectActDtlsChart2(param);
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("rsList", rsList);
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}	
	/**
	 * 활동량  차트 1주일 데이터 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@RequestMapping( value="/actDtlsChart3.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectActDtlsChart3(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String,String>> rsList = new ArrayList<Map<String,String>>();
		String chkYn = "N";
		
		try{
			rsList = myhealthService.selectActDtlsChart3(param);
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("rsList", rsList);
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}	

	/**
	 * 심박수 정보 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@RequestMapping( value="/heartRateChartList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectHeartRateChartList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
//		List<Map<String,String>> rsList = new ArrayList<Map<String,String>>();
//		Map<String,Object> listMap = new HashMap<String,Object>();
		Map<String,Object> statsMap = new HashMap<String,Object>();
		String chkYn = "N";
		
		try{
			rsMap = myhealthService.selectMainHeartRateChartList(param);
			statsMap = myhealthService.selectHeartRateStats(param);
			rsMap.put("statsMap", statsMap);
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
//		rsMap.put("returnVal", rsList);
		System.out.println("==========================================="+rsMap);
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}	
	
	/**
	 * 심박수 그래프 상세 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@RequestMapping( value="/heartRateDtlsChart.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectHeartRateDtlsChart(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String,String>> rsList = new ArrayList<Map<String,String>>();
		String chkYn = "N";
		
		try{
			rsList = myhealthService.heartRateDtlsChart(param);
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("rsList", rsList);
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}	
	
	/**
	 * 혈압 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@RequestMapping( value="/bloodPressList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectBloodPressChartList(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String,String>> rsList = new ArrayList<Map<String,String>>();
		String chkYn = "N";
		
		try{
			rsList = myhealthService.selectBloodPressList(param);
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("rsList",rsList);
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}	
	
	/**
	 * 혈압 차트 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@RequestMapping( value="/bloodPressChartList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectBloodPressList(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String,String>> rsList = new ArrayList<Map<String,String>>();
		String chkYn = "N";
		
		try{
			rsList = myhealthService.selectBloodPressChartList(param);
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("rsList", rsList);
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}	
	
	/**
	 * 혈압 상세 평균 및 횟수 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@RequestMapping( value="/bloodPressDtlsList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectBloodPressDtlsList(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String,String>> rsList = new ArrayList<Map<String,String>>();
		String chkYn = "N";
		
		try{
			rsList = myhealthService.selectBloodPressDtlsList(param);
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("rsList", rsList);
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}	
	
	/**
	 * 혈압 상세 차트 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@RequestMapping( value="/bloodPressDtlsChartList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectBloodPressDtlsChartList(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String,String>> rsList = new ArrayList<Map<String,String>>();
		String chkYn = "N";
		
		try{
			rsList = myhealthService.selectBloodPressDtlsChartList(param);
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("rsList", rsList);
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}	
	/**
	 * 혈당 상세 평균 및 횟수 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@RequestMapping( value="/bloodSugarDtlsList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectBloodSugarDtlsList(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String,String>> rsList = new ArrayList<Map<String,String>>();
		String chkYn = "N";
		
		try{
			rsList = myhealthService.selectBloodSugarDtlsList(param);
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("rsList", rsList);
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}	
	
	/**
	 * 혈당 상세 차트 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@RequestMapping( value="/bloodSugarDtlsChartList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectBloodSugarDtlsChartList(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String,String>> rsList = new ArrayList<Map<String,String>>();
		String chkYn = "N";
		
		try{
			rsList = myhealthService.selectBloodSugarDtlsChartList(param);
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("rsList", rsList);
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}	
	
	/**
	 * 혈당 차트 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@RequestMapping( value="/bloodSugarChartList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectBloodSugarChartList(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String,String>> rsList = new ArrayList<Map<String,String>>();
		String chkYn = "N";
		
		try{
			rsList = myhealthService.selectBloodSugarChartList(param);
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("rsList", rsList);
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}	
	
	/**
	 * 혈당 리스트 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@RequestMapping( value="/bloodSugarList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectBloodSugarList(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String,String>> rsList = new ArrayList<Map<String,String>>();
		Map<String,Object> rangeMap = new HashMap<String,Object>();
		String chkYn = "N";
		
		try{
			rsList = myhealthService.selectBloodSugarList(param);
//			rangeMap = myhealthService.selectBloodSugarRange(param);
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("rsList", rsList);
		rsMap.put("rangeMap", rangeMap);
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}	
	
	/**
	 * 체성분 차트 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@RequestMapping( value="/bodyCompChartList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectBodyCompChartList(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String,String>> rsList = new ArrayList<Map<String,String>>();
		List<Map<String,String>> cnslList = new ArrayList<Map<String,String>>();
		String chkYn = "N";
		
		try{
			rsList = myhealthService.selectBodyCompChartList(param);
			cnslList = myhealthService.selectBodyCompCnslList(param);
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("chartList", rsList);
		rsMap.put("cnslList", cnslList);
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}	
	
	/**
	 * 체성분 리스트 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping( value="/bodyCompList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectBodycompList(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String,String>> rsList = new ArrayList<Map<String,String>>();
		String chkYn = "N";
		
		try{
			rsList = myhealthService.selectBodycompList(param);
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("rsList", rsList);
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}
	
	/**
	 * 체성분 상세 차트 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@RequestMapping( value="/bodyCompDtlsChartList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectBodyCompDtlsChartList(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String,String>> rsList = new ArrayList<Map<String,String>>();
		String chkYn = "N";
		
		try{
			rsList = myhealthService.selectBodyCompDtlsChartList(param);
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("rsList", rsList);
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}	
	
	/**
	 * 체성분 상세 차트 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@RequestMapping( value="/bodyCompDtlsList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectBodyCompDtlsList(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String,String>> rsList = new ArrayList<Map<String,String>>();
		String chkYn = "N";
		
		try{
			rsList = myhealthService.selectBodyCompDtlsList(param);
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("rsList", rsList);
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}	
	
	/**
	 * 운동기록 등록 데이터 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@RequestMapping( value="/exeRecordList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectExeRecordList(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String,String>> rsList = new ArrayList<Map<String,String>>();
		String chkYn = "N";
		
		try{
			rsList = myhealthService.selectExeRecordList(param);
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("rsList", rsList);
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}	
	
	/**
	 * 운동기록 저장
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/exeRecordInsert.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> insertExeRecord(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		int rsCount = 0;
		String chkYn = "N";
		
		try{
			rsCount = myhealthService.exeRecordInsert(param);
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("rsList", rsCount);
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}	
	
	/**
	 * 운동기록 수정
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/exeRecordUpdate.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> updateExeRecord(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		int rsCount = 0;
		String chkYn = "N";
		
		try{
			rsCount = myhealthService.exeRecordUpdate(param);
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("rsList", rsCount);
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}	
	
	/**
	 * 운동기록 삭제
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/exeRecordDelete.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> deleteExeRecord(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		int rsCount = 0;
		String chkYn = "N";
		
		try{
			rsCount = myhealthService.exeRecordDelete(param);
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("rsList", rsCount);
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}	
	
	/**
	 * 식사기록 등록 데이터 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@RequestMapping( value="/dietRecordList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectdietRecordList(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		Map<String,Object> rsMap2 = new HashMap<String,Object>();
		String chkYn = "N";
		
		try{
			param.put("INSERT", "N");
			List<Map<String,String>> rsList = myhealthService.selectDietRecordList(param);   
			rsMap2.put("recList",rsList);
			
			param.put("INSERT", "Y");
			List<Map<String,String>> rsList2 = myhealthService.selectDietRecordList(param);   
			rsMap2.put("recList2",rsList2);
			
			rsMap.put("rsList",rsMap2);
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("chkYn", chkYn);
		model.addAllAttributes(rsMap);
		return rsMap;
	}	
	
	/**
	 * 식사 구분 조회
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/exeRecordClfCheck.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> checkExeRecordClf(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String,String>> rsList = new ArrayList<Map<String,String>>();
		String chkYn = "N";
		
		try{
			rsList = myhealthService.exeRecordClfCheck(param);
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("rsList", rsList);
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}	
	
	
	/**
	 * 식사기록 저장
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/dietRecordInsert.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> insertDietRecord(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		int rsList = 0;
		String chkYn = "N";
		
		try{
			rsList = myhealthService.dietRecordInsert(param);
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("rsList", rsList);
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}	
	
	/**
	 * 식사기록 수정
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/dietRecordUpdate.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> updateDietRecord(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		int rsCount = 0;
		String chkYn = "N";
		
		try{
			rsCount = myhealthService.dietRecordUpdate(param);
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("rsList", rsCount);
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}	
	
	/**
	 * 식사상담 추가
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/dietCnslUpdate.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> updateDietCnsl(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		int rsCount = 0;
		String chkYn = "N";
		
		try{
			rsCount = myhealthService.dietCnslUpdate(param);
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("rsList", rsCount);
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}	
	
	
	/**
	 * 식사 상세 조회
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/mealDtlsList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectMealDtls(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String,String>> rsList = myhealthService.selectMealDtls(param);   
		rsMap.put("rsList", rsList);
		return rsMap;
	}	
	
	/**
	 * 식사 상세 추가
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/mealDtlsInsert.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> insertMealDtls(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap1 = new HashMap<String,Object>();
		Map<String,Object> rsMap2= new HashMap<String,Object>();
		String insertChk = "N";
		int rsCount = 0;
		
		//체크
		List<Map<String,String>> pList = myhealthService.selectDietPeriod(param); 
		Map<String,Object> rParam = new HashMap<String,Object>();
		rParam.put("INSERT", "Y");
		rParam.put("SESS_USER_ID", param.get("SESS_USER_ID").toString());
		rParam.put("CNSL_PRD_BGN_DE",pList.get(0).get("CNSL_PRD_BGN_DE").toString());
		rParam.put("CNSL_PRD_END_DE",pList.get(0).get("CNSL_PRD_END_DE").toString());
		List<Map<String, String>> rList = myhealthService.selectDietRecordList(rParam);	//집중상담 기간내에 등록된 식단 조회
		List<Map<String,String>> rsList = myhealthService.dietMasterCheck(param);  		//해당일자에 등록된 식단 조회
		
		if(rList.isEmpty()){
			insertChk = "Y";
		}else{
			if(rsList.size()>0){
				insertChk = "YU";
			}else{
				insertChk = "B";
			}
		}
		
		if(insertChk=="Y"){
			myhealthService.dietRecordInsert(param);  
		}
		
		if(insertChk!="B"){
			int snCnt = 0;
			//식사 상세 입력시 식사 입력 없을시 SN조회
			if("".equals((String)param.get("MEAL_REG_SN"))){
				snCnt = myhealthService.selectMealRegSn(param);
				param.put("MEAL_REG_SN", snCnt);
			}else{
				snCnt = Integer.parseInt((String)param.get("MEAL_REG_SN")); 
			}
			rsMap2.put("MEAL_REG_SN", snCnt);
			rsCount = myhealthService.mealDtlsInsert(param);
		}
		
		rsMap2.put("insertChk", insertChk);
		rsMap2.put("rsCount", rsCount);
		rsMap1.put("rsList", rsMap2);
		return rsMap1;
	}	
	
	/**
	 * 식사 기록 삭제
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/mealAllDelete.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> allDeleteMeal(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		int rsCount = 0;
		String chkYn = "N";
		
		try{
			rsCount = myhealthService.mealAllDelete(param);
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("rsList", rsCount);
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}	
	
	//------------------------------------------------------------------------------------------------
	/**
	 * 메인 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/myhealth.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> main(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String,String>> rsList11 = new ArrayList<Map<String,String>>();
		List<Map<String,String>> rsList12 = new ArrayList<Map<String,String>>();
		List<Map<String,String>> rsList14 = new ArrayList<Map<String,String>>();
		List<Map<String,String>> rsList15 = new ArrayList<Map<String,String>>();
		
		Map<String,String> resveSttusMap = new HashMap<String,String>();
		String chkYn = "N";
		
		try{
			rsList11 = myhealthService.selectNoticeListChk(param); //알림내역리스트
			rsList12 = myhealthService.selectDietPrdChk(param); //집중상담기간체크
			rsList14 =myhealthService.selectPractMissionSch(param); //실천 미션 스케쥴 조회 
			resveSttusMap = visitReservationService.selectResveSttus(param);
			
			//20230424 chyoon 복약 미션 조회 추가
			if("Y".equals(param.get("SESS_CHRONIC_DISEASES_YN"))) {
				rsList15 = myhealthService.selectDrugList(param); //복약 미션 조회 					
				if(rsList15.size() > 0) {
					rsMap.put("drugMission", "Y");
				}
			}
			
			if(rsList12.size()>=1){
				rsMap.put("dietPrd", "Y");
			}
			if(rsList14.size() > 0){
				rsMap.put("practMission", "Y");
			}
			
			
			
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("list", rsList11);
		rsMap.put("chkYn", chkYn);
		rsMap.put("SCHDUL_CNT", resveSttusMap.get("SCHDUL_CNT"));
		rsMap.put("RESRVT_CNT", resveSttusMap.get("RESRVT_CNT"));
		return rsMap;
	}	
	

	/**
	 * 메인 사용자별 데이터 조회
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/mainUserData.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectMainUserData(HttpServletRequest req, @ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		Map<String,Object> rsMap_sub = new HashMap<String,Object>();
		HttpSession session=req.getSession();
		String chkYn = "N";
		
		try{
			List<Map<String,String>> rsList2 = myhealthService.selectActDtlsStat1(param); 
			List<Map<String,String>> rsList16 = myhealthService.selectActDtlsStat2(param); 
			List<Map<String,String>> rsList5 = myhealthService.selectExerDietData(param);
			List<Map<String,String>> rsList6 = myhealthService.selectNoticeChk(param); //알림도착체크
			List<Map<String,String>> rsList7 = myhealthService.selectMeasrActList(param); //주간 미션 측정 정보 조회
			List<Map<String,String>> rsList8 = myhealthService.selectActTot(param); //미션 주건 칼로리, 운동시간 체크    
			List<Map<String,String>> rsListCnfm = myhealthService.selectContentsCnfm(param); //주차별 컨텐츠 확인
			List<Map<String,String>> rsList9 = new ArrayList<Map<String,String>>();
			List<Map<String,String>> rsList13 = myhealthService.selectDietPeriod(param); //집중상담 식사 등록 기간
			List<Map<String,String>> rsList18 = myhealthService.selectMobileNotice(param); // 모바일 공지사항 조회
			
			if(rsListCnfm != null){
				for (int i = rsListCnfm.size() - 1; i >= 0; i--) {
					Map<String,String> cnfmMap = rsListCnfm.get(i);
					//컨텐츠
					if("Y".equals(cnfmMap.get("CNFM_YN"))){ //확인
						rsListCnfm.remove(i);				
					}
				}
				if(rsListCnfm.size() > 0){
					param.put("CONTENT_LIST", rsListCnfm);
					rsMap_sub.put("mainPopChk", rsListCnfm);
					rsList9 = myhealthService.selectContentsDtls(param);
				}
			}
			rsMap_sub.put("act", rsList2);
			rsMap_sub.put("act2", rsList16);
			rsMap_sub.put("exerdiet", rsList5);
			rsMap_sub.put("noticechk", rsList6);
			rsMap_sub.put("measrcnt", rsList7);
			rsMap_sub.put("kcaltime", rsList8);
			rsMap_sub.put("contents", rsList9);
			rsMap_sub.put("dietPeriod", rsList13);
			rsMap_sub.put("noticeList", rsList18);

			//20170331 윤봉훈 - 설정값에 따른 대시보드 컨텐츠 조회 분기 추가
			if("Y".equals(session.getAttribute("SESS_DASH_BODYCOMP_SET_YN")) || "Y".equals(session.getAttribute("SESS_DASH_WEIGHT_SET_YN"))){
				List<Map<String,String>> rsList1 = myhealthService.selectMainBodyCompChartList(param);   
				rsMap_sub.put("bodycomp", rsList1);
			}
			if("Y".equals(session.getAttribute("SESS_DASH_BLOODPRESS_SET_YN")) || "Y".equals(session.getAttribute("SESS_BLOOD_PRESS_PYMNT_YN"))){
				List<Map<String,String>> rsList3 = myhealthService.selectBloodPressData(param);  
				rsMap_sub.put("bloodpress", rsList3);
			}
			if("Y".equals(session.getAttribute("SESS_DASH_BLOODSUGAR_SET_YN")) || "Y".equals(session.getAttribute("SESS_BLOOD_SUGAR_PYMNT_YN"))){
				List<Map<String,String>> rsList4 = myhealthService.selectBloodSugarData(param);
				rsMap_sub.put("bloodsugar", rsList4);
			}
//			if("Y".equals(session.getAttribute("SESS_DASH_HEARTRATE_SET_YN")) && !"Y".equals(session.getAttribute("SESS_ACT_SELFMEASR_SET_YN"))){
				rsMap_sub.put("heartRate", myhealthService.selectHeartRateStats(param));
//			}
			
			//20170331 윤봉훈 - 실천 미션 조회 추가
			List<Map<String,String>> rsList14 = myhealthService.selectPractMissionSch(param); //실천 미션 스케쥴 조회
			List<Map<String,String>> rsList15 = null;
			if(rsList14 != null){
				if(rsList14.size() > 0){
					Map<String,String> missionMap = (Map<String, String>)rsList14.get(0);
					rsList15 = myhealthService.selectPractMissionAnswr(missionMap); //실천 미션 답변 조회
				}
			}
			rsMap_sub.put("practSch", rsList14);
			rsMap_sub.put("practAnswr", rsList15);
			
			//20230424 chyoon 복약 미션 조회 추가
			if("Y".equals(session.getAttribute("SESS_CHRONIC_DISEASES_YN"))) {
				List<Map<String,String>> rsList17 = myhealthService.selectDrugList(param);  
				
				if(rsList17 != null) {
					rsMap_sub.put("drugInfo", rsList17);
				}
				
			}
			
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("rsList", rsMap_sub);
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}	
	
	/**
	 * 식사 상담기간 조회
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/dietPeriodList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectDietPeriod(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String,String>> rsList = new ArrayList<Map<String,String>>();
		String chkYn = "N";
		
		try{
			rsList = myhealthService.selectDietPeriod(param);
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("rsList", rsList);
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}
	
	/**
	 * 운동 목록 조회
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/excsList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectExcsList(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String,String>> rsList = new ArrayList<Map<String,String>>();
		String chkYn = "N";
		
		try{
			rsList = myhealthService.selectExcsList(param);
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("rsList", rsList);
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}
	
	
	/**
	 * 식사 일기 메인 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping( value="/mealDiaryList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectMealDiaryList(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		Map<String,Object> selMap = new HashMap<String,Object>();
		String chkYn = "N";
		
		try{
			//List<Map<String,String>> eatList1 = myhealthService.selectObjEatDay(param);			//영양소섭취군별 섭취 카운트 정보
			List<Map<String,String>> eatList2 = myhealthService.selectObjEatNeed(param);		//영양소별 기준 섭취량 대비 비율 정보
			List<Map<String,String>> eatList3 = myhealthService.selectMealDiaryList(param); 	//식사일기 끼니 구분 별 정보
			List<Map<String,String>> practList = myhealthService.selectPractMission(param);		//실천미션 정보
			//Map<String,Object> stndRecomPer = myhealthService.selectStndRecomPer(param);		//적정 탄단지 비율 정보
			List<Map<String,String>> eatList4 = myhealthService.selectMealDiarySgCal(param);	//유지당류 및 주류 섭취 칼로리 정보
			List<Map<String,String>> weekList = myhealthService.selectMealDiaryWeekInfo(param);	//날짜정보
			
			selMap.put("weekList", weekList);
			//selMap.put("eatList1", eatList1);
			selMap.put("eatList2", eatList2);
			selMap.put("eatList3", eatList3);
			selMap.put("eatList4", eatList4);
			selMap.put("practList", practList);
			//selMap.put("stndRecomPer", stndRecomPer);
			selMap.put("isMobile", param.get("SESS_ISMOBILE"));
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("rsList", selMap);
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}
	
	/**
	 * 식사 일기 상세 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping( value="/mealDiaryDtlsList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectMealDiaryDtlsList(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String,String>> rsList = new ArrayList<Map<String,String>>();
		String chkYn = "N";
		
		try{
			rsList = myhealthService.selectMealDiaryDtlsList(param);
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("rsList", rsList);
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}
	
	/**
	 * 음식 검색어 조회
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping( value="/selectMealSearch.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> mealSearch(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String,String>> rsList = new ArrayList<Map<String,String>>();
		String chkYn = "N";
		
		try{
			rsList = myhealthService.selectMealSearch(param);
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("rsList", rsList);
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}
	
	/**
	 * 음식 칼로리 정보 조회 - 팝업
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping( value="/selectFoodSearch.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> foodSearch(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String,String>> rsList = new ArrayList<Map<String,String>>();
		String chkYn = "N";
		
		try{
			rsList = myhealthService.selectFoodSearch(param);
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("rsList", rsList);
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}
	
	/**
	 * 음식인식 결과 저장
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
//	@RequestMapping( value="/foodRecognInsert.do", method = RequestMethod.POST)
//	public @ResponseBody Map<String,Object> insertMealDiary2(@ModelAttribute Map param, ModelMap model) throws Exception{
//		Map<String,Object> rsMap = new HashMap<String,Object>();
//		int rsCnt = 0;
//		String chkYn = "N";		
//		try{
//			rsCnt = myhealthService.foodRecognInsert(param);
//			if(rsCnt==1) chkYn = "Y";
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		rsMap.put("rsList", rsCnt);
//		rsMap.put("chkYn", chkYn);
//		model.addAllAttributes(rsMap);
//		return rsMap;
//	}
	/**
	 * 식사_일기 저장
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping( value="/mealDiaryInsert.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> insertMealDiary(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		int rsCnt = 0;
		String chkYn = "N";
		
		try{
			rsCnt = myhealthService.mealDiaryInsert(param);
			if(rsCnt==1) chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("rsList", rsCnt);
		rsMap.put("chkYn", chkYn);
		model.addAllAttributes(rsMap);
		return rsMap;
	}

	/**
	 * 실천 미션 스케쥴 조회
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/selectPractMissionList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> practMissionList(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		//20170331 윤봉훈 - 실천 미션 조회 추가
		List<Map<String,String>> rsListSch = new ArrayList<Map<String,String>>();
		List<Map<String,String>> rsListFile = new ArrayList<Map<String,String>>();
		List<Map<String,String>> rsListAnswr = null;
		String chkYn = "N";
		
		try{
			rsListSch = myhealthService.selectPractMissionSch(param); //실천 미션 스케쥴 조회
			rsListFile = myhealthService.selectPractMissionAttchFile(param); //실천 미션 답변 조회
			if(rsListSch != null){
				if(rsListSch.size() > 0){
					Map<String,String> missionMap = (Map<String, String>)rsListSch.get(0);
					rsListAnswr = myhealthService.selectPractMissionAnswr(missionMap); //실천 미션 답변 조회
				}
			}
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("practSch", rsListSch);
		rsMap.put("practAnswr", rsListAnswr);
		rsMap.put("practFile", rsListFile);
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}	
	
	/**
	 * 실천 미션 답변 저장
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/savePractMissionAnswr.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> savePractMission(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		int cnt = myhealthService.savePractMissionAnswr(param);
		if(cnt > 0){
			rsMap.put("chkYn", "Y");
		}else{
			rsMap.put("chkYn", "N");
		}
		
		return rsMap;
	}	
	
	/**
	 * 집중상담 식단기록 화면 호출
	 * @param param 검색 조건
	 * @throws Exception 
	 */
	@RequestMapping( value="/myhealthMeal.do", method = RequestMethod.POST)
	public String myhealthMeal(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		model.addAttribute("datebox", "Y");
		model.addAllAttributes(param);
		return "app/sv/myhealth_diet";
	}
	
	/**
	 * 집중상담 식단기록 상세 화면 호출
	 * @param param 검색 조건
	 * @throws Exception 
	 */
	@RequestMapping( value="/myhealthMealDtls.do", method = RequestMethod.GET)
	public String myhealthMealDtls(@ModelAttribute Map<String,Object> param, ModelMap model) throws Exception{
		model.addAllAttributes(param);
		return "app/sv/myhealth_meal";
	}
	
	/**
	 * 운동모드 마지막 데이터 조회
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/excsModeLastData.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectExcsModeLastData(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String,String>> rsList = new ArrayList<Map<String,String>>();
		
		try{
			rsList = myhealthService.selectExcsModeLastData(param);
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("rsList", rsList);
		
		return rsMap;
	}
	
	/**
	 * 운동스케줄 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@RequestMapping( value="/exerSchList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> exerSchList(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String,String>> rsList = new ArrayList<Map<String,String>>();
		String chkYn = "N";
		
		try{
			rsList = myhealthService.exerSchList(param);
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("rsList", rsList);
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}	
	
	/**
	 * 운동스케줄 저장
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/exerSchInsert.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> exerSchInsert(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		int rsCount = 0;
		String chkYn = "N";
		
		try{
			rsCount = myhealthService.exerSchInsert(param);
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("rsList", rsCount);
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}	
	
	/**
	 * 운동스케줄 수정
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/exeSchUpdate.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> exeSchUpdate(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		int rsCount = 0;
		String chkYn = "N";
		
		try{
			rsCount = myhealthService.exeSchUpdate(param);
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("rsList", rsCount);
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}	
	
	/**
	 * 운동스케줄 삭제
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/exeSchDelete.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> exeSchDelete(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		int rsCount = 0;
		String chkYn = "N";
		
		try{
			rsCount = myhealthService.exeSchDelete(param);
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("rsList", rsCount);
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}	
	
	
	/**
	 * 복약 미션 답변 저장
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/saveDrugMissionAnswr.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> saveDrugMission(@ModelAttribute Map<String, Object> param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		int cnt = myhealthService.saveDrugMissionAnswr(param);
		if(cnt > 0){
			rsMap.put("chkYn", "Y");
		}else{
			rsMap.put("chkYn", "N");
		}
		
		return rsMap;
	}	
}
