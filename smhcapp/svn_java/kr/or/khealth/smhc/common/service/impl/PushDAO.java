package kr.or.khealth.smhc.common.service.impl;

import java.util.List;
import java.util.Map;



import kr.or.khealth.smhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;

@Repository("pushDAO")
public class PushDAO extends DMultiEgovAbstractMapper{
	
	public int updateToken(Map<String, Object> param) throws Exception {
		
		return update("mhc.common.push.updateToken", param);			  
	}
	
	public int updatePushStatus(Map<String, Object> param) throws Exception {
		
		return update("mhc.common.push.updatePushStatus", param);			  
	}
	
	public int pushUpdateCnfm(Map<String, Object> param) throws Exception {
		
		return update("mhc.common.push.pushUpdateCnfm", param);			  
	}
	
	public List<Map<String, Object>> selectAdmTokenList(Map<String, Object> param) {
		return selectList("mhc.common.push.selectAdmTokenList", param);
	}
	
	public int insertPushMas(Map<String, Object> param) throws Exception {
		return update("mhc.common.push.insertPushMas", param);
	}
	
	public int insertPushHis(Map<String, Object> param) throws Exception {
		return update("mhc.common.push.insertPushHis", param);
	}
	
	
	public int updatePushMas(Map<String, Object> param) throws Exception {
		return update("mhc.common.push.updatePushMas", param);
	}
	
	public int updatePushHis(Map<String, Object> param) throws Exception {
		return update("mhc.common.push.updatePushHis", param);
	}
	
	public List<Map<String, Object>> selectSendList(Map<String, Object> param) throws Exception {		
		return selectList("mhc.common.push.selectSendList", param);		
	}
}
