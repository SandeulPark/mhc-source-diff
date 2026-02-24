package kr.go.mhc.mhcweb.tg.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.go.mhc.mhcweb.tg.service.PhisPreTrgterMngtService;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;


@Service("web.tg.PhisPreTrgterMngtService")
public class PhisPreTrgterMngtServiceImpl extends EgovAbstractServiceImpl implements PhisPreTrgterMngtService{
	
	@Resource(name="web.tg.PhisPreTrgterMngtDAO")
	private PhisPreTrgterMngtDAO phisPreTrgterMngtDAO;
	
	@Override
	public List<Map<String, String>> phisPreTrgterRegitList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return phisPreTrgterMngtDAO.phisPreTrgterRegitList(param);
	}

	@Override
	public Map<String, Object> phisPreTrgterRegitExamInfo(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return phisPreTrgterMngtDAO.phisPreTrgterRegitExamInfo(param);
	}

	@Override
	public Map<String, Object> phisNewPreTrgterRegit(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return phisPreTrgterMngtDAO.phisNewPreTrgterRegit(param);
	}

	@Override
	public Map<String, Object> phisHealthExamRsltPop(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return phisPreTrgterMngtDAO.phisHealthExamRsltPop(param);
	}

	@Override
	public Map<String, Object> selectUserPhisExamRslt(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return phisPreTrgterMngtDAO.selectUserPhisExamRslt(param);
	}

	@Override
	public Map<String, Object> regitExamPhisRslt(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return phisPreTrgterMngtDAO.regitExamPhisRslt(param);
	}

}
