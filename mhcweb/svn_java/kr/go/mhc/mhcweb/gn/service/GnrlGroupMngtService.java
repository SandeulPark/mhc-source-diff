package kr.go.mhc.mhcweb.gn.service;

import java.util.List;
import java.util.Map;

/**
 * @Class Name : GnrlGroupMngtService.java
 * @Description : 관리자 WEB에서 사용하는 일반사용자 그룹관리 업무를 관리하는 서비스 interface
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2019.10.23		이태석			최초생성
 * 
 * @author thejoin
 * @since 2019.10.23
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

public interface GnrlGroupMngtService {

	/**
	 * 그룹 목록 조회
	 * PK 정보로 ROW 조회
	 * @param param PK 정보
	 * @return 검색 된 ROW
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectGnrlGroupList(Map<String, Object> param) throws Exception;
	
	/**
	 * 그룹 참여자 조회
	 * PK 정보로 ROW 조회
	 * @param param PK 정보
	 * @return 검색 된 ROW
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectGroupJoinList(Map<String, Object> param) throws Exception;
	
	/**
	 * 그룹 참여 승인
	 * @param param 저장 데이터
	 * @return 저장 된 ROW 수
	 * @throws Exception 
	 */
	public int updateJoinYn(Map<String, Object> param) throws Exception;
	
	/**
	 * 추가 사용자 조회 (팝업)
	 * PK 정보로 ROW 조회
	 * @param param PK 정보
	 * @return 검색 된 ROW
	 * @throws Exception 
	 */
	public List<Map<String, String>> selectAddGroupUserList(Map<String, Object> param) throws Exception;

	/**
	 * 참여자 등록
	 * @param param PK 정보
	 * @return 
	 * @throws Exception 
	 */
	public void insertGroupUser(Map<String, Object> param) throws Exception;
	
	/**
	 * 참여자 제외
	 * @param param PK 정보
	 * @return 
	 * @throws Exception 
	 */
	public void deleteJoinGroup(Map<String, Object> param) throws Exception;
	
	/**
	 * 신규 그룹 순번 조회
	 * PK 정보로 단일 ROW 조회
	 * @param param PK 정보
	 * @return 검색 된 단일 ROW
	 * @throws Exception 
	 */
	public Map<String,Object> selectNewGroupSn(Map<String, Object> param) throws Exception;
	
	/**
	 * 신규 그룹 등록
	 * @param param PK 정보
	 * @return 
	 * @throws Exception 
	 */
	public void insertNewGroup(Map<String, Object> param) throws Exception;
	
	/**
	 * 그룹 수정
	 * @param param PK 정보
	 * @return 
	 * @throws Exception 
	 */
	public void updateGroup(Map<String, Object> param) throws Exception;
	
	/**
	 * 일별 걸음수 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectDayAct(Map<String, Object> param) throws Exception;
	
	/**
	 * 요일별 걸음수 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectDayWeekAct(Map<String, Object> param) throws Exception;
	
	/**
	 * 시간대별 걸음수 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectTmAct(Map<String, Object> param) throws Exception;
	
	/**
	 * 성별 분포 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectGenderUserCnt(Map<String, Object> param) throws Exception;
	
	/**
	 * 연령별 분포 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectAgeUserCnt(Map<String, Object> param) throws Exception;
	
	/**
	 * 성별/연령별 걸음수 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectGenderAgeAct(Map<String, Object> param) throws Exception;
	
	/**
	 * 그룹 별 걸음수 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectGruopRank(Map<String, Object> param) throws Exception;
	
	/**
	 * 개인 걸음수 조회
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectActCntList(Map<String, Object> param) throws Exception;

	/**
	 * 보편 기관 개인정보 동의서 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectOrgPrivacyAgree(Map<String, Object> param) throws Exception;

	/**
	 * 보편 기관 개인정보 동의서 저장
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public void insertOrgPrivacyAgree(Map<String, Object> param) throws Exception;
}
