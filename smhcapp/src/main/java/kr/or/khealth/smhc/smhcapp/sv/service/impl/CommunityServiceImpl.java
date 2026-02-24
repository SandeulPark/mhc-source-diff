package kr.or.khealth.smhc.smhcapp.sv.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import kr.or.khealth.smhc.smhcapp.sv.service.CommunityService;

@Service("smhcapp.sv.CommunityService")
public class CommunityServiceImpl extends EgovAbstractServiceImpl implements CommunityService{
	
	@Resource(name="smhcapp.sv.CommunityDAO")
	private CommunityDAO communityDAO;

	@Override
	public List<Map<String, Object>> selectCommunityList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return communityDAO.selectCommunityList(param);
	}

	@Override
	public int checkCommunityInfo(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return communityDAO.checkCommunityInfo(param);
	}

	@Override
	public Map<String, Object> getCommunitySttus(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return communityDAO.getCommunitySttus(param);
	}

	@Override
	public Map<String, Object> selectCommunityCont(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return communityDAO.selectCommunityCont(param);
	}

	@Override
	public List<Map<String, Object>> selectCommunityCmmnt(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return communityDAO.selectCommunityCmmnt(param);
	}

	@Override
	public int insertComment(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return communityDAO.insertComment(param);
	}

	@Override
	public int mergeCmmntLike(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return communityDAO.mergeCmmntLike(param);
	}

	@Override
	public int updateRdCnt(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return communityDAO.updateRdCnt(param);
	}

	@Override
	public int updateCmmnt(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return communityDAO.updateCmmnt(param);
	}

	@Override
	public void commentRptSubmit(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		communityDAO.commentRptSubmit(param);
	}

	@Override
	public void userBlockSubmit(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		communityDAO.userBlockSubmit(param);
	}
	
	
}
