package kr.or.khealth.smhc.smhcweb.tg.service;

import java.util.List;
import java.util.Map;

/**
 * @Class Name : SeniorMeasrInfoService.java
 * @Description : 관리자 WEB에서 사용하는 어르신 대상자 측정정보 조회를 관리하는 서비스 interface
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2020.10.15		오샘이			수정
 *
 * @author thejoin
 * @since 2020.10.15
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

public interface SeniorMeasrInfoService {
	
	/**
	 * 대상자총괄관리 7일간 측정 횟수 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selectMeasrWeekCntInfo(Map<String, Object> param) throws Exception;
	
	/**
	 * 대상자총괄관리 미측정 경과일 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selectMeasrNoMeasrInfo(Map<String, Object> param) throws Exception;	
	
	/**
	 * 대상자총괄관리 활동정보 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectActChartList(Map<String, Object> param) throws Exception;
	
	/**
	 * 대상자총괄관리 체성분정보 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectBodyCompChartList(Map<String, Object> param) throws Exception;
	
	/**
	 * 대상자총괄관리 혈압정보 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectBloodPressChartList(Map<String, Object> param) throws Exception;
	
	/**
	 * 대상자총괄관리 혈당정보 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectBloodSugarChartList(Map<String, Object> param) throws Exception;	

}
