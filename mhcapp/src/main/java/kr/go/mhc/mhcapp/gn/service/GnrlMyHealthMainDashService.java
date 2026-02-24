package kr.go.mhc.mhcapp.gn.service;

import java.util.List;
import java.util.Map;

/**
 * @Class Name : GnrlMyHealthMainDashService.java
 * @Description : 보편건강 App에서 사용하는 나의건강-메인 대시 서비스 Class
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

public interface GnrlMyHealthMainDashService {
	
	/**
	 * 시간별 활동량 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectDayActCnt(Map<String, Object> param) throws Exception;
	
	/**
	 * 입력 정보 조회 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public Map<String, Object> selectInsertInfo(Map<String, Object> param) throws Exception;
	
	/**
	 * 활동량 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectActDtlsList(Map<String, Object> param) throws Exception;
	
	/**
	 * 활동량  총걸음수, 총거리, 칼로리, 운동시간 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectActTot(Map<String, Object> param) throws Exception;
	
	/**
	 * 활동량 차트 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectActChartList(Map<String, Object> param) throws Exception;
	
	/**
	 * 활동량 상세 통계 1주일 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectActDtlsStat2(Map<String, Object> param) throws Exception;
	
	/**
	 * 활동량 상세 통계 1,3개월 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectActDtlsStat3(Map<String, Object> param) throws Exception;
	
	/**
	 * 활동량 상세 차트 1주일 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectActDtlsChart2(Map<String, Object> param) throws Exception;
	
	/**
	 * 활동량 상세 차트 1,3개월 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectActDtlsChart3(Map<String, Object> param) throws Exception;
	
	/**
	 * 나의건강상세 체성분 차트 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectBodyCompChartList(Map param) throws Exception;
	
	/**
	 * 체성분 상세 차트 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectBodyCompDtlsChartList(Map param) throws Exception;
	
	/**
	 * 체성분 상세 각 데이터 차이값 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectBodyCompDtlsList(Map param) throws Exception;
	
	/**
	 * 체성분 리스트 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> selectBodycompList(Map param) throws Exception;
	
	/**
	 * 혈당 상세 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectBloodSugarDtlsList(Map param) throws Exception;
	
	/**
	 * 혈당 상세 차트 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectBloodSugarDtlsChartList(Map param) throws Exception;
	
	/**
	 * 혈당 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectBloodSugarList(Map param) throws Exception;
	
	/**
	 * 혈당 차트 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectBloodSugarChartList(Map param) throws Exception;
	
	/**
	 * 혈압 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectBloodPressList(Map param) throws Exception;
	
	/**
	 * 혈압 차트 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectBloodPressChartList(Map param) throws Exception;
	
	/**
	 * 혈압 상세 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectBloodPressDtlsList(Map param) throws Exception;
	
	/**
	 * 혈압 상세 차트 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectBloodPressDtlsChartList(Map param) throws Exception;
	
	/**
	 * 운동기록 등록 데이터 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectExeRecordList(Map param) throws Exception;
	
	
	/**
	 * 운동기록 등록
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public int exeRecordInsert(Map param) throws Exception;
	
	/**
	 * 운동기록 수정
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public int exeRecordUpdate(Map param) throws Exception;
	
	/**
	 * 운동기록 삭제
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public int exeRecordDelete(Map param) throws Exception;
	
	/**
	 * 운동 목록 조회
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectExcsList(Map param) throws Exception;
	
	/**
	 * 영양소별 기준 섭취량 대비 비율 정보
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> selectObjEatNeed(Map param) throws Exception;
	
	/**
	 * 식사 일기 메인 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> selectMealDiaryList(Map param) throws Exception;
	
	/**
	 * 식사 일기 상세_유지당류 및 주류 섭취 칼로리 정보
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> selectMealDiarySgCal(Map param) throws Exception;
	
	/**
	 * 식사 일기 주차 및 날짜 정보
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> selectMealDiaryWeekInfo(Map param) throws Exception;
	
	/**
	 * 식사 일기 상세 정보 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> selectMealDiaryDtlsList(Map param) throws Exception;
	
	/**
	 * 음식 검색어 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> selectMealSearch(Map param) throws Exception;
	
	/**
	 * 음식 칼로리 정보 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> selectFoodSearch(Map param) throws Exception;
	
	/**
	 * 식사_일기 저장
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public int mealDiaryInsert(Map param) throws Exception;
	
	/**
	 * 식사_일기 저장
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public int foodRecognInsert(Map param) throws Exception;

	/**
	 * 도시락 정보 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
    public List<Map<String, String>> selectLunchbox(Map param) throws Exception;

	/**
	 * 도시락 식사_일기 저장
	 * @param param
	 * @return
	 * @throws Exception
	 */
	//public int lunchboxRecognInsert(Map param) throws Exception;
}
