package kr.or.khealth.smhc.smhcweb.cm.service;

import java.util.List;
import java.util.Map;
import kr.or.khealth.smhc.common.util.PushMessageUtil;


public interface PushService {

	public void updateToken(Map<String, Object> param) throws Exception;
	
	public Map<String, Object> getSndSn() throws Exception;
	
	public int insertPushHis(Map<String, Object> param) throws Exception;
	
	public int updatePushHis(Map<String, Object> param) throws Exception;
	
	public int insertPushHisTopic(Map<String, Object> param) throws Exception;
	
	public List<Map<String, Object>> selectTokenList(Map<String, Object> param) throws Exception;
	
	public List<Map<String, Object>> selectSendList(Map<String, Object> param) throws Exception;
	
	public Map<String, String> selectGrpUserList(Map<String, Object> param) throws Exception;
	
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
	
	public List<Map<String, Object>> selectExcsSchSendList(Map<String, Object> param) throws Exception;

	public void insertResvrtPushMas(Map<String, Object> param) throws Exception;
	
	public void insertResvrtPushHis(Map<String, Object> param) throws Exception;
	
	public String selectOrgCd(String userId) throws Exception;

	public int updatePushHisSchedulerForMulti(Map<String, Object> threadMap) throws Exception;

	public String selectPushMsgSeq() throws Exception;

	public Map<String, Object> getPushTemplete(Map<String, Object> pushTempMap) throws Exception;

	public void insertFcstResvrtPushHis(Map<String, Object> insPushHisMap) throws Exception;

	public List<Map<String, Object>> getFcstPushSndSnList(int fcstSn) throws Exception;

	public int selectPushSendHistCnt(String pushSndSn) throws Exception;

	public void updatePushSendCnt(Map<String, Object> updPushMasMap) throws Exception;
}
