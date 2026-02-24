package kr.go.mhc.mhcweb.tm.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import kr.go.mhc.mhcweb.tm.service.TmService;

@Service("web.tm.TmService")
public class TmServiceImpl extends EgovAbstractServiceImpl implements TmService{

	@Resource(name="web.tm.TmDAO")
	private TmDAO tmDAO;

	@Override
	public List<Map<String, String>> getCntnsDtaNurt(Map<String, Object> param) throws Exception {
		return tmDAO.getCntnsDtaNurt(param);
	}

	@Override
	public void updateCntnsDtaNurt(Map<String, Object> param) throws Exception {
		tmDAO.updateCntnsDtaNurt(param);
	}

	@Override
	public List<Map<String, String>> getCntnsDtaSend(Map<String, Object> param) throws Exception {
		return tmDAO.getCntnsDtaSend(param);
	}
	
	@Override
	public void updateCntnsDtaSend(Map<String, Object> param) throws Exception {
		tmDAO.updateCntnsDtaSend(param);
	}	
	
}
