package kr.or.khealth.smhc.smhcweb.tg.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Class Name : HealthMngtCnslService.java
 * @Description : 관리자 WEB에서 사용하는 건강관리 상담 업무를 관리하는 서비스 interface
 * @Modification Information
 * @
 * @	수정일			수정자		수정내용
 * @	----------		-----		---------------------------
 * @	2016.08.28		이현규		최초생성
 *
 * @author gst
 * @since 2016.08.28
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

public interface HealthMngtCnslService {
	
	/**
	 * 건강관리 상담
	 * 건강관리 상담 기본정보 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception
	 */
	public Map<String, String> selectHealthMngtCnslInfo(Map<String, Object> param) throws Exception;
	
	/**
	 * 건강관리 상담
	 * 건강관리 상담 위험요인 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception
	 */
	public List<Map<String, String>> selectDangerFactorList(Map<String, Object> param) throws Exception;
	
	/**
	 * 건강관리 상담
	 * 건강관리 상담 금연절주 설문내용 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception
	 */
	public List<Map<String, String>> selectSmokServeyList(Map<String, Object> param) throws Exception;
	
	/**
	 * 건강관리 상담
	 * 건강관리 상담 상담내용 및 관리계획 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception
	 */
	public Map<String, String> selectCnslContMngt(Map<String, Object> param) throws Exception;
	
	/**
	 * 건강관리 상담
	 * 건강관리 상담 관리군, 영양 목표 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception
	 */
	public Map<String, String> selectMngtObj(Map<String, Object> param) throws Exception;
	
	/**
	 * 건강관리 상담
	 * 건강관리 상담 신체활동비만관리 목표 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception
	 */
	public Map<String, String> selectBodyActFatMngtObj(Map<String, Object> param) throws Exception;
	
	/**
	 * 건강관리 상담
	 * 건강관리 상담 서비스 일정 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception
	 */
	public List<Map<String, String>> selectServiceSchedule(Map<String, Object> param) throws Exception;
	
	/**
	 * 건강관리 상담
	 * 건강관리 상담 서비스 일정 변경 가능 여부 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception
	 */
	public Map<String, String> selectChkCreateSch(Map<String, Object> param) throws Exception;	
	
	/**
	 * 건강관리 상담
	 * 건강관리 상담 디바이스 배포목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception
	 */
	public List<Map<String, String>> selectDeviceDistList(Map<String, Object> param) throws Exception;
	
	/**
	 * 건강관리 상담
	 * 건강관리 상담 상담일자 저장
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception
	 */
	public void updateCnsl(Map<String, Object> param) throws Exception;
	
	/**
	 * 건강관리 상담
	 * 건강관리 상담 상담내용 수정
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception
	 */
	public void updateCnslContMngt(Map<String, Object> param) throws Exception;
	
	/**
	 * 건강관리 상담
	 * 건강관리 상담 혈압계 지급완료
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception
	 */
	public void updateBloodPressPymnt(Map<String, Object> param) throws Exception;
	
	/**
	 * 건강관리 상담
	 * 건강관리 상담 혈당기 지급완료
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception
	 */
	public void updateBloodSugarPymnt(Map<String, Object> param) throws Exception;
	
	/**
	 * 건강관리 상담
	 * 건강관리 상담 스케줄생성
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception
	 */
	public void insertCreateSchedule(Map<String, Object> param) throws Exception;
	
	/**
	 * 건강관리 상담
	 * 건강관리 상담 설문 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception
	 */
	public Map<String, String> selectServeyList(Map<String, Object> param) throws Exception;
	
	/**
	 * 건강관리 상담
	 * 건강관리 상담 설문결과보기 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception
	 */
	public List<Map<String, String>> selectServeyResultList(Map<String, Object> param) throws Exception;
	
	/**
	 * 건강관리 상담
	 * 건강관리 상담 상담일자 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception
	 */
	public List<Map<String, String>> selectHealthCnslDe(Map<String, Object> param) throws Exception;
	
	/**
	 * 건강관리 상담
	 * 건강관리 상담 스케줄생성 후 프로시져
	 * @param
	 * @return
	 * @throws Exception 
	 */
	public String callProcCnslAllIns(Map<String, Object> param) throws Exception;
	
	/**
	 * 건강관리 상담
	 * 방문상담일 수정
	 * @param 방문상담일
	 * @return 검색된 ROW
	 * @throws Exception
	 */
	public int updateVisitCnslDe(Map<String, Object> param) throws Exception;
	
	/**
	 * 종합상담 결과지
	 * 건강검진 조회
	 * @param 방문상담일
	 * @return 검색된 ROW
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectCnslRsltExamList(Map<String, Object> param) throws Exception;
	
	/**
	 * 종합상담 결과지
	 * 영양관리목표 조회
	 * @param 방문상담일
	 * @return 검색된 ROW
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectNurtObjList(Map<String, Object> param) throws Exception;
	
	/**
	 * 종합상담 결과지
	 * 실천미션결과 조회
	 * @param 방문상담일
	 * @return 검색된 ROW
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectMissionResultList(Map<String, Object> param) throws Exception;
	
	/**
	 * 종합상담 결과지
	 * 집중상담 식품군 섭취 평가 조회
	 * @param 방문상담일
	 * @return 검색된 ROW
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectIntensiveNurtCnslList(Map<String, Object> param) throws Exception;
	
	/**
	 * 종합상담 결과지
	 * 식사일기 주차별 섭취칼로리 조회
	 * @param 방문상담일
	 * @return 검색된 ROW
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectMealDiaryWeekCalList(Map<String, Object> param) throws Exception;
	
	/**
	 * 종합상담 결과지
	 * 식사일기 일평균 섭취현황 조회
	 * @param 방문상담일
	 * @return 검색된 ROW
	 * @throws Exception
	 */
	public Map<String, Object> selectMealDiaryDayAvgPer(Map<String, Object> param) throws Exception;
	
	/**
	 * 종합상담 결과지
	 * 식사일기 탄단지 비율 조회
	 * @param 방문상담일
	 * @return 검색된 ROW
	 * @throws Exception
	 */
	public Map<String, Object> selectCPFPer(Map<String, Object> param) throws Exception;
	
	/**
	 * 종합상담 결과지
	 * 신체활동 관리목표 조회
	 * @param 방문상담일
	 * @return 검색된 ROW
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectBodyActObjList(Map<String, Object> param) throws Exception;
	
	/**
	 * 종합상담 결과지
	 * 신체활동 분석 조회
	 * @param 방문상담일
	 * @return 검색된 ROW
	 * @throws Exception
	 */
	public Map<String, Object> selectBodyActAnalysis(Map<String, Object> param) throws Exception;
	
	/**
	 * 종합상담 결과지
	 * 신체활동 주차별 조회
	 * @param 방문상담일
	 * @return 검색된 ROW
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectBodyActWeekList(Map<String, Object> param) throws Exception;
	
	/**
	 * 종합상담 결과지
	 * 데이터 유무 조회
	 * @param 방문상담일
	 * @return 검색된 ROW
	 * @throws Exception
	 */
	public Map<String, Object> selectNoDataCheckInfo(Map<String, Object> param) throws Exception;
	
	/**
	 * 종합상담 결과지
	 * 요일별 활동량 조회
	 * @param 방문상담일
	 * @return 검색된 ROW
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectWeekDayActList(Map<String, Object> param) throws Exception;
	
	/**
	 * 종합상담 결과지
	 * 실천미션결과 요약 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectMissionResultList2(Map<String, Object> param) throws Exception;
	
	/**
	 * 종합상담 결과지
	 * 식사일기 요약 정보 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectMealDiaryResult(Map<String, Object> param) throws Exception;
	
	/**
	 * 심뇌혈관 건강 위험도 계산 기준 정보 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectCvdPointList(Map<String, Object> param) throws Exception;
	
	/**
	 * 대상자 기본 정보 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selectBasicUserInfo(Map<String, Object> param) throws Exception;
	
	/**
	 * 종합상담 결과지
	 * 영양 섭취평가 정보 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selectMealtNutriRsltInfo(Map<String, Object> param) throws Exception;
	
	/**
	 * 금영절주 컨텐츠 수신여부 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selectCnslYnList(Map<String, Object> param) throws Exception;
}
