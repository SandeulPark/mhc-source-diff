package kr.go.mhc.mhcweb.tg.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.go.mhc.mhcweb.tg.service.PhisCnctTrgterCurService;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;


@Service("web.tg.PhisCnctTrgterCurService")
public class PhisCnctTrgterCurServiceImpl extends EgovAbstractServiceImpl implements PhisCnctTrgterCurService{
	
	@Resource(name="web.tg.PhisCnctTrgterCurDAO")
	private PhisCnctTrgterCurDAO phisCnctTrgterCurDAO;

	@Override
	public List<Map<String, String>> phisCnctTrgterCurCount(Map<String, Object> param) throws Exception {
		return phisCnctTrgterCurDAO.phisCnctTrgterCurCount(param);
	}

	@Override
	public List<Map<String, String>> phisCnctTrgterCurTrgterList(Map<String, Object> param) throws Exception {
		return phisCnctTrgterCurDAO.phisCnctTrgterCurTrgterList(param);
	}

	@Override
	public List<Map<String, String>> phisCnctTrgterCurPopTrgterExamList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return phisCnctTrgterCurDAO.phisCnctTrgterCurPopTrgterExamList(param);
	}
}
