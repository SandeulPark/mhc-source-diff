package kr.go.mhc.mhcweb.st.service;

import java.util.List;
import java.util.Map;



public interface MthlyHealthRptService {

	/**
	 * 월간 리포트 제공 리스트 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> mthlyHealthRptList(Map<String, Object> param) throws Exception;
	
	/**
	 * 월간 리포트 제공 차수 및 일자 리스트 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> mthlyHealthRptDateList(Map<String, Object> param) throws Exception;
	
	/**
	 * 신체활동 관리목표 리스트 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> bodyGoalList(Map<String, Object> param) throws Exception;
	
	/**
	 * 영양 관리목표 리스트 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> nutriGoalList(Map<String, Object> param) throws Exception;
	
	/**
	 * 상담 템플릿 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectCnslTemplateNmList(Map<String, Object> param) throws Exception;
	
	/**
	 * 월간리포트 상담 내용 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selectMonthlyCnsl(Map<String, Object> param) throws Exception;
	
	/**
	 * 월간리포트 첨부파일 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectMonthlyCnslAttchFiles(Map<String, Object> param) throws Exception;
	
	/**
	 * 요일별 평균 걸음수 차트
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectDayExceActList(Map<String, Object> param) throws Exception;
	
	/**
	 * 목표걸음달성, 평균걸음수조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selectObeActSuc(Map<String, Object> param) throws Exception;
	
	/**
	 * 종합 체중변화 차트 데이터 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectWeightChartList(Map<String, Object> param) throws Exception;
	
	/**
	 * 종합 현재/목표 체중
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectWeightList(Map<String, Object> param) throws Exception;
	
	/**
	 * 요일별, 끼니별 칼로리 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectWeekMealCalList(Map<String, Object> param) throws Exception;
	
	/**
	 * 식사기록일수, 평균섭취칼로리 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selectDayMealCal(Map<String, Object> param) throws Exception;
	
	/**
	 * 종합 월별 체성분 차트 데이터 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectMonthlyBodyCompChartList(Map<String, Object> param) throws Exception;
	
	/**
	 * 종합 월별 체성분 각 비교값 데이터 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectMonthlyWeightList(Map<String, Object> param) throws Exception;
	
	/**
	 * 혈압 차트 데이터 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectMonthlyBloodPressChartList(Map<String, Object> param) throws Exception;
	
	/**
	 * 혈압 평균 데이터 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectMonthlyBloodPressData(Map<String, Object> param) throws Exception;
	
	/**
	 * 혈당 차트 데이터 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectMonthlyBloodSugarChartList(Map<String, Object> param) throws Exception;
	
	/**
	 * 혈당 평균 데이터 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectMonthlyBloodSugarData(Map<String, Object> param) throws Exception;
	
	/**
	 * 칼로리, 운동시간 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectMonthlyKcalTime(Map<String, Object> param) throws Exception;
	
	/**
	 * 영양미션 실천율 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selectNurtMissionSucPer(Map<String, Object> param) throws Exception;

	/**
	 * 복약미션 실천율 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selectDrugMissionSucPer(Map<String, Object> param) throws Exception;

	/**
	 * 의사 지시내용 차수별 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selectRqstDtls(Map<String, Object> param) throws Exception;
	
	/**
	 * 월간리포트 저장
	 * @param param
	 * @throws Exception
	 */
	public int updateMonthlyCnsl(Map<String, Object> param) throws Exception;
	
	/**
	 * 월간리포트 삭제
	 * @param param
	 * @throws Exception
	 */
	public int deleteMonthlyCnsl(Map<String, Object> param) throws Exception;	
	
	/**
	 * 월간리포트 발송
	 * @param param
	 * @throws Exception 
	 */
	public int updateSubmit(Map<String, Object> param) throws Exception;
	
	/**
	 * 금영절주 컨텐츠 수신여부 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selectCnslYnList(Map<String, Object> param) throws Exception;
}
