package kr.go.mhc.mhcweb.tg.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import kr.go.mhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;

/**
 * @Class Name : HealthExamMngtDAO.java
 * @Description : 관리자 WEB에서 사용하는 건강검진 관리 업무 DataBase 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.08.20		이은주			최초생성
 *
 * @author gst
 * @since 2016.08.20
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Repository("web.tg.HealthExamMngtDAO")
public class HealthExamMngtDAO extends DMultiEgovAbstractMapper {

	//건강검진관리 조회
	public List<Map<String, String>> getHealthExamList(Map<String, Object> param) throws Exception {
		List<Map<String, String>> rsList = selectList("mhc.web.tg.healthexammngt.healthExamList", param);
		return rsList;
	}
	
	//건강검진관리 상세 조회
	public Map<String, Object> getHealthExamMngtDtls(Map<String, Object> param) throws Exception {
		Map<String, Object> rsMap = selectOne("mhc.web.tg.healthexammngt.healthExamMngtDtls", param);
		return rsMap;
	}
	
	//건강검진관리 계측정보저장
	public void updateHealthExamBody(Map<String, Object> param) throws Exception {
		update("mhc.web.tg.healthexammngt.updateHealthExamBody", param);
	}
	
	//건강검진관리 혈액검사정보저장
	public void updateHealthExamBld(Map<String, Object> param) throws Exception {
		update("mhc.web.tg.healthexammngt.updateHealthExamBld", param);
	}

	// 건강검진관리 만성질환정보 저장
	public void updateHealthExamChronic(Map<String, Object> param) throws Exception {

		System.out.println("############################################ dao ");
		update("mhc.web.tg.healthexammngt.updateHealthExamChronic", param);
		System.out.println("############################################ dao 끝 ");
		
	}

	//건강검진관리 인바디 정보 저장 검사일자 조회
//	public Map<String, Object> getSelHealthExamDE(Map<String, Object> param) throws Exception {
//		Map<String, Object> rsMap = selectOne("mhc.web.tg.healthexammngt.selHealthExamDE", param);
//		return rsMap;
//	}
	
	public List<Map<String, Object>> getSelHealthExamDEList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.tg.healthexammngt.selHealthExamDEList", param);
		return rsList;
	}
	
	//건강검진관리 인바디 정보 체성분결과저장
	public void updateHealthBodyComp(Map<String, Object> param) throws Exception {
		update("mhc.web.tg.healthexammngt.updateHealthBodyComp", param);
	}
	
	//건강검진관리 검사완료
	public Map<String, Object> updateHealthComplete(Map<String, Object> param) throws Exception {
		Map<String, Object> rsMap = selectOne("mhc.web.tg.healthexammngt.selectCnslNeedYn", param);
		Map<String, Object> rsMap2 = selectOne("mhc.web.tg.healthexammngt.selectPreTrgeterSttus", param);
		param.put("CNSL_NEED_YN", rsMap.get("CNSL_NEED_YN"));
		update("mhc.web.tg.healthexammngt.updateHealthComplete", param);
		update("mhc.web.tg.healthexammngt.updateSvcCnsl", param);
		if (rsMap2.get("PRE_TRGTER_STTUS").toString().equals("10")){
			update("mhc.web.tg.pretrgtermngt.updateChoiceRequest", param);
			return rsMap2;
		}else{
		return rsMap;
		}
	}
	
	//건강검진관리 POCT
	public void insertHealthExamDta(Map<String, Object> param) throws Exception {
		update("mhc.web.tg.healthexammngt.insertHealthExamDta", param);
	}
	
	//건강검진관리 상세 조회
	public Map<String, Object> selectHealthMngtDetail(Map<String, Object> param) throws Exception {
		Map<String, Object> rsMap = selectOne("mhc.web.tg.healthexammngt.selectHealthMngtDetail", param);
		return rsMap;
	}
	
	//건강검진관리 저장여부 조회
	public Map<String, Object> selectSaveYnCheck(Map<String, Object> param) throws Exception {
		Map<String, Object> rsMap = selectOne("mhc.web.tg.healthexammngt.selectSaveYnCheck", param);
		return rsMap;
	}
	
	//건강검진관리 삭제
	public void delHealthMngt(Map<String, Object> param) throws Exception {

		String preTrgterNo[] = param.get("PRE_TRGTER_NO").toString().replaceAll("&quot;", "").replaceAll("\\[", "").replaceAll("\\]", "").split(",");

				List userList = new ArrayList();
				for(int i=0; i < preTrgterNo.length; i++) {
					userList.add(preTrgterNo[i]);
				}

				param.put("userList", userList);

		delete("mhc.web.tg.healthexammngt.delHealthMngt", param);
	}
	
	//체성분 조회
	public Map<String, Object> getHealthBodyComp(Map<String, Object> param) throws Exception {
		Map<String, Object> rsMap = selectOne("mhc.web.tg.healthexammngt.selectHealthBodyComp", param);
		return rsMap;
	}
	
	//주민등록번호 저장
	public Map<String, Object> updateResNo(Map<String, Object> param) throws Exception {

		Map<String, Object> rsMap = selectOne("mhc.web.tg.healthexammngt.selectChckBirth", param);
		
		if(param.get("RES_NO_AGREE").toString().equals("N")){
			update("mhc.web.tg.healthexammngt.updateResNo", param);
			update("mhc.web.tg.healthexammngt.updateDigiSign", param);			
			rsMap.put("updateSuccYn", "E");			
		}else{
			update("mhc.web.tg.healthexammngt.updateResNo", param);				
			update("mhc.web.tg.healthexammngt.updateDigiSign2", param);		
			
			if(rsMap.get("BIRTH_CHCK").toString().equals("1")){
				rsMap.put("updateSuccYn", "Y");
			}else{
				rsMap.put("updateSuccYn", "N");
			}			
			
		}
		return rsMap;
	}
	
	//생년월일 수정
	public void updateBirth(Map<String, Object> param) throws Exception {
		insert("mhc.web.tg.healthexammngt.insertBirthHist", param);
		update("mhc.web.tg.healthexammngt.updateBirth", param);
		//update("mhc.web.tg.healthexammngt.updateResNo", param);
	}
	
	public Map<String, Object> healthExamCntChk(Map<String, Object> param) throws Exception{
		Map<String, Object> rsMap = selectOne("mhc.web.tg.healthexammngt.selectHealthExamCountCheck", param);
		return rsMap;
	}

	
	public Map<String, Object> poctSaveYnChk(Map<String, Object> param) throws Exception{
		// TODO Auto-generated method stub
		Map<String, Object> rsMap = selectOne("mhc.web.tg.healthexammngt.poctSaveYnChk", param);
		return rsMap;
	}
}
