package kr.go.mhc.mhcapp.gn.service.impl;

import java.util.List;
import java.util.Map;

import kr.go.mhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;

@Repository("gn.gnrlLoginDAO")
public class GnrlLoginDAO extends DMultiEgovAbstractMapper{
	public int checkLoginId(Map<String, Object> param) throws Exception{
		return selectOne("mhcapp.gn.login.checkLoginId", param);
	}
	
	public Map<String, Object> userTypeCheck(Map<String, Object> param) throws Exception{
		return selectOne("mhcapp.gn.login.userTypeCheck", param);
	}
	
	public Map<String, String> login(Map<String, Object> param) throws Exception{
		return selectOne("mhcapp.gn.login.login", param);
	}
	
	public Map<String, String> snsLogin(Map<String, Object> param) throws Exception{
		return selectOne("mhcapp.gn.login.snsLogin", param);
	}
	
	public String selectGetUserId() throws Exception{
		return selectOne("mhcapp.gn.login.selectGetUserId");
	}

	public int userRegit(Map<String, Object> param) throws Exception{
		return insert("mhcapp.gn.login.userRegit", param);
	}
	
	public int gnUserRegit(Map<String, Object> param) throws Exception{
		return insert("mhcapp.gn.login.gnUserRegit", param);
	}
	
	public int userSnsRegit(Map<String, Object> param) throws Exception{
		return insert("mhcapp.gn.login.userSnsRegit", param);
	}
	
	public int updateToken(Map<String, Object> param) throws Exception{
		return update("mhcapp.gn.login.updateToken", param);
	}	
	public List<Map<String, Object>> findUser(Map<String, Object> param) throws Exception{
		return selectList("mhcapp.gn.login.findUser", param);		
		
	}
	public Map<String, Object> pwUpdate(Map<String, Object> param) throws Exception{
		return selectOne("mhcapp.gn.login.pwUpdate", param);
	}

	/**
	 * 회원 탈퇴
	 * @param
	 * @return
	 * @throws Exception
	 */
	public void userDropout(Map<String, Object> param) throws Exception {
		insert("mhcapp.gn.login.userBackup", param);
		update("mhcapp.gn.login.userDropout", param);
	}

	public Map<String, Object> withdrawCheckUser(Map<String, Object> param) throws Exception{
		return selectOne("mhcapp.gn.login.withdrawCheckUser", param);
	}

	public void withdrawUser(Map<String, Object> param) throws Exception{
		insert("mhcapp.gn.login.withdrawUser", param);		
	}

	public int userWithdrawCheck(Map<String, Object> param) throws Exception{
		return selectOne("mhcapp.gn.login.userWithdrawCheck", param);
	}
	
	/**
	 * 법정대리인 등록
	 * @param param
	 * @return
	 */
	public int legalGuardianRegit(Map<String, Object> param) throws Exception{
		return insert("mhcapp.gn.login.legalGuardianRegit", param);
	}
	/**
	 * 14세 미만 userId 찾기
	 * @param param
	 * @return
	 */
	public List<Map<String, Object>> findMinorUser(Map<String, Object> param) {		
		return selectList("mhcapp.gn.login.findMinorUser", param);
	}
}
