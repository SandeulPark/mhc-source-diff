package kr.or.khealth.smhc.smhcweb.sv.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.or.khealth.smhc.smhcweb.sv.service.SelfMissionMngtService;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;


/**
 * @Class Name :SelfMissionMngtServiceImpl.java
 * @Description : 관리자 WEB에서 사용하는 자체 미션설정 관리업무에 필요한 DAO와 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2020.11.09		양현우		최초생성
 
 * @author thejoin
 * @since 2020.11.09
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Service(value = "web.sv.SelfMissionMngtService")
public class SelfMissionMngtServiceImpl extends EgovAbstractServiceImpl implements SelfMissionMngtService{
	
	@Resource(name="web.sv.SelfMissionMngtDAO")
	private SelfMissionMngtDAO selfMissionMngtDAO;

	@Override
	public List<Map<String, Object>> selectSelfMissionMngtList(Map<String, Object> param) throws Exception {
		return selfMissionMngtDAO.selectSelfMissionMngtList(param);
	}

	@Override
	public int insertSelfMissionMngt(Map<String, Object> param) throws Exception {
		return selfMissionMngtDAO.insertSelfMissionMngt(param);
	}
	
	@Override
	public int updSelfMissionMngt(Map<String, Object> param) throws Exception {
		return selfMissionMngtDAO.updSelfMissionMngt(param);
	}

	@Override
	public Map<String, Object> selectSelfMissionMngtCount(Map<String, Object> param) throws Exception {
		return selfMissionMngtDAO.selectSelfMissionMngtCount(param);
	}


	
	

}
