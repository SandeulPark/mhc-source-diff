package kr.go.mhc.mhcweb.sv.service;

import java.util.List;
import java.util.Map;


public interface ConcCnslMngtService {

	public List<Map<String, String>> getConcCnslList(Map<String, Object> param) throws Exception;
	
}
