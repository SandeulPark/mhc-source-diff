package kr.go.mhc.mhcweb.mr.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.go.mhc.mhcweb.mr.service.VisitExptService;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * @Class Name :VisitExptServiceImpl.java
 * @Description : 관리자 WEB에서 사용하는 방문예정 업무에 필요한 DAO와 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.10.24		이은주			최초생성
 
 * @author gst
 * @since 2016.10.24
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */
@Service(value = "web.mr.VisitExptService")
public class VisitExptServiceImpl extends EgovAbstractServiceImpl implements VisitExptService {
	
	@Resource(name= "web.mr.VisitExptDAO")
	private VisitExptDAO visitExptDAO;

	@Override
	public List<Map<String, Object>> visitExptList(Map<String, Object> param) throws Exception {
		return visitExptDAO.visitExptList(param);
	}
	
	@Override
	public List<Map<String, Object>> selectVisitAllList(Map<String, Object> param) throws Exception {
		return visitExptDAO.selectVisitAllList(param);
	}
		
	@Override
	public void updateVisitList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		visitExptDAO.updateVisitList(param);
		
	}
	
	
}
