package kr.or.khealth.smhc.smhcweb.sv.service.impl;

import java.util.List;
import java.util.Map;

import kr.or.khealth.smhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;

/**
 * @Class Name : PointRankingDAO.java
 * @Description : 관리자 WEB에서 사용하는 포인트 및 랭킹 업무 DataBase 연동 관리하는 Class
 * @Modification Information
* @
 * @	수정일			수정자		수정내용
 * @	----------		------		---------------------------
 * @	2016.11.28		이태석		최초생성
 *
 * @author	gst
 * @since	2016.11.28
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Repository("web.sv.PointMngtDAO")
public class PointMngtDAO extends DMultiEgovAbstractMapper {

	public List<Map<String, Object>> selectPointMngtList(Map<String, Object> param) {
		List<Map<String, Object>> rsList = selectList("smhc.web.sv.pointmngt.selectPointMngtList",param);
		return rsList;
	}

	public List<Map<String, Object>> searchPointLogPop(Map<String, Object> param) {
		List<Map<String, Object>> rsList = selectList("smhc.web.sv.pointmngt.searchPointLogPop",param);
		return rsList;
	}
}