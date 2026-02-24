package kr.or.khealth.smhc.smhcweb.tg.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.khealth.smhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;

/**
 * @Class Name : TrgterInfoMngtDAO.java
 * @Description : 관리자 WEB에서 사용하는 어르신 대상자 등록  DataBase 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2020.09.16		양현우			최초생성
 *
 * @author thejoin
 * @since 2020.09.16
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */



@Repository("web.tg.SeniorTrgterRegDAO")
public class SeniorTrgterRegDAO extends DMultiEgovAbstractMapper {

	public List<Map<String, Object>> selectSeniorTrgterList(Map<String, Object> param)throws Exception {
		List<Map<String, Object>> rsList = selectList("smhc.web.tg.seriortrgterreg.selectSeniorTrgterList",param);
		return rsList;
	}

	public Map<String, Object> selectSeniorTrgterDtls(Map<String, Object> param)throws Exception {
		Map<String, Object> rsMap = selectOne("smhc.web.tg.seriortrgterreg.selectSeniorTrgterDtls",param);
		return rsMap;
	}
	
	public List<Map<String, Object>> selectManagerCombo(Map<String, Object> param)throws Exception {
		List<Map<String, Object>> rsList = selectList("smhc.web.tg.seriortrgterreg.selectManagerCombo",param);
		return rsList;
	}	
	
	public int insertSeniorTrgter(Map<String, Object> param) throws Exception {
		String newUserId = selectOne("smhc.web.tg.seriortrgterreg.selectGetNewUserId",param);	

		param.put("USER_ID", newUserId);
		
		int rsInt = insert("smhc.web.tg.seriortrgterreg.insertSeniorInfo", param);

		insert("smhc.web.tg.seriortrgterreg.insertSeniorSvcMngt", param);			
		insert("smhc.web.tg.seriortrgterreg.insertSeniorMngInfo", param);
		insert("smhc.web.tg.seriortrgterreg.insertSeniorVisitSch", param);
		insert("smhc.web.tg.seriortrgterreg.insertSeniorServey", param);		
		
		return rsInt;
	}
	
	public int updateSeniorTrgter(Map<String, Object> param) throws Exception {
		int rsInt = update("smhc.web.tg.seriortrgterreg.updateSeniorInfo", param);
		update("smhc.web.tg.seriortrgterreg.updateSeniorMngInfo", param);
		update("smhc.web.tg.seriortrgterreg.updateSeniorVisitSch", param);
		return rsInt;
	}
	
	public Map<String, Object> selectSeniorDuplicationSch(Map<String, Object> param)throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
	
		if("PHIS_REG_NO".equals(param.get("DIV"))){
			rsMap = selectOne("smhc.web.tg.seriortrgterreg.selectDuplicationSchPhisRegNo",param);
		}else if("LOGIN_ID".equals(param.get("DIV"))){
			rsMap = selectOne("smhc.web.tg.seriortrgterreg.selectDuplicationSchLoginId",param);
		}		
		return rsMap;
	}
	
	public Map<String, Object> selectSeniorDuplicationMobileNo(Map<String, Object> param)throws Exception {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		rsMap = selectOne("smhc.web.tg.seriortrgterreg.selectSeniorDuplicationMobileNo",param);
		return rsMap;
	}

	public int seniorTrgterDeleteInfo(Map<String, Object> param) {
		insert("smhc.web.tg.seriortrgterreg.logingDeleteInfo", param);
		int rsInt = delete("smhc.web.tg.seriortrgterreg.seniorTrgterDeleteInfo", param);
		/*delete("smhc.web.tg.seriortrgterreg.seniorTrgterDeleteSvcMngt", param);
		delete("smhc.web.tg.seriortrgterreg.seniorTrgterDeleteCnslMngt", param);
		delete("smhc.web.tg.seriortrgterreg.seniorTrgterDeleteMatchMngt", param);
		delete("smhc.web.tg.seriortrgterreg.seniorTrgterDeleteSvrveyMastr", param);*/
		return rsInt;
	}

	public boolean updateLoginFailCnt(Map<String, Object> param) throws Exception {
		try {
			update("smhc.web.tg.seriortrgterreg.updateLoginFailCnt", param);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public int insertUnlockHist(Map<String, Object> param) throws Exception {
		return insert("smhc.web.tg.seriortrgterreg.insertUnlockHist", param);
	}
}
