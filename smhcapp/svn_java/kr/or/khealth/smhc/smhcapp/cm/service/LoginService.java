package kr.or.khealth.smhc.smhcapp.cm.service;

import java.util.Map;

public interface LoginService {
	
	public Map<String, String> selectUser(Map<String, Object> param) throws Exception;
	
	public void insertUserMobileInfo(Map<String, Object> param) throws Exception;
	
	public Map<String, Object> changeUserIdentify(Map<String, Object> param) throws Exception;
	
	public Map<String, Object> regUserIdentify(Map<String, Object> param) throws Exception;
	
	public Map<String, Object> getUserInfo(Map<String, Object> param) throws Exception;

	public Map<String, Object> chkExistNotice(Map<String, Object> param) throws Exception;

	public void insertDoNotShowNotice(Map<String, Object> param) throws Exception;

	public Map<String, Object> searchIdentify(Map<String, Object> param) throws Exception;

	public Map<String, Object> changePwd(Map<String, Object> param)  throws Exception;
	
	public Integer selectUserCnt(Map<String, Object> param) throws Exception;

	public void updateUserDupMobileInfo(Map<String, Object> param) throws Exception;
	
	public int getLoginFailCnt(Map<String, Object> param) throws Exception;
	
	public void updateLoginFailCnt(Map<String, Object> param) throws Exception;
	
	public void insertUnlockHist(Map<String, Object> param) throws Exception;
	
	public void insertLastConnectDt(Map<String, Object> param) throws Exception;
	
	public Map<String, String> selectUserMobileInfo(Map<String, Object> param) throws Exception;
	
	/*public Integer getCertificationCnt(Map<String, Object> param) throws Exception;
	
	public void updateCertificationCnt(Map<String, Object> param) throws Exception;*/

	
    public Map<String, Object> verifyUserPassword(Map<String, Object> param) throws Exception;
}
