package kr.go.mhc.mhcweb.cm.service.impl;

import java.util.List;
import java.util.Map;

import kr.go.mhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;

@Repository("pushDAO")
public class PushDAO extends DMultiEgovAbstractMapper{
	
	public List<Map<String, Object>> selectTokenList(Map<String, Object> param) throws Exception {		
		List<Map<String,Object>> rsList = selectList("mhc.web.cm.push.selectTokenList", param);		
		return rsList;  
	}
	
	public List<Map<String, Object>> topicUserList(Map<String, Object> param) throws Exception {		
		List<Map<String,Object>> rsList = selectList("mhc.web.cm.push.topicUserList", param);		
		return rsList;  
	}
	
	public List<Map<String, Object>> selectSendList(Map<String, Object> param) throws Exception {		
		List<Map<String,Object>> rsList = selectList("mhc.web.cm.push.selectSendList", param);		
		return rsList;  
	}
		
	public String selectGrpUserList(Map<String, Object> param) throws Exception {		
		String rsStr = selectOne("mhc.web.cm.push.selectGrpUserList", param);		
		return rsStr;  
	}
	
	public Map<String, String> getPushSndYn(Map<String, Object> param) throws Exception {		
		Map<String,String> rsList = selectOne("mhc.web.cm.push.getPushSndYn", param);		
		return rsList;  
	}
	
	public void setPushSndYn(Map<String, Object> param) throws Exception {		
		update("mhc.web.cm.push.setPushSndYn", param);		
	}
	
	public void setNoticePushSndYn(Map<String, Object> param) throws Exception{
		update("mhc.web.cm.push.setNoticePushSndYn", param);
	}
	
	public int updateToken(Map<String, Object> param) throws Exception {
		
		return update("mhc.web.cm.push.updateToken", param);			  
	}
	
	public Map<String,Object> getSndDn() throws Exception {

		return selectOne("mhc.web.cm.push.getSndDn");
	}
	
	public Map<String, Object> selectPushMas(Map<String, Object> param) throws Exception{
		Map<String, Object >rsMap = selectOne("mhc.web.cm.push.selectPushMas", param);
		return rsMap;
	}
	
	public Map<String, Object> selectPushMasServey(Map<String, Object> param) throws Exception{
		Map<String, Object >rsMap = selectOne("mhc.web.cm.push.selectPushMasServey", param);
		return rsMap;
	}
	
	public void deletePushMasHist(Map<String, Object> param) throws Exception{
		delete("mhc.web.cm.push.deletePushMasHist", param);
	}
	
	public int insertPushMas(Map<String, Object> param) throws Exception {
		
		return update("mhc.web.cm.push.insertPushMas", param);
	}
		
	public int insertPushHis(Map<String, Object> param) throws Exception {
		
		return update("mhc.web.cm.push.insertPushHis", param);
	}
	
	public int insertPushHisTopic(Map<String, Object> param) throws Exception {
		
		return insert("mhc.web.cm.push.insertPushHisTopic", param);			  
	}
	
	public int updatePushMas(Map<String, Object> param) throws Exception {
		
		return update("mhc.web.cm.push.updatePushMas", param);
	}
	
	public int updatePushHis(Map<String, Object> param) throws Exception {
		
		return update("mhc.web.cm.push.updatePushHis", param);
	}

	public Map<String, String> getPushSndYnNotice(Map<String, Object> param) throws Exception {		
		Map<String,String> rsList = selectOne("mhc.web.cm.push.getPushSndYnNotice", param);		
		return rsList;
	}
	
	public Map<String, String> selectGrpList(Map<String, Object> param) throws Exception {
		Map<String,String> rsList = selectOne("mhc.web.cm.push.selectGrpList", param);
		return rsList;
	}
	

	public Map<String, String> selectMclasList(Map<String, Object> param) {
		Map<String,String> rsList = selectOne("mhc.web.cm.push.selectMclasList", param);
		return rsList;
	}

	public Map<String, String> selectChronicList(Map<String, Object> param) {
		Map<String,String> rsList = selectOne("mhc.web.cm.push.selectChronicList", param);
		return rsList;
	}
	
	public int updatePushStatus(Map<String, Object> param) throws Exception {
		
		return update("mhc.web.cm.push.updatePushStatus", param);			  
	}
	
	public int pushUpdateCnfm(Map<String, Object> param) throws Exception {
		
		return update("mhc.web.cm.push.pushUpdateCnfm", param);			  
	}
	
	public int pushChatStatus(Map<String, Object> param) throws Exception{
		return update("mhc.web.cm.push.pushChatStatus", param);
	}
	
	public List<Map<String,Object>> selectPushCnslCnt(Map<String,Object> param) throws Exception{
		// TODO Auto-generated method stub
		List<Map<String,Object>> rsList = selectList("mhc.web.cm.push.selectPushCnslCnt", param);
		return rsList;
	}
	
	//푸시 링크 대상자 리스트 조회
	public List<Map<String, Object>> selectPushTgrList(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.cm.push.selectPushTgrList", param);
		return rsList;
	}
	
	//모바일 공지 푸시 대상자 목록 조회
	public List<Map<String, Object>> selectNoticeUserList(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.cm.push.selectNoticeUserList", param);
		return rsList;
	}
	
	//푸시 대상자 정보 및 배지 카운트 조회
	public List<Map<String, Object>> selectPushInfoBadgeList(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.cm.push.selectPushInfoBadgeList", param);
		return rsList;
	}
	
	public List<Map<String, Object>> selectExcsSchSendList(Map<String, Object> param) throws Exception {		
		List<Map<String,Object>> rsList = selectList("mhc.web.cm.push.selectExcsSchSendList", param);		
		return rsList;  
	}
	
	// 중간검진 방문예약대상자 건수 조회
	public List<Map<String, Object>> selectVisitSchResvrtSendCnt() throws Exception {		
		List<Map<String,Object>> rsList = selectList("mhc.web.cm.push.selectVisitSchResvrtSendCnt");		
		return rsList;  
	}
	
	// 중간검진 방문예약대상자 목록 조회
	public List<Map<String, Object>> selectVisitSchResvrtSendList(String orgCd) throws Exception {		
		List<Map<String,Object>> rsList = selectList("mhc.web.cm.push.selectVisitSchResvrtSendList", orgCd);		
		return rsList;  
	}
	
	public void insertResvrtPushMas(Map<String, Object> param) throws Exception{
		insert("mhc.web.cm.push.insertPushMas", param);		
	}
	
	public void insertResvrtPushHis(Map<String, Object> param) throws Exception{
		insert("mhc.web.cm.push.insertPushHis", param);		
	}
	
	public String selectOrgCd(String userId) throws Exception {
		return selectOne("mhc.web.cm.push.selectOrgCd", userId);
		
	}

	public Map<String, Object> getPushSetInfo(Map<String, Object> param) throws Exception {
		return selectOne("mhc.web.cm.push.getPushSetInfo", param);
	}

	public String selectOrgUserList(Map<String, Object> param) {
		String rsStr = selectOne("mhc.web.cm.push.selectOrgUserList", param);		
		return rsStr;  
	}


}