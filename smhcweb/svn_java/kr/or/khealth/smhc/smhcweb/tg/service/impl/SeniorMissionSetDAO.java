package kr.or.khealth.smhc.smhcweb.tg.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import kr.or.khealth.smhc.common.DMultiEgovAbstractMapper;
import kr.or.khealth.smhc.common.util.StringUtil;

import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Repository;


/**
 * @Class Name : SeniorSvrveyDAO.java
 * @Description : 관리자 WEB에서 사용하는 어르신 대면평가정보등록(미션설정)  DataBase 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자		수정내용
 * @	----------		------		---------------------------
 * @	2020.10.10		오샘이			최초생성
 *
 * @author theJoin
 * @since 2020.10.10
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Repository("web.tg.SeniorMissionSetDAO")
public class SeniorMissionSetDAO extends DMultiEgovAbstractMapper{
	
	public List<Map<String, Object>> selectMissionSetList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("smhc.web.tg.seniormissionset.selectMissionSetList", param);			
		return rsList;
	}

	public int insertMissionSet(Map<String, Object> param) throws Exception {
		int rsInt=0;
		if(param.get("MAIN_DRUG_CD").equals(param.get("CHANGE_DRUG_CD"))){
			String[] missionCd  = param.get("MISSION_CD").toString().split("\\,");	
			for(int i=0; i<missionCd.length;i++){
				param.put("MISSION_CD",  missionCd[i]);
				if(!param.get("MISSION_CD").equals("M001")){
					param.put("SORT_ORD",(i+1));
					rsInt = insert("smhc.web.tg.seniormissionset.insertMissionSet", param);
				}
			}
			for(int j=0;j<missionCd.length;j++){
				String formatta = String.format("%02d", (j+1));
				param.put("MISSION_CD",  missionCd[j]);
				 if(!param.get("MISSION_CD").equals("M001")){
					if(param.get("MISSION_CD").equals("M006")){
						for(int k=1;k<=3;k++){
							String formattaM006 = String.format("%02d", (k));
							param.put("MISSION_CD_DTLS",formattaM006);
							param.put("MISSION_CLF", "00");
						 rsInt += update("smhc.web.tg.seniormissionset.insertMissionSetDtls", param);	
						}
					}else{
						param.put("MISSION_CD_DTLS","01");
					}
					if(param.get("MISSION_CD").equals("W001")){
						param.put("MISSION_CLF", "01");
					}else{
						param.put("MISSION_CLF", "00");
					}
					if(!param.get("MISSION_CD").equals("M006")){
						rsInt += update("smhc.web.tg.seniormissionset.insertMissionSetDtls", param);
					}
				 }
			}
		}else{
		String[] missionCd  = param.get("MISSION_CD").toString().split("\\,");
		if(!param.get("MISSION_CD").toString().equals("")){
			for(int i=0; i<missionCd.length;i++){
				param.put("MISSION_CD",  missionCd[i]);
				param.put("SORT_ORD",(i+1));
			    rsInt = insert("smhc.web.tg.seniormissionset.insertMissionSet", param);		
			}
			for(int j=0;j<missionCd.length;j++){
				String formatta = String.format("%02d", (j+1));
				param.put("MISSION_CD",  missionCd[j]);
				if(param.get("MISSION_CD").equals("M001")){
					param.put("MISSION_CD_DTLS",formatta);
				}else if(param.get("MISSION_CD").equals("M006")){
					for(int k=1;k<=3;k++){
						String formattaM006 = String.format("%02d", (k));
						param.put("MISSION_CD_DTLS",formattaM006);
						param.put("MISSION_CLF", "00");
					 rsInt += update("smhc.web.tg.seniormissionset.insertMissionSetDtls", param);	
					}
				}else{
					param.put("MISSION_CD_DTLS","01");
				}
				
				if(param.get("MISSION_CD").equals("W001")){
					param.put("MISSION_CLF", "01");
				}else{
					param.put("MISSION_CLF", "00");
				}
				if(!param.get("MISSION_CD").equals("M006")){
					rsInt += update("smhc.web.tg.seniormissionset.insertMissionSetDtls", param);
				}
			}
		}
			if(param.get("STATUS").equals("Y")){
				rsInt += update("smhc.web.tg.seriortrgterreg.updSvcBgnDeStatusCd", param);
			}
		}
		return rsInt;
	}
	
	public List<Map<String, Object>> selectUserMissionList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("smhc.web.tg.seniormissionset.selectUserMissionList", param);
		return rsList;
	}
	
	public List<Map<String, Object>> selectUserMissionChk(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("smhc.web.tg.seniormissionset.selectUserMissionChk", param);
		return rsList;
	}
	
	
	
	public int insertSeniorDrugInfo(Map<String, Object> param) throws Exception{
		int rsInt =0;	
		String DrugDATE="";
		String MissionSetUseYn = (String) param.get("MISSION_SET_USE_YN");
		String[] drugNm  = param.get("DRUG_NM").toString().split("\\,");
		String[] drugStartDe  = param.get("DRUG_START_DE").toString().split("\\,");
		String[] drugPrd = param.get("DRUG_PRD").toString().split("\\,");
		String[] drugCount    = param.get("DRUG_COUNT").toString().split("\\,"); 
		String[] drugTime     = param.get("DRUG_TIME").toString().split("\\/");
		for(int i=0; i<=drugNm.length;i++){
			if(MissionSetUseYn.equals(Integer.toString(i))){
				param.put("MISSION_SET_USE_YN", "Y");
			}else{
				param.put("MISSION_SET_USE_YN", "N");
			}
			param.put("DRUG_NM",  drugNm[i]);
			param.put("DRUG_START_DE", drugStartDe[i]);
			param.put("DRUG_PRD",    drugPrd[i]);
			param.put("DRUG_COUNT",     drugCount[i]);
			String[] drugTime2     = drugTime[i].toString().split("\\,");
			DrugDATE="";
			for (int j = 0; j <= drugTime2.length-1;j++){
					String drugDateDtls=drugTime2[j];					
					if(j!=0){
						if(j==drugTime2.length-1){
							DrugDATE+=",'"+drugDateDtls+"'";
						}else{
							DrugDATE+=",'"+drugDateDtls+"'";
						}
					}else{						
						DrugDATE+="'"+drugDateDtls+"'";
					}
				
			}
			param.put("DRUG_TIME",  DrugDATE);			
			rsInt = insert("smhc.web.tg.seniormissionset.insertSeniorDrugInfo", param);	
		}
		
		return rsInt;
	}
	
	public String selectUserSvcMngt(Map<String, Object> param) throws Exception{
		return selectOne("smhc.web.tg.seniormissionset.selectUserSvcMngt", param);
	}
}
