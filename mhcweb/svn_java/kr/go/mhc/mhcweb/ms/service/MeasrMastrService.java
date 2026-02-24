package kr.go.mhc.mhcweb.ms.service;

import java.util.List;
import java.util.Map;

public interface MeasrMastrService {

	
	public Map<String,Object> getMeasrSeq() throws Exception;
	
	public Map<String,Object> insertBloodPress(Map<String, Object> param) throws Exception;
	
	public Map<String,Object> insertBloodSugar(Map<String, Object> param) throws Exception;
	
	public Map<String,Object> selectMeasrJudge(Map<String, Object> param) throws Exception; 
	
	public int insertActDta(Map<String, Object> param) throws Exception;
	
	public int insertHRDta(Map<String, Object> param) throws Exception;
	
	public int insertBodyCompDta(Map<String, Object> param) throws Exception;
	
	public Map<String,Object> insertBodyComp(Map<String, Object> param) throws Exception;
	
	public int updatePairDeviceInfo(Map<String, Object> param) throws Exception;
	
	public String callProcActIns(Map<String, Object> param) throws Exception;
	
	public int mergeEquipInfo(Map<String, Object> param) throws Exception;
	
	public Map<String,Object> searchSerialNo(Map<String, Object> param) throws Exception;
	
}
