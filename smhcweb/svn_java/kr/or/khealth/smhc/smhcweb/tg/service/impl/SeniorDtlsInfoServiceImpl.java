package kr.or.khealth.smhc.smhcweb.tg.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.or.khealth.smhc.smhcweb.tg.service.SeniorDtlsInfoService;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * @Class Name : SeniorDtlsInfoController.java
 * @Description : 대면평가 등록 - 대상자 상세 정보
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2020.09.29		정준호			최초생성
 *
 * @author thejoin
 * @since 2020.09.29
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Service(value="web.tg.SeniorDtlsInfoService")
public class SeniorDtlsInfoServiceImpl extends EgovAbstractServiceImpl implements SeniorDtlsInfoService{

	@Resource(name="web.tg.SeniorDtlsInfoDAO")
	private SeniorDtlsInfoDAO seniorDtlsInfoDAO;

	@Override
	public int regUserDetail(Map<String, Object> param) throws Exception {
		return seniorDtlsInfoDAO.regUserDetail(param);
	}

	@Override
	public int updateServiceManageDetail(Map<String, Object> param) throws Exception {
		return seniorDtlsInfoDAO.updateServiceManageDetail(param);
	}
	
	@Override
	public int insertUserFormInfo(Map<String, Object> param) throws Exception {
		return seniorDtlsInfoDAO.insertUserFormInfo(param);
	}	

	@Override
	public Map<String, Object> mngtUserInfoDetailChk(Map<String, Object> param) throws Exception {
		return seniorDtlsInfoDAO.mngtUserInfoDetailChk(param);
	}

	@Override
	public Map<String, Object> setUserInfoDetail(Map<String, Object> param) throws Exception {
		return seniorDtlsInfoDAO.setUserInfoDetail(param);
	}

	@Override
	public Map<String, Object> setMultipleTimes(Map<String, Object> param) {
		return seniorDtlsInfoDAO.setMultipleTimes(param);
	}

	@Override
	public Map<String, Object> selectUserInfoDetail(Map<String, Object> param)throws Exception {
		return seniorDtlsInfoDAO.selectUserInfoDetail(param);
	}

	
}
