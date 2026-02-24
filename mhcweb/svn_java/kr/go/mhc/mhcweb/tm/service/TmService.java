package kr.go.mhc.mhcweb.tm.service;

import java.util.List;
import java.util.Map;

public interface TmService {

	public List<Map<String, String>> getCntnsDtaNurt(Map<String, Object> param) throws Exception;
	
	public void updateCntnsDtaNurt(Map<String, Object> param) throws Exception;
	
	
	public List<Map<String, String>> getCntnsDtaSend(Map<String, Object> param) throws Exception;
	
	public void updateCntnsDtaSend(Map<String, Object> param) throws Exception;
}
