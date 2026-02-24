package kr.or.khealth.smhc.smhcweb.mr.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

/**
 * @Class Name : TrgterInfoMngtDAO.java
 * @Description : 관리자 WEB에서 사용하는 어르신 대상자 조회 업무 DataBase 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2020.09.16		양현우			수정
 *
 * @author thejoin
 * @since 2020.09.16
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Repository("web.mr.MeasrStatusDAO")
public class MeasrStatusDAO extends EgovAbstractMapper{

	public List<Map<String, Object>> selectMeasrStatusList(Map<String, Object> param) {
		List<Map<String, Object>> rsList = selectList("smhc.web.mr.measrstatus.selectMeasrStatusList",param);
		return rsList;
	}

	public Map<String, Object> unmeasuredCount(Map<String, Object> param) {
		return selectOne("smhc.web.mr.measrstatus.unmeasuredCount",param);
	}
}
