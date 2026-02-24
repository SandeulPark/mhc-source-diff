package kr.go.mhc.mhcweb.tg.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.go.mhc.mhcweb.tg.service.PhisPreTrgterMngtService;
import kr.go.mhc.mhcweb.tg.service.PreTrgterApplyService;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;


@Service("web.tg.PreTrgterApplyService")
public class PreTrgterApplyServiceImpl extends EgovAbstractServiceImpl implements PreTrgterApplyService{
	
	@Resource(name="web.tg.PreTrgterApplyDAO")
	private PreTrgterApplyDAO preTrgterApplyDAO;
	
	@Override
	public List<Map<String, String>> preTrgterApplyList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return preTrgterApplyDAO.preTrgterApplyList(param);
	}

	@Override
	public int updateApprovalYn(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return preTrgterApplyDAO.updateApprovalYn(param);
	}

	@Override
	public Map<String, Object> preTrgterApplyRegit(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return preTrgterApplyDAO.preTrgterApplyRegit(param);
	}


}
