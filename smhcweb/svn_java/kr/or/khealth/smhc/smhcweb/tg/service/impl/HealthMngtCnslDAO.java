package kr.or.khealth.smhc.smhcweb.tg.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




import kr.or.khealth.smhc.common.DMultiEgovAbstractMapper;
import kr.or.khealth.smhc.common.util.StringUtil;

import org.springframework.stereotype.Repository;

/**
 * @Class Name : HealthMngtCnslDAO.java
 * @Description : 관리자 WEB에서 사용하는 건강관리 상담업무 DataBase 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자		수정내용
 * @	----------		-----		---------------------------
 * @	2016.08.28		이현규		최초생성
 *
 * @author gst
 * @since 2016.08.28
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Repository("web.tg.HealthMngtCnslDAO")
public class HealthMngtCnslDAO extends DMultiEgovAbstractMapper {
	
	public Map<String, String> selectHealthMngtCnslInfo(Map<String, Object> param) throws Exception {
		Map<String,String> rsMap = selectOne("mhc.web.tg.healthmngtcnsl.selectHealthMngtCnslInfo", param);
		return rsMap;  
	}
	
	public List<Map<String, String>> selectDangerFactorList(Map<String, Object> param) throws Exception {
		List<Map<String,String>> rsList = selectList("mhc.web.tg.healthmngtcnsl.selectDangerFactorList", param);
		return rsList;  
	}
	
	public List<Map<String, String>> selectSmokServeyList(Map<String, Object> param) throws Exception {
		List<Map<String,String>> rsList = selectList("mhc.web.tg.healthmngtcnsl.selectSmokServeyList", param);
		return rsList;  
	}
	
	public Map<String, String> selectCnslContMngt(Map<String, Object> param) throws Exception {
		Map<String,String> rsMap = selectOne("mhc.web.tg.healthmngtcnsl.selectCnslContMngt", param);
		return rsMap;  
	}
	
	public Map<String, String> selectMngtObj(Map<String, Object> param) throws Exception {
		Map<String,String> rsMap = selectOne("mhc.web.tg.healthmngtcnsl.selectMngtObj", param);
		return rsMap;  
	}
	
	public Map<String, String> selectBodyActFatMngtObj(Map<String, Object> param) throws Exception {
		Map<String,String> rsMap = selectOne("mhc.web.tg.healthmngtcnsl.selectBodyActFatMngtObj", param);
		return rsMap;  
	}
	
	public List<Map<String, String>> selectServiceSchedule(Map<String, Object> param) throws Exception {
		List<Map<String,String>> rsList = selectList("mhc.web.tg.healthmngtcnsl.selectServiceSchedule", param);
		return rsList;  
	}
	
	public Map<String, String> selectChkCreateSch(Map<String, Object> param) throws Exception {
		Map<String,String> rsMap = selectOne("mhc.web.tg.healthmngtcnsl.selectChkCreateSch", param);
		return rsMap;  
	}	
	
	public List<Map<String, String>> selectDeviceDistList(Map<String, Object> param) throws Exception {
		List<Map<String,String>> rsList = selectList("mhc.web.tg.healthmngtcnsl.selectDeviceDistList", param);
		return rsList;  
	}
	
	public void updateCnsl(Map<String, Object> param) throws Exception {
		update("mhc.web.tg.healthmngtcnsl.updateCnsl", param);
		
				
		//20190806 최종상담 시 상담순번 관련하여 로직 순서 변경
		if("6".equals(param.get("CNSL_NO"))){	//최종상담 완료 시 대상자 졸업처리
			update("mhc.web.tg.healthmngtcnsl.updateTrgterSttus_90", param);
		}

		//월간리포트 4차수 순번 조회
		Map<String, Object> rsMap = selectOne("mhc.web.tg.healthmngtcnsl.selectAutoSendCnslSn",param);	
		
		if(StringUtil.nvl(String.valueOf(rsMap)) != ""){
			String cnslSn = StringUtil.nvl(String.valueOf(rsMap.get("CNSL_SN")));	
			param.put("CNSL_SN", cnslSn);
			update("mhc.web.tg.healthmngtcnsl.updateCnslAutoSendSetYn", param);			
		}
		

		
		//중간 방문 상담 시 월간리포트 자동 발송 여부 설정
		/*
		if("3".equals(param.get("CNSL_NO"))){
			String cnslSn = "";			
			Map<String, Object> rsMap = selectOne("mhc.web.tg.healthmngtcnsl.selectAutoSendCnslSn",param);	
			cnslSn = rsMap.get("CNSL_SN").toString();
			
			System.out.println("rsMap :::::::::::::: " + rsMap);
			
			

		}		
		*/	

		
		
		

	}
	
	public void updateCnslContMngt(Map<String, Object> param) throws Exception {
		update("mhc.web.tg.healthmngtcnsl.updateCnslContMngt", param);
		update("mhc.web.tg.healthmngtcnsl.updateCnslContMngtHist", param);
	}
	
	public void updateBloodPressPymnt(Map<String, Object> param) throws Exception {
		update("mhc.web.tg.healthmngtcnsl.updateBloodPressPymnt", param);
	}
	
	public void updateBloodSugarPymnt(Map<String, Object> param) throws Exception {
		update("mhc.web.tg.healthmngtcnsl.updateBloodSugarPymnt", param);
	}
	
	public void insertCreateSchedule(Map<String, Object> param) throws Exception {
		//스케줄 삭제 후 재생성
		update("mhc.web.tg.healthmngtcnsl.deleteSchedule", param);		
		insert("mhc.web.tg.healthmngtcnsl.insertCreateSchedule", param);
	}
	
	public void updateSvcBgnDe(Map<String, Object> param) throws Exception {
		update("mhc.web.tg.healthmngtcnsl.updateSvcBgnDe", param);
	}
	
	public Map<String, String> selectServeyList(Map<String, Object> param) throws Exception {
		Map<String, String> rsMap = selectOne("mhc.web.tg.healthmngtcnsl.selectServeyList", param);
		return rsMap;
	}
	
	public List<Map<String, String>> selectServeyResultList(Map<String, Object> param) throws Exception {
		List<Map<String,String>> rsList = selectList("mhc.web.tg.healthmngtcnsl.selectServeyResultList", param);
		return rsList;
	}
	
	public List<Map<String, String>> selectHealthCnslDe(Map<String, Object> param) throws Exception {
		List<Map<String,String>> rsList = selectList("mhc.web.tg.healthmngtcnsl.selectHealthCnslDe", param);
		return rsList;
	}
	
	public String callProcCnslAllIns(Map<String, Object> param) {
		return selectOne("mhc.web.tg.healthmngtcnsl.callProcCnslAllIns", param);
	}
	
	public int updateVisitCnslDe(Map<String, Object> param) throws Exception {
		update("mhc.web.tg.healthmngtcnsl.updateVisitCnslDe", param);						
		return update("mhc.web.tg.healthmngtcnsl.updateVisitSchDe", param);
	}
	
	public List<Map<String, Object>> selectCnslRsltExamList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = new ArrayList<Map<String,Object>>();
		if(param.get("preTrgterYn") == "Y"){
			rsList = selectList("mhc.web.tg.healthmngtcnsl.selectSvcJoinMngtCnslRsltExamList", param);
		}else{
			rsList = selectList("mhc.web.tg.healthmngtcnsl.selectCnslRsltExamList", param);
		}
		return rsList;
	}
	
	public List<Map<String, Object>> selectNurtObjList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.tg.healthmngtcnsl.selectNurtObjList", param);
		return rsList;
	}
	
	public List<Map<String, Object>> selectMissionResultList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.tg.healthmngtcnsl.selectMissionResultList", param);
		return rsList;
	}
	
	public List<Map<String, Object>> selectIntensiveNurtCnslList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.tg.healthmngtcnsl.selectIntensiveNurtCnslList", param);
		return rsList;
	}
	
	public List<Map<String, Object>> selectMealDiaryWeekCalList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.tg.healthmngtcnsl.selectMealDiaryWeekCalList", param);
		return rsList;
	}
	
	public Map<String, Object> selectMealDiaryDayAvgPer(Map<String, Object> param) throws Exception {
		Map<String, Object> rsMap = selectOne("mhc.web.tg.healthmngtcnsl.selectMealDiaryDayAvgPer", param);
		return rsMap;
	}
	
	public Map<String, Object> selectCPFPer(Map<String, Object> param) throws Exception {
		Map<String, Object> rsMap = selectOne("mhc.web.tg.healthmngtcnsl.selectCPFPer", param);
		return rsMap;
	}
	
	public List<Map<String, Object>> selectBodyActObjList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.tg.healthmngtcnsl.selectBodyActObjList", param);
		return rsList;
	}
	
	public Map<String, Object> selectBodyActAnalysis(Map<String, Object> param) throws Exception {
		Map<String, Object> rsMap = selectOne("mhc.web.tg.healthmngtcnsl.selectBodyActAnalysis", param);
		return rsMap;
	}
	
	public List<Map<String, Object>> selectBodyActWeekList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.tg.healthmngtcnsl.selectBodyActWeekList", param);
		return rsList;
	}
	
	public Map<String, Object> selectNoDataCheckInfo(Map<String, Object> param) throws Exception {
		Map<String, Object> rsMap = selectOne("mhc.web.tg.healthmngtcnsl.selectNoDataCheckInfo", param);
		return rsMap;
	}
	
	public List<Map<String, Object>> selectWeekDayActList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.tg.healthmngtcnsl.selectWeekDayActList", param);
		return rsList;
	}
	
	public List<Map<String, Object>> selectMissionResultList2(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.tg.healthmngtcnsl.selectMissionResultList2", param);
		return rsList;
	}
	
	public List<Map<String, Object>> selectMealDiaryResult(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.tg.healthmngtcnsl.selectMealDiaryResult", param);
		return rsList;
	}
	
	public List<Map<String, Object>> selectCvdPointList(Map<String, Object> param) throws Exception{
		List<Map<String, Object>> rsList = selectList("mhc.web.tg.healthmngtcnsl.selectCvdPointList", param);
		return rsList;
	}
	
	public Map<String, Object> selectBasicUserInfo(Map<String, Object> param) throws Exception{
		Map<String, Object> rsMap = selectOne("mhc.web.tg.healthmngtcnsl.selectBasicUserInfo", param);
		return rsMap;
	}
	
	public Map<String, Object> selectMealtNutriRsltInfo(Map<String, Object> param) throws Exception{
		Map<String, Object> rsMap = selectOne("mhc.web.tg.healthmngtcnsl.selectMealtNutriRsltInfo", param);
		return rsMap;
	}
	
	// 금연절주컨텐츠 이전 상담 수신여부 조회
	public Map<String, Object> selectCnslYnList(Map<String, Object> param) throws Exception{
		Map<String, Object> rsMap = selectOne("mhc.web.tg.healthmngtcnsl.selectCnslYnList", param);
		return rsMap;
	}
}
