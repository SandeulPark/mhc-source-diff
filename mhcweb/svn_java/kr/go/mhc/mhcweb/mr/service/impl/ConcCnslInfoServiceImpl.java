package kr.go.mhc.mhcweb.mr.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.go.mhc.mhcweb.mr.service.ConcCnslInfoService;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * @Class Name :ConcCnslInfoServiceImpl.java
 * @Description : 관리자 WEB에서 사용하는 집중상담 정보 업무에 필요한 DAO와 연동 관리하는 Class
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

@Service(value="web.mr.ConcCnslInfoService")
public class ConcCnslInfoServiceImpl extends EgovAbstractServiceImpl implements ConcCnslInfoService {
	
	@Resource(name= "web.mr.ConcCnslInfoDAO")
	private ConcCnslInfoDAO concCnslInfoDAO;

	//집중상담 정보 목록 조회
	@Override
	public List<Map<String, Object>> getConcCnslInfoList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String, Object>> rsList = concCnslInfoDAO.getConcCnslInfoList(param);
		return rsList;
	}

}