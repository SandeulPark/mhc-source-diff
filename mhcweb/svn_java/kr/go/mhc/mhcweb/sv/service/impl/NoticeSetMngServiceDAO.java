package kr.go.mhc.mhcweb.sv.service.impl;

import java.util.List;
import java.util.Map;

import kr.go.mhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;

@Repository("web.sv.NoticeSetMngServiceDAO")
public class NoticeSetMngServiceDAO extends DMultiEgovAbstractMapper{

	public List<Map<String, String>> getNoticeSttusList(Map<String, Object> param)throws Exception {
		// TODO Auto-generated method stub
		List<Map<String, String>> rsList = selectList("mhc.web.sv.noticesetmng.selectNoticeSttusList",param);
		
		return rsList;
	}

	public List<Map<String, String>> getReceiverList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String, String>> rsList = selectList("mhc.web.sv.noticesetmng.selectReceiverList",param);
	
		return rsList;
	}

	public Map<String, Object> getSttusCnt(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> rsMap = selectOne("mhc.web.sv.noticesetmng.selectNoticeSttusList",param);	
		return rsMap;
	}

	public List<Map<String, String>> getPushNoticetrgterList(Map<String, Object> param) throws Exception{
		// TODO Auto-generated method stub
		List<Map<String, String>> rsList = selectList("mhc.web.sv.noticesetmng.selectPushNoticetrgterList",param);
		
		return rsList;
	}

	public List<Map<String, String>> getTodayManualSendList(Map<String, Object> param)throws Exception {
		// TODO Auto-generated method stub
		List<Map<String, String>> rsList = selectList("mhc.web.sv.noticesetmng.selectTodayManualSendList",param);
		
		return rsList;
	}
	
	public List<Map<String, String>> manualLinkPageList(Map<String, Object> param)throws Exception {
		// TODO Auto-generated method stub
		List<Map<String, String>> rsList = selectList("mhc.web.sv.noticesetmng.manualLinkPageList",param);
		
		return rsList;
	}

	public List<Map<String, String>> getTrgterGroup(Map<String, Object> param) throws Exception{
		// TODO Auto-generated method stub
		List<Map<String, String>> rsList = selectList("mhc.web.sv.noticesetmng.selectTrgterGroup",param);
		
		return rsList;
	}

	public String getPushMsgSeq(Map<String, Object> param) throws Exception{
		// TODO Auto-generated method stub
		String rsMap = selectOne("mhc.web.sv.noticesetmng.selectPushMsgSeq",param);	
		return rsMap;
	}

	public List<Map<String, String>> getAutoPushMsgList(Map<String, Object> param) throws Exception{
		// TODO Auto-generated method stub
		List<Map<String, String>> rsList = selectList("mhc.web.sv.noticesetmng.selectAutoPushMsgList",param);
		
		return rsList;
	}

	public void saveAutoPushMsg(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		update("mhc.web.sv.noticesetmng.saveAutoPushMsg", param);
	}

	public List<Map<String, String>> getDetailList(Map<String, Object> param) throws Exception{
		// TODO Auto-generated method stub
		List<Map<String, String>> rsList = selectList("mhc.web.sv.noticesetmng.selectDetailList",param);

		return rsList;
	}
	
	public List<Map<String, String>> selectTrgterNoticeSndSttus(Map<String, Object> param) throws Exception{
		// TODO Auto-generated method stub
		List<Map<String, String>> rsList = selectList("mhc.web.sv.noticesetmng.selectTrgterNoticeSndSttus",param);
		
		return rsList;
	}
	
	public Map<String, String> checkTrgterJoinInfo(Map<String, Object> param) throws Exception{
		// TODO Auto-generated method stub
		Map<String, String> rsMap = selectOne("mhc.web.sv.noticesetmng.checkTrgterJoinInfo",param);
		
		return rsMap;
	}

	public int getNoticeSttusListCount(Map<String, Object> param) {
		int rsList = selectOne("mhc.web.sv.noticesetmng.selectNoticeSttusListCount",param);
		return rsList;
	}
	
	public int deletePushInfoHis(Map<String, Object> param) {
		int rsList = delete("mhc.web.sv.noticesetmng.deletePushInfoHis",param);
		return rsList;
	}
	
	public int updatePushInfoHis(Map<String, Object> param) {
		int rsList = update("mhc.web.sv.noticesetmng.updatePushInfoHis",param);
		return rsList;
	}
	
	public int insertPushInfoHis(Map<String, Object> param) {
		return insert("mhc.web.sv.noticesetmng.insertPushInfoHis",param);
	}
	
	public List<Map<String, String>> selectPushInfoHisList(Map<String, Object> param) throws Exception{
		// TODO Auto-generated method stub
		List<Map<String, String>> rsList = selectList("mhc.web.sv.noticesetmng.selectPushInfoHisList",param);
		
		return rsList;
	}
}
