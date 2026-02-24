package kr.go.mhc.mhcapp.gn.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import kr.go.mhc.mhcapp.gn.service.GnrlTutorialService;


@Service("mhcapp.gn.gnrlTutorialService")
public class GnrlTutorialServiceImpl extends EgovAbstractServiceImpl implements GnrlTutorialService{

	@Resource(name="mhcapp.gn.gnrlTutorialDAO")
	private GnrlTutorialDAO gnrlTutorialDAO;
	
	@Override
	public List<Map<String, String>> checkTutoYn(Map<String, Object> param) throws Exception {		
		return gnrlTutorialDAO.checkTutoYn(param);
	}

	@Override
	public void updateTutoYn(Map<String, Object> param) throws Exception {	
		gnrlTutorialDAO.updateTutoYn(param);
	}

	@Override
	public void resetTutorial(Map<String, Object> param) throws Exception {
		gnrlTutorialDAO.resetTutorial(param);
	}

	@Override
	public List<Map<String, String>> tutoUseYn(Map<String, Object> param) throws Exception {
		return gnrlTutorialDAO.tutoUseYn(param);
	}

}
