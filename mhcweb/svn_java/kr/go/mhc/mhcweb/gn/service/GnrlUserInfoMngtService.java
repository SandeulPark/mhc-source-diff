package kr.go.mhc.mhcweb.gn.service;

import java.util.List;
import java.util.Map;


/**
 * @Class Name : GnrlUserInfoMngtService.java
 * @Description : 일반 대상자 정보 조회 서비스 interface
 * @Modification Information
 * @
 * @	수정일			수정자		수정내용
 * @	----------		------		---------------------------
 * @	2019.11.22					최초생성
 *
 * @author theJoin
 * @since 
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

public interface GnrlUserInfoMngtService {

	/**
	 * 일반대상자 정보 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception 
	 */
	public List<Map<String,Object>> selectGnrlUserInfoList(Map<String, Object> param)throws Exception;
	/**
	 * 일반대상자 운동 정보 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception 
	 */
	public List<Map<String,Object>> selectGnrlUserExcsList(Map<String, Object> param)throws Exception;
	/**
	 * 일반대상자 활동 정보 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception 
	 */
	public List<Map<String,Object>> selectGnrlUserActList(Map<String, Object> param)throws Exception;
	/**
	 * 일반대상자 체중 정보 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception 
	 */
	public List<Map<String,Object>> selectGnrlUserBodyCompList(Map<String, Object> param)throws Exception;
	/**
	 * 일반대상자 혈압 정보 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception 
	 */
	public List<Map<String,Object>> selectGnrlUserBloodPressList(Map<String, Object> param)throws Exception;
	/**
	 * 일반대상자 혈당 정보 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception 
	 */
	public List<Map<String,Object>> selectGnrlUserBloodSugarList(Map<String, Object> param)throws Exception;
	
	/**
	 * 일반대상자 식사 정보 목록 조회 1116추가
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception 
	*/ 
	public List<Map<String,Object>> selectGnrlUserMealDiaryList(Map<String, Object> param)throws Exception;
	
	
	/**
	 * 일반대상자 식사일기 정보 팝업 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception 
	 */
	public List<Map<String,Object>> selectGnrlMealDiaryDtlsPop(Map<String, Object> param)throws Exception;
	
	/**
	 * 일반대상자 이름,생년월일 ,성별 수정
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception 
	 */
	public void updateUserInfo(Map<String, Object> param)throws Exception;

	/**
	 * 일반사용자 수면 정보 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> selectGnrlUserSleepList(Map<String, Object> param)throws Exception;
	
}
