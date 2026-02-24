package kr.go.mhc.mhcapp.gn.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import kr.go.mhc.mhcapp.gn.service.GnrlSelfMissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Class Name : GnrlSelfMissionServiceImpl.java
 * @Description : 보편건강 App에서 사용하는 셀프미션에서 필요한 DAO와 연동 관리하는 Class
 * @Modification Information
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Service("mhcapp.gn.GnrlSelfMissionService")
public class GnrlSelfMissionServiceImpl extends EgovAbstractServiceImpl implements GnrlSelfMissionService{
	
	@Resource(name="mhcapp.gn.GnrlSelfMissionDAO")
	private GnrlSelfMissionDAO gnrlSelfMissionDAO;

	/**
	 * 셀프미션 목록 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception
	 */
	@Override
	public List<Map<String, String>> selectSelfMissionList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return gnrlSelfMissionDAO.selectSelfMissionList(param);
	}

	/**
	 * 셀프미션 상세 조회
	 * @param param 검색 조건
	 * @return 검색된 ROW
	 * @throws Exception
	 */
	@Override
	public Map<String,Object> selectSelfMission(Map<String, Object> param) throws Exception {
		return gnrlSelfMissionDAO.selectSelfMission(param);
	}

	/**
	 * 셀프미션 등록
	 */
	@Override
	public int insertSelfMission(Map<String, Object> param) throws Exception {
		return gnrlSelfMissionDAO.insertSelfMission(param);
	}

	/**
	 * 셀프미션 삭제
	 */
	@Override
	public Map<String, Object> deleteSelfMission(Map<String, Object> param) throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		int rtInt = 0;
		rtInt = gnrlSelfMissionDAO.deleteSelfMission(param);
		rsMap.put("deleteCnt", rtInt);
		return rsMap;
	}

	/**
	 * 셀프미션 달성
	 */
	@Override
	public void completeSelfMission(Map<String, Object> param) throws Exception {
		// 달성테이블 insert
		gnrlSelfMissionDAO.completeSelfMission(param);
	}

	/**
	 * 셀프미션 총 점수
	 */
	@Override
	public Map<String, Object> selfMissionTotalPoint(Map<String, Object> param) throws Exception {
		return gnrlSelfMissionDAO.selfMissionTotalPoint(param);
	}

	/**
	 * 셀프미션 뱃지
	 */
	@Override
	public Map<String, Object> selectBadge(Map<String, Object> param) throws Exception {
		return gnrlSelfMissionDAO.selectBadge(param);
	}

	@Override
	public Map<String, String> checkAchieveBadge(Map<String, Object> param) throws Exception {
		return gnrlSelfMissionDAO.checkAchieveBadge(param);
	}

	@Override
	public void insertAchieveBadge(Map<String, Object> param) throws Exception {
		gnrlSelfMissionDAO.insertAchieveBadge(param);
	}

	@Override
	public void updateAchieveBadge(Map<String, Object> param) throws Exception {
		gnrlSelfMissionDAO.updateAchieveBadge(param);
	}

	@Override
	public List<Map<String, String>> selectSelfMissionBadgeStts(Map<String, Object> param) throws Exception {
		return gnrlSelfMissionDAO.selectSelfMissionBadgeStts(param);
	}
}
