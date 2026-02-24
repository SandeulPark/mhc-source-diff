package kr.or.khealth.smhc.smhcweb.tg.service;

import java.util.List;
import java.util.Map;

public interface SeniorMissionSetService {

	public List<Map<String, Object>> selectMissionSetList(Map<String, Object> param) throws Exception;	
	
	public int insertMissionSet(Map<String, Object> param) throws Exception;
	
	public List<Map<String, Object>> selectUserMissionList(Map<String, Object> param) throws Exception;	
	
	public List<Map<String, Object>> selectUserMissionChk(Map<String, Object> param) throws Exception;	
	
	public String selectUserSvcMngt(Map<String, Object> param) throws Exception;
	
	public int insertSeniorDrugInfo(Map<String, Object> param) throws Exception;
	
	
}
