package kr.go.mhc.mhcweb.sv.service.impl;

import java.util.List;
import java.util.Map;

import kr.go.mhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;

@Repository("web.sv.SmsMngDAO")
public class SmsMngDAO extends DMultiEgovAbstractMapper {
	public List<Map<String, String>> getSmsSendTrgterList(Map<String, Object> param) throws Exception {
		List<Map<String, String>> rsList = selectList("mhc.web.sv.smsmng.selectSmsSendTrgterList", param);
		return rsList;
	}
	
	public List<Map<String, String>> getSmsSendManagerList(Map<String, Object> param) throws Exception {
		List<Map<String, String>> rsList = selectList("mhc.web.sv.smsmng.selectSmsSendManagerList", param);
		return rsList;
	}
	
	public List<Map<String, String>> getSmsSendTodayList(Map<String, Object> param) throws Exception {
		List<Map<String, String>> rsList = selectList("mhc.web.sv.smsmng.selectSmsSendTodayList", param);
		return rsList;
	}
	
	public void saveSmsMaster(Map<String, Object> param) throws Exception {
		insert("mhc.web.sv.smsmng.insertSmsMaster", param);
	}
	
	public void saveSmsHis(Map<String, Object> param) throws Exception {
		insert("mhc.web.sv.smsmng.insertSmsHis", param);
	}
	
	public List<Map<String, String>> getSmsSendList(Map<String, Object> param) throws Exception {
		List<Map<String, String>> rsList = selectList("mhc.web.sv.smsmng.selectSmsSendList", param);
		return rsList;
	}
	
	public List<Map<String, String>> getSmsSendDetailList(Map<String, Object> param) throws Exception {
		List<Map<String, String>> rsList = selectList("mhc.web.sv.smsmng.selectSmsSendDetailList", param);
		return rsList;
	}

	public List<Map<String, String>> getSmsTrgterSttus(Map<String, Object> param) throws Exception {
		List<Map<String, String>> rsList = selectList("mhc.web.sv.smsmng.selectSmsTrgterSttus", param);
		return rsList;
	}
	
	public int getSmsMsgId() throws Exception {
		return selectOne("mhc.web.sv.smsmng.selectSmsMsgId");
	}
	
	public int getMmsMsgId() throws Exception {
		return selectOne("mhc.web.sv.smsmng.selectMmsMsgId");
	}
	
	public void saveSmsAgent(Map<String, Object> param) throws Exception {
		insert("mhc.web.sv.smsmng.insertSmsAgent", param);
	}
	
	public void saveMmsAgent(Map<String, Object> param) throws Exception {
		insert("mhc.web.sv.smsmng.insertMmsAgent", param);
	}
	
	public Map<String, String> getSmsCharge(Map<String, Object> param) throws Exception {
		return selectOne("mhc.web.sv.smsmng.selectSmsCharge");
	}
}
