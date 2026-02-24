package kr.go.mhc.mhcweb.sv.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.go.mhc.mhcweb.sv.service.PractMissonSchMngtService;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * @Class Name : PractMissonSchMngtServiceImpl.java
 * @Description : 관리자 WEB에서 사용하는 실천미션 일정을 관리하는 DAO와 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2017.04.06		이태석			최초생성
 *
 * @author thejoin
 * @since 2017.04.06
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Service("web.sv.PractMissonSchMngtService")
public class PractMissonSchMngtServiceImpl extends EgovAbstractServiceImpl implements PractMissonSchMngtService{

	@Resource(name = "web.sv.PractMissonSchMngtServiceDAO")
	private PractMissonSchMngtServiceDAO practMissonSchMngtServiceDAO;

	@Override
	public List<Map<String, Object>> getPractMissonSchList(Map<String, Object> param) throws Exception {
		return practMissonSchMngtServiceDAO.getPractMissonSchList(param);
	}
	@Override
	public List<Map<String, Object>> getPractMissonCdList(Map<String, Object> param) throws Exception {
		return practMissonSchMngtServiceDAO.getPractMissonCdList(param);
	}
	//추가
	@Override
	public List<Map<String, Object>> getAllMissionCDList(Map<String, Object> param) throws Exception {
		return practMissonSchMngtServiceDAO.getAllMissionCDList(param);
	}
	//추가
	@Override
	public int updatePublicHealthMissionDelete(Map<String, Object> param) throws Exception {
		return practMissonSchMngtServiceDAO.updatePublicHealthMissionDelete(param);
	}
	//추가
	@Override
	public int updatePublicHealthMissionUpdate(Map<String, Object> param) throws Exception {
		return practMissonSchMngtServiceDAO.updatePublicHealthMissionUpdate(param);
	}
	//추가
	@Override
	public Map<String, Object> insertPublicHealthMisson(Map<String, Object> param)throws Exception {
		return practMissonSchMngtServiceDAO.insertPublicHealthMisson(param);
	}
	//추가
	@Override
	public List<Map<String, Object>> selectPublicMissionFile(Map<String, Object> param) throws Exception {
		return practMissonSchMngtServiceDAO.selectPublicMissionFile(param);
	}
	@Override
	public int updatePractMissionSchCd(Map<String, Object> param)	throws Exception {
		return practMissonSchMngtServiceDAO.updatePractMissionSchCd(param);
	}
	
	@Override
	public int getSelWeekTrgterChk(Map<String, Object> param) throws Exception {
		return practMissonSchMngtServiceDAO.getSelWeekTrgterChk(param);
	}

	/* ################################################################################# */
	/* ######################### 만성질환 실천미션 추가 202304 ######################### */
	@Override
	public List<Map<String, Object>> getPractMissonChronicSchList(Map<String, Object> param) throws Exception {
		return practMissonSchMngtServiceDAO.getPractMissonChronicSchList(param);
	}

	@Override
	public List<Map<String, Object>> getPractMissonCdChronicList(Map<String, Object> param) throws Exception {
		return practMissonSchMngtServiceDAO.getPractMissonCdChronicList(param);
	}

	@Override
	public List<Map<String, Object>> getAllMissionCDChronicList(Map<String, Object> param) throws Exception {
		return practMissonSchMngtServiceDAO.getAllMissionCDChronicList(param);
	}

	@Override
	public Map<String, Object> insertPublicHealthMissonChronic(Map<String, Object> param) throws Exception {
		return practMissonSchMngtServiceDAO.insertPublicHealthMissonChronic(param);
	}

	@Override
	public int updatePublicHealthMissionChronicUpdate(Map<String, Object> param) throws Exception {
		return practMissonSchMngtServiceDAO.updatePublicHealthMissionChronicUpdate(param);
	}

	@Override
	public int updatePractMissionChronicSchCd(Map<String, Object> param) throws Exception {
		return practMissonSchMngtServiceDAO.updatePractMissionChronicSchCd(param);
	}
}
