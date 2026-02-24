package kr.go.mhc.mhcweb.gn.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.go.mhc.common.DMultiEgovAbstractMapper;

@Repository("gnMainDAO")
public class GnMainDAO extends DMultiEgovAbstractMapper{
	
	
	
	public List<Map<String, Object>> getCmntyList(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.gn.main.selectCmntyList", param);
		return rsList;
	}
	
	public int getGrpJoinCnt(Map<String, Object> param) throws Exception{	
		int rsInt = 0;
		List<Map<String,Object>> rsList = selectList("mhc.web.gn.main.selectGrpJoinCnt", param);						
		rsInt = rsList.size();
		return rsInt;
	}	
	
	public int getGrpJoinApproval(Map<String, Object> param) throws Exception{	
		int rsInt = 0;
		List<Map<String,Object>> rsList = selectList("mhc.web.gn.main.selectGrpJoinApproval", param);						
		rsInt = rsList.size();
		return rsInt;
	}
	public int getSportActivityCnt(Map<String, Object> param) throws Exception{	
		int rsInt = 0;
		List<Map<String,Object>> rsList = selectList("mhc.web.gn.main.getSportActivityCnt", param);						
		rsInt = rsList.size();
		return rsInt;
	}
	
	public List<Map<String, Object>> getPointRankingSn(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.gn.main.getPointRankingSn", param);
		return rsList;
	}
	
	public List<Map<String, Object>> getStepRankingList(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.gn.main.getStepRankingList", param);
		return rsList;
	}
	
	public List<Map<String, Object>> getGrpGenderCnt(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.gn.main.getGrpGenderCnt", param);
		return rsList;
	}
	
	public List<Map<String, Object>> getAgeCnt(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.gn.main.getAgeCnt", param);
		return rsList;
	}

	public Map<String, Object> getGrpJoinInfo(Map<String, Object> param) throws Exception{
		Map<String, Object> rsMap = selectOne("mhc.web.gn.main.getGrpJoinInfo", param);
		return rsMap;
	}

	public Map<String, Object> getSportActivityInfo(Map<String, Object> param) throws Exception{
		Map<String, Object> rsMap = selectOne("mhc.web.gn.main.getSportActivityInfo", param);
		return rsMap;
	}

	public Map<String, Object> getSelfMissionInfo(Map<String, Object> param) {
		Map<String, Object> rsMap = selectOne("mhc.web.gn.main.getSelfMissionInfo", param);
		return rsMap;
	}

	public Map<String, Object> getMealDiaryInfo(Map<String, Object> param) {
		Map<String, Object> rsMap = selectOne("mhc.web.gn.main.getMealDiaryInfo", param);
		return rsMap;
	}
	
}
