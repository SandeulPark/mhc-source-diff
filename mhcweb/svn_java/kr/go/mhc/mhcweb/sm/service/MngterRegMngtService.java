package kr.go.mhc.mhcweb.sm.service;

import java.util.List;
import java.util.Map;

/**
 * @Class Name : MngterRegMngtService.java
 * @Description : 관리자 WEB에서 사용하는 관리자 정보를 등록 관리하는 서비스 interface
 * @Modification Information
 * @
 * @	수정일			수정자		수정내용
 * @	----------		----		---------------------------
 * @	2017.03.15		이태석		최초생성
 *
 * @author theJoin
 * @since 2017.03.15
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

public interface MngterRegMngtService {
	/**
	 * 등록된 관리자 정보 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception 
	 */
	public List<Map<String, String>> getRegMngterList(Map<String, Object> param) throws Exception;
	
	/**
	 * 관리자 등록 및 수정
	 * @param param 저장 데이터
	 * @return 저장 된 ROW 수
	 * @throws Exception 
	 */
	public int saveMngter(Map<String, Object> param) throws Exception;
	
	/**
	 * 관리자 중복 체크
	 * @param param
	 * @return 
	 * @throws Exception 
	 */
	public Map<String, Object> getManagerDuplicationCnt(Map<String, Object> param)throws Exception;

	/**
	 * 관리자 등록 승인
	 * @param param 저장 데이터
	 * @return 저장 된 ROW 수
	 * @throws Exception 
	 */
	public int updateApprovalYn(Map<String, Object> param) throws Exception;

	/**
	 * 관리자 공인인증서1 제한 해제
	 * @param param 저장 데이터
	 * @return 저장 된 ROW 수
	 * @throws Exception 
	 */
	public int updatedn1Use(Map<String, Object> param) throws Exception;

	/**
	 * 관리자 공인인증서2 제한 해제
	 * @param param 저장 데이터
	 * @return 저장 된 ROW 수
	 * @throws Exception 
	 */
	public int updatedn2Use(Map<String, Object> param) throws Exception;
	
	/**
	 * 등록된 관리자 팝업 정보 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception 
	 */
	public Map<String, Object> getdigiSignPopUp(Map<String, Object> param) throws Exception;
	
	/**
	 * 그룹 목록 조회
	 * PK 정보로 ROW 조회
	 * @param param PK 정보
	 * @return 검색 된 ROW
	 * @throws Exception 
	 */
	public List<Map<String, String>> permissionsList(Map<String, Object> param) throws Exception;
	
	/**
	 * 그룹 목록 조회
	 * PK 정보로 ROW 조회
	 * @param param PK 정보
	 * @return 검색 된 ROW
	 * @throws Exception 
	 */
	public List<Map<String, String>> userAuthList(Map<String, Object> param) throws Exception;
	
	/**
	 * 그룹 목록 조회
	 * PK 정보로 ROW 조회
	 * @param param PK 정보
	 * @return 검색 된 ROW
	 * @throws Exception 
	 */
	public List<Map<String, String>> permissionsTrgterList(Map<String, Object> param) throws Exception;
	
	/**
	 * 권한 메뉴 조회
	 * PK 정보로 ROW 조회
	 * @param param PK 정보
	 * @return 검색 된 ROW
	 * @throws Exception 
	 */
	public List<Map<String, String>> perMenuList(Map<String, Object> param) throws Exception;
	
	
	/**
	 * 권힌 메뉴  count
	 * PK 정보조회
	 * @param param PK 정보
	 * @return 검색 된 ROW
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectAuthKey(Map<String, Object> param) throws Exception;
	
	/**
	 * 권힌 메뉴 리스트
	 * PK 정보로 ROW 조회
	 * @param param PK 정보
	 * @return 검색 된 ROW
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectPerMenuList(Map<String, Object> param) throws Exception;
	
	
	/**
	 * 권힌 메뉴 count
	 * @param 
	 * @return 검색 된 ROW
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectPerMenuCnt(Map<String, Object> param) throws Exception;
	
	/**
	 * 권한  속성 등록
	 * PK 정보로 ROW 조회
	 * @param param PK 정보
	 * @return 검색 된 ROW
	 * @throws Exception 
	 */
	public void perMenuGInsert(Map<String, Object> param) throws Exception;
	
	/**
	 * 권힌 목록 등록
	 * PK 정보로 ROW 조회
	 * @param param PK 정보
	 * @return 검색 된 ROW
	 * @throws Exception 
	 */
	public void authListInsert(Map<String, Object> param) throws Exception;
	
	/**
	 * 권힌 목록 속성수정	 * 
	 * @param param 
	 * @return 검색 된 ROW
	 * @throws Exception 
	 */
	public void authListUpdate(Map<String, Object> param) throws Exception;
	
	/**
	 * 권힌 목록  아이디수정* 
	 * @param param 
	 * @return 검색 된 ROW
	 * @throws Exception 
	 */
	public void authListUpdate2(Map<String, Object> param) throws Exception;
	
	/**
	 * 권한삭제
	 * @param 
	 * @return sequence
	 * @throws Exception 
	 */
	public void authDelete(Map<String, Object> param) throws Exception;
	
	/**
	 * 권한목록삭제
	 * @param 
	 * @return sequence
	 * @throws Exception 
	 */
	public void authList(Map<String, Object> param) throws Exception;
	
	/**
	 * 유저권한 삭제
	 * @param 
	 * @return sequence
	 * @throws Exception 
	 */
	public void userAuthDelete(Map<String, Object> param) throws Exception;
	
	/**
	 * 관리자 권한 등록 
	 * @param param PK 정보
	 * @return 검색 된 ROW
	 * @throws Exception 
	 */
	public void orgAuthUsersInsert(Map<String, Object> param) throws Exception;
	
	/**
	 * 로그인 메뉴 권한체크
	 * PK 정보로 ROW 조회
	 * @param param PK 정보
	 * @return 검색 된 ROW
	 * @throws Exception 
	 */
	public List<Map<String, String>> userPerCheck(Map<String, Object> param) throws Exception;
	
	/**
	 * 보건소 권한 목록 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */	
	
	public List<Map<String, Object>> getOrgAuthList(Map<String, Object> param) throws Exception;
	
	/**
	 * errReport 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> slectErrReport(Map<String, Object> param) throws Exception;
	
	/**
	 * 권한 등록 유저정보
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> checkRegUser(Map<String, Object> param) throws Exception;
}
