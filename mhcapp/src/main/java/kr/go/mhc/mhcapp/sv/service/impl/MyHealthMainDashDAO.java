package kr.go.mhc.mhcapp.sv.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.go.mhc.common.DMultiEgovAbstractMapper;
import kr.go.mhc.common.util.StringUtil;

import org.springframework.stereotype.Repository;

/**
 * @Class Name : MyHealthMainDashDAO.java
 * @Description : 모바일 헬스케어 App에서 사용하는 나의건강-메인 대시 DataBase 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일				수정자			수정내용
 * @	----------		----		---------------------------
 * 		2016.07.01		오명빈				최초생성
 *
 * @author gst
 * @since 2016.07.01
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Repository("mhcapp.sv.MyHealthMainDashDAO")
public class MyHealthMainDashDAO extends DMultiEgovAbstractMapper{

	/**
	 * 활동량 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectActDtlsList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.selectActDtlsList", param);	
		return rsList;  
	}
	
	/**
	 * 활동량 총걸음수, 총거리, 칼로리, 운동시간 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectActTot(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.selectActTot", param);	
		return rsList;  
	}
	
	/**
	 * 활동량 차트 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectActChartList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.selectActChartList", param);	
		return rsList;  
	}
	
	/**
	 * 활동량 상세 1일 차트 데이터 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectActDtlsChart(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.selectActDtlsChart", param);	
		return rsList;  
	}
	/**
	 * 활동량 상세 1일 통계 데이터 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectActDtlsStat1(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.selectActDtlsStat1", param);	
		return rsList;  
	}
	/**
	 * 활동량 상세 1주일 통계 데이터 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectActDtlsStat2(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.selectActDtlsStat2", param);	
		return rsList;  
	}
	/**
	 * 활동량 상세 1,3개월 통계 데이터 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectActDtlsStat3(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.selectActDtlsStat3", param);	
		return rsList;  
	}
	
	/**
	 * 활동량 상세 1주일 차트 데이터 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectActDtlsChart2(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.selectActDtlsChart2", param);	
		return rsList;  
	}
	
	/**
	 * 활동량 상세 1,3개월 차트 데이터 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectActDtlsChart3(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.selectActDtlsChart3", param);	
		return rsList;  
	}
	
	/**
	 * 혈압 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectBloodPressList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.selectBloodPressList", param);	
		return rsList;  
	}
	
	/**
	 * 혈압 차트 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectBloodPressChartList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.selectBloodPressChartList", param);	
		return rsList;  
	}
	
	/**
	 * 혈압 상세 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectBloodPressDtlsList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.selectBloodPressDtlsList", param);	
		return rsList;  
	}
	
	/**
	 * 혈압 상세 차트 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectBloodPressDtlsChartList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.selectBloodPressDtlsChartList", param);	
		return rsList;  
	}
	/**
	 * 혈당 상세 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectBloodSugarDtlsList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.selectBloodSugarDtlsList", param);	
		return rsList;  
	}
	
	/**
	 * 혈당 상세 차트 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectBloodSugarDtlsChartList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.selectBloodSugarDtlsChartList", param);	
		return rsList;  
	}
	
	/**
	 * 혈당 차트 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectBloodSugarChartList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.selectBloodSugarChartList", param);	
		return rsList;  
	}
	/**
	 * 혈당 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectBloodSugarList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.selectBloodSugarList", param);	
		return rsList;  
	}
	
	/**
	 * 나의 건강 상세 체성분 차트 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectBodyCompChartList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.selectBodyCompChartList", param);	
		return rsList;  
	}
	
	/**
	 * 메인 체성분 차트 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectMainBodyCompChartList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.selectMainBodyCompChartList", param);	
		return rsList;  
	}
	
	/**
	 * 체성분 상세 차트 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectBodyCompDtlsChartList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.selectBodyCompDtlsChartList", param);	
		return rsList;  
	}
	
	/**
	 * 체성분 상세 각 데이터 차이값 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectBodyCompDtlsList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.selectBodyCompDtlsList", param);	
		return rsList;  
	}
	
	/**
	 * 체성분 리스트 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> selectBodycompList(Map<String, Object> param) throws Exception{
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.selectBodycompList", param);
		return rsList;
	}
	
	/**
	 * 운동기록 등록 데이터 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectExeRecordList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.selectExeRecordList", param);	
		return rsList;  
	}
	
	
	/**
	 * 운동기록 등록 
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	public int exeRecordInsert(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		int rsList = insert("mhcapp.sv.myhealth.exeRecordInsert", param);	
		return rsList;  
	}
	
	/**
	 * 운동기록 수정 
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	public int exeRecordUpdate(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		int rsList = update("mhcapp.sv.myhealth.exeRecordUpdate", param);	
		return rsList;  
	}
	
	/**
	 * 운동기록 삭제
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	public int exeRecordDelete(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		int rsList = delete("mhcapp.sv.myhealth.exeRecordDelete", param);	
		return rsList;  
	}
	
	/**
	 * 식사기록 등록 데이터 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectDietRecordList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.selectDietRecordList", param);	
		return rsList;  
	}
	
	//-------------------------------------------------------------------------
	
	
	/**
	 * 메인 대시 혈압 데이터 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectBloodPressData(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.selectBloodPressData", param);	
		return rsList;  
	}
	
	/**
	 * 메인 대시 혈당 데이터 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectBloodSugarData(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.selectBloodSugarData", param);	
		return rsList;  
	}
	
	/**
	 * 운동/식사 기록 데이터 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectExerDietData(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.selectExerDietData", param);	
		return rsList;  
	}
	
	/**
	 * 알림 도착 유무 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectNoticeChk(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.selectNoticeChk", param);	
		return rsList;  
	}
	
	/**
	 * 식사 구분 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> exeRecordClfCheck(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.exeRecordClfCheck", param);	
		return rsList;  
	}
	
	/**
	 * 식사기록 등록 
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	public int dietRecordInsert(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		int rsList = insert("mhcapp.sv.myhealth.dietRecordInsert", param);	
		return rsList;  
	}
	
	/**
	 * 식사 기록 수정 
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	public int dietRecordUpdate(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		int rsList = update("mhcapp.sv.myhealth.dietRecordUpdate", param);	
		return rsList;  
	}
	
	/**
	 * 식사 상담 추가 
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	public int dietCnslUpdate(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		int rsList = update("mhcapp.sv.myhealth.dietCnslUpdate", param);	
		return rsList;  
	}
	
	/**
	 * 식사 상세 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectMealDtls(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.selectMealDtls", param);	
		return rsList;  
	}
	
	/**
	 * 식사 SN 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public int selectMealRegSn(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		int rsList = selectOne("mhcapp.sv.myhealth.selectMealRegSn", param);	
		return rsList;  
	}
	
	/**
	 * 식사 상세 추가 
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	public int mealDtlsInsert(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		int rsList = insert("mhcapp.sv.myhealth.mealDtlsInsert", param);	
		return rsList;  
	}
	
	/**
	 * 식사 기록 삭제
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	public int mealAllDelete(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		int rsList = delete("mhcapp.sv.myhealth.mealAllDelete", param);	
		int rsInt = selectOne("mhcapp.sv.myhealth.selectDietDtlsCheck", param);
		if(rsInt == 0 && param.get("All_DEl_YN").equals("Y")){
			delete("mhcapp.sv.myhealth.msMealDelete", param);
		}
		return rsList;  
	}
	
	/**
	 * 식사 마스터 유무 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> dietMasterCheck(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.selectDietMasterCheck", param);	
		return rsList;  
	}
	
	/**
	 * 미션 측정 횟수 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectMeasrCntList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.selectMeasrCntList", param);	
		return rsList;  
	}
	
	/**
	 * 활동량 주간 미션 측정 정보 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectMeasrActList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.selectMeasrActList", param);	
		return rsList;  
	}
	
	/**
	 * 주차별 컨텐츠 상세정보
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectContentsDtls(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.selectContentsDtls", param);	
		return rsList;  
	}
	
	/**
	 * 주차별 컨텐츠 확인여부
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectContentsCnfm(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String, String>> rsList = selectList("mhcapp.sv.myhealth.selectContentsCnfm", param);	
		return rsList;  
	}
	
	/**
	 * 선물받기 체크
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectGiftChk(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.selectGiftChk", param);	
		return rsList;  
	}
	
	/**
	 * 식사 상담기간 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectDietPeriod(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.selectDietPeriod", param);	
		return rsList;  
	}
	
	/**
	 * 집중상담 알림내역 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectNoticeListChk(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.selectNoticeListChk", param);	
		return rsList;  
	}
	
	/**
	 * 집중상담 기간체크 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectDietPrdChk(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.selectDietPrdChk", param);	
		return rsList;  
	}

	/**
	 * 모바일 공지사항 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> selectMobileNotice(Map<String, Object> param) throws Exception{
		List<Map<String, String>> rsList= selectList("mhcapp.sv.myhealth.selectMobileNotice", param);
		return rsList;
	}

	/**
	 * 운동 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectExcsList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.selectExcsList", param);	
		return rsList;  
	}
	
	/**
	 * 식사 일기 메인 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> selectMealDiaryList(Map<String, Object> param) throws Exception{
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.selectMealDiaryList", param);
		return rsList;
	}
	
	/**
	 * 식사 일기 주차 및 날짜 정보
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> selectMealDiaryWeekInfo(Map<String, Object> param) throws Exception{
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.selectMealDiaryWeekInfo", param);
		return rsList;
	}
	
	/**
	 * 섭취군별 기준 섭취량 대비 비율 정보
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> selectObjEatDay(Map<String,Object> param) throws Exception{
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.selectObjEatDay", param);
		return rsList;
	}
	
	/**
	 * 영양소별 기준 섭취량 대비 비율 정보
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> selectObjEatNeed(Map<String, Object> param) throws Exception{
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.selectObjEatNeed", param);
		return rsList;
	}
	
	/**
	 * 식사 일기 상세_유지당류 및 주류 섭취 칼로리 정보
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> selectMealDiarySgCal(Map<String, Object> param) throws Exception{
		List<Map<String, String>> rsList = selectList("mhcapp.sv.myhealth.selectMealDiarySgCal", param);
		return rsList;
	}
	
	/**
	 * 식사 일기 상세 정보 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> selectMealDiaryDtlsList(Map<String, Object> param) throws Exception{
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.selectMealDiaryDtlsList", param);
		return rsList;
	}
	
	/**
	 * 음식 검색어 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> selectMealSearch(Map<String, Object> param) throws Exception{
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.selectMealSearch", param);
		return rsList;
	}
	
	/**
	 * 음식 칼로리 정보 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> selectFoodSearch(Map<String, Object> param) throws Exception{
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.selectFoodSearch", param);
		return rsList;
	}
	
	/**
	 * 실천_미션_조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,String>> selectPractMission(Map<String, Object> param) throws Exception{
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.selectPractMission", param);
		return rsList;
	}
	
	/**
	 * 식사_일기 저장
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public void insertMealDiary(Map<String, Object> param) throws Exception{
		insert("mhcapp.sv.myhealth.insertMealDiary", param);
	}
	
	/**
	 * 식사_일기_상세 저장
	 * @param param
	 * @throws Exception
	 */
	public void insertMealDiaryDtls(Map<String, Object> param) throws Exception{
		insert("mhcapp.sv.myhealth.insertMealDiaryDtls", param);
	}
	
	/**
	 * 식사_일기_상세 저장 리스트 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> selectInsertDtls(Map<String, Object> param) throws Exception{
		List<Map<String,Object>> rsList = selectList("mhcapp.sv.myhealth.selectInsertDtls", param);
		return rsList; 
	}

	/**
	 * 식사_일기 삭제
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public void deleteMealDiary(Map<String, Object> param) throws Exception{
		delete("mhcapp.sv.myhealth.deleteMealDiary", param);
	}

	/**
	 * 식사_일기_상세 삭제
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public void deleteMealDiaryDtls(Map<String, Object> param) throws Exception{
		delete("mhcapp.sv.myhealth.deleteMealDiaryDtls", param);
	}

	
	/**
	 * 적정 탄단지 비율 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> selectStndRecomPer(Map<String, Object> param) throws Exception{
		Map<String,Object> rsMap = selectOne("mhcapp.sv.myhealth.selectStndRecomPer", param);
		return rsMap;
	}


	/**
	 * 실천 미션 스케쥴 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectPractMissionSch(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.selectPractMissionSch", param);	
		return rsList;  
	}
	
	/**
	 * 실천 미션 답변 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectPractMissionAnswr(Map<String, String> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.selectPractMissionAnswr", param);	
		return rsList;  
	}
	
	/**
	 * 실천 미션 첨부파일 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectPractMissionAttchFile(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.selectPractMissionAttchFile", param);	
		return rsList;  
	}
	
	/**
	 * 실천 미션 답변 저장
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public int savePractMissionAnswr(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return update("mhcapp.sv.myhealth.savePractMissionAnswr", param);
	}
	
	/**
	 * 대시보드 심박수 차트 데이터 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public Map<String,Object> selectMainHeartRateChartList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> rsMap = new HashMap<String,Object>();
		List<Map<String,String>> charList = new ArrayList<Map<String,String>>();
		charList = selectList("mhcapp.sv.myhealth.selectMainHeartRateChartList", param);
		if(charList.size() <= 0){
			List<Map<String,String>> dataList = selectList("mhcapp.sv.myhealth.selectMainHeartRateDataList", param);
			rsMap.put("dataList", dataList);
			charList = selectList("mhcapp.sv.myhealth.selectMainHeartRateChartListArrBefore", param);
			rsMap.put("ArrClf", "NoArr");
		}else{
			rsMap.put("ArrClf", "Arr");
		}
		rsMap.put("returnVal", charList);
		
		return rsMap;  
	}
	
	/**
	 * 심박수 그래프 상세 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> heartRateDtlsChart(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = new ArrayList<Map<String,String>>();
		if(param.get("MONTH") == null){
			rsList = selectList("mhcapp.sv.myhealth.heartRateDtlsChartWeek", param);	
		}else{
			rsList = selectList("mhcapp.sv.myhealth.heartRateDtlsChartMonth", param);	
		}
		return rsList;  
	}
	
	/**
	 * 운동모드 마지막 데이터  조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectExcsModeLastData(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.selectExcsModeLastData", param);	
		return rsList;  
	}
	
	/**
	 * 심박 강도별 범위 및 데이터 개수 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selectHeartRateStats(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> rsMap = new HashMap<String, Object>(); 
			rsMap = selectOne("mhcapp.sv.myhealth.selectHeartRateStats", param);
			if(StringUtil.nvl(rsMap).equals("")){
				rsMap = selectOne("mhcapp.sv.myhealth.selectHeartRateStatsArrBefore", param);
			}
		
		return rsMap;
	}

	/**
	 * 체성분 상담 값 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> selectBodyCompCnslList(Map param) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.selectBodyCompCnslList", param);	
		return rsList;  
	}
	
	/**
	 * 운동스케줄 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> exerSchList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.exerSchList", param);	
		return rsList;  
	}
	
	
	/**
	 * 운동스케줄 저장
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	public int exerSchInsert(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		int rsList = insert("mhcapp.sv.myhealth.exerSchInsert", param);	
		return rsList;  
	}
	
	/**
	 * 운동스케줄 수정
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	public int exeSchUpdate(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		int rsList = update("mhcapp.sv.myhealth.exeSchUpdate", param);	
		return rsList;  
	}
	
	/**
	 * 운동스케줄 삭제
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	public int exeSchDelete(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		int rsList = delete("mhcapp.sv.myhealth.exeSchDelete", param);	
		return rsList;  
	}

	/**
	 * 복약 미션 답변 저장
	 * @param param
	 * @return
	 */
	public int saveDrugMissionAnswr(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return update("mhcapp.sv.myhealth.saveDrugMissionAnswr", param);
	}

	/**
	 * 복약 미션 리스트 조회
	 * @param param
	 * @return
	 */
	public List<Map<String, String>> selectDrugList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.sv.myhealth.selectDrugList", param);	
		return rsList;  
	}
	/**
	 * 식사_일기_직접입력_저장
	 * @param param
	 * @throws Exception
	 */
	public void insertMealDiaryManual(Map<String, Object> param) throws Exception{
		insert("mhcapp.sv.myhealth.insertMealDiaryManual", param);
	}
	/**
	 * 식사_일기_직접입력_삭제
	 * @param param
	 * @throws Exception
	 */
	public void deleteMealDiaryManual(Map<String, Object> param) throws Exception{
		delete("mhcapp.sv.myhealth.deleteMealDiaryManual", param);
	}
}
