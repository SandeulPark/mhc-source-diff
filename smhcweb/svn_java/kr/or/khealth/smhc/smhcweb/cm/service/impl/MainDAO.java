package kr.or.khealth.smhc.smhcweb.cm.service.impl;

import java.util.List;
import java.util.Map;



import kr.or.khealth.smhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;

@Repository("mainDAO")
public class MainDAO extends DMultiEgovAbstractMapper{
	
	public List<Map<String, Object>> selectSeniorCalendarMainList(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("smhc.web.cm.main.selectSeniorCalendarMainList", param);
		return rsList;
	}
	
	public Map<String,Object> selectServerTime(Map<String,Object> param) throws Exception{
		Map<String,Object> rsMap = selectOne("smhc.web.cm.main.selectServerTime", param);
		return rsMap;
	}
	
	public Map<String,Object> selectSvcStatusIng(Map<String,Object> param) throws Exception{
		Map<String,Object> rsMap = selectOne("smhc.web.cm.main.selectSvcStatusIng", param);
		return rsMap;
	}
	
	public Map<String,Object> selectTodaySvcStatusReg(Map<String,Object> param) throws Exception{
		Map<String,Object> rsMap = selectOne("smhc.web.cm.main.selectTodaySvcStatusReg", param);
		return rsMap;
	}
	
	public List<Map<String, Object>> selectSeniorFaceToFaceVisitList(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("smhc.web.cm.main.selectSeniorFaceToFaceVisitList", param);
		return rsList;
	}
	
	public Map<String, Object> selectTodayMeasrInfo(Map<String, Object> param) throws Exception{
		Map<String, Object> rsMap = selectOne("smhc.web.cm.main.selectTodayMeasrInfo", param);
		return rsMap;
	}
}
