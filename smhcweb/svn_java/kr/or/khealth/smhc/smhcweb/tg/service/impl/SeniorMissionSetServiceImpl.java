package kr.or.khealth.smhc.smhcweb.tg.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.or.khealth.smhc.smhcweb.tg.service.SeniorMissionSetService;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;


/**
 * @Class Name :SeniorMissionSetServiceImpl.java
 * @Description : 관리자 WEB에서 사용하는 대면정보 (미션설정) 에 필요한 DAO와 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2020.10.10		오샘이			최초생성
 *
 * @author theJoin
 * @since 2020.10.10
 * @version 1.0
 * @see
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Service(value= "web.tg.SeniorMissionSetService")
public class SeniorMissionSetServiceImpl extends EgovAbstractServiceImpl implements SeniorMissionSetService{
	
	@Resource(name="web.tg.SeniorMissionSetDAO")
	private SeniorMissionSetDAO seniorMissionSetDAO;
	
	@Override
	public List<Map<String, Object>> selectMissionSetList(Map<String, Object> param) throws Exception {
		return seniorMissionSetDAO.selectMissionSetList(param);
	}

	@Override
	public int insertMissionSet(Map<String, Object> param) throws Exception {
		return seniorMissionSetDAO.insertMissionSet(param);
	}

	@Override
	public List<Map<String, Object>> selectUserMissionList(Map<String, Object> param) throws Exception {
		return seniorMissionSetDAO.selectUserMissionList(param);
	}

	@Override
	public int insertSeniorDrugInfo(Map<String, Object> param) throws Exception {
		return seniorMissionSetDAO.insertSeniorDrugInfo(param);
	}

	@Override
	public List<Map<String, Object>> selectUserMissionChk( Map<String, Object> param) throws Exception {
		return seniorMissionSetDAO.selectUserMissionChk(param);
	}

	@Override
	public String selectUserSvcMngt(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return seniorMissionSetDAO.selectUserSvcMngt(param);
	}	
	
	
}
