package kr.or.khealth.smhc.smhcweb.sv.service.impl;

import java.util.List;
import java.util.Map;
import kr.or.khealth.smhc.common.DMultiEgovAbstractMapper;
import org.springframework.stereotype.Repository;

@Repository("web.sv.PushSendDAO")
public class PushSendDAO extends DMultiEgovAbstractMapper{

	public List<Map<String, String>> getNoticeSttusList(Map<String, Object> param)throws Exception {
		// TODO Auto-generated method stub
		List<Map<String, String>> rsList = selectList("smhc.web.sv.pushsend.selectNoticeSttusList",param);
		
		return rsList;
	}

	public List<Map<String, String>> getReceiverList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String, String>> rsList = selectList("smhc.web.sv.pushsend.selectReceiverList",param);
	
		return rsList;
	}

	public Map<String, Object> getSttusCnt(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> rsMap = selectOne("smhc.web.sv.pushsend.selectNoticeSttusList",param);	
		return rsMap;
	}

	public List<Map<String, String>> getPushNoticetrgterList(Map<String, Object> param) throws Exception{
		// TODO Auto-generated method stub
		List<Map<String, String>> rsList = selectList("smhc.web.sv.pushsend.selectPushNoticetrgterList",param);
		
		return rsList;
	}

	public List<Map<String, String>> getTodayManualSendList(Map<String, Object> param)throws Exception {
		// TODO Auto-generated method stub
		List<Map<String, String>> rsList = selectList("smhc.web.sv.pushsend.selectTodayManualSendList",param);
		
		return rsList;
	}
	
	public List<Map<String, String>> manualLinkPageList(Map<String, Object> param)throws Exception {
		// TODO Auto-generated method stub
		List<Map<String, String>> rsList = selectList("smhc.web.sv.pushsend.manualLinkPageList",param);
		
		return rsList;
	}

	public List<Map<String, String>> getTrgterGroup(Map<String, Object> param) throws Exception{
		// TODO Auto-generated method stub
		List<Map<String, String>> rsList = selectList("smhc.web.sv.pushsend.selectTrgterGroup",param);
		
		return rsList;
	}

	public String getPushMsgSeq(Map<String, Object> param) throws Exception{
		// TODO Auto-generated method stub
		String rsMap = selectOne("smhc.web.sv.pushsend.selectPushMsgSeq",param);	
		return rsMap;
	}

	public List<Map<String, String>> getAutoPushMsgList(Map<String, Object> param) throws Exception{
		// TODO Auto-generated method stub
		List<Map<String, String>> rsList = selectList("smhc.web.sv.pushsend.selectAutoPushMsgList",param);
		
		return rsList;
	}

	public void saveAutoPushMsg(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		update("smhc.web.sv.pushsend.saveAutoPushMsg", param);
	}

	public List<Map<String, String>> getDetailList(Map<String, Object> param) throws Exception{
		// TODO Auto-generated method stub
		List<Map<String, String>> rsList = selectList("mhc.web.sv.noticesetmng.selectDetailList",param);

		return rsList;
	}
	
	public int selectNoticeSndCount(Map<String, Object> param) throws Exception {
		int rsInt = selectOne("smhc.web.sv.pushsend.selectNoticeSndCount", param);
		return rsInt;
	}
	
	public List<Map<String, String>> selectNoticeSndSttus(Map<String, Object> param) throws Exception{
		List<Map<String, String>> rsList = selectList("smhc.web.sv.pushsend.selectNoticeSndSttus",param);
		return rsList;
	}
	
	public int selectTrgterNoticeSndCount(Map<String, Object> param) throws Exception {
		int rsInt = selectOne("smhc.web.sv.pushsend.selectTrgterNoticeSndCount", param);
		return rsInt;
	}
	
	public List<Map<String, String>> selectTrgterNoticeSndSttus(Map<String, Object> param) throws Exception{
		List<Map<String, String>> rsList = selectList("smhc.web.sv.pushsend.selectTrgterNoticeSndSttus",param);
		return rsList;
	}
	
	public Map<String, String> checkTrgterJoinInfo(Map<String, Object> param) throws Exception{
		// TODO Auto-generated method stub
		Map<String, String> rsMap = selectOne("mhc.web.sv.noticesetmng.checkTrgterJoinInfo",param);
		
		return rsMap;
	}

}
