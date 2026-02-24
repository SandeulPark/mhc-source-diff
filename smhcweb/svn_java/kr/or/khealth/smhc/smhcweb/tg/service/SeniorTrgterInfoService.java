package kr.or.khealth.smhc.smhcweb.tg.service;

import java.util.List;
import java.util.Map;

/**
 * @Class Name : TrgterInfoMngtService.java
 * @Description : 관리자 WEB에서 사용하는 어르신 대상자 조회를 관리하는 서비스 interface
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2020.09.16		양현우			수정
 *
 * @author thejoin
 * @since 2020.09.16
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

public interface SeniorTrgterInfoService {
	
	/**
	 * 대상자총괄관리 대상자 목록 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectSeniorTrgterInfoList(Map<String, Object> param) throws Exception;
	
	/**
	 * 대상자총괄관리 대상자 달력 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectSeniorTrgterCalendarList(Map<String, Object> param) throws Exception;
//	
//	/**
//	 * 대상자정보관리 대상자 저장
//	 * @param
//	 * @return
//	 * @throws Exception
//	 */
//	public void updateTrgterInfo(Map<String, Object> param) throws Exception;
//	
//	/**
//	 * 대상자정보관리 신체활동 탭 활동목표
//	 * @param
//	 * @return
//	 * @throws Exception
//	 */
//	public List<Map<String, Object>> selTrgterActList(Map<String, Object> param) throws Exception;
//	
//	/**
//	 * 대상자정보관리 신체활동 탭 일자별
//	 * @param
//	 * @return
//	 * @throws Exception
//	 */
//	public List<Map<String, Object>> selTrgterActDEList(Map<String, Object> param) throws Exception;
//	
//	/**
//	 * 대상자정보관리 신체활동 탭 일자별 평균
//	 * @param
//	 * @return
//	 * @throws Exception
//	 */
//	public Map<String, Object> selTrgterActDEAVG(Map<String, Object> param) throws Exception;
//	
//	/**
//	 * 대상자정보관리 신체활동 탭 요일별
//	 * @param
//	 * @return
//	 * @throws Exception
//	 */
//	public List<Map<String, Object>> selTrgterActDYList(Map<String, Object> param) throws Exception;
//	
//	/**
//	 * 대상자정보관리 신체활동 탭 누적, 평균
//	 * @param
//	 * @return
//	 * @throws Exception
//	 */
//	public List<Map<String, Object>> selTrgterTotActCnt(Map<String, Object> param) throws Exception;
//	
//	/**
//	 * 대상자정보관리 신체활동 탭 주차별 현황
//	 * @param
//	 * @return
//	 * @throws Exception
//	 */
//	public List<Map<String, Object>> selTrgterWKInfo(Map<String, Object> param) throws Exception;
//	
//	/**
//	 * 대상자정보관리 체성분 탭 체중목표
//	 * @param
//	 * @return
//	 * @throws Exception
//	 */
//	public List<Map<String, Object>> selTrgterWeightList(Map<String, Object> param) throws Exception;
//	
//	/**
//	 * 대상자정보관리 체성분 탭 측정값 변화(일자별)
//	 * @param
//	 * @return
//	 * @throws Exception
//	 */
//	public List<Map<String, Object>> selTrgterWeightDEList(Map<String, Object> param) throws Exception;
//	
//	/**
//	 * 대상자정보관리 체성분 탭 체성분 시작, 종료 비교
//	 * @param
//	 * @return
//	 * @throws Exception
//	 */
//	public Map<String, Object> selTrgterWeightSTED(Map<String, Object> param) throws Exception;
//	
//	/**
//	 * 대상자정보관리 체성분 탭 서비스 주차별 현황
//	 * @param
//	 * @return
//	 * @throws Exception
//	 */
//	public List<Map<String, Object>> selTrgterWeightWKInfo(Map<String, Object> param) throws Exception;
//	
//	/**
//	 * 대상자정보관리 혈압 최근 측정현황
//	 * @param
//	 * @return
//	 * @throws Exception
//	 */
//	public Map<String, Object> selTrgterBldPressList(Map<String, Object> param) throws Exception;
//	
//	/**
//	 * 대상자정보관리 혈압 측정값 변화 (차트)
//	 * @param
//	 * @return
//	 * @throws Exception
//	 */
//	public List<Map<String, Object>> selTrgterBldPressDEList(Map<String, Object> param) throws Exception;
//	
//	/**
//	 * 대상자정보관리 혈압 기간 평균, 최초 측정 비교
//	 * @param
//	 * @return
//	 * @throws Exception
//	 */
//	public Map<String, Object> selTrgterBldPressSTAVG(Map<String, Object> param) throws Exception;
//	
//	/**
//	 * 대상자정보관리 혈압 서비스 주차별 현황
//	 * @param
//	 * @return
//	 * @throws Exception
//	 */
//	public List<Map<String, Object>> selTrgterBldWKInfo(Map<String, Object> param) throws Exception;
//	
//	/**
//	 * 대상자정보관리 혈압 이상 측정정보 (그리드)
//	 * @param
//	 * @return
//	 * @throws Exception
//	 */
//	public List<Map<String, Object>> selPressDisorderExamInfo(Map<String, Object> param) throws Exception;
//	
//	/**
//	 * 대상자정보관리 혈압 및 혈당 이상 측정정보 처리내역저장 팝업창 update 업데이트 저장 
//	 * @param
//	 * @return
//	 * @throws Exception
//	 */
//	public void updateDisorderExamProc(Map<String, Object> param) throws Exception;
//	
//	/**
//	 * 대상자정보관리 혈당 최근 측정현황 (테이블)
//	 * @param
//	 * @return
//	 * @throws Exception
//	 */
//	public Map<String, Object> selTrgterBldSugarList(Map<String, Object> param) throws Exception;
//	
//	/**
//	 * 대상자정보관리 혈당 측정값 변화 (차트)
//	 * @param
//	 * @return
//	 * @throws Exception
//	 */
//	public List<Map<String, Object>> selTrgterBldSugarDEList(Map<String, Object> param)	throws Exception;
//	
//	/**
//	 * 대상자정보관리 혈당 기간 평균, 최초 측정 비교 (테이블)
//	 * @param
//	 * @return
//	 * @throws Exception
//	 */
//	public Map<String, Object> selTrgterBldSugarSTAVG(Map<String, Object> param) throws Exception;
//	
//	/**
//	 * 대상자정보관리 혈당 서비스 주차별 현황 (그리드)
//	 * @param
//	 * @return
//	 * @throws Exception
//	 */
//	public List<Map<String, Object>> selTrgterBldSugarWKInfo(Map<String, Object> param) throws Exception;
//	
//	/**
//	 * 대상자정보관리 혈당 이상 측정정보 (그리드)
//	 * @param
//	 * @return
//	 * @throws Exception
//	 */
//	public List<Map<String, Object>> selSugarDisorderExamInfo(Map<String, Object> param) throws Exception;
//	
//	/**
//	 * 대상자정보관리 검진기록 검진결과 (그리드)
//	 * @param
//	 * @return
//	 * @throws Exception
//	 */
//	public List<Map<String, Object>> selTrgterExamRsltList(Map<String, Object> param) throws Exception;
//	
//	/**
//	 * 대상자정보관리 검진기록 검사결과 (테이블)
//	 * @param
//	 * @return
//	 * @throws Exception
//	 */
//	public Map<String, Object> selTrgterExamRslt(Map<String, Object> param) throws Exception;
//	
//	/**
//	 * 대상자정보관리 서비스참여 탭 건강정보 측정 상세
//	 * @param
//	 * @return
//	 * @throws Exception
//	 */
//	public List<Map<String, Object>> selTrgterSvcHealthInfo(Map<String, Object> param) throws Exception;
//	
//	/**
//	 * 대상자정보관리 서비스참여 탭 건강정보 측정 실천률
//	 * @param
//	 * @return
//	 * @throws Exception
//	 */
//	public Map<String, Object> selTrgterSvcHealthInfoPracRate(Map<String, Object> param) throws Exception;
//	
//	/**
//	 * 대상자정보관리 상담 탭 방문상담 조회
//	 * @param
//	 * @return
//	 * @throws Exception
//	 */
//	public List<Map<String, Object>> selectVisitCnslList(Map<String, Object> param) throws Exception;
//	
//	/**
//	 * 대상자정보관리 상담 탭 집중상담 조회
//	 * @param
//	 * @return
//	 * @throws Exception
//	 */
//	public List<Map<String, Object>> selectFocusCnslList(Map<String, Object> param) throws Exception;
//	
//	/**
//	 * 대상자정보관리 상담 탭 일반상담 조회
//	 * @param
//	 * @return
//	 * @throws Exception
//	 */
//	public List<Map<String, Object>> selectGeneralCnslList(Map<String, Object> param) throws Exception;
//	
//	/**
//	 * 대상자정보관리 상담 탭 일반상담 상담확인 팝업
//	 * @param
//	 * @return
//	 * @throws Exception
//	 */
//	public Map<String, Object> selectGeneralCnsl(Map<String, Object> param) throws Exception;
//	
//	/**
//	 * 대상자정보관리 상담 탭 일반상담 저장여부 체크
//	 * @param param
//	 * @return
//	 * @throws Exception
//	 */
//	public Map<String, Object> selectGeneralCnslChk(Map<String, Object> param) throws Exception;
//	
//	/**
//	 * 대상자정보관리 상담 탭 일반상담 상담저장, 상담게시
//	 * @param
//	 * @return
//	 * @throws Exception
//	 */
//	public void updateGeneralCnsl(Map<String, Object> param) throws Exception;
//	
//	/**
//	 * 대상자정보관리 상담 탭 실시간 상담 1:1상담 요청  중복 체크
//	 * @param
//	 * @return
//	 * @throws Exception
//	 */
//	public int countRealTimeCnslChk(Map<String, Object> param) throws Exception;
//	
//	/**
//	 * 대상자정보관리 중도탈락 업데이트
//	 * @param
//	 * @return
//	 * @throws Exception
//	 */
//	public void updateDrop(Map<String, Object> param) throws Exception;
//	
//	/**
//	 * 대상자정보관리 중도탈락 조회
//	 * @param
//	 * @return
//	 * @throws Exception
//	 */
//	public Map<String, Object> selectDrop(Map<String, Object> param) throws Exception;
//	
//	/**
//	 * 대상자정보관리 중도탈락 취소
//	 * @param
//	 * @return
//	 * @throws Exception
//	 */
//	public Map<String, Object> cancelDrop(Map<String, Object> param) throws Exception;
//	
//	/**
//	 * 2017.02.23 이태석 추가
//	 * 대상자정보관리 신체활동 탭 활동량 일자별 현황
//	 * @param
//	 * @return
//	 * @throws Exception
//	 */
//	public List<Map<String, Object>> getDayActDataList(Map<String, Object> param) throws Exception;
//	
//	/**
//	 * 2017.02.23 이태석 추가
//	 * 대상자정보관리 체성분 탭 체성분 일자별 현황
//	 * @param
//	 * @return
//	 * @throws Exception
//	 */
//	public List<Map<String, Object>> getDayBodyCompDataList(Map<String, Object> param) throws Exception;
//	
//	/**
//	 * 2017.02.23 이태석 추가
//	 * 대상자정보관리 혈압 탭 혈압 일자별 현황
//	 * @param
//	 * @return
//	 * @throws Exception
//	 */
//	public List<Map<String, Object>> getDayBloodPressDataList(Map<String, Object> param) throws Exception;
//	
//	/**
//	 * 2017.02.23 이태석 추가
//	 * 대상자정보관리 혈당 탭 혈당 일자별 현황
//	 * @param
//	 * @return
//	 * @throws Exception
//	 */
//	public List<Map<String, Object>> getDayBloodSugarDataList(Map<String, Object> param) throws Exception;
//	
//	
//
//	/**
//	 * 2017.06.16 추가
//	 * 대상자정보관리 심박수 탭 심박목표
//	 * @param
//	 * @return
//	 * @throws Exception
//	 */
//	public List<Map<String, Object>> selHeartGoal(Map<String, Object> param) throws Exception;	
//	
//	/**
//	 * 2017.06.16 추가
//	 * 대상자정보관리 심박수 탭 목표심박달성률
//	 * @param
//	 * @return
//	 * @throws Exception
//	 */
//	public List<Map<String, Object>> selHeartAchiRate(Map<String, Object> param) throws Exception;	
//	
//	/**
//	 * 2017.06.16 추가
//	 * 대상자정보관리 심박수 탭 일자별 심박측정정보
//	 * @param
//	 * @return
//	 * @throws Exception
//	 */
//	public List<Map<String, Object>> selDayHeartData(Map<String, Object> param) throws Exception;	
//	
//	/**
//	 * 2017.06.16 추가
//	 * 대상자정보관리 심박수 탭 시간대별 심박측정정보
//	 * @param
//	 * @return
//	 * @throws Exception
//	 */
//	public List<Map<String, Object>> selHourHeartData(Map<String, Object> param) throws Exception;		
//	
//	/**
//	 * 대상자 모니터링 메모 조회
//	 * @param param
//	 * @return
//	 * @throws Exception
//	 */
//	public List<Map<String, Object>> selectMemoDtls(Map<String, Object> param) throws Exception;
//	
//	/**
//	 * 대상자 모니터링 메모 신규 저장
//	 * @param param
//	 * @return
//	 * @throws Exception
//	 */
//	public int insertMemo(Map<String,Object> param) throws Exception;
//	
//	
//	/**
//	 * 2018.06.19 유준영 추가
//	 * 대상자 이력정보 조회 
//	 * @param
//	 * @return
//	 * @throws Exception
//	 */
//	public List<Map<String, Object>> getTrgterMeasrInfoExcelAct(Map<String, Object> param) throws Exception;
//	
//	public List<Map<String, Object>> getTrgterMeasrInfoExcelBodyComp(Map<String, Object> param) throws Exception;
//	
//	public List<Map<String, Object>> getTrgterMeasrInfoExcelBloodPress(Map<String, Object> param) throws Exception;
//	
//	public List<Map<String, Object>> getTrgterMeasrInfoExcelBloodSugar(Map<String, Object> param) throws Exception;
//	
//	
//	/**
//	 * 대상자 통합검색 리스트 조회(대상자 검색 팝업)
//	 * @param param
//	 * @return
//	 * @throws Exception
//	 */
//	public List<Map<String, Object>> trgterCbSearchList(Map<String, Object> param) throws Exception;
//	
//	/**
//	 * 대상자 통합검색 관리목표-신체활동
//	 * @param param
//	 * @return
//	 * @throws Exception
//	 */
//	public List<Map<String, Object>> bodyGoalList(Map<String, Object> param) throws Exception;
//	
//	/**
//	 * 대상자 통합검색 관리목표-영양
//	 * @param param
//	 * @return
//	 * @throws Exception
//	 */
//	public List<Map<String, Object>> nutriGoalList(Map<String, Object> param) throws Exception;
//	
//	/**
//	 * 대상자 통합검색 목표정보 조회
//	 * @param param
//	 * @return
//	 * @throws Exception
//	 */
//	public List<Map<String, Object>> trgterCbInfo(Map<String, Object> param) throws Exception;
}
