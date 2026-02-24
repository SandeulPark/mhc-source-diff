package kr.go.mhc.mhcweb.sm.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import kr.go.mhc.mhcweb.sm.service.HealthDisValMngtService;

/**
 * @Class Name :HealthDisValMngtServiceImpl.java
 * @Description : 관리자 WEB에서 사용하는 그룹설정에 필요한 DAO와 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일				수정자			수정내용
 * @	----------		----		---------------------------
 * @	2017.02.17		나연이			최초생성
 *
 * @author theJoin
 * @since 2017.02.17
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Service("web.sm.HealthDisValMngtService")
public class HealthDisValMngtServiceImpl extends EgovAbstractServiceImpl implements HealthDisValMngtService{

	@Resource(name="web.sm.HealthDisValMngtServiceDAO")
	private HealthDisValMngtServiceDAO healthDisValMngtServiceDAO;
	
	@Override
	public List<Map<String, String>> getHealthDisValList( Map<String, Object> param) throws Exception {
		return healthDisValMngtServiceDAO.getHealthDisValList(param);
	}
	
	@Override
	public List<Map<String, Object>> getOrgCdList(Map<String, Object> param) throws Exception {
		return healthDisValMngtServiceDAO.getOrgCdList(param);
	}
	
	@Override
	public int mergeHealthDisVal(Map<String, Object> param) throws Exception {
		return healthDisValMngtServiceDAO.mergeHealthDisVal(param);
	}
	
}
