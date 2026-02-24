package kr.go.mhc.mhcapp.gn.service;

import java.util.List;
import java.util.Map;

public interface GnrlLoginService {
	
	public int checkLoginId(Map<String, Object> param) throws Exception;
	
	public Map<String, Object> userTypeCheck(Map<String, Object> param) throws Exception;
	
	public Map<String, String> login(Map<String, Object> param) throws Exception;
	
	public Map<String, String> snsLogin(Map<String, Object> param) throws Exception;
	
	public int userRegit(Map<String, Object> param) throws Exception;
	
	public int updateToken(Map<String, Object> param) throws Exception;
	
	public List<Map<String, Object>> findUser(Map<String, Object> param) throws Exception;
	
	public Map<String, Object> pwUpdate(Map<String, Object> param) throws Exception;

	/**
	 * 회원 탈퇴
	 * @param
	 * @return
	 * @throws Exception
	 */
	public void userDropout(Map<String, Object> param) throws Exception;

	public Map<String, Object> withdrawCheckUser(Map<String, Object> param)  throws Exception;

	public void withdrawUser(Map<String, Object> param) throws Exception;

	public int userWithdrawCheck(Map<String, Object> param)  throws Exception;

}
