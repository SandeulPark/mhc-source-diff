package kr.go.mhc.mhcapp.ms.service;

import java.util.List;
import java.util.Map;

public interface GnrlMeasrMastrService {

	public Map<String,Object> getMeasrSeq() throws Exception;
	
	public Map<String,Object> insertBloodPress(Map<String, Object> param) throws Exception;
	
	public Map<String,Object> updateBloodPress(Map<String, Object> param) throws Exception;
	
	public Map<String,Object> deleteBloodPress(Map<String, Object> param) throws Exception;
	
	public Map<String,Object> insertBloodSugar(Map<String, Object> param) throws Exception;
	
	public Map<String,Object> updateBloodSugar(Map<String, Object> param) throws Exception;
	
	public Map<String,Object> deleteBloodSugar(Map<String, Object> param) throws Exception;
	
	public Map<String,Object> selectMeasrJudge(Map<String, Object> param) throws Exception;

	// 체성분 추가
	public int insertBodyComp(Map<String, Object> param) throws Exception;

	public Map<String,Object> insertBodyCompManu(Map<String,Object> param) throws Exception;
	
	public Map<String,Object> deleteBodyCompManu(Map<String,Object> param) throws Exception;
	
	/********************************** OpenApi 적용 관련 START ***************************************/
	public Map<String,Object> checkLastData(Map<String, Object> param) throws Exception;
	
	public int insertAct(Map<String, Object> param) throws Exception;
	
	public Map<String,Object> deviceUserInfo(Map<String, Object> param) throws Exception;
}
