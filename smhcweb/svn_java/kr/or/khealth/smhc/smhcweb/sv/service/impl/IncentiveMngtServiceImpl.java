package kr.or.khealth.smhc.smhcweb.sv.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.or.khealth.smhc.smhcweb.sv.service.IncentiveMngtService;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;


/**
 * @Class Name :TrgterInfoMngtServiceImpl.java
 * @Description : 관리자 WEB에서 사용하는 어르신 미션실천현황에 필요한 DAO와 연동 관리하는 Class
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

@Service(value="web.sv.IncentiveMngtService")
public class IncentiveMngtServiceImpl extends EgovAbstractServiceImpl implements IncentiveMngtService{
	
	@Resource(name="web.sv.IncentiveMngtDAO")
	private IncentiveMngtDAO incentiveMngtDAO;

	@Override
	public List<Map<String, Object>> selectIncentiveTarget(Map<String, Object> param) throws Exception {
		return incentiveMngtDAO.selectIncentiveTarget(param);
	}

	@Override
	public List<Map<String, Object>> searchIncentiveLogPop(Map<String, Object> param) throws Exception {
		return incentiveMngtDAO.searchIncentiveLogPop(param);
	}

	@Override
	public int paymentPoint(Map<String, Object> param) throws Exception {
		return incentiveMngtDAO.paymentPoint(param);
	}
	

}
