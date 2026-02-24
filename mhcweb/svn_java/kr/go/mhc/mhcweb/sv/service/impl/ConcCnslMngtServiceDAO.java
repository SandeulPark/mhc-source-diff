package kr.go.mhc.mhcweb.sv.service.impl;

import java.util.List;
import java.util.Map;

import kr.go.mhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;

@Repository("web.sv.ConcCnslMngtServiceDAO")
public class ConcCnslMngtServiceDAO extends DMultiEgovAbstractMapper{

	
	public List<Map<String, String>> getConcCnslList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String, String>> rsList = selectList("mhc.web.sv.conccnslmngt.selectConcCnslList",param);
		return rsList;
	}		


}
