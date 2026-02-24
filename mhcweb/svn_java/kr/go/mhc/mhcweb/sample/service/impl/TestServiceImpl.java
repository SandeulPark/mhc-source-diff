package kr.go.mhc.mhcweb.sample.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.go.mhc.mhcweb.sample.service.TestService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("testService")
public class TestServiceImpl extends EgovAbstractServiceImpl implements TestService{
	
	@Resource(name="testDAO")
    private TestDAO testDAO;

	public List<Map<String, Object>> getList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return testDAO.getList(param);
	}

	@Override
	public Map<String, Object> getDetail(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		return testDAO.getDetail(param);
	}
	
	public Map<String, Object> insert(Map<String, Object> param)
			throws Exception {
		testDAO.insert(param);

		// TODO Auto-generated method stub
		return null;
	}	

	@Override
	public int update(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return testDAO.update(param);
	}

	@Override
	public int delete(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return testDAO.delete(param);
	}

}
