package kr.go.mhc.mhcapp.gn.service;

import java.util.List;
import java.util.Map;

/**
 * @Class Name : GnrlVisitReservationService.java
 * @Description : 모바일 헬스케어 App에서 사용하는 방문예약 서비스 Class
 * @Modification Information
 * @
 * @	     수정일			수정자			수정내용
 * @	----------		------		---------------------------
 * @	2019.09.27		이태석			최초생성
 *
 * @author thejoin
 * @since 2019.09.27
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

public interface GnrlVisitReservationService {
	
	/**
	 * 보건소 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectOrgNmList(Map<String, Object> param) throws Exception;

	/**
	 * 방문 달력 설정 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectVisitCalendarSet(Map<String, Object> param) throws Exception;
	
	/**
	 * 방문가능 시간 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectVisitDeTm(Map<String, Object> param) throws Exception;
	
	/**
	 * 예약방문 정보 저장
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public Map<String, String> insertVisitReservationInfo(Map<String, Object> param) throws Exception;
	
	/**
	 * 예약 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectVisitReservationList(Map<String, Object> param) throws Exception;
	
	/**
	 * 예약 수 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public Map<String, String> selectVisitReservationCnt(Map<String, Object> param) throws Exception;
	
	/**
	 * 예약 상태 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public Map<String, String> selectResveSttus(Map<String, Object> param) throws Exception;
	
	/**
	 * 방문예약 취소
	 * @param param 검색 조건
	 * @return 
	 * @throws Exception 
	 */
	public void cancelVisitReservationInfo(Map<String, Object> param) throws Exception;
	
	/**
	 * 기관 설정 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public Map<String, String> selectOrgCalendarSetCnt(Map<String, Object> param) throws Exception;
}
