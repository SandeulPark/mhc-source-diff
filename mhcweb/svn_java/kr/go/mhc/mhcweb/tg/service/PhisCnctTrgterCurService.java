package kr.go.mhc.mhcweb.tg.service;

import java.util.List;
import java.util.Map;

public interface PhisCnctTrgterCurService {

	public List<Map<String, String>> phisCnctTrgterCurCount(Map<String, Object> param)throws Exception;
	
	public List<Map<String, String>> phisCnctTrgterCurTrgterList(Map<String, Object> param)throws Exception;
	
	public List<Map<String, String>> phisCnctTrgterCurPopTrgterExamList(Map<String, Object> param)throws Exception;
	
}
