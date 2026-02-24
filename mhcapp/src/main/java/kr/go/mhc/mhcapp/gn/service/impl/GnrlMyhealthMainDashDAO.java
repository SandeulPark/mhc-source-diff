package kr.go.mhc.mhcapp.gn.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.go.mhc.common.DMultiEgovAbstractMapper;

/**
 * @Class Name : GnrlMyhealthMainDashDAO.java
 * @Description : 모바일 헬스케어 App에서 사용하는 나의건강-메인 대시 DataBase 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2019.08.13		이태석			최초생성
 * 		
 * @author thejoin
 * @since 2019.08.13
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Repository("mhcapp.gn.GnrlMyhealthMainDashDAO")
public class GnrlMyhealthMainDashDAO extends DMultiEgovAbstractMapper{
	
	/**
	 * 시간별 활동량 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectDayActCnt(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.gn.myhealth.selectDayActCnt", param);	
		return rsList;  
	}
	
	/**
	 * 입력 정보 조회 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public Map<String, Object> selectInsertInfo(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> rsMap = selectOne("mhcapp.gn.myhealth.selectInsertInfo", param);	
		return rsMap;  
	}
	
	/**
	 * 활동량 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectActDtlsList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhcapp.gn.myhealth.selectActDtlsList", param);	
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
		List<Map<String,String>> rsList = selectList("mhcapp.gn.myhealth.selectActTot", param);	
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
		List<Map<String,String>> rsList = selectList("mhcapp.gn.myhealth.selectActChartList", param);	
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
		List<Map<String,String>> rsList = selectList("mhcapp.gn.myhealth.selectActDtlsStat2", param);	
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
		List<Map<String,String>> rsList = selectList("mhcapp.gn.myhealth.selectActDtlsStat3", param);	
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
		List<Map<String,String>> rsList = selectList("mhcapp.gn.myhealth.selectActDtlsChart2", param);	
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
		List<Map<String,String>> rsList = selectList("mhcapp.gn.myhealth.selectActDtlsChart3", param);	
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
		List<Map<String,String>> rsList = selectList("mhcapp.gn.myhealth.selectBodyCompChartList", param);	
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
		List<Map<String,String>> rsList = selectList("mhcapp.gn.myhealth.selectBodyCompDtlsChartList", param);	
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
		List<Map<String,String>> rsList = selectList("mhcapp.gn.myhealth.selectBodyCompDtlsList", param);	
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
		List<Map<String,String>> rsList = selectList("mhcapp.gn.myhealth.selectBodycompList", param);
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
		List<Map<String,String>> rsList = selectList("mhcapp.gn.myhealth.selectBloodSugarDtlsList", param);	
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
		List<Map<String,String>> rsList = selectList("mhcapp.gn.myhealth.selectBloodSugarDtlsChartList", param);	
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
		List<Map<String,String>> rsList = selectList("mhcapp.gn.myhealth.selectBloodSugarChartList", param);	
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
		List<Map<String,String>> rsList = selectList("mhcapp.gn.myhealth.selectBloodSugarList", param);	
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
		List<Map<String,String>> rsList = selectList("mhcapp.gn.myhealth.selectBloodPressList", param);	
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
		List<Map<String,String>> rsList = selectList("mhcapp.gn.myhealth.selectBloodPressChartList", param);	
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
		List<Map<String,String>> rsList = selectList("mhcapp.gn.myhealth.selectBloodPressDtlsList", param);	
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
		List<Map<String,String>> rsList = selectList("mhcapp.gn.myhealth.selectBloodPressDtlsChartList", param);	
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
		List<Map<String,String>> rsList = selectList("mhcapp.gn.myhealth.selectExeRecordList", param);	
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
		int rsList = insert("mhcapp.gn.myhealth.exeRecordInsert", param);	
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
		int rsList = update("mhcapp.gn.myhealth.exeRecordUpdate", param);	
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
		int rsList = delete("mhcapp.gn.myhealth.exeRecordDelete", param);	
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
		List<Map<String,String>> rsList = selectList("mhcapp.gn.myhealth.selectExcsList", param);	
		return rsList;  
	}
	
	/**
	 * 영양소별 기준 섭취량 대비 비율 정보
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> selectObjEatNeed(Map<String, Object> param) throws Exception{
		List<Map<String,String>> rsList = selectList("mhcapp.gn.myhealth.selectObjEatNeed", param);
		return rsList;
	}
	
	/**
	 * 식사 일기 메인 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> selectMealDiaryList(Map<String, Object> param) throws Exception{
		List<Map<String,String>> rsList = selectList("mhcapp.gn.myhealth.selectMealDiaryList", param);
		return rsList;
	}
	
	/**
	 * 식사 일기 상세_유지당류 및 주류 섭취 칼로리 정보
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> selectMealDiarySgCal(Map<String, Object> param) throws Exception{
		List<Map<String, String>> rsList = selectList("mhcapp.gn.myhealth.selectMealDiarySgCal", param);
		return rsList;
	}
	
	/**
	 * 식사 일기 주차 및 날짜 정보
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> selectMealDiaryWeekInfo(Map<String, Object> param) throws Exception{
		List<Map<String,String>> rsList = selectList("mhcapp.gn.myhealth.selectMealDiaryWeekInfo", param);
		return rsList;
	}
	
	/**
	 * 식사 일기 상세 정보 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> selectMealDiaryDtlsList(Map<String, Object> param) throws Exception{
		List<Map<String,String>> rsList = selectList("mhcapp.gn.myhealth.selectMealDiaryDtlsList", param);
		return rsList;
	}
	
	/**
	 * 음식 검색어 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> selectMealSearch(Map<String, Object> param) throws Exception{
		List<Map<String,String>> rsList = new ArrayList<Map<String,String>>();

		if ("LUNCH_BOX".equals(param.get("LUNCH_BOX"))){
			rsList = selectList("mhcapp.gn.myhealth.selectLunchBoxMealSearch", param);
		} else {
			rsList = selectList("mhcapp.gn.myhealth.selectMealSearch", param);
		}

		return rsList;
	}
	
	/**
	 * 음식 칼로리 정보 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> selectFoodSearch(Map<String, Object> param) throws Exception{
		List<Map<String,String>> rsList = selectList("mhcapp.gn.myhealth.selectFoodSearch", param);
		return rsList;
	}
	
	/**
	 * 식사_일기_상세 저장 리스트 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> selectInsertDtls(Map<String, Object> param) throws Exception{
		List<Map<String,Object>> rsList = selectList("mhcapp.gn.myhealth.selectInsertDtls", param);
		return rsList; 
	}
	
	/**
	 * 식사_일기 저장
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public void insertMealDiary(Map<String, Object> param) throws Exception{
		insert("mhcapp.gn.myhealth.insertMealDiary", param);
	}
	
	/**
	 * 식사_일기_상세 저장
	 * @param param
	 * @throws Exception
	 */
	public void insertMealDiaryDtls(Map<String, Object> param) throws Exception{
		insert("mhcapp.gn.myhealth.insertMealDiaryDtls", param);
	}
	
	/**
	 * 식사_일기_상세 삭제
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public void deleteMealDiaryDtls(Map<String, Object> param) throws Exception{
		delete("mhcapp.gn.myhealth.deleteMealDiaryDtls", param);
	}
	
	/**
	 * 식사_일기 삭제
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public void deleteMealDiary(Map<String, Object> param) throws Exception{
		delete("mhcapp.gn.myhealth.deleteMealDiary", param);
	}

	public List<Map<String, String>> selectLunchbox(Map<String, Object> param) throws Exception{
		List<Map<String,String>> rsList = selectList("mhcapp.gn.myhealth.selectLunchbox", param);
		return rsList;
	}

}
