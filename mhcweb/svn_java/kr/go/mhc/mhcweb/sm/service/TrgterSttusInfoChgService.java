package kr.go.mhc.mhcweb.sm.service;

import java.util.List;
import java.util.Map;


/**
 * @Class Name : TrgterSttusInfoChgService.java
 * @Description : 관리자 WEB에서 사용하는 대상자 상태 정보 변경 서비스 interface
 * @Modification Information
 * @
 * @	수정일			수정자		수정내용
 * @	----------		------		---------------------------
 * @	2019.11.04				최초생성
 *
 * @author theJoin
 * @since 
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */


public interface TrgterSttusInfoChgService {

	/**
	 * 대상자 정보 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception 
	 */
	public List<Map<String, Object>> selectTrgterSttusInfoList(Map<String, Object> param) throws Exception;
	/**
	 * 대상자 이름 성별 생년 월일 테스트 구분 변경 
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception 
	 */
	public int trgterSttusInfoTestChg(Map<String, Object> param) throws Exception;
	/**
	 * 대상자 서비스 상태  변경 
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception 
	 */
	public int trgterSttusInfoTestChange(Map<String, Object> param) throws Exception;

	/**
	 * 대상자 스케줄 조회 
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception 
	 */
	public List<Map<String, Object>> selectTrgterSttusInfoServiceSchedule(Map<String, Object> param) throws Exception;

	/**
	 * 대상자 정보 검진 데이터 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception 
	 */
	public List<Map<String, Object>> selectTrgterSttusInfoExamList(Map<String, Object> param) throws Exception;
	
	/**
	 * 대상자 건강검진 정보 변경
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception 
	 */
	public int trgterSttusChange(Map<String, Object> param) throws Exception;
	
	/**
	 * 대상자 스케줄 재생성 프로시저
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception 
	 */
	public String trgterScheduleChg(Map<String, Object> param) throws Exception;

	/**
	 * 대상자 스케줄 삭제
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception 
	 */
	public int trgterScheduleDelete(Map<String, Object> param) throws Exception;
	/**
	 * 대상자 정보 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception 
	 */
	public List<Map<String, Object>> selectTrgterSttusInfoExamSn(Map<String, Object> param) throws Exception;
	/**
	 * 대상자 성별 변경시  변경
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception 
	 */
	public int updateTrgterSttusInfoExamSn(Map<String, Object> param) throws Exception;
}
