package kr.or.khealth.smhc.smhcweb.sv.service.impl;

import java.util.List;
import java.util.Map;

import kr.or.khealth.smhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;


/**
 * @Class Name : TrgterInfoMngtDAO.java
 * @Description : 관리자 WEB에서 사용하는 어르신 미션실천현황  DataBase 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2020.09.16		양현우			최초생성
 *
 * @author thejoin
 * @since 2020.09.16
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */


@Repository("web.sv.IncentiveMngtDAO")
public class IncentiveMngtDAO extends DMultiEgovAbstractMapper{

	public List<Map<String, Object>> selectIncentiveTarget(Map<String, Object> param) {
		List<Map<String, Object>> rsList = selectList("smhc.web.sv.incentivemngt.selectIncentiveTarget",param);
		return rsList;
	}

	public List<Map<String, Object>> searchIncentiveLogPop(Map<String, Object> param) {
		List<Map<String, Object>> rsList = selectList("smhc.web.sv.incentivemngt.searchIncentiveLogPop",param);
		return rsList;
	}

	public int paymentPoint(Map<String, Object> param) throws Exception {
		int rsInt = insert("smhc.web.sv.incentivemngt.paymentPoint",param);	
		return rsInt;
	}

}
