package kr.go.mhc.mhcweb.sv.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

@Repository("web.sv.PractMissonSchMngtServiceDAO")
public class PractMissonSchMngtServiceDAO extends EgovAbstractMapper{
	
	public List<Map<String, Object>> getPractMissonSchList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.sv.practmissonschmngt.selectPractMissonSchList", param);
		return rsList;
	}
	
	public List<Map<String, Object>> getPractMissonCdList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.sv.practmissonschmngt.selectPractMissonCdList", param);
		return rsList;
	}	
	//추가
	public List<Map<String, Object>> getAllMissionCDList(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.sv.practmissonschmngt.selectAllMissionCDList", param);
		return rsList;
	}
	//추가
	public int updatePublicHealthMissionDelete(Map<String, Object> param) throws Exception {
		int rsInt = update("mhc.web.sv.practmissonschmngt.updatePublicHealthMissionDelete", param);
		return rsInt;
	}
	//추가
	public int updatePublicHealthMissionUpdate(Map<String, Object> param) throws Exception {
		int rsInt = update("mhc.web.sv.practmissonschmngt.updatePublicHealthMissionUpdate",param);
		return rsInt;
	}
	//추가
	public Map<String, Object> insertPublicHealthMisson(Map<String, Object> param) throws Exception{
		Map<String, Object> rsMap =selectOne("mhc.web.sv.practmissonschmngt.selectAllMissionCDList2", param);
		param.put("PRACT_MISSION_CD",rsMap.get("PRACT_MISSION_CD"));
		insert("mhc.web.sv.practmissonschmngt.InsertPublicHealthMisson", param);
		return rsMap;
	}
	//추가
	public List<Map<String,Object>> selectPublicMissionCount(Map<String,Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.sv.practmissonschmngt.selectPublicMissionCount", param);
		return rsList;
	}
	//추가
	public List<Map<String,Object>> selectPublicMissionFile(Map<String,Object> param) throws Exception{
		List<Map<String,Object>> rsList =selectList("mhc.web.sv.practmissonschmngt.selectPublicMissionFile", param);
		return rsList;
	}



	public int getSelWeekTrgterChk(Map<String, Object> param) throws Exception {
		Map<String, Object> rsMap = selectOne("mhc.web.sv.practmissonschmngt.selectSelWeekTrgterChk", param);
		int rsInt = Integer.parseInt(rsMap.get("MAX_WEEK_CNT").toString());
		return rsInt;
	}
	
	public int updatePractMissionSchCd(Map<String, Object> param) throws Exception {
		String weekCnt[] = param.get("weekCnt").toString().split(",");
		String selCd[] = param.get("selCd").toString().split(",");
		int rsInt=0;
		int selCdCnt=0;
		for(int i=0; i<weekCnt.length; i++){
			param.put("WEEK_CNT", weekCnt[i]);
			param.put("SN", "1");
			param.put("PRACT_MISSION_CD", selCd[selCdCnt]);
			rsInt = 0;
			rsInt += update("mhc.web.sv.practmissonschmngt.updatePractMissionSchCd", param);
			if(rsInt != 0){
				update("mhc.web.sv.practmissonschmngt.updateTrgterPractMissionSch", param);
			}
			param.remove("SN");
			param.remove("PRACT_MISSION_CD");
			param.put("SN", "2");
			param.put("PRACT_MISSION_CD", selCd[selCdCnt+1]);
			rsInt = 0;
			rsInt += update("mhc.web.sv.practmissonschmngt.updatePractMissionSchCd", param);
			if(rsInt != 0){
				update("mhc.web.sv.practmissonschmngt.updateTrgterPractMissionSch", param);
			}
			selCdCnt += 2;
		}
		return rsInt;
	}

	/* ################################################################################# */
	/* ######################### 만성질환 실천미션 추가 202304 ######################### */
	public List<Map<String, Object>> getPractMissonChronicSchList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.sv.practmissonschmngt.selectPractMissonChronicSchList", param);
		return rsList;
	}
	public List<Map<String, Object>> getPractMissonCdChronicList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.sv.practmissonschmngt.selectPractMissonCdChronicList", param);
		return rsList;
	}

	//추가
	public List<Map<String, Object>> getAllMissionCDChronicList(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.sv.practmissonschmngt.selectAllMissionCDChronicList", param);
		return rsList;
	}

	public Map<String, Object> insertPublicHealthMissonChronic(Map<String, Object> param) throws Exception{
		Map<String, Object> rsMap = selectOne("mhc.web.sv.practmissonschmngt.selectAllMissionCDList2", param);
		param.put("PRACT_MISSION_CD",rsMap.get("PRACT_MISSION_CD"));
		insert("mhc.web.sv.practmissonschmngt.InsertPublicHealthMissonChronic", param);
		return rsMap;
	}

	public int updatePublicHealthMissionChronicUpdate(Map<String, Object> param) throws Exception {
		int rsInt = update("mhc.web.sv.practmissonschmngt.updatePublicHealthMissionChronicUpdate",param);
		return rsInt;
	}

	public int updatePractMissionChronicSchCd(Map<String, Object> param) throws Exception {
		String weekCnt[] = param.get("weekCnt").toString().split(",");
		String selCd[] = param.get("selCd").toString().split(",");
		String chronicCd = param.get("CHRONIC_CD").toString();
		int rsInt=0;
		int selCdCnt=0;
		for(int i=0; i<weekCnt.length; i++){
			param.put("WEEK_CNT", weekCnt[i]);
			param.put("SN", "1");
			param.put("PRACT_MISSION_CD", selCd[selCdCnt]);
			param.put("CHRONIC_CD", chronicCd);
			rsInt = 0;
			rsInt += update("mhc.web.sv.practmissonschmngt.updatePractMissionChronicSchCd", param);
//			if(rsInt != 0){
//				update("mhc.web.sv.practmissonschmngt.updateTrgterPractMissionSch", param);
//			}
			param.remove("SN");
			param.remove("PRACT_MISSION_CD");
			param.put("SN", "2");
			param.put("PRACT_MISSION_CD", selCd[selCdCnt+1]);
			param.put("CHRONIC_CD", chronicCd);
			rsInt = 0;
			rsInt += update("mhc.web.sv.practmissonschmngt.updatePractMissionChronicSchCd", param);
//			if(rsInt != 0){
//				update("mhc.web.sv.practmissonschmngt.updateTrgterPractMissionSch", param);
//			}
			selCdCnt += 2;
		}
		return rsInt;
	}
}
