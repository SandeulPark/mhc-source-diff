package kr.go.mhc.mhcweb.pm.service;

import java.util.List;
import java.util.Map;

/**
 * @Class Name : TrgterRegSttusService.java
 * @Description : 관리자 WEB에서 사용하는 대상자 등록현황 실적관리 업무를 관리하는 컨트롤러 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2018.10.11		오샘이			최초생성
 *
 * @author theJoin
 * @since 2018.10.11
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

public interface TrgterRegSttusService {

	/**
	 * 대상자 등록현황 일반 목록 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectTrgterRegSttusGenList(Map<String, Object> param) throws Exception;
	
	/**
	 * 대상자 등록현황 위험요인 목록 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectTrgterRegSttusDenList(Map<String, Object> param) throws Exception;
	
	/**
	 * 대상자 등록현황 군분류 목록 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectTrgterRegSttusDivList(Map<String, Object> param) throws Exception;	
	
	
	/**
	 * 대상자 등록현황 일반 대상자 목록 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectTrgterRegSttusGenTrgterList(Map<String, Object> param) throws Exception;
	
	
	/*
	 * 실적 개편
	 * 
	 * */

	/**
	 * 대상자 등록현황 일반 목록 조회(실적 개편)
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectTrgterRegSttusGenListNew(Map<String, Object> param) throws Exception;
	
	/**
	 * 대상자 등록현황 위험요인 목록 조회(실적 개편)
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectTrgterRegSttusDenListNew(Map<String, Object> param) throws Exception;
	
	/**
	 * 대상자 등록현황 군분류 목록 조회(실적 개편)
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectTrgterRegSttusDivListNew(Map<String, Object> param) throws Exception;	
	
	
	/**
	 * 대상자 등록현황 일반 대상자 목록 조회(실적 개편)
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectTrgterRegSttusGenTrgterListNew(Map<String, Object> param) throws Exception;
}
