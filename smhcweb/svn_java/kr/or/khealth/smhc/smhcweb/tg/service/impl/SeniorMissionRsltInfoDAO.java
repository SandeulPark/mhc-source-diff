package kr.or.khealth.smhc.smhcweb.tg.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

/**
 * @Class Name : SeniorMissionRsltInfoDAO.java
 * @Description : 관리자 WEB에서 사용하는 어르신 미션실천현황 조회 업무 DataBase 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2020.10.19	
 *
 * @author thejoin
 * @since 2020.10.19
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Repository("web.tg.SeniorMissionRsltInfoDAO")
public class SeniorMissionRsltInfoDAO extends EgovAbstractMapper{

	//미션실천현황 리스트 조회
	public List<Map<String, Object>> selectMissionRsltInfoList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("smhc.web.tg.seniormissionrslt.selectMissionRsltInfoList", param);
		return rsList;
		}
	//외출미션인증사진 리스트 조회
	public List<Map<String, Object>> selectPhotoRsltInfoList(Map<String, Object> param) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> rsList = selectList("smhc.web.tg.seniormissionrslt.selectPhotoRsltInfoList", param);
		return rsList;
	}
}
