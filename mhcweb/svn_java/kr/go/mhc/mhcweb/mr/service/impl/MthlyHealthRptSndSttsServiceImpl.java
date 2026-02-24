package kr.go.mhc.mhcweb.mr.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.go.mhc.mhcweb.mr.service.MthlyHealthRptSndSttsService;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * @Class Name :MthlyHealthRptSndSttsServiceImpl.java
 * @Description : 관리자 WEB에서 사용하는 월간 건강리포트 발송현황 업무에 필요한 DAO와 연동 관리하는 Class
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

@Service(value="web.mr.MthlyHealthRptSndSttsService")
public class MthlyHealthRptSndSttsServiceImpl extends EgovAbstractServiceImpl implements MthlyHealthRptSndSttsService {
	
	@Resource(name= "web.mr.MthlyHealthRptSndSttsDAO")
	private MthlyHealthRptSndSttsDAO mthlyHealthRptSndSttsDAO;

	//월간 건강리포트 발송현황 목록 조회
	@Override
	public List<Map<String, Object>> getMthlyHealthRptSndSttsList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String, Object>> rsList = mthlyHealthRptSndSttsDAO.getMthlyHealthRptSndSttsList(param);
		return rsList;
	}

}