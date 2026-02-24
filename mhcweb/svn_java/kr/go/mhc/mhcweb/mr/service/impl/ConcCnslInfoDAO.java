package kr.go.mhc.mhcweb.mr.service.impl;

import java.util.List;
import java.util.Map;

import kr.go.mhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;

/**
 * @Class Name : ConcCnslInfoDAO.java
 * @Description : 관리자 WEB에서 사용하는 집중상담 정보 업무 DataBase 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자		수정내용
 * @	----------		------		---------------------------
 * @	2016.09.22		전정은		최초생성
 *
 * @author	gst
 * @since	2016.09.22
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Repository("web.mr.ConcCnslInfoDAO")
public class ConcCnslInfoDAO extends DMultiEgovAbstractMapper {

	//집중상담 정보 목록 조회
	public List<Map<String, Object>> getConcCnslInfoList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.mr.concCnslInfo.concCnslInfoList", param);
		return rsList;
	}
	
}