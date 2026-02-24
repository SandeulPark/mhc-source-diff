package kr.go.mhc.mhcweb.sv.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import kr.go.mhc.mhcweb.sv.service.SvcBgnAppointService;

/**
 * @Class Name :SvcMngtServiceImpl.java
 * @Description : 관리자 WEB에서 사용하는 건강정보관리에 필요한 DAO와 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일				수정자			수정내용
 * @	----------		----		---------------------------
 * @	2016.08.08		이태석			최초생성
 *
 * @author gst
 * @since 2016.08.08
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Service("web.sv.SvcBgnAppointService")
public class SvcBgnAppointServiceImpl extends EgovAbstractServiceImpl implements SvcBgnAppointService{
	
	@Resource(name="web.sv.SvcBgnAppointDAO")
	private SvcBgnAppointDAO svcBgnAppointDAO;

	@Override
	public List<Map<String, String>> selectServiceBeginApList(Map<String, Object> param) throws Exception{
		return svcBgnAppointDAO.selectServiceBeginApList(param);
	}
	
	@Override
	public Map<String, Object> selectServiceBeginApDtls(Map<String, Object> param) throws Exception{
		return svcBgnAppointDAO.selectServiceBeginApDtls(param);
	}
	
	@Override
	public List<Map<String, Object>> selectServiceSchedule(Map<String, Object> param) throws Exception{
		return svcBgnAppointDAO.selectServiceSchedule(param);
	}
	
	@Override
	public void updateReExamSttus(Map<String, Object> param) throws Exception{
		svcBgnAppointDAO.updateReExamSttus(param);
	}
	
}
