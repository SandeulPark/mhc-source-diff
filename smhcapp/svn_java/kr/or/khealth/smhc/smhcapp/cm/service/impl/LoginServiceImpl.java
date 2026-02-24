package kr.or.khealth.smhc.smhcapp.cm.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.khealth.smhc.smhcapp.cm.service.LoginService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("smhcapp.cm.LoginService")
public class LoginServiceImpl extends EgovAbstractServiceImpl implements LoginService{
	
	@Resource(name="smhcapp.cm.LoginDAO")
    private LoginDAO loginDAO;

	@Override
	public Map<String, String> selectUser(Map<String, Object> param)
			throws Exception {
		return loginDAO.selectUser(param);
	}
	
	@Override
	public void insertUserMobileInfo(Map<String, Object> param)
			throws Exception {
		loginDAO.insertUserMobileInfo(param);
	}

	@Override
	public Map<String, Object> changeUserIdentify(Map<String, Object> param) 
			throws Exception {
		return loginDAO.changeUserIdentify(param);
	}
	
	@Override
	public Map<String, Object> regUserIdentify(Map<String, Object> param) 
			throws Exception {
		return loginDAO.regUserIdentify(param);
	}
	
	@Override
	public Map<String, Object> getUserInfo(Map<String, Object> param) 
			throws Exception {
		return loginDAO.getUserInfo(param);
	}

	@Override
	public Map<String, Object> chkExistNotice(Map<String, Object> param) throws Exception {		
		return loginDAO.chkExistNotice(param);
	}

	@Override
	public void insertDoNotShowNotice(Map<String, Object> param) throws Exception {
		loginDAO.insertDoNotShowNotice(param);
	}

	@Override
	public Map<String, Object> searchIdentify(Map<String, Object> param) throws Exception {		
		return loginDAO.searchIdentify(param);
	}

	@Override
	public Map<String, Object> changePwd(Map<String, Object> param) throws Exception {
		return loginDAO.changePwd(param);
	}
	
	@Override
	public Integer selectUserCnt(Map<String, Object> param) throws Exception {
		return loginDAO.selectUserCnt(param);
	}

	@Override
	public void updateUserDupMobileInfo(Map<String, Object> param) throws Exception {
		loginDAO.updateUserDupMobileInfo(param);
	}
	
	@Override
	public int getLoginFailCnt(Map<String, Object> param) throws Exception {
		return loginDAO.getLoginFailCnt(param);
	}
	
	@Override
	public void updateLoginFailCnt(Map<String, Object> param) throws Exception {
		loginDAO.updateLoginFailCnt(param);
	}
	
	@Override
	public void insertUnlockHist(Map<String, Object> param) throws Exception {
		loginDAO.insertUnlockHist(param);
	}

	@Override
	public void insertLastConnectDt(Map<String, Object> param) throws Exception {
		loginDAO.insertLastConnectDt(param);
	}
	
	@Override
	public Map<String, String> selectUserMobileInfo(Map<String, Object> param) throws Exception {
		return loginDAO.selectUserMobileInfo(param);
	}
	
	/*@Override
	public Integer getCertificationCnt(Map<String, Object> param) throws Exception {
		return loginDAO.getCertificationCnt(param);
	}
	
	@Override
	public void updateCertificationCnt(Map<String, Object> param) throws Exception {
		loginDAO.updateCertificationCnt(param);
	}*/

	@Override
    public Map<String, Object> verifyUserPassword(Map<String, Object> param) throws Exception {
        Map<String, Object> rsMap = new HashMap<String,Object>();
        
        int count = loginDAO.selectUserPasswordMatchCount(param);
        
        if(count > 0) {
            rsMap.put("result", "success");
            rsMap.put("message", "본인 확인이 완료되었습니다.");
        } else {
            throw new Exception("비밀번호가 일치하지 않습니다.");
        }
        
        return rsMap;
    }
}
