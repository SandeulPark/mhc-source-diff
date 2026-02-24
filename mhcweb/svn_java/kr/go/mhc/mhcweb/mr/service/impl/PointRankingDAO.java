package kr.go.mhc.mhcweb.mr.service.impl;

import java.util.List;
import java.util.Map;

import kr.go.mhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;

/**
 * @Class Name : PointRankingDAO.java
 * @Description : 관리자 WEB에서 사용하는 포인트 및 랭킹 업무 DataBase 연동 관리하는 Class
 * @Modification Information
* @
 * @	수정일			수정자		수정내용
 * @	----------		------		---------------------------
 * @	2016.11.28		이태석		최초생성
 *
 * @author	gst
 * @since	2016.11.28
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Repository("web.mr.PointRankingDAO")
public class PointRankingDAO extends DMultiEgovAbstractMapper {
	// 포인트 및 랭킹 메세지 조회
	public Map<String, Object> getPointRankingMsg(Map<String, Object> param) throws Exception {
		Map<String, Object> rsMap = selectOne("mhc.web.mr.pointranking.selectPointRankingMsg", param);
		return rsMap;
	}
	
	// 포인트 및 랭킹 목록 조회
	public List<Map<String, Object>> getPointRankingList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.mr.pointranking.selectPointRankingList", param);
		return rsList;
	}
	
	// 순위 별 건강포인트 전체 조회
	public List<Map<String, Object>> getRankingAllPointList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.mr.pointranking.selectRankingAllPointList", param);
		return rsList;
	}
	
	//대상여부_초기화 및 선정 업데이트
	public int updatepymntTrgtY(Map<String, Object> param) throws Exception {
		update("mhc.web.mr.pointranking.updatePymntTrgtN", param);
		String[] selUserId = param.get("selUserId").toString().split("\\,");
		String[] selRankSn = param.get("selRankSn").toString().split("\\,");
		int updateCnt = 0;
		for(int i=0; i<selUserId.length; i++) {
			param.put("USER_ID", selUserId[i]);
			param.put("RANK_SN", selRankSn[i]);
			update("mhc.web.mr.pointranking.updatePymntTrgtY", param);
			updateCnt ++;
		}
		return updateCnt;
	}
	
	// 걸음수 랭킹 메세지 조회
	public Map<String, Object> getStepRankingMsg(Map<String, Object> param) throws Exception {
		Map<String, Object> rsMap = selectOne("mhc.web.mr.pointranking.selectStepRankingMsg", param);
		return rsMap;
	}
	
	// 걸음수 랭킹 목록 조회
	public List<Map<String, Object>> getStepRankingList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.mr.pointranking.selectStepRankingList", param);
		return rsList;
	}
}