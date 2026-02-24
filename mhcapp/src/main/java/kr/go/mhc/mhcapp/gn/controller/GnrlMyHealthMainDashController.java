package kr.go.mhc.mhcapp.gn.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.go.mhc.common.DMultiActionController;
import kr.go.mhc.mhcapp.gn.service.GnrlMyHealthMainDashService;

/**
 * @Class Name : GnrlMyHealthMainDashController.java
 * @Description : 보편건강 App에서 사용하는 나의건강-메인 대시를 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2019.08.13		이태석			최초생성
 * 		
 *
 * @author thejoin
 * @since 2019.08.13
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Controller
@RequestMapping(value="/gn")
public class GnrlMyHealthMainDashController extends DMultiActionController{ 
	
	@Resource(name="mhcapp.gn.GnrlMyHealthMainDashService")
	private GnrlMyHealthMainDashService gnrlMyhealthService;

	@ModelAttribute
	public Map initData(HttpServletRequest req) throws Exception{
		return super.initData(req);
	}
	
	/**
	 * 시간별 활동량 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@RequestMapping( value="/selectDayActCnt.do", method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectDayActCnt(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		
		try{
			List<Map<String,String>> rsList = gnrlMyhealthService.selectDayActCnt(param);   
						
			rsMap.put("rsList", rsList);
		}catch(Exception e){
			e.printStackTrace();
		}
		return rsMap;
	}	
	
	/**
	 * 입력 정보 조회 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@RequestMapping( value="/selectSelfMeasrInfo.do", method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectInsertInfo(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		
		try{
			rsMap = gnrlMyhealthService.selectInsertInfo(param);   
						
		}catch(Exception e){
			e.printStackTrace();
		}
		return rsMap;
	}	
	
	/**
	 * 나의건강 상세 활동량 목록들 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@RequestMapping( value="/actMaindash.do", method=RequestMethod.POST)	
	public @ResponseBody Map<String,Object> selectActMainDashList(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		Map<String,Object> rsMap_sb = new HashMap<String,Object>();
		String chkYn = "N";
		
		try{
			List<Map<String,String>> rsList1 = gnrlMyhealthService.selectActDtlsList(param);   
			List<Map<String,String>> rsList2 = gnrlMyhealthService.selectActTot(param);     
			List<Map<String,String>> rsList3 = gnrlMyhealthService.selectActChartList(param);
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
			rsList = gnrlMyhealthService.selectActDtlsStat2(param);
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
			rsList = gnrlMyhealthService.selectActDtlsStat3(param);
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
			rsList = gnrlMyhealthService.selectActDtlsChart2(param);
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
			rsList = gnrlMyhealthService.selectActDtlsChart3(param);
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("rsList", rsList);
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
		String chkYn = "N";
		
		try{
			rsList = gnrlMyhealthService.selectBodyCompChartList(param);
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("chartList", rsList);
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
			rsList = gnrlMyhealthService.selectBodycompList(param);
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
			rsList = gnrlMyhealthService.selectBodyCompDtlsChartList(param);
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
			rsList = gnrlMyhealthService.selectBodyCompDtlsList(param);
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
			rsList = gnrlMyhealthService.selectBloodSugarDtlsList(param);
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
			rsList = gnrlMyhealthService.selectBloodSugarDtlsChartList(param);
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
			rsList = gnrlMyhealthService.selectBloodSugarChartList(param);
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
			rsList = gnrlMyhealthService.selectBloodSugarList(param);
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
			rsList = gnrlMyhealthService.selectBloodPressList(param);
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
			rsList = gnrlMyhealthService.selectBloodPressChartList(param);
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
			rsList = gnrlMyhealthService.selectBloodPressDtlsList(param);
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
			rsList = gnrlMyhealthService.selectBloodPressDtlsChartList(param);
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
			rsList = gnrlMyhealthService.selectExeRecordList(param);
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
			rsCount = gnrlMyhealthService.exeRecordInsert(param);
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
			rsCount = gnrlMyhealthService.exeRecordUpdate(param);
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
			rsCount = gnrlMyhealthService.exeRecordDelete(param);
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("rsList", rsCount);
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
			rsList = gnrlMyhealthService.selectExcsList(param);
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
			List<Map<String,String>> eatList2 = gnrlMyhealthService.selectObjEatNeed(param);		//영양소별 기준 섭취량 대비 비율 정보
			List<Map<String,String>> eatList3 = gnrlMyhealthService.selectMealDiaryList(param); 	//식사일기 끼니 구분 별 정보
			List<Map<String,String>> eatList4 = gnrlMyhealthService.selectMealDiarySgCal(param);	//유지당류 및 주류 섭취 칼로리 정보
			List<Map<String,String>> weekList = gnrlMyhealthService.selectMealDiaryWeekInfo(param);	//날짜정보
			
			selMap.put("weekList", weekList);
			selMap.put("eatList2", eatList2);
			selMap.put("eatList3", eatList3);
			selMap.put("eatList4", eatList4);
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
			rsList = gnrlMyhealthService.selectMealDiaryDtlsList(param);
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
			rsList = gnrlMyhealthService.selectMealSearch(param);
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
			rsList = gnrlMyhealthService.selectFoodSearch(param);
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("rsList", rsList);
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}
	
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
			rsCnt = gnrlMyhealthService.mealDiaryInsert(param);
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
	 * 음식인식 결과 저장
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping( value="/foodRecognInsert.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> insertMealDiary2(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		int rsCnt = 0;
		String chkYn = "N";		
		try{
			rsCnt = gnrlMyhealthService.foodRecognInsert(param);
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
			 rsList = gnrlMyhealthService.selectActDtlsList(param);
			chkYn = "Y";
		}catch(Exception e){
			e.printStackTrace();
		}
		rsMap.put("rsList", rsList);
		rsMap.put("chkYn", chkYn);
		return rsMap;
	}
	
	@RequestMapping( value="/selectFoodRecogn.do", method = RequestMethod.GET)
	public Map<String, Object>  selectFoodRecogn(String param, HttpServletRequest req,String charset, String parameterInfo) throws Exception{		
		String filepath = req.getParameter("filepath");	
		Map<String, Object> map = null;
		String jsonInfo = null;		
		   try {
				URL url = new URL(("http://192.168.10.143:8000/ocr/?filepath=" + filepath));
				System.out.println(url);
				HttpURLConnection con = (HttpURLConnection) url.openConnection(); 
				con.setConnectTimeout(5000); //서버에 연결되는 Timeout 시간 설정
				con.setReadTimeout(5000); // InputStream 읽어 오는 Timeout 시간 설정
				con.setRequestMethod("GET");
				con.setRequestProperty("Accept", "application/json");
				con.setDoOutput(false);
				StringBuilder sb = new StringBuilder();
				if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
					  BufferedReader br = new BufferedReader( new
					  InputStreamReader(con.getInputStream(), "utf-8")); String line; while ((line
					  = br.readLine()) != null) { sb.append(line).append("\n");
					  sb.toString();
					  }					  
					  jsonInfo=sb.toString();					  					
				      map = new ObjectMapper().readValue(jsonInfo, Map.class);
				      map.put("filepath", filepath);				      
				} else {					
					System.out.println(con.getResponseMessage());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		return map;				  				   				
	}
	
	@RequestMapping( value="/foodRecognList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> regMngt(@ModelAttribute Map param, ModelMap model) throws Exception{		
		Map<String,Object> rsMap = new HashMap<String,Object>();		
				
		List<Map<String, String>> rsList = new ArrayList<Map<String,String>>();
		List<Map<String, String>> rsList2 = new ArrayList<Map<String,String>>();
		String chkYn = "N";
			
		try {			
			rsList = gnrlMyhealthService.selectMealSearch(param);			
			chkYn = "Y";
			rsMap.put("rsList", rsList);
			rsMap.put("chkYn", chkYn);					
			rsMap.put("FOOD_CD", rsList.get(0).get("FOOD_CD"));
			rsList2 = gnrlMyhealthService.selectFoodSearch(rsMap);			
			rsMap.put("rsList2", rsList2);			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		return rsMap;	
					
	}

	@RequestMapping(value="/lunchboxRecognList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> lunchboxRecognList(@ModelAttribute Map param, ModelMap model) throws Exception {
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String, String>> rsList = new ArrayList<Map<String,String>>();

		String chkYn = "N";
		try {
			rsList = gnrlMyhealthService.selectLunchbox(param);
			chkYn = "Y";

			rsMap.put("rsList", rsList);
			rsMap.put("chkYn", chkYn);
		} catch (Exception e){
			e.printStackTrace();
		}

		return rsMap;
	}
}
