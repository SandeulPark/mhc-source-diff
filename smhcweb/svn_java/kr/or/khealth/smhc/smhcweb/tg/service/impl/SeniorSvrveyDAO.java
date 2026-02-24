package kr.or.khealth.smhc.smhcweb.tg.service.impl;

import java.util.List;
import java.util.Map;

import kr.or.khealth.smhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;


/**
 * @Class Name : SeniorSvrveyDAO.java
 * @Description : 관리자 WEB에서 사용하는 기관 정보 DataBase 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자		수정내용
 * @	----------		------		---------------------------
 * @	2020.09.28		양현우			최초생성
 *
 * @author theJoin
 * @since 2020.09.28
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Repository("web.tg.SeniorSvrveyDAO")
public class SeniorSvrveyDAO extends DMultiEgovAbstractMapper{
	
	public List<Map<String, Object>> selectSeniorSvrVeyList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("smhc.web.tg.seniorsvrvey.selectSeniorSvrVeyList", param);
		return rsList;
	}

	public int insertSeniorHealthSvrVey(Map<String, Object> param) throws Exception {
		int rsInt=0;
		String[] question_no = param.get("QUESTION_NO").toString().split("\\,");
		String[] question_no_dtls = param.get("QUESTION_NO_DTLS").toString().split("\\,");
		String[] answr_cd = param.get("ANSWR_CD").toString().split("\\,");
		String[] short_answr = param.get("SHORT_ANSWR").toString().split("\\,");
		String[] answr_cd_dtls = param.get("ANSWR_CD_DTLS").toString().split("\\,");
		for(int i=0; i<answr_cd.length;i++){
			param.put("QUESTION_NO", question_no[i]);
			param.put("ANSWR_CD", answr_cd[i]);
			param.put("QUESTION_NO_DTLS",question_no_dtls[i]);
			param.put("SHORT_ANSWR",short_answr[i]);
			param.put("ANSWR_CD_DTLS",answr_cd_dtls[i]);
		    rsInt = insert("smhc.web.tg.seniorsvrvey.insertSeniorHealthSvrVey", param);		
		}
		 if(param.get("SVRVEY_TYPE").equals("S3")){
			 rsInt += insert("smhc.web.tg.seniorsvrvey.insertSeniorJudgeGrpRslt", param);	
			 rsInt += update("smhc.web.tg.seniorsvrvey.updSvcMngtSvrvey",param);
	     }
		    rsInt += update("smhc.web.tg.seniorsvrvey.updateSvrveyMastr",param);
		    rsInt += update("smhc.web.tg.seniorsvrvey.insertDeviceDistributeAll",param);
		return rsInt;
	}
	
	public List<Map<String, Object>> selectSvrveyMastrChk(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("smhc.web.tg.seniorsvrvey.selectSvrveyMastrChk", param);
		return rsList;
	}
	
	public List<Map<String, Object>> selectSvrveyAnswr(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("smhc.web.tg.seniorsvrvey.selectSvrveyAnswr", param);
		return rsList;
	}
	
	public List<Map<String, Object>> selectSvrveyMastrResult(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("smhc.web.tg.seniorsvrvey.selectSvrveyMastrResult", param);
		return rsList;
	}
	
	public int insertDeviceDistributes(Map<String, Object> param) throws Exception {
		int rsInt=0;
		String[] device_clf = param.get("DEVICE_CLF").toString().split("\\,");
		if(!param.get("DEVICE_CLF").toString().equals("NO_DEVICE")){
			for(int i=0; i<device_clf.length;i++){
				param.put("DEVICE_CLF", device_clf[i]);
			    rsInt = insert("smhc.web.tg.seniorsvrvey.insertDeviceDistributes", param);		
			}
		}
			rsInt += update("smhc.web.tg.seniorsvrvey.updSvcMngtDevice",param);
		return rsInt;
	}
	
	public int insertDeviceFormInfo(Map<String, Object> param) throws Exception {
		int rsInt=0;
		rsInt += insert("smhc.web.tg.seniorsvrvey.insertDeviceFormInfo",param);
		rsInt += update("smhc.web.tg.seniorsvrvey.updateGetDeviceInfo",param);		
		return rsInt;
	}	
	
	
	
	public List<Map<String, Object>> selectDeviceDistributesList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("smhc.web.tg.seniorsvrvey.selectDeviceDistributesList", param);
		return rsList;
	}
	
	public List<Map<String , Object>> selectMissionNmList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("smhc.web.tg.seniorsvrvey.selectMissionNmList", param);
		return rsList;
	}
	
	public List<Map<String , Object>> selectMissionGrpNmList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("smhc.web.tg.seniorsvrvey.selectMissionGrpNmList", param);
		return rsList;
	}

	public int updateGetDeviceInfo(Map<String, Object> param) throws Exception {
		int rsInt=0;
		return rsInt = update("smhc.web.tg.seniorsvrvey.updateGetDeviceInfo",param);
	}
	
	public int updSeniorHealthSvrVey(Map<String, Object> param) throws Exception {
		int rsInt=0;
		String[] question_no = param.get("QUESTION_NO").toString().split("\\,");
		String[] question_no_dtls = param.get("QUESTION_NO_DTLS").toString().split("\\,");
		String[] answr_cd = param.get("ANSWR_CD").toString().split("\\,");
		String[] short_answr = param.get("SHORT_ANSWR").toString().split("\\,");
		String[] answr_cd_dtls = param.get("ANSWR_CD_DTLS").toString().split("\\,");
		for(int i=0; i<answr_cd.length;i++){
			param.put("QUESTION_NO", question_no[i]);
			param.put("ANSWR_CD", answr_cd[i]);
			param.put("QUESTION_NO_DTLS",question_no_dtls[i]);
			param.put("SHORT_ANSWR",short_answr[i]);
			param.put("ANSWR_CD_DTLS",answr_cd_dtls[i]);
		    rsInt = update("smhc.web.tg.seniorsvrvey.updSeniorHealthSvrVey",param);
		}
		 if(param.get("SVRVEY_TYPE").equals("S3")){
			 rsInt += insert("smhc.web.tg.seniorsvrvey.updSeniorJudgeGrpRslt", param);	
	     }
		return rsInt;
	}

	public int updDeviceDistributes(Map<String, Object> param) throws Exception {
		int rsInt=0;
		String[] device_clf = param.get("DEVICE_CLF").toString().split("\\,");
		String[] use_yn = param.get("USE_YN").toString().split("\\,");
		for(int i=0; i<device_clf.length;i++){
			param.put("DEVICE_CLF", device_clf[i]);
			param.put("USE_YN", use_yn[i]);
		    rsInt = update("smhc.web.tg.seniorsvrvey.updDeviceDistributes", param);		
		}
		return rsInt;
	}
	
	public String selectSvcMngtDevice(Map<String, Object> param) throws Exception{
		return selectOne("smhc.web.tg.seniorsvrvey.selectSvcMngtDevice", param);
	}
}
