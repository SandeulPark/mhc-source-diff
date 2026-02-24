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
	 * 체성분 리스트 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> selectBodycompList(Map param) throws Exception;
	
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
	public int selectMealRegSn(Map param) throws Exception;
	
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
	 * 활동량 주간 미션 측정 정보 조회
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectMeasrActList(Map param) throws Exception;
	
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
	public List<Map<String, String>> selectContentsCnfm(Map param) throws Exception;
	
	/**
	 * 선물받기체크
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectGiftChk(Map param) throws Exception;
	
	/**
	 * 식사 상담기간 조회
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectDietPeriod(Map param) throws Exception;
	
	/**
	 * 집중상담 알림내역 리스트 조회
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectNoticeListChk(Map param) throws Exception;
	
	/**
	 *집중상담기간체크
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectDietPrdChk(Map param) throws Exception;

	/**
	 * 모바일 공지사항 리스트 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> selectMobileNotice(Map param) throws Exception;

	/**
	 * 운동 목록 조회
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectExcsList(Map param) throws Exception;
	
	
	/**
	 * 식사 일기 메인 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> selectMealDiaryList(Map param) throws Exception;
	
	/**
	 * 식사 일기 주차 및 날짜 정보
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> selectMealDiaryWeekInfo(Map param) throws Exception;
	
	/**
	 * 섭취군별 기준 섭취량 대비 비율 정보
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> selectObjEatDay(Map param) throws Exception;
	
	/**
	 * 영양소별 기준 섭취량 대비 비율 정보
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> selectObjEatNeed(Map param) throws Exception;
	
	/**
	 * 식사 일기 상세_유지당류 및 주류 섭취 칼로리 정보
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> selectMealDiarySgCal(Map param) throws Exception;
	
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
	 * 실천_미션_조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> selectPractMission(Map param) throws Exception;
	
	/**
	 * 식사_일기 저장
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public int mealDiaryInsert(Map param) throws Exception;

	/**
	 * 실천 미션 스케쥴 목록 조회
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectPractMissionSch(Map<String, Object> param) throws Exception;
	
	/**
	 * 실천 미션 답변 목록 조회
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectPractMissionAnswr(Map<String, String> param) throws Exception;
	
	/**
	 * 실천 미션 첨부파일 목록 조회
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectPractMissionAttchFile(Map<String, Object> param) throws Exception;
	
	/**
	 * 실천 미션 답변 저장
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	public int savePractMissionAnswr(Map<String, Object> param) throws Exception;
	
	/**
	 * 대시보드 심박수 차트 데이터 조회
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	public Map<String,Object> selectMainHeartRateChartList(Map<String, Object> param) throws Exception;
	
	/**
	 * 심박수 그래프 상세 조회
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	public List<Map<String, String>> heartRateDtlsChart(Map<String, Object> param) throws Exception;
	
	/**
	 * 적정 탄단지 비율 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> selectStndRecomPer(Map param) throws Exception;
	
	/**
	 * 운동모드 마지막 데이터 조회
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectExcsModeLastData(Map param) throws Exception;
	
	/**
	 * 심박 강도별 범위 및 데이터 개수 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selectHeartRateStats(Map<String, Object> param) throws Exception;

	/**
	 * 체성분 상담 값 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> selectBodyCompCnslList(Map param) throws Exception;
	
	/**
	 * 운동스케줄 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> exerSchList(Map param) throws Exception;
	
	
	/**
	 * 운동스케줄 저장
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public int exerSchInsert(Map param) throws Exception;
	
	/**
	 * 운동스케줄 수정
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public int exeSchUpdate(Map param) throws Exception;
	
	/**
	 * 운동스케줄 삭제
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public int exeSchDelete(Map param) throws Exception;

	/**
	 * 복약 미션 답변
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public int saveDrugMissionAnswr(Map<String, Object> param) throws Exception;

	/**
	 * 복약 미션 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> selectDrugList(Map<String, Object> param) throws Exception;

	
}
