package kr.or.khealth.smhc.smhcweb.sv.service.impl;

import java.util.List;
import java.util.Map;

import kr.or.khealth.smhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;

/**
 * @Class Name : MissionMngtDAO.java
 * @Description : 관리자 WEB에서 사용하는 미션설정 관리업무 DataBase 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.08.12		이은주			최초생성
 *
 * @author gst
 * @since 2016.08.12
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Repository("web.sv.MissionMngtDAO")
public class MissionMngtDAO extends DMultiEgovAbstractMapper{

	public List<Map<String, Object>> selectMissionMngtList(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("smhc.web.sv.missionmngt.selectMissionMngtList",param);
		return rsList;
	}

	public List<Map<String, Object>> setDeviceInfo(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> deviceList = selectList("smhc.web.sv.missionmngt.setDeviceInfo",param);
		return deviceList;
	}

	public List<Map<String, Object>> setMissionInfo(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> missionList = selectList("smhc.web.sv.missionmngt.setMissionInfo",param);
		return missionList;
	}
	
	public int updMissionSet(Map<String, Object> param) throws Exception{
		int rsInt = 0;
		String[] mission_cd = param.get("MISSION_CD").toString().split("\\,");
		if(param.get("MAIN_DRUG_CD").equals(param.get("CHANGE_DRUG_CD"))){
			for(int i=0; i<mission_cd.length;i++){
					param.put("MISSION_CD", mission_cd[i]);
					if(!param.get("MISSION_CD").equals("M001")){
					rsInt = update("smhc.web.sv.missionmngt.updMissionSet",param);
					rsInt += update("smhc.web.sv.missionmngt.updMissionSetDtls",param);
					}
			}
		}else{
			for(int i=0; i<mission_cd.length;i++){
				param.put("MISSION_CD", mission_cd[i]);
				rsInt = update("smhc.web.sv.missionmngt.updMissionSet",param);
				rsInt += update("smhc.web.sv.missionmngt.updMissionSetDtls",param);
		}
	}
		return rsInt;
	}
	
	public int updDrugInfo(Map<String, Object> param) throws Exception{
		int rsInt = 0;
		String[] drug_cd = param.get("DRUG_CD").toString().split("\\,");
		for(int i=0; i<drug_cd.length;i++){
			param.put("DRUG_CD", drug_cd[i]);
			rsInt = update("smhc.web.sv.missionmngt.updDrugInfo",param);
		}
		return rsInt;
	}
	
	public int updDrugMissionSet(Map<String, Object> param) throws Exception{
		int rsInt = 0;
			rsInt = update("smhc.web.sv.missionmngt.updDrugMissionSet",param);
			rsInt += update("smhc.web.sv.missionmngt.updDrugTnSvUserMissionSet",param);
			rsInt += update("smhc.web.sv.missionmngt.updDrugTnSvUserMissionSetDtls",param);	
		return rsInt;
	}
	
}
