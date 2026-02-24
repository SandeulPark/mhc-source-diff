package kr.go.mhc.mhcweb.gn.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import kr.go.mhc.mhcweb.gn.service.GnMainService;


@Service("gnMainService")
public class GnMainServiceImpl extends EgovAbstractServiceImpl implements GnMainService{
	
	@Resource(name="gnMainDAO")
    private GnMainDAO gnMainDAO;


	@Override
	public List<Map<String, Object>> getCmntyList(Map<String, Object> param) throws Exception {
		return gnMainDAO.getCmntyList(param);
	}
	
	@Override
	public int getGrpJoinCnt(Map<String, Object> param) throws Exception {
		return gnMainDAO.getGrpJoinCnt(param);
	}
	
	@Override
	public int getGrpJoinApproval(Map<String, Object> param) throws Exception {
		return gnMainDAO.getGrpJoinApproval(param);
	}

	@Override
	public int getSportActivityCnt(Map<String, Object> param) throws Exception {
		return gnMainDAO.getSportActivityCnt(param);
	}

	@Override
	public List<Map<String, Object>> getPointRankingSn(Map<String, Object> param) throws Exception {
		return gnMainDAO.getPointRankingSn(param);
	}

	@Override
	public List<Map<String, Object>> getStepRankingList(Map<String, Object> param) throws Exception {
		return gnMainDAO.getStepRankingList(param);		
	}
	
	@Override
	public List<Map<String, Object>> getGrpGenderCnt(Map<String, Object> param) throws Exception {
		return gnMainDAO.getGrpGenderCnt(param);		
	}
	
	@Override
	public List<Map<String, Object>> getAgeCnt(Map<String, Object> param) throws Exception {
		return gnMainDAO.getAgeCnt(param);		
	}

	@Override
	public Map<String, Object> getGrpJoinInfo(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return gnMainDAO.getGrpJoinInfo(param);
	}

	@Override
	public Map<String, Object> getSportActivityInfo(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return gnMainDAO.getSportActivityInfo(param);
	}

	@Override
	public Map<String, Object> getSelfMissionInfo(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return gnMainDAO.getSelfMissionInfo(param);
	}

	@Override
	public Map<String, Object> getMealDiaryInfo(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return gnMainDAO.getMealDiaryInfo(param);
	}	
	

}
