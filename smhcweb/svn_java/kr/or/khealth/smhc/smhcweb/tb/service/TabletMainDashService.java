package kr.or.khealth.smhc.smhcweb.tb.service;

import java.util.List;
import java.util.Map;



/**
 * @Class Name : SeniorMeasrInfoService.java
 * @Description : 관리자 TABLET에서 사용하는 어르신 건강  업무를 관리하는 서비스 interface
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2020.10.19		양현우
 *
 * @author thejoin
 * @since 2020.10.19
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */
public interface TabletMainDashService {
	
	/**
	 * 태블릿 메인 화면 전체 대상자 수 조회
	 */
	public Map<String, Object> selectTrgterTodayTotalCount(Map<String, Object> param) throws Exception;
	
	/**
	 * 태블릿 메인 화면 예정 대상자 수 조회
	 */
	public Map<String, Object> selectTrgterTodayIngCount(Map<String, Object> param) throws Exception;
	
	/**
	 * 태블릿 메인 화면 완료 대상자 수 조회
	 */
	public Map<String, Object> selectTrgterTodayEndCount(Map<String, Object> param) throws Exception;
	
	/**
	 * 태블릿 메인 화면 대상자 리스트 조회
	 */
	public List<Map<String, Object>> selectTrgterList(Map<String, Object> param) throws Exception;
	
	/**
	 * 태블릿 메인 화면 검색 화면 리스트 조회
	 */
	public List<Map<String, Object>> selectAllTrgterList(Map<String, Object> param) throws Exception;	

}
