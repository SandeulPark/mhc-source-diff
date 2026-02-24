package kr.or.khealth.smhc.smhcapp.cm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.or.khealth.smhc.common.DMultiEgovAbstractMapper;

@Repository("smhcapp.cm.LoginDAO")
public class LoginDAO extends DMultiEgovAbstractMapper{

	public Map<String, String> selectUser(Map<String, Object> param) throws Exception {
		Map<String,String> rsMap = new HashMap<String, String>();
		rsMap.put("loginChk", "NOREG");
		Map<String,String> userMap = selectOne("cm.login.selectUser", param);
		if(userMap != null){
			rsMap = userMap;
			Map<String,String> cmmnCdUseMap = selectOne("common.cmmn.selectCmmnCdUseYn", param);
			if(getLoginFailCnt(param) > 4 && cmmnCdUseMap.get("USE_YN").equals("Y")) {
				rsMap.put("loginChk", "LOCK");
				return rsMap;
			}
			rsMap.put("loginChk", "PASS");
		}else{
			rsMap.put("loginChk", "PWERR");
		}
		return rsMap;  
	}
	
	public void insertUserMobileInfo(Map<String, Object> param)
			throws Exception {
		insert("cm.login.insertUserMobileInfo", param);
	}
	
	public Map<String, Object> changeUserIdentify(Map<String, Object> param) throws Exception{
		Map<String,Object> rsMap = new HashMap<String, Object>();	
		int encountPhisInfoCnt = selectOne("cm.login.encountPhisInfoCnt", param);
		int rsInt = 0; 
		if(encountPhisInfoCnt==1){
			Map<String, Object> encountMap = new HashMap<String,Object>();
			encountMap.put("loginId", param.get("NEW_ID"));
			int userCnt = selectOne("cm.login.selectUserEnchanced", param);
			if(userCnt == 0){
				rsInt += update("cm.login.changeUserIdentify", param);
				rsMap.put("RETURN_STTUS", "INFO_COMMITED");
			}else {
				rsMap.put("RETURN_STTUS", "SAME_ID");
			}
		}else {
			rsMap.put("RETURN_STTUS", "NO_SEARCH");
		}
		return rsMap;
	}
	
	public Map<String, Object> regUserIdentify(Map<String, Object> param) throws Exception{
		Map<String,Object> rsMap = new HashMap<String, Object>();	
		//PHIS 등록 번호, PHIS 기관코드로 등록 유무 확인
		int regPhisInfoCnt = selectOne("cm.login.encountPhisInfoCnt", param);
				
		if(regPhisInfoCnt == 0) { //데이터가 없으면
			rsMap.put("RETURN_STTUS", "NO_SEARCH");
		}else if(regPhisInfoCnt == 1){ //데이터가 하나면 등록된 로그인 아이디 확인
			String loginId = selectOne("cm.login.selectLoginId", param);
			
			System.out.println("#### loginId ===> " + loginId);
						
			if(loginId == null) {	//등록된 로그인 아이디가 없으면 로그인 아이디 중복 여부 확인			
				int userCnt = selectOne("cm.login.selectUserEnchanced", param);
				if(userCnt == 0) { //로그인 아이디 중복이 아니면 등록
					update("cm.login.regUserIdentify", param);
					rsMap.put("RETURN_STTUS", "INFO_COMMITED");					
				}else { //로그인 아이디 중복일 경우
					rsMap.put("RETURN_STTUS", "SAME_ID");
				}				
			}else { //등록된 로그인 아이디가 있을 경우
				rsMap.put("RETURN_STTUS", "ALREADY_REG");
			}			
		}else { //데이터가 2개 이상일 경우
			rsMap.put("RETURN_STTUS", "MORE_ID");
		}
		
		return rsMap;
	}
	
	public Map<String, Object> getUserInfo(Map<String, Object> param) throws Exception{
		Map<String,Object> rsMap = selectOne("cm.login.getUserInfo", param);
		return rsMap;
	}

	public Map<String, Object> chkExistNotice(Map<String, Object> param) {	
		String testYn = selectOne("cm.login.chkNoticeTest", param);		
		param.put("TEST_YN", testYn);
		int noticeCnt = selectOne("cm.login.chkExistNotice", param);
		Map<String,Object> rsMap = new HashMap<String, Object>();		
		if(noticeCnt > 0) {			
			rsMap = selectOne("cm.login.chkExistNoticeInfo", param);			
			rsMap.put("NOTICE_CNT", noticeCnt);
		}else {
			rsMap.put("NOTICE_CNT", 0);
		}
		return rsMap;
	}

	public void insertDoNotShowNotice(Map<String, Object> param) {
		insert("cm.login.insertDoNotShowNotice", param);
	}

	public Map<String, Object> searchIdentify(Map<String, Object> param) {
		Map<String, Object> rsMap = new HashMap<String,Object>();
		
		int encountIdentifyCnt = selectOne("cm.login.encountIdentifyCnt", param);
		
		if(encountIdentifyCnt > 0) {	
			List<Map<String,String>> rsList = selectList("cm.login.searchIdentify", param);
			rsMap.put("USER_LIST", rsList);			
		}else {
			rsMap.put("RETURN_STTUS", "NO_SEARCH");
		}
		
		return rsMap;
	}

	public Map<String, Object> changePwd(Map<String, Object> param) {
		Map<String, Object> rsMap = new HashMap<String,Object>();
		
		param.put("MODE", "pwd");
		int encountIdentifyCnt = selectOne("cm.login.encountIdentifyCnt", param);
		
		if(encountIdentifyCnt > 0) {	
			update("cm.login.changePwd", param);
			rsMap.put("RETURN_STTUS", "SUCCESS");			
		}else {
			rsMap.put("RETURN_STTUS", "NO_SEARCH");
		}
		
		return rsMap;
	}
	
	public Integer selectUserCnt(Map<String, Object> param) {
		return selectOne("cm.login.selectUserCnt", param);
	}

	public void updateUserDupMobileInfo(Map<String, Object> param) {
		update("cm.login.updateUserDupMobileInfo",param);
	}
	
	public int getLoginFailCnt(Map<String, Object> param) {
		return selectOne("cm.login.getLoginFailCnt", param);
	}
	
	public void updateLoginFailCnt(Map<String, Object> param) {
		update("cm.login.updateLoginFailCnt",param);
	}
	
	public void insertUnlockHist(Map<String, Object> param) {
		update("cm.login.insertUnlockHist",param);
	}
	
	public void insertLastConnectDt(Map<String, Object> param) {
		update("cm.login.insertLastConnectDt",param);
	}
	
	public Map<String, String> selectUserMobileInfo(Map<String, Object> param) {
		Map<String,String> rsMap = selectOne("cm.login.selectUserMobileInfo", param);
		return rsMap;
	}
	
	/*public Integer getCertificationCnt(Map<String, Object> param) {
		return selectOne("cm.login.getCertificationCnt", param);
	}
	
	public void updateCertificationCnt(Map<String, Object> param) {
		update("cm.login.updateCertificationCnt",param);
	}*/

	 public int selectUserPasswordMatchCount(Map<String, Object> param) throws Exception {
        return selectOne("cm.login.selectUserPasswordMatchCount", param);
    }
}
