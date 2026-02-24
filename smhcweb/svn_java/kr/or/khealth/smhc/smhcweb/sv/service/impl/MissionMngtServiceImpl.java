package kr.or.khealth.smhc.smhcweb.sv.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.or.khealth.smhc.smhcweb.sv.service.MissionMngtService;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * @Class Name :MissionMngtServiceImpl.java
 * @Description : 관리자 WEB에서 사용하는 미션설정 관리업무에 필요한 DAO와 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.08.12		이은주			최초생성
 
 * @author gst
 * @since 2016.08.12
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Service(value= "web.sv.MissionMngtService")
public class MissionMngtServiceImpl extends EgovAbstractServiceImpl implements MissionMngtService {
	
	@Resource(name="web.sv.MissionMngtDAO")
	private MissionMngtDAO missionMngtDAO;

	@Override
	public List<Map<String, Object>> selectMissionMngtList(Map<String, Object> param) throws Exception {
		return missionMngtDAO.selectMissionMngtList(param);
	}

	@Override
	public List<Map<String, Object>> setDeviceInfo(Map<String, Object> param) throws Exception {
		return missionMngtDAO.setDeviceInfo(param);
	}

	@Override
	public List<Map<String, Object>> setMissionInfo(Map<String, Object> param) throws Exception {
		return missionMngtDAO.setMissionInfo(param);
	}

	@Override
	public int updMissionSet(Map<String, Object> param) throws Exception {
		return missionMngtDAO.updMissionSet(param);
	}

	@Override
	public int updDrugInfo(Map<String, Object> param) throws Exception {
		return missionMngtDAO.updDrugInfo(param);
	}

	@Override
	public int updDrugMissionSet(Map<String, Object> param) throws Exception {
		return missionMngtDAO.updDrugMissionSet(param);
	}
	
}
