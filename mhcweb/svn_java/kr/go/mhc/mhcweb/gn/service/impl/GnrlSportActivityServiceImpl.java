package kr.go.mhc.mhcweb.gn.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import kr.go.mhc.mhcweb.gn.service.GnrlSportActivityService;

/**
 * @Class Name :SportActivityServiceImpl.java
 * @Description : 관리자 WEB에서 사용하는 스포츠 활동 인증 현황에 필요한 DAO와 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자		수정내용
 * @	----------		------		---------------------------
 * @	2021.12.09		chyoon		최초생성
 * 
 * @author	chyoon
 * @since	2021.12.09
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Service(value="web.gn.SportActivityService")
public class GnrlSportActivityServiceImpl extends EgovAbstractServiceImpl implements GnrlSportActivityService{

	@Resource(name= "web.gn.SportActivityDAO")
	private GnrlSportActivityDAO sportActivityDAO;
	
	@Override
	public List<Map<String, Object>> getSportActivityUserList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return sportActivityDAO.getSportActivityUserList(param);
	}

	@Override
	public Map<String, Object> getSportActivityCertMsg(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return sportActivityDAO.getSportActivityCertMsg(param);
	}
	
	@Override
	public Map<String, Object> userInfo(String param) throws Exception {		
		return sportActivityDAO.userInfo(param);
	}
}
