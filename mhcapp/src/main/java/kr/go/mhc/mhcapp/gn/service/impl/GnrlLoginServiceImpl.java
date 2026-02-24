package kr.go.mhc.mhcapp.gn.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.go.mhc.mhcapp.gn.service.GnrlLoginService;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("gn.gnrlLoginService")
public class GnrlLoginServiceImpl extends EgovAbstractServiceImpl implements GnrlLoginService{
	
	@Resource(name="gn.gnrlLoginDAO")
	private GnrlLoginDAO gnrlLoginDAO;
	
	public int checkLoginId(Map<String, Object> param) throws Exception{
		return gnrlLoginDAO.checkLoginId(param);
	}
	
	public Map<String, Object> userTypeCheck(Map<String, Object> param) throws Exception{
		return gnrlLoginDAO.userTypeCheck(param);
	}
	
	public Map<String, String> login(Map<String, Object> param) throws Exception{		
		return gnrlLoginDAO.login(param);
	}
	
	public Map<String, String> snsLogin(Map<String, Object> param) throws Exception{		
		return gnrlLoginDAO.snsLogin(param);
	}
	
	public int userRegit(Map<String, Object> param) throws Exception{
		int rtnInt = 0;
		
		String userId = gnrlLoginDAO.selectGetUserId();
		String snsType = (String) param.get("SNS_TYPE");
		String childYn = (String) param.get("CHILD_YN");
		
		param.put("USER_ID", userId);
		
		rtnInt = gnrlLoginDAO.userRegit(param);
		rtnInt = gnrlLoginDAO.gnUserRegit(param);
		
		if("Y".equals(childYn)) {
			rtnInt = gnrlLoginDAO.legalGuardianRegit(param);
		}
		if(!"".equals(snsType)){
			rtnInt = gnrlLoginDAO.userSnsRegit(param);
		}
		
		return rtnInt;
	}
	
	public int updateToken(Map<String, Object> param) throws Exception{
		return gnrlLoginDAO.updateToken(param);
	}

	@Override
	public List<Map<String, Object>> findUser(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = new ArrayList<>();
		String childYn = (String) param.get("CHILD_YN");
		
		if("Y".equals(childYn)) {
			rsList = gnrlLoginDAO.findMinorUser(param);
		}else {
			rsList = gnrlLoginDAO.findUser(param);
		}
		
		return rsList;
	}
	
	@Override
	public Map<String, Object> pwUpdate(Map<String, Object> param) throws Exception {
		return gnrlLoginDAO.pwUpdate(param);
	}

	/**
	 * 회원 탈퇴
	 * @param
	 * @return
	 * @throws Exception
	 */
	@Override
	public void userDropout(Map<String, Object> param) throws Exception {
		gnrlLoginDAO.userDropout(param);
	}

	@Override
	public Map<String, Object> withdrawCheckUser(Map<String, Object> param) throws Exception {
		return gnrlLoginDAO.withdrawCheckUser(param);
	}

	@Override
	public void withdrawUser(Map<String, Object> param) throws Exception {
		gnrlLoginDAO.withdrawUser(param);
	}

	@Override
	public int userWithdrawCheck(Map<String, Object> param) throws Exception {
		return gnrlLoginDAO.userWithdrawCheck(param);
	}
}
