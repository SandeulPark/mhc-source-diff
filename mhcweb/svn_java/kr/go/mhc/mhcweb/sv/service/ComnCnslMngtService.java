package kr.go.mhc.mhcweb.sv.service;

import java.util.List;
import java.util.Map;


public interface ComnCnslMngtService {

	public List<Map<String, String>> getCnslNonCompList(Map<String, Object> param) throws Exception;
	
	public List<Map<String, String>> getCnslCompList(Map<String, Object> param) throws Exception;
	
	public List<Map<String, String>> getRealTimeCnslReqList(Map<String, Object> param) throws Exception;
	
	public List<Map<String, Object>> selectAllCnslList(Map<String, Object> param) throws Exception;
}
