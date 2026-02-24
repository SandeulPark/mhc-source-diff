package kr.go.mhc.mhcweb.mr.service.impl;

import java.util.List;
import java.util.Map;

import kr.go.mhc.common.DMultiEgovAbstractMapper;

import org.springframework.stereotype.Repository;

/**
 * @Class Name : MthlyHealthRptSndSttsDAO.java
 * @Description : 관리자 WEB에서 사용하는 월간 건강리포트 발송현황 업무 DataBase 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자		수정내용
 * @	----------		------		---------------------------
 * @	2016.09.20		전정은		최초생성
 *
 * @author	gst
 * @since	2016.09.20
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Repository("web.mr.MthlyHealthRptSndSttsDAO")
public class MthlyHealthRptSndSttsDAO extends DMultiEgovAbstractMapper {

	//월간 건강리포트 발송현황 목록 조회
	public List<Map<String, Object>> getMthlyHealthRptSndSttsList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.mr.mthlyhealthrptsndstts.mthlyHealthRptSndSttsList", param);
		return rsList;
	}
	
}