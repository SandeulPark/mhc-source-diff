package kr.go.mhc.mhcweb.sm.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.go.mhc.mhcweb.sm.service.MngterRegMngtService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * @Class Name  : MngterRegMngtServiceImpl.java
 * @Description : 관리자 WEB에서 사용하는 관리자 정보를 등록에 필요한 DAO와 연동 관리하는 Class
 * @Modification Information
 * @
 * @	수정일			수정자		수정내용
 * @	----------		----		---------------------------
 * @	2017.03.15		이태석		최초생성
 *
 * @author theJoin
 * @since 2017.03.15
 * @version 1.0
 * @see
 *
 *  Copyright (C) by Mobile Health Care All right reserved.
 */

@Service("web.sm.MngterRegMngtService")
public class MngterRegMngtServiceImpl extends EgovAbstractServiceImpl implements MngterRegMngtService{

	@Resource(name="web.sm.MngterRegMngtServiceDAO")
	private MngterRegMngtServiceDAO mngterRegMngtServiceDAO;

	@Override
	public List<Map<String, String>> getRegMngterList(Map<String, Object> param) throws Exception {
		return mngterRegMngtServiceDAO.getRegMngterList(param);
	}

	@Override
	public int saveMngter(Map<String, Object> param) throws Exception {
		return mngterRegMngtServiceDAO.saveMngter(param);
	}

	@Override
	public Map<String, Object> getManagerDuplicationCnt(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return mngterRegMngtServiceDAO.getManagerDuplicationCnt(param);
	}
	
	@Override
	public int updateApprovalYn(Map<String, Object> param) throws Exception {
		return mngterRegMngtServiceDAO.updateApprovalYn(param);
	}
	
	@Override
	public int updatedn1Use(Map<String, Object> param) throws Exception {
		return mngterRegMngtServiceDAO.updatedn1Use(param);
	}
	
	@Override
	public int updatedn2Use(Map<String, Object> param) throws Exception {
		return mngterRegMngtServiceDAO.updatedn2Use(param);
	}

	@Override
	public Map<String, Object> getdigiSignPopUp(Map<String, Object> param) throws Exception {
		return mngterRegMngtServiceDAO.getdigiSignPopUp(param);
	}

	@Override
	public List<Map<String, String>> permissionsList(Map<String, Object> param) throws Exception {
		return mngterRegMngtServiceDAO.permissionsList(param);
	}
	
	@Override
	public List<Map<String, String>> userAuthList(Map<String, Object> param) throws Exception {
		return mngterRegMngtServiceDAO.userAuthList(param);
	}

	@Override
	public List<Map<String, String>> permissionsTrgterList(Map<String, Object> param) throws Exception {
		return mngterRegMngtServiceDAO.permissionsTrgterList(param);
	}
	
	@Override
	public List<Map<String, String>> perMenuList(Map<String, Object> param) throws Exception {
		return mngterRegMngtServiceDAO.perMenuList(param);
	}
	
	@Override
	public List<Map<String, String>> selectPerMenuList(Map<String, Object> param) throws Exception {
		return mngterRegMngtServiceDAO.selectPerMenuList(param);
	}
	
	@Override
	public List<Map<String, String>> selectPerMenuCnt(Map<String, Object> param) throws Exception {
		return mngterRegMngtServiceDAO.selectPerMenuCnt(param);
	}	
		
	@Override
	public List<Map<String, String>> selectAuthKey(Map<String, Object> param) throws Exception {
		return mngterRegMngtServiceDAO.selectAuthKey(param);
	}
	
	@Override
	public void perMenuGInsert(Map<String, Object> param) throws Exception {
		mngterRegMngtServiceDAO.perMenuGInsert(param);
	}
	
	@Override
	public void authListInsert(Map<String, Object> param) throws Exception {
		mngterRegMngtServiceDAO.authListInsert(param);
	}
	
	@Override
	public void authListUpdate(Map<String, Object> param) throws Exception {
		mngterRegMngtServiceDAO.authListUpdate(param);
	}
	
	@Override
	public void authListUpdate2(Map<String, Object> param) throws Exception {
		mngterRegMngtServiceDAO.authListUpdate2(param);
	}
	
	@Override
	public void authDelete(Map<String, Object> param) throws Exception {
		mngterRegMngtServiceDAO.authDelete(param);
	}
	
	@Override
	public void authList(Map<String, Object> param) throws Exception {
		mngterRegMngtServiceDAO.authList(param);
	}
	
	@Override
	public void userAuthDelete(Map<String, Object> param) throws Exception {
		mngterRegMngtServiceDAO.userAuthDelete(param);
	}
	
	@Override
	public void orgAuthUsersInsert(Map<String, Object> param) throws Exception {
		mngterRegMngtServiceDAO.orgAuthUsersInsert(param);
	}
	
	@Override
	public List<Map<String, String>> userPerCheck(Map<String, Object> param) throws Exception {
		return mngterRegMngtServiceDAO.userPerCheck(param);
	}
	
	@Override
	public List<Map<String, Object>> getOrgAuthList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return mngterRegMngtServiceDAO.getOrgAuthList(param);
	}
	
	@Override
	public List<Map<String, Object>> slectErrReport(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return mngterRegMngtServiceDAO.slectErrReport(param);
	}
	
	@Override
	public List<Map<String, Object>> checkRegUser(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return mngterRegMngtServiceDAO.checkRegUser(param);
	}
	
}
