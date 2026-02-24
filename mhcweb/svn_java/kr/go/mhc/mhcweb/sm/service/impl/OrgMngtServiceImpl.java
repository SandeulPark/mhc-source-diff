package kr.go.mhc.mhcweb.sm.service.impl;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import kr.go.mhc.mhcweb.sm.service.OrgMngtService;

/**
 * @Class Name :OrgMngtServiceImpl.java
 * @Description : 관리자 WEB에서 사용하는 기관정보 화면에 필요한 DAO와 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자		수정내용
 * @	----------		------		---------------------------
 * @	2017.04.12		이현규		최초생성
 *
 * @author theJoin
 * @since 2017.04.12
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Service("web.sm.OrgMngtService")
public class OrgMngtServiceImpl extends EgovAbstractServiceImpl implements OrgMngtService {

	@Resource(name="web.sm.OrgMngtDAO")
	private OrgMngtDAO orgMngtDAO;

	@Override
	public List<Map<String, String>> getOrgMngtList(Map<String, Object> param) throws Exception {
		return orgMngtDAO.getOrgMngtList(param);
	}

	@Override
	public List<Map<String, String>> getOrgDtlsList(Map<String, Object> param) throws Exception {
		return orgMngtDAO.getOrgDtlsList(param);
	}

	@Override
	public int saveOrgMngt(Map<String, Object> param) throws Exception {
		return orgMngtDAO.saveOrgMngt(param);
	}

	//20191203양현우 추가
	@Override
	public List<Map<String, Object>> selectOrgMngtPop(Map<String, Object> param)throws Exception {
		return orgMngtDAO.selectOrgMngtPop(param);
	}
	//20191203양현우 추가 끝


	@Override
	public int updateOrgApprovalYn(Map<String, Object> param) throws Exception {
		return orgMngtDAO.updateOrgApprovalYn(param);
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly = false)
	public int saveOrgMngter(Map<String, Object> param) throws Exception {
		int rsInt2 = orgMngtDAO.saveOrgMngt(param); //기관등록				
		param.put("rsInt2", rsInt2);
		return orgMngtDAO.saveOrgMngter(param);//담당자등록
	}

	@Override
	public List<Map<String, String>> selectOrgList(Map<String, Object> param) throws Exception {
		return orgMngtDAO.selectOrgList(param);
	}

}
