package kr.or.khealth.smhc.smhcweb.tg.service.impl;

import java.util.List;
import java.util.Map;

import kr.or.khealth.smhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;

/**
 * @Class Name : TrgterInfoMngtDAO.java
 * @Description : 관리자 WEB에서 사용하는  어르신 대면평가정보등록  DataBase 연동 관리하는 Class
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

@Repository("web.tg.FaceToFaceInfoRegDAO")
public class FaceToFaceInfoRegDAO extends DMultiEgovAbstractMapper{

	
	public List<Map<String, Object>> selectFaceToFaceInfoList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("smhc.web.tg.facetofaceinforeg.selectFaceToFaceInfoList", param);
		return rsList;
	}
	
	public Map<String,Object> selectFaceToFaceSeniorDtls(Map<String, Object> param) throws Exception{
		Map<String, Object> rsMap = selectOne("smhc.web.tg.facetofaceinforeg.selectFaceToFaceSeniorDtls", param);
		return rsMap;
	}
	
}
