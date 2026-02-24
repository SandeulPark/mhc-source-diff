package kr.go.mhc.mhcapp.sv.service;

import java.util.List;
import java.util.Map;

/**
 * @Class Name : MyHealthMainDashService.java
 * @Description : 모바일 헬스케어 App에서 사용하는 나의건강-메인 대시 서비스 Class
 * @Modification Information
 * @
 * @	수정일				수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.07.01		오명빈			최초생성
 *
 * @author gst
 * @since 2016.07.01
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

public interface MyHealthMainDashService {
	
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
	 * 활동량 상세 차트 1일 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectActDtlsChart(Map<String, Object> param) throws Exception;
	/**
	 * 메인 활동량  데이터 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectActDtlsStat1(Map<String, Object> param) throws Exception;
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
	 * 나의건강상세 체성분 차트 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectBodyCompChartList(Map param) throws Exception;
	
	/**
	 * 메인 체성분 차트 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectMainBodyCompChartList(Map param) throws Exception;
	
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
	 * 식사기록 등록 데이터 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectDietRecordList(Map param) throws Exception;
	

	/**
	 * 메인 대시 혈압 데이터 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectBloodPressData(Map param) throws Exception;
	
	/**
	 * 메인 대시 혈당 데이터 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectBloodSugarData(Map param) throws Exception;
	
	/**
	 * 운동/식사 기록 데이터 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectExerDietData(Map param) throws Exception;
	
	/**
	 * 알림 도착 유무 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectNoticeChk(Map param) throws Exception;
	
	/**
	 * 식사 구분 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> exeRecordClfCheck(Map param) throws Exception;
	
	/**
	 * 식사기록 등록
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public int dietRecordInsert(Map param) throws Exception;
	
	/**
	 * 식사기록 수정
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public int dietRecordUpdate(Map param) throws Exception;
	
	/**
	 * 식사상담 추가
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public int dietCnslUpdate(Map param) throws Exception;
	
	/**
	 * 식사 상세 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectMealDtls(Map param) throws Exception;
	
	/**
	 * 식사 SN 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectMealRegSn(Map param) throws Exception;
	
	/**
	 * 식사 상세 추가
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	public int mealDtlsInsert(Map param) throws Exception;
	
	/**
	 * 식사 기록 삭제
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	public int mealAllDelete(Map param) throws Exception;
	
	/**
	 * 식사 마스터 유무 조회
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	public List<Map<String, String>> dietMasterCheck(Map param) throws Exception;
	
	/**
	 * 미션 측정 횟수 조회
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectMeasrCntList(Map param) throws Exception;
	
	/**
	 * 주차별 컨텐츠 상세정보
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectContentsDtls(Map param) throws Exception;

	/**
	 * 주차별 컨텐츠 확인여부
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	public String selectContentsCnfm(Map param) throws Exception;
}
