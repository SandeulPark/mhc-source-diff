package kr.go.mhc.mhcweb.mr.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

/**
 * @Class Name : VisitExptDAO.java
 * @Description : 관리자 WEB에서 사용하는 방문예정 업무 DataBase 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.10.24		이은주			최초생성
 *
 * @author gst
 * @since 2016.10.24
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Repository("web.mr.VisitExptDAO")
public class VisitExptDAO extends EgovAbstractMapper {
	
	//방문예정 목록 조회
	public List<Map<String, Object>> visitExptList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.mr.visitexpt.visitExptList", param);
		return rsList;
	}
	
	//방문예정 목록 조회
	public List<Map<String, Object>> selectVisitAllList(Map<String, Object> param) throws Exception {
		List<Map<String, Object>> rsList = selectList("mhc.web.mr.visitexpt.selectVisitAllList", param);
		return rsList;
	}
		
	public int updateVisitList(Map<String, Object> param) throws Exception {
		
		return update("mhc.web.mr.visitexpt.updateVisitList", param);			  
	}
	
}
