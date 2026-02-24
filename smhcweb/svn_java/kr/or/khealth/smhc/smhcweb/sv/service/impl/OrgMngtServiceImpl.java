package kr.or.khealth.smhc.smhcweb.sv.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.khealth.smhc.smhcweb.sv.service.OrgMngtService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * @Class Name :OrgMngtServiceImpl.java
 * @Description : 관리자 WEB에서 사용하는 기관정보 화면에 필요한 DAO와 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자		수정내용
 * @	----------		------		---------------------------
 * @	2020.09.22		양현우			최초생성
 *
 * @author theJoin
 * @since 2020.09.22
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */
@Service(value= "web.sv.OrgMngtService")
public class OrgMngtServiceImpl extends EgovAbstractServiceImpl implements OrgMngtService{

	@Resource(name="web.sv.OrgMngtDAO")
	private OrgMngtDAO orgMngtDAO;
	
	@Override
	public List<Map<String, String>> selectOrgMngtList(Map<String, Object> param) throws Exception {
		return orgMngtDAO.selectOrgMngtList(param);
	}
	
	@Override
	public int saveOrgMngt(Map<String, Object> param) throws Exception {
		return orgMngtDAO.saveOrgMngt(param);
	}
}
