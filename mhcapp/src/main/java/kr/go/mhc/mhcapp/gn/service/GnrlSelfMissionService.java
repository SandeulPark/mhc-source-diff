package kr.go.mhc.mhcapp.gn.service;

import java.util.List;
import java.util.Map;

/**
 * @Class Name : GnrlSelfMissionService.java
 * @Description : 보편건강 App에서 사용하는 셀프미션 서비스 Class
 * @Modification Information
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

public interface GnrlSelfMissionService {

	/**
	 * 셀프미션 리스트 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception
	 */
    public List<Map<String, String>> selectSelfMissionList(Map<String, Object> param) throws Exception;

	/**
	 * 셀프미션 상세 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception
	 */
	public Map<String,Object> selectSelfMission(Map<String, Object> param) throws Exception;

	/**
	 * 셀프미션 등록
	 */
	public int insertSelfMission(Map<String, Object> param) throws Exception;

	/**
	 * 셀프미션 삭제
	 */
	public Map<String,Object> deleteSelfMission(Map<String, Object> param) throws Exception;

	public void completeSelfMission(Map<String, Object> param) throws Exception;

	public Map<String, Object> selfMissionTotalPoint(Map<String, Object> param) throws Exception;

	public Map<String, Object> selectBadge(Map<String, Object> param) throws Exception;

	public Map<String, String> checkAchieveBadge(Map<String, Object> param) throws Exception;

	public void insertAchieveBadge(Map<String, Object> param) throws Exception;

	public void updateAchieveBadge(Map<String, Object> param) throws Exception;

	public List<Map<String, String>> selectSelfMissionBadgeStts(Map<String, Object> param) throws Exception;
}
