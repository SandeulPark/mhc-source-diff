package kr.or.khealth.smhc.smhcweb.cm.service.impl;

import java.util.List;
import java.util.Map;

import kr.or.khealth.smhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;

@Repository("pushDAO")
public class PushDAO extends DMultiEgovAbstractMapper{
	
	public List<Map<String, Object>> selectTokenList(Map<String, Object> param) throws Exception {		
		List<Map<String,Object>> rsList = selectList("smhc.web.cm.push.selectTokenList", param);		
		return rsList;  
	}
	
	public List<Map<String, Object>> topicUserList(Map<String, Object> param) throws Exception {		
		List<Map<String,Object>> rsList = selectList("smhc.web.cm.push.topicUserList", param);		
		return rsList;  
	}
	
	public List<Map<String, Object>> selectSendList(Map<String, Object> param) throws Exception {		
		List<Map<String,Object>> rsList = selectList("smhc.web.cm.push.selectSendList", param);		
		return rsList;  
	}
	
	public Map<String, String> selectGrpUserList(Map<String, Object> param) throws Exception {		
		Map<String,String> rsList = selectOne("smhc.web.cm.push.selectGrpUserList", param);		
		return rsList;  
	}
	
	public Map<String, String> getPushSndYn(Map<String, Object> param) throws Exception {		
		Map<String,String> rsList = selectOne("smhc.web.cm.push.getPushSndYn", param);		
		return rsList;  
	}
	
	public void setPushSndYn(Map<String, Object> param) throws Exception {		
		update("smhc.web.cm.push.setPushSndYn", param);		
	}
	
	public void setNoticePushSndYn(Map<String, Object> param) throws Exception{
		update("smhc.web.cm.push.setNoticePushSndYn", param);
	}
	
	public int updateToken(Map<String, Object> param) throws Exception {
		
		return update("smhc.web.cm.push.updateToken", param);			  
	}
	
	public Map<String,Object> getSndDn() throws Exception {

		return selectOne("smhc.web.cm.push.getSndDn");
	}
	
	public Map<String, Object> selectPushMas(Map<String, Object> param) throws Exception{
		Map<String, Object >rsMap = selectOne("smhc.web.cm.push.selectPushMas", param);
		return rsMap;
	}
	
	public void deletePushMasHist(Map<String, Object> param) throws Exception{
		delete("smhc.web.cm.push.deletePushMasHist", param);
	}
	
	public int insertPushMas(Map<String, Object> param) throws Exception {
		
		return update("smhc.web.cm.push.insertPushMas", param);
	}
	
	public int insertPushHis(Map<String, Object> param) throws Exception {
		
		return update("smhc.web.cm.push.insertPushHis", param);
	}
	
	public int insertPushHisTopic(Map<String, Object> param) throws Exception {
		
		return insert("smhc.web.cm.push.insertPushHisTopic", param);			  
	}
	
	public int updatePushMas(Map<String, Object> param) throws Exception {
		
		return update("smhc.web.cm.push.updatePushMas", param);
	}
	
	public int updatePushHis(Map<String, Object> param) throws Exception {
		
		return update("smhc.web.cm.push.updatePushHis", param);
	}
	
	public int updatePushAllHis(Map<String, Object> param) throws Exception {
		
		return update("smhc.web.cm.push.updatePushAllHis", param);
	}

	public Map<String, String> getPushSndYnNotice(Map<String, Object> param) throws Exception {		
		Map<String,String> rsList = selectOne("smhc.web.cm.push.getPushSndYnNotice", param);		
		return rsList;
	}
	
	public Map<String, String> selectGrpList(Map<String, Object> param) throws Exception {
		Map<String,String> rsList = selectOne("smhc.web.cm.push.selectGrpList", param);
		return rsList;
	}
	
	public int updatePushStatus(Map<String, Object> param) throws Exception {
		
		return update("smhc.web.cm.push.updatePushStatus", param);			  
	}
	
	public int pushUpdateCnfm(Map<String, Object> param) throws Exception {
		
		return update("smhc.web.cm.push.pushUpdateCnfm", param);			  
	}
	
	public int pushChatStatus(Map<String, Object> param) throws Exception{
		return update("smhc.web.cm.push.pushChatStatus", param);
	}
	
	public List<Map<String,Object>> selectPushCnslCnt(Map<String,Object> param) throws Exception{
		// TODO Auto-generated method stub
		List<Map<String,Object>> rsList = selectList("smhc.web.cm.push.selectPushCnslCnt", param);
		return rsList;
	}
	
	//푸시 링크 대상자 리스트 조회
	public List<Map<String, Object>> selectPushTgrList(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("smhc.web.cm.push.selectPushTgrList", param);
		return rsList;
	}
	
	//모바일 공지 푸시 대상자 목록 조회
	public List<Map<String, Object>> selectNoticeUserList(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("smhc.web.cm.push.selectNoticeUserList", param);
		return rsList;
	}
	
	//푸시 대상자 정보 및 배지 카운트 조회
	public List<Map<String, Object>> selectPushInfoBadgeList(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("smhc.web.cm.push.selectPushInfoBadgeList", param);
		return rsList;
	}
	
	public List<Map<String, Object>> selectExcsSchSendList(Map<String, Object> param) throws Exception {		
		List<Map<String,Object>> rsList = selectList("smhc.web.cm.push.selectExcsSchSendList", param);		
		return rsList;  
	}
	
	public void insertResvrtPushMas(Map<String, Object> param) throws Exception{
		insert("smhc.web.cm.push.insertPushMas", param);		
	}
	
	public void insertResvrtPushHis(Map<String, Object> param) throws Exception{
		insert("smhc.web.cm.push.insertPushHis", param);		
	}
	
	public String selectOrgCd(String userId) throws Exception {
		return selectOne("smhc.web.cm.push.selectOrgCd", userId);
		
	}

	public List<Map<String, Object>> selectGrpUserList2(Map<String, Object> param) {		
		List<Map<String,Object>> rsList = selectList("smhc.web.cm.push.selectGrpUserList2", param);		
		return rsList;  
	}

	public String selectPushMsgSeq() {
		// TODO Auto-generated method stub
		return selectOne("smhc.web.cm.push.selectPushMsgSeq");
	}

	public Map<String, Object> getPushTemplete(Map<String, Object> pushTempMap) {
		// TODO Auto-generated method stub
		return selectOne("smhc.web.cm.push.getPushTemplete", pushTempMap);
	}

	public void insertFcstResvrtPushHis(Map<String, Object> insPushHisMap) {
		// TODO Auto-generated method stub
		insert("smhc.web.cm.push.insertFcstResvrtPushHis", insPushHisMap);
	}

	public List<Map<String, Object>> getFcstPushSndSnList(int fcstSn) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> rsList = selectList("smhc.web.cm.push.getFcstPushSndSnList", fcstSn);	
		return rsList;
	}

	public int selectPushSendHistCnt(String pushSndSn) {
		// TODO Auto-generated method stub
		return selectOne("smhc.web.cm.push.selectPushSendHistCnt", pushSndSn);
	}

	public void updatePushSendCnt(Map<String, Object> updPushMasMap) {
		// TODO Auto-generated method stub
		update("smhc.web.cm.push.updatePushSendCnt", updPushMasMap);
	}
}