package kr.go.mhc.mhcweb.gn.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import kr.go.mhc.mhcweb.gn.service.GnrlUserInfoMngtService;

/**
 * @Class Name :GnrlUserInfoMngtServiceImpl.java
 * @Description : 관리자 WEB에서 사용하는 대상자 정보 변경에 필요한 DAO와 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자		수정내용
 * @	----------		------		---------------------------
 * @	2019.11.22					최초생성
 *
 * @author theJoin
 * @since 
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Service("web.gn.GnrlUserInfoMngtService")
public class GnrlUserInfoMngtServiceImpl extends EgovAbstractServiceImpl implements GnrlUserInfoMngtService {

	@Resource(name = "web.gn.GnrlUserInfoMngtDAO")
	private GnrlUserInfoMngtDAO gnrlUserInfoMngtDAO;

	@Override
	public List<Map<String, Object>> selectGnrlUserInfoList(Map<String, Object> param) throws Exception {
		return gnrlUserInfoMngtDAO.selectGnrlUserInfoList(param);
	}

	@Override
	public List<Map<String, Object>> selectGnrlUserExcsList(Map<String, Object> param) throws Exception {
		return gnrlUserInfoMngtDAO.selectGnrlUserExcsList(param);
	}

	@Override
	public List<Map<String, Object>> selectGnrlUserActList(Map<String, Object> param) throws Exception {
		return gnrlUserInfoMngtDAO.selectGnrlUserActList(param);
	}

	@Override
	public List<Map<String, Object>> selectGnrlUserBodyCompList(Map<String, Object> param) throws Exception {
		return gnrlUserInfoMngtDAO.selectGnrlUserBodyCompList(param);
	}

	@Override
	public List<Map<String, Object>> selectGnrlUserBloodPressList(Map<String, Object> param) throws Exception {
		return gnrlUserInfoMngtDAO.selectGnrlUserBloodPressList(param);
	}

	@Override
	public List<Map<String, Object>> selectGnrlUserBloodSugarList(Map<String, Object> param) throws Exception {
		return gnrlUserInfoMngtDAO.selectGnrlUserBloodSugarList(param);
	}
	@Override
	public List<Map<String, Object>> selectGnrlUserMealDiaryList(Map<String, Object> param) throws Exception {
		return gnrlUserInfoMngtDAO.selectGnrlUserMealDiaryList(param);
	}
	@Override
	public List<Map<String, Object>> selectGnrlMealDiaryDtlsPop(Map<String, Object> param) throws Exception {
		return gnrlUserInfoMngtDAO.selectGnrlMealDiaryDtlsPop(param);
	}

	@Override
	public void updateUserInfo(Map<String, Object> param) throws Exception {
		gnrlUserInfoMngtDAO.updateUserInfo(param);
	}

	@Override
	public List<Map<String, Object>> selectGnrlUserSleepList(Map<String, Object> param) throws Exception {
		return gnrlUserInfoMngtDAO.selectGnrlUserSleepList(param);
	}
}
