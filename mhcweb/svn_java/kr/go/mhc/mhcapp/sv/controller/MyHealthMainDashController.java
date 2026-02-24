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
	@RequestMapping( value="/actMaindash.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectActMainDashList(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		Map<String,Object> rsMap_sb = new HashMap<String,Object>();
		
		List<Map<String,String>> rsList1 = myhealthService.selectActDtlsList(param);   
		List<Map<String,String>> rsList2 = myhealthService.selectActTot(param);     
		List<Map<String,String>> rsList3 = myhealthService.selectActChartList(param);
		rsMap_sb.put("actlist", rsList1); //활동량 리스트
		rsMap_sb.put("acttot", rsList2); //활동량 총걸음수, 총거리, 칼로리, 운동시간
		rsMap_sb.put("actchart", rsList3); //활동량 차트
		
		rsMap.put("rsList", rsMap_sb);
		
		return rsMap;
	}	
	
	/**
	 * 나의건강 상세 활동량 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	@RequestMapping( value="/selectActList.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectActList(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		
		List<Map<String,String>> rsList = myhealthService.selectActDtlsList(param);   
		
		rsMap.put("rsList", rsList);
		
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
		
		List<Map<String,String>> rsList = myhealthService.selectActDtlsChart(param);   
		
		rsMap.put("rsList", rsList);
		if(rsList.size()==0){
			//rsMap.put("msg", getMsg("common.list.null"));
		}else{
			//rsMap.put("msg", getMsg("common.list.succ"));
		}
		
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
		
		List<Map<String,String>> rsList = myhealthService.selectActDtlsStat1(param);   
		
		rsMap.put("rsList", rsList);
		if(rsList.size()==0){
			//rsMap.put("msg", getMsg("common.list.null"));
		}else{
			//rsMap.put("msg", getMsg("common.list.succ"));
		}
		
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
		
		List<Map<String,String>> rsList = myhealthService.selectActDtlsStat2(param);   
		
		rsMap.put("rsList", rsList);
		if(rsList.size()==0){
			//rsMap.put("msg", getMsg("common.list.null"));
		}else{
			//rsMap.put("msg", getMsg("common.list.succ"));
		}
		
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
		
		List<Map<String,String>> rsList = myhealthService.selectActDtlsStat3(param);   
		
		rsMap.put("rsList", rsList);
		if(rsList.size()==0){
			//rsMap.put("msg", getMsg("common.list.null"));
		}else{
			//rsMap.put("msg", getMsg("common.list.succ"));
		}
		
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
		
		List<Map<String,String>> rsList = myhealthService.selectActDtlsChart2(param);   
		
		rsMap.put("rsList", rsList);
		if(rsList.size()==0){
			//rsMap.put("msg", getMsg("common.list.null"));
		}else{
			//rsMap.put("msg", getMsg("common.list.succ"));
		}
		
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
		
		List<Map<String,String>> rsList = myhealthService.selectActDtlsChart3(param);   
		
		rsMap.put("rsList", rsList);
		if(rsList.size()==0){
			//rsMap.put("msg", getMsg("common.list.null"));
		}else{
			//rsMap.put("msg", getMsg("common.list.succ"));
		}
		
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
		
		List<Map<String,String>> rsList = myhealthService.selectBloodPressList(param);   
		rsMap.put("rsList", rsList);
		if(rsList.size()==0){
			//rsMap.put("msg", getMsg("common.list.null"));
		}else{
			//rsMap.put("msg", getMsg("common.list.succ"));
		}
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
		
		List<Map<String,String>> rsList = myhealthService.selectBloodPressChartList(param);   
		rsMap.put("rsList", rsList);
		if(rsList.size()==0){
			//rsMap.put("msg", getMsg("common.list.null"));
		}else{
			//rsMap.put("msg", getMsg("common.list.succ"));
		}
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
		
		List<Map<String,String>> rsList = myhealthService.selectBloodPressDtlsList(param);   
		rsMap.put("rsList", rsList);
		if(rsList.size()==0){
			//rsMap.put("msg", getMsg("common.list.null"));
		}else{
			//rsMap.put("msg", getMsg("common.list.succ"));
		}
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
		
		List<Map<String,String>> rsList = myhealthService.selectBloodPressDtlsChartList(param);   
		rsMap.put("rsList", rsList);
		if(rsList.size()==0){
			//rsMap.put("msg", getMsg("common.list.null"));
		}else{
			//rsMap.put("msg", getMsg("common.list.succ"));
		}
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
		
		List<Map<String,String>> rsList = myhealthService.selectBloodSugarDtlsList(param);   
		rsMap.put("rsList", rsList);
		if(rsList.size()==0){
			//rsMap.put("msg", getMsg("common.list.null"));
		}else{
			//rsMap.put("msg", getMsg("common.list.succ"));
		}
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
		
		List<Map<String,String>> rsList = myhealthService.selectBloodSugarDtlsChartList(param);   
		rsMap.put("rsList", rsList);
		if(rsList.size()==0){
			//rsMap.put("msg", getMsg("common.list.null"));
		}else{
			//rsMap.put("msg", getMsg("common.list.succ"));
		}
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
		
		List<Map<String,String>> rsList = myhealthService.selectBloodSugarChartList(param);   
		rsMap.put("rsList", rsList);
		if(rsList.size()==0){
			//rsMap.put("msg", getMsg("common.list.null"));
		}else{
			//rsMap.put("msg", getMsg("common.list.succ"));
		}
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
		
		List<Map<String,String>> rsList = myhealthService.selectBloodSugarList(param);   
		rsMap.put("rsList", rsList);
		if(rsList.size()==0){
			//rsMap.put("msg", getMsg("common.list.null"));
		}else{
			//rsMap.put("msg", getMsg("common.list.succ"));
		}
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
		
		List<Map<String,String>> rsList = myhealthService.selectBodyCompChartList(param);   
		rsMap.put("rsList", rsList);
		if(rsList.size()==0){
			//rsMap.put("msg", getMsg("common.list.null"));
		}else{
			//rsMap.put("msg", getMsg("common.list.succ"));
		}
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
		
		List<Map<String,String>> rsList = myhealthService.selectBodyCompDtlsChartList(param);   
		rsMap.put("rsList", rsList);
		if(rsList.size()==0){
			//rsMap.put("msg", getMsg("common.list.null"));
		}else{
			//rsMap.put("msg", getMsg("common.list.succ"));
		}
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
		
		List<Map<String,String>> rsList = myhealthService.selectBodyCompDtlsList(param);   
		rsMap.put("rsList", rsList);
		if(rsList.size()==0){
			//rsMap.put("msg", getMsg("common.list.null"));
		}else{
			//rsMap.put("msg", getMsg("common.list.succ"));
		}
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
		
		List<Map<String,String>> rsList = myhealthService.selectExeRecordList(param);   
		rsMap.put("rsList", rsList);
		if(rsList.size()==0){
			//rsMap.put("msg", getMsg("common.list.null"));
		}else{
			//rsMap.put("msg", getMsg("common.list.succ"));
		}
		return rsMap;
	}	
	
	/**
	 * 운동기록 저장
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/exeRecordInsert.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> exeRecordInsert(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		int rsCount = myhealthService.exeRecordInsert(param);   
		rsMap.put("rsList", rsCount);
		/*if(rsCount>0){
			rsMap.put("msg", getMsg("common.write.succ"));
		}else{
			rsMap.put("msg", getMsg("common.write.err"));
		}*/
		return rsMap;
	}	
	
	/**
	 * 운동기록 수정
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/exeRecordUpdate.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> exeRecordUpdate(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		
		int rsCount = myhealthService.exeRecordUpdate(param);   
		rsMap.put("rsList", rsCount);
		/*if(rsCount>0){
			rsMap.put("msg", getMsg("common.edit.succ"));
		}else{
			rsMap.put("msg", getMsg("common.edit.err"));
		}*/
		return rsMap;
	}	
	
	/**
	 * 운동기록 삭제
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/exeRecordDelete.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> exeRecordDelete(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		
		int rsCount = myhealthService.exeRecordDelete(param); 
		rsMap.put("rsList", rsCount);
		/*if(rsCount>0){
			rsMap.put("msg", getMsg("common.delete.succ"));
		}else{
			rsMap.put("msg", getMsg("common.delete.err"));
		}*/
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
		
		List<Map<String,String>> rsList = myhealthService.selectDietRecordList(param);   
		rsMap.put("rsList", rsList);
		if(rsList.size()==0){
			//rsMap.put("msg", getMsg("common.list.null"));
		}else{
			//rsMap.put("msg", getMsg("common.list.succ"));
		}
		return rsMap;
	}	
	
	/**
	 * 식사 구분 조회
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/exeRecordClfCheck.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> exeRecordClfCheck(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		
		List<Map<String,String>> rsList = myhealthService.exeRecordClfCheck(param);   
		rsMap.put("rsList", rsList);
		if(rsList.size()==0){
			//rsMap.put("msg", getMsg("common.list.null"));
		}else{
			//rsMap.put("msg", getMsg("common.list.succ"));
		}
		return rsMap;
	}	
	
	
	/**
	 * 식사기록 저장
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/dietRecordInsert.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> dietRecordInsert(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		
		int rsList = myhealthService.dietRecordInsert(param);   
		rsMap.put("rsList", rsList);
		if(rsList>0){
			//rsMap.put("msg", getMsg("common.write.succ"));
		}else{
			//rsMap.put("msg", getMsg("common.write.err"));
		}
		return rsMap;
	}	
	
	/**
	 * 식사기록 수정
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/dietRecordUpdate.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> dietRecordUpdate(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		
		int rsCount = myhealthService.dietRecordUpdate(param);   
		if(rsCount>0){
			//rsMap.put("msg", getMsg("common.edit.succ"));
		}else{
			//rsMap.put("msg", getMsg("common.edit.err"));
		}
		return rsMap;
	}	
	
	/**
	 * 식사상담 추가
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/dietCnslUpdate.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> dietCnslUpdate(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		
		int rsCount = myhealthService.dietCnslUpdate(param);   
		if(rsCount>0){
			//rsMap.put("msg", getMsg("common.edit.succ"));
		}else{
			//rsMap.put("msg", getMsg("common.edit.err"));
		}
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
		if(rsList.size()==0){
			//rsMap.put("msg", getMsg("common.list.null"));
		}else{
			//rsMap.put("msg", getMsg("common.list.succ"));
		}
		return rsMap;
	}	
	
	/**
	 * 식사 SN 조회
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/getMealRegSn.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectMealRegSn(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		
		List<Map<String,String>> rsList = myhealthService.selectMealRegSn(param);   
		rsMap.put("rsList", rsList);
		if(rsList.size()==0){
			//rsMap.put("msg", getMsg("common.list.null"));
		}else{
			//rsMap.put("msg", getMsg("common.list.succ"));
		}
		return rsMap;
	}	
	
	/**
	 * 식사 상세 추가
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/mealDtlsInsert.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> mealDtlsInsert(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		
		List<Map<String,String>> rsList = myhealthService.dietMasterCheck(param);  
		
		if(rsList.size()<1){
			myhealthService.dietRecordInsert(param);  
		}
		
		int rsCount = myhealthService.mealDtlsInsert(param);
		rsMap.put("rsList", rsCount);
		if(rsCount>0){
			//rsMap.put("msg", getMsg("common.edit.succ"));
		}else{
			//rsMap.put("msg", getMsg("common.edit.err"));
		}
		return rsMap;
	}	
	
	/**
	 * 식사 기록 삭제
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/mealAllDelete.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> mealAllDelete(@ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		
		int rsCount = myhealthService.mealAllDelete(param);
		rsMap.put("rsList", rsCount);
		if(rsCount>0){
			//rsMap.put("msg", getMsg("common.edit.succ"));
		}else{
			//rsMap.put("msg", getMsg("common.edit.err"));
		}
		return rsMap;
	}	
	
	//------------------------------------------------------------------------------------------------
	/**
	 * 메인 화면 호출
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/myhealth.do", method = RequestMethod.GET)
	public String main(@ModelAttribute Map param, ModelMap model) throws Exception{
		
		return "app/sv/myhealth";
	}	
	
	
	/**
	 * 메인 사용자별 데이터 조회
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping( value="/mainUserData.do", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> selectMainUserData(HttpServletRequest req, @ModelAttribute Map param, ModelMap model) throws Exception{
		Map<String,Object> rsMap = new HashMap<String,Object>();
		Map<String,Object> rsMap_sub = new HashMap<String,Object>();
		HttpSession session=req.getSession();
		
		List<Map<String,String>> rsList1 = myhealthService.selectMainBodyCompChartList(param);   
		List<Map<String,String>> rsList2 = myhealthService.selectActDtlsStat1(param); 
		List<Map<String,String>> rsList5 = myhealthService.selectExerDietData(param);
		List<Map<String,String>> rsList6 = myhealthService.selectNoticeChk(param); //알림도착체크
		List<Map<String,String>> rsList7 = myhealthService.selectMeasrCntList(param); //미션 측정 횟수 조회
		List<Map<String,String>> rsList8 = myhealthService.selectActTot(param); //미션 주건 칼로리, 운동시간 체크    
		String CNFM_YN = myhealthService.selectContentsCnfm(param); //주차별 컨텐츠 확인
		List<Map<String,String>> rsList9 = new ArrayList<Map<String,String>>();
		
		//컨텐츠
		if("Y".equals(CNFM_YN)){ //확인
			
		}else if("N".equals(CNFM_YN)){ //미확인
			rsList9 = myhealthService.selectContentsDtls(param);
		}else{ //주차별 컨텐츠 없음(null)
			
		}
		
		rsMap_sub.put("bodycomp", rsList1);
		rsMap_sub.put("act", rsList2);
		rsMap_sub.put("exerdiet", rsList5);
		rsMap_sub.put("noticechk", rsList6);
		rsMap_sub.put("measrcnt", rsList7);
		rsMap_sub.put("kcaltime", rsList8);
		rsMap_sub.put("contents", rsList9);
		
		if("Y".equals(session.getAttribute("SESS_BLOOD_PRESS_PYMNT_YN"))){
			List<Map<String,String>> rsList3 = myhealthService.selectBloodPressData(param);  
			rsMap_sub.put("bloodpress", rsList3);
		}
		if("Y".equals(session.getAttribute("SESS_BLOOD_SUGAR_PYMNT_YN"))){
			List<Map<String,String>> rsList4 = myhealthService.selectBloodSugarData(param);
			rsMap_sub.put("bloodsugar", rsList4);
		}
		
		rsMap.put("rsList", rsMap_sub);
		return rsMap;
	}	
	
}
