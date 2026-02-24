package kr.go.mhc.mhcweb.sv.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.go.mhc.mhcweb.sv.service.ComnCnslMngtService;

import org.springframework.stereotype.Service;

@Service("web.sv.ComnCnslMngtService")
public class ComnCnslMngtServiceImpl implements ComnCnslMngtService{
	
	@Resource(name="web.sv.ComnCnslMngtServiceDAO")
	private ComnCnslMngtServiceDAO comnCnslMngtServiceDAO;

	@Override
	public List<Map<String, String>> getCnslNonCompList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return comnCnslMngtServiceDAO.getCnslNonCompList(param);
	}

	public List<Map<String, String>> getCnslCompList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return comnCnslMngtServiceDAO.getCnslCompList(param);
	}	

	@Override
	public List<Map<String, String>> getRealTimeCnslReqList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return comnCnslMngtServiceDAO.getRealTimeCnslReqList(param);
		
	}
	
	@Override
	public List<Map<String, Object>> selectAllCnslList(Map<String, Object> param) throws Exception{
		// TODO Auto-generated method stub
		return comnCnslMngtServiceDAO.selectAllCnslList(param);
	}

}
