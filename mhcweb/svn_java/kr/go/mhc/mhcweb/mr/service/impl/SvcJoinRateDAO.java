package kr.go.mhc.mhcweb.mr.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

/**
  * @Class Name : SvcJoinRateDAO.java
 * @Description : 관리자 WEB에서 사용하는 서비스 참여율 업무 DataBase 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.11.15		이은주			최초생성
 *
 * @author gst
 * @since 2016.11.15
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Repository("web.mr.SvcJoinRateDAO")
public class SvcJoinRateDAO extends EgovAbstractMapper {

	//서비스 참여율 목록 조회
	public List<Map<String, Object>> svcJoinRateList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.mr.svcjoinrate.svcJoinRateList", param);
		return rsList;
	}
}
