package kr.go.mhc.mhcweb.tg.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.go.mhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;

/**
 * @Class Name : TrgterSelMngtDAO.java
 * @Description : 관리자 WEB에서 사용하는 신체활동비만 상담업무 DataBase 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일				수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.08.23		이태석			최초생성
 *
 * @author gst
 * @since 2016.08.23
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Repository("web.tg.BodyActObstyCnslDAO")
public class BodyActObstyCnslDAO extends DMultiEgovAbstractMapper{

	public List<Map<String, String>> getBodyActObstyCnslList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
//		List<Map<String,String>> rsList = selectList("mhc.web.tg.bodyactobstycnsl.bodyactobstycnsllist", param);	
		return null;  
	}
	
	public int getBodyActObstyCnslCount() throws Exception {
		// TODO Auto-generated method stub
//		int rsInt = selectOne("mhc.web.tg.bodyactobstycnsl.bodyactobstycnslsttuslcount");	
		return 0;  
	}
	
	public Map<String, String> getBodyActObstyCnslDtls(Map<String, Object> param) throws Exception {		
		// TODO Auto-generated method stub
		Map<String,String> rsMap = selectOne("mhc.web.tg.bodyactobstycnsl.selectBodyActObstyCnslDtls",param);	
		return rsMap;  
	}
	
	public Map<String,String> getActEquipTestYn(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		Map<String,String> rsMap = selectOne("mhc.web.tg.bodyactobstycnsl.selectActEquipTestYn", param);
		return rsMap;  
	}
	
	public void getActEquipPymntY(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		update("mhc.web.tg.bodyactobstycnsl.updateActEquipPymntY", param);
		update("mhc.web.tg.bodyactobstycnsl.updateActEquipSttus", param);
	}
	
	public List<Map<String, String>> getCnslActRegDeList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhc.web.tg.bodyactobstycnsl.selectCnslActRegDeList", param);
		return rsList;  
	}
	
	public Map<String, String> getCnslAct(Map<String, Object> param) throws Exception {		
		// TODO Auto-generated method stub
		Map<String,String> rsMap; 
		rsMap = selectOne("mhc.web.tg.bodyactobstycnsl.selectCnslAct",param);
		if(rsMap == null){
			int cnslDesel_size = Integer.parseInt(param.get("cnslDesel_size").toString());
			int cnslDeSel_index = Integer.parseInt(param.get("cnslDeSel_index").toString());
			if(cnslDesel_size != 1 && cnslDesel_size != cnslDeSel_index+1){
				String cnslSn =  param.get("CNSL_SN").toString();
				param.remove("CNSL_SN"); 
				param.put("CNSL_SN", param.get("CNSL_SN_before"));
				param.put("NOW_CNSL_NO", cnslSn);
				rsMap = selectOne("mhc.web.tg.bodyactobstycnsl.selectCnslAct",param);
				rsMap.put("CNSL_SN_beforeYn", "Y");
				rsMap.put("cnslActContYn", "Y");
			}else{
				rsMap = new HashMap<String, String>(); 
				rsMap.put("cnslActContYn", "N");				
			}
		}else{
			rsMap.put("CNSL_SN_beforeYn", "N");
			rsMap.put("cnslActContYn", "Y");
		}
		
		return rsMap;  
	}
	
	public List<Map<String, String>> getRiskFactorList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhc.web.tg.bodyactobstycnsl.selectRiskFactorList", param);
		return rsList;  
	}
	
	public Map<String, String> getActCnslSn(Map<String, Object> param) throws Exception {		
		// TODO Auto-generated method stub
		Map<String,String> rsMap = selectOne("mhc.web.tg.bodyactobstycnsl.selectGclas",param);
		return rsMap;  
	}
	
	public void getSaveActCnsl(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub		

		param.put("saveGb", "Y");
		insert("mhc.web.tg.bodyactobstycnsl.saveActCnsl", param);
		update("mhc.web.tg.healthmngtcnsl.updateCnsl", param);


		//중간 방문 상담 시 집중상담 자동 발송 여부 설정
		if("3".equals(param.get("CNSL_NO"))){
			String cnslSn = "";			
			Map<String, Object> rsMap = selectOne("mhc.web.tg.bodyactobstycnsl.selectAutoSendCnslSn",param);	
			cnslSn = rsMap.get("AUTO_CNSL_SN").toString();
			param.put("AUTO_CNSL_SN", cnslSn);
			update("mhc.web.tg.bodyactobstycnsl.updateCnslAutoSendSetYn", param);
		}		
		
		
		if("6".equals(param.get("CNSL_NO"))){
			update("mhc.web.tg.healthmngtcnsl.updateTrgterSttus_90", param);
		}
		//이력 테이블 업데이트
		param.put("deGubun", "TN");
		update("mhc.web.tg.bodyactobstycnsl.updateBodyObstyCnsl", param);
	}
	
	/* 2017.04.12 이태석 추가 (운동 정보 팝업) */
	public List<Map<String, String>> getBodyPartExcsList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhc.web.tg.bodyactobstycnsl.selectBodyPartExcsList", param);
		return rsList;  
	}
	
	public Map<String, String> getMaxOxyIntakeAmJudge(Map<String, Object> param) throws Exception {		
		// TODO Auto-generated method stub
		Map<String,String> rsMap = selectOne("mhc.web.tg.bodyactobstycnsl.selectMaxOxyIntakeAmJudge",param);
		return rsMap;  
	}
	
	/**
	 * 운동 목표 설정 변경
	 * @param param
	 * @throws Exception
	 */
	public void updateBodyObstyCnsl(Map<String, Object> param) throws Exception{
		// TODO Auto-generated method stub
		try{
			//이력 테이블에 없는 상담내용 저장
			String updateGb = param.get("updateGb")==null?"":(String)param.get("updateGb");
			param.put("saveGb", "N");
			
			//1. 변경 전 목표설정 데이터 이력 관리 
//			param.put("deGubun", "TN");
//			update("mhc.web.tg.bodyactobstycnsl.updateBodyObstyCnsl", param);
			
			if("Y".equals(updateGb)){
				//2. 입력정보 저장
				String autoManuClf = param.get("AUTO_MANU_CLF")==null?"M":(String)param.get("AUTO_MANU_CLF");
				param.put("autoManuClf", autoManuClf);
				insert("mhc.web.tg.bodyactobstycnsl.saveActCnsl", param);
				update("mhc.web.tg.healthmngtcnsl.updateCnsl", param);
				
				//3. 이력테이블 저장
				param.put("deGubun", "TH");
				update("mhc.web.tg.bodyactobstycnsl.updateBodyObstyCnsl", param);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 상담_활동_이력 카운트 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> getCnslHistCnt(Map<String,Object> param) throws Exception{
		Map<String,String> rsMap = selectOne("mhc.web.tg.bodyactobstycnsl.selectCnslHistCnt", param);
		return rsMap;
	}
	
	
	/**
	 * 추천 운동 선정 목록 추가
	 * @param param
	 * @throws Exception
	 */
	public void addRecomExcsInfo(Map<String, Object> param) throws Exception{
		String[] modalwrapInfo = param.get("modalwrapInfo").toString().split(",");	
		int modalwrapCnt = Integer.parseInt(param.get("modalwrapCnt").toString());
		Map<String, Object> modalwrapInfoMap = new HashMap<String, Object>();	


		try{			
			for(int i=0; i < modalwrapCnt; i++){
				modalwrapInfoMap.put("CNSL_SN", param.get("CNSL_SN"));
				modalwrapInfoMap.put("BODY_PART_EXCS_CD", modalwrapInfo[i]);
				insert("mhc.web.tg.bodyactobstycnsl.addRecomExcsInfo", modalwrapInfoMap);	
			}			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 추천 운동 선정 목록 삭제
	 * @param param
	 * @throws Exception
	 */
	public void deleteRecomExcsInfo(Map<String, Object> param) throws Exception{
		try{
			if(param.get("newRecomChck").equals("Y")){
				delete("mhc.web.tg.bodyactobstycnsl.deleteAllRecomExcsInfo", param);
			}else{
				delete("mhc.web.tg.bodyactobstycnsl.deleteRecomExcsInfo", param);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}	
	
	/**
	 * 추천 운동 선정 목록 조회
	 * @param param
	 * @throws Exception
	 */	
	public List<Map<String, String>> getRecomExcsSetList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhc.web.tg.bodyactobstycnsl.selectRecomExcsSetList", param);
		return rsList;  
	}	
	
	/**
	 * 목표 심박 계산 나이 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> getHRCalAge(Map<String,Object> param) throws Exception{
		Map<String,Object> rsMap = selectOne("mhc.web.tg.bodyactobstycnsl.selectHrCalAge", param);
		return rsMap;
	}
	
	/**
	 * 신체활동 상담 내용 정보 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selectBodyActCnslContInfo(Map<String, Object> param) throws Exception {
		Map<String, Object> rsMap = selectOne("mhc.web.tg.bodyactobstycnsl.selectBodyActCnslContInfo", param);
		return rsMap;
	}
	
	public List<Map<String, Object>> getRecomExcsTemplateList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.tg.bodyactobstycnsl.selectRecomExcsTempMastrList", param);
		return rsList;
	}
	
	public int getRecomExcsTemplateUpdate (Map<String, Object> param) {
		if(param.get("TEMPLATE_SN").equals("")){
			Map<String, Object> rsMap = selectOne("mhc.web.tg.bodyactobstycnsl.selectRecomExcsNewTempSn", param);
			param.put("TEMPLATE_SN",rsMap.get("TEMPLATE_SN"));
		}
		int rsInt = update("mhc.web.tg.bodyactobstycnsl.updateRecomExcsTempMastr", param);
		delete("mhc.web.tg.bodyactobstycnsl.deleteRecomExcsTemp", param);
		insert("mhc.web.tg.bodyactobstycnsl.insertRecomExcsTemp", param);
		return rsInt;
	}

	public int getRecomExcsTempDelOne (Map<String, Object> param) {
		int rsInt = delete("mhc.web.tg.bodyactobstycnsl.deleteRecomExcsTempOne", param);
		return rsInt;
	}
	
	public int getRecomExcsTemplateDel (Map<String, Object> param) {
		int rsInt = delete("mhc.web.tg.bodyactobstycnsl.deleteRecomExcsTempMastr", param);
		delete("mhc.web.tg.bodyactobstycnsl.deleteRecomExcsTemp", param);
		return rsInt;
	}
	
	public int getRecomExcsTemplateCnslUpdate (Map<String, Object> param) {		
		delete("mhc.web.tg.bodyactobstycnsl.deleteAllRecomExcsInfo", param);
		int rsInt = insert("mhc.web.tg.bodyactobstycnsl.insertRecomExcsTemplateCnsl", param);
		return rsInt;
	}
	
	public List<Map<String, String>> getRecomExcsTempList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> rsList = selectList("mhc.web.tg.bodyactobstycnsl.selectRecomExcsTempList", param);
		return rsList;  
	}
	
	public int getRecomExcsPreCnsllUpdate (Map<String, Object> param) {		
		delete("mhc.web.tg.bodyactobstycnsl.deleteAllRecomExcsInfo", param);
		int rsInt = insert("mhc.web.tg.bodyactobstycnsl.insertRecomExcsPreCnsllUpdate", param);
		return rsInt;
	}
}
