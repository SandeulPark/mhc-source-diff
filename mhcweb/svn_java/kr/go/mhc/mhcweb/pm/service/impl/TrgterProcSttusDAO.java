package kr.go.mhc.mhcweb.pm.service.impl;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Class Name : TrgterProcSttusDAO.java
 * @Modification Information
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Repository("web.pm.TrgterProcSttusDAO")
public class TrgterProcSttusDAO extends EgovAbstractMapper {

	//대상자 등록 일반 현황 조회
	public List<Map<String, Object>> selectTrgterProcSttusList(Map<String, Object> param) throws Exception {

		List<Map<String, Object>> rsList = selectList("mhc.web.pm.trgterprocsttus.selectTrgterProcSttusList", param);
		return rsList;
	}

	/**
	 * 추후관리 스케줄 생성
	 * @param param
	 * @return
	 */
	public void trgterProcSttusAfterSchedule(Map<String, Object> param) throws Exception{
		insert("mhc.web.pm.trgterprocsttus.insertTrgterAfterSchedule", param);
		update("mhc.web.pm.trgterprocsttus.updateTrgterAftMngtEndYn", param);
	}

	/**
	 * 추후관리 스케줄 생성 후 설문지 마스터 업데이트
	 * @param param
	 * @return
	 */
	public int updateTrgterProcSttus(Map<String, Object> param) throws Exception {
		int rsList = insert("mhc.web.pm.trgterprocsttus.updateTrgterProcSttus", param);
		return rsList;
	}

}
