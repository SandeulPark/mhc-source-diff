package kr.go.mhc.mhcweb.mr.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

/**
 * @Class Name : SvcJoinInfoDAO.java
 * @Description : 관리자 WEB에서 사용하는 서비스 참여정보 업무 DataBase 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.09.19		이은주			최초생성
 *
 * @author gst
 * @since 2016.09.19
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Repository("web.mr.SvcJoinInfoDAO")
public class SvcJoinInfoDAO extends EgovAbstractMapper {

	//서비스 참여 정보 목록 조회
	public List<Map<String, Object>> svcJoinInfoList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.mr.svcjoininfo.svcJoinInfoList", param);
		return rsList;
	}
	
	//서비스 참여 정보 2주간 미사용자 목록 조회
	public List<Map<String, Object>> svcJoinInfoTwoWeeksNull(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.mr.svcjoininfo.svcJoinInfoTwoWeeksNull", param);
		return rsList;
	}
}
