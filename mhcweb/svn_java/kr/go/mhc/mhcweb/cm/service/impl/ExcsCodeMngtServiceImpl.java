package kr.go.mhc.mhcweb.cm.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.go.mhc.mhcweb.cm.service.ExcsCodeMngtService;
import kr.go.mhc.mhcweb.cm.service.impl.ExcsCodeMngtServiceDAO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("web.cm.ExcsCodeMngtService")
public class ExcsCodeMngtServiceImpl extends EgovAbstractServiceImpl implements ExcsCodeMngtService{
	@Resource(name="web.cm.ExcsCodeMngtServiceDAO")
	private ExcsCodeMngtServiceDAO excsCodeMngtServiceDAO;

	@Override
	public List<Map<String, String>> getExcsCodeList(Map<String, Object> param) throws Exception {
		return excsCodeMngtServiceDAO.getExcsCodeList(param);
	}

	@Override
	public int insertExcsCode(Map<String, Object> param)throws Exception {
		return excsCodeMngtServiceDAO.insertExcsCode(param);
	}

	@Override
	public int updateExcsCode(Map<String, Object> param)throws Exception {
		return excsCodeMngtServiceDAO.updateExcsCode(param);
	}

	@Override
	public int updateExcsCodeApprovalYn(Map<String, Object> param) throws Exception {
		return excsCodeMngtServiceDAO.updateExcsCodeApprovalYn(param);
	}
	

}
