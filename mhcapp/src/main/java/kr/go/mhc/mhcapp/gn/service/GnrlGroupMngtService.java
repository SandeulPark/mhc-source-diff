package kr.go.mhc.mhcapp.gn.service;

import java.util.List;
import java.util.Map;

/**
 * @Class Name : GnrlGroupMngtService.java
 * @Description : 모바일 헬스케어 App에서 사용하는 그룹관리 서비스 Class
 * @Modification Information
 * @
 * @	     수정일			수정자			수정내용
 * @	----------		------		---------------------------
 * @	2019.10.7		이태석			최초생성
 *
 * @author thejoin
 * @since 2019.10.7
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

public interface GnrlGroupMngtService {

	/**
	 * 그룹 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectGroupList(Map<String, Object> param) throws Exception;

	/**
	 * 참여그룹존재여부
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public String isExistGroup(Map<String, Object> param) throws Exception;
	
	
	/**
	 * 참여세부그룹존재여부
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public String isExistGrpSn(Map<String, Object> param) throws Exception;
	
	
	/**
	 * 그룹 참여 신청
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public void insertJoinGroup(Map<String, Object> param) throws Exception;
	
	/**
	 * 참여 그룹 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectJoinGroupList(Map<String, Object> param) throws Exception;

	/**
	 * 참여 그룹 탈퇴
	 * @param param 검색 조건
	 * @return 검색된 ROW 
	 * @throws Exception 
	 */
	public void deleteJoinGroup(Map<String, Object> param) throws Exception;

	/**
	 * 소속 식별 번호 INSERT
	 * @param param
	 */
	public void insertGrpIndfr(Map<String, Object> param) throws Exception;

	/**
	 * 소속 식별 번호 DELETE
	 * @param param
	 */
	public void deleteGrpIdnfr(Map<String, Object> param) throws Exception;

	/**
	 * 소속 식별 번호 조회
	 * @param param
	 * @return
	 */
	public String isExistGrpIdnfr(Map<String, Object> param) throws Exception;

	/**
	 * 기관 개인정보 동의서 조회
	 * jeeeeey 20231128 추가
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> selectOrgPrivacyAgree(Map<String, Object> param) throws Exception;
}
