package kr.or.khealth.smhc.smhcweb.tg.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.or.khealth.smhc.smhcweb.tg.service.SeniorTrgterRegService;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * @Class Name :TrgterInfoMngtServiceImpl.java
 * @Description : 관리자 WEB에서 사용하는 어르신 대상자 등록 업무에 필요한 DAO와 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2020.09.16		양현우			최초생성
 
 * @author thejoin
 * @since 2020.09.16
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Service(value="web.tg.SeniorTrgterRegService")
public class SeniorTrgterRegServiceImpl extends EgovAbstractServiceImpl implements SeniorTrgterRegService{

	@Resource(name="web.tg.SeniorTrgterRegDAO")
	private SeniorTrgterRegDAO seniorTrgterRegDAO;
	
	@Override
	public List<Map<String, Object>> selectSeniorTrgterList(Map<String, Object> param) throws Exception {
		return seniorTrgterRegDAO.selectSeniorTrgterList(param);
	}
	
	@Override
	public Map<String, Object> selectSeniorTrgterDtls(Map<String, Object> param) throws Exception {
		return seniorTrgterRegDAO.selectSeniorTrgterDtls(param);
	}	

	@Override
	public List<Map<String, Object>> selectManagerCombo(Map<String, Object> param) throws Exception {
		return seniorTrgterRegDAO.selectManagerCombo(param);
	}

	@Override
	public int insertSeniorTrgter(Map<String, Object> param) throws Exception {
		return seniorTrgterRegDAO.insertSeniorTrgter(param);
	}
	
	@Override
	public int updateSeniorTrgter(Map<String, Object> param) throws Exception {
		return seniorTrgterRegDAO.updateSeniorTrgter(param);
	}

	@Override
	public Map<String, Object> selectSeniorDuplicationSch(Map<String, Object> param) throws Exception {
		return seniorTrgterRegDAO.selectSeniorDuplicationSch(param);
	}	
	
	@Override
	public Map<String, Object> selectSeniorDuplicationMobileNo(Map<String, Object> param) throws Exception {
		return seniorTrgterRegDAO.selectSeniorDuplicationMobileNo(param);
	}

	@Override
	public int seniorTrgterDeleteInfo(Map<String, Object> param)throws Exception {
		return seniorTrgterRegDAO.seniorTrgterDeleteInfo(param);
	}

	@Override
	public boolean updateLoginFailCnt(Map<String, Object> param) throws Exception {
		return seniorTrgterRegDAO.updateLoginFailCnt(param);
	}
	
	@Override
	public int insertUnlockHist(Map<String, Object> param) throws Exception {
		return seniorTrgterRegDAO.insertUnlockHist(param);
	}
}
