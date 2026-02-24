package kr.go.mhc.mhcapp.sv.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.go.mhc.common.DMultiEgovAbstractMapper;

@Repository("mhcapp.sv.AfterServeyDAO")
public class AfterServeyDAO extends DMultiEgovAbstractMapper{
	
	public Map<String, Object> insertafterServeyAnswr(Map<String, Object> param) throws Exception {
		Map<String, Object> rsMap = selectOne("mhcapp.sv.afterServey.selectAfterServeySnSeq", param);
		param.put("answrSn", rsMap.get("ANSWRSN"));
		insert("mhcapp.sv.afterServey.insertafterServeyAnswr", param);
		return rsMap;  
	}
	
	public List<Map<String, String>> selectAfterServeyCodeList(Map<String, Object> param) throws Exception {
		List<Map<String,String>> rsList = selectList("mhcapp.sv.afterServey.selectAfterServeyCodeList", param);	
		return rsList;  
	}
	
	public List<Map<String, String>> selectAfterServeyList(Map<String, Object> param) throws Exception {

		List<Map<String,String>> rsList = selectList("mhcapp.sv.afterServey.selectAfterServeyList", param);
		return rsList;  
	}
	
public Integer updateAfterServeyAnswr(Map<String, Object> param) throws Exception {
		
		int rsInt = update("mhcapp.sv.afterServey.updateAfterServeyAnswr", param);

		return rsInt;  
	}
}
