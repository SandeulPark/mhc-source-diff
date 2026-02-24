package kr.go.mhc.mhcweb.tm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.go.mhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;

@Repository("web.tm.TmDAO")
public class TmDAO extends DMultiEgovAbstractMapper{

	
	public List<Map<String, String>> getCntnsDtaNurt(Map<String, Object> param) throws Exception {
		List<Map<String,String>> rsList = selectList("mhc.web.tm.temp.getCntnsDtaNurt", param);	
		return rsList;  
	}
	
	public void updateCntnsDtaNurt(Map<String, Object> param) throws Exception {
		update("mhc.web.tm.temp.updateCntnsDtaNurt", param);
	}

	
	public List<Map<String, String>> getCntnsDtaSend(Map<String, Object> param) throws Exception {
		List<Map<String,String>> rsList = selectList("mhc.web.tm.temp.getCntnsDtaSend", param);	
		return rsList;  
	}
	
	public void updateCntnsDtaSend(Map<String, Object> param) throws Exception {
		update("mhc.web.tm.temp.updateCntnsDtaSend", param);
	}
	
}
