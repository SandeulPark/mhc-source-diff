package kr.go.mhc.mhcweb.sv.service.impl;

import java.util.List;
import java.util.Map;

import kr.go.mhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;

@Repository("web.sv.ComnCnslMngtServiceDAO")
public class ComnCnslMngtServiceDAO extends DMultiEgovAbstractMapper{

	
	public List<Map<String, String>> getCnslNonCompList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String, String>> rsList = selectList("mhc.web.sv.comncnslmngt.selectCnslNonCompList",param);
		return rsList;
	}	
	
	public List<Map<String, String>> getCnslCompList(Map<String, Object> param) throws Exception{
		// TODO Auto-generated method stub
		List<Map<String, String>> rsList = selectList("mhc.web.sv.comncnslmngt.selectCnslCompList",param);
		return rsList;
	}		

	public List<Map<String, String>> getRealTimeCnslReqList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String, String>> rsList = selectList("mhc.web.sv.comncnslmngt.selectRealTimeCnslReqList",param);
		return rsList;
	}	

	public List<Map<String, Object>> selectAllCnslList(Map<String, Object> param) throws Exception{
		// TODO Auto-generated method stub
		List<Map<String, Object>> rsList = selectList("mhc.web.sv.comncnslmngt.selectAllCnslList", param);
		return rsList;
	}


}
