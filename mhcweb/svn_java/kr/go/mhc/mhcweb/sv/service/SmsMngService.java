package kr.go.mhc.mhcweb.sv.service;

import java.util.List;
import java.util.Map;

public interface SmsMngService {
	
	public List<Map<String, String>> getSmsSendTrgterList(Map<String, Object> param) throws Exception;
	
	public List<Map<String, String>> getSmsSendManagerList(Map<String, Object> param) throws Exception;
	
	public List<Map<String, String>> getSmsSendTodayList(Map<String, Object> param) throws Exception;
	
	public void saveSmsMaster(Map<String, Object> param) throws Exception;
	
	public void saveSmsHis(Map<String, Object> param) throws Exception;
	
	public List<Map<String, String>> getSmsSendList(Map<String, Object> param) throws Exception;
	
	public List<Map<String, String>> getSmsSendDetailList(Map<String, Object> param) throws Exception;
	
	public List<Map<String, String>> getSmsTrgterSttus(Map<String,Object> param) throws Exception;
	
	public int getSmsMsgId() throws Exception;
	
	public int getMmsMsgId() throws Exception;
	
	public void saveSmsAgent(Map<String, Object> param) throws Exception;
	
	public void saveMmsAgent(Map<String, Object> param) throws Exception;
	
	public Map<String, String> getSmsCharge(Map<String, Object> param) throws Exception;
}
