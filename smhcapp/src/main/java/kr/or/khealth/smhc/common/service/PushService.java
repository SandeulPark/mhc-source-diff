package kr.or.khealth.smhc.common.service;

import java.util.List;
import java.util.Map;

public interface PushService {

	public void updateToken(Map<String, Object> param) throws Exception;
	
	public int updatePushStatus(Map<String, Object> param) throws Exception;
	
	public int pushUpdateCnfm(Map<String, Object> param) throws Exception;
	
	public List<Map<String,Object>> selectAdmTokenList(Map<String,Object> param) throws Exception;
	
	public int insertPushHis(Map<String,Object>param) throws Exception;
	
	public int updatePushHis(Map<String,Object>param) throws Exception;
	
	public List<Map<String,Object>> selectSendList(Map<String,Object> param) throws Exception;
}
