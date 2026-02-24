package kr.go.mhc.mhcweb.cm.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.go.mhc.mhcweb.cm.service.EduVideosMngtService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("web.cm.EduVideosMngtService")
public class EduVideosMngtServiceImpl extends EgovAbstractServiceImpl implements EduVideosMngtService{
	@Resource(name="web.cm.EduVideosMngtDAO")
	private EduVideosMngtDAO eduVideosMngtDAO;

	@Override
	public List<Map<String, String>> getEduVideosMngtList(Map<String, Object> param) throws Exception {
		
		return eduVideosMngtDAO.getEduVideosMngtList(param);
	}
	
	@Override
	public Map<String, String> getEduVideosDtls(Map<String, Object> param) throws Exception {
		
		return eduVideosMngtDAO.getEduVideosDtls(param);
	}
	
	@Override
	public void getEduVideosMngtRegInsert(Map<String, Object> param) throws Exception {
		
		eduVideosMngtDAO.getEduVideosMngtRegInsert(param);
	}
	
	@Override
	public void getEduVideosMngtDel(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		eduVideosMngtDAO.getEduVideosMngtDel(param);
	}
}
