package kr.go.mhc.mhcweb.sv.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.go.mhc.mhcweb.sv.service.ConcCnslMngtService;

import org.springframework.stereotype.Service;

@Service("web.sv.ConcCnslMngtService")
public class ConcCnslMngtServiceImpl implements ConcCnslMngtService{
	
	@Resource(name="web.sv.ConcCnslMngtServiceDAO")
	private ConcCnslMngtServiceDAO ConcCnslMngtServiceDAO;

	@Override
	public List<Map<String, String>> getConcCnslList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return ConcCnslMngtServiceDAO.getConcCnslList(param);
	}
}
