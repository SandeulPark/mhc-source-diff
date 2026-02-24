package kr.go.mhc.mhcweb.pm.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

/**
 * @Class Name : WorkManagerSttusDAO.java
 * @Description : 관리자 WEB에서 사용하는 업무담당자 현황 실적관리 업무 DataBase 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2018.10.11		유준영			최초생성
 *
 * @author theJoin
 * @since 2018.10.11
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Repository("web.pm.WorkManagerSttusDAO")
public class WorkManagerSttusDAO extends EgovAbstractMapper {

	//업무담당자 목록 조회
	public List<Map<String, Object>> selectWorkManagerSttusList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.pm.workmanagersttus.selectWorkManagerSttusList", param);
		return rsList;
	}
	
	
	//업무담당자 담당자 목록 조회
	public List<Map<String, Object>> selectWorkManagerSttusTrgterList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.pm.workmanagersttus.selectWorkManagerSttusTrgterList", param);
		return rsList;
	}
}




