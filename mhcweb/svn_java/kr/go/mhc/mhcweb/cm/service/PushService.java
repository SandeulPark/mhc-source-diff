package kr.go.mhc.mhcweb.cm.service;

import java.util.List;
import java.util.Map;

import kr.go.mhc.common.util.PushMessageUtil;

public interface PushService {

	public void updateToken(Map<String, Object> param) throws Exception;
	
	public Map<String, Object> getSndSn() throws Exception;
	
	public int insertPushHis(Map<String, Object> param) throws Exception;
	
	public int updatePushHis(Map<String, Object> param) throws Exception;
	
	public int insertPushHisServey(Map<String, Object> param) throws Exception;
		
	public int insertPushHisTopic(Map<String, Object> param) throws Exception;
	
	public List<Map<String, Object>> selectTokenList(Map<String, Object> param) throws Exception;
	
	public List<Map<String, Object>> selectSendList(Map<String, Object> param) throws Exception;
	
	public String selectGrpUserList(Map<String, Object> param) throws Exception;
	
	public Map<String, String> getPushSndYn(Map<String, Object> param) throws Exception;
	
	public void setPushSndYn(Map<String, Object> param) throws Exception;
	
	public void setNoticePushSndYn(Map<String, Object> param) throws Exception;
	
	public List<Map<String, Object>> topicUserList(Map<String, Object> param) throws Exception;
	
	public boolean sendPushData(PushMessageUtil pushMessageUtil, Map<String, Object> param) throws Exception;

	public Map<String, String> getPushSndYnNotice(Map<String, Object> param) throws Exception;
	
	public Map<String, String> selectGrpList(Map<String, Object> param) throws Exception;
	
	public int updatePushStatus(Map<String, Object> param) throws Exception;
	
	public int pushUpdateCnfm(Map<String, Object> param) throws Exception;
	
	public int pushChatStatus(Map<String, Object> param) throws Exception;
	
	public List<Map<String,Object>> selectPushCnslCnt(Map<String,Object> param) throws Exception;
	
	public List<Map<String,Object>> selectPushTgrList(Map<String,Object> param) throws Exception;
	
	public int updatePushHisScheduler(Map<String, Object> param) throws Exception;
	
	public List<Map<String, Object>> selectNoticeUserList(Map<String,Object> param) throws Exception;
	
	public List<Map<String, Object>> selectPushInfoBadgeList(Map<String,Object> param) throws Exception;
	
	public void deletePushMasHist(Map<String, Object> param) throws Exception;
	
	public Map<String, Object> selectPushMas(Map<String, Object> param) throws Exception;
	
	public Map<String, Object> selectPushMasServey(Map<String, Object> param) throws Exception;
	
	public List<Map<String, Object>> selectExcsSchSendList(Map<String, Object> param) throws Exception;

	public List<Map<String, Object>> selectVisitSchResvrtSendCnt() throws Exception;
	
	public List<Map<String, Object>> selectVisitSchResvrtSendList(String orgCd) throws Exception;
	
	public void insertResvrtPushMas(Map<String, Object> param) throws Exception;
	
	public void insertResvrtPushHis(Map<String, Object> param) throws Exception;
	
	public String selectOrgCd(String userId) throws Exception;

	public Map<String, String> selectMclasList(Map<String, Object> param);

	public Map<String, String> selectChronicList(Map<String, Object> param);

	public Map<String, Object> getPushSetInfo(Map<String, Object> param) throws Exception;

	public String selectOrgUserList(Map<String, Object> param) throws Exception;

}
