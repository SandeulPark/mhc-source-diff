package kr.go.mhc.mhcweb.tg.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.go.mhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;

@Repository("web.tg.ServiceObjMngtServiceDAO")
public class ServiceObjMngtServiceDAO extends DMultiEgovAbstractMapper{

	public Map<String, String> getUserInfo(Map<String, Object> param) throws Exception {
		Map<String,String> rsMap = selectOne("mhc.web.tg.serviceobjmngt.selectUserInfo",param);	
		return rsMap;
    }

	public Map<String, String> getCnslInfo(Map<String, Object> param) throws Exception {
		Map<String,String> rsMap = new HashMap<String, String>();
		if(param.get("fromListChk").equals("Y")){
			rsMap = selectOne("mhc.web.tg.serviceobjmngt.selectCnslInfo",param);	
		}else{
			rsMap = selectOne("mhc.web.tg.serviceobjmngt.selectCnslHistConts",param);
		}
		return rsMap;
	}

	public List<Map<String, String>> getActiceCnt(Map<String, Object> param) throws Exception {
		 List<Map<String, String>> rsList = selectList("mhc.web.tg.serviceobjmngt.selectActiceCnt",param);	
		return rsList;
	}

	public List<Map<String, String>> getControlCnt(Map<String, Object> param) throws Exception {
		List<Map<String, String>> rsList = selectList("mhc.web.tg.serviceobjmngt.selectControlCnt",param);	
		return rsList;
	}

	public List<Map<String, String>> getActiveControl(Map<String, Object> param)throws Exception {
		List<Map<String, String>> rsList = selectList("mhc.web.tg.serviceobjmngt.selectActiveControl",param);	
		return rsList;
	}

	public Map<String, String> changeNeedam(Map<String, Object> param)throws Exception {
		Map<String, String> rsMap = selectOne("mhc.web.tg.serviceobjmngt.selectChangeNeedam",param);	
		return rsMap;
	}

	public Map<String, String> changeObjNeedam(Map<String, Object> param)throws Exception {
		Map<String, String> rsMap = selectOne("mhc.web.tg.serviceobjmngt.selectChangeObjNeedam",param);	
		return rsMap;
	}

	public List<Map<String, String>> getObjEatNeed(Map<String, Object> param)throws Exception {
		List<Map<String, String>> rsList = selectList("mhc.web.tg.serviceobjmngt.selectObjEatNeed",param);	
		return rsList;
	}

	public List<Map<String, String>> getRecommendEatCnt(Map<String, Object> param) throws Exception {
		List<Map<String, String>> rsList = selectList("mhc.web.tg.serviceobjmngt.selectRecommendEatCnt",param);	
		return rsList;
	}

	public List<Map<String, String>> getDangerFactor(Map<String, Object> param) throws Exception{
		List<Map<String, String>> rsList = selectList("mhc.web.tg.serviceobjmngt.selectDangerFactor",param);	
		return rsList;
	}

	public void updateCnslInfo(Map<String, Object> param) throws Exception {
	   update("mhc.web.tg.serviceobjmngt.updateCnslInfo",param);
	}

	public void updateCnslNurtInfo(Map<String, Object> param) throws Exception {
		update("mhc.web.tg.serviceobjmngt.updateCnslNurtInfo",param);
		update("mhc.web.tg.serviceobjmngt.updateCnslNurtHistInfo",param);
		update("mhc.web.tg.healthmngtcnsl.updateCnsl", param);
		
		
		//중간 방문 상담 시 집중상담 자동 발송 여부 설정
		if("3".equals(param.get("CNSL_NO"))){
			String cnslSn = "";			
			Map<String, Object> rsMap = selectOne("mhc.web.tg.serviceobjmngt.selectAutoSendCnslSn",param);	
			cnslSn = rsMap.get("CNSL_SN").toString();
			param.put("CNSL_SN", cnslSn);
			update("mhc.web.tg.serviceobjmngt.updateCnslAutoSendSetYn", param);
		}
				
		//최종 방문 시 모든 상담 완료 시 대상자 졸업 업데이트
		if("6".equals(param.get("CNSL_NO"))){
			update("mhc.web.tg.healthmngtcnsl.updateTrgterSttus_90", param);
		}

		
		
		
	}

	public int getCnslSnSeq(Map<String, Object> param)throws Exception {
		int rsMap = selectOne("mhc.web.tg.serviceobjmngt.selectCnslSnSeq",param);	
		return rsMap;
	}

	public void insertNewCnslInfo(Map<String, Object> param)throws Exception {
		insert("mhc.web.tg.serviceobjmngt.insertNewCnslInfo",param);
	}

	public void insertNewCnslNurtInfo(Map<String, Object> param) throws Exception{
		insert("mhc.web.tg.serviceobjmngt.insertNewCnslNurtInfo",param);
	}

	public List<Map<String, String>> getDateList(Map<String, Object> param) throws Exception{
		List<Map<String, String>> rsList = selectList("mhc.web.tg.serviceobjmngt.selectDateList",param);	
		return rsList;
	}
	
	public Map<String, String> getMyWeek(Map<String, Object> param) {
		Map<String,String> rsMap = selectOne("mhc.web.tg.serviceobjmngt.selectMyWeek", param);	
		return rsMap;  
	}	
	
	public Map<String, String> getRecomCRFPer(Map<String, Object> param) throws Exception {
		Map<String,String> rsMap = selectOne("mhc.web.tg.serviceobjmngt.selectRecomCRFPer",param);	
		return rsMap;
	}	

	public Map<String, String> checkingIntegration(Map<String, Object> param) throws Exception{
		Map<String, String> rsMap = selectOne("mhc.web.tg.serviceobjmngt.checkingIntegration",param);	
		return rsMap;
	}

	public void success_pgmt(Map<String, Object> param) throws Exception{
		update("mhc.web.tg.serviceobjmngt.success_pgmt",param);
	}

	public List<Map<String, String>> getSerivceObjMngtList(Map<String, Object> param) throws Exception{
		List<Map<String, String>> rsList = selectList("mhc.web.tg.serviceobjmngt.selectSerivceObjMngtList",param);	
		return rsList;
	}

	public Map<String, String> getCountServiceObjMngt(Map<String, Object> param) throws Exception{
		Map<String, String> rsMap = selectOne("mhc.web.tg.serviceobjmngt.selectCountServiceObjMngt",param);	
		return rsMap;
	}

	public List<Map<String, String>> selectPractMissionSch(Map<String, Object> param) throws Exception {
		 List<Map<String, String>> rsList = selectList("mhc.web.tg.serviceobjmngt.selectPractMissionSch", param);
		 return rsList;
	}

	public List<Map<String, String>> selectPractMissionSchChronic(Map<String, Object> param) throws Exception {
		List<Map<String, String>> rsList = selectList("mhc.web.tg.serviceobjmngt.selectPractMissionSchChronic", param);
		return rsList;
	}

	public int insertCreatePractMissionSch(Map<String, Object> param)throws Exception {
		insert("mhc.web.tg.serviceobjmngt.insertCreatePractMissionSch", param);
		return 1;
	}

	public int insertCreatePractMissionSchChronic(Map<String, Object> param)throws Exception {
		insert("mhc.web.tg.serviceobjmngt.insertCreatePractMissionSchChronic", param);
		return 1;
	}

	public List<Map<String, String>> selectPractMission(Map<String, Object> param) throws Exception {
		 List<Map<String, String>> rsList = selectList("mhc.web.tg.serviceobjmngt.selectPractMission", param);
		 return rsList;
	}

	public List<Map<String, String>> selectPractMissionChronic(Map<String, Object> param) throws Exception {
		List<Map<String, String>> rsList = selectList("mhc.web.tg.serviceobjmngt.selectPractMissionChronic", param);
		return rsList;
	}

	public int updatePractMissionSch(Map<String, Object> param) throws Exception {
		
		String[] arr_crudMode   = param.get("CRUD_MODE").toString().replaceAll("&quot;", "").replaceAll("\\[", "").replaceAll("\\]", "").split(",");
		String[] arr_weekCnt    = param.get("WEEK_CNT").toString().replaceAll("&quot;", "").replaceAll("\\[", "").replaceAll("\\]", "").split(",");
		String[] arr_missionCd1 = param.get("PRACT_MISSION_CD_1").toString().replaceAll("&quot;", "").replaceAll("\\[", "").replaceAll("\\]", "").split(",");		
		String[] arr_missionCd2 = param.get("PRACT_MISSION_CD_2").toString().replaceAll("&quot;", "").replaceAll("\\[", "").replaceAll("\\]", "").split(",");				
		
		System.out.println("arr_crudMode>>>> " + arr_crudMode.length);
		
		for(int i=0; i<arr_crudMode.length; i++) {
			if(!arr_crudMode[i].equals("null")){
				param.put("CRUD_MODE", arr_crudMode[i]);
				param.put("WEEK_CNT", arr_weekCnt[i]);
				param.put("SN", "1");
				param.put("PRACT_MISSION_CD", arr_missionCd1[i]);		

				update("mhc.web.tg.serviceobjmngt.updatePractMissionSch", param);									
			}	
		}
		
		for(int i=0; i<arr_crudMode.length; i++) {
			if(!arr_crudMode[i].equals("null")){
				param.put("CRUD_MODE", arr_crudMode[i]);
				param.put("WEEK_CNT", arr_weekCnt[i]);
				param.put("SN", "2");
				param.put("PRACT_MISSION_CD", arr_missionCd2[i]);		

				update("mhc.web.tg.serviceobjmngt.updatePractMissionSch", param);									
			}	
		}		

		return 1;
	}

	public int updatePractMissionSchChronic(Map<String, Object> param) throws Exception {

		String[] arr_crudMode   = param.get("CRUD_MODE").toString().replaceAll("&quot;", "").replaceAll("\\[", "").replaceAll("\\]", "").split(",");
		String[] arr_weekCnt    = param.get("WEEK_CNT").toString().replaceAll("&quot;", "").replaceAll("\\[", "").replaceAll("\\]", "").split(",");
		String[] arr_missionCd1 = param.get("PRACT_MISSION_CD_1").toString().replaceAll("&quot;", "").replaceAll("\\[", "").replaceAll("\\]", "").split(",");
		String[] arr_missionCd2 = param.get("PRACT_MISSION_CD_2").toString().replaceAll("&quot;", "").replaceAll("\\[", "").replaceAll("\\]", "").split(",");

		System.out.println("arr_crudMode>>>> " + arr_crudMode.length);

		for(int i=0; i<arr_crudMode.length; i++) {
			if(!arr_crudMode[i].equals("null")){
				param.put("CRUD_MODE", arr_crudMode[i]);
				param.put("WEEK_CNT", arr_weekCnt[i]);
				param.put("SN", "1");
				param.put("PRACT_MISSION_CD", arr_missionCd1[i]);

				update("mhc.web.tg.serviceobjmngt.updatePractMissionSchChronic", param);
			}
		}

		for(int i=0; i<arr_crudMode.length; i++) {
			if(!arr_crudMode[i].equals("null")){
				param.put("CRUD_MODE", arr_crudMode[i]);
				param.put("WEEK_CNT", arr_weekCnt[i]);
				param.put("SN", "2");
				param.put("PRACT_MISSION_CD", arr_missionCd2[i]);

				update("mhc.web.tg.serviceobjmngt.updatePractMissionSchChronic", param);
			}
		}

		return 1;
	}


	public Map<String, Object> getSvcSchCreateYn(Map<String, Object> param)throws Exception {
		Map<String, Object> rsMap = selectOne("mhc.web.tg.serviceobjmngt.selectSvcSchCreateYn",param);	
		return rsMap;
	}
	
	public Map<String, Object> getCnslCompleteSvcBgnAppontYn(Map<String, Object> param)throws Exception {
		Map<String, Object> rsMap = selectOne("mhc.web.tg.serviceobjmngt.selectCnslCompleteSvcBgnAppontYn",param);	
		return rsMap;
	}

	public List<Map<String, Object>> getSelfMngtSurveyList(Map<String, Object> param) {
		List<Map<String, Object>> rsList = selectList("mhc.web.tg.serviceobjmngt.getSelfMngtSurveyList", param);
		return rsList;
	}

	public String getCnslSn(Map<String, Object> param) {
		return selectOne("mhc.web.tg.serviceobjmngt.getCnslSn",param);
	}

}
