package kr.go.mhc.mhcweb.gn.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import kr.go.mhc.mhcweb.gn.service.GnrlUserAppActHistService;
import kr.go.mhc.mhcweb.gn.service.GnrlUserInfoMngtService;

/**
 * @Class Name :GnrlUserInfoMngtServiceImpl.java
 * @Description : 관리자 WEB에서 사용하는 대상자 정보 변경에 필요한 DAO와 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자		수정내용
 * @	----------		------		---------------------------
 * @	2019.11.22					최초생성
 *
 * @author theJoin
 * @since 
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Service("web.gn.GnrlUserAppActHistService")
public class GnrlUserAppActHistServiceImpl extends EgovAbstractServiceImpl implements GnrlUserAppActHistService {

	@Resource(name = "web.gn.GnrlUserAppActHistDAO")
	private GnrlUserAppActHistDAO gnrlUserAppActHistDAO;
	
	@Override
	public List<Map<String, Object>> trgtAppActList(Map<String, Object> param)throws Exception {
		return gnrlUserAppActHistDAO.trgtAppActList(param);
	}
	
	@Override
	public int trgtAppActListCount(Map<String, Object> param) throws Exception {
		return gnrlUserAppActHistDAO.trgtAppActListCount(param);
	}


}
