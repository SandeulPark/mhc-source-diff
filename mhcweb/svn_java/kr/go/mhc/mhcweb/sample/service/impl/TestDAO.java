package kr.go.mhc.mhcweb.sample.service.impl;

import java.util.List;
import java.util.Map;

import kr.go.mhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;

@Repository("testDAO")
public class TestDAO extends DMultiEgovAbstractMapper{
	
	public List<Map<String, Object>> getList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,Object>> rsList = selectList("mhc.mhcweb.list", param);	
		return rsList;  
	}
	
	public Map<String, Object> getDetail(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> rsMap = selectOne("mhc.mhcweb.detail", param);
		return rsMap;  
	}	
	
	public void insert(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		insert("mhc.mhcweb.insert", param);
	}
	
	public int update(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		int rsInt = update("mhc.mhcweb.update", param);
		return rsInt;
	}
	
	public int delete(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		int rsInt = delete("mhc.mhcweb.delete", param);
		return rsInt;
	}	
}
