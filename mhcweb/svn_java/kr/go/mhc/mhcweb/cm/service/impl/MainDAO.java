package kr.go.mhc.mhcweb.cm.service.impl;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import kr.go.mhc.common.DMultiEgovAbstractMapper;

@Repository("mainDAO")
public class MainDAO extends DMultiEgovAbstractMapper{
	
	public Map<String, Object> getTrgterJoinSttus(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> rsMap = selectOne("mhc.web.cm.main.selectTrgterJoinSttus", param);	
		return rsMap;  
	}

	public Map<String, Object> getSvcContinuePer(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> rsMap = selectOne("mhc.web.cm.main.selectSvcContinuePer", param);	
		return rsMap;  
	}

	public Map<String, Object> getWeekSvcJoinPer(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> rsMap = selectOne("mhc.web.cm.main.selectWeekSvcJoinPer", param);	
		return rsMap;  
	}

	public Map<String, Object> getTodaySvcJoinCnt(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> rsMap = selectOne("mhc.web.cm.main.selectTodaySvcJoinCnt", param);	
		return rsMap;  
	}
	
	public Map<String, Object> getRealTimeCnsl(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> rsMap = selectOne("mhc.web.cm.main.selectRealTimeCnsl", param);	
		return rsMap;  
	}
	
	public Map<String, Object> getNormalCnsl(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> rsMap = selectOne("mhc.web.cm.main.selectNormalCnsl", param);	
		return rsMap;  
	}
	
	public Map<String, Object> getIntenseCnsl(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> rsMap = selectOne("mhc.web.cm.main.selectIntenseCnsl", param);	
		return rsMap;  
	}

	public Map<String, Object> getIntenseCnslNew(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> rsMap = selectOne("mhc.web.cm.main.selectIntenseCnslNew", param);
		return rsMap;
	}
	
	public Map<String, Object> getVisitCnsl(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> rsMap = selectOne("mhc.web.cm.main.selectVisitCnsl", param);	
		return rsMap;  
	}
	
	public List<Map<String,Object>> getNoticeList(Map<String, Object> param)
			throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,Object>> rsList = selectList("mhc.web.cm.main.selectNoticeList", param);	
		return rsList;  
	}
	
	public int selectLoginIdCheck(Map<String, Object> param)
			throws Exception {
		int rsInt = selectOne("mhc.web.cm.main.selectLoginIdCheck", param);
		return rsInt;
	}
	
	public int selectTrgterCheck(Map<String, Object> param)
			throws Exception {
		int rsInt = selectOne("mhc.web.cm.main.selectTrgterCheck", param);
		return rsInt;
	}
	
	public int selectHealthDisorderInfoTrgter(Map<String, Object> param)
			throws Exception {
		int rsInt = 0;
		List<Map<String,Object>> rsList = selectList("mhc.web.cm.main.selectHealthDisorderInfoTrgter", param);
		rsInt = rsList.size();		
		return rsInt;
	}
	
	public int selectSvcNoJoinTrgter(Map<String, Object> param)
			throws Exception {
		int rsInt = 0;
		List<Map<String,Object>> rsList = selectList("mhc.web.cm.main.selectSvcNoJoinTrgter", param);
		rsInt = rsList.size();	
		return rsInt;
	}
	
	public Map<String,Object> getServerTime(Map<String,Object> param) throws Exception{
		// TODO Auto-generated method stub
		Map<String,Object> rsMap = selectOne("mhc.web.cm.main.selectServerTime", param);
		return rsMap;
	}
	
	public int selectSvcSchNotCreateCnt(Map<String, Object> param) throws Exception{
		int rsInt = 0;
		List<Map<String, Object>> rsList = selectList("mhc.web.cm.main.selectSvcSchNotCreateCnt", param);
		rsInt = rsList.size();
		return rsInt;
	}
	
	public Map<String,Object> getTrgterInfo(Map<String,Object> param) throws Exception{
		// TODO Auto-generated method stub
		Map<String,Object> rsMap = selectOne("mhc.web.cm.main.selectTrgterInfo", param);
		return rsMap;
	}
	
	public List<Map<String,Object>> getTrgterSpecialNote(Map<String,Object> param) throws Exception{
		// TODO Auto-generated method stub
		List<Map<String,Object>> rsList = selectList("mhc.web.cm.main.selectTrgterSpecialNote", param);
		return rsList;
	}
	
	public List<Map<String,Object>> getObjCnslMemo(Map<String,Object> param) throws Exception{
		// TODO Auto-generated method stub
		List<Map<String,Object>> rsList = selectList("mhc.web.cm.main.selectObjCnslMemo", param);
		return rsList;
	}
	
	public List<Map<String,Object>> getIntenseCnslMemo(Map<String,Object> param) throws Exception{
		// TODO Auto-generated method stub
		List<Map<String,Object>> rsList = selectList("mhc.web.cm.main.selectIntenseCnslMemo", param);
		return rsList;
	}
	
	public int updateSpecialNote(Map<String,Object> param) throws Exception{
		// TODO Auto-generated method stub		
		return insert("mhc.web.cm.main.updateSpecialNote", param);
	}
	
	public int deleteSpecialNote(Map<String,Object> param) throws Exception{
		// TODO Auto-generated method stub		
		return insert("mhc.web.cm.main.deleteSpecialNote", param);
	}
	
	public List<Map<String,Object>> getFavoriteMenu(Map<String,Object> param) throws Exception{
		// TODO Auto-generated method stub
		List<Map<String,Object>> rsList = selectList("mhc.web.cm.main.selectFavoriteMenu", param);
		return rsList;
	}

	public int saveFavoriteMenu(Map<String,Object> param) throws Exception{
		// TODO Auto-generated method stub		
		return insert("mhc.web.cm.main.insertFavoriteMenu", param);
		
	}
	
	public int deleteFavoriteMenu(Map<String,Object> param) throws Exception{
		// TODO Auto-generated method stub		
		return insert("mhc.web.cm.main.deleteFavoriteMenu", param);	
		
	}
	
	public List<Map<String, Object>> getPopNoticeList(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.cm.main.selectPopNoticeList", param);
		return rsList;
	}
	
	public int updPopNoticeCnfm(Map<String, Object> param) throws Exception{
		// TODO Auto-generated method stub
		return insert("mhc.web.cm.main.updPopNoticeCnfm", param);
	}
	
	public List<Map<String, Object>> getOrgAuthList(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.cm.main.selectOrgAuthList", param);
		return rsList;
	}

	public List<Map<String, Object>> getPopCallingMaterialNoticeList(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.cm.main.selectPopCallingMaterialNoticeList", param);
		return rsList;
	}
	
	public List<Map<String, Object>> getPopServeyList(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.cm.main.getPopServeyList", param);
		return rsList;
	}

	public Map<String, Object> getAccumulateSvcJoinCnt(Map<String, Object> param) {
		// TODO Auto-generated method stub
		Map<String,Object> rsMap = selectOne("mhc.web.cm.main.getAccumulateSvcJoinCnt", param);	
		return rsMap;  
	}

	public Map<String, Object> getAccumulateSvcDropCnt(Map<String, Object> param) {
		// TODO Auto-generated method stub
		Map<String,Object> rsMap = selectOne("mhc.web.cm.main.getAccumulateSvcDropCnt", param);	
		return rsMap;
	}
}
