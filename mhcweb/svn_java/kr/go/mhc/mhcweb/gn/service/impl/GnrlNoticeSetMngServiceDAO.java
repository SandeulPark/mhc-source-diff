package kr.go.mhc.mhcweb.gn.service.impl;

import java.util.List;
import java.util.Map;

import kr.go.mhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;

@Repository("web.gn.GnrlNoticeSetMngServiceDAO")
public class GnrlNoticeSetMngServiceDAO extends DMultiEgovAbstractMapper{

	public List<Map<String, String>> getTrgterGroup(Map<String, Object> param) {
		// TODO Auto-generated method stub
		List<Map<String, String>> rsList = selectList("mhc.web.gn.gnrlnoticesetmng.selectTrgterGroup",param);
		
		return rsList;
	}

	public List<Map<String, String>> manualLinkPageList(Map<String, Object> param) {
		// TODO Auto-generated method stub
		List<Map<String, String>> rsList = selectList("mhc.web.gn.gnrlnoticesetmng.manualLinkPageList",param);
		
		return rsList;
	}

	public List<Map<String, String>> getPushNoticetrgterList(Map<String, Object> param) {
		// TODO Auto-generated method stub
		List<Map<String, String>> rsList = selectList("mhc.web.gn.gnrlnoticesetmng.selectPushNoticetrgterList",param);
		
		return rsList;
	}

	public List<Map<String, String>> getNoticeSttusList(Map<String, Object> param) {
		// TODO Auto-generated method stub
		List<Map<String, String>> rsList = selectList("mhc.web.gn.gnrlnoticesetmng.selectNoticeSttusList",param);
		
		return rsList;
	}

	public List<Map<String, String>> getTodayManualSendList(Map<String, Object> param) {
		// TODO Auto-generated method stub
		List<Map<String, String>> rsList = selectList("mhc.web.gn.gnrlnoticesetmng.selectTodayManualSendList",param);
		
		return rsList;
	}

	public List<Map<String, String>> getReceiverList(Map<String, Object> param) {
		// TODO Auto-generated method stub
		List<Map<String, String>> rsList = selectList("mhc.web.gn.gnrlnoticesetmng.selectReceiverList",param);
		
		return rsList;
	}

	public Map<String, Object> getSttusCnt(Map<String, Object> param) {
		// TODO Auto-generated method stub
		Map<String,Object> rsMap = selectOne("mhc.web.gn.gnrlnoticesetmng.selectNoticeSttusList",param);	
		return rsMap;
	}

	public List<Map<String, String>> selectTrgterNoticeSndSttus(Map<String, Object> param) {
		// TODO Auto-generated method stub
		List<Map<String, String>> rsList = selectList("mhc.web.gn.gnrlnoticesetmng.selectTrgterNoticeSndSttus",param);
		
		return rsList;
	}

	public Map<String, String> checkTrgterJoinInfo(Map<String, Object> param) {
		// TODO Auto-generated method stub
		Map<String, String> rsMap = selectOne("mhc.web.gn.gnrlnoticesetmng.checkTrgterJoinInfo",param);
		
		return rsMap;
	}

	

}
