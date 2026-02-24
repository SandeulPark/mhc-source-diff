package kr.or.khealth.smhc.smhcweb.sv.service;

import java.util.List;
import java.util.Map;


public interface PushSendService {

	public List<Map<String, String>> getNoticeSttusList(Map<String, Object> param) throws Exception;

	public List<Map<String, String>> getReceiverList(Map<String, Object> param)throws Exception;

	public Map<String, Object> getSttusCnt(Map<String, Object> param)throws Exception;

	public List<Map<String, String>> getPushNoticetrgterList(Map<String, Object> param)throws Exception;

	public List<Map<String, String>> getTodayManualSendList(Map<String, Object> param)throws Exception;
	
	public List<Map<String, String>> manualLinkPageList(Map<String, Object> param)throws Exception;

	public List<Map<String, String>> getTrgterGroup(Map<String, Object> param)throws Exception;

	public String getPushMsgSeq(Map<String, Object> param) throws Exception;

	public List<Map<String, String>> getAutoPushMsgList(Map<String, Object> param)throws Exception;

	public void saveAutoPushMsg(Map<String, Object> param)throws Exception;

	public List<Map<String, String>> getDetailList(Map<String, Object> param)throws Exception;
	
	public int selectNoticeSndCount(Map<String, Object> param) throws Exception;
	
	public List<Map<String, String>> selectNoticeSndSttus(Map<String, Object> param)throws Exception;
	
	public int selectTrgterNoticeSndCount(Map<String, Object> param) throws Exception;
	
	public List<Map<String, String>> selectTrgterNoticeSndSttus(Map<String, Object> param)throws Exception;
	
	public Map<String, String> checkTrgterJoinInfo(Map<String, Object> param)throws Exception;

}
