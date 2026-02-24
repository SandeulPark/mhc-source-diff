package kr.or.khealth.smhc.common.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.or.khealth.smhc.common.DMultiEgovAbstractMapper;
import kr.or.khealth.smhc.common.service.CommonUserService;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * @Class Name : CommonUserServiceImpl.java
 * @Description : 모바일 헬스케어에서 사용하는 자용자공통모듈에 필요한 DAO와 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일				수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.08.05		김보람			최초생성
 *
 * @author gst
 * @since 2016.08.05
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Service("common.userService")
public class CommonUserServiceImpl extends EgovAbstractServiceImpl implements CommonUserService{
	
	@Resource(name="common.userDAO")
    private CommonUserDAO commonUserDAO;

	@Override
	public List<Map<String, String>> selectUserList(Map<String, Object> param) throws Exception {
		return commonUserDAO.selectUserList(param);
	}

	@Override
	public Map<String, String> selectUser(Map<String, Object> param) throws Exception {
		return commonUserDAO.selectUser(param);
	}

	@Override
	public Map<String, Object> checkLoginUser(Map<String, Object> param) throws Exception {
		return commonUserDAO.checkLoginUser(param);
	}

	@Override
	public List<Map<String, String>> checkAppInfo(Map<String, Object> param) throws Exception {
		return commonUserDAO.checkAppInfo(param);
	}
	
	@Override
	public int updateTokenInfo(Map<String, Object> param) throws Exception{
		return commonUserDAO.updateTokenInfo(param);
	}
		
	@Override
	public void setCertDn(Map<String, Object> param) throws Exception{
		commonUserDAO.setCertDn(param);
	}

	@Override
	public Map<String, Object> checkWebLoginUser(Map<String, Object> param) throws Exception {
		return commonUserDAO.checkWebLoginUser(param);
	}
	
	@Override
	public List<Map<String,Object>> checkLoginUserSttus(Map<String,Object> param) throws Exception{
		return commonUserDAO.checkLoginUserSttus(param);
	}
	
	@Override
	public int updateDnPwCheck(Map<String,Object> param) throws Exception{
		return commonUserDAO.updateDnPwCheck(param);
	}
	
	@Override
	public void updateDnLoginSucss(Map<String, Object> param) throws Exception{
		commonUserDAO.updateDnLoginSucss(param);
	}
	
	@Override
	public Map<String, Object> checkTabletLoginUser(Map<String, Object> param) throws Exception {
		return commonUserDAO.checkTabletLoginUser(param);
	}	

}
