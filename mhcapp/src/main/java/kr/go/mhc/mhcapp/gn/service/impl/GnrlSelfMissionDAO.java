package kr.go.mhc.mhcapp.gn.service.impl;

import kr.go.mhc.common.DMultiEgovAbstractMapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Class Name : GnrlSelfMissionDAO.java
 * @Description : 모바일 헬스케어 App에서 사용하는 셀프미션 DataBase 연동 관리하는 Class
 * @Modification Information
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Repository("mhcapp.gn.GnrlSelfMissionDAO")
public class GnrlSelfMissionDAO extends DMultiEgovAbstractMapper{

	/**
	 * 셀프미션 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception
	 */
	public List<Map<String, String>> selectSelfMissionList(Map<String, Object> param) {
		List<Map<String,String>> rsList = selectList("mhcapp.gn.selfmission.selectSelfMissionList", param);
		return rsList;
	}

	/**
	 * 셀프미션 상세 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception
	 */
	public Map<String,Object> selectSelfMission(Map<String, Object> param) {
		Map<String,Object> rsMap = new HashMap<String,Object>();
		Map<String,String> rsList = selectOne("mhcapp.gn.selfmission.selectSelfMission", param);
		Map<String,String> rsList2 = null;

		if("M".equals(param.get("MISSION_CYCLE"))) {
			rsList2 = selectOne("mhcapp.gn.selfmission.completeMonthSelfMission", param);
		} else if("W".equals(param.get("MISSION_CYCLE"))) {
			rsList2 = selectOne("mhcapp.gn.selfmission.completeWeekSelfMission", param);
		} else if("D".equals(param.get("MISSION_CYCLE"))) {
			rsList2 = selectOne("mhcapp.gn.selfmission.completeDaySelfMission", param);
		}

		rsList.put("MISSION_CMPT", rsList2.get("MISSION_CMPT"));
		rsList.put("BGN_DE", rsList2.get("BGN_DE"));
		rsList.put("END_DE", rsList2.get("END_DE"));

		rsMap.put("rsList", rsList);
		return rsMap;
	}

	/**
	 * 셀프미션 등록
	 * @throws Exception
	 */
	public int insertSelfMission(Map<String, Object> param) {
		int rsList = insert("mhcapp.gn.selfmission.insertSelfMission", param);
		return rsList;
	}

	/**
	 * 셀프미션 삭제
	 * @throws Exception
	 */
	public int deleteSelfMission(Map<String, Object> param) {
		int rsList = update("mhcapp.gn.selfmission.deleteSelfMission", param);
		delete("mhcapp.gn.selfmission.deleteSelfMissionAchv", param);
		return rsList;
	}

	/**
	 * 셀프미션 완료하기
	 * @throws Exception
	 */
	public void completeSelfMission(Map<String, Object> param) {
		insert("mhcapp.gn.selfmission.completeSelfMission", param);
	}

	/**
	 * 셀프미션 최대점수 조회
	 * @throws Exception
	 */
	public Map<String, Object> selfMissionTotalPoint(Map<String, Object> param) {
		Map<String,Object> rsList = selectOne("mhcapp.gn.selfmission.selfMissionTotalPoint", param);
		return rsList;
	}

	/**
	 * 셀프미션 한달 점수와 뱃지조회
	 * @throws Exception
	 */
	public Map<String, Object> selectBadge(Map<String, Object> param) {
		Map<String,Object> rsMap = selectOne("mhcapp.gn.selfmission.selectBadge", param);
		return rsMap;
	}

	/**
	 * 이번달 뱃지 유무 조회
	 * @throws Exception
	 */
	public Map<String, String> checkAchieveBadge(Map<String, Object> param) {
		Map<String,String> rsList = selectOne("mhcapp.gn.selfmission.checkAchieveBadge", param);
		return rsList;
	}

	/**
	 * 뱃지 등록
	 * @throws Exception
	 */
	public void insertAchieveBadge(Map<String, Object> param) {
		insert("mhcapp.gn.selfmission.insertAchieveBadge", param);
	}

	/**
	 * 뱃지 업데이트
	 * @throws Exception
	 */
	public void updateAchieveBadge(Map<String, Object> param) {
		update("mhcapp.gn.selfmission.updateAchieveBadge", param);
	}

	public List<Map<String, String>> selectSelfMissionBadgeStts(Map<String, Object> param) {
		List<Map<String,String>> rsList = selectList("mhcapp.gn.selfmission.selectSelfMissionBadgeStts", param);
		return rsList;
	}
}
