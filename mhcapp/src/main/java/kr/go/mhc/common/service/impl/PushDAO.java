package kr.go.mhc.common.service.impl;

import java.util.List;
import java.util.Map;

import kr.go.mhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;

@Repository("pushDAO")
public class PushDAO extends DMultiEgovAbstractMapper{
	
	public int updateToken(Map<String, Object> param) throws Exception {
		
		return update("mhc.common.push.updateToken", param);			  
	}
	
	public int updatePushStatus(Map<String, Object> param) throws Exception {
		
		return update("mhc.common.push.updatePushStatus", param);			  
	}
	
	public int updateSndSnNum(Map<String, Object> param) throws Exception {
		
		return update("mhc.common.push.updateSndSnNum", param);			  
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
	
	//방문예정 목록 조회
	public List<Map<String, Object>> selectVisitAllList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.common.push.selectVisitAllList", param);
		return rsList;
	}
}
