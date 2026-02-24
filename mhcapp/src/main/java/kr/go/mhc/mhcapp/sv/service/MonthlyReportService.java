package kr.go.mhc.mhcapp.sv.service;

import java.util.List;
import java.util.Map;

public interface MonthlyReportService {

	/**
	 * 월간리포트 카테고리 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectCategoryList(Map<String, Object> param) throws Exception;
	
	/**
	 * 리포트 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectMonthlyReportList(Map<String, Object> param) throws Exception;
	
	/**
	 * 평가 기간 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectPeriod(Map<String, Object> param) throws Exception;
	
	/**
	 * 월간리포트 종합 체중변화 차트 데이터 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectWeightChartList(Map<String, Object> param) throws Exception;
	
	/**
	 * 월간리포트 종합 현재/목표 체중 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectWeightList(Map<String, Object> param) throws Exception;
	
	/**
	 * 월간리포트 종합 월간 체성분 차트 데이터 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectMonthlyBodyCompChartList(Map<String, Object> param) throws Exception;
	
	/**
	 * 월간리포트 종합 월간 체성분 각 차이값 데이터 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectMonthlyWeightList(Map<String, Object> param) throws Exception;
	
	/**
	 * 월간리포트 활동량 차트 데이터 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectMonthlyActChartList(Map<String, Object> param) throws Exception;
	
	/**
	 * 월간리포트 총 활동량, 평균 조회 
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectMonthlyActList(Map<String, Object> param) throws Exception;
	
	/**
	 * 월간리포트 칼로리,운동시간 조회 
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectMonthlyKcalTime(Map<String, Object> param) throws Exception;
	
	/**
	 * 월간리포트 총 누적거리 조회 
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectMonthlyDstc(Map<String, Object> param) throws Exception;
	
	/**
	 * 월간리포트 혈압 차트 데이터 조회 
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectMonthlyBloodPressChartList(Map<String, Object> param) throws Exception;
	
	/**
	 * 월간리포트 혈압 평균 데이터 조회 
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectMonthlyBloodPressData(Map<String, Object> param) throws Exception;
	
	/**
	 * 월간리포트 혈당 차트 데이터 조회 
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectMonthlyBloodSugarChartList(Map<String, Object> param) throws Exception;
	
	/**
	 * 월간리포트 혈당 평균 데이터 조회 
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectMonthlyBloodSugarData(Map<String, Object> param) throws Exception;
	
	/**
	 * 월간리포트 측정달성 조회 
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectMeasrComplete(Map<String, Object> param) throws Exception;
	
	/**
	 * 월간리포트 컨텐츠리스트 조회 
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectContentsList(Map<String, Object> param) throws Exception;
	
	/**
	 * 리포트 확인 업데이트
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	public int updateReportCnfmChk(Map<String, Object> param) throws Exception;
	
	/**
	 * 요일별 평균 걸음수 조회
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectDayExceActList(Map<String, Object> param) throws Exception;
	
	/**
	 * 목표걸음달성, 평균걸음수 조회
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	public Map<String, String> selectObeActSuc(Map<String, Object> param) throws Exception;
	
	/**
	 * 요일별 평균 소모칼로리 조회
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectWeekMealCalList(Map<String, Object> param) throws Exception;
	
	/**
	 * 식사기록일수, 평균섭취칼로리 조회
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	public Map<String, String> selectDayMealCal(Map<String, Object> param) throws Exception;
	
	/**
	 * 미확인 상담 조회
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectUncnfmCnslList(Map<String, Object> param) throws Exception;
	
	/**
	 * 영양미션실천율 조회
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	public Map<String, String> selectNurtMissionSucPer(Map<String, Object> param) throws Exception;
	
	/**
	 * 월간리포트 상담 내용 조회

	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectCnslCont(Map<String, Object> param) throws Exception;
	
	/**
	 * 월간리포트 추가 컨텐츠 조회
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectAttachFileList(Map<String, Object> param) throws Exception;
	
	
	/**
	 * 자동 발송 여부 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public Map<String, String> selectAutoSendYn(Map<String, Object> param) throws Exception;

	
}
